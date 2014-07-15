package com.imotion.dslam.antlr.executor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import com.imotion.antlr.ImoLangLexer;
import com.imotion.antlr.ImoLangParser;
import com.imotion.antlr.ImoLangParser.ProgramContext;
import com.imotion.dslam.antlr.CRONIOInterpreterVisitorImpl;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.bom.DSLAMBOIVariable;
import com.imotion.dslam.conn.CRONIOConnectionFactory;
import com.imotion.dslam.conn.CRONIOIConnection;
import com.imotion.dslam.logger.CRONIOExecutionLoggerImpl;
import com.imotion.dslam.logger.CRONIOIExecutionLogger;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;

public class CRONIOExecutorImpl implements CRONIOIExecutor {
	
	private CRONIOIExecutionLogger 		logger;
	private DSLAMBOIProject				project;
	private HashMap<Long, Thread> 		threads;

	public CRONIOExecutorImpl(DSLAMBOIProject project) throws Exception {
		this.project			= project;
		this.logger				= new CRONIOExecutionLoggerImpl(project);
	}

	@Override
	public void execute() {
		String 				scriptCode	= project.getMainScript().getContent();
		Map<String, Object> variables 	= getVariablesFromProject(project);
		
		DSLAMBOIProcess		process		= project.getProcess();
		List<CRONIOBOINode> nodeList	= process.getNodeList();
		boolean				sync		= process.isSynchronous();
		long				processId	= process.getProcessId();
		
		executeInNodes(processId, scriptCode, variables, nodeList, sync);
	}
	
	/**
	 * PRIVATE
	 */
	
	private void executeInNode(long processId, CRONIOBOINode node, String scriptCode, Map<String, Object> allVariables) {
		ImoLangLexer		lexer	= new ImoLangLexer(new ANTLRInputStream(scriptCode));
		CommonTokenStream	tokens	= new CommonTokenStream(lexer);
		
		ImoLangParser 	parser 	= new ImoLangParser(tokens);
		ProgramContext 	tree 	= parser.program();
		
		CRONIOIConnection 			connection 	= CRONIOConnectionFactory.getConnection(processId, node, getLogger());
		CRONIOInterpreterVisitorImpl visitor 	= new CRONIOInterpreterVisitorImpl(connection, allVariables);
		visitor.visit(tree);
		CRONIOConnectionFactory.releaseConnection(connection);
	}
	
	private void executeInNodes(long processId, String scriptCode, Map<String, Object> processVariables, List<CRONIOBOINode> nodeList, boolean sync) {
		threads = new HashMap<>();
		for (CRONIOBOINode node : nodeList) {
			Map<String, Object> nodeVariables = getNodeVariables(node);
			Map<String, Object> allVariables = new HashMap<>();
			allVariables.putAll(nodeVariables);
			allVariables.putAll(processVariables);
			if (sync) {
				executeInNode(processId, node, scriptCode, allVariables);
			} else {
				Thread thread = executeInNodeAsync(processId, node, scriptCode, allVariables);
				threads.put(node.getNodeId(), thread);
			}
		}
	}

	private Thread executeInNodeAsync(final long processId, final CRONIOBOINode node, final String scriptCode, final Map<String, Object> allVariables) {
		 Runnable task = new Runnable() {
			
			@Override
			public void run() {
				executeInNode(processId, node, scriptCode, allVariables);
				checkEnd(node.getNodeId());
			}
		};
		
		Thread thread = new Thread(task);
		thread.start();
		
		return thread;
	}

	private synchronized void checkEnd(long nodeId) {
		threads.remove(nodeId);
		if (threads.isEmpty()) {
			getLogger().flush();
		}
	}

	private Map<String, Object> getNodeVariables(CRONIOBOINode node) {
		Map<String, Object> 	nodeVariablesMap 	= new HashMap<>();
		List<DSLAMBOIVariable> 	nodeVariables 		= node.getVariableList();
		if (!AEMFTCommonUtilsBase.isEmptyList(nodeVariables)) {
			for (DSLAMBOIVariable variable : nodeVariables) {
				addVariableToMap(nodeVariablesMap, variable);
			}
		}
		return nodeVariablesMap;
	}

	private Map<String, Object> getVariablesFromProject(DSLAMBOIProject project) {
		Map<String, Object> variablesMap 	= new HashMap<>();
		DSLAMBOIProcess			process 		= project.getProcess();
		List<DSLAMBOIVariable>	processVariableList 	= process.getVariableList();
		if (!AEMFTCommonUtilsBase.isEmptyList(processVariableList)) {
			for (DSLAMBOIVariable variable : processVariableList) {
				addVariableToMap(variablesMap, variable);
			}
		}
		return variablesMap;
	}
	
	private void addVariableToMap(Map<String, Object> variablesMap, DSLAMBOIVariable variable) {
		StringBuilder varNameSB = new StringBuilder();
		varNameSB.append(VARIABLE_PREFFIX_PROCESS);
		varNameSB.append(variable.getVariableName());
		
		Object variableValue = getVariableValue(variable.getVariableValue());
		if (variableValue != null) {
			variablesMap.put(varNameSB.toString(), variableValue);
		}
	}

	private Object getVariableValue(String variableValueStr) {
		Object variableValue = null;
		if (!AEMFTCommonUtilsBase.isEmptyString(variableValueStr)) {
			if (variableValueStr.contains("\"")) {
				variableValue = variableValueStr.replaceAll("\"", "");
			} else {
				variableValue = AEMFTCommonUtilsBase.getIntegerFromString(variableValueStr);
			}
		}
		return variableValue;
	}
	
	private CRONIOIExecutionLogger getLogger() {
		return logger;
	}

}
