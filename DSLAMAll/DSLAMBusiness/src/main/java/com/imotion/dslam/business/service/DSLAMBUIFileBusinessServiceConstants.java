package com.imotion.dslam.business.service;

import com.imotion.dslam.business.DSLAMBUIConstant;
import com.imotion.dslam.business.DSLAMBUIServiceConstant;
import com.selene.arch.base.MFTIConstant;

public interface DSLAMBUIFileBusinessServiceConstants {
	
	// BusinessFILEService types
	long 	CTE_BUSINESS_FILE_SERVICE_TYPE 		= DSLAMBUIServiceConstant.CTE_DSLAM_APP_BUS_FILE_SERVICE_SUBTYPE | DSLAMBUIConstant.CTE_DSLAM_APP_BUSINESS_SERVICE_TYPE;
	String 	CTE_BUSINESS_FILE_SERVICE_HEX_TYPE 	= Long.toHexString(CTE_BUSINESS_FILE_SERVICE_TYPE);
	String 	CTE_BUSINESS_FILE_SERVICE_NAME 		= "DSLAMBUIFileBusinessService";

	// RANGE CONSTANTS
	long	CTE_BUSINESS_FILE_SERVICE_RANGE_TRACE = CTE_BUSINESS_FILE_SERVICE_TYPE | MFTIConstant.CTE_MFT_RANGE_TRACE;

	//METHODS
	String 	METHOD_ADD_FILE			= "ADD_FILE";
	String 	METHOD_GET_ALL_FILES	= "GET_ALL_FILES";
	String 	METHOD_UPDATE_FILE		= "UPDATE_FILE";
	String 	METHOD_REMOVE_FILE		= "REMOVE_FILE";
	
	//CONTEXT
	String 	FILE_DATA = "FILE_DATA";
	
}
