package com.imotion.dslam.front.business.desktop.client.presenter;

import com.imotion.dslam.front.business.client.CRONIOBusCommonConstants;

public interface CRONIOBusProjectBasePresenterConstants {
	
	public static final String PROJECT_PRESENTER  = "PROJECT_PRESENTER";
	
	String PROJECT_NAVIGATION_DATA							= "PROJECT_NAVIGATION_DATA";
	String PROJECT_NAVIGATION_DATA_PREFFIX					= PROJECT_NAVIGATION_DATA + CRONIOBusCommonConstants.ELEMENT_SEPARATOR;
	
	String CURRENT_PROJECT_ID								= "CURRENT_PROJECT_ID";
	String CURRENT_MAIN_SECTION_ID							= "CURRENT_MAIN_SECTION_ID";
	String CURRENT_FINAL_SECTION_ID							= "CURRENT_FINAL_SECTION_ID";
	
	String PROJECT_NAVIGATION_DATA_CURRENT_PROJECT_ID		= PROJECT_NAVIGATION_DATA_PREFFIX + CURRENT_PROJECT_ID;
	String PROJECT_NAVIGATION_DATA_CURRENT_MAIN_SECTION_ID	= PROJECT_NAVIGATION_DATA_PREFFIX + CURRENT_MAIN_SECTION_ID;
	String PROJECT_NAVIGATION_DATA_CURRENT_FINAL_SECTION_ID	= PROJECT_NAVIGATION_DATA_PREFFIX + CURRENT_FINAL_SECTION_ID;
	
	
	//PROJECT SECTIONS
	String SECTION_TYPE_PROJECT 		= "SECTION_TYPE_PROJECT";
	String SECTION_TYPE_PROCESS 		= "SECTION_TYPE_PROCESS";
	String SECTION_TYPE_ENVIROMENTS 	= "SECTION_TYPE_ENVIROMENTS";
	String SECTION_TYPE_SCRIPT			= "SECTION_TYPE_SCRIPT"; 
	String SECTION_TYPE_EXECUTION		= "SECTION_TYPE_EXECUTION";
	String SECTION_TYPE_LOG				= "SECTION_TYPE_LOG";
	String SECTION_TYPE_ROOT			= "SECTION_TYPE_ROOT";

}
