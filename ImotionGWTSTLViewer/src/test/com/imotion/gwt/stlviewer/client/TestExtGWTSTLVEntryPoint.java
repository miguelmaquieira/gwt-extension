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
//		String modelPath = "models/thingiverse/Angel_Candle_Holder_Multiscan.stl";
//		String modelPath = "models/thingiverse/Doll_Multiscan.stl";
//		String modelPath = "models/thingiverse/knight_bookend_multiscan_flatbottom.stl";
		String modelPath = "models/thingiverse/Smokin_Gnome_on_Mushroom_Multi.stl";
//		String modelPath = "models/thingiverse/COLONEL_72k_v2.stl";
//		String modelPath = "models/thingiverse/doublesized_s1_body_with_details.stl";
//		String modelPath = "models/thingiverse/CreativeTools.se_-_ZPrinter-model_-_Xmas_Utah-teapot.stl";
		EXTGWTSTLVLoaderWidget stlWidget = new EXTGWTSTLVLoaderWidget(modelPath, 0x0743AE, 0xFFFFFF, 0x000, 1f, 640, 480);
		rootPanel.add(stlWidget);
//		CubeExampleWidget cubeWidget = new CubeExampleWidget(Window.getClientWidth(), Window.getClientHeight());
//		rootPanel.add(cubeWidget);
	}
}
