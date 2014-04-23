package com.imotion.gwt.webmessenger.client.comm;

import com.imotion.gwt.webmessenger.client.ExtGWTWMException;


public interface ExtGWTWMCommCS {

	public ExtGWTWMCommCSConnection getConnection(String roomId, String userId) throws ExtGWTWMException ;
	public ExtGWTWMCommCSConnection getConnection(String roomId, String userId, int timeout) throws ExtGWTWMException;
	public void releaseConnection(ExtGWTWMCommCSConnection connection);

}
