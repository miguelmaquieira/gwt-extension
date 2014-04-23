package com.example.stl.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.imotion.gwt.stlviewer.client.widget.threejs.EXTGWTSTLVLoaderWidgetThreeJS;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class STLLoaderExample implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		RootPanel rootPanel = RootPanel.get();
		rootPanel.setWidth("640px");
		
		EXTGWTSTLVLoaderWidgetThreeJS rendererWidget = new EXTGWTSTLVLoaderWidgetThreeJS(
																"https://dl.dropboxusercontent.com/u/62612071/imotion/stl/COLONEL_72k_v2.stl",
																0xA09595, 0xFFFFFF, 640, 480);
		rootPanel.add(rendererWidget);
		rendererWidget.setWidth("100%");
		
	}
}
