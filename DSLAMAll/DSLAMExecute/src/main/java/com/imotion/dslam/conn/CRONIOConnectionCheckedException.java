package com.imotion.dslam.conn;

public class CRONIOConnectionCheckedException extends Exception {

	private static final long serialVersionUID = -7412109688080394422L;

	public CRONIOConnectionCheckedException() {
		super();
	}

	public CRONIOConnectionCheckedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CRONIOConnectionCheckedException(String message, Throwable cause) {
		super(message, cause);
	}

	public CRONIOConnectionCheckedException(String message) {
		super(message);
	}

	public CRONIOConnectionCheckedException(Throwable cause) {
		super(cause);
	}

}
