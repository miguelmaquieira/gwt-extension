package com.imotion.gwt.webmessenger.client.comm;

import java.util.List;

import com.imotion.gwt.webmessenger.client.ExtGWTWMReleasable;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasErrorHandler;

public interface ExtGWTWMErrorCSHandler extends ExtGWTWMReleasable {
	
	public void addErrorHandler(ExtGWTWMHasErrorHandler handler);
	
	public void removeErrorHandler(ExtGWTWMHasErrorHandler handler);
	
	public List<ExtGWTWMHasErrorHandler> getErrorHandlers();

}
