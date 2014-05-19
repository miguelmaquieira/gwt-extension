package com.imotion.transit.backend;

import com.imotion.transit.TRANSIConstants;
import com.selene.arch.base.exe.AEMFTIBaseConstantes;

public interface TRANSBKIConstants {
	
	// 0x0020000000000000L | 0x0001130000000000L = 0x0021130000000000L 
	public final static long CTE_TRANS_BACKEND_SERVICE_TYPE = TRANSIConstants.CTE_TRANS_APP_TYPE| AEMFTIBaseConstantes.CTE_MFT_ARCH_EXE_BACK_IDENTIFICATION;
	
}
