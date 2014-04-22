package com.imotion.gwt.webmessenger.client.handler;

import java.util.List;

public interface ExtGWTWMErrorHandlerManager {
	
	public void addErrorHandler(String roomId, ExtGWTWMHasErrorHandler handler);
	public void removeErrorHandler(String roomId, ExtGWTWMHasErrorHandler handler);
	public void releaseError(String roomId);
	
	public List<ExtGWTWMHasErrorHandler> getErrorHandlers(String roomId);
}
