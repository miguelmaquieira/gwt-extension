package com.imotion.gwt.stlviewer.client.threejs;

import com.akjava.gwt.three.client.THREE;
import com.imotion.gwt.stlviewer.client.EXTGWTWebGLRenderer;

public class EXTGWTTHREE extends THREE {

	public static  final EXTGWTMeshPongMaterialBuilder MeshPongMaterial(){
		return EXTGWTMeshPongMaterialBuilder.create();
	}
	
	public static native final EXTGWTWebGLRenderer EXTGWTWebGLRenderer()/*-{
	return new $wnd.THREE.WebGLRenderer();
	}-*/;

}
