package com.imotion.dslam.business.service;

import com.selene.arch.base.exe.bus.AEMFTIBusinessConstant;


public interface CRONIOBUIPreferencesBusinessServiceConstants {
	
	//METHODS
	String 	METHOD_ADD_PREFERENCES		= "ADD_PREFERENCES";
	String 	METHOD_UPDATE_PREFERENCES	= "UPDATE_PREFERENCES";
	String 	METHOD_REMOVE_PREFERENCES	= "REMOVE_PREFERENCES";
	
	//CONTEXT
	String 	PREFERENCES_DATA 			= "PREFERENCES_DATA";
	String 	PREFERENCES_DATA_PREFFIX 	= PREFERENCES_DATA		+	AEMFTIBusinessConstant.CTE_MFT_AE_BUS_SERVICE_ID_SEPARATOR;
}
