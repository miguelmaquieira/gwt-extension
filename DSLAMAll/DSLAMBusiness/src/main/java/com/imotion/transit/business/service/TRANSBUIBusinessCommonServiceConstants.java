package com.imotion.transit.business.service;

import com.imotion.transit.business.TRANSBUIConstant;
import com.selene.arch.base.MFTIConstant;

public interface TRANSBUIBusinessCommonServiceConstants {

	// BusinessServiceCommonConstant
	public static long	CTE_BUSINESS_SERVICE_COMMON_TYPE			= TRANSBUIServiceConstant.CTE_TRANS_APP_BUS_COMMON_SERVICE_SUBTYPE | TRANSBUIConstant.CTE_TRANS_APP_BUSINESS_SERVICE_TYPE;
	public static String	CTE_BUSINESS_SERVICE_COMMON_HEX_TYPE		= Long.toHexString(CTE_BUSINESS_SERVICE_COMMON_TYPE);

	// Constants
	public static long	CTE_BUSINESS_SERVICE_COMMON_RANGE_TRACE		= CTE_BUSINESS_SERVICE_COMMON_TYPE | MFTIConstant.CTE_MFT_RANGE_TRACE;

	//PARAMS

}

