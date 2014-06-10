package com.imotion.dslam.antlr;

public class DSLAMResponse implements DSLAMIResponse {

	private String sourceCommand;
	private String prompt;
	private String response;
	
	public DSLAMResponse(String sourceCommand, String prompt, String response) {
		this.sourceCommand	= sourceCommand;
		this.prompt			= prompt;
		this.response		= response;
	}
	
	@Override
	public String getSourceCommand() {
		return sourceCommand;
	}

	@Override
	public String getPrompt() {
		return prompt;
	}

	@Override
	public String getResponse() {
		return response;
	}

}
