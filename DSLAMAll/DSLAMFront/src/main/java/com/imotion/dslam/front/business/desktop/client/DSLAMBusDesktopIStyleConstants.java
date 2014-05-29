package com.imotion.dslam.front.business.desktop.client;

public interface DSLAMBusDesktopIStyleConstants {
	
	String APP_ID_PREFFIX = "dslam_studio-";
	
	//TOOLBAR

	String TOOLBAR										= APP_ID_PREFFIX 		+ "toolbar"; 			//dslam_studio-toolbar
	String TOOLBAR_ACTIONS 								= TOOLBAR				+ "-actions";			//dslam_studio-toolbar-actions
	String TOOLBAR_ACTIONS_ZONE							= TOOLBAR_ACTIONS		+ "-zone";				//dslam_studio-toolbar-actions-zone
	String TOOLBAR_INFO 								= TOOLBAR				+ "-info";				//dslam_studio-toolbar-info
	String TOOLBAR_INFO_LAST_SAVED_ZONE 				= TOOLBAR_INFO			+ "-lastsaved";			//dslam_studio-toolbar-info-lastsaved
	String TOOLBAR_INFO_CLOSE_ZONE 						= TOOLBAR_INFO			+ "-close";				//dslam_studio-toolbar-info-close 
	String TOOLBAR_INFO_TITLE_ZONE 						= TOOLBAR_INFO			+ "-titleZone";			//dslam_studio-toolbar-info-titleZone
	String TOOLBAR_INFO_TITLE_ZONE_MAIN_TITLE 			= TOOLBAR_INFO_TITLE_ZONE + "-mainTitle";		//dslam_studio-toolbar-info-titleZone-mainTitle
	String TOOLBAR_INFO_TITLE_ZONE_SEPARATOR 			= TOOLBAR_INFO_TITLE_ZONE + "-separator";		//dslam_studio-toolbar-info-titleZone-separator
	String TOOLBAR_INFO_TITLE_ZONE_SECONDARY_TITLE 		= TOOLBAR_INFO_TITLE_ZONE + "-secondaryTitle";	//dslam_studio-toolbar-info-titleZone-secondaryTitle
	
	//CONNECTION TOOLBAR
	String TOOLBAR_CONNECTION_ZONE 						= APP_ID_PREFFIX			+ "toolbar-connection-zone";	//dslam_studio-toolbar-connection-zone 
	String CONNECTION_TOOLBAR							= APP_ID_PREFFIX 			+ "connection-toolbar"; 		//dslam_studio-connection-toolbar
	String SERVER_LABEL									= APP_ID_PREFFIX 			+ "server-label"; 				//dslam_studio-server-label
	String USER_LABEL									= APP_ID_PREFFIX 			+ "user-label"; 				//dslam_studio-user-label
	String PASSWORD_LABEL								= APP_ID_PREFFIX 			+ "password-label"; 			//dslam_studio-password-label
	String SERVER_TEXTBOX								= APP_ID_PREFFIX 			+ "server-textbox"; 			//dslam_studio-server-textbox
	String USER_TEXTBOX									= APP_ID_PREFFIX 			+ "user-textbox"; 				//dslam_studio-user-textbox
	String PASSWORD_TEXTBOX								= APP_ID_PREFFIX 			+ "password-textbox"; 			//dslam_studio-password-textbox
	
	//LIST
	String LIST											= APP_ID_PREFFIX 		+ "list";				//dslam_studio-list
	String LIST_CONTAINER								= LIST		 			+ "-container";			//dslam_studio-list-container
	String LIST_CONTAINER_ZONE 							= LIST_CONTAINER		+ "-zone";				//dslam_studio-list-container-zone
	String LIST_HEADER 									= LIST					+ "-header";			//dslam_studio-list-header
	String LIST_ELEMENT 								= LIST					+ "-element";			//dslam_studio-list-element
	String LIST_ZONE 									= LIST					+ "-zone";				//dslam_studio-list-zone
	String FILE_LIST_ELEMENT 							= APP_ID_PREFFIX		+ "fileListElement";	//dslam_studio-fileListElement
	
	
	//PROCESS LIST
	String PROCESS_LIST									= APP_ID_PREFFIX 			+ "processlist";	//dslam_studio-processlist
	String PROCESS_LIST_CONTAINER						= PROCESS_LIST		 		+ "-container";		//dslam_studio-processlist-container
	String PROCESS_LIST_CONTAINER_ZONE 					= PROCESS_LIST_CONTAINER	+ "-zone";			//dslam_studio-processlist-container-zone
	String PROCESS_LIST_HEADER 							= PROCESS_LIST				+ "-header";		//dslam_studio-processlist-header
	String PROCESS_LIST_ELEMENT 						= PROCESS_LIST				+ "-element";		//dslam_studio-processlist-element
	String PROCESS_LIST_ZONE 							= PROCESS_LIST				+ "-zone";			//dslam_studio-processlist-zone

	String EDITOR_ZONE 									= APP_ID_PREFFIX			+ "editorZone";		//dslam_studio-editorZone
	String PROCESS_ZONE 								= APP_ID_PREFFIX			+ "processZone"; 	//dslam_studio-processZone

	String NEW_SCRIPT_FORM 								= APP_ID_PREFFIX			+ "newScriptForm";	//dslam_studio-newScriptForm
	String NEW_SCRIPT_FORM_ERROR_ZONE 					= NEW_SCRIPT_FORM			+ "-errorZone";		//dslam_studio-newScriptForm-errorZone
	String NEW_SCRIPT_FORM_INPUT_ZONE 					= NEW_SCRIPT_FORM			+ "-inputZone";		//dslam_studio-newScriptForm-inputZone
	String NEW_SCRIPT_FORM_SAVE_ZONE 					= NEW_SCRIPT_FORM			+ "-saveZone";		//dslam_studio-newScriptForm-saveZone

	//EDITOR VIEW
	String EDITOR_VIEW 									= APP_ID_PREFFIX			+ "editorView";		//dslam_studio-editorView
	String EDITOR_VIEW_BOTTOM_ZONE 						= EDITOR_VIEW				+ "-bottomZone";	//dslam_studio-editorView-bottomZone
	String EDITOR_VIEW_FILE_NAME 						= EDITOR_VIEW				+ "-filename";		//dslam_studio-editorView-filename
	String EDITOR_VIEW_CONTENT_TYPE						= EDITOR_VIEW				+ "-contentType";	//dslam_studio-editorView-contentType
	
	//PROCESSES VIEW
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
	String PROCESS_CONFIGURE_OPTIONS_VARIABLES_LIST		= APP_ID_PREFFIX			+ "processConfigureOptionsVariablesList";		//dslam_studio-processConfigureOptionsVariablesList
	String PROCESS_CONFIGURE_OPTIONS_SCHEDULE_ZONE		= APP_ID_PREFFIX			+ "processConfigureOptionsScheduleZone";		//dslam_studio-processConfigureOptionsScheduleZone
	String PROCESS_CONFIGURE_OPTIONS_SCHEDULE			= APP_ID_PREFFIX			+ "processConfigureOptionsSchedule";			//dslam_studio-processConfigureOptionsSchedule
	String PROCESS_CONFIGURE_OPTIONS_SCHEDULE_HEADER	= APP_ID_PREFFIX			+ "processConfigureOptionsScheduleHeader"; 		//dslam_studio-processConfigureOptionsScheduleHeader
	String PROCESS_CONFIGURE_OPTIONS_SCHEDULE_LIST		= APP_ID_PREFFIX			+ "processConfigureOptionsScheduleList";		//dslam_studio-processConfigureOptionsScheduleList
	String PROCESS_CONFIGURE_OPTIONS_SCHEDULE_LINE		= APP_ID_PREFFIX			+ "processConfigureOptionsScheduleLine";		//dslam_studio-processConfigureOptionsScheduleLine
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
