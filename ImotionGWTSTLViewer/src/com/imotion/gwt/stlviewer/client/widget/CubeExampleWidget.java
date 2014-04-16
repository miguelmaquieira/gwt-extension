package com.imotion.gwt.stlviewer.client.widget;

import com.akjava.gwt.three.client.THREE;
import com.akjava.gwt.three.client.cameras.Camera;
import com.akjava.gwt.three.client.core.Face;
import com.akjava.gwt.three.client.core.Geometry;
import com.akjava.gwt.three.client.materials.Material;
import com.akjava.gwt.three.client.objects.Mesh;
import com.akjava.gwt.three.client.renderers.WebGLRenderer;
import com.akjava.gwt.three.client.scenes.Scene;
import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.animation.client.AnimationScheduler.AnimationCallback;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.imotion.gwt.stlviewer.client.threejs.EXTGWTTHREE;

public class CubeExampleWidget extends Composite implements AnimationCallback {

	private 	WebGLRenderer 	renderer;
	private 	Scene 			scene;
	private 	Camera 			camera;
	private 	Mesh 			cubeMesh;
	private 	Mesh 			planeMesh;

	public CubeExampleWidget(final int width, final int height) {
		HTMLPanel root = new HTMLPanel(""); 
		initWidget(root);

		//Camera
		float ratio = width / height;
		camera = THREE.PerspectiveCamera(70, ratio, 1f, 1000f);
		camera.getPosition().set(0, 150, 500);

		//Scene
		scene = THREE.Scene();

		//Floor
		Geometry floorGeometry = THREE.PlaneGeometry( 200, 200 );
		floorGeometry.applyMatrix(THREE.Matrix4().makeRotationX(- Math.PI / 2));
		Material floorMaterial = THREE.MeshBasicMaterial().color(0xe0e0e0).overdraw(true).build();
		planeMesh = THREE.Mesh(floorGeometry, floorMaterial);
		scene.add(planeMesh);

		//Cube
		Geometry cubeGeometry = THREE.CubeGeometry(200, 200, 200);
		JsArray<Face> faces = cubeGeometry.faces();
		for (int i = 0; i < faces.length(); i += 2) {
			int hex = (int) (Math.random() * 0xffffff);
			faces.get(i).getColor().setHex(hex);
			faces.get(i + 1).getColor().setHex(hex);
		}
		
		Material material = EXTGWTTHREE.MeshBasicMaterial().vertexColors(THREE.Colors.FaceColors()).overdraw(true).build();
		cubeMesh = THREE.Mesh(cubeGeometry, material);
		cubeMesh.setPosition(0, 150, 0);
		scene.add(cubeMesh);

		//Renderer
		renderer = THREE.CanvasRenderer();
		renderer.setSize(width, height);
		renderer.setClearColorHex(0xf0f0f0, 1.0);
		root.getElement().appendChild(renderer.getDomElement());

		AnimationScheduler.get().requestAnimationFrame(this);

	}

	@Override
	public void execute(double timestamp) {
		cubeMesh.getRotation().setY(cubeMesh.getRotation().getY() + 0.02);
		planeMesh.getRotation().setY(cubeMesh.getRotation().getY());

		renderer.render(scene, camera);
		AnimationScheduler.get().requestAnimationFrame(this);
	}

}
