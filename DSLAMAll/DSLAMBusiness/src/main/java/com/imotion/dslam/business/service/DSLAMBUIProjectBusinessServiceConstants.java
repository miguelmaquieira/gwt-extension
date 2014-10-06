package com.imotion.dslam.business.service;

import com.selene.arch.base.exe.bus.AEMFTIBusinessConstant;


public interface DSLAMBUIProjectBusinessServiceConstants {
	
	//METHODS
	String 	METHOD_ADD_PROJECT					= "ADD_PROJECT";
	String 	METHOD_GET_ALL_PROJECTS_BY_USER		= "GET_ALL_PROJECTS_BY_USER";
	String 	METHOD_UPDATE_PROJECT				= "UPDATE_PROJECT";
	String 	METHOD_UPDATE_PROJECTS				= "UPDATE_PROJECTS";
	String 	METHOD_REMOVE_PROJECT				= "REMOVE_PROJECT";
	
	//CONTEXT
	String 	PROJECT_DATA 				= "PROJECT_DATA";
	String 	PROJECTS_DATA 				= "PROJECTS_DATA";
	String 	PROJECT_DATA_LIST 			= "PROJECT_DATA_LIST";
	String 	PROJECT_DATA_LIST_PREFFIX 	= PROJECT_DATA_LIST	+	AEMFTIBusinessConstant.CTE_MFT_AE_BUS_SERVICE_ID_SEPARATOR;
	String 	PROJECTS_DATA_PREFFIX 		= PROJECTS_DATA		+	AEMFTIBusinessConstant.CTE_MFT_AE_BUS_SERVICE_ID_SEPARATOR;
	//METHODS
	String 	METHOD_ADD_PROCESS						= "ADD_PROCESS";
	String 	METHOD_GET_ALL_PROCESSES				= "GET_ALL_PROCESSES";
	String 	METHOD_UPDATE_PROCESS					= "UPDATE_PROCESS";
	String 	METHOD_REMOVE_PROCESS					= "REMOVE_PROCESS";
	String 	METHOD_GET_CSV_NODES					= "GET_CSV_NODES";
	String	METHOD_GET_ALL_NODELISTS_BY_PROJECT_ID	= "GET_ALL_NODELISTS_BY_PROJECT_ID";
	//CONTEXT
	String 	PROCESS_DATA 				= "PROCESS_DATA";
	String 	PROCESS_DATA_LIST 			= "PROCESS_DATA_LIST";
	String 	PROCESS_FILE_DATA_LIST 		= "PROCESS_FILE_DATA_LIST";
	String 	NODES_DATA_LIST 			= "NODES_DATA_LIST";
	String 	NODELIST_DATA 				= "NODELIST_DATA";
	String 	LIST_NODELIST_DATA 			= "LIST_NODELIST_DATA";
	String 	KEY_EXIST_ERROR 			= "ERROR_EXIST";
	//METHODS
	String 	METHOD_ADD_NODELIST			= "ADD_NODELIST";

}
