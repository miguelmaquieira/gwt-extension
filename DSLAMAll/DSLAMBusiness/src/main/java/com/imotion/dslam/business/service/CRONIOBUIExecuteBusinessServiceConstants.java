package com.imotion.dslam.business.service;

import com.imotion.dslam.business.DSLAMBUIConstant;
import com.imotion.dslam.business.DSLAMBUIServiceConstant;
import com.selene.arch.base.MFTIConstant;

public interface CRONIOBUIExecuteBusinessServiceConstants {
	
	// BusinessExecuteService types
	long 	CTE_BUSINESS_EXECUTE_SERVICE_TYPE 		= DSLAMBUIServiceConstant.CTE_DSLAM_APP_BUS_EXECUTE_SERVICE_SUBTYPE | DSLAMBUIConstant.CTE_DSLAM_APP_BUSINESS_SERVICE_TYPE;
	String 	CTE_BUSINESS_EXECUTE_SERVICE_HEX_TYPE 	= Long.toHexString(CTE_BUSINESS_EXECUTE_SERVICE_TYPE);
	String 	CTE_BUSINESS_EXECUTE_SERVICE_NAME 		= "DSLAMBUIExecuteBusinessService";

	// RANGE CONSTANTS
	long	CTE_BUSINESS_EXECUTE_SERVICE_RANGE_TRACE = CTE_BUSINESS_EXECUTE_SERVICE_TYPE | MFTIConstant.CTE_MFT_RANGE_TRACE;

	//METHODS
	String 	METHOD_EXECUTE_PROJECT 					= "EXECUTE_PROJECT";
	String 	METHOD_ADD_EXECUTION 					= "ADD_EXECUTION";
	String 	METHOD_GET_EXECUTION 					= "GET_EXECUTION";
	String  METHOD_GET_ALL_EXECUTIONS_BY_PROJECT_ID	= "GET_ALL_EXCUTIONS_BY_PROJECT_ID";
	
	//CONTEXT
	String 	EXECUTE_DATA 					= "EXECUTE_DATA";
	String 	EXECUTIONS_BY_PROJECT_LIST_DATA = "EXECUTIONS_BY_PROJECT_LIST_DATA";
	String 	EXECUTION_DATA 					= "EXECUTION_DATA";
	String  EXECUTIONS_DATA					= "EXECUTIONS_DATA";
	
}
