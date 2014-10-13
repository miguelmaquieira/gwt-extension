package com.imotion.gwt.stlviewer.client.threejs;

import com.akjava.gwt.three.client.renderers.WebGLRenderer;

public class EXTGWTSTLVWebGLRenderer extends WebGLRenderer {

	protected EXTGWTSTLVWebGLRenderer() {
		super();
	}
	
	public final native void setPhysicallyBasedShading (boolean bool)/*-{
		this.physicallyBasedShading = bool;
	}-*/;
	
	public final native void setAntialias (boolean bool)/*-{
		this.antialias = bool;
	}-*/;
	
	public final native void setGammaInput (boolean bool)/*-{
		this.gammaInput = bool;
	}-*/;
	
	public final native void setGammaOutput (boolean bool)/*-{
		this.gammaOutput = bool;
	}-*/;
}