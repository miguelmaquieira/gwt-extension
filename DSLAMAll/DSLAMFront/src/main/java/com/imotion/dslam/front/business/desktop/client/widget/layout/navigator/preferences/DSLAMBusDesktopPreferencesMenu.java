package com.imotion.dslam.front.business.desktop.client.widget.layout.navigator.preferences;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusPreferencesBasePresenterConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenu;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenuItem;

public class DSLAMBusDesktopPreferencesMenu extends AEGWTCompositePanel implements CRONIOBusPreferencesBasePresenterConstants {

	public static final String NAME = "DSLAMBusDesktopPreferencesMenu";

	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private AEGWTBootstrapTreeMenu 				menu;
	private AEGWTBootstrapTreeMenuItem 			menuMachines;
	private AEGWTBootstrapTreeMenuItem		 	dslamMachine;
//	private AEGWTBootstrapTreeMenuItem 			menuProcess;
//	private AEGWTBootstrapTreeMenuItem 			menuExecution;
	private CRONIOBusDesktopPreferencesMenuFinalItem 	connectionDslamScript;
	private CRONIOBusDesktopPreferencesMenuFinalItem 	disconnectionDslamScript;
	private CRONIOBusDesktopPreferencesMenuFinalItem 	variablesMachine;
//	private CRONIOBusDesktopProjectNavigatorFinalItem 	scheduleProcess;
//	private CRONIOBusDesktopProjectNavigatorFinalItem 	propertiesProcess;
//	private CRONIOBusDesktopProjectNavigatorFinalItem 	nodesProcess;
//	private CRONIOBusDesktopProjectNavigatorFinalItem 	nodesLog;

	public DSLAMBusDesktopPreferencesMenu() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		//setId(projectId);

		//MENU
		menu 				= new AEGWTBootstrapTreeMenu();
		root.add(menu);

		//MENU -> Machines
		menuMachines 		= new AEGWTBootstrapTreeMenuItem(TEXTS.machines());
		menuMachines.setCloseMenu();
		menu.addWidget(menuMachines);

		//MENU -> Machines -> DSLAM
		dslamMachine 			= new AEGWTBootstrapTreeMenuItem(TEXTS.dslam());
		dslamMachine.setCloseMenu();
		menuMachines.addWidget(dslamMachine);

		//MENU -> Machines -> DSLAM --> Connection Script
		connectionDslamScript 		= new CRONIOBusDesktopPreferencesMenuFinalItem(SECTION_CONNECTION_SCRIPT, CRONIOBOIPreferences.PREFERENCES_CONNECTION_DSLAM		,TEXTS.connection_script(), this);
		dslamMachine.addWidget(connectionDslamScript);

		//MENU -> Machines -> DSLAM --> Disconnection Script
	//	disconnectionDslamScript 	= new CRONIOBusDesktopPreferencesMenuFinalItem(SECTION_DISCONNECTION_SCRIPT, CRONIOBOIPreferences.PROJECT_ROLLBACK_SCRIPT	,TEXTS.disconnection_script(),this);
		dslamMachine.addWidget(disconnectionDslamScript);
		
		//MENU -> Machines -> DSLAM --> Machine Variables
	//	variablesMachine 			= new CRONIOBusDesktopPreferencesMenuFinalItem(SECTION_MACHINE_VARIABLES, CRONIOBOIPreferences.PROJECT_ROLLBACK_SCRIPT	,TEXTS.variables(),this);
		dslamMachine.addWidget(variablesMachine);
		
		//MENU -> Machines -> DSLAM --> Connection Protocol
	//	variablesMachine 			= new CRONIOBusDesktopPreferencesMenuFinalItem(SECTION_MACHINE_VARIABLES, CRONIOBOIPreferences.PROJECT_ROLLBACK_SCRIPT	,TEXTS.variables(),this);
		dslamMachine.addWidget(variablesMachine);
		
	}

//	public void setProjectSectionModified(String sectionId) {
//		menuProject.setModified(true);
//		if (DSLAMBOIProject.PROJECT_MAIN_SCRIPT.equals(sectionId)) {
//			menuScript.setModified(true);
//			mainScript.setModified(true);
//		} else if (DSLAMBOIProject.PROJECT_ROLLBACK_SCRIPT.equals(sectionId)) {
//			menuScript.setModified(true);
//			rollbackScript.setModified(true);
//		} else if (DSLAMBOIProject.PROJECT_PROCESS_VARIABLE_LIST.equals(sectionId)) {
//			menuProcess.setModified(true);
//			variableProcess.setModified(true);
//		} else if (DSLAMBOIProject.PROJECT_PROCESS_SCHEDULE_LIST.equals(sectionId)) {
//			menuProcess.setModified(true);
//			scheduleProcess.setModified(true);
//		} else if (DSLAMBOIProject.PROJECT_PROCESS_EXTRA_OPTIONS.equals(sectionId)) {
//			menuProcess.setModified(true);
//			propertiesProcess.setModified(true);
//		} else if (DSLAMBOIProject.PROJECT_PROCESS_NODE_LIST.equals(sectionId)) {
//			menuProcess.setModified(true);
//			nodesProcess.setModified(true);
//		}
//	}
//
//	public boolean isModified() {
//		return menuProject.isModified();
//	}
//
//	public void setProjectSaved() {
//		menuProject.setModified(false);
//		menuScript.setModified(false);
//		mainScript.setModified(false);
//		rollbackScript.setModified(false);
//		menuProcess.setModified(false);
//		variableProcess.setModified(false);
//		scheduleProcess.setModified(false);
//		propertiesProcess.setModified(false);
//		nodesProcess.setModified(false);
//	}
//
//	public AEMFTMetadataElementComposite getData() {
//		return null;
//	}
//
//	public String getElementName() {
//		return menuProject.getLabelText();
//	}
//
//	//	protected static native void addJS() /*-{
//	//	$wnd.jQuery('label.tree-toggler').click(function () {
//	//			$wnd.jQuery(this).parent().children('ul.tree').toggle(300);
//	//	});
//	//	}-*/;


	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub	
	}

}
