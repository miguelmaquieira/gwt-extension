package com.imotion.dslam.backend.persistence.login;

import com.selene.arch.exe.core.envi.trace.AEMFTITraceConstant;

public interface CRONIOBKILoginPersistenceTrace {

	/***********************************************************************
	 *                            TRACE LEVEL 1                            *
	 ***********************************************************************/
	
	
	/***********************************************************************
	 *                            TRACE LEVEL 2                            *
	 ***********************************************************************/
	/**
	 * Message:  Login invalid because user with credentials {0} is not activated yet
	 */
	public static long CTE_SNDO_PERSISTENCE_LOGIN_USER_NOT_ACTIVATED_TRACE = 
			CRONIOBKILoginPersistenceConstants.CTE_CRONIO_PERSISTENCE_LOGIN_SERVICE_RANGE_TRACE
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_2  
			| 0x0000000000000001L;
	
	/**
	 * Message:  User with credentials {0} is stored {1} times in database
	 */
	public static long CTE_SNDO_PERSISTENCE_LOGIN_DUPLICATED_USER_TRACE = 
			CRONIOBKILoginPersistenceConstants.CTE_CRONIO_PERSISTENCE_LOGIN_SERVICE_RANGE_TRACE 
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_2  
			| 0x0000000000000002L;
	
	/**
	 * Message:  Login invalid for email {0} hash was: {1} ... Login failed
	 */
	public static long CTE_SNDO_PERSISTENCE_LOGIN_INVALID_LOGIN_TRACE = 
			CRONIOBKILoginPersistenceConstants.CTE_CRONIO_PERSISTENCE_LOGIN_SERVICE_RANGE_TRACE 
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_2  
			| 0x0000000000000003L;
	
	/**
	 * Message: SessionId {0} is already stored {1} times in database
	 */
	public static long CTE_SNDO_PERSISTENCE_LOGIN_DUPLICATED_SESSION_ID_TRACE = 
			CRONIOBKILoginPersistenceConstants.CTE_CRONIO_PERSISTENCE_LOGIN_SERVICE_RANGE_TRACE 
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_2  
			| 0x0000000000000004L;
	
	/***********************************************************************
	 *                            TRACE LEVEL 3                            *
	 ***********************************************************************/
	/**
	 * Message:  Validated login for user: {0}
	 */
	public static long CTE_SNDO_PERSISTENCE_LOGIN_USER_LOGIN_VALIDATED_LOGIN_TRACE = 
			CRONIOBKILoginPersistenceConstants.CTE_CRONIO_PERSISTENCE_LOGIN_SERVICE_RANGE_TRACE 
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_3  
			| 0x0000000000000001L;
	
	/**
	 * Message:  Login correct for user: {0}
	 */
	public static long CTE_SNDO_PERSISTENCE_LOGIN_USER_LOGIN_CORRECT_LOGIN_TRACE = 
			CRONIOBKILoginPersistenceConstants.CTE_CRONIO_PERSISTENCE_LOGIN_SERVICE_RANGE_TRACE 
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_3  
			| 0x0000000000000002L;
	
	/**
	 * Message:  Valid sessionId for user {0}. Last login: {1}
	 */
	public static long CTE_SNDO_PERSISTENCE_LOGIN_USER_LOGIN_VALID_SESSION_TRACE = 
			CRONIOBKILoginPersistenceConstants.CTE_CRONIO_PERSISTENCE_LOGIN_SERVICE_RANGE_TRACE 
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_3  
			| 0x0000000000000003L;
	
	/**
	 * Message:  The user: {0} has been successfully created in our database
	 */
	public static long CTE_SNDO_PERSISTENCE_LOGIN_USER_LOGIN_USER_CREATED_TRACE = 
			CRONIOBKILoginPersistenceConstants.CTE_CRONIO_PERSISTENCE_LOGIN_SERVICE_RANGE_TRACE 
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_3  
			| 0x0000000000000004L;
	
	/**
	 * Message:  The user: {0} already exits in our database
	 */
	public static long CTE_SNDO_PERSISTENCE_LOGIN_USER_LOGIN_USER_ALREADY_EXITS_TRACE = 
			CRONIOBKILoginPersistenceConstants.CTE_CRONIO_PERSISTENCE_LOGIN_SERVICE_RANGE_TRACE 
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_3  
			| 0x0000000000000005L;
	
}
