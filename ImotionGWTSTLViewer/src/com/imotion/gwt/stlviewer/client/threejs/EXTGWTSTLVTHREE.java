package com.imotion.gwt.stlviewer.client.threejs;

import com.akjava.gwt.three.client.THREE;
import com.akjava.gwt.three.client.cameras.Camera;
import com.imotion.gwt.stlviewer.client.utils.EXTGWTSTLVSceneParameters;

public class EXTGWTSTLVTHREE extends THREE {

	public static final EXTGWTSTLVMeshPhongMaterialBuilder MeshPhongMaterial() {
		return EXTGWTSTLVMeshPhongMaterialBuilder.create();
	}
	
	public static native final EXTGWTSTLVWebGLRenderer EXTGWTWebGLRenderer()/*-{
		return new $wnd.THREE.WebGLRenderer();
	}-*/;
	
	public static Camera PerspectiveCamera(double ratio,double near,double far) {
		return THREE.PerspectiveCamera(EXTGWTSTLVSceneParameters.CAMERA_VISION_ANGLE_DEGREES, ratio, near, far);
	}

}
