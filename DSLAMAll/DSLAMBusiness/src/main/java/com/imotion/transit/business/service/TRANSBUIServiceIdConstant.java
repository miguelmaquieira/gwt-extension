package com.imotion.transit.business.service;

import com.selene.arch.base.exe.bus.AEMFTIBusinessConstant;
import com.selene.arch.base.exe.bus.service.AEMFTIBusinessServiceIdConstant;

public interface TRANSBUIServiceIdConstant extends AEMFTIBusinessServiceIdConstant {

	//CRON
	String CTE_TRANS_BU_SRV_CRON_ID						= "SRV_BUS_CRON";
	String CTE_TRANS_BU_SRV_CRON_PREFIX					= CTE_TRANS_BU_SRV_CRON_ID + AEMFTIBusinessConstant.CTE_MFT_AE_BUS_SERVICE_ID_SEPARATOR;
	
	String CTE_TRANS_BU_SRV_CRON_GET_TRANSIT_DATA		= CTE_TRANS_BU_SRV_CRON_PREFIX	+ TRANSBUICronBusinessServiceConstants.METHOD_STORE_LAST_DATA;
	
}	
