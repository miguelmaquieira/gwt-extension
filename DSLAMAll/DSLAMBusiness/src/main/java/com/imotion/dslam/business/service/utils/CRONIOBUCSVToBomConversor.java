package com.imotion.dslam.business.service.utils;

import java.util.ArrayList;
import java.util.List;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.DSLAMBOIVariable;
import com.imotion.dslam.bom.DSLAMBOIVariablesDataConstants;
import com.imotion.dslam.bom.data.CRONIOBONode;
import com.imotion.dslam.bom.data.DSLAMBOVariable;

public class CRONIOBUCSVToBomConversor {

	/**
	 * NODE
	 */

	public static List<CRONIOBOINode> convertCsvToNode(String content, String splitBy) {     
		String splitByToken = splitBy;
		String splitBySpace = " ";
		String splitNewLine = "\\r?\\n";
		String[]			variableNameList	= null;
		List<CRONIOBOINode> nodeList = new ArrayList<>();  

		String[] lines = content.split(splitNewLine);

		for (int line = 0; line < lines.length; line++) {  

			String[] nodes = lines[line].split(splitByToken);  

			String[]				variableListString	= nodes[3].split(splitBySpace);
			List<DSLAMBOIVariable>	variableList 		= new ArrayList<>();

			if (line == 0) {
				variableNameList = variableListString;
			} else {
				CRONIOBONode node = new CRONIOBONode();  
				node.setNodeName(nodes[0]);
				node.setNodeType(nodes[1]);
				node.setNodeIp(nodes[2]); 

				for (int i = 0; i < variableListString.length; i++) {
					DSLAMBOIVariable variable = new DSLAMBOVariable();
					variable.setVariableName(variableNameList[i]);
					variable.setVariableScope(DSLAMBOIVariablesDataConstants.VARIABLE_SCOPE_NODE);
					variable.setVariableType(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE_TEXT);
					variable.setVariableValue(variableListString[i]);
					variableList.add(variable);
				}

				node.setVariableList(variableList);  

				nodeList.add(node);  
			}
		}
		return nodeList;
	}
}
