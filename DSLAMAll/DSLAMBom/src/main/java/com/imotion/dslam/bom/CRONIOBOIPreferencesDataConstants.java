package com.imotion.dslam.bom;

import com.selene.arch.base.exe.bus.AEMFTIBusinessConstant;


public interface CRONIOBOIPreferencesDataConstants {
	
	
	String PREFERENCES_ID 						= "preferencesId";
	String PREFERENCES_MACHINE_PROPERTIES_LIST	= "machinePropertiesList";
	String SAVED_TIME							= "savedTime";
	String CREATION_TIME						= "creationTime";

	//CONTEXT
	String	PREFERENCES_DATA							= "preferencesData";
	String	PREFERENCES_DATA_PREFFIX					= PREFERENCES_DATA + AEMFTIBusinessConstant.CTE_MFT_AE_BUS_SERVICE_ID_SEPARATOR;
	String	PREFERENCES_DATA_MACHINE_PROPERTIES_LIST	= PREFERENCES_DATA_PREFFIX + PREFERENCES_MACHINE_PROPERTIES_LIST;
	
	String INFO								= "INFO";
	String INFO_PREFFIX						= INFO + AEMFTIBusinessConstant.CTE_MFT_AE_BUS_SERVICE_ID_SEPARATOR;
	String IS_MODIFIED 						= "IS_MODIFIED";
	String INFO_IS_MODIFIED					= INFO_PREFFIX + IS_MODIFIED;
	
	long PREFERENCES_DEFAULT_ID				= 1;

	
	String PREFERENCES_CONNECTION_DSLAM			= "preferencesConnectionDslam";
	
	
}