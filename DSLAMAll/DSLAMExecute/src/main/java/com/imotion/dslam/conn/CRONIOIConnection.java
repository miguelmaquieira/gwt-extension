package com.imotion.dslam.conn;

public interface CRONIOIConnection {
	
	String CONNECTION_ID_SEP = ":";

	CRONIOIExecutionData executeCommand(String command);
	
}
