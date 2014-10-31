package com.imotion.dslam.front.business.desktop.client.widget.layout.navigator;

import java.util.Iterator;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.imotion.dslam.bom.CRONIOBOINodeList;
import com.imotion.dslam.bom.CRONIOBOIProcess;
import com.imotion.dslam.bom.CRONIOBOIProject;
import com.imotion.dslam.front.business.client.CRONIOBusCommonConstants;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusProjectBasePresenterConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenu;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenuFinalItem;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenuItem;

public class CRONIOBusDesktopProjectNavigatorElement extends AEGWTCompositePanel implements CRONIOBusProjectBasePresenterConstants {

	public static final String NAME = "CRONIOBusDesktopProjectNavigatorElement";
	private static CRONIOBusI18NTexts TEXTS = GWT.create(CRONIOBusI18NTexts.class);

	private AEGWTBootstrapTreeMenu 							menu;
	private CRONIOBusDesktopProjectNavigatorTreeMenuItem 	menuProject;
	private CRONIOBusDesktopProjectNavigatorTreeMenuItem	menuScript;
	private CRONIOBusDesktopProjectNavigatorTreeMenuItem 	menuProcess;
	private CRONIOBusDesktopProjectNavigatorTreeMenuItem 	menuNodesProcess;
	private CRONIOBusDesktopProjectNavigatorTreeMenuItem 	menuExecution;
	private CRONIOBusDesktopProjectNavigatorTreeMenuItem 	menuLogs;
	private CRONIOBusDesktopProjectNavigatorFinalItem 		mainScript;
	private CRONIOBusDesktopProjectNavigatorFinalItem 		rollbackScript;
	private CRONIOBusDesktopProjectNavigatorFinalItem 		variableProcess;
	private CRONIOBusDesktopProjectNavigatorFinalItem 		scheduleProcess;
	private CRONIOBusDesktopProjectNavigatorFinalItem 		propertiesProcess;
	private CRONIOBusDesktopProjectNavigatorFinalItem 		nodesConsole;

	public CRONIOBusDesktopProjectNavigatorElement(String projectId, String projectName) {
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
		mainScript 			= new CRONIOBusDesktopProjectNavigatorFinalItem(projectId, SECTION_TYPE_SCRIPT, CRONIOBOIProject.PROJECT_MAIN_SCRIPT		,TEXTS.main_script_label(), this);
		menuScript.add(mainScript);

		//MENU -> Project -> Script -> RollbackScript
		rollbackScript 		= new CRONIOBusDesktopProjectNavigatorFinalItem(projectId, SECTION_TYPE_SCRIPT, CRONIOBOIProject.PROJECT_ROLLBACK_SCRIPT	,TEXTS.rollback_script_label(),this);
		menuScript.add(rollbackScript);

		//MENU -> Project  -> Process
		menuProcess 		= new CRONIOBusDesktopProjectNavigatorTreeMenuItem(projectId, SECTION_TYPE_PROCESS, TEXTS.process_label(), this);
		menuProcess.setCloseMenu();
		menuProject.add(menuProcess.asWidget());

		//MENU -> Project  -> Process -> Variables
		variableProcess 	= new CRONIOBusDesktopProjectNavigatorFinalItem(projectId, SECTION_TYPE_PROCESS, CRONIOBOIProject.PROJECT_PROCESS_VARIABLE_LIST		,TEXTS.variables(), this);
		menuProcess.add(variableProcess);

		//MENU -> Project  -> Process -> Schedule
		scheduleProcess 	= new CRONIOBusDesktopProjectNavigatorFinalItem(projectId, SECTION_TYPE_PROCESS, CRONIOBOIProject.PROJECT_PROCESS_SCHEDULE_LIST		,TEXTS.schedule(), this);
		menuProcess.add(scheduleProcess);

		//MENU -> Project  -> Process -> Properties
		propertiesProcess 	= new CRONIOBusDesktopProjectNavigatorFinalItem(projectId, SECTION_TYPE_PROCESS, CRONIOBOIProject.PROJECT_PROCESS_EXTRA_OPTIONS		,TEXTS.properties(), this);
		menuProcess.add(propertiesProcess);

		//MENU -> Project  -> Process -> Environments
		menuNodesProcess 		= new CRONIOBusDesktopProjectNavigatorTreeMenuItem(projectId, SECTION_TYPE_ENVIROMENTS, TEXTS.environments(), this);
		menuNodesProcess.setCloseMenu();
		menuNodesProcess.addIconButton(AEGWTIBoostrapConstants.GLYPHICON + " " + AEGWTIBoostrapConstants.GLYPHICON_PLUS);
		menuNodesProcess.addStyleName(CRONIOBusDesktopIStyleConstants.BOOTSTRAP_TREEMENU_ITEM_LEVEL3);
		menuProcess.add(menuNodesProcess.asWidget());

		//MENU -> Project  -> Execution
		menuExecution 		= new CRONIOBusDesktopProjectNavigatorTreeMenuItem(projectId, SECTION_TYPE_EXECUTION, TEXTS.execution(), this);
		menuExecution.setCloseMenu();
		menuProject.add(menuExecution);
		
		//MENU -> Project -> Execution -> Console
		nodesConsole 			= new CRONIOBusDesktopProjectNavigatorFinalItem(projectId, SECTION_TYPE_EXECUTION, CRONIOBOIProject.PROJECT_EXECUTION_CONSOLE			,TEXTS.console_label(), this);
		menuExecution.add(nodesConsole);
		
		//MENU -> Project  -> Execution -> Logs
		menuLogs 		= new CRONIOBusDesktopProjectNavigatorTreeMenuItem(projectId, SECTION_TYPE_LOG, TEXTS.logs(), this);
		menuLogs.setCloseMenu();
		menuProject.add(menuLogs);

		menu.addSeparator();
	}
	
	public void removeProjectSectionSelected() {
		mainScript.setSelected(false);
		rollbackScript.setSelected(false);
		variableProcess.setSelected(false);
		scheduleProcess.setSelected(false);
		propertiesProcess.setSelected(false);
		menuNodesProcess.setSelected(false);
		nodesConsole.setSelected(false);
		menuProject.setSelected(false);
		menuScript.setSelected(false);
		menuProcess.setSelected(false);
		menuExecution.setSelected(false);
		menuLogs.setSelected(false);
		
		Iterator<Widget> logItemList = menuLogs.iterator();
		while (logItemList.hasNext()) {
			AEGWTBootstrapTreeMenuFinalItem log = (AEGWTBootstrapTreeMenuFinalItem) logItemList.next();
			log.setSelected(false);
		}
		
		Iterator<Widget> nodeListItemList = menuNodesProcess.iterator();
		while (nodeListItemList.hasNext()) {
			AEGWTBootstrapTreeMenuFinalItem nodeList = (AEGWTBootstrapTreeMenuFinalItem) nodeListItemList.next();
			nodeList.setSelected(false);
		}
	}
	
	public void setProjectSectionSelected(String sectionId) {
	
		if (CRONIOBOIProject.PROJECT_MAIN_SCRIPT.equals(sectionId)) {
			mainScript.setSelected(true);
		} else if (CRONIOBOIProject.PROJECT_ROLLBACK_SCRIPT.equals(sectionId)) {
			rollbackScript.setSelected(true);
		} else if (CRONIOBOIProject.PROJECT_PROCESS_VARIABLE_LIST.equals(sectionId)) {
			variableProcess.setSelected(true);
		} else if (CRONIOBOIProject.PROJECT_PROCESS_SCHEDULE_LIST.equals(sectionId)) {
			scheduleProcess.setSelected(true);
		} else if (CRONIOBOIProject.PROJECT_PROCESS_EXTRA_OPTIONS.equals(sectionId)) {
			propertiesProcess.setSelected(true);
		} else if (CRONIOBOIProject.PROJECT_EXECUTION_CONSOLE.equals(sectionId)) {
			nodesConsole.setSelected(true);
		} else if (SECTION_TYPE_PROJECT.equals(sectionId)) {
			menuProject.setSelected(true);
		} else if (SECTION_TYPE_SCRIPT.equals(sectionId)) {
			menuScript.setSelected(true);
		} else if (SECTION_TYPE_PROCESS.equals(sectionId)) {
			menuProcess.setSelected(true);
		} else if (SECTION_TYPE_EXECUTION.equals(sectionId)) {
			menuExecution.setSelected(true);
		} else if (SECTION_TYPE_LOG.equals(sectionId)) {
			menuLogs.setSelected(true);
		} else if (SECTION_TYPE_ENVIROMENTS.equals(sectionId)) {
			menuNodesProcess.setSelected(true);
		} else {
			Iterator<Widget> logItemList = menuLogs.iterator();
			while (logItemList.hasNext()) {
				AEGWTBootstrapTreeMenuFinalItem log = (AEGWTBootstrapTreeMenuFinalItem) logItemList.next();
				if (sectionId.equals(log.getElementName())) {
					log.setSelected(true);
				}
			}
			Iterator<Widget> nodeListItemList = menuNodesProcess.iterator();
			while (nodeListItemList.hasNext()) {
				AEGWTBootstrapTreeMenuFinalItem nodeList = (AEGWTBootstrapTreeMenuFinalItem) nodeListItemList.next();
				StringBuilder sbKey = new StringBuilder();
				sbKey.append(CRONIOBOINodeList.NODELIST_PROCESS);
				sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
				sbKey.append(CRONIOBOIProcess.PROCESS_NODELIST_LIST);
				sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
				sbKey.append(nodeList.getElementName());
				String nodeListKey = sbKey.toString();
				if (sectionId.equals(nodeListKey)) {
					nodeList.setSelected(true);
				}
			}
		}
		
	}

	public void setProjectSectionModified(String sectionId) {
		menuProject.setModified(true);
		if (CRONIOBOIProject.PROJECT_MAIN_SCRIPT.equals(sectionId)) {
			menuScript.setModified(true);
			mainScript.setModified(true);
		} else if (CRONIOBOIProject.PROJECT_ROLLBACK_SCRIPT.equals(sectionId)) {
			menuScript.setModified(true);
			rollbackScript.setModified(true);
		} else if (CRONIOBOIProject.PROJECT_PROCESS_VARIABLE_LIST.equals(sectionId)) {
			menuProcess.setModified(true);
			variableProcess.setModified(true);
		} else if (CRONIOBOIProject.PROJECT_PROCESS_SCHEDULE_LIST.equals(sectionId)) {
			menuProcess.setModified(true);
			scheduleProcess.setModified(true);
		} else if (CRONIOBOIProject.PROJECT_PROCESS_EXTRA_OPTIONS.equals(sectionId)) {
			menuProcess.setModified(true);
			propertiesProcess.setModified(true);
		} else if (sectionId.contains(CRONIOBOIProject.PROJECT_PROCESS_NODE_LISTS)) {
			menuProcess.setModified(true);
			menuNodesProcess.setModified(true);
			
			Iterator<Widget> nodeListItemList = menuNodesProcess.iterator();
			while (nodeListItemList.hasNext()) {
				AEGWTBootstrapTreeMenuFinalItem nodeList = (AEGWTBootstrapTreeMenuFinalItem) nodeListItemList.next();
				StringBuilder sbKey = new StringBuilder();
				sbKey.append(CRONIOBOINodeList.NODELIST_PROCESS);
				sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
				sbKey.append(CRONIOBOIProcess.PROCESS_NODELIST_LIST);
				sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
				sbKey.append(nodeList.getElementName());
				String nodeListKey = sbKey.toString();
				if (sectionId.equals(nodeListKey)) {
					nodeList.setModified(true);
				}
			}
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
		menuNodesProcess.setModified(false);
		variableProcess.setModified(false);
		scheduleProcess.setModified(false);
		propertiesProcess.setModified(false);
		setResetModifiedEnviromentsSection(menuNodesProcess);
	}
	
	public void setResetModifiedEnviromentsSection (AEGWTBootstrapTreeMenuItem section) {
		Iterator<Widget> listNodeList = section.iterator();
		while (listNodeList.hasNext()) {
			AEGWTBootstrapTreeMenuFinalItem nodeListSection = (AEGWTBootstrapTreeMenuFinalItem) listNodeList.next();
			nodeListSection.setModified(false);
		}
	}
	
	public void addExecution(String projectId,long executionId, String executionDateStr) {
		
		String executionText = executionDateStr + " " + String.valueOf(executionId);
		
		CRONIOBusDesktopProjectNavigatorFinalItem execution = new CRONIOBusDesktopProjectNavigatorFinalItem(projectId, SECTION_TYPE_LOG, CRONIOBOIProject.PROJECT_EXECUTION_LOG			, executionText, this);
		menuLogs.add(execution);
	}
	
	public void addNodeList(String projectId, String nodeListName) {
		CRONIOBusDesktopProjectNavigatorFinalItem nodeList = new CRONIOBusDesktopProjectNavigatorFinalItem(projectId, SECTION_TYPE_ENVIROMENTS, CRONIOBOIProject.PROJECT_PROCESS_NODE_LISTS	, nodeListName, this);
		menuNodesProcess.add(nodeList);
	}
	
	public void hideAddNodeListForm() {
		menuNodesProcess.resetForm();
	}

	public AEMFTMetadataElementComposite getData() {
		return null;
	}

	public String getElementName() {
		return menuProject.getLabelText();
	}
	
	public void showDuplicateNodeListNameError(String nodeListName) {
		menuNodesProcess.showDuplicateNodeListNameError(nodeListName);
	}
	
	/**
	 * AEGWTCompositePanel
	 */

	@Override
	public String getName() {
		return NAME;
	}
	

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub	
	}
}
