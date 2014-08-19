package com.imotion.dslam.front.business.desktop.client.presenter;

import com.imotion.dslam.front.business.client.DSLAMBusCommonConstants;


public interface CRONIOBusPreferencesBasePresenterConstants {
	
	public static final String PREFERENCES_PRESENTER  = "PREFERENCES_PRESENTER";
	
	String PREFERENCES_NAVIGATION_DATA						= "PREFERENCES_NAVIGATION_DATA";
	String SECTION_DATA										= "SECTION_DATA";
	String PREFERENCES_NAVIGATION_DATA_PREFFIX				= PREFERENCES_NAVIGATION_DATA + DSLAMBusCommonConstants.ELEMENT_SEPARATOR;
//	
//	String CURRENT_PROJECT_ID								= "CURRENT_PROJECT_ID";
//	String CURRENT_MAIN_SECTION_ID							= "CURRENT_MAIN_SECTION_ID";
	String CURRENT_FINAL_SECTION_ID							= "CURRENT_FINAL_SECTION_ID";
//	
//	String PROJECT_NAVIGATION_DATA_CURRENT_PROJECT_ID		= PROJECT_NAVIGATION_DATA_PREFFIX + CURRENT_PROJECT_ID;
//	String PROJECT_NAVIGATION_DATA_CURRENT_MAIN_SECTION_ID	= PROJECT_NAVIGATION_DATA_PREFFIX + CURRENT_MAIN_SECTION_ID;
	String PREFERENCES_NAVIGATION_DATA_CURRENT_FINAL_SECTION_ID	= PREFERENCES_NAVIGATION_DATA_PREFFIX + CURRENT_FINAL_SECTION_ID;
	
	
	//PREFERENCES SECTIONS
	String SECTION_TYPE_MACHINE_PREFERENCES 	= "SECTION_TYPE_MACHINE_PREFERENCES";
	String SECTION_TYPE_USER_PREFERENCES 		= "SECTION_TYPE_USER_PREFERENCES";
	String SECTION_TYPE_ROOT 	= "SECTION_TYPE_ROOT";
}
