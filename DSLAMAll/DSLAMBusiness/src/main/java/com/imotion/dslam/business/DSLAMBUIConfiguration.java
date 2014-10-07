package com.imotion.dslam.business;

import com.imotion.dslam.CRONIOIConstants;
import com.selene.arch.base.exe.core.common.AEMFTICommonConstants;
import com.selene.arch.base.exe.core.envi.config.AEMFTIConfigurationConstant;

public interface DSLAMBUIConfiguration {
	 
	 //CFG.DSLAM
	 public static String CTE_DSLAM_CONFIG_PREFIX = AEMFTIConfigurationConstant.CTE_MFT_AE_CORE_ENTO_CONFIGURACION_PREFIJO_VARIABLES_CONFIGURACION
			  							+ AEMFTICommonConstants.CTE_MFT_AE_CORE_COMM_ELEMENT_SEPARATOR 
			  							+ CRONIOIConstants.CTE_CRONIO_APPLI_NAME;
}
