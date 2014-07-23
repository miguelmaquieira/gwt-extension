package com.imotion.dslam.antlr.executor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.imotion.antlr.ImoLangParser.ProgramContext;
import com.imotion.dslam.antlr.CRONIOAntlrUtils;
import com.imotion.dslam.antlr.CRONIOInterpreterVisitorImpl;
import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIProject;
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
		String 				scriptCode			= project.getMainScript().getContent();
		String				rollbackScriptCode	= project.getRollBackScript().getContent();
		Map<String, Object> variables 	= CRONIOAntlrUtils.getVariablesFromProject(project);

		DSLAMBOIProcess		process		= project.getProcess();
		List<CRONIOBOINode> nodeList	= process.getNodeList();
		boolean				sync		= process.isSynchronous();
		long				processId	= process.getProcessId();

		executeInNodes(processId, scriptCode, rollbackScriptCode, variables, nodeList, sync);
	}

	/**
	 * PRIVATE
	 */

	private void executeInNode(long processId, CRONIOBOINode node, String scriptCode, String rollbackScriptCode, Map<String, Object> allVariables) {
		//RollbackScript
		ProgramContext 	rollbackTree 				= null;
		String			defaultRollbackCondition 	= null;
		if (!AEMFTCommonUtilsBase.isEmptyString(rollbackScriptCode)) {
			CRONIOBOIMachineProperties machineProperties = node.getMachineProperties();
			defaultRollbackCondition	= machineProperties.getRollbackConditionRegEx();
			rollbackTree				= CRONIOAntlrUtils.getTreeFromCode(rollbackScriptCode);
		}

		//MainScript
		ProgramContext mainTree		= CRONIOAntlrUtils.getTreeFromCode(scriptCode);

		//Open Connection
		CRONIOIConnection connection = CRONIOConnectionFactory.getConnection(processId, node, getLogger());

		//RollbackVisitor
		CRONIOInterpreterVisitorImpl 	rollbackVisitor	= new CRONIOInterpreterVisitorImpl(connection, allVariables, null, null, null);

		//MainVisitor
		CRONIOInterpreterVisitorImpl 	mainVisitor		= new CRONIOInterpreterVisitorImpl(connection, allVariables, defaultRollbackCondition, rollbackVisitor, rollbackTree);
		mainVisitor.visit(mainTree);

		//Close connection
		CRONIOConnectionFactory.releaseConnection(connection);
	}

	private void executeInNodes(long processId, String scriptCode, String rollbackScriptCode, Map<String, Object> processVariables, List<CRONIOBOINode> nodeList, boolean sync) {
		threads = new HashMap<>();
		for (CRONIOBOINode node : nodeList) {
			Map<String, Object> nodeVariables = CRONIOAntlrUtils.getNodeVariables(node);
			Map<String, Object> allVariables = new HashMap<>();
			allVariables.putAll(nodeVariables);
			allVariables.putAll(processVariables);
			if (sync) {
				executeInNode(processId, node, scriptCode, rollbackScriptCode, allVariables);
			} else {
				Thread thread = executeInNodeAsync(processId, node, scriptCode, rollbackScriptCode, allVariables);
				threads.put(node.getNodeId(), thread);
			}
		}
	}

	private Thread executeInNodeAsync(final long processId, final CRONIOBOINode node, final String scriptCode, final String rollbackScriptCode, final Map<String, Object> allVariables) {
		Runnable task = new Runnable() {

			@Override
			public void run() {
				executeInNode(processId, node, scriptCode, rollbackScriptCode, allVariables);
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

	private CRONIOIExecutionLogger getLogger() {
		return logger;
	}

}
