package com.imotion.gwt.webmessenger.client;

import com.imotion.gwt.webmessenger.client.common.ExtGWTWMError.TYPE;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMUtils;


public class ExtGWTWMException extends Exception {

	//
	private static final long serialVersionUID = 6910358675102290847L;
	
	private TYPE 	errorType;
	private String 	errorMessage;
	
	public ExtGWTWMException() {
		errorType = TYPE.UNDEFINED;
		errorMessage = super.getMessage();
	}
	
	public ExtGWTWMException(TYPE type, String message) {
		this.errorType 		= type;
		this.errorMessage 	= message;
	}
	
	public TYPE getErrorType() {
		return errorType;
	}

	public void setErrorType(TYPE errorType) {
		this.errorType = errorType;
	}

	public void setMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	@Override
	public String getMessage() {
		if (errorMessage == null) {
			errorMessage = super.getMessage();
		}
		return errorMessage;
	}
	
	public String getStacktrace() {
		return ExtGWTWMUtils.getStacktrace(this);
	}
}
