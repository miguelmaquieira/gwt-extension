package com.imotion.dslam.conn;

public class CRONIOExecutionData implements CRONIOIExecutionData {

	private String sourceCommand;
	private String prompt;
	private String response;
	
	public CRONIOExecutionData(String sourceCommand, String prompt, String response) {
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
