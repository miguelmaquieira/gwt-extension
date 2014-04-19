package com.imotion.gwt.webmessenger.client.comm;

import com.imotion.gwt.webmessenger.client.common.ExtGWTWMSession;

public interface ExtGWTWMCommCSConnection {
	
	public void release();
	public void connect();
	public void disconnect();
	public void sendMessage(String message);
	
	
	public void renameUser(String userId);
	public ExtGWTWMSession getSessionData();
	
	public ExtGWTWMCommCSHandlerNew getCommHandlerWrapper();
	public ExtGWTWMErrorCSHandlerNew getErrorHandlerWrapper();

}
