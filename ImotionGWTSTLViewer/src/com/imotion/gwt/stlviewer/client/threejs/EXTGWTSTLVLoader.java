package com.imotion.gwt.stlviewer.client.threejs;

import com.akjava.gwt.three.client.core.Geometry;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.imotion.gwt.stlviewer.client.exception.EXTGWTSTLException;

public class EXTGWTSTLVLoader {

	protected EXTGWTSTLVLoader() {}

	public native static final void load(String url, AsyncCallback<Geometry> callback) /*-{
		var loader = new $wnd.THREE.STLLoader();
		
		loader.addEventListener( 'load', function ( event ) {
			var geometry = event.content;
			@com.imotion.gwt.stlviewer.client.threejs.EXTGWTSTLVLoader::callbackSuccess(Lcom/google/gwt/user/client/rpc/AsyncCallback;Lcom/akjava/gwt/three/client/core/Geometry;)(callback, geometry);
		} );
		
		loader.addEventListener( 'error', function ( event ) {
			var message = event.message;
			@com.imotion.gwt.stlviewer.client.threejs.EXTGWTSTLVLoader::callbackError(Lcom/google/gwt/user/client/rpc/AsyncCallback;Ljava/lang/String;)(callback, message);
		} );
		
		loader.addEventListener( 'progress', function ( event ) {
			@com.imotion.gwt.stlviewer.client.threejs.EXTGWTSTLVLoader::callbackUpdateProgress(Lcom/google/gwt/user/client/rpc/AsyncCallback;II)(callback, event.total, event.loaded);
		});
		
		loader.load(url);
	}-*/;
	
	public static void callbackSuccess(AsyncCallback<Geometry> callback, Geometry geometry) {
		callback.onSuccess (geometry);
	}
	
	public static void callbackError(AsyncCallback<Geometry> callback, String message) {
		callback.onFailure(new EXTGWTSTLException(message));
	}
	
	public static void callbackUpdateProgress(AsyncCallback<Geometry> callback, int total, int loaded) {
		com.google.gwt.user.client.Window.alert("loaded: " + loaded + " of " + total);
	}
}
