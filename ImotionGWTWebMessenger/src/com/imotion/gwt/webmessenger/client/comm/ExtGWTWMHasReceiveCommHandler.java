package com.imotion.gwt.webmessenger.client.comm;

public interface ExtGWTWMHasReceiveCommHandler extends ExtGWTWMHasCommHandler {

	public void handleReceivedMessage(String message, long timstamp, String sender);
}
