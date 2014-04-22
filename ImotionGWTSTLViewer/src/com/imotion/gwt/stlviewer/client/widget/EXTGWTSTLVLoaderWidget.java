package com.imotion.gwt.stlviewer.client.widget;

import com.akjava.gwt.three.client.THREE;
import com.akjava.gwt.three.client.cameras.Camera;
import com.akjava.gwt.three.client.core.Geometry;
import com.akjava.gwt.three.client.core.Vector3;
import com.akjava.gwt.three.client.gwt.core.BoundingBox;
import com.akjava.gwt.three.client.lights.DirectionalLight;
import com.akjava.gwt.three.client.materials.Material;
import com.akjava.gwt.three.client.objects.Mesh;
import com.akjava.gwt.three.client.scenes.Scene;
import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.animation.client.AnimationScheduler.AnimationCallback;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.imotion.gwt.stlviewer.client.threejs.EXTGWTSTLVLoader;
import com.imotion.gwt.stlviewer.client.threejs.EXTGWTSTLVTHREE;
import com.imotion.gwt.stlviewer.client.threejs.EXTGWTSTLVWebGLRenderer;
import com.imotion.gwt.stlviewer.client.utils.EXTGWTSTLVSceneParameters;

public class EXTGWTSTLVLoaderWidget extends Composite implements AnimationCallback {

	private 	EXTGWTSTLVWebGLRenderer 	renderer;
	private 	Scene 						scene;
	private 	Camera 						camera;
	private 	Mesh 						objectMesh;
	private 	Mesh 						planeMesh;
	private		int							sceneWidth;
	private		int							sceneHeight;
	private		boolean						scale;

	public EXTGWTSTLVLoaderWidget(String url, final int objectColorAsHex, int floorColorAsHex, int backgroundColorAsHex, int width, int height, boolean scale) {
		this.sceneHeight 	= height;
		this.sceneWidth		= width;
		this.scale			= scale;

		HTMLPanel root = new HTMLPanel(""); 
		initWidget(root);

		//Scene
		scene = THREE.Scene();

		//Floor
		Geometry floorGeometry = THREE.PlaneGeometry( 10000, 10000);
		Material floorMaterial = THREE.MeshBasicMaterial().color(floorColorAsHex).overdraw(true).build();
		planeMesh = EXTGWTSTLVTHREE.Mesh(floorGeometry, floorMaterial);
		scene.add(planeMesh);
		planeMesh.setRotation(- Math.PI / 2, 0, 0 );
		planeMesh.setPosition(-100, 0, -100);
		planeMesh.setReceiveShadow(true);
		planeMesh.setCastShadow(true);


		EXTGWTSTLVLoader.load(url, new AsyncCallback<Geometry>() {

			@Override
			public void onSuccess(Geometry geometry) {
				setupSTLObject(geometry, objectColorAsHex);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error cargando stl");
			}
		});

		//Renderer
		renderer = EXTGWTSTLVTHREE.EXTGWTWebGLRenderer();
		renderer.setSize(width, height);
		renderer.setClearColorHex(backgroundColorAsHex, 1d);
		renderer.setShadowMapEnabled(true);
		renderer.setPhysicallyBasedShading(true);

		root.getElement().appendChild(renderer.getDomElement());
	}

	private void setupSTLObject(Geometry geometry, int objectColorAsHex) {
		//Solve scene parameters
		geometry.computeBoundingBox();
		BoundingBox boundingBox = geometry.getBoundingBox();
		Vector3 	maxVector3 	= boundingBox.getMax();
		Vector3 	minVector3 	= boundingBox.getMin();

		double height 		= Math.abs(maxVector3.getY() - minVector3.getY());
		double width		= Math.abs(maxVector3.getZ() - minVector3.getZ());
		EXTGWTSTLVSceneParameters sceneParameters = new EXTGWTSTLVSceneParameters(height, width, this.scale	);

		//Build mesh
		Material material = EXTGWTSTLVTHREE.MeshPhongMaterial().color(objectColorAsHex).ambient(0x000).specular(0x009900).shininess(30).build();
		objectMesh = THREE.Mesh(geometry, material);
		objectMesh.setPosition(0, 0, 0);
		objectMesh.setRotation(- Math.PI / 2, 0, 0 );
		objectMesh.setReceiveShadow(true);
		objectMesh.setCastShadow(true);
		objectMesh.setScale(sceneParameters.getScale(), sceneParameters.getScale(), sceneParameters.getScale());

		//Add object to scene
		scene.add(objectMesh);

		//Camera
		float ratio = this.sceneWidth / this.sceneHeight;
		double cameraLookAtY = objectMesh.getPosition().getY() + sceneParameters.getCameraLookAtYAddition();
		camera = EXTGWTSTLVTHREE.PerspectiveCamera(ratio, 1f, 1000f);
		camera.setPosition(sceneParameters.getCameraPositionX(), sceneParameters.getCameraPositionY(), sceneParameters.getCameraPositionZ());
		camera.lookAt(objectMesh.getPosition().getX(), cameraLookAtY, objectMesh.getPosition().getZ());
		
		//Light
		DirectionalLight dirLight = THREE.DirectionalLight( 0xfff, 10 );
		dirLight.setPosition(sceneParameters.getLightPositionX(), sceneParameters.getLightPositionY(), sceneParameters.getLightPositionZ());
		dirLight.setCastShadow(true);
		dirLight.setVisible(true);
		scene.add(dirLight);
		
//		AmbientLight ambientLight = THREE.AmbientLight(0xff6600);
//		ambientLight.setPosition(sceneParameters.getLightPositionX(), sceneParameters.getLightPositionY(), sceneParameters.getLightPositionZ());
//		ambientLight.setCastShadow(true);
//		ambientLight.setVisible(true);
//		scene.add(ambientLight);
		
		AnimationScheduler.get().requestAnimationFrame(EXTGWTSTLVLoaderWidget.this);
	}

	@Override
	public void execute(double timestamp) {
		objectMesh.getRotation().setZ(objectMesh.getRotation().getZ() + 0.1);
		renderer.render(scene, camera);
		AnimationScheduler.get().requestAnimationFrame(this);
	}

}
