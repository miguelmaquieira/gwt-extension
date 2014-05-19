package com.imotion.dslam.business.service;

import com.imotion.dslam.business.DSLAMBUIConstant;
import com.selene.arch.base.MFTIConstant;

public interface DSLAMBUICronBusinessServiceConstants {
	
	// BusinessCronService types
	long 		CTE_BUSINESS_CRON_SERVICE_TYPE 		= DSLAMBUIServiceConstant.CTE_DSLAM_APP_BUS_CRON_SERVICE_SUBTYPE | DSLAMBUIConstant.CTE_DSLAM_APP_BUSINESS_SERVICE_TYPE;
	String 		CTE_BUSINESS_CRON_SERVICE_HEX_TYPE 	= Long.toHexString(CTE_BUSINESS_CRON_SERVICE_TYPE);
	String 		CTE_BUSINESS_CRON_SERVICE_NAME 		= "DSLAMBUICronBusinessService";

	// RANGE CONSTANTS
	long 		CTE_BUSINESS_CRON_SERVICE_RANGE_TRACE = CTE_BUSINESS_CRON_SERVICE_TYPE | MFTIConstant.CTE_MFT_RANGE_TRACE;

	//METHODS
	String	METHOD_STORE_LAST_DATA		= "STORE_LAST_DATA";
	String	METHOD_COMPUTE_DAILY_DATA	= "COMPUTE_DAILY_DATA";
	
	//CONTEXT
	
	//QUERY
	String QUERY_TIME_INIT 	= "init";
	String QUERY_TIME_END 	= "end";
	String QUERY_TIME_TABLE	= "table";
	
	//RESULT
	String DSLAMIT_INIT 		= "init";
	String DSLAMIT_END 			= "end";
	String DSLAMIT_TOTAL 		= "T";
	String DSLAMIT_IN 			= "I";
	String DSLAMIT_PASSING 		= "P";
	String DSLAMIT_DATE_FORMAT	= "yyyy-MM-dd'T'HH:mm:ss'Z'";
	
}
