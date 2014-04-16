package com.imotion.gwt.stlviewer.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.imotion.gwt.stlviewer.client.widget.EXTGWTSTLLoaderWidget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ImotionGWTSTLViewer implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		EXTGWTSTLLoaderWidget stlWidget = new EXTGWTSTLLoaderWidget("models/thingiverse/Doll_Multiscan.stl", 0x0743AE, 0xFFFFFF, 0xe0e0e0, 0.5f, 640, 480);
		rootPanel.add(stlWidget);
//		CubeExampleWidget cubeWidget = new CubeExampleWidget(Window.getClientWidth(), Window.getClientHeight());
//		rootPanel.add(cubeWidget);
	}
}
