package com.imotion.gwt.webmessenger.client.comm.cs;

import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMErrorCSHandlerNew;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHandlerManager;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasErrorHandler;

public class ExtGWTWMErrorCSHandlerWrapperNew implements ExtGWTWMErrorCSHandlerNew {
	
	private ExtGWTWMHandlerManager handlerManager;
	
	@SuppressWarnings("unused")
	private ExtGWTWMErrorCSHandlerWrapperNew() {
		// not allowed
	}
	
	public ExtGWTWMErrorCSHandlerWrapperNew(ExtGWTWMHandlerManager handlerManager) {
		this.handlerManager = handlerManager;
	}

	@Override
	public void addErrorHandler(ExtGWTWMHasErrorHandler handler) {
		handlerManager.addErrorHandler(handler);
	}

	@Override
	public void removeErrorHandler(ExtGWTWMHasErrorHandler handler) {
		handlerManager.removeErrorHandler(handler);
	}
}
