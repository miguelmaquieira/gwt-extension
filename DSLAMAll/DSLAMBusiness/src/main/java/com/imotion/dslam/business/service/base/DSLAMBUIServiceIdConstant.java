package com.imotion.dslam.business.service.base;

import com.imotion.dslam.business.service.CRONIOBUIPreferencesBusinessServiceConstants;
import com.imotion.dslam.business.service.CRONIOBUIExecuteBusinessServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUIProjectBusinessServiceConstants;
import com.selene.arch.base.exe.bus.AEMFTIBusinessConstant;
import com.selene.arch.base.exe.bus.service.AEMFTIBusinessServiceIdConstant;

public interface DSLAMBUIServiceIdConstant extends AEMFTIBusinessServiceIdConstant {

	//PROJECT
	String CTE_DSLAM_BU_SRV_PROJECT_ID									= "SRV_BUS_PROJECT";
	String CTE_DSLAM_BU_SRV_PROJECT_PREFIX								= CTE_DSLAM_BU_SRV_PROJECT_ID 		+ AEMFTIBusinessConstant.CTE_MFT_AE_BUS_SERVICE_ID_SEPARATOR;
	String CTE_DSLAM_BU_SRV_PROJECT_ADD_PROJECT_ID						= CTE_DSLAM_BU_SRV_PROJECT_PREFIX	+ DSLAMBUIProjectBusinessServiceConstants.METHOD_ADD_PROJECT;
	String CTE_DSLAM_BU_SRV_PROJECT_GET_ALL_PROJECTS_BY_USER_ID			= CTE_DSLAM_BU_SRV_PROJECT_PREFIX	+ DSLAMBUIProjectBusinessServiceConstants.METHOD_GET_ALL_PROJECTS_BY_USER;
	String CTE_DSLAM_BU_SRV_PROJECT_UPDATE_PROJECT_ID					= CTE_DSLAM_BU_SRV_PROJECT_PREFIX	+ DSLAMBUIProjectBusinessServiceConstants.METHOD_UPDATE_PROJECT;
	String CTE_DSLAM_BU_SRV_PROJECT_UPDATE_PROJECTS_ID					= CTE_DSLAM_BU_SRV_PROJECT_PREFIX	+ DSLAMBUIProjectBusinessServiceConstants.METHOD_UPDATE_PROJECTS;
	String CTE_DSLAM_BU_SRV_PROJECT_REMOVE_PROJECT_ID					= CTE_DSLAM_BU_SRV_PROJECT_PREFIX	+ DSLAMBUIProjectBusinessServiceConstants.METHOD_REMOVE_PROJECT;
	String CTE_DSLAM_BU_SRV_PROJECT_GET_CSV_NODES_ID					= CTE_DSLAM_BU_SRV_PROJECT_PREFIX	+ DSLAMBUIProjectBusinessServiceConstants.METHOD_GET_CSV_NODES;
	
	//PREFERENCES
	String CTE_DSLAM_BU_SRV_PREFERENCES_ID							= "SRV_BUS_PREFERENCES";
	String CTE_DSLAM_BU_SRV_PREFERENCES_PREFIX						= CTE_DSLAM_BU_SRV_PREFERENCES_ID 		+ AEMFTIBusinessConstant.CTE_MFT_AE_BUS_SERVICE_ID_SEPARATOR;
	String CTE_DSLAM_BU_SRV_PREFERENCES_ADD_CONNECTION_ID			= CTE_DSLAM_BU_SRV_PREFERENCES_PREFIX	+ CRONIOBUIPreferencesBusinessServiceConstants.METHOD_ADD_CONNECTION;
	String CTE_DSLAM_BU_SRV_PREFERENCES_GET_PREFERENCES_ID			= CTE_DSLAM_BU_SRV_PREFERENCES_PREFIX	+ CRONIOBUIPreferencesBusinessServiceConstants.METHOD_GET_PREFERENCES;
	String CTE_DSLAM_BU_SRV_PREFERENCES_UPDATE_PREFERENCES_ID		= CTE_DSLAM_BU_SRV_PREFERENCES_PREFIX	+ CRONIOBUIPreferencesBusinessServiceConstants.METHOD_UPDATE_PREFERENCES;
	String CTE_DSLAM_BU_SRV_PREFERENCES_UPDATE_MACHINE_CONFIG_ID	= CTE_DSLAM_BU_SRV_PREFERENCES_PREFIX	+ CRONIOBUIPreferencesBusinessServiceConstants.METHOD_UPDATE_MACHINE_CONFIG;
	
	//EXECUTE
	String CTE_DSLAM_BU_SRV_EXECUTE_ID									= "SRV_BUS_EXECUTE";
	String CTE_DSLAM_BU_SRV_EXECUTE_PREFIX								= CTE_DSLAM_BU_SRV_EXECUTE_ID 		+ AEMFTIBusinessConstant.CTE_MFT_AE_BUS_SERVICE_ID_SEPARATOR;
	String CTE_DSLAM_BU_SRV_EXECUTE_EXECUTE_PROJECT_ID					= CTE_DSLAM_BU_SRV_EXECUTE_PREFIX	+ CRONIOBUIExecuteBusinessServiceConstants.METHOD_EXECUTE_PROJECT;
	String CTE_DSLAM_BU_SRV_EXECUTE_ADD_EXECUTION_ID					= CTE_DSLAM_BU_SRV_EXECUTE_PREFIX	+ CRONIOBUIExecuteBusinessServiceConstants.METHOD_ADD_EXECUTION;
	String CTE_DSLAM_BU_SRV_EXECUTE_GET_ALL_EXECUTIONS_BY_PROJECT_ID	= CTE_DSLAM_BU_SRV_EXECUTE_PREFIX	+ CRONIOBUIExecuteBusinessServiceConstants.METHOD_GET_ALL_EXCUTIONS_BY_PROJECT_ID;
	
}	
