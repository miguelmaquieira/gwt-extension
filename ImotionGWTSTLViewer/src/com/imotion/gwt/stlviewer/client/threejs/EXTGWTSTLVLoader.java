package com.imotion.gwt.stlviewer.client.threejs;

import com.akjava.gwt.three.client.core.Geometry;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.imotion.gwt.stlviewer.client.exception.EXTGWTSTLException;
import com.imotion.gwt.stlviewer.client.widget.EXTGWTSTLILoaderDisplay;

public class EXTGWTSTLVLoader {
	
	private EXTGWTSTLILoaderDisplay renderer;
	
	private EXTGWTSTLVLoader() {
		
	}

	public EXTGWTSTLVLoader(EXTGWTSTLILoaderDisplay renderer) {
		this.renderer = renderer;
	}

	public native final void load(String url, AsyncCallback<Geometry> callback) /*-{
		var loader = new $wnd.THREE.STLLoader();
		
		var instance = this;
		loader.addEventListener( 'load', function ( event ) {
			var geometry = event.content;
			instance.@com.imotion.gwt.stlviewer.client.threejs.EXTGWTSTLVLoader::callbackSuccess(Lcom/google/gwt/user/client/rpc/AsyncCallback;Lcom/akjava/gwt/three/client/core/Geometry;)(callback, geometry);
		} );
		
		loader.addEventListener( 'error', function ( event ) {
			var message = event.message;
			instance.@com.imotion.gwt.stlviewer.client.threejs.EXTGWTSTLVLoader::callbackError(Lcom/google/gwt/user/client/rpc/AsyncCallback;Ljava/lang/String;)(callback, message);
		} );
		
		loader.addEventListener( 'progress', function ( event ) {
			instance.@com.imotion.gwt.stlviewer.client.threejs.EXTGWTSTLVLoader::callbackUpdateProgress(Lcom/google/gwt/user/client/rpc/AsyncCallback;II)(callback, event.total, event.loaded);
		});
		
		loader.load(url);
	}-*/;
	
	public void callbackSuccess(AsyncCallback<Geometry> callback, Geometry geometry) {
		callback.onSuccess (geometry);
	}
	
	public void callbackError(AsyncCallback<Geometry> callback, String message) {
		callback.onFailure(new EXTGWTSTLException(message));
	}
	
	public void callbackUpdateProgress(AsyncCallback<Geometry> callback, int total, int loaded) {
		float div = loaded / (float)total;
		int percentage = Math.round(div * 100);
		renderer.setProgress(percentage);
	}
}
