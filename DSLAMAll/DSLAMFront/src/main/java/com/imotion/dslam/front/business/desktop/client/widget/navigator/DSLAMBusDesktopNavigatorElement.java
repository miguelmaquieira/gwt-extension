package com.imotion.dslam.front.business.desktop.client.widget.navigator;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenu;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenuFinalItem;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenuItem;

public class DSLAMBusDesktopNavigatorElement extends AEGWTCompositePanel {

	public static final String NAME = "DSLAMBusDesktopNavigatorElement";
	
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private AEGWTBootstrapTreeMenu 				menu;
	private AEGWTBootstrapTreeMenuItem 			menuProject;
	private AEGWTBootstrapTreeMenuItem		 	menuScritp;
	private AEGWTBootstrapTreeMenuItem 			menuProcess;
	private AEGWTBootstrapTreeMenuFinalItem 	mainScritp;
	private AEGWTBootstrapTreeMenuFinalItem 	rollbackScritp;
	private AEGWTBootstrapTreeMenuFinalItem 	variableProcess;
	private AEGWTBootstrapTreeMenuFinalItem 	scheduleProcess;
	private AEGWTBootstrapTreeMenuFinalItem 	propertiesProcess;
	private AEGWTBootstrapTreeMenuFinalItem 	nodesProcess;
	
	public DSLAMBusDesktopNavigatorElement(String name) {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		
		menu 			= new AEGWTBootstrapTreeMenu();
		menuProject 	= new AEGWTBootstrapTreeMenuItem(name);
		menuScritp 		= new AEGWTBootstrapTreeMenuItem(TEXTS.scripts_label());
		menuProcess 	= new AEGWTBootstrapTreeMenuItem(TEXTS.process_label());
		mainScritp 		= new AEGWTBootstrapTreeMenuFinalItem("main",TEXTS.main_script_label());
		rollbackScritp 	= new AEGWTBootstrapTreeMenuFinalItem("rollback",TEXTS.roolback_script_label());
		variableProcess = new AEGWTBootstrapTreeMenuFinalItem("Variables",TEXTS.variables());
		scheduleProcess = new AEGWTBootstrapTreeMenuFinalItem("Schedule",TEXTS.schedule());
		propertiesProcess = new AEGWTBootstrapTreeMenuFinalItem("Properties",TEXTS.properties());
		nodesProcess = new AEGWTBootstrapTreeMenuFinalItem("Nodes",TEXTS.nodes());
		
		
		root.add(menu);
		menu.addWidget(menuProject);
		menuProject.addWidget(menuScritp);
		menuProject.addWidget(menuProcess);
		menuScritp.addWidget(mainScritp);
		menuScritp.addWidget(rollbackScritp);
		menuProcess.addWidget(variableProcess);
		menuProcess.addWidget(scheduleProcess);
		menuProcess.addWidget(propertiesProcess);
		menuProcess.addWidget(nodesProcess);
		
		menu.addSeparator();
		
		
	}
	
	public AEMFTMetadataElementComposite getData() {
		return null;
	}
	
	public String getElementName() {
		return null;
	}
	
	/************************************************************************
	 * JS
	 ************************************************************************/
	
//	$wnd.jQuery('label.tree-toggler').click(function () {
//			$wnd.jQuery(this).parent().children('ul.tree').toggle(300);
//	});
	
	protected static native void addJS() /*-{
	$wnd.jQuery('.tree-toggler').click(function () {
			$wnd.jQuery(this).parent().children('ul.tree').toggle(300);
	});
}-*/;

	@Override
	public void postDisplay() {
		super.postDisplay();
		addJS();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub
		
	}
		
}