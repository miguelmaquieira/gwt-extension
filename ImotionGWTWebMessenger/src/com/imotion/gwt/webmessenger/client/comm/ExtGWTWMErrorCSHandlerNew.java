package com.imotion.gwt.webmessenger.client.comm;

import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasErrorHandler;

public interface ExtGWTWMErrorCSHandlerNew {
	
	public void addErrorHandler(ExtGWTWMHasErrorHandler handler);
	
	public void removeErrorHandler(ExtGWTWMHasErrorHandler handler);

}
