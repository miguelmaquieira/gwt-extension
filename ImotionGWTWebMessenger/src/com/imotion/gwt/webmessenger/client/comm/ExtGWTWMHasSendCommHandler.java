package com.imotion.gwt.webmessenger.client.comm;

public interface ExtGWTWMHasSendCommHandler extends ExtGWTWMHasCommHandler {

	public void handleSendMessage(String message, long timestamp, String sender);
}
