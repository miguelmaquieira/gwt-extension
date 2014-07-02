package com.imotion.dslam.front.business.desktop.client;

public interface DSLAMBusDesktopIStyleConstants {

	String APP_ID_PREFFIX = "dslam_studio-";

	
	//TOOLBAR
	String TOOLBAR										= APP_ID_PREFFIX 		+ "toolbar"; 			//dslam_studio-toolbar
	String TOOLBAR_ACTIONS 								= TOOLBAR				+ "-actions";			//dslam_studio-toolbar-actions
	String TOOLBAR_ACTIONS_ZONE							= TOOLBAR_ACTIONS		+ "-zone";				//dslam_studio-toolbar-actions-zone
	String TOOLBAR_INFO 								= TOOLBAR				+ "-info";				//dslam_studio-toolbar-info
	String TOOLBAR_INFO_ZONE 							= TOOLBAR_INFO			+ "-zone";				//dslam_studio-toolbar-info-zone
	String TOOLBAR_INFO_LAST_SAVED_ZONE 				= TOOLBAR_INFO			+ "-lastsaved";			//dslam_studio-toolbar-info-lastsaved
	String TOOLBAR_INFO_CLOSE_ZONE 						= TOOLBAR_INFO			+ "-close";				//dslam_studio-toolbar-info-close 
	String TOOLBAR_INFO_TITLE_ZONE 						= TOOLBAR_INFO			+ "-titleZone";			//dslam_studio-toolbar-info-titleZone
	String TOOLBAR_INFO_TITLE_ZONE_MAIN_TITLE 			= TOOLBAR_INFO_TITLE_ZONE + "-mainTitle";		//dslam_studio-toolbar-info-titleZone-mainTitle
	String TOOLBAR_INFO_TITLE_ZONE_SEPARATOR 			= TOOLBAR_INFO_TITLE_ZONE + "-separator";		//dslam_studio-toolbar-info-titleZone-separator
	String TOOLBAR_INFO_TITLE_ZONE_SECONDARY_TITLE 		= TOOLBAR_INFO_TITLE_ZONE + "-secondaryTitle";	//dslam_studio-toolbar-info-titleZone-secondaryTitle

	//Project Navigator
	String LIST											= APP_ID_PREFFIX 		+ "list";				//dslam_studio-list
	String LIST_CONTAINER								= LIST		 			+ "-container";			//dslam_studio-list-container
	String LIST_CONTAINER_ZONE 							= LIST_CONTAINER		+ "-zone";				//dslam_studio-list-container-zone

	String NODE_LIST									= APP_ID_PREFFIX 		+ "nodeList";			//dslam_studio-nodeList		
	String NODE_LIST_CONTAINER							= NODE_LIST		 		+ "-container";			//dslam_studio-nodeList-container
	String NODE_LIST_CONTAINER_ZONE 					= LIST_CONTAINER		+ "-zone";				//dslam_studio-nodeList-container-zone
	
	//PROJECT FORM
	String NEW_PROJECT_FORM 							= APP_ID_PREFFIX			+ "newProjectForm";	//dslam_studio-newProjectForm
	String NEW_PROJECT_FORM_ERROR_ZONE 					= NEW_PROJECT_FORM			+ "-errorZone";		//dslam_studio-newProjectForm-errorZone
	String NEW_PROJECT_FORM_INPUT_ZONE 					= NEW_PROJECT_FORM			+ "-inputZone";		//dslam_studio-newProjectForm-inputZone
	String NEW_PROJECT_FORM_SAVE_ZONE 					= NEW_PROJECT_FORM			+ "-saveZone";		//dslam_studio-newProjectForm-saveZone

	//PROJECT LAYOUT
	String PROJECTS_LAYOUT 											= APP_ID_PREFFIX								+ "projectsLayout";		//dslam_studio-projectsLayout
	String PROJECTS_LAYOUT_LIST_ZONE 								= PROJECTS_LAYOUT								+ "-listZone";			//dslam_studio-projectsLayout-listZone
	String PROJECTS_LAYOUT_WORK_ZONE 								= PROJECTS_LAYOUT								+ "-projectWorkZone"; 	//dslam_studio-projectsLayout-projectWorkZone
	String PROJECTS_LAYOUT_WORK_ZONE_HEADER							= PROJECTS_LAYOUT_WORK_ZONE						+ "-header";			//dslam_studio-projectsLayout-projectWorkZone-header
	String PROJECTS_LAYOUT_WORK_ZONE_HEADER_ACTIONS_ZONE 			= PROJECTS_LAYOUT_WORK_ZONE_HEADER				+ "-actionsZone";		//dslam_studio-projectsLayout-projectWorkZone-header-actionsZone
	String PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE 				= PROJECTS_LAYOUT_WORK_ZONE_HEADER				+ "-infoZone";			//dslam_studio-projectsLayout-projectWorkZone-header-infoZone
	String PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_MOD_INDICATOR = PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE	+ "-modIndicator";		//dslam_studio-projectsLayout-projectWorkZone-header-infoZone-modIndicator
	String PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_SECTION_NAME 	= PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE	+ "-sectionName";		//dslam_studio-projectsLayout-projectWorkZone-header-infoZone-sectionName
	String PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_SEPARATOR 	= PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE	+ "-separator";			//dslam_studio-projectsLayout-projectWorkZone-header-infoZone-separator
	String PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_PROJECT_NAME 	= PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE	+ "-projectName";		//dslam_studio-projectsLayout-projectWorkZone-header-infoZone-projectName
	String PROJECTS_LAYOUT_BOTTOM_ZONE 								= PROJECTS_LAYOUT								+ "-bottomZone";		//dslam_studio-projectsLayout-bottomZone
	String PROJECTS_LAYOUT_BOTTOM_ZONE_RIGHT 						= PROJECTS_LAYOUT_BOTTOM_ZONE					+ "-right";				//dslam_studio-projectsLayout-bottomZone-right
	String PROJECTS_LAYOUT_ZONE_HEADER 								= PROJECTS_LAYOUT								+ "-zoneHeader";		//dslam_studio-projectsLayout-zoneHeader
	String PROJECTS_LAYOUT_CONTENT_IN_BOX							= PROJECTS_LAYOUT								+ "-contentInBox ";		//dslam_studio-projectsLayout-contentInBox 
	
	
	//SCRITPS EDITOR
	String SCRIPTS_EDITOR_CONTAINER						= APP_ID_PREFFIX		+ "scriptsEditorContainer";	//dslam_studio-scriptsEditorContainer
	String SCRIPTS_EDITOR_AREA 							= APP_ID_PREFFIX		+ "scriptsEditorArea";		//dslam_studio-scriptsEditorArea

	//HEADER WITH ACTIONS
	String HEADER_ACTIONS								= APP_ID_PREFFIX 		+ "header-actions"; 			//dslam_studio-header-actions
	String HEADER_ACTIONS_WITH_LABEL_BUTTON				= APP_ID_PREFFIX 		+ "header-actions-with-label-button"; 			//dslam_studio-header-actions-with-label-button
	
	//PROCESS VIEW
	String PROCESS_VIEW 								= APP_ID_PREFFIX			+ "processView";						//dslam_studio-processView
	String PROCESS_ZONE 								= APP_ID_PREFFIX			+ "processZone"; 						//dslam_studio-processZone
	String PROJECT_CONFIGURE_DECKPANEL 					= APP_ID_PREFFIX			+ "projectConfigureDeckPanel"; 			//dslam_studio-projectConfigureDeckPanel
	String PROCESS_CONFIGURE 							= APP_ID_PREFFIX			+ "processConfigure"; 					//dslam_studio-processConfigure
	String PROCESS_CONFIGURE_ZONE 						= APP_ID_PREFFIX			+ "processConfigureZone";				//dslam_studio-processConfigureZone
	String PROCESS_CONFIGURE_PROPERTIES_CHECKBOX		= APP_ID_PREFFIX			+ "processConfigurePropertiesCheckBox";	//dslam_studio-processConfigurePropertiesCheckBox
	String PROCESS_CONFIGURE_VARIABLES_LIST				= APP_ID_PREFFIX			+ "processConfigureVariablesList";		//dslam_studio-processConfigureVariablesList
	String PROCESS_CONFIGURE_SCHEDULE_LIST				= APP_ID_PREFFIX			+ "processConfigureScheduleList";		//dslam_studio-processConfigureScheduleList
	String PROCESS_CONFIGURE_SCHEDULE_LINE				= APP_ID_PREFFIX			+ "processConfigureScheduleLine";		//dslam_studio-processConfigureScheduleLine
	String PROCESS_CONFIGURE_NODES_ZONE 				= APP_ID_PREFFIX			+ "processConfigureNodesZone"; 					//dslam_studio-processConfigureNodeZone
	String PROCESS_CONFIGURE_NODES 						= APP_ID_PREFFIX			+ "processConfigureNodes"; 						//dslam_studio-processConfigureNode
	String PROCESS_CONFIGURE_NODES_LIST_ZONE 			= APP_ID_PREFFIX			+ "processConfigureNodesListZone";			//dslam_studio-processConfigureNodesListZone
	String PROCESS_CONFIGURE_NODES_LEFT_ZONE 			= APP_ID_PREFFIX			+ "processConfigureNodesLeftZone";			//dslam_studio-processConfigureNodesLeftZone
	String PROCESS_CONFIGURE_NODES_RIGHT_ZONE 			= APP_ID_PREFFIX			+ "processConfigureNodesRightZone";			//dslam_studio-processConfigureNodesRightZone
	String PROCESS_CONFIGURE_NODES_INFO_ZONE 			= APP_ID_PREFFIX			+ "processConfigureNodesInfoZone";		//dslam_studio-processConfigureNodesInfoZone
	String PROCESS_CONFIGURE_NODES_INFO 				= APP_ID_PREFFIX			+ "processConfigureNodesInfo";			//dslam_studio-processConfigureNodesInfo
	String PROCESS_CONFIGURE_NODES_LIST 				= APP_ID_PREFFIX			+ "processConfigureNodesList";				//dslam_studio-processConfigureNodesList
	String PROCESS_CONFIGURE_NODE_VARIABLES_LIST        = APP_ID_PREFFIX			+ "processConfigureNodesVariableList";		//dslam_studio-processConfigureNodesVariableList
	String POPUP_VARIABLES_FORM_CONTAINER				= APP_ID_PREFFIX			+ "processConfigureVariablesFormContainer";		//dslam_studio-processConfigureVariablesFormContainer
	String POPUP_SCHEDULE_FORM_CONTAINER				= APP_ID_PREFFIX			+ "processConfigureScheduleFormContainer";		//dslam_studio-processConfigureScheduleFormContainer
	String POPUP_SCHEDULE_FORM_SAVE_ZONE				= APP_ID_PREFFIX			+ "processConfigureScheduleFormSaveZone";		//dslam_studio-processConfigureScheduleFormSaveZone
	String SUBMIT_BUTTON_VARIABLES_FORM					= APP_ID_PREFFIX			+ "processConfigureVariablesFormSubmitButton";		//dslam_studio-processConfigureVariablesFormSubmitButton

	//EXECUTION
	String EXECUTION 									= APP_ID_PREFFIX			+ "execution";
	String EXECUTION_LOGGER_TABS 						= EXECUTION					+ "-loggerTabs";

}
