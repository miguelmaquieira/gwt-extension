package com.imotion.dslam.business.service.base;

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
	String CTE_DSLAM_BU_SRV_PROJECT_REMOVE_PROJECT_ID	= CTE_DSLAM_BU_SRV_PROJECT_PREFIX	+ DSLAMBUIProjectBusinessServiceConstants.METHOD_REMOVE_PROJECT;
	String CTE_DSLAM_BU_SRV_PROJECT_GET_CSV_NODES_ID	= CTE_DSLAM_BU_SRV_PROJECT_PREFIX	+ DSLAMBUIProjectBusinessServiceConstants.METHOD_GET_CSV_NODES;
}	
