package com.imotion.dslam.antlr;

import java.util.HashMap;
import java.util.Map;

public class CRONIOAntlrCompilationException extends RuntimeException {

	private static final long serialVersionUID = -9090482999423473484L;
	
	private Map<String, String> errors = new HashMap<>();
	
	public CRONIOAntlrCompilationException(String message) {
		super(message);
	}

	public CRONIOAntlrCompilationException(Throwable cause) {
		super(cause);
	}
	
	public CRONIOAntlrCompilationException(Map<String, String> errors) {
		super(errors.toString());
		this.errors.putAll(errors);
	}

	public Map<String, String> getErrors() {
		return errors;
	}

}
