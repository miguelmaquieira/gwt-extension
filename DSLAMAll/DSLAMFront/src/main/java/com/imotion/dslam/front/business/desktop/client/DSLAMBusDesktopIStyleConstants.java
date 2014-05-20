package com.imotion.dslam.front.business.desktop.client;

public interface DSLAMBusDesktopIStyleConstants {
	
	String APP_ID_PREFFIX = "dslam_studio-";
	
	//TOOLBAR
	String TOOLBAR								= APP_ID_PREFFIX 	+ "toolbar"; 		//dslam_studio-toolbar
	String TOOLBAR_FILE_ACTIONS 				= TOOLBAR			+ "-file_actions";	//dslam_studio-toolbar-file_actions
	String TOOLBAR_FILE_INFO 					= TOOLBAR			+ "-file_info";		//dslam_studio-toolbar-file_info
	String TOOLBAR_FILE_INFO_FILE_NAME_ZONE 	= TOOLBAR_FILE_INFO	+ "-filename";		//dslam_studio-toolbar-file_info-filename
	String TOOLBAR_FILE_INFO_LAST_SAVED_ZONE 	= TOOLBAR_FILE_INFO	+ "-lastsaved";		//dslam_studio-toolbar-file_info-lastsaved
	String TOOLBAR_FILE_INFO_CLOSE_ZONE 		= TOOLBAR_FILE_INFO	+ "-close";			//dslam_studio-toolbar-file_info-close 
}
