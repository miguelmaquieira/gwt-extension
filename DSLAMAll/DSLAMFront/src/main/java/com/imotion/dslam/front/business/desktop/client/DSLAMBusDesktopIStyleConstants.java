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

	//FILE LIST
	String FILE_LIST							= APP_ID_PREFFIX 		+ "filelist";
	String FILE_LIST_CONTAINER					= FILE_LIST		 		+ "-container";		//dslam_studio-filelist-container
	String FILE_LIST_HEADER 					= FILE_LIST				+ "-header";		//dslam_studio-filelist-header
	String FILE_LIST_ELEMENT 					= FILE_LIST				+ "-element";		//dslam_studio-filelist-element

	String EDITOR_VIEW 							= APP_ID_PREFFIX	+ "editorView";			//dslam_studio-editorView
	String EDITOR_VIEW_BOTTOM_ZONE 				= EDITOR_VIEW		+ "-bottomZone";		//dslam_studio-editorView-bottomZone
	
}
