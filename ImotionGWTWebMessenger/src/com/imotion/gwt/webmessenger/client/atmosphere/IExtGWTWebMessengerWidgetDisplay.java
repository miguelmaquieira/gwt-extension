package com.imotion.gwt.webmessenger.client.atmosphere;

public interface IExtGWTWebMessengerWidgetDisplay {

	public void handleReceivedMessage(String text, long date, String sender);
	public void handleConnectionOpened();
	public void handleConnectionClosed();
	
}
