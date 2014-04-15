package com.imotion.gwt.stlviewer.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.imotion.gwt.stlviewer.client.widget.STLLoaderWidget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ImotionGWTSTLViewer implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		STLLoaderWidget stlWidget = new STLLoaderWidget();
		rootPanel.add(stlWidget);
	}
}
