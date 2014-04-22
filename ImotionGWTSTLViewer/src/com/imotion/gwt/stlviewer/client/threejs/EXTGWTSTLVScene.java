package com.imotion.gwt.stlviewer.client.threejs;

import com.akjava.gwt.three.client.scenes.Scene;

public class EXTGWTSTLVScene extends Scene {

	protected EXTGWTSTLVScene() {}
	
	public final native void setFog(EXTGWTSTLVFog fog)/*-{
		this.fog = fog;
	}-*/;

}
