package com.imotion.dslam.conn;

import java.io.IOException;

public interface CRONIOIConnection {
	
	String CONNECTION_ID_SEP = ":";
	
	void openConnection() throws IOException;
	
	CRONIOIExecutionData executeCommand(String command) throws CRONIOConnectionUncheckedException;
	
	String readUntil(String regExp) throws CRONIOConnectionUncheckedException;
	
	void closeConnection();

}
