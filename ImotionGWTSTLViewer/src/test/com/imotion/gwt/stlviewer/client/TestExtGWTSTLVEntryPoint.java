package test.com.imotion.gwt.stlviewer.client;

import test.com.imotion.gwt.stlviewer.client.widget.TestExtGWTSTLVSpinner;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.imotion.gwt.stlviewer.client.widget.EXTGWTSTLILoaderDisplay;
import com.imotion.gwt.stlviewer.client.widget.threejs.EXTGWTSTLVLoaderWidgetThreeJS;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestExtGWTSTLVEntryPoint implements EntryPoint {

	private static final String 	DEFAULT_MODEL_PATH 		= "models/thingiverse/Doll_Multiscan.stl";
	private static final double	DEFAULT_SPEED_VARIATION	= 0.01;
	
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
		contentPanel.setWidth("640px");

		//FORM
		VerticalPanel formVP = new VerticalPanel();
		contentPanel.add(formVP);
		formVP.setWidth("100%");
		
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

		//Load button
		Button okButton = new Button("CARGAR");
		loadZone.add(okButton);
		okButton.setWidth("100%");
		loadZone.setCellWidth(okButton, "30%");
		loadZone.setCellHorizontalAlignment(okButton, HasHorizontalAlignment.ALIGN_LEFT);
		
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
		rendererWidget = new EXTGWTSTLVLoaderWidgetThreeJS(0xA09595, 0xFFFFFF, 640, 480);
		contentPanel.add(rendererWidget);
		
		//Controls Panel
		HorizontalPanel controlsPanel = new HorizontalPanel();
		contentPanel.add(controlsPanel);
		controlsPanel.setWidth("100%");
		
		TestExtGWTSTLVSpinner zoomSpinner = new TestExtGWTSTLVSpinner("ZOOM");
		controlsPanel.add(zoomSpinner);
		controlsPanel.setCellHorizontalAlignment(zoomSpinner, HasHorizontalAlignment.ALIGN_CENTER);
		
		TestExtGWTSTLVSpinner speedSpinner = new TestExtGWTSTLVSpinner("SPEED");
		controlsPanel.add(speedSpinner);
		controlsPanel.setCellHorizontalAlignment(speedSpinner, HasHorizontalAlignment.ALIGN_CENTER);
		
		//Handlers
		okButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String path = pathTextBox.getText();
				rendererWidget.loadModel(path);

			}
		});
		
		
		zoomSpinner.addDecraseClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rendererWidget.zoomIn();
				
			}
		});
		
		zoomSpinner.addIncreaseClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rendererWidget.zoomOut();
			}
		});
		
		speedSpinner.addDecraseClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rendererWidget.decreaseGyreSpeed(DEFAULT_SPEED_VARIATION);
			}
		});
		
		speedSpinner.addIncreaseClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rendererWidget.increaseGyreSpeed(DEFAULT_SPEED_VARIATION);
			}
		});
		
		
	}

}
