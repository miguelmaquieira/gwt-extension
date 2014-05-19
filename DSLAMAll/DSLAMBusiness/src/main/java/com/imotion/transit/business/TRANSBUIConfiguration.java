package com.imotion.transit.business;

import com.imotion.transit.TRANSIConstants;
import com.selene.arch.base.exe.core.common.AEMFTICommonConstants;
import com.selene.arch.base.exe.core.envi.config.AEMFTIConfigurationConstant;

public interface TRANSBUIConfiguration {
	 
	 //CFG.TRANS
	 public static String CTE_TRANS_CONFIG_PREFIX = AEMFTIConfigurationConstant.CTE_MFT_AE_CORE_ENTO_CONFIGURACION_PREFIJO_VARIABLES_CONFIGURACION
			  							+ AEMFTICommonConstants.CTE_MFT_AE_CORE_COMM_ELEMENT_SEPARATOR 
			  							+ TRANSIConstants.CTE_SNDO_APPLI_NAME;
}
