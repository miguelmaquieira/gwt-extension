package com.imotion.dslam.front.business.desktop.client;

public interface CRONIOBusDesktopIStyleConstants {

	String APP_ID_PREFFIX = "chronium_studio-";

	//COMMON
	String PASSWORD_TEXTBOX 							= APP_ID_PREFFIX			+ "passwordTextBox";			//chronium_studio-passwordTextBox
	String PASSWORD_CHECKBOX 							= APP_ID_PREFFIX			+ "passwordCheckBox";			//chronium_studio-passwordCheckBox
	
	//TOOLBAR
	String TOOLBAR										= APP_ID_PREFFIX 			+ "toolbar"; 					//chronium_studio-toolbar
	String TOOLBAR_ACTIONS 								= TOOLBAR					+ "-actions";					//chronium_studio-toolbar-actions
	String TOOLBAR_ACTIONS_PREFERENCES_BUTTON			= TOOLBAR					+ "-actions-preferencesButton";	//chronium_studio-toolbar-actions-preferencesButton
	String TOOLBAR_ACTIONS_OPTIONS_BUTTON				= TOOLBAR					+ "-actions-optionsButton";		//chronium_studio-toolbar-actions-optionsButton
	String TOOLBAR_ACTIONS_CLOSE_BUTTON					= TOOLBAR					+ "-actions-closeButton";		//chronium_studio-toolbar-actions-closeButton
	String TOOLBAR_ACTIONS_ZONE							= TOOLBAR_ACTIONS			+ "-zone";						//chronium_studio-toolbar-actions-zone
	String TOOLBAR_INFO 								= TOOLBAR					+ "-info";						//chronium_studio-toolbar-info
	String TOOLBAR_INFO_ZONE 							= TOOLBAR_INFO				+ "-zone";						//chronium_studio-toolbar-info-zone
	String TOOLBAR_INFO_LAST_SAVED_ZONE 				= TOOLBAR_INFO				+ "-lastsaved";					//chronium_studio-toolbar-info-lastsaved
	String TOOLBAR_INFO_CLOSE_ZONE 						= TOOLBAR_INFO				+ "-close";						//chronium_studio-toolbar-info-close 
	String TOOLBAR_INFO_TITLE_ZONE 						= TOOLBAR_INFO				+ "-titleZone";					//chronium_studio-toolbar-info-titleZone
	String TOOLBAR_INFO_TITLE_ZONE_MAIN_TITLE 			= TOOLBAR_INFO_TITLE_ZONE 	+ "-mainTitle";					//chronium_studio-toolbar-info-titleZone-mainTitle
	String TOOLBAR_INFO_TITLE_ZONE_SEPARATOR 			= TOOLBAR_INFO_TITLE_ZONE 	+ "-separator";					//chronium_studio-toolbar-info-titleZone-separator
	String TOOLBAR_INFO_TITLE_ZONE_SECONDARY_TITLE 		= TOOLBAR_INFO_TITLE_ZONE 	+ "-secondaryTitle";			//chronium_studio-toolbar-info-titleZone-secondaryTitle

	//Project Navigator
	String LIST											= APP_ID_PREFFIX 			+ "list";						//chronium_studio-list
	String LIST_CONTAINER								= LIST		 				+ "-container";					//chronium_studio-list-container
	String LIST_CONTAINER_ZONE 							= LIST_CONTAINER			+ "-zone";						//chronium_studio-list-container-zone

	String NODE_LIST									= APP_ID_PREFFIX 			+ "nodeList";					//chronium_studio-nodeList		
	String NODE_LIST_CONTAINER							= NODE_LIST		 			+ "-container";					//chronium_studio-nodeList-container
	String NODE_LIST_CONTAINER_ZONE 					= LIST_CONTAINER			+ "-zone";						//chronium_studio-nodeList-container-zone
	String NODE_LIST_ADD_ELEMENT						= NODE_LIST					+ "-addElement"; 				//chronium_studio-nodeList-addElement
	String BOOTSTRAP_TREEMENU_ITEM_LEVEL3				= APP_ID_PREFFIX			+ "treeMenuItem-level3"; 		//chronium_studio-treeMenuItem-level3	
	
	//PROJECT FORM
	String NEW_PROJECT_FORM 							= APP_ID_PREFFIX			+ "newProjectForm";				//chronium_studio-newProjectForm
	String NEW_PROJECT_FORM_ERROR_ZONE 					= NEW_PROJECT_FORM			+ "-errorZone";					//chronium_studio-newProjectForm-errorZone
	String NEW_PROJECT_FORM_INPUT_ZONE 					= NEW_PROJECT_FORM			+ "-inputZone";					//chronium_studio-newProjectForm-inputZone
	String NEW_PROJECT_FORM_SAVE_ZONE 					= NEW_PROJECT_FORM			+ "-saveZone";					//chronium_studio-newProjectForm-saveZone

	//EXECUTE FORM
	String EXECUTE_FORM 								= APP_ID_PREFFIX			+ "executeForm";				//chronium_studio-executeForm
	String EXECUTE_FORM_INPUT_ZONE 						= EXECUTE_FORM				+ "-inputZone";					//chronium_studio-executeForm-inputZone
	String EXECUTE_FORM_SAVE_ZONE 						= EXECUTE_FORM				+ "-saveZone";					//chronium_studio-executeForm-saveZone
	
	//CONNECTION FORM
	String NEW_CONNECTION_FORM 							= APP_ID_PREFFIX			+ "newConnectionForm";			//chronium_studio-newConnectionForm
	String NEW_CONNECTION_FORM_ERROR_ZONE 				= NEW_CONNECTION_FORM		+ "-errorZone";					//chronium_studio-newConnectionForm-errorZone
	String NEW_CONNECTION_FORM_INPUT_ZONE 				= NEW_CONNECTION_FORM		+ "-inputZone";					//chronium_studio-newConnectionForm-inputZone
	String NEW_CONNECTION_FORM_SAVE_ZONE 				= NEW_CONNECTION_FORM		+ "-saveZone";					//chronium_studio-newConnectionForm-saveZone
	
	//PROJECT LAYOUT
	String PROJECTS_LAYOUT 											= APP_ID_PREFFIX								+ "projectsLayout";		//chronium_studio-projectsLayout
	String PROJECTS_LAYOUT_LIST_ZONE 								= PROJECTS_LAYOUT								+ "-listZone";			//chronium_studio-projectsLayout-listZone
	String PROJECTS_LAYOUT_WORK_ZONE 								= PROJECTS_LAYOUT								+ "-projectWorkZone"; 	//chronium_studio-projectsLayout-projectWorkZone
	String PROJECTS_LAYOUT_WORK_ZONE_HEADER							= PROJECTS_LAYOUT_WORK_ZONE						+ "-header";			//chronium_studio-projectsLayout-projectWorkZone-header
	String PROJECTS_LAYOUT_WORK_ZONE_HEADER_ACTIONS_ZONE 			= PROJECTS_LAYOUT_WORK_ZONE_HEADER				+ "-actionsZone";		//chronium_studio-projectsLayout-projectWorkZone-header-actionsZone
	String PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE 				= PROJECTS_LAYOUT_WORK_ZONE_HEADER				+ "-infoZone";			//chronium_studio-projectsLayout-projectWorkZone-header-infoZone
	String PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_MOD_INDICATOR = PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE	+ "-modIndicator";		//chronium_studio-projectsLayout-projectWorkZone-header-infoZone-modIndicator
	String PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_SECTION_NAME 	= PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE	+ "-sectionName";		//chronium_studio-projectsLayout-projectWorkZone-header-infoZone-sectionName
	String PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_SEPARATOR 	= PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE	+ "-separator";			//chronium_studio-projectsLayout-projectWorkZone-header-infoZone-separator
	String PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_PROJECT_NAME 	= PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE	+ "-projectName";		//chronium_studio-projectsLayout-projectWorkZone-header-infoZone-projectName
	String PROJECTS_LAYOUT_BOTTOM_ZONE 								= PROJECTS_LAYOUT								+ "-bottomZone";		//chronium_studio-projectsLayout-bottomZone
	String PROJECTS_LAYOUT_BOTTOM_ZONE_RIGHT 						= PROJECTS_LAYOUT_BOTTOM_ZONE					+ "-right";				//chronium_studio-projectsLayout-bottomZone-right
	String PROJECTS_LAYOUT_ZONE_HEADER 								= PROJECTS_LAYOUT								+ "-zoneHeader";		//chronium_studio-projectsLayout-zoneHeader
	String PROJECTS_LAYOUT_CONTENT_IN_BOX							= PROJECTS_LAYOUT								+ "-contentInBox ";		//chronium_studio-projectsLayout-contentInBox 
	
	// PROCESS --> NODES
	String PROJECT_PROCESS_NODE_INFO_LABEL_ZONE						= APP_ID_PREFFIX + "projectProcess-nodeInfo-LabelZone";					//chronium_studio-projectProcess-nodeInfo-LabelZone
	String PROJECT_PROCESS_NODE_INFO_LABEL							= APP_ID_PREFFIX + "projectProcess-nodeInfo-Label";						//chronium_studio-projectProcess-nodeInfo-Label
	String PROJECT_PROCESS_NODE_INFO_CONTENT_LABEL					= APP_ID_PREFFIX + "projectProcess-nodeInfo-contentLabel";				//chronium_studio-projectProcess-nodeInfo-contentLabel
	
	//PREFERENCES LAYOUT
	String PREFERENCES_LAYOUT 												= APP_ID_PREFFIX								+ "preferencesLayout";	//chronium_studio-preferencesLayout
	String PREFERENCES_LAYOUT_MENU		 									= PREFERENCES_LAYOUT							+ "-menu";				//chronium_studio-preferencesLayout-menu
	String PREFERENCES_LAYOUT_MENU_ZONE 									= PREFERENCES_LAYOUT_MENU						+ "Zone";				//chronium_studio-preferencesLayout-menuZone
	String PREFERENCES_LAYOUT_WORK_ZONE  									= PREFERENCES_LAYOUT							+ "-workZone";			//chronium_studio-preferencesLayout-workZone
	String PREFERENCES_LAYOUT_WORK_ZONE_HEADER								= PREFERENCES_LAYOUT_WORK_ZONE					+ "-header";			//chronium_studio-preferencesLayout-workZone-header
	String PREFERENCES_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE 					= PREFERENCES_LAYOUT_WORK_ZONE_HEADER			+ "-infoZone";			//chronium_studio-preferencesLayout-workZone-header-infoZone
	String PREFERENCES_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_MOD_INDICATOR 		= PREFERENCES_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE	+ "-modIndicator";		//chronium_studio-preferencesLayout-workZone-header-infoZone-modIndicator
	String PREFERENCES_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_SECTION_NAME 		= PREFERENCES_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE	+ "-sectionName";		//chronium_studio-preferencesLayout-workZone-header-infoZone-sectionName
	String PREFERENCES_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_SEPARATOR 			= PREFERENCES_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE	+ "-separator";			//chronium_studio-preferencesLayout-workZone-header-infoZone-separator
	String PREFERENCES_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_PREFERENCES_NAME 	= PREFERENCES_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE	+ "-preferencesName";	//chronium_studio-preferencesLayout-workZone-header-infoZone-preferencesName
	String PREFERENCES_LAYOUT_BOTTOM_ZONE 									= PREFERENCES_LAYOUT							+ "-bottomZone";		//chronium_studio-preferencesLayout-bottomZone
	String PREFERENCES_LAYOUT_BOTTOM_ZONE_RIGHT  							= PREFERENCES_LAYOUT_BOTTOM_ZONE				+ "-right";				//chronium_studio-preferencesLayout-bottomZone-right
	String PREFERENCES_LAYOUT_ZONE_HEADER 									= PREFERENCES_LAYOUT							+ "-zoneHeader";		//chronium_studio-preferencesLayout-zoneHeader
	String PREFERENCES_LAYOUT_CONTENT_IN_BOX								= PREFERENCES_LAYOUT							+ "-contentInBox ";		//chronium_studio-preferencesLayout-contentInBox 	

	//SCRITPS EDITOR
	String SCRIPTS_EDITOR_CONTAINER						= APP_ID_PREFFIX		+ "scriptsEditorContainer";				//chronium_studio-scriptsEditorContainer
	String SCRIPTS_EDITOR_AREA 							= APP_ID_PREFFIX		+ "scriptsEditorArea";					//chronium_studio-scriptsEditorArea

	//HEADER WITH ACTIONS
	String HEADER_ACTIONS								= APP_ID_PREFFIX 		+ "header-actions"; 					//chronium_studio-header-actions
	String HEADER_ACTIONS_WITH_LABEL_BUTTON				= APP_ID_PREFFIX 		+ "header-actions-with-label-button"; 	//chronium_studio-header-actions-with-label-button
	
	//PROCESS VIEW
	String PROCESS_VIEW 								= APP_ID_PREFFIX			+ "processView";								//chronium_studio-processView
	String PROCESS_ZONE 								= APP_ID_PREFFIX			+ "processZone"; 								//chronium_studio-processZone
	String PROCESS_CONFIGURE_DECKPANEL 					= APP_ID_PREFFIX			+ "processConfigureDeckPanel"; 					//chronium_studio-processConfigureDeckPanel
	String PROCESS_CONFIGURE 							= APP_ID_PREFFIX			+ "processConfigure"; 							//chronium_studio-processConfigure
	String PROCESS_CONFIGURE_ZONE 						= APP_ID_PREFFIX			+ "processConfigureZone";						//chronium_studio-processConfigureZone
	String PROCESS_CONFIGURE_PROPERTIES_CHECKBOX		= APP_ID_PREFFIX			+ "processConfigurePropertiesCheckBox";			//chronium_studio-processConfigurePropertiesCheckBox
	String PROCESS_CONFIGURE_VARIABLES_LIST				= APP_ID_PREFFIX			+ "processConfigureVariablesList";				//chronium_studio-processConfigureVariablesList
	String PROCESS_CONFIGURE_SCHEDULE_LIST				= APP_ID_PREFFIX			+ "processConfigureScheduleList";				//chronium_studio-processConfigureScheduleList
	String PROCESS_CONFIGURE_SCHEDULE_LINE				= APP_ID_PREFFIX			+ "processConfigureScheduleLine";				//chronium_studio-processConfigureScheduleLine
	String PROCESS_CONFIGURE_NODES_ZONE 				= APP_ID_PREFFIX			+ "processConfigureNodesZone"; 					//chronium_studio-processConfigureNodeZone
	String PROCESS_CONFIGURE_NODES 						= APP_ID_PREFFIX			+ "processConfigureNodes"; 						//chronium_studio-processConfigureNode
	String PROCESS_CONFIGURE_NODES_LIST_ZONE 			= APP_ID_PREFFIX			+ "processConfigureNodesListZone";				//chronium_studio-processConfigureNodesListZone
	String PROCESS_CONFIGURE_NODES_LEFT_ZONE 			= APP_ID_PREFFIX			+ "processConfigureNodesLeftZone";				//chronium_studio-processConfigureNodesLeftZone
	String PROCESS_CONFIGURE_NODES_RIGHT_ZONE 			= APP_ID_PREFFIX			+ "processConfigureNodesRightZone";				//chronium_studio-processConfigureNodesRightZone
	String PROCESS_CONFIGURE_NODES_INFO_ZONE 			= APP_ID_PREFFIX			+ "processConfigureNodesInfoZone";				//chronium_studio-processConfigureNodesInfoZone
	String PROCESS_CONFIGURE_NODES_INFO 				= APP_ID_PREFFIX			+ "processConfigureNodesInfo";					//chronium_studio-processConfigureNodesInfo
	String PROCESS_CONFIGURE_NODES_LIST 				= APP_ID_PREFFIX			+ "processConfigureNodesList";					//chronium_studio-processConfigureNodesList
	String PROCESS_CONFIGURE_NODE_VARIABLES_LIST        = APP_ID_PREFFIX			+ "processConfigureNodesVariableList";			//chronium_studio-processConfigureNodesVariableList
	String PROCESS_CONFIGURE_ADD_NODE_VARIABLES         = APP_ID_PREFFIX			+ "processConfigureAddNodeVariables";			//chronium_studio-processConfigureAddNodeVariables
	String POPUP_VARIABLES_FORM_CONTAINER				= APP_ID_PREFFIX			+ "processConfigureVariablesFormContainer";		//chronium_studio-processConfigureVariablesFormContainer
	String POPUP_NODES_FORM_CONTAINER					= APP_ID_PREFFIX			+ "processAddNodeFormContainer";				//chronium_studio-processAddNodeFormContainer
	String POPUP_NODE_LIST_FORM_CONTAINER				= APP_ID_PREFFIX			+ "processAddNodeListFormContainer";			//chronium_studio-processAddNodeListFormContainer
	String POPUP_SCHEDULE_FORM_CONTAINER				= APP_ID_PREFFIX			+ "processConfigureScheduleFormContainer";		//chronium_studio-processConfigureScheduleFormContainer
	String POPUP_SCHEDULE_FORM_SAVE_ZONE				= APP_ID_PREFFIX			+ "processConfigureScheduleFormSaveZone";		//chronium_studio-processConfigureScheduleFormSaveZone
	String SUBMIT_BUTTON_VARIABLES_FORM					= APP_ID_PREFFIX			+ "processConfigureVariablesFormSubmitButton";	//chronium_studio-processConfigureVariablesFormSubmitButton

	//PREFERENCES --> MACHINES VIEW
	String PREFERENCES_MACHINE_DECKPANEL 				= APP_ID_PREFFIX			+ "preferencesMachineDeckPanel"; 			//chronium_studio-preferencesMachineDeckPanel
	String PREFERENCES_MACHINE_VARIABLES_LIST			= APP_ID_PREFFIX			+ "preferencesMachineVariablesList";		//chronium_studio-preferencesMachineVariablesList
	String PREFERENCES_MACHINE_CONFIGURE_FORM			= APP_ID_PREFFIX			+ "preferencesMachineConfigureForm";		//chronium_studio-preferencesMachineConfigureForm
	String PREFERENCES_CONNECTION_VIEW 					= APP_ID_PREFFIX			+ "preferencesConnectionView";				//chronium_studio-preferencesConnectionView
	
	//PREFERENCES --> USER VIEW
	String PREFERENCES_USER_CONFIGURE_FORM				= APP_ID_PREFFIX			+ "preferencesUserConfigureForm";		//chronium_studio-preferencesUserConfigureForm
	String PREFERENCES_USER_VIEW 						= APP_ID_PREFFIX			+ "preferencesUserView";				//chronium_studio-preferencesUserView	
	
	
	//EXECUTION
	String LOGS 											= APP_ID_PREFFIX			+ "logs";							//chronium_studio-logs
	String EXECUTION 										= APP_ID_PREFFIX			+ "execution";							//chronium_studio-execution
	String EXECUTION_LOGGER_TABS 							= EXECUTION					+ "-loggerTabs";						//chronium_studio-execution-loggerTabs
	String EXECUTION_LOGGER_TABS_CONTAINER 					= EXECUTION					+ "-loggerTabsContainer";				//chronium_studio-execution-loggerTabsContainer
	String EXECUTION_LOGGER 								= EXECUTION					+ "-logger";							//chronium_studio-execution-logger
	String EXECUTION_LOGGER_CONTAINER 						= EXECUTION_LOGGER			+ "Container";							//chronium_studio-execution-loggerContainer
	String EXECUTION_LOGGER_DECKPANEL 						= EXECUTION_LOGGER			+ "DeckPanel";							//chronium_studio-execution-loggerDeckPanel
	String EXECUTION_LOGGER_FILTER_PANEL 					= EXECUTION					+ "-loggerFilterPanel";					//chronium_studio-execution-loggerFilterPanel
	String EXECUTION_LOGGER_FILTER_PANEL_FORM 				= EXECUTION					+ "-loggerFilterPanelForm";				//chronium_studio-execution-loggerFilterPanelForm
	String EXECUTION_LOGGER_FILTER_PANEL_FORM_SEVERITYZONE 	= EXECUTION					+ "-loggerFilterPanelFormSeverityZone";	//chronium_studio-execution-loggerFilterPanelFormSeverityZone
	String EXECUTION_LOGGER_FILTER_PANEL_FORM_DATETIME		= EXECUTION					+ "-loggerFilterPanelFormDateTime";		//chronium_studio-execution-loggerFilterPanelFormDateTime
	String EXECUTION_LOGGER_TABS_CONTENT_ZONE 				= EXECUTION					+ "-loggerTabsContentZone";				//chronium_studio-execution-loggerTabsContentZone
	String EXECUTION_LOGGER_TABS_RESPONSE_ZONE 				= EXECUTION					+ "-loggerTabsResponseZone";			//chronium_studio-execution-loggerTabsResponseZone
	String EXECUTION_LOGGER_TABS_REQUEST_ZONE 				= EXECUTION					+ "-loggerTabsRequestZone";				//chronium_studio-execution-loggerTabsRequestZone
	String EXECUTION_LOGGER_TABS_PROMPT_ZONE 				= EXECUTION					+ "-loggerTabsPromptZone";				//chronium_studio-execution-loggerTabsPromptZone
	String EXECUTION_LOGGER_NODES_LEFTZONE					= EXECUTION_LOGGER			+ "NodesLeftZone";						//chronium_studio-execution-loggerNodesLeftZone
	String EXECUTION_LOGGER_NODES_RIGHTZONE					= EXECUTION_LOGGER			+ "NodesRightZone";						//chronium_studio-execution-loggerNodesRightZone
	String EXECUTION_LOGGER_INFOZONE 						= EXECUTION_LOGGER			+ "InfoZone";							//chronium_studio-execution-loggerInfoZone
	String EXECUTION_LOGGER_NODES_LISTZONE					= EXECUTION_LOGGER			+ "NodesListZone";						//chronium_studio-execution-loggerNodesListZone
	String EXECUTION_LOGGER_NODES_LIST						= EXECUTION_LOGGER			+ "NodesList";							//chronium_studio-execution-loggerNodesList
	String EXECUTION_LOGGER_NODES_CONTAINERZONE				= EXECUTION_LOGGER			+ "NodesContainerZone";					//chronium_studio-execution-loggerNodesContainerZone
	String EXECUTION_LOGGER_NODES_CONTAINER					= EXECUTION_LOGGER			+ "NodesContainer";						//chronium_studio-execution-loggerNodesContainer
	String EXECUTION_LOGGER_EXECUTION_INFO_LABEL_ZONE		= EXECUTION_LOGGER          + "InfoLabelZone";						//chronium_studio-execution-loggerInfoLabelZone
	String EXECUTION_LOGGER_EXECUTION_INFO_LABEL			= EXECUTION_LOGGER          + "InfoLabel";							//chronium_studio-execution-loggerInfoLabel
	String EXECUTION_LOGGER_EXECUTION_INFO_CONTENT_LABEL	= EXECUTION_LOGGER          + "InfoContentLabel";					//chronium_studio-execution-loggerInfoContentLabel
	String EXECUTION_LOGGER_EXECUTION_INFO_ONLY_CONTENT_LABEL	= EXECUTION_LOGGER      + "InfoOnlyContentLabel";				//chronium_studio-execution-loggerInfoOnlyContentLabel
	
	//PREFERENCES
	String PREFERENCES_VIEW 							= APP_ID_PREFFIX			+ "preferencesView";	//chronium_studio-preferencesView

	
	//LOGIN	
	String LOGIN_VIEW 									= APP_ID_PREFFIX 			+ "loginView";			//chronium_studio-loginView
	
	
	//SIGN_FORM
	String SIGN_FORM 									= APP_ID_PREFFIX			+ "signForm";			//chronium_studio-signForm
	
	//AUTHENTICATION FORM
	String AUTHENTICATION_FORM 							= APP_ID_PREFFIX			+ "authenticationForm"; //chronium_studio-authenticationForm
	String AUTHENTICATION_FORM_LOGO 					= AUTHENTICATION_FORM		+ "-logo";				//chronium_studio-authenticationForm-logo
	String AUTHENTICATION_FORM_DESCRIPTION 				= AUTHENTICATION_FORM		+ "-description";		//chronium_studio-authenticationForm-description
	String AUTHENTICATION_FORM_ERROR 					= AUTHENTICATION_FORM		+ "-error";				//chronium_studio-authenticationForm-error
	String AUTHENTICATION_FORM_ACTION_BUTTON 			= AUTHENTICATION_FORM		+ "-actionButton";		//chronium_studio-authenticationForm-actionButton
	String AUTHENTICATION_FORM_CONTAINER 				= AUTHENTICATION_FORM		+ "-container";			//chronium_studio-authenticationForm-container
	String AUTHENTICATION_FORM_FIELDS_ZONE 				= AUTHENTICATION_FORM		+ "-fieldsZone";		//chronium_studio-authenticationForm-fieldsZone
	String AUTHENTICATION_FORM_BOTTOM_ZONE 				= AUTHENTICATION_FORM		+ "-bottomZone";		//chronium_studio-authenticationForm-bottomZone

	// FORM ERROR
	String FORM_ERROR 									= APP_ID_PREFFIX + "formError";						//chronium_studio-formError				
}
