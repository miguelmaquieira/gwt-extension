package com.imotion.dslam.business.service.base;

import com.imotion.dslam.business.CRONIOBUIConstant;
import com.imotion.dslam.business.CRONIOBUIServiceConstant;
import com.selene.arch.base.MFTIConstant;

public interface CRONIOBUICommonServiceConstants {

	// BusinessServiceCommonConstant
	public static long	CTE_BUSINESS_SERVICE_COMMON_TYPE			= CRONIOBUIServiceConstant.CTE_CRONIO_APP_BUS_COMMON_SERVICE_SUBTYPE | CRONIOBUIConstant.CTE_CRONIO_APP_BUSINESS_SERVICE_TYPE;
	public static String	CTE_BUSINESS_SERVICE_COMMON_HEX_TYPE		= Long.toHexString(CTE_BUSINESS_SERVICE_COMMON_TYPE);

	// Constants
	public static long	CTE_BUSINESS_SERVICE_COMMON_RANGE_TRACE		= CTE_BUSINESS_SERVICE_COMMON_TYPE | MFTIConstant.CTE_MFT_RANGE_TRACE;

	//PARAMS

}

