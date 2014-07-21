package com.imotion.dslam.bom;


public interface CRONIOBOINodeDataConstants {
	
	//ATTRIBUTES
	String NODE_ID 				= "nodeId";
	String NODE_NAME			= "nodeName";
	String NODE_IP				= "nodeIp";
	String NODE_VARIABLE_LIST	= "nodeVariableList";	
	String SAVED_TIME			= "savedTime";
	String CREATION_TIME		= "creationTime";
	String NODE_PROCESS			= "process";
	String MACHINE_PROPERTIES	= "machineProperties";
	
	//RELATIONS
	String MACHINE_PROPERTIES_ID = "machinePropertiesId";

	//CONTEXT
	String NODE_DATA			= "nodeData";
	String NODE_VARIABLES_DATA	= "nodeVariablesData";
	String NODE_TYPE			= "nodeType";
	
}