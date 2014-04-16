package com.imotion.gwt.webmessenger.client;

import com.imotion.gwt.webmessenger.client.session.ExtGWTWebMessengerSession;

public interface ExtGWTWebMessengerCommCS extends ExtGWTWebMessengerCommHandlerManager {

	public void sendMessage(String message);
	public void disconnect();
	public void autoReconnection(long timeframe);
	public void initConnection(String nickname, String roomname);
	public void reconnect(String nickname, String roomname);
	public void reconnect(String nickname);
	
	public ExtGWTWebMessengerSession getSessionData();
	
}
