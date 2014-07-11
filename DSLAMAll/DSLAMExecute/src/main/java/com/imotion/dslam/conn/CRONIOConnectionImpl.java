package com.imotion.dslam.conn;

import java.io.IOException;

import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.conn.wrapper.CRONIOConnectionIWrapper;
import com.imotion.dslam.conn.wrapper.CRONIOConnectionWrapperSSH;
import com.imotion.dslam.conn.wrapper.CRONIOConnectionWrapperTelnet;
import com.imotion.dslam.logger.CRONIOIExecutionLogger;

public class CRONIOConnectionImpl implements CRONIOIConnection {
	
	private String						connectionId;
	private CRONIOBOINode				node;
	private CRONIOIExecutionLogger		logger;
	private CRONIOConnectionIWrapper	connectionWrapper;

	public CRONIOConnectionImpl(long processId, CRONIOBOINode node, CRONIOIExecutionLogger	logger) {
		this.connectionId	= generateConnectionId(processId, node.getNodeId());
		this.node 			= node;
		this.logger			= logger;
		CRONIOBOIMachineProperties machineProperties = node.getMachineProperties();
		int protocolType = machineProperties.getProtocolType();
		if (CRONIOBOIMachineProperties.PROTOCOL_TYPE_SSH == protocolType) {
			connectionWrapper = new CRONIOConnectionWrapperSSH();
		} else if (CRONIOBOIMachineProperties.PROTOCOL_TYPE_TELNET == protocolType) {
			connectionWrapper = new CRONIOConnectionWrapperTelnet();
		}
	}
	
	@Override
	public CRONIOIExecutionData executeCommand(String command) {
		CRONIOIExecutionData executionData = null;
		connectionWrapper.sendCommand(command);
		try {
			String fullRespose	= connectionWrapper.getResponse();
			//EXAMPLE BEGIN
			String prompt		= "#prompt ";
			String response		= "reponse";
			executionData		= new CRONIOExecutionData(command, prompt, response);
			//EXAMPLE END
			getLogger().log(getConnectionId(), getNode(), executionData);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return executionData;
	}
	
	@Override
	public void closeConnection() {
		connectionWrapper.disconnect();
	}
	
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
		connectionIdSB.append(CONNECTION_ID_SEP);
		connectionIdSB.append(nodeId);
		return connectionIdSB.toString();
	}

}
