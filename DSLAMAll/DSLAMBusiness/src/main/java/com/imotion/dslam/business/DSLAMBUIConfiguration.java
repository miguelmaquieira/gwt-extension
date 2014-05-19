package com.imotion.dslam.business;

import com.imotion.dslam.DSLAMIConstants;
import com.selene.arch.base.exe.core.common.AEMFTICommonConstants;
import com.selene.arch.base.exe.core.envi.config.AEMFTIConfigurationConstant;

public interface DSLAMBUIConfiguration {
	 
	 //CFG.DSLAM
	 public static String CTE_DSLAM_CONFIG_PREFIX = AEMFTIConfigurationConstant.CTE_MFT_AE_CORE_ENTO_CONFIGURACION_PREFIJO_VARIABLES_CONFIGURACION
			  							+ AEMFTICommonConstants.CTE_MFT_AE_CORE_COMM_ELEMENT_SEPARATOR 
			  							+ DSLAMIConstants.CTE_SNDO_APPLI_NAME;
}
