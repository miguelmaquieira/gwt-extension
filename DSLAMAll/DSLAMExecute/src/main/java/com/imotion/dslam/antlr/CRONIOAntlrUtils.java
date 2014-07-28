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
	private static final String COMMAND_START 			= ">";
	private static final String NEW_LINE		 			= "\n";
	private static final String CONCAT_OPERATOR 			= " . ";
	private static final String VARIABLE_REGEX			= "^(\\$|#|@)[A-Za-z][A-Za-z0-9_]*";
	private static final String INSTRUCTION_END			= ";";

	//ERRORS
	private static final String END_COMMAND_ERROR_CODE 		= "1";
	private static final String END_COMMAND_ERROR_MSG 		= "Line must end with: " + INSTRUCTION_END;
	private static final String COMMAND_NOT_FOUND_CODE	 	= "2";
	private static final String COMMAND_NOT_FOUND_ERROR_MSG 	= "Command not found: ";

	public static String precompileCode(String originalCode, int languageType) throws CRONIOAntlrCompilationException {
		StringBuilder compiledCodeSb = new StringBuilder();
		if (!AEMFTCommonUtilsBase.isEmptyString(originalCode)) {
			Map<String, String> errors 			= new HashMap<>();
			List<String>		originalLines	= AEMFTCommonUtilsBase.splitByToken(originalCode, NEW_LINE);
			for (String line : originalLines) {
				StringBuilder compiledLineSB = new StringBuilder();
				if (line.contains(COMMAND_START)) {
					line = line.trim();
					if (!line.endsWith(INSTRUCTION_END)) {
						errors.put(END_COMMAND_ERROR_CODE, END_COMMAND_ERROR_MSG);
					}
					if (line.contains(" ")) {
						List<String> lineItems = AEMFTCommonUtilsBase.splitByToken(line	, INSTRUCTION_END);
						lineItems = AEMFTCommonUtilsBase.splitByToken(lineItems.get(0)	, " ");
						for (int i = 0; i < lineItems.size(); i++) {
							String lineItem = lineItems.get(i);
							if (i == 0) {
								compiledLineSB.append(lineItem);
							} else {
								lineItem = lineItem.trim();
								if (i > 1) {
									compiledLineSB.append(CONCAT_OPERATOR);
								}
								if (lineItem.matches(VARIABLE_REGEX)) {
									compiledLineSB.append(lineItem);
								} else {
									if (lineItem.contains("\"")) {
										compiledLineSB.append(lineItem);
									} else {
										boolean validCommand = checkValidCommand(lineItem, languageType);
										if (validCommand) {
											compiledLineSB.append("\"");
											compiledLineSB.append(lineItem);
											compiledLineSB.append(" \"");
										} else {
											errors.put(COMMAND_NOT_FOUND_CODE, COMMAND_NOT_FOUND_ERROR_MSG + lineItem);
										}
									}
								}
							}
						}
						compiledLineSB.append(INSTRUCTION_END);
					} else {
						compiledLineSB.append(line);
					}
				} else {
					compiledLineSB.append(line);
				}

				if (errors.size() > 0) {
					throw new CRONIOAntlrCompilationException(errors);
				}

				String compiledLine = compiledLineSB.toString();
				compiledCodeSb.append(compiledLine);
				compiledCodeSb.append(NEW_LINE);
			}
		}
		String compiledCode = compiledCodeSb.toString();
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

	private static boolean checkValidCommand(String lineItem, int languageType) {
		return true;
	}

}
