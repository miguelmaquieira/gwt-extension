package com.imotion.gwt.webmessenger.client.common;


public class ExtGWTWMError {
	
	public enum TYPE {
		EXCEPTION,
		COMMAND,
		TRANSPORT,
		WRAPPER_CS,
		UNDEFINED
	}
	
	
	@SuppressWarnings("unused")
	private ExtGWTWMError() {
		// not allowed
	}
	
	public  ExtGWTWMError(String message) {
		this.errorType = TYPE.UNDEFINED;
		this.message = message;
	}
	
	public  ExtGWTWMError(TYPE type, String message) {
		this.errorType = type;
		this.message = message;
	}
	
	public  ExtGWTWMError(Exception exception) {
		this.errorType = TYPE.EXCEPTION;
		this.exception = exception;
		this.message = ExtGWTWMUtils.getStacktrace(exception);
	}
	
	public  ExtGWTWMError(TYPE type, String message, Exception exception) {
		this.errorType = type;
		this.exception = exception;
		this.message = message;
	}
	
	private TYPE 		errorType;
	private String 		message;
	private Exception	exception;
	
	public TYPE getErrorType() {
		return errorType;
	}
	public void setErrorType(TYPE errorType) {
		this.errorType = errorType;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
}
