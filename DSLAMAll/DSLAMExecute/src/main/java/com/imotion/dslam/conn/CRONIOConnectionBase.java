package com.imotion.dslam.conn;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.logger.CRONIOIExecutionLogger;

public abstract class CRONIOConnectionBase implements CRONIOIConnection {
	
	private String					connectionId;
	private CRONIOBOINode			node;
	private CRONIOIExecutionLogger	logger;

	public CRONIOConnectionBase(long processId, CRONIOBOINode node, CRONIOIExecutionLogger	logger) {
		this.connectionId	= generateConnectionId(processId, node.getNodeId());
		this.node 			= node;
		this.logger			= logger;
	}
	
	@Override
	public CRONIOIExecutionData executeCommand(String command) {
		CRONIOIExecutionData executionData = executeNativeCommand(command);
		getLogger().log(getConnectionId(), getNode(), executionData);
		return executionData;
	}
	
	/**
	 *  PROTECTED
	 */
	
	protected abstract CRONIOIExecutionData executeNativeCommand(String command);
	
	/**
	 *	PRIVATE 
	 */
	
	private String getConnectionId() {
		return connectionId;
	}
	
	private CRONIOBOINode getNode() {
		return node;
	}
	
	private CRONIOIExecutionLogger getLogger() {
		return logger;
	}
	
	private String generateConnectionId(long processId, long nodeId) {
		StringBuilder connectionIdSB = new StringBuilder();
		connectionIdSB.append(processId);
		connectionIdSB.append(nodeId);
		return connectionIdSB.toString();
	}

}
