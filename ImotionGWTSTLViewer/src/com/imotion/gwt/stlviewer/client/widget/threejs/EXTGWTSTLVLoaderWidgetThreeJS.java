package com.imotion.gwt.stlviewer.client.widget.threejs;

import com.akjava.gwt.three.client.THREE;
import com.akjava.gwt.three.client.cameras.Camera;
import com.akjava.gwt.three.client.core.Geometry;
import com.akjava.gwt.three.client.core.Vector3;
import com.akjava.gwt.three.client.gwt.core.BoundingBox;
import com.akjava.gwt.three.client.lights.AmbientLight;
import com.akjava.gwt.three.client.lights.DirectionalLight;
import com.akjava.gwt.three.client.lights.Light;
import com.akjava.gwt.three.client.materials.Material;
import com.akjava.gwt.three.client.objects.Mesh;
import com.akjava.gwt.three.client.renderers.WebGLRenderer;
import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.imotion.gwt.stlviewer.client.exception.EXTGWTSTLException;
import com.imotion.gwt.stlviewer.client.exception.EXTGWTSTLExceptionCallback;
import com.imotion.gwt.stlviewer.client.threejs.EXTGWTSTLVLoader;
import com.imotion.gwt.stlviewer.client.threejs.EXTGWTSTLVScene;
import com.imotion.gwt.stlviewer.client.threejs.EXTGWTSTLVTHREE;
import com.imotion.gwt.stlviewer.client.threejs.EXTGWTSTLVWebGLRenderer;
import com.imotion.gwt.stlviewer.client.utils.EXTGWTSTLVSceneParameters;
import com.imotion.gwt.stlviewer.client.widget.EXTGWTSTLILoaderDisplay;

public class EXTGWTSTLVLoaderWidgetThreeJS extends Composite implements EXTGWTSTLILoaderDisplay {

	private 	WebGLRenderer	 			renderer;
	private 	EXTGWTSTLVScene 			scene;
	private 	Camera 						camera;
	private 	Mesh 						objectMesh;
	private 	Mesh 						groundMesh;
	private 	AmbientLight 				ambientLight;
	private 	DirectionalLight 			dirLight;

	private		int							sceneWidth;
	private		int							sceneHeight;
	private 	double 						gyreSpeed;
	private 	double 						zoomPercentage;
	private 	int 						objectColorAsHex;

	public EXTGWTSTLVLoaderWidgetThreeJS(String modelpath, EXTGWTSTLExceptionCallback exceptionCallback, int objectColorAsHex, int backgroundColorAsHex, int width, int height) {
		this(modelpath, exceptionCallback, false, objectColorAsHex, backgroundColorAsHex, width, height,DEFAULT_GYRE_SPEED, DEFAULT_ZOOM_PCTG);
	}

	public EXTGWTSTLVLoaderWidgetThreeJS(int objectColorAsHex, int backgroundColorAsHex, int width, int height) {
		this(null, null, false, objectColorAsHex, backgroundColorAsHex, width, height,DEFAULT_GYRE_SPEED, DEFAULT_ZOOM_PCTG);
	}

	public EXTGWTSTLVLoaderWidgetThreeJS(int objectColorAsHex, int backgroundColorAsHex, int width, int height, double gyreSpeed) {
		this(null, null, false, objectColorAsHex, backgroundColorAsHex, width, height, gyreSpeed, DEFAULT_ZOOM_PCTG);
	}

	public EXTGWTSTLVLoaderWidgetThreeJS(boolean canvas,  int objectColorAsHex, int backgroundColorAsHex, int width, int height, double gyreSpeed) {
		this(null, null, canvas, objectColorAsHex, backgroundColorAsHex, width, height, gyreSpeed, DEFAULT_ZOOM_PCTG);
	}

	public EXTGWTSTLVLoaderWidgetThreeJS(String url, EXTGWTSTLExceptionCallback exceptionCallback, boolean canvas,  int objectColorAsHex, int backgroundColorAsHex, int width, int height, double gyreSpeed) {
		this(url, exceptionCallback, canvas, objectColorAsHex, backgroundColorAsHex, width, height, gyreSpeed, DEFAULT_ZOOM_PCTG);
	}

	public EXTGWTSTLVLoaderWidgetThreeJS(String url, EXTGWTSTLExceptionCallback exceptionCallback, boolean canvas,  int objectColorAsHex, int backgroundColorAsHex, int width, int height, double gyreSpeed, double zoomPercentage) {
		this.sceneHeight 			= height;
		this.sceneWidth				= width;
		this.objectColorAsHex		= objectColorAsHex;
		setGyreSpeed(gyreSpeed);
		setZoomPercentage(zoomPercentage);

		HTMLPanel root = new HTMLPanel(""); 
		initWidget(root);

		//Scene
		scene = EXTGWTSTLVTHREE.EXTGWTSTLVScene();

		//GROUND
		Geometry 		groundGeometry 	= THREE.PlaneGeometry( 10000, 10000);
		Material 		groundMaterial 	= EXTGWTSTLVTHREE.MeshBasicMaterial().color(backgroundColorAsHex).build();
		groundMesh = EXTGWTSTLVTHREE.Mesh(groundGeometry, groundMaterial);
		scene.add(groundMesh);
		groundMesh.setRotation(- Math.PI / 2, 0, 0 );
		groundMesh.setPosition(0, 0, 0);
		groundMesh.setReceiveShadow(true);
		groundMesh.setCastShadow(true);

		//Model
		loadModel(url, exceptionCallback);

		//Renderer
		if (canvas) {
			renderer = EXTGWTSTLVTHREE.EXTGWTCanvasRenderer();
		} else {
			renderer = EXTGWTSTLVTHREE.EXTGWTWebGLRenderer();
			( (EXTGWTSTLVWebGLRenderer) renderer).setPhysicallyBasedShading(true);
			( (EXTGWTSTLVWebGLRenderer) renderer).setAntialias(true);
			( (EXTGWTSTLVWebGLRenderer) renderer).setGammaInput(true);
			( (EXTGWTSTLVWebGLRenderer) renderer).setGammaOutput(true);
		}
		WebGLRenderer webGLRenderer = (WebGLRenderer) renderer;
		webGLRenderer.setSize(width, height);
		webGLRenderer.setClearColorHex(backgroundColorAsHex, 1d);
		webGLRenderer.setShadowMapEnabled(true);
		root.getElement().appendChild(webGLRenderer.getDomElement());
	}

	@Override
	public void loadModel(String url, final EXTGWTSTLExceptionCallback exceptionCallback) {
		if (url != null && url.length() > 0) {
			EXTGWTSTLVLoader.load(url, new AsyncCallback<Geometry>() {

				@Override
				public void onSuccess(Geometry geometry) {
					setupSTLObject(geometry, objectColorAsHex);
				}

				@Override
				public void onFailure(Throwable caught) {
					if (exceptionCallback != null) {
						EXTGWTSTLException exception = new EXTGWTSTLException(caught);
						exceptionCallback.onFailure(exception);
					}
				}
			});
		}
	}

	@Override
	public double increaseGyreSpeed(double radiansPerIteration) {
		if (radiansPerIteration > 0) {
			gyreSpeed += radiansPerIteration;
		}
		return gyreSpeed;
	}

	@Override
	public double decreaseGyreSpeed(double radiansPerIteration) {
		if (gyreSpeed >= Math.abs(radiansPerIteration) && radiansPerIteration > 0 ) {
			gyreSpeed -= radiansPerIteration;
		}
		return gyreSpeed;
	}

	@Override
	public void zoomIn() {
		double scaleVariation 	= getScaleVariation();
		double scale			= getScale();
		if (objectMesh != null && scale > scaleVariation) {
			scale -= scaleVariation;
			objectMesh.setScale(scale, scale, scale);
		}
	}

	@Override
	public void zoomOut() {
		double scaleVariation 	= getScaleVariation();
		double scale			= getScale();
		if (objectMesh != null) {
			scale += scaleVariation;
			objectMesh.setScale(scale, scale, scale);
		}
	}

	@Override
	public void setZoomPercentage(double zoomPercentage) {
		this.zoomPercentage = zoomPercentage;
	}

	@Override
	public void setGyreSpeed(double radiansPerIteration) {
		this.gyreSpeed = radiansPerIteration;
	}

	/**
	 * AnimationCallback 
	 */

	@Override
	public void execute(double timestamp) {
		objectMesh.getRotation().setZ(objectMesh.getRotation().getZ() + gyreSpeed);
		renderer.render(scene, camera);
		AnimationScheduler.get().requestAnimationFrame(this);
	}

	/***
	 * PRIVATE
	 */

	private void setupSTLObject(Geometry geometry, int objectColorAsHex) {
		//Solve scene parameters
		geometry.computeBoundingBox();
		BoundingBox boundingBox = geometry.getBoundingBox();
		Vector3 	maxVector3 	= boundingBox.getMax();
		Vector3 	minVector3 	= boundingBox.getMin();

		double height 		= Math.abs(maxVector3.getY() - minVector3.getY());
		double width		= Math.abs(maxVector3.getZ() - minVector3.getZ());
		EXTGWTSTLVSceneParameters sceneParameters = new EXTGWTSTLVSceneParameters(height, width);

		boolean firstTime = objectMesh == null;
		if (!firstTime) {
			scene.remove(dirLight);
			scene.remove(ambientLight);
			scene.remove(objectMesh);
		} 

		//Light
		if (firstTime) {
			Light hemisphereLight = EXTGWTSTLVTHREE.HemisphereLight(0xffffff, 0xffffff, 0.2f);
			hemisphereLight.setCastShadow(true);
			scene.add(hemisphereLight);
			hemisphereLight.setVisible(true);
		}
		dirLight = THREE.DirectionalLight( 0xffffff, 1 );
		dirLight.setPosition(sceneParameters.getLightPositionX(), sceneParameters.getLightPositionY(), sceneParameters.getLightPositionZ());
		dirLight.setCastShadow(true);
		scene.add(dirLight);

		ambientLight = THREE.AmbientLight(0xffffff);
		ambientLight.setPosition(sceneParameters.getLightPositionX(), sceneParameters.getLightPositionY(), sceneParameters.getLightPositionZ());
		ambientLight.setCastShadow(true);
		ambientLight.setVisible(true);
		scene.add(ambientLight);

		//Build mesh
		Material material = EXTGWTSTLVTHREE.MeshPhongMaterial().color(objectColorAsHex).ambient(0x030303).specular(0xFFFFFF).shininess(1).build();
		objectMesh = THREE.Mesh(geometry, material);
		objectMesh.setPosition(0, 0, 0);
		objectMesh.setRotation(- Math.PI / 2, 0, 0 );
		objectMesh.setReceiveShadow(true);
		objectMesh.setCastShadow(true);
		scene.add(objectMesh);

		//Camera
		float ratio = this.sceneWidth / this.sceneHeight;
		double cameraLookAtY = objectMesh.getPosition().getY() + sceneParameters.getCameraLookAtYAddition();
		camera = EXTGWTSTLVTHREE.PerspectiveCamera(ratio, 1f, 1000f);
		camera.setPosition(sceneParameters.getCameraPositionX(), sceneParameters.getCameraPositionY(), sceneParameters.getCameraPositionZ());
		camera.lookAt(objectMesh.getPosition().getX(), cameraLookAtY, objectMesh.getPosition().getZ());

		if (firstTime) {
			AnimationScheduler.get().requestAnimationFrame(EXTGWTSTLVLoaderWidgetThreeJS.this);
		}
	}

	private double getScaleVariation() {
		double scaleVariation 	= 0d;
		double scale			= getScale();
		if (scale > 0) {
			scaleVariation 	= scale * (zoomPercentage / 100);
		}
		return scaleVariation;
	}

	private double getScale() {
		double scale = 0d;
		if (objectMesh != null) {
			scale 	= objectMesh.getScale().getX();
		}
		return scale;
	}

}
