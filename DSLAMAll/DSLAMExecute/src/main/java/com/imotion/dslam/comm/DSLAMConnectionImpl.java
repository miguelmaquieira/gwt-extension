package com.imotion.dslam.comm;

public class DSLAMConnectionImpl implements DSLAMIConnection {

	public DSLAMConnectionImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public DSLAMIResponse executeCommand(String command) {
		return new DSLAMResponse(command, "prompt", "RESPONSE");
	}

}
