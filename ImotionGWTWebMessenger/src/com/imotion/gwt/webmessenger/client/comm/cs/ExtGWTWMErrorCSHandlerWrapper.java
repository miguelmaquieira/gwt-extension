package com.imotion.gwt.webmessenger.client.comm.cs;

import java.util.List;

import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMErrorCSHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHandlerManager;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasErrorHandler;

public class ExtGWTWMErrorCSHandlerWrapper implements ExtGWTWMErrorCSHandler {
	
	private ExtGWTWMHandlerManager handlerManager;
	private String roomId;
	
	@SuppressWarnings("unused")
	private ExtGWTWMErrorCSHandlerWrapper() {
		// not allowed
	}
	
	public ExtGWTWMErrorCSHandlerWrapper(ExtGWTWMHandlerManager handlerManager, String roomId) {
		this.handlerManager = handlerManager;
		this.roomId = roomId;
	}

	@Override
	public void addErrorHandler(ExtGWTWMHasErrorHandler handler) {
		handlerManager.addErrorHandler(roomId, handler);
	}

	@Override
	public void removeErrorHandler(ExtGWTWMHasErrorHandler handler) {
		handlerManager.removeErrorHandler(roomId, handler);
	}

	@Override
	public List<ExtGWTWMHasErrorHandler> getErrorHandlers() {
		return handlerManager.getErrorHandlers(roomId);
	}

	@Override
	public void release() {
		handlerManager.releaseError(roomId);
		handlerManager = null;
		roomId = null;
	}
}
