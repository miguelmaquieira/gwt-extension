package com.imotion.dslam.front.business.desktop.client.widget.layout;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.label.AEGWTLabel;

public class CRONIOBusDesktopPreferencesLayoutItemHeader extends AEGWTCompositePanel {
	
	public		static final String			 NAME	= "CRONIOBusDesktopPreferencesLayoutItemHeader";
	private		static final DSLAMBusI18NTexts TEXTS	= GWT.create(DSLAMBusI18NTexts.class);
	
	private AEGWTLabel		sectionName;
	private AEGWTLabel 		finalSectionName;
	private AEGWTLabel 		modifiedIndicator;

	public CRONIOBusDesktopPreferencesLayoutItemHeader() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PREFERENCES_LAYOUT_ZONE_HEADER);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PREFERENCES_LAYOUT_WORK_ZONE_HEADER);
		
		//INFO
		FlowPanel infoZone = new FlowPanel();
		root.add(infoZone);
		infoZone.addStyleName(DSLAMBusDesktopIStyleConstants.PREFERENCES_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE);
		
		modifiedIndicator = new AEGWTLabel("");
		infoZone.add(modifiedIndicator);
		modifiedIndicator.addStyleName(DSLAMBusDesktopIStyleConstants.PREFERENCES_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_MOD_INDICATOR);
		
		sectionName = new AEGWTLabel("Section");
		infoZone.add(sectionName);
		sectionName.addStyleName(DSLAMBusDesktopIStyleConstants.PREFERENCES_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_SECTION_NAME);
		
		AEGWTLabel separator = new AEGWTLabel("-");
		infoZone.add(separator);
		separator.addStyleName(DSLAMBusDesktopIStyleConstants.PREFERENCES_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_SEPARATOR);
		
		finalSectionName = new AEGWTLabel("FinalSection");
		infoZone.add(finalSectionName);
		finalSectionName.addStyleName(DSLAMBusDesktopIStyleConstants.PREFERENCES_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_PREFERENCES_NAME);
	}
	
	public void setMachineName(String finalSectionNameStr) {
		finalSectionName.setText(finalSectionNameStr);
	}
	
	public void setSectionNameFromId(String sectionId) {
		String sectionNameValue = "";
		if (CRONIOBOIMachineProperties.MACHINE_CONNECTION_SCRIPT.equals(sectionId)) {
			sectionNameValue = TEXTS.connection_script();
		} else if (CRONIOBOIMachineProperties.MACHINE_DISCONNECTION_SCRIPT.equals(sectionId)) {
			sectionNameValue = TEXTS.disconnection_script();
		} else if (CRONIOBOIMachineProperties.MACHINE_VARIABLES.equals(sectionId)) {
			sectionNameValue = TEXTS.variables();
		} else if (CRONIOBOIMachineProperties.MACHINE_CONNECTION_CONFIG.equals(sectionId)) {
			sectionNameValue = TEXTS.config();
		}
//		else if (DSLAMBOIProject.PROJECT_ROLLBACK_SCRIPT.equals(sectionId)) {
//			sectionNameValue = TEXTS.rollback_script_label();
//		} 
		sectionName.setText(sectionNameValue);
	}
	
	public void setModified(boolean modified) {
		String txt = "";
		if (modified) {
			txt = "*";
		}
		modifiedIndicator.setText(txt);
	}

	/**
	 * AEGWTICompositePanel
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
