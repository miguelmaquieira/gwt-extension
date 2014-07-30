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
import com.selene.arch.exe.core.common.AEMFTCommonUtils;

public class CRONIOAntlrUtils {

	private static final String COMMAND_START 			= ">";
	private static final String COMMAND_NO_RESPONSE_START	= ">>";
	private static final String READ_START 				= "read";
	private static final String MATCH_START 				= "match";
	private static final String ROLLBACK_START 			= "rb";
	private static final String TAG_START 				= "tag";
	private static final String RB_CASE_START 			= "rbCase";
	private static final String RB_CASE_DEFAULT_START 	= "rbDefault";

	private static final String VARIABLE_REGEX			= "^(\\$|#|@)[A-Za-z][A-Za-z0-9_]*(\\[\\d\\])?";
	private static final String INSTRUCTION_END			= ";";
	private static final String CONCATENATION_OPERATOR	= ".";
	private static final String MATCH_TARGET_OPERATOR		= ">";


	private static final String VARIABLE_PREFFIX_PROCESS 	= "#";
	private static final String NEW_LINE		 			= "\n";

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
				line = processLineSpaces(line);
				String compiledLine = "";
				if (isExecutionInstruction(line)) {
					if (!line.endsWith(INSTRUCTION_END)) {
						errors.put(END_COMMAND_ERROR_CODE, END_COMMAND_ERROR_MSG);
					} else {
						if (isCommandLine(line)) {
							compiledLine = processCommandsLine(line, languageType, errors);
						} else {
							compiledLine = processFunctionLine(line, errors);
						}
					}
				} else {
					compiledLine = line;
				}
				compiledCodeSb.append(compiledLine);
				compiledCodeSb.append(NEW_LINE);
			}
			if (errors.size() > 0) {
				throw new CRONIOAntlrCompilationException(errors);
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

	private static boolean isExecutionInstruction(String line) {
		boolean execution = isCommandLine(line)
				|| line.startsWith(READ_START) 
				|| line.startsWith(MATCH_START) 
				|| (!line.startsWith(RB_CASE_START) && !line.startsWith(RB_CASE_DEFAULT_START) && line.startsWith(ROLLBACK_START))
				|| line.startsWith(TAG_START);
		return execution;
	}
	
	private static boolean isCommandLine(String line) {
		boolean commandLine = line.startsWith(COMMAND_START) 
				|| line.startsWith(COMMAND_NO_RESPONSE_START) ;
		return commandLine;
	}

	private static boolean isValidCommand(String command, int languageType) {
		boolean validCommand = false;
		//TODO: check if command belongs to machine language.
		validCommand = true;
		return validCommand;
	}

	private static String processLineSpaces(String line) {
		line = line.trim();
		String processedLine = line;
		if (line.startsWith(COMMAND_NO_RESPONSE_START)) {
			processedLine = line.replaceFirst(COMMAND_NO_RESPONSE_START, COMMAND_NO_RESPONSE_START + " ");
		} else if (line.startsWith(COMMAND_START) ) {
			processedLine = line.replaceFirst(COMMAND_START, COMMAND_START + " ");
		} else if (line.startsWith(READ_START)  ) {
			processedLine = line.replaceFirst(READ_START, READ_START + " ");
		} else if (line.startsWith(MATCH_START)  ) {
			processedLine = line.replaceFirst(MATCH_START, MATCH_START + " ");
		} else if (line.startsWith(RB_CASE_START)  ) {
			processedLine = line.replaceFirst(RB_CASE_START, RB_CASE_START + " ");
		} else if (line.startsWith(RB_CASE_DEFAULT_START)  ) {
			processedLine = line.replaceFirst(RB_CASE_DEFAULT_START, RB_CASE_DEFAULT_START + " ");
		} else if (line.startsWith(ROLLBACK_START)  ) {
			processedLine = line.replaceFirst(ROLLBACK_START, ROLLBACK_START + " ");
		} else if (line.startsWith(TAG_START) ) {
			processedLine = line.replaceFirst(TAG_START, TAG_START + " ");
		}
		processedLine = processedLine.replaceAll(INSTRUCTION_END, " " + INSTRUCTION_END);
		processedLine = processedLine.replaceAll("\\s{2,}", " ");
		return processedLine;
	}
	
	private static String processCommandsLine(String line, int languageType, Map<String, String> errors) {
		StringBuilder	processedLineSb	= new StringBuilder();
		List<String> lineItems = AEMFTCommonUtils.splitByToken(line, " ");
		if (!AEMFTCommonUtilsBase.isEmptyList(lineItems)) {
			processedLineSb.append(lineItems.get(0));
			boolean		itemStartsString		= false;
			boolean 	stringNotClosed 		= false;
			int			itemsSize				= lineItems.size();
			//from ">" to ";"
			for (int i = 1; i < itemsSize - 1; i++ ) {
				boolean		itemClosesString 		= false;
				boolean		concatenatorOperator	= false;
				String currentItem 		= lineItems.get(i);
				currentItem				= currentItem.trim();
				
				String processedItem	= "";
				if (CONCATENATION_OPERATOR.equals(currentItem)) {
					concatenatorOperator = true;
					processedItem = currentItem;
				} else if (currentItem.length() > 1 && currentItem.startsWith("\"") && currentItem.endsWith("\"")) {
					processedItem = currentItem;
				} else if ( (currentItem.startsWith("\"") && !currentItem.endsWith("\"")) || (!stringNotClosed && currentItem.equals("\"")) ) {
					stringNotClosed		= true;
					itemStartsString	= true;
					processedItem = currentItem;
				} else if ( (currentItem.endsWith("\"") && !currentItem.startsWith("\"")) || (stringNotClosed && currentItem.equals("\"")) ) {
					stringNotClosed 	= false;
					itemStartsString 	= false;
					itemClosesString	= true;
					processedItem		= currentItem;
				} else if (stringNotClosed) {
					processedItem = currentItem;
					itemStartsString = false;
				} else if (currentItem.matches(VARIABLE_REGEX)) {
					processedItem = currentItem;
				} else if (isValidCommand(currentItem, languageType)) {
					processedItem = "\"" + currentItem + "\"";
				} else {
					processedItem = currentItem;
					errors.put(COMMAND_NOT_FOUND_CODE, COMMAND_NOT_FOUND_ERROR_MSG + currentItem);
				}
				
				if ( (i > 1 && !stringNotClosed && !itemClosesString && !concatenatorOperator) || (i > 1 && stringNotClosed && itemStartsString) ) {
					processedLineSb.append(" ");
					processedLineSb.append(CONCATENATION_OPERATOR);
				}
				processedLineSb.append(" ");
				processedLineSb.append(processedItem);
			}
			processedLineSb.append(lineItems.get(itemsSize - 1));
		}
		String processedLine = processedLineSb.toString();
		return processedLine;
	}
	
	private static String processFunctionLine(String line, Map<String, String> errors) {
		List<String> lineItems = AEMFTCommonUtils.splitByToken(line, " ");
		StringBuilder processedLineSb = new StringBuilder();
		if (!AEMFTCommonUtilsBase.isEmptyList(lineItems)) {
			processedLineSb.append(lineItems.get(0));
			int	itemsSize = lineItems.size();
			//from "function" to ";"
			for (int i = 1; i < itemsSize - 1; i++ ) {
				String currentItem 	= lineItems.get(i);
				currentItem			= currentItem.trim();
				String processedItem = "";
				if (currentItem.matches(VARIABLE_REGEX)) {
					processedItem = currentItem;
				} else {
					if (currentItem.startsWith("\"")) {
						processedItem = currentItem;
					} else if (currentItem.equals(CONCATENATION_OPERATOR) || currentItem.equals(MATCH_TARGET_OPERATOR)) {
						processedItem = currentItem;
					} else {
						processedItem = "\"" + currentItem + "\"";
					}
				}
				processedLineSb.append(" ");
				processedLineSb.append(processedItem);
			}
			processedLineSb.append(lineItems.get(itemsSize - 1));
		}
		String processedLine = processedLineSb.toString();
		return processedLine;
	}

}
