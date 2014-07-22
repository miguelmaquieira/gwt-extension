package com.imotion.dslam.conn;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.conn.wrapper.CRONIOConnectionIWrapper;
import com.imotion.dslam.conn.wrapper.CRONIOConnectionWrapperSSH;
import com.imotion.dslam.conn.wrapper.CRONIOConnectionWrapperTelnet;
import com.imotion.dslam.logger.CRONIOIExecutionLogger;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;

public class CRONIOConnectionImpl implements CRONIOIConnection {

	private String						connectionId;
	private CRONIOBOINode				node;
	private CRONIOIExecutionLogger		logger;
	private CRONIOConnectionIWrapper	connectionWrapper;
	private Pattern 					patternPrompt;
	private String						promptRegEx;
	private int 						protocolType;

	public CRONIOConnectionImpl(long processId, CRONIOBOINode node, CRONIOIExecutionLogger	logger) {
		CRONIOBOIMachineProperties machineProperties = node.getMachineProperties();
		this.connectionId	= generateConnectionId(processId, node.getNodeId());
		this.node 			= node;
		this.logger			= logger;
		this.promptRegEx 	= machineProperties.getPromptRegEx();
		this.patternPrompt 	= Pattern.compile(promptRegEx);
		this.protocolType 	= machineProperties.getProtocolType();
		if (CRONIOBOIMachineProperties.PROTOCOL_TYPE_SSH == protocolType) {
			connectionWrapper = new CRONIOConnectionWrapperSSH();
		} else if (CRONIOBOIMachineProperties.PROTOCOL_TYPE_TELNET == protocolType) {
			connectionWrapper = new CRONIOConnectionWrapperTelnet();
		}
//		connectionWrapper = new CRONIOConnectionWrapperDummy();
	}

	@Override
	public void openConnection() throws IOException {
		connectionWrapper.connect(node);
	}

	@Override
	public CRONIOIExecutionData executeCommand(String command) throws CRONIOConnectionUncheckedException {
		return executeCommand(command, null);
	}
	
	@Override
	public CRONIOIExecutionData executeCommand(String command, String readUntilRegEx) throws CRONIOConnectionUncheckedException {
		CRONIOIExecutionData executionData = null;
		try {
			connectionWrapper.sendCommand(command);
			if (AEMFTCommonUtilsBase.isEmptyString(readUntilRegEx)) {
				readUntilRegEx = promptRegEx;
			}
			String fullResponse	= connectionWrapper.readResponseUntil(readUntilRegEx);
			//BEGIN EXAMPLE
			//		String fullResponse		= "Response with data " + promptRegEx;
			//END EXAMPLE
			if (!AEMFTCommonUtilsBase.isEmptyString(fullResponse)) {
				String prompt 	= getLastPrompt(fullResponse);
				String response	= fullResponse.replace(prompt, "");
				executionData	= new CRONIOExecutionData(command, prompt, response);
				if (getLogger() != null) {
					getLogger().log(getConnectionId(), getNode(), executionData);
				}
			} else {
				throw new CRONIOConnectionUncheckedException("No response received from command: " + command);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return executionData;
	}

	@Override
	public String readUntil(String regExp) throws CRONIOConnectionUncheckedException {
		String read = null;
		try {
			read = connectionWrapper.readResponseUntil(regExp);
		} catch (IOException e) {
			throw new CRONIOConnectionUncheckedException(e);
		}
		return read;
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

	private String getLastPrompt(String fullResponse) {
		String prompt = "";
		Matcher	matcher = patternPrompt.matcher(fullResponse);
		if (matcher.find()) {
			prompt = matcher.group();
		}
		return prompt;
	}

}
