package com.imotion.gwt.webmessenger.client.comm.cs;

import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCSHandlerNew;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHandlerManager;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasAllCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasCloseCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasOpenCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasReceiveCommHandler;


public class ExtGWTWMCommCSHandlerWrapperNew implements ExtGWTWMCommCSHandlerNew {
	
	private ExtGWTWMHandlerManager handlerManager;
	
	@SuppressWarnings("unused")
	private ExtGWTWMCommCSHandlerWrapperNew() {
		// not allowed
	}
	
	public ExtGWTWMCommCSHandlerWrapperNew(ExtGWTWMHandlerManager handlerManager) {
		this.handlerManager = handlerManager;
	}

	@Override
	public void addCommHandler(ExtGWTWMHasAllCommHandler handler) {
		handlerManager.addCommHandler(handler);
	}

	@Override
	public void removeCommHandler(ExtGWTWMHasAllCommHandler handler) {
		handlerManager.removeCommHandler(handler);
	}

	@Override
	public void addCommOpenHandler(ExtGWTWMHasOpenCommHandler handler) {
		handlerManager.addCommHandler(handler);
	}

	@Override
	public void removeCommOpenHandler(ExtGWTWMHasOpenCommHandler handler) {
		handlerManager.removeCommHandler(handler);
	}

	@Override
	public void addCommReceiveHandler(ExtGWTWMHasReceiveCommHandler handler) {
		handlerManager.addCommHandler(handler);
	}

	@Override
	public void removeCommReceiveHandler(ExtGWTWMHasReceiveCommHandler handler) {
		handlerManager.removeCommHandler(handler);
	}

	@Override
	public void addCommCloseHandler(ExtGWTWMHasCloseCommHandler handler) {
		handlerManager.addCommHandler(handler);
	}

	@Override
	public void removeCommCloseHandler(ExtGWTWMHasCloseCommHandler handler) {
		handlerManager.removeCommHandler(handler);
	}
}
