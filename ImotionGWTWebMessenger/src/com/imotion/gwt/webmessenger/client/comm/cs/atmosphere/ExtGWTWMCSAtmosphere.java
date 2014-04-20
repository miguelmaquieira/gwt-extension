package com.imotion.gwt.webmessenger.client.comm.cs.atmosphere;

import java.util.HashMap;
import java.util.Map;

import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCSConnection;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCS;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHandlerManager;
import com.imotion.gwt.webmessenger.client.handler.impl.ExtGWTWMHandlerManagerImpl;

public class ExtGWTWMCSAtmosphere implements ExtGWTWMCommCS {
	
	private static final int DEFAULT_CLIENT_TIMEOUT = 300000;
	
	private ExtGWTWMHandlerManager 					handlerManager;
	private Map<String, ExtGWTWMCommCSConnection> 	connectionsMap;

	public ExtGWTWMCSAtmosphere() {
		
	}
	
	/**********************************************************************
	 *                   	   ExtGWTWMCommCS	    			          * 
	 **********************************************************************/
	@Override
	public ExtGWTWMCommCSConnection getConnection(String roomId, String userId) {
		return getConnection(roomId, userId, DEFAULT_CLIENT_TIMEOUT);
	}
	
	@Override
	public ExtGWTWMCommCSConnection getConnection(String roomId, String userId, int timeout) {
		String connectionKey = roomId + "_" + userId;
		ExtGWTWMCommCSConnection connection = getConnectionsMap().get(connectionKey);
		if (connection == null) {
			connection = new ExtGWTWMCSConnectionAtmosphere(getHandlerManager(), roomId, userId, timeout);
			getConnectionsMap().put(connectionKey, connection);
		}
		return connection;
	}

	@Override
	public void releaseConnection(ExtGWTWMCommCSConnection connection) {
		String connectionKey = connection.getSessionData().getRoomId() + "_" + connection.getSessionData().getUserId();
		getConnectionsMap().remove(connectionKey);
		connection.release();
	}
	
	
	/**********************************************************************
	 *                        PROTECTED FUNCTIONS						  *
	 **********************************************************************/
	

	
	/**********************************************************************
	 *                        PRIVATE FUNCTIONS					     	  *
	 **********************************************************************/
	private Map<String, ExtGWTWMCommCSConnection> getConnectionsMap() {
		if (connectionsMap == null) {
			connectionsMap = new HashMap<String, ExtGWTWMCommCSConnection>();
		}
		return connectionsMap;
	}
	
	private ExtGWTWMHandlerManager getHandlerManager() {
		if (handlerManager == null) {
			handlerManager = new ExtGWTWMHandlerManagerImpl();
		}
		return handlerManager;
	}
}
