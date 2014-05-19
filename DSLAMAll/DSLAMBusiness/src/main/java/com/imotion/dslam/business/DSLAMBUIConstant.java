package com.imotion.dslam.business;

import com.imotion.dslam.DSLAMIConstants;
import com.selene.arch.base.exe.AEMFTIBaseConstantes;

public interface DSLAMBUIConstant {
	
	// 0x0020000000000000L | 0x0001120000000000L = 0x0021120000000000L 
	public final static long CTE_DSLAM_APP_BUSINESS_SERVICE_TYPE = DSLAMIConstants.CTE_DSLAM_APP_TYPE | AEMFTIBaseConstantes.CTE_MFT_ARCH_EXE_BUS_IDENTIFICATION;
	
}
