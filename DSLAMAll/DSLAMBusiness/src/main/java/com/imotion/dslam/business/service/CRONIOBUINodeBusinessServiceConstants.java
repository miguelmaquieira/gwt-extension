package com.imotion.dslam.business.service;

import com.selene.arch.base.exe.bus.AEMFTIBusinessConstant;


public interface CRONIOBUINodeBusinessServiceConstants {
	
	//METHODS
	String 	METHOD_ADD_NODE				= "ADD_NODE";
	String 	METHOD_GET_ALL_NODES		= "GET_ALL_NODES";
	String 	METHOD_UPDATE_NODE			= "UPDATE_NODE";
	String 	METHOD_REMOVE_NODE			= "REMOVE_NODE";
	
	//CONTEXT
	String 	NODE_DATA 					= "NODE_DATA";
	String 	NODES_DATA 					= "NODES_DATA";
	String 	NODE_DATA_LIST 				= "NODE_DATA_LIST";
	String 	NODE_DATA_LIST_PREFFIX 		= NODE_DATA_LIST	+	AEMFTIBusinessConstant.CTE_MFT_AE_BUS_SERVICE_ID_SEPARATOR;
	String 	NODES_DATA_PREFFIX 			= NODES_DATA		+	AEMFTIBusinessConstant.CTE_MFT_AE_BUS_SERVICE_ID_SEPARATOR;

}
