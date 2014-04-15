package com.imotion.gwt.stlviewer.client.widget;

import com.akjava.gwt.three.client.THREE;
import com.akjava.gwt.three.client.cameras.Camera;
import com.akjava.gwt.three.client.core.Geometry;
import com.akjava.gwt.three.client.core.Vector3;
import com.akjava.gwt.three.client.lights.DirectionalLight;
import com.akjava.gwt.three.client.materials.Material;
import com.akjava.gwt.three.client.objects.Mesh;
import com.akjava.gwt.three.client.renderers.WebGLRenderer;
import com.akjava.gwt.three.client.scenes.Scene;
import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.animation.client.AnimationScheduler.AnimationCallback;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.imotion.gwt.stlviewer.client.threejs.STLLoader;
import com.imotion.gwt.stlviewer.client.threejs.THREEEXT;

public class STLLoaderWidget extends Composite implements AnimationCallback {

	private 	WebGLRenderer 	renderer;
	private 	Scene 			scene;
	private 	Camera 			camera;
	private 	Mesh 			mesh;
	private 	AbsolutePanel 	abs;
	private 	Vector3 		cameraTarget;

	public STLLoaderWidget() {
		// IHM
		AbsolutePanel root = new AbsolutePanel(); 
		initWidget(root);
		abs = new AbsolutePanel();
		abs.setSize("800px", "600px");
		root.add(abs);
		// fin IHM

		camera = THREE.PerspectiveCamera(35, 800.0 / 600.0, 1f, 15f);
		camera.getPosition().set(3, 0.75, 3);

		cameraTarget = THREE.Vector3(0, 0.45, 0);

		scene = THREE.Scene();

		// Ground
		Mesh plane = THREE.Mesh( THREE.PlaneGeometry( 4, 4 ), THREE.MeshBasicMaterial().color(0xFFFFFF).build() );
		plane.setRotation(-Math.PI/2, 0, 0);
		plane.setPosition(0f, 0f, 0f);
		scene.add(plane);
		plane.setReceiveShadow(true);

		// ASCII file
		STLLoader.load("models/thingiverse/Doll_Multiscan.stl", new AsyncCallback<Geometry>() {

			@Override
			public void onSuccess(Geometry geometry) {
				Material material = THREEEXT.MeshBasicMaterial().color(0x0743AE).build();
				mesh = THREE.Mesh(geometry, material);

				mesh.setPosition( 0, 0, 0.3 );
				mesh.setRotation( - Math.PI / 2, 0, 0 );
				mesh.setScale( 0.007, 0.007, 0.007);

				mesh.setCastShadow(true);
				mesh.setReceiveShadow(true);

				scene.add(mesh);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error cargando stl");
			}
		});

		// Lights

		scene.add(THREE.AmbientLight( 0x777777 ) );

		addShadowedLight( 1d, 1d, 1d, 0xffffff, 1 );

		// renderer
		renderer = THREE.CanvasRenderer();
		renderer.setShadowMapEnabled(true);
		renderer.setSize(800, 600);
		HTMLPanel div = new HTMLPanel("");
		div.getElement().appendChild(renderer.getDomElement());
		root.add(div, 0, 0);

		renderer.setSize(800, 600);
		renderer.setClearColorHex(0x000000, 1.0f);

		camera.lookAt(cameraTarget.getX(), cameraTarget.getY(), cameraTarget.getZ());

		AnimationScheduler.get().requestAnimationFrame(this);
	}
	
	private void  addShadowedLight( double x, double y, double z, int color, int intensity ) {

		DirectionalLight directionalLight = THREE.DirectionalLight(color, intensity);
		directionalLight.setPosition( x, y, z );
		scene.add( directionalLight );

		directionalLight.setCastShadow(true);
	}

	@Override
	public void execute(double timestamp) {
		if (mesh != null) {
			mesh.getRotation().setZ(mesh.getRotation().getZ() + 0.01);
		}

		renderer.render(scene, camera);

		AnimationScheduler.get().requestAnimationFrame(this);
	}

}
