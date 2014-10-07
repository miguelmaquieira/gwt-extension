package com.imotion.dslam.backend;

import com.imotion.dslam.CRONIOIConstants;
import com.selene.arch.base.exe.AEMFTIBaseConstantes;

public interface CRONIOBKIConstants {
	
	// 0x0020000000000000L | 0x0001130000000000L = 0x0021130000000000L 
	public final static long CTE_CRONIO_BACKEND_SERVICE_TYPE = CRONIOIConstants.CTE_CRONIO_APP_TYPE| AEMFTIBaseConstantes.CTE_MFT_ARCH_EXE_BACK_IDENTIFICATION;
	
}
