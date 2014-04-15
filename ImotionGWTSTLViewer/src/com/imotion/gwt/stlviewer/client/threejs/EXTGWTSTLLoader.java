package com.imotion.gwt.stlviewer.client.threejs;

import com.akjava.gwt.three.client.core.Geometry;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class EXTGWTSTLLoader {

	protected EXTGWTSTLLoader() {}

	public native static final void load(String url, AsyncCallback<Geometry> callback) /*-{
		var loader = new $wnd.THREE.STLLoader();
		loader.addEventListener( 'load', function ( event ) {
			var geometry = event.content;
			@com.imotion.gwt.stlviewer.client.threejs.EXTGWTSTLLoader::callbackSuccess(Lcom/google/gwt/user/client/rpc/AsyncCallback;Lcom/akjava/gwt/three/client/core/Geometry;)(callback, geometry);
		} );
		loader.load(url);
	}-*/;
	
	public static void callbackSuccess(AsyncCallback<Geometry> callback, Geometry geometry) {
		callback.onSuccess (geometry);
	}

}
