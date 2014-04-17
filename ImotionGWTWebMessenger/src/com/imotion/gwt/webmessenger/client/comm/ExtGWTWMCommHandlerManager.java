package com.imotion.gwt.webmessenger.client.comm;

import java.util.List;

public interface ExtGWTWMCommHandlerManager <T extends ExtGWTWMHasCommHandler> {
	
	public void addCommHandler(T handler);
	public void addCommHandler(String roomId, T handler);
	
	public void removeCommHandler(T handler);
	public void removeCommHandler(String roomId, T handler);
	
	public <H extends ExtGWTWMHasCommHandler> List<H> getHandlers(Class<H> type, String room);
}
