package com.imotion.gwt.webmessenger.client.common;

import java.io.Serializable;



public class ExtGWTWMError implements Serializable {
	
	// serial UID
	private static final long serialVersionUID = -6706544960221285813L;

	public enum TYPE {
		ALL,
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
	
	public ExtGWTWMError(TYPE type) {
		this.errorType = type;
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
	
	@Override
	public String toString() {
		StringBuilder sbError = new StringBuilder();
			sbError.append("errorType: ").append(getErrorType().toString()).append("\n")
					.append("message: ").append(getMessage()).append("\n")
					.append("exception: ").append(exception);
		return sbError.toString();
	}
}
