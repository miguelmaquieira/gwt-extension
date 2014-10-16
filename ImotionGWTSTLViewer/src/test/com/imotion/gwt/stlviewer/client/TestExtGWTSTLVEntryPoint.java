package test.com.imotion.gwt.stlviewer.client;

import test.com.imotion.gwt.stlviewer.client.widget.TestExtGWTSTLVParameter;
import test.com.imotion.gwt.stlviewer.client.widget.TestExtGWTSTLVSpinner;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.imotion.gwt.stlviewer.client.exception.EXTGWTSTLException;
import com.imotion.gwt.stlviewer.client.exception.EXTGWTSTLExceptionCallback;
import com.imotion.gwt.stlviewer.client.widget.EXTGWTSTLILoaderDisplay;
import com.imotion.gwt.stlviewer.client.widget.threejs.EXTGWTSTLVLoaderWidgetThreeJS;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestExtGWTSTLVEntryPoint implements EntryPoint {

	private static final String 	DEFAULT_MODEL_PATH 		= "models/thingiverse/Doll_Multiscan.stl";
	private static final double	DEFAULT_Z_SPEED_VARIATION	= 0.01;
	private static final double	DEFAULT_X_SPEED_VARIATION	= 0.01;
	private static final double	DEFAULT_Y_SPEED_VARIATION	= 0.01;
	
	private TextBox 					pathTextBox;
	private EXTGWTSTLILoaderDisplay 	rendererWidget;
	private ListBox 					selectUrl;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		
		FlowPanel contentPanel = new FlowPanel();
		rootPanel.add(contentPanel);
		contentPanel.setWidth("720px");
		contentPanel.getElement().getStyle().setMarginLeft(50, Unit.PX);

		// FORM
		VerticalPanel formVP = new VerticalPanel();
		contentPanel.add(formVP);
		formVP.setWidth("100%");
		formVP.setSpacing(10);
		
		HorizontalPanel loadZone = new HorizontalPanel();
		formVP.add(loadZone);
		loadZone.setWidth("100%");

		//Url textBox
		pathTextBox = new TextBox();
		loadZone.add(pathTextBox);
		pathTextBox.setText(DEFAULT_MODEL_PATH);
		pathTextBox.setWidth("100%");
		loadZone.setCellWidth(pathTextBox, "70%");
		loadZone.setCellHorizontalAlignment(pathTextBox, HasHorizontalAlignment.ALIGN_RIGHT);
		
		// Parameters panel
		FlowPanel parametersPanel = new FlowPanel();
		formVP.add(parametersPanel);
		
		HorizontalPanel hrParameterPanel1 = new HorizontalPanel();
		parametersPanel.add(hrParameterPanel1);
		hrParameterPanel1.setWidth("100%");
		
		/// Zoom
		final TestExtGWTSTLVParameter zoomParam = new TestExtGWTSTLVParameter("Zoom (%)", "5", "60px", "60px");
		hrParameterPanel1.add(zoomParam);
		
		/// Color
		final TestExtGWTSTLVParameter colorParam = new TestExtGWTSTLVParameter("Color (#Hex)", "0xF0F0F0", "80px", "60px");
		hrParameterPanel1.add(colorParam);
		
		/// Opacity
		final TestExtGWTSTLVParameter opacityParam = new TestExtGWTSTLVParameter("Opacity", "0.5", "60px", "60px");
		hrParameterPanel1.add(opacityParam);
		
		// Button panel
		FlowPanel butonPanel = new FlowPanel();
		formVP.add(butonPanel);
		
		// Load button
		Button okButton = new Button("LOAD");
		butonPanel.add(okButton);
		okButton.setWidth("100px");
		
		// Start button
		Button startButton = new Button("START");
		butonPanel.add(startButton);
		startButton.setWidth("100px");
		
		// Stop button
		Button stopButton = new Button("STOP");
		butonPanel.add(stopButton);
		stopButton.setWidth("100px");
		
		// Mouse interaction button
		final Button mouseButton = new Button("MOUSE ACT.");
		butonPanel.add(mouseButton);
		mouseButton.setWidth("120px");
		
		// Mouse interaction button
		final Button lightButton = new Button("GROUND ON");
		butonPanel.add(lightButton);
		lightButton.setWidth("120px");
		
		//Model Selector
		selectUrl = new ListBox();
		formVP.add(selectUrl);
		selectUrl.addItem("[3,6 MB] Doll_Multiscan.stl"						, DEFAULT_MODEL_PATH);
		selectUrl.addItem("[600 KB] MAKE_Robot_V6.stl"						, "https://dl.dropboxusercontent.com/u/62612071/imotion/stl/MAKE_Robot_V6.stl");
		selectUrl.addItem("[3,6 MB] COLONEL_72k_v2.stl"						, "https://dl.dropboxusercontent.com/u/62612071/imotion/stl/COLONEL_72k_v2.stl");
		selectUrl.addItem("[8,2 MB] knight_bookend_single.stl"				, "https://dl.dropboxusercontent.com/u/62612071/imotion/stl/knight_bookend_single.stl");
		selectUrl.addItem("[13,7 MB] Owl_Facing_Left_fixed_sc.stl"			, "https://dl.dropboxusercontent.com/u/62612071/imotion/stl/Owl_Facing_Left_fixed_sc.stl");
		selectUrl.addItem("[1,5 MB] skewer.stl"								, "https://dl.dropboxusercontent.com/u/62612071/imotion/stl/skewer.stl");
		selectUrl.addItem("[880 KB] squirrel.stl"							, "https://dl.dropboxusercontent.com/u/62612071/imotion/stl/squirrel.stl");
		selectUrl.addItem("[6,7 MB] waving_gnome_single.stl"				, "https://dl.dropboxusercontent.com/u/62612071/imotion/stl/waving_gnome_single.stl");
		
		selectUrl.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				int index= selectUrl.getSelectedIndex();
				pathTextBox.setText(selectUrl.getValue(index));
			}
		});
		
		//Renderer
		rendererWidget = new EXTGWTSTLVLoaderWidgetThreeJS(false, 0xA09595, 0xF0F0F0, 640, 480);
		contentPanel.add(rendererWidget);
		rendererWidget.setZoomPercentage(5);
		
		//Controls Panel
		HorizontalPanel controlsPanel = new HorizontalPanel();
		contentPanel.add(controlsPanel);
		controlsPanel.setWidth("100%");
		
		TestExtGWTSTLVSpinner zoomSpinner = new TestExtGWTSTLVSpinner("ZOOM");
		controlsPanel.add(zoomSpinner);
		controlsPanel.setCellHorizontalAlignment(zoomSpinner, HasHorizontalAlignment.ALIGN_CENTER);
		
		TestExtGWTSTLVSpinner speedXSpinner = new TestExtGWTSTLVSpinner("SPEED X");
		controlsPanel.add(speedXSpinner);
		controlsPanel.setCellHorizontalAlignment(speedXSpinner, HasHorizontalAlignment.ALIGN_CENTER);
		
		TestExtGWTSTLVSpinner speedYSpinner = new TestExtGWTSTLVSpinner("SPEED Y");
		controlsPanel.add(speedYSpinner);
		controlsPanel.setCellHorizontalAlignment(speedYSpinner, HasHorizontalAlignment.ALIGN_CENTER);
		
		TestExtGWTSTLVSpinner speedZSpinner = new TestExtGWTSTLVSpinner("SPEED Z");
		controlsPanel.add(speedZSpinner);
		controlsPanel.setCellHorizontalAlignment(speedZSpinner, HasHorizontalAlignment.ALIGN_CENTER);
		
		//Handlers
		okButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String path = pathTextBox.getText();
				rendererWidget.loadModel(path, false, new EXTGWTSTLExceptionCallback() {
					
					@Override
					public void onFailure(EXTGWTSTLException exception) {
						Window.alert(exception.getMessage());
					}
				});

			}
		});
		
		startButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rendererWidget.setGyreZSpeed(DEFAULT_Z_SPEED_VARIATION);
			}
		});
		
		stopButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rendererWidget.setGyreZSpeed(0.0f);
			}
		});
		
		mouseButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String buttonText = mouseButton.getText();
				if (buttonText.equals("MOUSE ACT.")) {
					mouseButton.setText("MOUSE DEACT.");
					rendererWidget.captureRotationMouseEvents(true);
				} else {
					mouseButton.setText("MOUSE ACT.");
					rendererWidget.captureRotationMouseEvents(false);
				}
			}
		});
		
		lightButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String buttonText = lightButton.getText();
				if (buttonText.equals("GROUND ON")) {
					lightButton.setText("GROUND OFF");
					rendererWidget.groundVisibility(false);
				} else {
					lightButton.setText("GROUND ON");
					rendererWidget.groundVisibility(true);
				}
			}
		});
		
		zoomParam.addActionHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String zoomText = zoomParam.getValue();
				float zoom = 10;
				try {
					zoom = Float.parseFloat(zoomText);
					if (zoom >= 100) {
						Window.alert("Zoom value should be lower than 100");
					}
				} catch (NumberFormatException nfe) {
					
				}
				rendererWidget.setZoomPercentage(zoom);
			}
		});
		
		zoomSpinner.addDecraseClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rendererWidget.zoomOut();
			}
		});
		
		zoomSpinner.addIncreaseClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rendererWidget.zoomIn();
			}
		});
		
		speedZSpinner.addDecraseClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rendererWidget.decreaseZGyreSpeed(DEFAULT_Z_SPEED_VARIATION);
			}
		});
		
		speedZSpinner.addIncreaseClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rendererWidget.increaseZGyreSpeed(DEFAULT_Z_SPEED_VARIATION);
			}
		});
		
		speedXSpinner.addDecraseClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rendererWidget.decreaseXGyreSpeed(DEFAULT_X_SPEED_VARIATION);
			}
		});
		
		speedXSpinner.addIncreaseClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rendererWidget.increaseXGyreSpeed(DEFAULT_X_SPEED_VARIATION);
			}
		});
		
		speedYSpinner.addDecraseClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rendererWidget.decreaseYGyreSpeed(DEFAULT_Y_SPEED_VARIATION);
			}
		});
		
		speedYSpinner.addIncreaseClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rendererWidget.increaseYGyreSpeed(DEFAULT_Y_SPEED_VARIATION);
			}
		});
		
	}
}
