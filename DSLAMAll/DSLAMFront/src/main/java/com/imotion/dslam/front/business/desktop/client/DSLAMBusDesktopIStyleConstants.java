package com.imotion.dslam.front.business.desktop.client;

public interface DSLAMBusDesktopIStyleConstants {
	
	String APP_ID_PREFFIX = "dslam_studio-";
	
	//TOOLBAR
	String TOOLBAR								= APP_ID_PREFFIX 			+ "toolbar"; 		//dslam_studio-toolbar
	String TOOLBAR_FILE_ACTIONS 				= TOOLBAR					+ "-file_actions";	//dslam_studio-toolbar-file_actions
	String TOOLBAR_FILE_ACTIONS_ZONE			= TOOLBAR_FILE_ACTIONS		+ "-zone";			//dslam_studio-toolbar-file_actions-zone
	String TOOLBAR_FILE_INFO 					= TOOLBAR					+ "-file_info";		//dslam_studio-toolbar-file_info
	String TOOLBAR_FILE_INFO_FILE_NAME_ZONE 	= TOOLBAR_FILE_INFO			+ "-filename";		//dslam_studio-toolbar-file_info-filename
	String TOOLBAR_FILE_INFO_LAST_SAVED_ZONE 	= TOOLBAR_FILE_INFO			+ "-lastsaved";		//dslam_studio-toolbar-file_info-lastsaved
	String TOOLBAR_FILE_INFO_CLOSE_ZONE 		= TOOLBAR_FILE_INFO			+ "-close";			//dslam_studio-toolbar-file_info-close 

	//FILE LIST
	String FILE_LIST							= APP_ID_PREFFIX 			+ "filelist";		//dslam_studio-filelist
	String FILE_LIST_CONTAINER					= FILE_LIST		 			+ "-container";		//dslam_studio-filelist-container
	String FILE_LIST_CONTAINER_ZONE 			= FILE_LIST_CONTAINER		+ "-zone";			//dslam_studio-filelist-container-zone
	String FILE_LIST_HEADER 					= FILE_LIST					+ "-header";		//dslam_studio-filelist-header
	String FILE_LIST_ELEMENT 					= FILE_LIST					+ "-element";		//dslam_studio-filelist-element
	String FILE_LIST_ZONE 						= FILE_LIST					+ "-zone";			//dslam_studio-filelist-zone

	//PROCESS LIST
	String PROCESS_LIST							= APP_ID_PREFFIX 			+ "processlist";	//dslam_studio-processlist
	String PROCESS_LIST_CONTAINER				= PROCESS_LIST		 		+ "-container";		//dslam_studio-processlist-container
	String PROCESS_LIST_CONTAINER_ZONE 			= PROCESS_LIST_CONTAINER	+ "-zone";			//dslam_studio-processlist-container-zone
	String PROCESS_LIST_HEADER 					= PROCESS_LIST				+ "-header";		//dslam_studio-processlist-header
	String PROCESS_LIST_ELEMENT 				= PROCESS_LIST				+ "-element";		//dslam_studio-processlist-element
	String PROCESS_LIST_ZONE 					= PROCESS_LIST				+ "-zone";			//dslam_studio-processlist-zone

	String EDITOR_ZONE 							= APP_ID_PREFFIX			+ "editorZone";		//dslam_studio-editorZone
	String PROCESS_ZONE 						= APP_ID_PREFFIX			+ "processZone"; 	//dslam_studio-processZone

	String NEW_SCRIPT_FORM 						= APP_ID_PREFFIX			+ "newScriptForm";	//dslam_studio-newScriptForm
	String NEW_SCRIPT_FORM_INPUT_ZONE 			= NEW_SCRIPT_FORM			+ "-inputZone";		//dslam_studio-newScriptForm-inputZone
	String NEW_SCRIPT_FORM_SAVE_ZONE 			= NEW_SCRIPT_FORM			+ "-saveZone";		//dslam_studio-newScriptForm-saveZone

	
	String EDITOR_VIEW 							= APP_ID_PREFFIX			+ "editorView";		//dslam_studio-editorView
	String EDITOR_VIEW_BOTTOM_ZONE 				= EDITOR_VIEW				+ "-bottomZone";	//dslam_studio-editorView-bottomZone
	String PROCESSES_VIEW 						= APP_ID_PREFFIX			+ "processesView";	//dslam_studio-processesView
	String PROCESSES_VIEW_BOTTOM_ZONE 			= PROCESSES_VIEW			+ "-bottomZone";	//dslam_studio-processesView-bottomZone
	
	
}
