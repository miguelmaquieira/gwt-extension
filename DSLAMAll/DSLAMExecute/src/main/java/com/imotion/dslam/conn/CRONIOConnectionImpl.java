package com.imotion.dslam.conn;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Level;

import com.imotion.antlr.ImoLangParser.ProgramContext;
import com.imotion.dslam.antlr.CRONIOAntlrUtils;
import com.imotion.dslam.antlr.CRONIOInterpreterVisitorImpl;
import com.imotion.dslam.bom.CRONIOBOIFile;
import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.conn.wrapper.CRONIOConnectionIWrapper;
import com.imotion.dslam.conn.wrapper.CRONIOConnectionWrapperDummy;
import com.imotion.dslam.conn.wrapper.CRONIOConnectionWrapperSSH1;
import com.imotion.dslam.conn.wrapper.CRONIOConnectionWrapperSSH2;
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

	public CRONIOConnectionImpl(long processId, long executionId, CRONIOBOINode node, CRONIOIExecutionLogger	logger) {
		this.machineProperties 	= node.getMachineProperties();
		this.connectionId		= generateConnectionId(processId, executionId, node.getNodeId());
		this.node 				= node;
		this.logger				= logger;
		this.promptRegEx 		= machineProperties.getPromptRegEx();
		this.patternPrompt 		= Pattern.compile(promptRegEx);
		this.protocolType 		= machineProperties.getProtocolType();
		this.variablesMap		= CRONIOAntlrUtils.getMachineVariables(machineProperties);
		if (CRONIOBOIMachineProperties.PROTOCOL_TYPE_SSH_1 == protocolType) {
			connectionWrapper = new CRONIOConnectionWrapperSSH1();
		} else if (CRONIOBOIMachineProperties.PROTOCOL_TYPE_SSH_2 == protocolType) {
			connectionWrapper = new CRONIOConnectionWrapperSSH2();
		} else if (CRONIOBOIMachineProperties.PROTOCOL_TYPE_TELNET == protocolType) {
			connectionWrapper = new CRONIOConnectionWrapperTelnet();
		} else {
			connectionWrapper = new CRONIOConnectionWrapperDummy();
		}
	}

	@Override
	public void openConnection() throws IOException {
		connectionWrapper.connect(node);
		CRONIOBOIFile 	connectionScript		= machineProperties.getInitConnectionScript();
		String 			connectionScriptContent	= connectionScript.getCompiledContent();
		runScript(connectionScriptContent);
	}

	@Override
	public void closeConnection() {
		CRONIOBOIFile 	closeConnectionScript			= machineProperties.getCloseConnectionScript();
		String 			closeConnectionScriptContent	= closeConnectionScript.getCompiledContent();
		runScript(closeConnectionScriptContent);
		connectionWrapper.disconnect();
	}

	@Override
	public CRONIOIExecutionData executeCommand(String command) throws CRONIOConnectionUncheckedException {
		CRONIOIExecutionData executionData = null;
		try {
			connectionWrapper.sendCommand(command);
			String fullResponse	= connectionWrapper.readResponseUntil(promptRegEx);
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
		} catch (CRONIOConnectionUncheckedException e) {
			e.printStackTrace();
		}
		return executionData;
	}

	@Override
	public CRONIOIExecutionData executeCommandWithoutRead(String command) throws CRONIOConnectionUncheckedException {
		CRONIOIExecutionData executionData	= null;
		try {
			connectionWrapper.sendNoResponseCommand(command);
			executionData	= new CRONIOExecutionData(command, "", "");
			if (getLogger() != null) {
				getLogger().log(getConnectionId(), getNode(), executionData);
			}
		} catch (CRONIOConnectionUncheckedException e) {
			throw new CRONIOConnectionUncheckedException("Command not sent: " + command);
		}
		return executionData;
	}

	@Override
	public String readUntil(String regExp) throws CRONIOConnectionUncheckedException {
		String read = connectionWrapper.readResponseUntil(regExp);
		return read;
	}

	@Override
	public CRONIOIExecutionData logMessage(Level logLevel, String message) {
		CRONIOIExecutionData executionData	= null;
		executionData = new CRONIOExecutionData(message, "", "");
		if (getLogger() != null) {
			getLogger().log(getConnectionId(), getNode(), executionData, logLevel);
		} 
		return executionData;
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

	private String generateConnectionId(long processId, long executionId, long nodeId) {
		StringBuilder connectionIdSB = new StringBuilder();
		connectionIdSB.append(executionId);
		connectionIdSB.append(CONNECTION_ID_SEP);
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
