package test.com.imotion.gwt.stlviewer.client;

import test.com.imotion.gwt.stlviewer.client.widget.TestExtGWTSTLVSpinner;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.imotion.gwt.stlviewer.client.widget.EXTGWTSTLILoaderDisplay;
import com.imotion.gwt.stlviewer.client.widget.EXTGWTSTLVLoaderWidget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestExtGWTSTLVEntryPoint implements EntryPoint {

	private static final String 	DEFAULT_MODEL_PATH 		= "models/thingiverse/Doll_Multiscan.stl";
	private static final double	DEFAULT_SPEED_VARIATION	= 0.01;
	
	private TextBox 					pathTextBox;
	private EXTGWTSTLILoaderDisplay 	rendererWidget;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		
		FlowPanel contentPanel = new FlowPanel();
		rootPanel.add(contentPanel);
		contentPanel.setWidth("640px");

		//FORM
		HorizontalPanel hp = new HorizontalPanel();
		contentPanel.add(hp);
		hp.setWidth("100%");

		pathTextBox = new TextBox();
		hp.add(pathTextBox);
		pathTextBox.setText(DEFAULT_MODEL_PATH);
		pathTextBox.setWidth("100%");
		hp.setCellWidth(pathTextBox, "70%");
		hp.setCellHorizontalAlignment(pathTextBox, HasHorizontalAlignment.ALIGN_RIGHT);

		Button okButton = new Button("CARGAR");
		hp.add(okButton);
		okButton.setWidth("100%");
		hp.setCellWidth(okButton, "30%");
		hp.setCellHorizontalAlignment(okButton, HasHorizontalAlignment.ALIGN_LEFT);
		
		//Renderer
		rendererWidget = new EXTGWTSTLVLoaderWidget(0xA09595, 0xFFFFFF, 640, 480);
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
