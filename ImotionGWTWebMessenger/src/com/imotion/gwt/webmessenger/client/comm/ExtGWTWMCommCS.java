package com.imotion.gwt.webmessenger.client.comm;

import com.imotion.gwt.webmessenger.client.session.ExtGWTWMSession;

public interface ExtGWTWMCommCS extends ExtGWTWMCommCSHandler {

	public void init(String nickname, String roomname);
	public void connect(String nickname, String roomname);
	public void connect(String nickname);
	public void connect();
	public void disconnect();
	public void sendMessage(String message);
	
	public ExtGWTWMSession getSessionData();
}
