package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.DeckPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;


public class DSLAMBusDesktopProcessConfigure extends AEGWTCompositePanel  {

	public static final String NAME = "DSLAMBusDesktopProcessConfigure";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	//private FlowPanel 									root;
	private DeckPanel 											root;
	private AceEditor											mainScriptConfigure;
	private AceEditor											rollBackScriptConfigure;
	private DSLAMBusDesktopProcessConfigureOptionsVariables		variablesProcessConfigure;
	private DSLAMBusDesktopProcessConfigureOptionsSchedule		scheduleProcessConfigure;
	private DSLAMBusDesktopProcessConfigureOptionsProperties	propertiesProcessConfigure;
	//private DSLAMBusDesktopProcessConfigureOptions		optionsZone;
	//private DSLAMBusDesktopProcessConfigureNodes		nodeZone;
	
	public String MAIN_SCRIPT 			= "mainScritp";
	public String ROLLBACK_SCRIPT 		= "rollbackScritp";
	public String VARIABLE_PROCESS 		= "variableProcess";
	public String SCHEDULE_PROCESS 		= "scheduleProcess";
	public String PROPERTIES_PROCESS 	= "propertiesProcess";
	public String NODES_PROCESS			= "nodesProcess";

	public DSLAMBusDesktopProcessConfigure() {
		
		root = new DeckPanel();
		//root = new FlowPanel();
		initWidget(root);
		//root.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECT_CONFIGURE_DECKPANEL);
		
		mainScriptConfigure 		= new AceEditor();
		rollBackScriptConfigure 	= new AceEditor();
		variablesProcessConfigure 	= new DSLAMBusDesktopProcessConfigureOptionsVariables();
		scheduleProcessConfigure 	= new DSLAMBusDesktopProcessConfigureOptionsSchedule();
		propertiesProcessConfigure 	= new DSLAMBusDesktopProcessConfigureOptionsProperties();
		
		root.add(mainScriptConfigure);
		root.add(rollBackScriptConfigure);
		root.add(variablesProcessConfigure);
		root.add(scheduleProcessConfigure);
		root.add(propertiesProcessConfigure);
		
		

//		optionsZone = new DSLAMBusDesktopProcessConfigureOptions();
//		root.add(optionsZone);
//		optionsZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_OPTIONS_ZONE);
//
//		nodeZone = new DSLAMBusDesktopProcessConfigureNodes();
//		root.add(nodeZone);
//		nodeZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODES_ZONE);
//		nodeZone.addStyleName(AEGWTIBoostrapConstants.ROW);
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