package com.imotion.gwt.webmessenger.client.comm.cs.atmosphere;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.imotion.gwt.webmessenger.client.ExtGWTWMException;
import com.imotion.gwt.webmessenger.client.ExtGWTWMMessageTexts;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCS;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCSConnection;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCSConnection.TRANSPORT_TYPE;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMError.TYPE;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHandlerManager;
import com.imotion.gwt.webmessenger.client.handler.impl.ExtGWTWMHandlerManagerImpl;

public class ExtGWTWMCSAtmosphere implements ExtGWTWMCommCS {
	
	private final ExtGWTWMMessageTexts 	MESSAGES 	= GWT.create(ExtGWTWMMessageTexts.class);
	
	private static final int DEFAULT_CLIENT_TIMEOUT = 300000;
	
	private ExtGWTWMHandlerManager 					handlerManager;
	private Map<String, ExtGWTWMCommCSConnection> 	connectionsMap;

	public ExtGWTWMCSAtmosphere() {
		
	}
	
	/**********************************************************************
	 *                   	   ExtGWTWMCommCS	    			          * 
	 **********************************************************************/
	@Override
	public ExtGWTWMCommCSConnection getConnection(String roomId, String userId) throws ExtGWTWMException {
		return getConnection(roomId, userId, DEFAULT_CLIENT_TIMEOUT);
	}
	
	@Override
	public ExtGWTWMCommCSConnection getConnection(String roomId, String userId, int timeout) throws ExtGWTWMException {
		return getConnection(roomId, userId, timeout, TRANSPORT_TYPE.WEBSOCKETS, TRANSPORT_TYPE.STREAMING);
	}
	
	@Override
	public ExtGWTWMCommCSConnection getConnection(String roomId, String userId, TRANSPORT_TYPE protocol, TRANSPORT_TYPE fallback) throws ExtGWTWMException {
		return getConnection(roomId, userId, DEFAULT_CLIENT_TIMEOUT, protocol, fallback);
	}

	@Override
	public ExtGWTWMCommCSConnection getConnection(String roomId, String userId, int timeout, TRANSPORT_TYPE protocol, TRANSPORT_TYPE fallback) throws ExtGWTWMException {
		if (roomId == null || roomId.length() == 0 || userId == null || userId.length() == 0) {
			throw new ExtGWTWMException(TYPE.COMMAND, MESSAGES.error_get_connection_param_not_informed_message_text(roomId, userId));
		}
		String connectionKey = roomId + "_" + userId;
		ExtGWTWMCommCSConnection connection = getConnectionsMap().get(connectionKey);
		if (connection == null) {	
			connection = new ExtGWTWMCSConnectionAtmosphere(getHandlerManager(), roomId, userId, timeout, protocol, fallback);
			getConnectionsMap().put(connectionKey, connection);
		}
		return connection;
	}

	@Override
	public void releaseConnection(ExtGWTWMCommCSConnection connection) {
		if (connection != null) {
			String connectionKey = connection.getSessionData().getRoomId() + "_" + connection.getSessionData().getUserId();
			Map<String, ExtGWTWMCommCSConnection> connectionMap = getConnectionsMap();
			connectionMap.remove(connectionKey);
			if (connectionMap.isEmpty()) {
				connection.unsubscribe();
			}
			connection.release();
			connection = null;
		}
	}
	
	@Override
	public void releaseCloseConnection(ExtGWTWMCommCSConnection connection) {
		if (connection != null) {
			String connectionKey = connection.getSessionData().getRoomId() + "_" + connection.getSessionData().getUserId();
			Map<String, ExtGWTWMCommCSConnection> connectionMap = getConnectionsMap();
			connectionMap.remove(connectionKey);
			connection.release();
			connection = null;
		}
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
