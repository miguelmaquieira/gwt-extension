package com.imotion.gwt.stlviewer.client.threejs;

import com.akjava.gwt.three.client.THREE;
import com.akjava.gwt.three.client.cameras.Camera;
import com.akjava.gwt.three.client.lights.Light;
import com.google.gwt.core.client.JavaScriptObject;
import com.imotion.gwt.stlviewer.client.utils.EXTGWTSTLVSceneParameters;

public class EXTGWTSTLVTHREE extends THREE {

	public static final EXTGWTSTLVMeshPhongMaterialBuilder MeshPhongMaterial() {
		return EXTGWTSTLVMeshPhongMaterialBuilder.create();
	}
	
	public static native final EXTGWTSTLVWebGLRenderer EXTGWTWebGLRenderer()/*-{
//		if ( ! $wnd.Detector.webgl ) $wnd.Detector.addGetWebGLMessage();
		return new $wnd.THREE.WebGLRenderer();
	}-*/;
	
	public static native final EXTGWTSTLVCanvasRenderer EXTGWTCanvasRenderer()/*-{
		return new $wnd.THREE.CanvasRenderer();
	}-*/;
	
	public static Camera PerspectiveCamera(double ratio,double near,double far) {
		return THREE.PerspectiveCamera(EXTGWTSTLVSceneParameters.CAMERA_VISION_ANGLE_DEGREES, ratio, near, far);
	}
	
	public static native final EXTGWTSTLVScene EXTGWTSTLVScene()/*-{
		return new $wnd.THREE.Scene();
	}-*/;

	public static native final JavaScriptObject TrackballControls(Camera camera)/*-{
		return new $wnd.THREE.TrackballControls( camera );
	}-*/;
	
	public static native final Light HemisphereLight(int skyColorHex, int groundColorHex, float intensity)/*-{
		return new $wnd.THREE.HemisphereLight( skyColorHex, groundColorHex, intensity );
	}-*/;

}
