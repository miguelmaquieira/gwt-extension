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

	//LIST
	String LIST											= APP_ID_PREFFIX 		+ "list";				//dslam_studio-list
	String LIST_CONTAINER								= LIST		 			+ "-container";			//dslam_studio-list-container
	String LIST_CONTAINER_ZONE 							= LIST_CONTAINER		+ "-zone";				//dslam_studio-list-container-zone
	String LIST_HEADER 									= LIST					+ "-header";			//dslam_studio-list-header
	String LIST_ELEMENT 								= LIST					+ "-element";			//dslam_studio-list-element
	String LIST_ZONE 									= LIST					+ "-zone";				//dslam_studio-list-zone

	//PROJECT FORM
	String NEW_PROJECT_FORM 							= APP_ID_PREFFIX			+ "newProjectForm";	//dslam_studio-newProjectForm
	String NEW_PROJECT_FORM_ERROR_ZONE 					= NEW_PROJECT_FORM			+ "-errorZone";		//dslam_studio-newProjectForm-errorZone
	String NEW_PROJECT_FORM_INPUT_ZONE 					= NEW_PROJECT_FORM			+ "-inputZone";		//dslam_studio-newProjectForm-inputZone
	String NEW_PROJECT_FORM_SAVE_ZONE 					= NEW_PROJECT_FORM			+ "-saveZone";		//dslam_studio-newProjectForm-saveZone

	//PROJECTS VIEW
	String PROJECTS_VIEW 								= APP_ID_PREFFIX		+ "projectsView";	//dslam_studio-projectsView
	String PROJECTS_VIEW_BOTTOM_ZONE 					= PROJECTS_VIEW			+ "-bottomZone";	//dslam_studio-projectsView-bottomZone
	String PROJECTS_VIEW_PROCESS_NAME 					= PROJECTS_VIEW			+ "-projectName";	//dslam_studio-projectsView-projectName
	String PROJECTS_VIEW_SECTION_NAME 					= PROJECTS_VIEW			+ "-sectionName";	//dslam_studio-projectsView-sectionName	
	String PROJECT_LIST									= APP_ID_PREFFIX 		+ "projectlist";	//dslam_studio-projectlist
	String PROJECT_LIST_ZONE 							= PROJECT_LIST			+ "-zone";			//dslam_studio-projectlist-zone
	String PROJECT_WORK_ZONE 							= APP_ID_PREFFIX		+ "projectWorkZone"; 			//dslam_studio-projectWorkZone
	String PROJECT_CONFIGURE_DECKPANEL 					= APP_ID_PREFFIX		+ "projectConfigureDeckPanel"; 	//dslam_studio-projectConfigureDeckPanel

	//SCRITPS EDITOR
	String SCRIPTS_EDITOR_CONTAINER						= APP_ID_PREFFIX		+ "scriptsEditorContainer";	//dslam_studio-scriptsEditorContainer
	String SCRIPTS_EDITOR_AREA 							= APP_ID_PREFFIX		+ "scriptsEditorArea";		//dslam_studio-scriptsEditorArea

	String PROCESS_ZONE 								= APP_ID_PREFFIX			+ "processZone"; 	//dslam_studio-processZone

	//EDITOR VIEW
	String EDITOR_VIEW 									= APP_ID_PREFFIX			+ "editorView";		//dslam_studio-editorView
	String EDITOR_VIEW_BOTTOM_ZONE 						= EDITOR_VIEW				+ "-bottomZone";	//dslam_studio-editorView-bottomZone
	String EDITOR_VIEW_FILE_NAME 						= EDITOR_VIEW				+ "-filename";		//dslam_studio-editorView-filename
	String EDITOR_VIEW_CONTENT_TYPE						= EDITOR_VIEW				+ "-contentType";	//dslam_studio-editorView-contentType

	//PROCESS VIEW
	String PROCESS_VIEW 							= APP_ID_PREFFIX			+ "processView";		//dslam_studio-processView
	
	//PROCESS OPTIONS

	String PROCESS_CONFIGURE 							= APP_ID_PREFFIX			+ "processConfigure"; 							//dslam_studio-processConfigure
	String PROCESS_CONFIGURE_ZONE 						= APP_ID_PREFFIX			+ "processConfigureZone";				//dslam_studio-processConfigureZone
	String PROCESS_CONFIGURE_PROPERTIES_HEADER			= APP_ID_PREFFIX			+ "processConfigurePropertiesHeader";	//dslam_studio-processConfigurePropertiesHeader
	String PROCESS_CONFIGURE_PROPERTIES_CHECKBOX		= APP_ID_PREFFIX			+ "processConfigurePropertiesCheckBox";	//dslam_studio-processConfigurePropertiesCheckBox
	String PROCESS_CONFIGURE_VARIABLES_HEADER			= APP_ID_PREFFIX			+ "processConfigureVariablesHeader"; 	//dslam_studio-processConfigureVariablesHeader
	String PROCESS_CONFIGURE_VARIABLES_LIST				= APP_ID_PREFFIX			+ "processConfigureVariablesList";		//dslam_studio-processConfigureVariablesList
	String PROCESS_CONFIGURE_SCHEDULE_HEADER			= APP_ID_PREFFIX			+ "processConfigureScheduleHeader"; 		//dslam_studio-processConfigureScheduleHeader
	String PROCESS_CONFIGURE_SCHEDULE_LIST				= APP_ID_PREFFIX			+ "processConfigureScheduleList";		//dslam_studio-processConfigureScheduleList
	String PROCESS_CONFIGURE_SCHEDULE_LINE				= APP_ID_PREFFIX			+ "processConfigureScheduleLine";		//dslam_studio-processConfigureScheduleLine
	String PROCESS_CONFIGURE_NODES_ZONE 				= APP_ID_PREFFIX			+ "processConfigureNodesZone"; 					//dslam_studio-processConfigureNodeZone
	String PROCESS_CONFIGURE_NODES 						= APP_ID_PREFFIX			+ "processConfigureNodes"; 						//dslam_studio-processConfigureNode
	String PROCESS_CONFIGURE_NODES_SELECTION_ZONE 		= APP_ID_PREFFIX			+ "processConfigureNodesSelectionZone";			//dslam_studio-processConfigureNodesSelectionZone
	String PROCESS_CONFIGURE_NODES_PROPERTIES_ZONE 		= APP_ID_PREFFIX			+ "processConfigureNodesPropertiesZone";		//dslam_studio-processConfigureNodesPropertiesZone
	String PROCESS_CONFIGURE_NODES_PROPERTIES 			= APP_ID_PREFFIX			+ "processConfigureNodesProperties";			//dslam_studio-processConfigureNodesProperties
	String PROCESS_CONFIGURE_NODES_PROPERTIES_HEADER 	= APP_ID_PREFFIX			+ "processConfigureNodesPropertiesHeader";		//dslam_studio-processConfigureNodesPropertiesHeader
	String PROCESS_CONFIGURE_NODES_SELECTION 			= APP_ID_PREFFIX			+ "processConfigureNodesSelection";				//dslam_studio-processConfigureNodesSelection
	String PROCESS_CONFIGURE_NODES_SELECTION_HEADER 	= APP_ID_PREFFIX			+ "processConfigureNodesSelectionHeader";		//dslam_studio-processConfigureNodesSelectionHeader

	String POPUP_VARIABLES_FORM_CONTAINER				= APP_ID_PREFFIX			+ "processConfigureVariablesFormContainer";		//dslam_studio-processConfigureVariablesFormContainer
	String SUBMIT_BUTTON_VARIABLES_FORM					= APP_ID_PREFFIX			+ "processConfigureVariablesFormSubmitButton";		//dslam_studio-processConfigureVariablesFormSubmitButton

}
