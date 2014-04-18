package com.imotion.gwt.webmessenger.client.handler;

import com.imotion.gwt.webmessenger.client.common.ExtGWTWMError;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMError.TYPE;

public interface ExtGWTWMHasErrorHandler {
	
	public void onError(ExtGWTWMError error);
	
	public TYPE[] getErrorType();

}
