package com.imotion.gwt.webmessenger.client;

public interface ExtGWTWebMessengerCommHandlerManager {
	
	public void add(ExtGWTWebMessengerHasCommHandler handler);
	public void add(String userId, ExtGWTWebMessengerHasCommHandler handler);
	public void remove(ExtGWTWebMessengerHasCommHandler handler);
	public void remove(String roomId, ExtGWTWebMessengerHasCommHandler handler);

}
