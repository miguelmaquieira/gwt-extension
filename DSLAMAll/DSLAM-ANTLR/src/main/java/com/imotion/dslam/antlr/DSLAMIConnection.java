package com.imotion.dslam.antlr;

public interface DSLAMIConnection {

	DSLAMIResponse executeCommand(String command);
	
}
