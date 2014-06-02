package com.imotion.gwt.webmessenger.client.comm;

import com.imotion.gwt.webmessenger.client.ExtGWTWMException;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCSConnection.TRANSPORT_TYPE;


public interface ExtGWTWMCommCS {

	public ExtGWTWMCommCSConnection getConnection(String roomId, String userId) throws ExtGWTWMException ;
	public ExtGWTWMCommCSConnection getConnection(String roomId, String userId, TRANSPORT_TYPE protocol, TRANSPORT_TYPE fallback) throws ExtGWTWMException ;
	public ExtGWTWMCommCSConnection getConnection(String roomId, String userId, int timeout) throws ExtGWTWMException;
	public ExtGWTWMCommCSConnection getConnection(String roomId, String userId, int timeout, TRANSPORT_TYPE protocol, TRANSPORT_TYPE fallback) throws ExtGWTWMException;
	public void releaseConnection(ExtGWTWMCommCSConnection connection);
}
