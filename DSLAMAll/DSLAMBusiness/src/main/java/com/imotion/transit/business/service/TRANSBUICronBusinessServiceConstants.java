package com.imotion.transit.business.service;

import com.imotion.transit.business.TRANSBUIConstant;
import com.selene.arch.base.MFTIConstant;

public interface TRANSBUICronBusinessServiceConstants {
	
	// BusinessCronService types
	long 		CTE_BUSINESS_CRON_SERVICE_TYPE 		= TRANSBUIServiceConstant.CTE_TRANS_APP_BUS_CRON_SERVICE_SUBTYPE | TRANSBUIConstant.CTE_TRANS_APP_BUSINESS_SERVICE_TYPE;
	String 		CTE_BUSINESS_CRON_SERVICE_HEX_TYPE 	= Long.toHexString(CTE_BUSINESS_CRON_SERVICE_TYPE);
	String 		CTE_BUSINESS_CRON_SERVICE_NAME 		= "TRANSBUICronBusinessService";

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
	String TRANSIT_INIT 		= "init";
	String TRANSIT_END 			= "end";
	String TRANSIT_TOTAL 		= "T";
	String TRANSIT_IN 			= "I";
	String TRANSIT_PASSING 		= "P";
	String TRANSIT_DATE_FORMAT	= "yyyy-MM-dd'T'HH:mm:ss'Z'";
	
}
