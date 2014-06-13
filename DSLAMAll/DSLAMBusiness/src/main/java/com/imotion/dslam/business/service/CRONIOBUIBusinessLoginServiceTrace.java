package com.imotion.dslam.business.service;

import com.selene.arch.exe.core.envi.trace.AEMFTITraceConstant;

public interface CRONIOBUIBusinessLoginServiceTrace {


	/***********************************************************************
	 *                         TRAZAS DE NIVEL 1                           *
	 ***********************************************************************/

	/**
	 * Message: The LoginSrv is not found and when asked for it, null is returned 
	 */
	public static long CTE_LOGIN_BUSINESS_SERVICE_LOGIN_SESSION_SERVICE_IS_NULL_TRACE = 
			CRONIOBUIBusinessLoginServiceConstants.CTE_BUSINESS_LOGIN_SERVICE_RANGE_TRACE 
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_1  
			| 0x0000000000000001L;


	/***********************************************************************
	 *                         TRAZAS DE NIVEL 3                           *
	 ***********************************************************************/

	/**
	 * Message: Login attempt for username/email: {0}  
	 */
	public static long CTE_LOGIN_BUSINESS_SERVICE_PROCESS_LOGIN_TRACE = 
			CRONIOBUIBusinessLoginServiceConstants.CTE_BUSINESS_LOGIN_SERVICE_RANGE_TRACE  
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_3  
			| 0x0000000000000001L;
	/**
	 * Message: Login result for credentials {0} : {1}
	 */
	public static long CTE_LOGIN_BUSINESS_SERVICE_RESULT_TRACE = 
			CRONIOBUIBusinessLoginServiceConstants.CTE_BUSINESS_LOGIN_SERVICE_RANGE_TRACE  
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_3  
			| 0x0000000000000002L;


	/**
	 * Message: "New user registered: email {0}, firstname  {1}, lastname {2}, registrationId {3} - State: {4} 
	 */
	public static long CTE_LOGIN_BUSINESS_SERVICE_NEW_USER_REGISTERED_ACCEPT_PENDING_TRACE= 
			CRONIOBUIBusinessLoginServiceConstants.CTE_BUSINESS_LOGIN_SERVICE_RANGE_TRACE 
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_3  
			| 0x0000000000000003L;



	/**
	 * Message: "New user registered: email {0} - State: {1}
	 */
	public static long CTE_LOGIN_BUSINESS_SERVICE_NEW_USER_REGISTERED_ACCEPT_SUCCESS_TRACE= 
			CRONIOBUIBusinessLoginServiceConstants.CTE_BUSINESS_LOGIN_SERVICE_RANGE_TRACE 
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_3  
			| 0x0000000000000004L;

	/**
	 * Message: Session validation for session ID: {0}  
	 */
	public static long CTE_LOGIN_BUSINESS_SERVICE_IS_VALID_SESSION_TRACE = 
			CRONIOBUIBusinessLoginServiceConstants.CTE_BUSINESS_LOGIN_SERVICE_RANGE_TRACE 
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_3  
			| 0x0000000000000005L;

	/**
	 * Message: Validation result for Session ID {0} : {1}
	 */
	public static long CTE_LOGIN_BUSINESS_SERVICE_IS_VALID_SESSION_RESULT_TRACE = 
			CRONIOBUIBusinessLoginServiceConstants.CTE_BUSINESS_LOGIN_SERVICE_RANGE_TRACE 
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_3  
			| 0x0000000000000006L;


	/**
	 * Message: New user registration attempt:  email {0}, firstname  {1}, lastname {2}, registrationId {3} - State: {4} 
	 */

	public static long CTE_LOGIN_BUSINESS_SERVICE_NEW_USER_REGISTRATION_PROCESS_TRACE = 
			CRONIOBUIBusinessLoginServiceConstants.CTE_BUSINESS_LOGIN_SERVICE_RANGE_TRACE 
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_3  
			| 0x0000000000000007L;


	/***********************************************************************
	 *                         TRAZAS DE NIVEL 4                           *
	 ***********************************************************************/
	/**
	 * Message: The flow is going to ask for the srvSession for the application {0}
	 */
	public static long CTE_LOGIN_BUSINESS_SERVICE_GET_LOGIN_SESSION_TRACE =
			CRONIOBUIBusinessLoginServiceConstants.CTE_BUSINESS_LOGIN_SERVICE_RANGE_TRACE 
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_4  
			| 0x0000000000000001L;

}
