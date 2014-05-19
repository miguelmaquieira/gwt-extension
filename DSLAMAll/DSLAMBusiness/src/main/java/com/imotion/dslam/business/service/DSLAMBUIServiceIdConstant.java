package com.imotion.dslam.business.service;

import com.selene.arch.base.exe.bus.AEMFTIBusinessConstant;
import com.selene.arch.base.exe.bus.service.AEMFTIBusinessServiceIdConstant;

public interface DSLAMBUIServiceIdConstant extends AEMFTIBusinessServiceIdConstant {

	//CRON
	String CTE_DSLAM_BU_SRV_CRON_ID						= "SRV_BUS_CRON";
	String CTE_DSLAM_BU_SRV_CRON_PREFIX					= CTE_DSLAM_BU_SRV_CRON_ID + AEMFTIBusinessConstant.CTE_MFT_AE_BUS_SERVICE_ID_SEPARATOR;
	
	String CTE_DSLAM_BU_SRV_CRON_GET_DSLAMIT_DATA		= CTE_DSLAM_BU_SRV_CRON_PREFIX	+ DSLAMBUICronBusinessServiceConstants.METHOD_STORE_LAST_DATA;
	
}	
