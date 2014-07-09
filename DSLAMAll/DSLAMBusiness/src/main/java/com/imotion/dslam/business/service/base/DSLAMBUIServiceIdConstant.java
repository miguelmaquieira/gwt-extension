package com.imotion.dslam.business.service.base;

import com.imotion.dslam.business.service.CRONIOBUIPreferencesBusinessServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUIExecuteBusinessServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUIProjectBusinessServiceConstants;
import com.selene.arch.base.exe.bus.AEMFTIBusinessConstant;
import com.selene.arch.base.exe.bus.service.AEMFTIBusinessServiceIdConstant;

public interface DSLAMBUIServiceIdConstant extends AEMFTIBusinessServiceIdConstant {

	//PROJECT
	String CTE_DSLAM_BU_SRV_PROJECT_ID					= "SRV_BUS_PROJECT";
	String CTE_DSLAM_BU_SRV_PROJECT_PREFIX				= CTE_DSLAM_BU_SRV_PROJECT_ID 		+ AEMFTIBusinessConstant.CTE_MFT_AE_BUS_SERVICE_ID_SEPARATOR;
	String CTE_DSLAM_BU_SRV_PROJECT_ADD_PROJECT_ID		= CTE_DSLAM_BU_SRV_PROJECT_PREFIX	+ DSLAMBUIProjectBusinessServiceConstants.METHOD_ADD_PROJECT;
	String CTE_DSLAM_BU_SRV_PROJECT_GET_ALL_PROJECTS_ID	= CTE_DSLAM_BU_SRV_PROJECT_PREFIX	+ DSLAMBUIProjectBusinessServiceConstants.METHOD_GET_ALL_PROJECTS;
	String CTE_DSLAM_BU_SRV_PROJECT_UPDATE_PROJECT_ID	= CTE_DSLAM_BU_SRV_PROJECT_PREFIX	+ DSLAMBUIProjectBusinessServiceConstants.METHOD_UPDATE_PROJECT;
	String CTE_DSLAM_BU_SRV_PROJECT_UPDATE_PROJECTS_ID	= CTE_DSLAM_BU_SRV_PROJECT_PREFIX	+ DSLAMBUIProjectBusinessServiceConstants.METHOD_UPDATE_PROJECTS;
	String CTE_DSLAM_BU_SRV_PROJECT_REMOVE_PROJECT_ID	= CTE_DSLAM_BU_SRV_PROJECT_PREFIX	+ DSLAMBUIProjectBusinessServiceConstants.METHOD_REMOVE_PROJECT;
	String CTE_DSLAM_BU_SRV_PROJECT_GET_CSV_NODES_ID	= CTE_DSLAM_BU_SRV_PROJECT_PREFIX	+ DSLAMBUIProjectBusinessServiceConstants.METHOD_GET_CSV_NODES;
	
	
	//PREFERENCES
	String CTE_DSLAM_BU_SRV_PREFERENCES_ID					= "SRV_BUS_PREFERENCES";
	String CTE_DSLAM_BU_SRV_PREFERENCES_PREFIX				= CTE_DSLAM_BU_SRV_PREFERENCES_ID 		+ AEMFTIBusinessConstant.CTE_MFT_AE_BUS_SERVICE_ID_SEPARATOR;
	String CTE_DSLAM_BU_SRV_PREFERENCES_ADD_CONNECTION_ID	= CTE_DSLAM_BU_SRV_PREFERENCES_PREFIX	+ CRONIOBUIPreferencesBusinessServiceConstants.METHOD_ADD_CONNECTION;
	String CTE_DSLAM_BU_SRV_PREFERENCES_GET_PREFERENCES_ID	= CTE_DSLAM_BU_SRV_PREFERENCES_PREFIX	+ CRONIOBUIPreferencesBusinessServiceConstants.METHOD_GET_PREFERENCES;
	
	//EXECUTE
	String CTE_DSLAM_BU_SRV_EXECUTE_ID					= "SRV_BUS_EXECUTE";
	String CTE_DSLAM_BU_SRV_EXECUTE_PREFFIX				= CTE_DSLAM_BU_SRV_EXECUTE_ID 		+ AEMFTIBusinessConstant.CTE_MFT_AE_BUS_SERVICE_ID_SEPARATOR;
	String CTE_DSLAM_BU_SRV_EXECUTE_EXECUTE_PROJECT_ID	= CTE_DSLAM_BU_SRV_EXECUTE_PREFFIX	+ DSLAMBUIExecuteBusinessServiceConstants.METHOD_EXECUTE_PROJECT;
}	
