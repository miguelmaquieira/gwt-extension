package com.imotion.gwt.webmessenger.client.handler;

import java.util.List;

public interface ExtGWTWMErrorHandlerManager {
	
	public void addErrorHandler(ExtGWTWMHasErrorHandler handler);
	public void addErrorHandler(String roomId, ExtGWTWMHasErrorHandler handler);
	
	public void removeErrorHandler(ExtGWTWMHasErrorHandler handler);
	public void removeErrorHandler(String roomId, ExtGWTWMHasErrorHandler handler);
	
	public List<ExtGWTWMHasErrorHandler> getErrorHandlers(String roomId);
}
