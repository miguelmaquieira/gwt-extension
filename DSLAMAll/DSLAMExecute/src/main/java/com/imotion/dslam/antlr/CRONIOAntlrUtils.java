package com.imotion.dslam.antlr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import com.imotion.antlr.ImoLangLexer;
import com.imotion.antlr.ImoLangParser;
import com.imotion.antlr.ImoLangParser.ProgramContext;
import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.bom.DSLAMBOIVariable;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;

public class CRONIOAntlrUtils {
	
	private static final String VARIABLE_PREFFIX_PROCESS 	= "#";
	
	public static String precompileCode(String originalCode) {
		String compiledCode = "";
		
		return compiledCode;
	}
	
	public static  ProgramContext getTreeFromCode(String code) {
		ImoLangLexer		lexer	= new ImoLangLexer(new ANTLRInputStream(code));
		CommonTokenStream	tokens	= new CommonTokenStream(lexer);
		ImoLangParser 		parser	= new ImoLangParser(tokens);
		ProgramContext 		tree 	= parser.program();
		return tree;
	}
	
	public static Map<String, Object> getNodeVariables(CRONIOBOINode node) {
		List<DSLAMBOIVariable> 	nodeVariables 		= node.getVariableList();
		return getVariablesMapFromVariablesBomList(nodeVariables);
	}
	
	public static Map<String, Object> getMachineVariables(CRONIOBOIMachineProperties machineProperties) {
		List<DSLAMBOIVariable> 	machineVariables = machineProperties.getConnectionVariables();
		return getVariablesMapFromVariablesBomList(machineVariables);
	}

	public static Map<String, Object> getVariablesFromProject(DSLAMBOIProject project) {
		DSLAMBOIProcess			process 		= project.getProcess();
		List<DSLAMBOIVariable>	processVariableList 	= process.getVariableList();
		return getVariablesMapFromVariablesBomList(processVariableList);
	}
	
	public static Map<String, Object> getVariablesMapFromVariablesBomList(List<DSLAMBOIVariable>	variableList) {
		Map<String, Object> variablesMap 	= new HashMap<>();
		if (!AEMFTCommonUtilsBase.isEmptyList(variableList)) {
			for (DSLAMBOIVariable variable : variableList) {
				addVariableToMap(variablesMap, variable);
			}
		}
		return variablesMap;
	}
	
	/**
	 * PRIVATE 
	 */

	private static void addVariableToMap(Map<String, Object> variablesMap, DSLAMBOIVariable variable) {
		StringBuilder varNameSB = new StringBuilder();
		varNameSB.append(VARIABLE_PREFFIX_PROCESS);
		varNameSB.append(variable.getVariableName());

		Object variableValue = getVariableValue(variable.getVariableType(), variable.getVariableValue());
		if (variableValue != null) {
			variablesMap.put(varNameSB.toString(), variableValue);
		}
	}

	private static Object getVariableValue(int type, String variableValueStr) {
		Object variableValue = null;
		if (!AEMFTCommonUtilsBase.isEmptyString(variableValueStr)) {
			if (DSLAMBOIVariable.VARIABLE_TYPE_TEXT == type) {
				variableValue = variableValueStr.replaceAll("\"", "");
			} else {
				variableValue = AEMFTCommonUtilsBase.getIntegerFromString(variableValueStr);
			}
		}
		return variableValue;
	}
	
}
