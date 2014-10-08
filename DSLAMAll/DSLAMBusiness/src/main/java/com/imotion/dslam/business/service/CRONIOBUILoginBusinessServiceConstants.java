package com.imotion.dslam.business.service;

import com.imotion.dslam.business.CRONIOBUIConstant;
import com.imotion.dslam.business.CRONIOBUIServiceConstant;
import com.selene.arch.base.MFTIConstant;

public interface CRONIOBUILoginBusinessServiceConstants {

	// BusinessLoginService types
	public static long 		CTE_BUSINESS_LOGIN_SERVICE_TYPE 	= CRONIOBUIServiceConstant.CTE_CRONIO_APP_BUS_LOGIN_SERVICE_SUBTYPE | CRONIOBUIConstant.CTE_CRONIO_APP_BUSINESS_SERVICE_TYPE;
	public static String 	CTE_BUSINESS_LOGIN_SERVICE_HEX_TYPE 	= Long.toHexString(CTE_BUSINESS_LOGIN_SERVICE_TYPE);
	public static String 	CTE_BUSINESS_LOGIN_SERVICE_NAME 		= "CRONIOBUIBusinessLoginServiceConstants";

	// Constants
	public static long CTE_BUSINESS_LOGIN_SERVICE_RANGE_TRACE 		= CTE_BUSINESS_LOGIN_SERVICE_TYPE | MFTIConstant.CTE_MFT_RANGE_TRACE;
	public static String BUSINESS_APPLICATION_NAME						= "BUSINESS";
	
	//METHOD_NAMES
	
	//MAILS
	String		EMAIL_SUBJECT_RESET_PWD			= "resetPassword_subject";
	String		EMAIL_BODY_RESET_PWD			= "resetPassword_body";
	String		EMAIL_SUBJECT_ACTIVATE_ACCOUNT	= "activateAccount_subject";
	String		EMAIL_BODY_ACTIVATE_ACCOUNT		= "activateAccount_body";
	String		EMAIL_SUBJECT_ACTIVATE_USER		= "activateUser_subject";
	String		EMAIL_BODY_ACTIVATE_USER		= "activateUser_body";
	String		EMAIL_BODY_GUEST				= "guestEmail_body";
	String		EMAIL_SUBJECT_GUEST				= "guestEmail_subject";
	
	String USER_DATA = "USER_DATA";
}