package com.imotion.gwt.webmessenger.client.comm;

import com.imotion.gwt.webmessenger.client.common.ExtGWTWMSession;

public interface ExtGWTWMCommCS {

	public void init(String userId, String roomId);
	public void connect(String userId, String roomId);
	public void connect(String userId);
	public void connect();
	public void disconnect();
	public void sendMessage(String message);
	
	public ExtGWTWMSession getSessionData();
	
	public ExtGWTWMCommCSHandler getCommHandlerWrapper();
	public ExtGWTWMErrorCSHandler getErrorHandlerWrapper();
}
