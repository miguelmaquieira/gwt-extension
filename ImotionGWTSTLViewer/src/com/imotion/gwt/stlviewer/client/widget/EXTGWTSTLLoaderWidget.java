package com.imotion.gwt.stlviewer.client.widget;

import com.akjava.gwt.three.client.THREE;
import com.akjava.gwt.three.client.cameras.Camera;
import com.akjava.gwt.three.client.core.Geometry;
import com.akjava.gwt.three.client.core.Object3D;
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
import com.imotion.gwt.stlviewer.client.threejs.EXTGWTSTLLoader;
import com.imotion.gwt.stlviewer.client.threejs.EXTGWTTHREE;

public class EXTGWTSTLLoaderWidget extends Composite implements AnimationCallback {

	private 	WebGLRenderer 	renderer;
	private 	Scene 			scene;
	private 	Camera 			camera;
	private 	Mesh 			objectMesh;
	private 	Mesh 			planeMesh;
	private 	Mesh 			wallMesh;
	private Object3D wallDeepMesh;

	public EXTGWTSTLLoaderWidget(String url, final int objectColorAsHex, int floorColorAsHex, int backgroundColorAsHex, float backgroundTransparency, final int width, final int height) {
		HTMLPanel root = new HTMLPanel(""); 
		initWidget(root);

		int maxObjectSize = 80; 
		
		//Camera
		float ratio = width / height;
		camera = EXTGWTTHREE.PerspectiveCamera(60, ratio, 1f, 1000f);
		camera.getPosition().set(-250, 200, -250);

		//Scene
		scene = THREE.Scene();

		//WALL LEFT
//		Geometry wallGeometry = THREE.PlaneGeometry( 200, 600);
//		Material wallMaterial = THREE.MeshBasicMaterial().color(0xbb88ff).overdraw(true).build();
//		wallMesh = EXTGWTTHREE.Mesh(wallGeometry, wallMaterial);
//		scene.add(wallMesh);
//		wallMesh.setPosition(-50, 200, -50);
//		wallMesh.setRotation(0, Math.PI / 2, 0);
//		wallMesh.setReceiveShadow(true);
//		wallMesh.setCastShadow(true);

		//Floor
		Geometry floorGeometry = THREE.PlaneGeometry( 10000, 10000);
		Material floorMaterial = THREE.MeshBasicMaterial().color(floorColorAsHex).overdraw(true).build();
		planeMesh = EXTGWTTHREE.Mesh(floorGeometry, floorMaterial);
		scene.add(planeMesh);
		planeMesh.setRotation(- Math.PI / 2, 0, 0 );
		planeMesh.setPosition(0, 0, 0);
		planeMesh.setReceiveShadow(true);
		planeMesh.setCastShadow(true);
		

		EXTGWTSTLLoader.load(url, new AsyncCallback<Geometry>() {

			@Override
			public void onSuccess(Geometry geometry) {
				//Setup object texture, color, zoom etc
				Material material = EXTGWTTHREE.MeshBasicMaterial().color(objectColorAsHex).overdraw(true).opacity(0.7).build();
				objectMesh = THREE.MorphAnimMesh(geometry, material);
				objectMesh.setPosition(0, 0, 0);
				objectMesh.setRotation(- Math.PI / 2, 0, 0 );
				objectMesh.setReceiveShadow(true);
				objectMesh.setCastShadow(true);
				//Add object to scene
				scene.add(objectMesh);
				camera.lookAt(scene.getPosition().getX(), scene.getPosition().getY() + 30, scene.getPosition().getZ());
				AnimationScheduler.get().requestAnimationFrame(EXTGWTSTLLoaderWidget.this);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error cargando stl");
			}
		});

		DirectionalLight dirLight = THREE.DirectionalLight( 0xffffff, 1000 );
		dirLight.getColor().setHex(0xF07746);
		dirLight.getPosition().set(0, 10,-70);
//		dirLight.getPosition().multiplyScalar(50 );
		dirLight.setCastShadow(true);
		scene.add(dirLight);
		
		//Renderer
		renderer = EXTGWTTHREE.EXTGWTWebGLRenderer();
		renderer.setSize(width, height);
		renderer.setClearColorHex(backgroundColorAsHex, backgroundTransparency);
		renderer.setShadowMapEnabled(true);

		root.getElement().appendChild(renderer.getDomElement());
	}
	
	@Override
	public void execute(double timestamp) {
		objectMesh.getRotation().setZ(objectMesh.getRotation().getZ() + 0.2);
		renderer.render(scene, camera);
		AnimationScheduler.get().requestAnimationFrame(this);
	}

}
