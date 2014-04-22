package test.com.imotion.gwt.stlviewer.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.imotion.gwt.stlviewer.client.widget.EXTGWTSTLVLoaderWidget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestExtGWTSTLVEntryPoint implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		String modelPath = "models/thingiverse/Doll_Multiscan.stl";
		EXTGWTSTLVLoaderWidget stlWidget = new EXTGWTSTLVLoaderWidget(modelPath, 0x959A9D, 0xFFFFFF, 0x000, 640, 480, false);
		rootPanel.add(stlWidget);
	}
}
