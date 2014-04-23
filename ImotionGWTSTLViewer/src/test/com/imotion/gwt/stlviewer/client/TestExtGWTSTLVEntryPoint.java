package test.com.imotion.gwt.stlviewer.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.imotion.gwt.stlviewer.client.widget.EXTGWTSTLVLoaderWidget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestExtGWTSTLVEntryPoint implements EntryPoint {

	private static final String DEFAULT_MODEL_PATH = "models/thingiverse/Doll_Multiscan.stl";
	
	private TextBox 				pathTextBox;
	private EXTGWTSTLVLoaderWidget rendererWidget;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();

		//FORM
		HorizontalPanel hp = new HorizontalPanel();
		rootPanel.add(hp);

		pathTextBox = new TextBox();
		hp.add(pathTextBox);
		pathTextBox.setText(DEFAULT_MODEL_PATH);
		pathTextBox.setWidth("100%");
		hp.setCellWidth(pathTextBox, "400px");

		Button okButton = new Button("CARGAR");
		hp.add(okButton);
		
		//Renderer
		rendererWidget = new EXTGWTSTLVLoaderWidget(DEFAULT_MODEL_PATH, false, false, 0xA09595, 0xFFFFFF, 640, 480, 0.1f);
		rootPanel.add(rendererWidget);
		
		okButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String path = pathTextBox.getText();
				rendererWidget.loadModel(path);

			}
		});
	}

}
