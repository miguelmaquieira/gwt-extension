package com.imotion.dslam.business.service;

import com.imotion.dslam.business.DSLAMBUIConstant;
import com.selene.arch.base.MFTIConstant;

public interface DSLAMBUIFileBusinessServiceConstants {
	
	// BusinessFILEService types
	long 		CTE_BUSINESS_FILE_SERVICE_TYPE 		= DSLAMBUIServiceConstant.CTE_DSLAM_APP_BUS_FILE_SERVICE_SUBTYPE | DSLAMBUIConstant.CTE_DSLAM_APP_BUSINESS_SERVICE_TYPE;
	String 		CTE_BUSINESS_FILE_SERVICE_HEX_TYPE 	= Long.toHexString(CTE_BUSINESS_FILE_SERVICE_TYPE);
	String 		CTE_BUSINESS_FILE_SERVICE_NAME 		= "DSLAMBUIFileBusinessService";

	// RANGE CONSTANTS
	long 		CTE_BUSINESS_FILE_SERVICE_RANGE_TRACE = CTE_BUSINESS_FILE_SERVICE_TYPE | MFTIConstant.CTE_MFT_RANGE_TRACE;

	//METHODS
	
	//CONTEXT
	
}
