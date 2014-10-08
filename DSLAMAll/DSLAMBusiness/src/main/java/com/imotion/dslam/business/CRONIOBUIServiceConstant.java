package com.imotion.dslam.business;

import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTIMetadataElementConstant;


public interface CRONIOBUIServiceConstant {
	
	//LoginBusinessService
	public static final String CFG_SRV_BUSINESS_LOGIN_SERVICE_IMPL			= "CFG.SRV_BUSINESS_SERVICES.LOGIN.PERSISTENCE_IMPL";
	public static final String CTE_SRV_BUSINESS_LOGIN_SERVICE_DEFAULT_IMPL	= "com.selene.m2m.business.service.impl.BusinessLoginServiceImpl";
	
	//Other KEYS
	public static final String ID_KEY		= AEMFTIMetadataElementConstant.CTE_MFT_AE_CORE_APPLI_METADATA_COMMON_ID_KEY;
	public static final String DATA_KEY	= AEMFTIMetadataElementConstant.CTE_MFT_AE_CORE_APPLI_METADATA_COMMON_DATA_KEY;
	// Services subtypes
	public static long CTE_CRONIO_APP_BUS_COMMON_SERVICE_SUBTYPE		= 0x00000000001000000L;
	public static long CTE_CRONIO_APP_BUS_LOGIN_SERVICE_SUBTYPE		= 0x00000000002000000L;
	public static long CTE_CRONIO_APP_BUS_FILE_SERVICE_SUBTYPE			= 0x00000000003000000L;
	public static long CTE_CRONIO_APP_BUS_EXECUTE_SERVICE_SUBTYPE		= 0x00000000004000000L;
	
}
