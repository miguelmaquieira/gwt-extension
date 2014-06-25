package com.imotion.dslam.business.service.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.imotion.dslam.bom.DSLAMBOIVariable;
import com.imotion.dslam.bom.DSLAMBOIVariablesDataConstants;
import com.imotion.dslam.bom.data.CRONIOBONode;
import com.imotion.dslam.bom.data.DSLAMBOVariable;

public class CRONIOBUCSVToBomConversor {

	/**
	 * NODE
	 */

	public void convertCsvToNode(String src, String splitBy) {  
		String csvFileToRead = src;  
		BufferedReader br = null;  
		String line = "";  
		String splitByToken = splitBy;
		String splitBySpace = " ";
		List<CRONIOBONode> nodeList = new ArrayList<>();  

		try {  

			br = new BufferedReader(new FileReader(csvFileToRead));  
			while ((line = br.readLine()) != null) {  

				String[] nodes = line.split(splitByToken);  

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

		} catch (FileNotFoundException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		} finally {  
			if (br != null) {  
				try {  
					br.close();  
				} catch (IOException e) {  
					e.printStackTrace();  
				}  
			}  
		}  
	}
}
