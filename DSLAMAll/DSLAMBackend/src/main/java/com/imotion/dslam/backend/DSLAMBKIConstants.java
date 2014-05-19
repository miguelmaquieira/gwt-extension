package com.imotion.dslam.backend;

import com.imotion.dslam.DSLAMIConstants;
import com.selene.arch.base.exe.AEMFTIBaseConstantes;

public interface DSLAMBKIConstants {
	
	// 0x0020000000000000L | 0x0001130000000000L = 0x0021130000000000L 
	public final static long CTE_DSLAM_BACKEND_SERVICE_TYPE = DSLAMIConstants.CTE_DSLAM_APP_TYPE| AEMFTIBaseConstantes.CTE_MFT_ARCH_EXE_BACK_IDENTIFICATION;
	
}
