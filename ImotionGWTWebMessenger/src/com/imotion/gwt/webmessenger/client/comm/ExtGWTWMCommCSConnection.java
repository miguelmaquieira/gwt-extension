package com.imotion.gwt.webmessenger.client.comm;

import com.imotion.gwt.webmessenger.client.ExtGWTWMReleasable;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMSession;

public interface ExtGWTWMCommCSConnection extends ExtGWTWMReleasable {
	
	public enum TRANSPORT_TYPE {
		POLLING,
		LONG_POLLING,
		STREAMING, 
		WEBSOCKETS,
		SSE,
		SESSION
	}
	
	public void connect();
	public void connect(int connectionTimeout);
	public void disconnect();
	public void sendMessage(String message);
	
	public ExtGWTWMSession getSessionData();
	
	public ExtGWTWMCommCSHandler getCommHandlerWrapper();
	public ExtGWTWMErrorCSHandler getErrorHandlerWrapper();

}
