package com.imotion.gwt.webmessenger.client.comm;


public interface ExtGWTWMCommCS {

	public ExtGWTWMCommCSConnection getConnection(String roomId, String userId);
	public ExtGWTWMCommCSConnection getConnection(String roomId, String userId, int timeout);
	public void releaseConnection(ExtGWTWMCommCSConnection connection);

}
