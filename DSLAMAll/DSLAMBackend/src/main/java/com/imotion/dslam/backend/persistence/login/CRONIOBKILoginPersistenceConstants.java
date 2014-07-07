package com.imotion.dslam.backend.persistence.login;

import com.imotion.dslam.backend.DSLAMBKIConstants;
import com.imotion.dslam.backend.persistence.DSLAMBKIPersistenceConstants;
import com.selene.arch.base.MFTIConstant;

public interface CRONIOBKILoginPersistenceConstants {
	
	// 0x0000000002000000L | 0x0021130000000000L = 0x0021130001000000L
	public final static long 		CTE_CRONIO_APP_PERSISTENCE_LOGIN_SERVICE_TYPE 			= DSLAMBKIPersistenceConstants.CTE_DSLAM_PERSISTENCE_LOGIN_SERVICE_SUBTYPE | DSLAMBKIConstants.CTE_DSLAM_BACKEND_SERVICE_TYPE;
	public final static String 	CTE_CRONIO_APP_PERSISTENCE_LOGIN_SERVICE_HEX_TYPE 		= Long.toHexString(CTE_CRONIO_APP_PERSISTENCE_LOGIN_SERVICE_TYPE);
	public final static String 	CTE_CRONIO_APP_PERSISTENCE_LOGIN_SERVICE_NAME 			= "SNDOBKPersistenceLogin";
	
	// Constants
	public final static long 		CTE_CRONIO_PERSISTENCE_LOGIN_SERVICE_RANGE_TRACE 			= CTE_CRONIO_APP_PERSISTENCE_LOGIN_SERVICE_TYPE | MFTIConstant.CTE_MFT_RANGE_TRACE;

}
