package com.imotion.gwt.webmessenger.client.comm.cs;

import java.util.List;

import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCSHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHandlerManager;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasAllCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasCloseCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasOpenCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasReceiveCommHandler;


public class ExtGWTWMCommCSHandlerWrapper implements ExtGWTWMCommCSHandler {
	
	private ExtGWTWMHandlerManager handlerManager;
	private String roomId;
	
	@SuppressWarnings("unused")
	private ExtGWTWMCommCSHandlerWrapper() {
		// not allowed
	}
	
	public ExtGWTWMCommCSHandlerWrapper(ExtGWTWMHandlerManager handlerManager, String roomId) {
		this.handlerManager = handlerManager;
		this.roomId 		= roomId;
	}

	@Override
	public void addCommHandler(ExtGWTWMHasAllCommHandler handler) {
		handlerManager.addCommHandler(roomId, handler);
	}

	@Override
	public void removeCommHandler(ExtGWTWMHasAllCommHandler handler) {
		handlerManager.removeCommHandler(roomId, handler);
	}

	@Override
	public void addCommOpenHandler(ExtGWTWMHasOpenCommHandler handler) {
		handlerManager.addCommHandler(roomId, handler);
	}

	@Override
	public void removeCommOpenHandler(ExtGWTWMHasOpenCommHandler handler) {
		handlerManager.removeCommHandler(roomId, handler);
	}
	
	@Override
	public List<ExtGWTWMHasOpenCommHandler> getCommOpenHandlers() {
		return handlerManager.getCommOpenHandlers(roomId);
	}

	@Override
	public void addCommReceiveHandler(ExtGWTWMHasReceiveCommHandler handler) {
		handlerManager.addCommHandler(roomId, handler);
	}

	@Override
	public void removeCommReceiveHandler(ExtGWTWMHasReceiveCommHandler handler) {
		handlerManager.removeCommHandler(roomId, handler);
	}
	
	@Override
	public List<ExtGWTWMHasReceiveCommHandler> getCommReceiveHandlers() {
		return handlerManager.getCommReceiveHandlers(roomId);
	}

	@Override
	public void addCommCloseHandler(ExtGWTWMHasCloseCommHandler handler) {
		handlerManager.addCommHandler(roomId, handler);
	}

	@Override
	public void removeCommCloseHandler(ExtGWTWMHasCloseCommHandler handler) {
		handlerManager.removeCommHandler(roomId, handler);
	}

	@Override
	public List<ExtGWTWMHasCloseCommHandler> getCommCloseHandlers() {
		return handlerManager.getCommCloseHandlers(roomId);
	}

	@Override
	public void release() {
		handlerManager.releaseCommHandlers(roomId);
		handlerManager = null;
		roomId = null;
	}
}
