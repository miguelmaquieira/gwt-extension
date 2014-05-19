package com.imotion.transit.business;

import com.imotion.transit.TRANSIConstants;
import com.selene.arch.base.exe.AEMFTIBaseConstantes;

public interface TRANSBUIConstant {
	
	// 0x0020000000000000L | 0x0001120000000000L = 0x0021120000000000L 
	public final static long CTE_TRANS_APP_BUSINESS_SERVICE_TYPE = TRANSIConstants.CTE_TRANS_APP_TYPE | AEMFTIBaseConstantes.CTE_MFT_ARCH_EXE_BUS_IDENTIFICATION;
	
}
