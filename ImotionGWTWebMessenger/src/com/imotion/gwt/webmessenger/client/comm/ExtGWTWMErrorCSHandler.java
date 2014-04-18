package com.imotion.gwt.webmessenger.client.comm;

import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasErrorHandler;

public interface ExtGWTWMErrorCSHandler {
	
	public void addErrorHandler(ExtGWTWMHasErrorHandler handler);
	public void addErrorHandler(String roomId, ExtGWTWMHasErrorHandler handler);
	
	public void removeErrorHandler(ExtGWTWMHasErrorHandler handler);
	public void removeErrorHandler(String roomId, ExtGWTWMHasErrorHandler handler);

}
