package com.imotion.dslam.conn;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.imotion.antlr.ImoLangParser.ProgramContext;
import com.imotion.dslam.antlr.CRONIOAntlrUtils;
import com.imotion.dslam.antlr.CRONIOInterpreterVisitorImpl;
import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.conn.wrapper.CRONIOConnectionIWrapper;
import com.imotion.dslam.conn.wrapper.CRONIOConnectionWrapperDummy;
import com.imotion.dslam.conn.wrapper.CRONIOConnectionWrapperSSH;
import com.imotion.dslam.conn.wrapper.CRONIOConnectionWrapperTelnet;
import com.imotion.dslam.logger.CRONIOIExecutionLogger;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;

public class CRONIOConnectionImpl implements CRONIOIConnection {

	private String						connectionId;
	private CRONIOBOINode				node;
	private CRONIOBOIMachineProperties machineProperties;
	private CRONIOIExecutionLogger		logger;
	private CRONIOConnectionIWrapper	connectionWrapper;
	private Pattern 					patternPrompt;
	private String						promptRegEx;
	private int 						protocolType;
	private Map<String, Object> 		variablesMap;

	public CRONIOConnectionImpl(long processId, CRONIOBOINode node, CRONIOIExecutionLogger	logger) {
		this.machineProperties 	= node.getMachineProperties();
		this.connectionId		= generateConnectionId(processId, node.getNodeId());
		this.node 				= node;
		this.logger				= logger;
		this.promptRegEx 		= machineProperties.getPromptRegEx();
		this.patternPrompt 		= Pattern.compile(promptRegEx);
		this.protocolType 		= machineProperties.getProtocolType();
		this.variablesMap		= CRONIOAntlrUtils.getMachineVariables(machineProperties);
		if (CRONIOBOIMachineProperties.PROTOCOL_TYPE_SSH == protocolType) {
			connectionWrapper = new CRONIOConnectionWrapperSSH();
		} else if (CRONIOBOIMachineProperties.PROTOCOL_TYPE_TELNET == protocolType) {
			connectionWrapper = new CRONIOConnectionWrapperTelnet();
		} else {
			connectionWrapper = new CRONIOConnectionWrapperDummy();
		}
	}

	@Override
	public void openConnection() throws IOException {
		connectionWrapper.connect(node);
		DSLAMBOIFile 	connectionScript		= machineProperties.getInitConnectionScript();
		String 			connectionScriptContent	= connectionScript.getContent();
		runScript(connectionScriptContent);
	}
	
	@Override
	public void closeConnection() {
		DSLAMBOIFile 	closeConnectionScript			= machineProperties.getCloseConnectionScript();
		String 			closeConnectionScriptContent	= closeConnectionScript.getContent();
		runScript(closeConnectionScriptContent);
		connectionWrapper.disconnect();
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
	
	private void runScript(String content) {
		if (!AEMFTCommonUtilsBase.isEmptyString(content)) {
			ProgramContext 					tree	= CRONIOAntlrUtils.getTreeFromCode(content);
			CRONIOInterpreterVisitorImpl 	visitor	= new CRONIOInterpreterVisitorImpl(this, variablesMap, null, null, null);
			visitor.visit(tree);
		}
	}

}
