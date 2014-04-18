package com.imotion.gwt.webmessenger.client.handler;

import java.util.List;

public interface ExtGWTWMHandlerManager {
	
	public void addCommHandler(ExtGWTWMHasCommHandler handler);
	public void addCommHandler(String roomId, ExtGWTWMHasCommHandler handler);
	
	public void removeCommHandler(ExtGWTWMHasCommHandler handler);
	public void removeCommHandler(String roomId, ExtGWTWMHasCommHandler handler);
	
	public List<ExtGWTWMHasOpenCommHandler> getCommOpenHandlers(String roomId);
	public List<ExtGWTWMHasCloseCommHandler> getCommCloseHandlers(String roomId);
	public List<ExtGWTWMHasReceiveCommHandler> getCommReceiveHandlers(String roomId);
}
