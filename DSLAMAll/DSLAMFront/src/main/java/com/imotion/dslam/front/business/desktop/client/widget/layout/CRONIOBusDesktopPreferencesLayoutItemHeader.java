package com.imotion.dslam.front.business.desktop.client.widget.layout;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.label.AEGWTLabel;

public class CRONIOBusDesktopPreferencesLayoutItemHeader extends AEGWTCompositePanel {
	
	public		static final String			 NAME	= "CRONIOBusDesktopPreferencesLayoutItemHeader";
	private		static final CRONIOBusI18NTexts TEXTS	= GWT.create(CRONIOBusI18NTexts.class);
	
	private AEGWTLabel		sectionName;
	private AEGWTLabel 		finalSectionName;
	private AEGWTLabel 		modifiedIndicator;

	public CRONIOBusDesktopPreferencesLayoutItemHeader() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(CRONIOBusDesktopIStyleConstants.PREFERENCES_LAYOUT_WORK_ZONE_HEADER);
		
		//INFO
		FlowPanel infoZone = new FlowPanel();
		root.add(infoZone);
		infoZone.addStyleName(CRONIOBusDesktopIStyleConstants.PREFERENCES_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE);
		
		modifiedIndicator = new AEGWTLabel("");
		infoZone.add(modifiedIndicator);
		modifiedIndicator.addStyleName(CRONIOBusDesktopIStyleConstants.PREFERENCES_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_MOD_INDICATOR);
		
		finalSectionName = new AEGWTLabel("FinalSection");
		infoZone.add(finalSectionName);
		finalSectionName.addStyleName(CRONIOBusDesktopIStyleConstants.PREFERENCES_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_SECTION_NAME);
		
		AEGWTLabel separator = new AEGWTLabel("-");
		infoZone.add(separator);
		separator.addStyleName(CRONIOBusDesktopIStyleConstants.PREFERENCES_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_SEPARATOR);
		
		sectionName = new AEGWTLabel("Section");
		infoZone.add(sectionName);
		sectionName.addStyleName(CRONIOBusDesktopIStyleConstants.PREFERENCES_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_PREFERENCES_NAME);	
	}
	
	public void setSectionName(String sectionNameStr) {
		sectionName.setText(sectionNameStr);
	}
	
	
	public void setFinalSectionNameFromId(String finalSectionId) {
		String finalSectionNameValue = "";
		if (CRONIOBOIMachineProperties.MACHINE_CONNECTION_SCRIPT.equals(finalSectionId)) {
			finalSectionNameValue = TEXTS.connection_script();
		} else if (CRONIOBOIMachineProperties.MACHINE_DISCONNECTION_SCRIPT.equals(finalSectionId)) {
			finalSectionNameValue = TEXTS.disconnection_script();
		} else if (CRONIOBOIMachineProperties.MACHINE_VARIABLES.equals(finalSectionId)) {
			finalSectionNameValue = TEXTS.variables();
		} else if (CRONIOBOIMachineProperties.MACHINE_CONNECTION_CONFIG.equals(finalSectionId)) {
			finalSectionNameValue = TEXTS.config();
		} else if (CRONIOBOIPreferences.USER_CONFIG.equals(finalSectionId)) {
			finalSectionNameValue = TEXTS.config();
		} 
		finalSectionName.setText(finalSectionNameValue);
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
