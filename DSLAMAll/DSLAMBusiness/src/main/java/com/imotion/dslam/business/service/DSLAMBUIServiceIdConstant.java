package com.imotion.dslam.business.service;

import com.selene.arch.base.exe.bus.AEMFTIBusinessConstant;
import com.selene.arch.base.exe.bus.service.AEMFTIBusinessServiceIdConstant;

public interface DSLAMBUIServiceIdConstant extends AEMFTIBusinessServiceIdConstant {

	//FILE
	String CTE_DSLAM_BU_SRV_FILE_ID						= "SRV_BUS_FILE";
	String CTE_DSLAM_BU_SRV_FILE_PREFIX					= CTE_DSLAM_BU_SRV_FILE_ID 		+ AEMFTIBusinessConstant.CTE_MFT_AE_BUS_SERVICE_ID_SEPARATOR;
	String CTE_DSLAM_BU_SRV_FILE_ADD_FILE_ID			= CTE_DSLAM_BU_SRV_FILE_PREFIX	+ DSLAMBUIFileBusinessServiceConstants.METHOD_ADD_FILE;
	String CTE_DSLAM_BU_SRV_FILE_GET_ALL_FILES_ID		= CTE_DSLAM_BU_SRV_FILE_PREFIX	+ DSLAMBUIFileBusinessServiceConstants.METHOD_GET_ALL_FILES;
	String CTE_DSLAM_BU_SRV_FILE_UPDATE_FILE_ID			= CTE_DSLAM_BU_SRV_FILE_PREFIX	+ DSLAMBUIFileBusinessServiceConstants.METHOD_UPDATE_FILE;
	String CTE_DSLAM_BU_SRV_FILE_REMOVE_FILE_ID			= CTE_DSLAM_BU_SRV_FILE_PREFIX	+ DSLAMBUIFileBusinessServiceConstants.METHOD_REMOVE_FILE;
}	
