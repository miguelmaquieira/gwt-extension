package com.imotion.dslam.business.service;

import com.selene.arch.base.exe.bus.AEMFTIBusinessConstant;


public interface DSLAMBUIProjectBusinessServiceConstants {
	
	//METHODS
	String 	METHOD_ADD_PROJECT			= "ADD_PROJECT";
	String 	METHOD_GET_ALL_PROJECTS		= "GET_ALL_PROJECTS";
	String 	METHOD_UPDATE_PROJECT		= "UPDATE_PROJECT";
	String 	METHOD_REMOVE_PROJECT		= "REMOVE_PROJECT";
	
	//CONTEXT
	String 	PROJECT_DATA 					= "PROJECT_DATA";
	String 	PROJECTS_DATA 					= "PROJECTS_DATA";
	String 	PROJECT_DATA_LIST 				= "PROJECT_DATA_LIST";
	String 	PROJECT_DATA_LIST_PREFFIX 		= PROJECT_DATA_LIST	+	AEMFTIBusinessConstant.CTE_MFT_AE_BUS_SERVICE_ID_SEPARATOR;
	String 	PROJECTS_DATA_PREFFIX 			= PROJECTS_DATA	+	AEMFTIBusinessConstant.CTE_MFT_AE_BUS_SERVICE_ID_SEPARATOR;

}
