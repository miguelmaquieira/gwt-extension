package com.imotion.dslam.front.business.desktop.client;

public interface DSLAMBusDesktopIStyleConstants {
	
	String APP_ID_PREFFIX = "dslam_studio-";
	
	//TOOLBAR
	String TOOLBAR										= APP_ID_PREFFIX 			+ "toolbar"; 		//dslam_studio-toolbar
	String TOOLBAR_FILE_ACTIONS 						= TOOLBAR					+ "-file_actions";	//dslam_studio-toolbar-file_actions
	String TOOLBAR_FILE_ACTIONS_ZONE					= TOOLBAR_FILE_ACTIONS		+ "-zone";			//dslam_studio-toolbar-file_actions-zone
	String TOOLBAR_FILE_INFO 							= TOOLBAR					+ "-file_info";		//dslam_studio-toolbar-file_info
	String TOOLBAR_FILE_INFO_FILE_NAME_ZONE 			= TOOLBAR_FILE_INFO			+ "-filename";		//dslam_studio-toolbar-file_info-filename
	String TOOLBAR_FILE_INFO_LAST_SAVED_ZONE 			= TOOLBAR_FILE_INFO			+ "-lastsaved";		//dslam_studio-toolbar-file_info-lastsaved
	String TOOLBAR_FILE_INFO_CLOSE_ZONE 				= TOOLBAR_FILE_INFO			+ "-close";			//dslam_studio-toolbar-file_info-close 
	
	
	//CONNECTION TOOLBAR
	String TOOLBAR_CONNECTION_ZONE 						= APP_ID_PREFFIX			+ "toolbar-connection-zone";			//dslam_studio-toolbar-connection-zone 
	String CONNECTION_TOOLBAR							= APP_ID_PREFFIX 			+ "connection-toolbar"; //dslam_studio-connection-toolbar
	String SERVER_LABEL									= APP_ID_PREFFIX 			+ "server-label"; //dslam_studio-server-label
	String USER_LABEL									= APP_ID_PREFFIX 			+ "user-label"; //dslam_studio-user-label
	String PASSWORD_LABEL								= APP_ID_PREFFIX 			+ "password-label"; //dslam_studio-password-label
	String SERVER_TEXTBOX								= APP_ID_PREFFIX 			+ "server-textbox"; //dslam_studio-server-textbox
	String USER_TEXTBOX									= APP_ID_PREFFIX 			+ "user-textbox"; //dslam_studio-user-textbox
	String PASSWORD_TEXTBOX								= APP_ID_PREFFIX 			+ "password-textbox"; //dslam_studio-password-textbox
	
	//FILE LIST
	String FILE_LIST									= APP_ID_PREFFIX 			+ "filelist";		//dslam_studio-filelist
	String FILE_LIST_CONTAINER							= FILE_LIST		 			+ "-container";		//dslam_studio-filelist-container
	String FILE_LIST_CONTAINER_ZONE 					= FILE_LIST_CONTAINER		+ "-zone";			//dslam_studio-filelist-container-zone
	String FILE_LIST_HEADER 							= FILE_LIST					+ "-header";		//dslam_studio-filelist-header
	String FILE_LIST_ELEMENT 							= FILE_LIST					+ "-element";		//dslam_studio-filelist-element
	String FILE_LIST_ZONE 								= FILE_LIST					+ "-zone";			//dslam_studio-filelist-zone

	//PROCESS LIST
	String PROCESS_LIST									= APP_ID_PREFFIX 			+ "processlist";	//dslam_studio-processlist
	String PROCESS_LIST_CONTAINER						= PROCESS_LIST		 		+ "-container";		//dslam_studio-processlist-container
	String PROCESS_LIST_CONTAINER_ZONE 					= PROCESS_LIST_CONTAINER	+ "-zone";			//dslam_studio-processlist-container-zone
	String PROCESS_LIST_HEADER 							= PROCESS_LIST				+ "-header";		//dslam_studio-processlist-header
	String PROCESS_LIST_ELEMENT 						= PROCESS_LIST				+ "-element";		//dslam_studio-processlist-element
	String PROCESS_LIST_ZONE 							= PROCESS_LIST				+ "-zone";			//dslam_studio-processlist-zone

	String EDITOR_ZONE 									= APP_ID_PREFFIX			+ "editorZone";		//dslam_studio-editorZone
	
	String NEW_SCRIPT_FORM 								= APP_ID_PREFFIX			+ "newScriptForm";	//dslam_studio-newScriptForm
	String NEW_SCRIPT_FORM_INPUT_ZONE 					= NEW_SCRIPT_FORM			+ "-inputZone";		//dslam_studio-newScriptForm-inputZone
	String NEW_SCRIPT_FORM_SAVE_ZONE 					= NEW_SCRIPT_FORM			+ "-saveZone";		//dslam_studio-newScriptForm-saveZone

	
	String EDITOR_VIEW 									= APP_ID_PREFFIX			+ "editorView";		//dslam_studio-editorView
	String EDITOR_VIEW_BOTTOM_ZONE 						= EDITOR_VIEW				+ "-bottomZone";	//dslam_studio-editorView-bottomZone
	String PROCESSES_VIEW 								= APP_ID_PREFFIX			+ "processesView";	//dslam_studio-processesView
	String PROCESSES_VIEW_BOTTOM_ZONE 					= PROCESSES_VIEW			+ "-bottomZone";	//dslam_studio-processesView-bottomZone
	
	//PROCESS OPTIONS
	String PROCESS_CONFIGURE_ZONE 						= APP_ID_PREFFIX			+ "processConfigureZone"; 						//dslam_studio-processConfigureZone
	String PROCESS_CONFIGURE 							= APP_ID_PREFFIX			+ "processConfigure"; 							//dslam_studio-processConfigure
	String PROCESS_CONFIGURE_OPTIONS_ZONE 				= APP_ID_PREFFIX			+ "processConfigureOptionsZone";				//dslam_studio-processConfigureOptionsZone
	String PROCESS_CONFIGURE_OPTIONS					= APP_ID_PREFFIX			+ "processConfigureOptions"; 					//dslam_studio-processConfigureOptions
		
	String PROCESS_CONFIGURE_OPTIONS_PROPERTIES_ZONE	= APP_ID_PREFFIX			+ "processConfigureOptionsPropertiesZone";		//dslam_studio-processConfigureOptionsPropertiesZone
	String PROCESS_CONFIGURE_OPTIONS_PROPERTIES			= APP_ID_PREFFIX			+ "processConfigureOptionsProperties";			//dslam_studio-processConfigureOptionsProperties
	String PROCESS_CONFIGURE_OPTIONS_PROPERTIES_HEADER	= APP_ID_PREFFIX			+ "processConfigureOptionsPropertiesHeader";	//dslam_studio-processConfigureOptionsPropertiesHeader
	String PROCESS_CONFIGURE_OPTIONS_PROPERTIES_CHECKBOX= APP_ID_PREFFIX			+ "processConfigureOptionsPropertiesCheckBox";	//dslam_studio-processConfigureOptionsPropertiesCheckBox
	String PROCESS_CONFIGURE_OPTIONS_VARIABLES_ZONE		= APP_ID_PREFFIX			+ "processConfigureOptionsVariablesZone";		//dslam_studio-processConfigureOptionsvariablesZone
	String PROCESS_CONFIGURE_OPTIONS_VARIABLES			= APP_ID_PREFFIX			+ "processConfigureOptionsVariables";			//dslam_studio-processConfigureOptionsVariables
	String PROCESS_CONFIGURE_OPTIONS_VARIABLES_HEADER	= APP_ID_PREFFIX			+ "processConfigureOptionsVariablesHeader"; 	//dslam_studio-processConfigureOptionsVariablesHeader
	String PROCESS_CONFIGURE_OPTIONS_SCHEDULE_ZONE		= APP_ID_PREFFIX			+ "processConfigureOptionsScheduleZone";		//dslam_studio-processConfigureOptionsScheduleZone
	String PROCESS_CONFIGURE_OPTIONS_SCHEDULE			= APP_ID_PREFFIX			+ "processConfigureOptionsSchedule";			//dslam_studio-processConfigureOptionsSchedule
	String PROCESS_CONFIGURE_OPTIONS_SCHEDULE_HEADER	= APP_ID_PREFFIX			+ "processConfigureOptionsScheduleHeader";  	//dslam_studio-processConfigureOptionsScheduleHeader
	String PROCESS_CONFIGURE_NODES_ZONE 				= APP_ID_PREFFIX			+ "processConfigureNodesZone"; 					//dslam_studio-processConfigureNodeZone
	String PROCESS_CONFIGURE_NODES 						= APP_ID_PREFFIX			+ "processConfigureNodes"; 						//dslam_studio-processConfigureNode
	String PROCESS_CONFIGURE_NODES_SELECTION_ZONE 		= APP_ID_PREFFIX			+ "processConfigureNodesSelectionZone";			//dslam_studio-processConfigureNodesSelectionZone
	String PROCESS_CONFIGURE_NODES_PROPERTIES_ZONE 		= APP_ID_PREFFIX			+ "processConfigureNodesPropertiesZone";		//dslam_studio-processConfigureNodesPropertiesZone
	String PROCESS_CONFIGURE_NODES_PROPERTIES 			= APP_ID_PREFFIX			+ "processConfigureNodesProperties";			//dslam_studio-processConfigureNodesProperties
	String PROCESS_CONFIGURE_NODES_PROPERTIES_HEADER 	= APP_ID_PREFFIX			+ "processConfigureNodesPropertiesHeader";		//dslam_studio-processConfigureNodesPropertiesHeader
	String PROCESS_CONFIGURE_NODES_SELECTION 			= APP_ID_PREFFIX			+ "processConfigureNodesSelection";				//dslam_studio-processConfigureNodesSelection
	String PROCESS_CONFIGURE_NODES_SELECTION_HEADER 	= APP_ID_PREFFIX			+ "processConfigureNodesSelectionHeader";		//dslam_studio-processConfigureNodesSelectionHeader

	String POPUP_VARIABLES_FORM_CONTAINER				= APP_ID_PREFFIX			+ "processConfigureOptionsVariablesFormContainer";		//dslam_studio-processConfigureOptionsVariablesFormContainer
	String SUBMIT_BUTTON_VARIABLES_FORM					= APP_ID_PREFFIX			+ "processConfigureOptionsVariablesFormSubmitButton";		//dslam_studio-processConfigureOptionsVariablesFormSubmitButton
}
