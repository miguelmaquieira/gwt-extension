package com.imotion.gwt.stlviewer.client.threejs;

import com.akjava.gwt.three.client.THREE;

public class EXTGWTSTLVTHREE extends THREE {

	public static final EXTGWTSTLVMaterialBuilder MeshPongMaterial() {
		return EXTGWTSTLVMaterialBuilder.create();
	}
	
	public static native final EXTGWTSTLVWebGLRenderer EXTGWTWebGLRenderer()/*-{
		return new $wnd.THREE.WebGLRenderer();
	}-*/;

}
