package com.imotion.gwt.stlviewer.client.widget;

import com.akjava.gwt.three.client.THREE;
import com.akjava.gwt.three.client.cameras.Camera;
import com.akjava.gwt.three.client.core.Geometry;
import com.akjava.gwt.three.client.core.Vector3;
import com.akjava.gwt.three.client.gwt.core.BoundingBox;
import com.akjava.gwt.three.client.lights.DirectionalLight;
import com.akjava.gwt.three.client.materials.Material;
import com.akjava.gwt.three.client.objects.Mesh;
import com.akjava.gwt.three.client.renderers.WebGLRenderer;
import com.akjava.gwt.three.client.scenes.Scene;
import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.animation.client.AnimationScheduler.AnimationCallback;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.imotion.gwt.stlviewer.client.threejs.EXTGWTSTLVLoader;
import com.imotion.gwt.stlviewer.client.threejs.EXTGWTSTLVTHREE;
import com.imotion.gwt.stlviewer.client.utils.EXTGWTSTLVSceneParameters;

public class EXTGWTSTLVLoaderWidget extends Composite implements AnimationCallback {

	private 	WebGLRenderer 	renderer;
	private 	Scene 			scene;
	private 	Camera 			camera;
	private 	Mesh 			objectMesh;
	private 	Mesh 			planeMesh;
	private		int				sceneWidth;
	private		int				sceneHeight;

	public EXTGWTSTLVLoaderWidget(String url, final int objectColorAsHex, int floorColorAsHex, int backgroundColorAsHex, float backgroundTransparency, final int width, final int height) {
		this.sceneHeight 	= height;
		this.sceneWidth		= width;

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
		planeMesh.setPosition(0, 0, 0);
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
		renderer.setClearColorHex(backgroundColorAsHex, backgroundTransparency);
		renderer.setShadowMapEnabled(true);

		root.getElement().appendChild(renderer.getDomElement());
	}

	private void setupSTLObject(Geometry geometry, int objectColorAsHex) {
		//Solve scene parameters
		geometry.computeBoundingBox();
		BoundingBox boundingBox = geometry.getBoundingBox();
		Vector3 maxVector3 = boundingBox.getMax();
		Vector3 minVector3 = boundingBox.getMin();

		double height 		= Math.abs(maxVector3.getY() - minVector3.getY());
		double width		= Math.abs(maxVector3.getZ() - minVector3.getZ());
		EXTGWTSTLVSceneParameters sceneParameters = new EXTGWTSTLVSceneParameters(height, width);

		//Build mesh
		Material material = EXTGWTSTLVTHREE.MeshBasicMaterial().color(objectColorAsHex).overdraw(true).opacity(0.7).build();
		objectMesh = THREE.MorphAnimMesh(geometry, material);
		objectMesh.setPosition(0, 0, 0);
		objectMesh.setRotation(- Math.PI / 2, 0, 0 );
		objectMesh.setReceiveShadow(true);
		objectMesh.setCastShadow(true);
		objectMesh.setScale(sceneParameters.getScale(), sceneParameters.getScale(), sceneParameters.getScale());

		//Add object to scene
		scene.add(objectMesh);

		//Camera
		float ratio = this.sceneWidth / this.sceneHeight;
		double cameraLookAtY = objectMesh.getPosition().getY() + ( (3 / 4) * sceneParameters.getHeightScaled() );
		camera = EXTGWTSTLVTHREE.PerspectiveCamera(35, ratio, 1f, 1000f);
		camera.getPosition().set(sceneParameters.getCameraPositionX(), sceneParameters.getCameraPositionY(), sceneParameters.getCameraPositionZ());
		camera.lookAt(objectMesh.getPosition().getX(), cameraLookAtY, objectMesh.getPosition().getZ());
		
		//Light
		DirectionalLight dirLight = THREE.DirectionalLight( 0xff6600, 1000 );
		//dirLight.getColor().setHex(0xF07746);
		dirLight.getPosition().set(sceneParameters.getLightPositionX(), sceneParameters.getLightPositionY(), sceneParameters.getLightPositionZ());
		//		dirLight.getPosition().multiplyScalar(50 );
		dirLight.setCastShadow(true);
		scene.add(dirLight);

		AnimationScheduler.get().requestAnimationFrame(EXTGWTSTLVLoaderWidget.this);
	}

	@Override
	public void execute(double timestamp) {
		objectMesh.getRotation().setZ(objectMesh.getRotation().getZ() + 0.1);
		renderer.render(scene, camera);
		AnimationScheduler.get().requestAnimationFrame(this);
	}

}
