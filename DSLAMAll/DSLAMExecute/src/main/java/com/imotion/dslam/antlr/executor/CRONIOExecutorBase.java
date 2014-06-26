package com.imotion.dslam.antlr.executor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.bom.DSLAMBOIVariable;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;


public abstract class CRONIOExecutorBase implements CRONIOIExecutor {

	@Override
	public void execute(DSLAMBOIProject project) {
		String 				scriptCode		= project.getMainScript().getContent();
		Map<String, Object> variables 	= getVariablesFromProject(project);
		
		DSLAMBOIProcess		process		= project.getProcess();
		List<CRONIOBOINode> nodeList	= process.getNodeList();
		boolean				sync		= process.isSynchronous();
		long				processId	= process.getProcessId();
		
		executeInNodes(processId, scriptCode, variables, nodeList, sync);
	}
	
	/**
	 * PROTECTED 
	 */

	protected abstract void executeInNode(long processId, CRONIOBOINode node, String scriptCode, Map<String, Object> variables);
	
	/**
	 * PRIVATE
	 */
	private void executeInNodes(long processId, String scriptCode, Map<String, Object> processVariables, List<CRONIOBOINode> nodeList, boolean sync) {
		Map<Long, Thread> threads = new HashMap<>();
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
				
			}
		};
		
		Thread thread = new Thread(task);
		thread.start();
		
		return thread;
	}

	private Map<String, Object> getNodeVariables(CRONIOBOINode node) {
		Map<String, Object> 	nodeVariablesMap 	= new HashMap<>();
		List<DSLAMBOIVariable> 	nodeVariables 		= node.getVariableList();
		if (AEMFTCommonUtilsBase.isEmptyList(nodeVariables)) {
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

}
