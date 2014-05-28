package com.imotion.dslam.business.service;

import com.imotion.dslam.business.DSLAMBUIConstant;
import com.imotion.dslam.business.DSLAMBUIServiceConstant;
import com.selene.arch.base.MFTIConstant;

public interface DSLAMBUIBusinessCommonServiceConstants {

	// BusinessServiceCommonConstant
	public static long	CTE_BUSINESS_SERVICE_COMMON_TYPE			= DSLAMBUIServiceConstant.CTE_DSLAM_APP_BUS_COMMON_SERVICE_SUBTYPE | DSLAMBUIConstant.CTE_DSLAM_APP_BUSINESS_SERVICE_TYPE;
	public static String	CTE_BUSINESS_SERVICE_COMMON_HEX_TYPE		= Long.toHexString(CTE_BUSINESS_SERVICE_COMMON_TYPE);

	// Constants
	public static long	CTE_BUSINESS_SERVICE_COMMON_RANGE_TRACE		= CTE_BUSINESS_SERVICE_COMMON_TYPE | MFTIConstant.CTE_MFT_RANGE_TRACE;

	//PARAMS

}

