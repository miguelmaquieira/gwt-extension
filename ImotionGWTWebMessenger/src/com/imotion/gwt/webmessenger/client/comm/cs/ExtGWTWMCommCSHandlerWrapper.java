package com.imotion.gwt.webmessenger.client.comm.cs;

import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCSHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHandlerManager;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasAllCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasCloseCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasOpenCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasReceiveCommHandler;


public class ExtGWTWMCommCSHandlerWrapper implements ExtGWTWMCommCSHandler {
	
	private ExtGWTWMHandlerManager handlerManager;
	
	@SuppressWarnings("unused")
	private ExtGWTWMCommCSHandlerWrapper() {
		// not allowed
	}
	
	public ExtGWTWMCommCSHandlerWrapper(ExtGWTWMHandlerManager handlerManager) {
		this.handlerManager = handlerManager;
	}

	@Override
	public void addCommHandler(String roomId, ExtGWTWMHasAllCommHandler handler) {
		handlerManager.addCommHandler(roomId, handler);
	}

	@Override
	public void addCommHandler(ExtGWTWMHasAllCommHandler handler) {
		handlerManager.addCommHandler(handler);
	}

	@Override
	public void removeCommHandler(String roomId, ExtGWTWMHasAllCommHandler handler) {
		handlerManager.removeCommHandler(roomId, handler);
	}

	@Override
	public void removeCommHandler(ExtGWTWMHasAllCommHandler handler) {
		handlerManager.removeCommHandler(handler);
	}

	@Override
	public void addCommOpenHandler(String roomId, ExtGWTWMHasOpenCommHandler handler) {
		handlerManager.addCommHandler(roomId, handler);
	}

	@Override
	public void addCommOpenHandler(ExtGWTWMHasOpenCommHandler handler) {
		handlerManager.addCommHandler(handler);
	}

	@Override
	public void removeCommOpenHandler(String roomId, ExtGWTWMHasOpenCommHandler handler) {
		handlerManager.removeCommHandler(roomId, handler);
	}

	@Override
	public void removeCommOpenHandler(ExtGWTWMHasOpenCommHandler handler) {
		handlerManager.removeCommHandler(handler);
	}

	@Override
	public void addCommReceiveHandler(String roomId, ExtGWTWMHasReceiveCommHandler handler) {
		handlerManager.addCommHandler(roomId, handler);
	}

	@Override
	public void addCommReceiveHandler(ExtGWTWMHasReceiveCommHandler handler) {
		handlerManager.addCommHandler(handler);
	}

	@Override
	public void removeCommReceiveHandler(String roomId, ExtGWTWMHasReceiveCommHandler handler) {
		handlerManager.removeCommHandler(roomId, handler);
	}

	@Override
	public void removeCommReceiveHandler(ExtGWTWMHasReceiveCommHandler handler) {
		handlerManager.removeCommHandler(handler);
	}

	@Override
	public void addCommCloseHandler(String roomId, ExtGWTWMHasCloseCommHandler handler) {
		handlerManager.addCommHandler(roomId, handler);
	}

	@Override
	public void addCommCloseHandler(ExtGWTWMHasCloseCommHandler handler) {
		handlerManager.addCommHandler(handler);
	}

	@Override
	public void removeCommCloseHandler(String roomId, ExtGWTWMHasCloseCommHandler handler) {
		handlerManager.removeCommHandler(roomId, handler);
	}

	@Override
	public void removeCommCloseHandler(ExtGWTWMHasCloseCommHandler handler) {
		handlerManager.removeCommHandler(handler);
	}
}
