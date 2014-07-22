package com.imotion.cronio.business.service;

import com.imotion.dslam.business.DSLAMBUIConstant;
import com.imotion.dslam.business.DSLAMBUIServiceConstant;
import com.selene.arch.base.MFTIConstant;

public interface CRONIOBUIAdminBusinessServiceConstants {
	
	// BusinessExecuteService types
	long 	CTE_BUSINESS_ADMIN_SERVICE_TYPE 		= DSLAMBUIServiceConstant.CTE_DSLAM_APP_BUS_EXECUTE_SERVICE_SUBTYPE | DSLAMBUIConstant.CTE_DSLAM_APP_BUSINESS_SERVICE_TYPE;
	String 	CTE_BUSINESS_ADMIN_SERVICE_HEX_TYPE 	= Long.toHexString(CTE_BUSINESS_ADMIN_SERVICE_TYPE);
	String 	CTE_BUSINESS_ADMIN_SERVICE_NAME 		= "CRONIOBUIAdminBusinessService";

	// RANGE CONSTANTS
	long	CTE_BUSINESS_ADMIN_SERVICE_RANGE_TRACE = CTE_BUSINESS_ADMIN_SERVICE_TYPE | MFTIConstant.CTE_MFT_RANGE_TRACE;

	//METHODS
	String 	METHOD_ADD_DEFAULT_DATA = "ADD_DEFAULT_DATA";
	
	
}
