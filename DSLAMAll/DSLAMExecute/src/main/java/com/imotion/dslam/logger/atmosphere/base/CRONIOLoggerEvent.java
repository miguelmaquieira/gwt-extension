package com.imotion.dslam.logger.atmosphere.base;

import java.io.Serializable;

public class CRONIOLoggerEvent implements Serializable {

	private static final long serialVersionUID = 6796892836560128260L;

	private String connectionId;
	private String nodeName;
	private String request;
	private String response;
	private String prompt;
	private String	fullTrace;
	
	public CRONIOLoggerEvent() {}

	public String getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(String connectionId) {
		this.connectionId = connectionId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public String getFullTrace() {
		return fullTrace;
	}

	public void setFullTrace(String fullTrace) {
		this.fullTrace = fullTrace;
	}

}
