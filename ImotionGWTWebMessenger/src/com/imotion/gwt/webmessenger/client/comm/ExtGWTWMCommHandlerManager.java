package com.imotion.gwt.webmessenger.client.comm;

import java.util.List;

public interface ExtGWTWMCommHandlerManager {
	
	public void addCommHandler(ExtGWTWMHasCommHandler handler);
	public void addCommHandler(String roomId, ExtGWTWMHasCommHandler handler);
	
	public void removeCommHandler(ExtGWTWMHasCommHandler handler);
	public void removeCommHandler(String roomId, ExtGWTWMHasCommHandler handler);
	
	public List<ExtGWTWMHasOpenCommHandler> getCommOpenHandlers(String roomId);
	public List<ExtGWTWMHasCloseCommHandler> getCommCloseHandlers(String roomId);
	public List<ExtGWTWMHasSendCommHandler> getCommSendHandlers(String roomId);
	public List<ExtGWTWMHasReceiveCommHandler> getCommReceiveHandlers(String roomId);
}
