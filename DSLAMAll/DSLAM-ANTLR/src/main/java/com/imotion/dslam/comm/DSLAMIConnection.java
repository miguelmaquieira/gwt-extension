package com.imotion.dslam.comm;

public interface DSLAMIConnection {

	DSLAMIResponse executeCommand(String command);
	
}
