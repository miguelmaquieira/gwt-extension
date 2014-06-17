package com.imotion.dslam.bom;

import com.selene.arch.base.exe.bus.AEMFTIBusinessConstant;

public interface CRONIOBOIProjectDataConstants {
	
	
	String PROJECT_ID 				= "projectId";
	String PROJECT_NAME				= "projectName";
	String PROJECT_MACHINE_TYPE		= "projectMachineType";
	String PROJECT_MAIN_SCRIPT		= "projectMainScript";
	String PROJECT_ROLLBACK_SCRIPT	= "projectRollBackScript";	
	String PROJECT_PROCESS			= "projectProcess";
	String SAVED_TIME				= "savedTime";
	String CREATION_TIME			= "creationTime";
	
	String PROJECT_CONFIGURE_OPTION_ID	= "projectConfigureOptionId";
	String PROJECT_CONFIGURE_DATA		= "projectConfigureData";
	String PROJECT_DATA					= "projectData";
	
	//Project TYPES
	int PROJECT_MACHINE_TYPE_DSLAM = 0;
	
	String MAIN_SCRIPT_ID 		= "mainScriptId";
	String ROLLBACK_SCRIPT_ID 	= "rollBackScriptId";
	
	//CONTEXT
	String CURRENT_SECTION					= "CURRENT_SECTION";
	String PROJECT_PROCESS_PREFFIX			= PROJECT_PROCESS + AEMFTIBusinessConstant.CTE_MFT_AE_BUS_SERVICE_ID_SEPARATOR;
	String PROJECT_PROCESS_VARIABLE_LIST	= PROJECT_PROCESS_PREFFIX + DSLAMBOIProcess.PROCESS_VARIABLE_LIST;
	String PROJECT_PROCESS_SCHEDULE_LIST	= PROJECT_PROCESS_PREFFIX + DSLAMBOIProcess.PROCESS_SCHEDULE_LIST;
	String PROJECT_PROCESS_NODES			= PROJECT_PROCESS_PREFFIX + DSLAMBOIProcess.PROCESS_NODES;
	String PROJECT_PROCESS_EXTRA_OPTIONS	= PROJECT_PROCESS_PREFFIX + DSLAMBOIProcess.PROCESS_EXTRA_OPTIONS;
	

}