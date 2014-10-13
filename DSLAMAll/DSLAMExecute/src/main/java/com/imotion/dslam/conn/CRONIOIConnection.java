package com.imotion.dslam.conn;

import java.io.IOException;

public interface CRONIOIConnection {
	
	String CONNECTION_ID_SEP = ":";
	
	void openConnection() throws IOException;
	
	/**
	 * Execute a command and read entire response
	 * @param command
	 * @return execution data
	 * @throws CRONIOConnectionUncheckedException
	 */
	CRONIOIExecutionData executeCommand(String command) throws CRONIOConnectionUncheckedException;
	
	/**
	 * Execute a command and read until the sent command (included)
	 * @param command
	 * @return execution data
	 * @throws IOException
	 */
	CRONIOIExecutionData executeCommandWithoutRead(String command) throws CRONIOConnectionUncheckedException;
	
	/**
	 * Gets response until regExp (included)
	 * @param regExp
	 * @return read response
	 * @throws CRONIOConnectionUncheckedException
	 */
	String readUntil(String regExp) throws CRONIOConnectionUncheckedException;
	
	void closeConnection();

	CRONIOIExecutionData logMessage(String strCompositeLevel, String message);

}
