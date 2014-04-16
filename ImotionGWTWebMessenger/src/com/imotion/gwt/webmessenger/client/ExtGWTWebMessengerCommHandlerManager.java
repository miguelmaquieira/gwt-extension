package com.imotion.gwt.webmessenger.client;

public interface ExtGWTWebMessengerCommHandlerManager {
	
	public void addCommHandler(ExtGWTWebMessengerHasCommHandler handler);
	public void addCommHandler(String roomId, ExtGWTWebMessengerHasCommHandler handler);
	public void removeCommHandler(ExtGWTWebMessengerHasCommHandler handler);
	public void removeCommHandler(String roomId, ExtGWTWebMessengerHasCommHandler handler);

}
