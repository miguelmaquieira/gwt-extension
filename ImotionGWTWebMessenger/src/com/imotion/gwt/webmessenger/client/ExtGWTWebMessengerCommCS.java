package com.imotion.gwt.webmessenger.client;

public interface ExtGWTWebMessengerCommCS {

	public void sendMessage(String message);
	public void disconnect();
	public void autoReconnection(long timeframe);
	public void initConnection(String nickname, String roomname);
	public void reconnect(String nickname, String roomname);
	public void reconnect(String nickname);
}
