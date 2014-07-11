package com.imotion.dslam.conn;

public class CRONIOConnectionUncheckedException extends RuntimeException {

	private static final long serialVersionUID = 3871363566378889768L;

	public CRONIOConnectionUncheckedException() {
		super();
	}

	public CRONIOConnectionUncheckedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CRONIOConnectionUncheckedException(String message, Throwable cause) {
		super(message, cause);
	}

	public CRONIOConnectionUncheckedException(String message) {
		super(message);
	}

	public CRONIOConnectionUncheckedException(Throwable cause) {
		super(cause);
	}

}
