package com.imotion.dslam.business;

import com.imotion.dslam.CRONIOIConstants;
import com.selene.arch.base.exe.AEMFTIBaseConstantes;

public interface DSLAMBUIConstant {
	
	// 0x0020000000000000L | 0x0001120000000000L = 0x0021120000000000L 
	public final static long CTE_DSLAM_APP_BUSINESS_SERVICE_TYPE = CRONIOIConstants.CTE_CRONIO_APP_TYPE | AEMFTIBaseConstantes.CTE_MFT_ARCH_EXE_BUS_IDENTIFICATION;
	
}
