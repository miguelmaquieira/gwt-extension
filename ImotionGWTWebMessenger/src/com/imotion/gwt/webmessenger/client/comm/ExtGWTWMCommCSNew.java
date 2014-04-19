package com.imotion.gwt.webmessenger.client.comm;


public interface ExtGWTWMCommCSNew {

	public ExtGWTWMCommCSConnection getConnection(String roomId, String userId);
	public void releaseConnection(ExtGWTWMCommCSConnection connection);

}
