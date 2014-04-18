package com.imotion.gwt.webmessenger.client.comm.cs;

import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMErrorCSHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHandlerManager;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasErrorHandler;

public class ExtGWTWMErrorCSHandlerWrapper implements ExtGWTWMErrorCSHandler {
	
	private ExtGWTWMHandlerManager handlerManager;
	
	@SuppressWarnings("unused")
	private ExtGWTWMErrorCSHandlerWrapper() {
		// not allowed
	}
	
	public ExtGWTWMErrorCSHandlerWrapper(ExtGWTWMHandlerManager handlerManager) {
		this.handlerManager = handlerManager;
	}

	@Override
	public void addErrorHandler(ExtGWTWMHasErrorHandler handler) {
		handlerManager.addErrorHandler(handler);

	}

	@Override
	public void addErrorHandler(String roomId, ExtGWTWMHasErrorHandler handler) {
		handlerManager.addErrorHandler(roomId, handler);
	}

	@Override
	public void removeErrorHandler(ExtGWTWMHasErrorHandler handler) {
		handlerManager.removeErrorHandler(handler);
	}

	@Override
	public void removeErrorHandler(String roomId, ExtGWTWMHasErrorHandler handler) {
		handlerManager.removeErrorHandler(roomId, handler);	
	}
}
