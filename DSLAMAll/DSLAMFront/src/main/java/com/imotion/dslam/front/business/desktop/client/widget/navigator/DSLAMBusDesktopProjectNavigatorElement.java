package com.imotion.dslam.front.business.desktop.client.widget.navigator;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenu;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenuFinalItem;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenuItem;

public class DSLAMBusDesktopProjectNavigatorElement extends AEGWTCompositePanel {

	public static final String NAME = "DSLAMBusDesktopProjectNavigatorElement";
	
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private AEGWTBootstrapTreeMenu 				menu;
	private AEGWTBootstrapTreeMenuItem 			menuProject;
	private AEGWTBootstrapTreeMenuItem		 	menuScript;
	private AEGWTBootstrapTreeMenuItem 			menuProcess;
	private AEGWTBootstrapTreeMenuFinalItem 	mainScript;
	private AEGWTBootstrapTreeMenuFinalItem 	rollbackScript;
	private AEGWTBootstrapTreeMenuFinalItem 	variableProcess;
	private AEGWTBootstrapTreeMenuFinalItem 	scheduleProcess;
	private AEGWTBootstrapTreeMenuFinalItem 	propertiesProcess;
	private AEGWTBootstrapTreeMenuFinalItem 	nodesProcess;
	
	public DSLAMBusDesktopProjectNavigatorElement(String projectId, String name) {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		
		//MENU
		menu 				= new AEGWTBootstrapTreeMenu();
		root.add(menu);
		
		//MENU -> Project
		menuProject 		= new AEGWTBootstrapTreeMenuItem(name);
		menu.addWidget(menuProject);
		
		//MENU -> Project -> Script
		menuScript 			= new AEGWTBootstrapTreeMenuItem(TEXTS.scripts_label());
		menuProject.addWidget(menuScript);
		
		//MENU -> Project -> Script -> MainScript
		mainScript 			= new AEGWTBootstrapTreeMenuFinalItem(projectId, DSLAMBOIProject.PROJECT_MAIN_SCRIPT		,TEXTS.main_script_label(), this);
		menuScript.addWidget(mainScript);
		
		//MENU -> Project -> Script -> RollbackScript
		rollbackScript 		= new AEGWTBootstrapTreeMenuFinalItem(projectId, DSLAMBOIProject.PROJECT_ROLLBACK_SCRIPT	,TEXTS.roolback_script_label(),this);
		menuScript.addWidget(rollbackScript);
		
		//MENU -> Project  -> Process
		menuProcess 		= new AEGWTBootstrapTreeMenuItem(TEXTS.process_label());
		menuProject.addWidget(menuProcess);
		
		//MENU -> Project  -> Process -> Variables
		variableProcess 	= new AEGWTBootstrapTreeMenuFinalItem(projectId, DSLAMBOIProject.PROJECT_PROCESS_VARIABLE_LIST		,TEXTS.variables(), this);
		menuProcess.addWidget(variableProcess);
		
		//MENU -> Project  -> Process -> Schedule
		scheduleProcess 	= new AEGWTBootstrapTreeMenuFinalItem(projectId, DSLAMBOIProject.PROJECT_PROCESS_SCHEDULE_LIST		,TEXTS.schedule(), this);
		menuProcess.addWidget(scheduleProcess);
		
		//MENU -> Project  -> Process -> Properties
		propertiesProcess 	= new AEGWTBootstrapTreeMenuFinalItem(projectId, DSLAMBOIProject.PROJECT_PROCESS_EXTRA_OPTIONS		,TEXTS.properties(), this);
		menuProcess.addWidget(propertiesProcess);
		
		//MENU -> Project  -> Process -> Nodes
		nodesProcess 		= new AEGWTBootstrapTreeMenuFinalItem(projectId,  DSLAMBOIProject.PROJECT_PROCESS_NODES				,TEXTS.nodes(), this);
		menuProcess.addWidget(nodesProcess);
		
		menu.addSeparator();
	}
	
	public AEMFTMetadataElementComposite getData() {
		return null;
	}
	
	public String getElementName() {
		return menuProject.getLabelText();
	}
	
//	protected static native void addJS() /*-{
//	$wnd.jQuery('label.tree-toggler').click(function () {
//			$wnd.jQuery(this).parent().children('ul.tree').toggle(300);
//	});
//	}-*/;

	@Override
	public void postDisplay() {
		super.postDisplay();
		//addJS();
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