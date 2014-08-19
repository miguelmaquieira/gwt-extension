package com.imotion.dslam.front.business.desktop.client.widget.layout.navigator;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusProjectBasePresenterConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenu;

public class DSLAMBusDesktopProjectNavigatorElement extends AEGWTCompositePanel implements CRONIOBusProjectBasePresenterConstants {

	public static final String NAME = "DSLAMBusDesktopProjectNavigatorElement";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private AEGWTBootstrapTreeMenu 				menu;
	private CRONIOBusDesktopProjectNavigatorTreeMenuItem 			menuProject;
	private CRONIOBusDesktopProjectNavigatorTreeMenuItem		 	menuScript;
	private CRONIOBusDesktopProjectNavigatorTreeMenuItem 			menuProcess;
	private CRONIOBusDesktopProjectNavigatorTreeMenuItem 			menuExecution;
	private CRONIOBusDesktopProjectNavigatorFinalItem 	mainScript;
	private CRONIOBusDesktopProjectNavigatorFinalItem 	rollbackScript;
	private CRONIOBusDesktopProjectNavigatorFinalItem 	variableProcess;
	private CRONIOBusDesktopProjectNavigatorFinalItem 	scheduleProcess;
	private CRONIOBusDesktopProjectNavigatorFinalItem 	propertiesProcess;
	private CRONIOBusDesktopProjectNavigatorFinalItem 	nodesProcess;
	private CRONIOBusDesktopProjectNavigatorFinalItem 	nodesLog;

	public DSLAMBusDesktopProjectNavigatorElement(String projectId, String projectName) {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		setId(projectId);

		//MENU
		menu 				= new AEGWTBootstrapTreeMenu();
		root.add(menu);

		//MENU -> Project
		menuProject 		= new CRONIOBusDesktopProjectNavigatorTreeMenuItem(projectId, SECTION_TYPE_PROJECT, projectName, this);
		menuProject.setCloseMenu();
		menu.addWidget(menuProject);

		//MENU -> Project -> Script
		menuScript 			= new CRONIOBusDesktopProjectNavigatorTreeMenuItem(projectId, SECTION_TYPE_SCRIPT, TEXTS.scripts_label(), this);
		menuScript.setCloseMenu();
		menuProject.add(menuScript);

		//MENU -> Project -> Script -> MainScript
		mainScript 			= new CRONIOBusDesktopProjectNavigatorFinalItem(projectId, SECTION_TYPE_SCRIPT, DSLAMBOIProject.PROJECT_MAIN_SCRIPT		,TEXTS.main_script_label(), this);
		menuScript.add(mainScript);

		//MENU -> Project -> Script -> RollbackScript
		rollbackScript 		= new CRONIOBusDesktopProjectNavigatorFinalItem(projectId, SECTION_TYPE_SCRIPT, DSLAMBOIProject.PROJECT_ROLLBACK_SCRIPT	,TEXTS.rollback_script_label(),this);
		menuScript.add(rollbackScript);

		//MENU -> Project  -> Process
		menuProcess 		= new CRONIOBusDesktopProjectNavigatorTreeMenuItem(projectId, SECTION_TYPE_PROCESS, TEXTS.process_label(), this);
		menuProcess.setCloseMenu();
		menuProject.add(menuProcess.asWidget());

		//MENU -> Project  -> Process -> Variables
		variableProcess 	= new CRONIOBusDesktopProjectNavigatorFinalItem(projectId, SECTION_TYPE_PROCESS, DSLAMBOIProject.PROJECT_PROCESS_VARIABLE_LIST		,TEXTS.variables(), this);
		menuProcess.add(variableProcess);

		//MENU -> Project  -> Process -> Schedule
		scheduleProcess 	= new CRONIOBusDesktopProjectNavigatorFinalItem(projectId, SECTION_TYPE_PROCESS, DSLAMBOIProject.PROJECT_PROCESS_SCHEDULE_LIST		,TEXTS.schedule(), this);
		menuProcess.add(scheduleProcess);

		//MENU -> Project  -> Process -> Properties
		propertiesProcess 	= new CRONIOBusDesktopProjectNavigatorFinalItem(projectId, SECTION_TYPE_PROCESS, DSLAMBOIProject.PROJECT_PROCESS_EXTRA_OPTIONS		,TEXTS.properties(), this);
		menuProcess.add(propertiesProcess);

		//MENU -> Project  -> Process -> Nodes
		nodesProcess 		= new CRONIOBusDesktopProjectNavigatorFinalItem(projectId, SECTION_TYPE_PROCESS, DSLAMBOIProject.PROJECT_PROCESS_NODE_LIST			,TEXTS.nodes(), this);
		menuProcess.add(nodesProcess);

		//MENU -> Project  -> Execution
		menuExecution 		= new CRONIOBusDesktopProjectNavigatorTreeMenuItem(projectId, SECTION_TYPE_EXECUTION, TEXTS.execution(), this);
		menuExecution.setCloseMenu();
		menuProject.add(menuExecution);
		
		//MENU -> Project -> Execution -> Logs
		nodesLog 			= new CRONIOBusDesktopProjectNavigatorFinalItem(projectId, SECTION_TYPE_EXECUTION, DSLAMBOIProject.PROJECT_EXECUTION_LOG			,TEXTS.console_label(), this);
		menuExecution.add(nodesLog);

		menu.addSeparator();
	}
	
	public void removeProjectSectionSelected() {
		mainScript.setSelected(false);
		rollbackScript.setSelected(false);
		variableProcess.setSelected(false);
		scheduleProcess.setSelected(false);
		propertiesProcess.setSelected(false);
		nodesProcess.setSelected(false);
		nodesLog.setSelected(false);
		menuProject.setSelected(false);
		menuScript.setSelected(false);
		menuProcess.setSelected(false);
		menuExecution.setSelected(false);
	}
	
	public void setProjectSectionSelected(String sectionId) {
	
		if (DSLAMBOIProject.PROJECT_MAIN_SCRIPT.equals(sectionId)) {
			mainScript.setSelected(true);
		} else if (DSLAMBOIProject.PROJECT_ROLLBACK_SCRIPT.equals(sectionId)) {
			rollbackScript.setSelected(true);
		} else if (DSLAMBOIProject.PROJECT_PROCESS_VARIABLE_LIST.equals(sectionId)) {
			variableProcess.setSelected(true);
		} else if (DSLAMBOIProject.PROJECT_PROCESS_SCHEDULE_LIST.equals(sectionId)) {
			scheduleProcess.setSelected(true);
		} else if (DSLAMBOIProject.PROJECT_PROCESS_EXTRA_OPTIONS.equals(sectionId)) {
			propertiesProcess.setSelected(true);
		} else if (DSLAMBOIProject.PROJECT_PROCESS_NODE_LIST.equals(sectionId)) {
			nodesProcess.setSelected(true);
		} else if (DSLAMBOIProject.PROJECT_EXECUTION_LOG.equals(sectionId)) {
			nodesLog.setSelected(true);
		} else if (SECTION_TYPE_PROJECT.equals(sectionId)) {
			menuProject.setSelected(true);
		} else if (SECTION_TYPE_SCRIPT.equals(sectionId)) {
			menuScript.setSelected(true);
		} else if (SECTION_TYPE_PROCESS.equals(sectionId)) {
			menuProcess.setSelected(true);
		} else if (SECTION_TYPE_EXECUTION.equals(sectionId)) {
			menuExecution.setSelected(true);
		}
		
	}

	public void setProjectSectionModified(String sectionId) {
		menuProject.setModified(true);
		if (DSLAMBOIProject.PROJECT_MAIN_SCRIPT.equals(sectionId)) {
			menuScript.setModified(true);
			mainScript.setModified(true);
		} else if (DSLAMBOIProject.PROJECT_ROLLBACK_SCRIPT.equals(sectionId)) {
			menuScript.setModified(true);
			rollbackScript.setModified(true);
		} else if (DSLAMBOIProject.PROJECT_PROCESS_VARIABLE_LIST.equals(sectionId)) {
			menuProcess.setModified(true);
			variableProcess.setModified(true);
		} else if (DSLAMBOIProject.PROJECT_PROCESS_SCHEDULE_LIST.equals(sectionId)) {
			menuProcess.setModified(true);
			scheduleProcess.setModified(true);
		} else if (DSLAMBOIProject.PROJECT_PROCESS_EXTRA_OPTIONS.equals(sectionId)) {
			menuProcess.setModified(true);
			propertiesProcess.setModified(true);
		} else if (DSLAMBOIProject.PROJECT_PROCESS_NODE_LIST.equals(sectionId)) {
			menuProcess.setModified(true);
			nodesProcess.setModified(true);
		}
	}

	public boolean isModified() {
		return menuProject.isModified();
	}

	public void setProjectSaved() {
		menuProject.setModified(false);
		menuScript.setModified(false);
		mainScript.setModified(false);
		rollbackScript.setModified(false);
		menuProcess.setModified(false);
		variableProcess.setModified(false);
		scheduleProcess.setModified(false);
		propertiesProcess.setModified(false);
		nodesProcess.setModified(false);
	}

	public AEMFTMetadataElementComposite getData() {
		return null;
	}

	public String getElementName() {
		return menuProject.getLabelText();
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub	
	}
}
