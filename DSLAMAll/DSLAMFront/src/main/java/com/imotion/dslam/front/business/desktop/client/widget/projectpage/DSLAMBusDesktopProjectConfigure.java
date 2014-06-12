package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.DeckPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.widget.editor.CRONIOBusDesktopScriptsEditor;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;


public class DSLAMBusDesktopProjectConfigure extends AEGWTCompositePanel  {

	public static final String NAME = "DSLAMBusDesktopProjectConfigure";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	
	private DeckPanel 									root;
	private CRONIOBusDesktopScriptsEditor				mainScriptEditor;
	private CRONIOBusDesktopScriptsEditor				rollBackScriptEditor;
	private DSLAMBusDesktopProcessConfigureVariables	variablesProcessConfigure;
	private DSLAMBusDesktopProcessConfigureSchedule		scheduleProcessConfigure;
	private DSLAMBusDesktopProcessConfigureProperties	propertiesProcessConfigure;
	//private DSLAMBusDesktopProcessConfigureOptions		optionsZone;
	//private DSLAMBusDesktopProcessConfigureNodes		nodeZone;
	
	public String MAIN_SCRIPT 			= "mainScritp";
	public String ROLLBACK_SCRIPT 		= "rollbackScritp";
	public String VARIABLE_PROCESS 		= "variableProcess";
	public String SCHEDULE_PROCESS 		= "scheduleProcess";
	public String PROPERTIES_PROCESS 	= "propertiesProcess";
	public String NODES_PROCESS			= "nodesProcess";

	public DSLAMBusDesktopProjectConfigure() {
		
		root = new DeckPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECT_CONFIGURE_DECKPANEL);
		
		mainScriptEditor 			= new CRONIOBusDesktopScriptsEditor();
		rollBackScriptEditor 		= new CRONIOBusDesktopScriptsEditor();
		variablesProcessConfigure 	= new DSLAMBusDesktopProcessConfigureVariables();
		scheduleProcessConfigure 	= new DSLAMBusDesktopProcessConfigureSchedule();
		propertiesProcessConfigure 	= new DSLAMBusDesktopProcessConfigureProperties();
		
		root.add(mainScriptEditor);
		root.add(rollBackScriptEditor);
		root.add(variablesProcessConfigure);
		root.add(scheduleProcessConfigure);
		root.add(propertiesProcessConfigure);
		
	}
	
	public void reset() {
		//optionsZone.reset();
		this.setVisibility(Visibility.HIDDEN);
	}
	
	public void showSection(String itemId) {
		
		if (MAIN_SCRIPT.equals(itemId)) {
			root.showWidget(0);
		} else if (ROLLBACK_SCRIPT.equals(itemId)) {
			root.showWidget(1);
		} else if (VARIABLE_PROCESS.equals(itemId)) {
			root.showWidget(2);
		} else if (SCHEDULE_PROCESS.equals(itemId)) {
			root.showWidget(3);
		} else if (PROPERTIES_PROCESS.equals(itemId)) {
			root.showWidget(4);
		} else if (NODES_PROCESS.equals(itemId)) {
			root.showWidget(5);
		}
	}
	
	/**
	 * AEGWTCompositePanel
	 */
	
	public void postDisplay() {
		super.postDisplay();
		//optionsZone.postDisplay();
	}
	
	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		//optionsZone.setData(data);
	}
	
	public AEMFTMetadataElementComposite getData() {
		//return optionsZone.getData();
		return null;
	}
}