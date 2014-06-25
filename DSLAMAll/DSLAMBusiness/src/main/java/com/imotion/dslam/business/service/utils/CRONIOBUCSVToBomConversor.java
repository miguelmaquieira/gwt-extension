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
		String splitNewLine = "\n";
		List<CRONIOBOINode> nodeList = new ArrayList<>();  

		String[] lines = content.split(splitNewLine);
		
		for (int line = 0; line < lines.length; line++) {  

			String[] nodes = lines[line].split(splitByToken);  

			CRONIOBONode node = new CRONIOBONode();  

			int 					nodeTypeInt 		= Integer.parseInt(nodes[1]);
			String[]				variableListString	= nodes[3].split(splitBySpace);
			List<DSLAMBOIVariable>	variableList 		= new ArrayList<>();


			for (int i = 0; i < variableListString.length; i++) {
				DSLAMBOIVariable variable = new DSLAMBOVariable();
				variable.setVariableName(variableListString[i]);
				variable.setVariableType(DSLAMBOIVariablesDataConstants.VARIABLE_NODE_TYPE);
				variable.setVariableValue(variableListString[i]);
				variableList.add(variable);
			}

			node.setNodeName(nodes[0]);
			node.setNodeType(nodeTypeInt); 
			node.setNodeIp(nodes[2]);  
			node.setVariableList(variableList);  

			nodeList.add(node);  
		}
		return nodeList;
	}
}
