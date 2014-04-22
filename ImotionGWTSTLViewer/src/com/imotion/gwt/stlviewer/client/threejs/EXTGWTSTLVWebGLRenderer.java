package com.imotion.gwt.stlviewer.client.threejs;

import com.akjava.gwt.three.client.renderers.WebGLRenderer;

public class EXTGWTSTLVWebGLRenderer extends WebGLRenderer implements EXTGWTSTLIRenderer {

	protected EXTGWTSTLVWebGLRenderer() {
		super();
	}
	
	public final native void setPhysicallyBasedShading (boolean bool)/*-{
	this.physicallyBasedShading =bool;
	}-*/;

}
