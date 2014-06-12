package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.DeckPanel;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.widget.editor.CRONIOBusDesktopScriptsEditor;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;


public class DSLAMBusDesktopProjectSectionsDeckPanel extends AEGWTCompositePanel  {

	public static final String NAME = "DSLAMBusDesktopProjectSectionsDeckPanel";
	
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	
	private DeckPanel 										rootDeckPanel;
	private CRONIOBusDesktopScriptsEditor					mainScriptEditor;
	private CRONIOBusDesktopScriptsEditor					rollBackScriptEditor;
	private DSLAMBusDesktopProcessConfigureVariables		variablesProcessConfigure;
	private DSLAMBusDesktopProcessConfigureSchedule			scheduleProcessConfigure;
	private DSLAMBusDesktopProcessConfigureExtraOptions		extraOptionsConfigure;
	//private DSLAMBusDesktopProcessConfigureNodes		nodeZone;
	
	public DSLAMBusDesktopProjectSectionsDeckPanel() {
		
		rootDeckPanel = new DeckPanel();
		initWidget(rootDeckPanel);
		rootDeckPanel.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECT_CONFIGURE_DECKPANEL);
		rootDeckPanel.addStyleName(AEGWTIBoostrapConstants.ROW);
		
		mainScriptEditor 			= new CRONIOBusDesktopScriptsEditor();
		rollBackScriptEditor 		= new CRONIOBusDesktopScriptsEditor();
		variablesProcessConfigure 	= new DSLAMBusDesktopProcessConfigureVariables();
		scheduleProcessConfigure 	= new DSLAMBusDesktopProcessConfigureSchedule();
		extraOptionsConfigure 	= new DSLAMBusDesktopProcessConfigureExtraOptions();
		
		rootDeckPanel.add(mainScriptEditor);
		rootDeckPanel.add(rollBackScriptEditor);
		rootDeckPanel.add(variablesProcessConfigure);
		rootDeckPanel.add(scheduleProcessConfigure);
		rootDeckPanel.add(extraOptionsConfigure);
		
	}
	
	public void reset() {
		//optionsZone.reset();
		this.setVisibility(Visibility.HIDDEN);
	}
	
	public void showSection(String sectionId, AEMFTMetadataElementComposite sectionData) {
		if (DSLAMBOIProject.PROJECT_MAIN_SCRIPT.equals(sectionId)) {
			rootDeckPanel.showWidget(0);
			mainScriptEditor.setData(sectionData);
		} else if (DSLAMBOIProject.PROJECT_ROLLBACK_SCRIPT.equals(sectionId)) {
			rootDeckPanel.showWidget(1);
			rollBackScriptEditor.setData(sectionData);
		} else if (DSLAMBOIProject.PROJECT_PROCESS_VARIABLE_LIST.equals(sectionId)) {
			rootDeckPanel.showWidget(2);
			variablesProcessConfigure.setData(sectionData);
		} else if (DSLAMBOIProject.PROJECT_PROCESS_SCHEDULE_LIST.equals(sectionId)) {
			rootDeckPanel.showWidget(3);
			scheduleProcessConfigure.setData(sectionData);
		} else if (DSLAMBOIProject.PROJECT_PROCESS_EXTRA_OPTIONS.equals(sectionId)) {
			rootDeckPanel.showWidget(4);
			extraOptionsConfigure.setData(sectionData);
		} else if (DSLAMBOIProject.PROJECT_PROCESS_NODES.equals(sectionId)) {
			rootDeckPanel.showWidget(5);
//			nodes.setData(sectionData);
		}
	}
	
	/**
	 * AEGWTCompositePanel
	 */
	
	public void postDisplay() {
		super.postDisplay();
		variablesProcessConfigure.postDisplay();
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
