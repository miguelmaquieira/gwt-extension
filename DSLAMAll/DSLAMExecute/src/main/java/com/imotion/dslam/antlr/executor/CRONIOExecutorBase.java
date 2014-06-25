package com.imotion.dslam.antlr.executor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import com.imotion.antlr.DSLAMLexer;
import com.imotion.antlr.DSLAMParser;
import com.imotion.antlr.DSLAMParser.ProgramContext;
import com.imotion.dslam.antlr.DSLAMInterpreterVisitorImpl;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.bom.DSLAMBOIVariable;
import com.imotion.dslam.comm.DSLAMConnectionImpl;
import com.imotion.dslam.comm.DSLAMIConnection;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;


public class CRONIOExecutorBase implements CRONIOIExecutor {

	@Override
	public void execute(DSLAMBOIProject project, String executionId) {
		String 				scriptCode		= project.getMainScript().getContent();
		Map<String, Object> variables 	= getVariablesFromProject(project);
		
		DSLAMBOIProcess		process		= project.getProcess();
		List<CRONIOBOINode> nodeList	= process.getNodeList();
		boolean				sync		= process.isSynchronous();
		
		executeInNodes(scriptCode, variables, nodeList, sync);
	}
	
	/**
	 * PROTECTED 
	 */

	protected void executeInNode(String scriptCode, Map<String, Object> variables) {
		DSLAMLexer			lexer	= new DSLAMLexer(new ANTLRInputStream(scriptCode));
		CommonTokenStream	tokens	= new CommonTokenStream(lexer);
		
		DSLAMParser parser = new DSLAMParser(tokens);
		ProgramContext tree = parser.program();
		
		DSLAMIConnection connection = new DSLAMConnectionImpl();
		
		
		DSLAMInterpreterVisitorImpl visitor = new DSLAMInterpreterVisitorImpl(connection, variables);
		visitor.visit(tree);
	}
	
	/**
	 * PRIVATE
	 */
	private void executeInNodes(String scriptCode, Map<String, Object> variables, List<CRONIOBOINode> nodeList, boolean sync) {
		Map<String, Object> allVariables = new HashMap<>();
		for (CRONIOBOINode node : nodeList) {
			Map<String, Object> nodeVariables = getNodeVariables(node);
			allVariables.putAll(nodeVariables);
		}
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
