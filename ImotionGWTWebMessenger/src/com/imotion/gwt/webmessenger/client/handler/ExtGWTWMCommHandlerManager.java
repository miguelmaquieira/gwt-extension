package com.imotion.gwt.webmessenger.client.handler;

import java.util.List;

public interface ExtGWTWMCommHandlerManager {
	
	public void addCommHandler(String roomId, ExtGWTWMHasCommHandler handler);
	public void removeCommHandler(String roomId, ExtGWTWMHasCommHandler handler);
	public void releaseComm(String roomId);
	
	public List<ExtGWTWMHasOpenCommHandler> getCommOpenHandlers(String roomId);
	public List<ExtGWTWMHasCloseCommHandler> getCommCloseHandlers(String roomId);
	public List<ExtGWTWMHasReceiveCommHandler> getCommReceiveHandlers(String roomId);

}
