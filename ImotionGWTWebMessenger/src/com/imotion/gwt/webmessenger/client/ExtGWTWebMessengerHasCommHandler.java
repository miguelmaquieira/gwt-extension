package com.imotion.gwt.webmessenger.client;

public interface ExtGWTWebMessengerHasCommHandler {

	public void handleReceivedMessage(String text, long date, String sender);
	public void handleConnectionOpened();
	public void handleConnectionClosed();
	
}
