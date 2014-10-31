package com.imotion.dslam.front.business.desktop.client.widget.layout;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOIProject;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.label.AEGWTLabel;

public class CRONIOBusDesktopProjectsLayoutItemHeader extends AEGWTCompositePanel {
	
	public		static final String			 NAME	= "CRONIOBusDesktopProjectsLayoutItemHeader";
	private		static final CRONIOBusI18NTexts TEXTS	= GWT.create(CRONIOBusI18NTexts.class);
	
	private AEGWTLabel		sectionName;
	private AEGWTLabel 		projectName;
	private AEGWTLabel 		modifiedIndicator;

	public CRONIOBusDesktopProjectsLayoutItemHeader() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(CRONIOBusDesktopIStyleConstants.PROJECTS_LAYOUT_ZONE_HEADER);
		root.addStyleName(CRONIOBusDesktopIStyleConstants.PROJECTS_LAYOUT_WORK_ZONE_HEADER);
		
		//INFO
		FlowPanel infoZone = new FlowPanel();
		root.add(infoZone);
		infoZone.addStyleName(CRONIOBusDesktopIStyleConstants.PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE);
		
		modifiedIndicator = new AEGWTLabel("");
		infoZone.add(modifiedIndicator);
		modifiedIndicator.addStyleName(CRONIOBusDesktopIStyleConstants.PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_MOD_INDICATOR);
		
		sectionName = new AEGWTLabel("Section");
		infoZone.add(sectionName);
		sectionName.addStyleName(CRONIOBusDesktopIStyleConstants.PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_SECTION_NAME);
		
		AEGWTLabel separator = new AEGWTLabel("-");
		infoZone.add(separator);
		separator.addStyleName(CRONIOBusDesktopIStyleConstants.PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_SEPARATOR);
		
		projectName = new AEGWTLabel("Project");
		infoZone.add(projectName);
		projectName.addStyleName(CRONIOBusDesktopIStyleConstants.PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_PROJECT_NAME);
	}
	
	public void setProyectName(String projectNameValue) {
		projectName.setText(projectNameValue);
	}
	
	public void setSectionNameFromId(String sectionId) {
		String sectionNameValue = "";
		String dateRegEx 		= "[0-9]{2}\\-[0-9]{2}\\-[0-9]{4}\\s([0-9]{2})\\:([0-9]{2})\\:([0-9]{2}).*";
		if (CRONIOBOIProject.PROJECT_MAIN_SCRIPT.equals(sectionId)) {
			sectionNameValue = TEXTS.main_script_label();
		} else if (CRONIOBOIProject.PROJECT_ROLLBACK_SCRIPT.equals(sectionId)) {
			sectionNameValue = TEXTS.rollback_script_label();
		} else if (CRONIOBOIProject.PROJECT_PROCESS_VARIABLE_LIST.equals(sectionId)) {
			sectionNameValue = TEXTS.variables();
		} else if (CRONIOBOIProject.PROJECT_PROCESS_SCHEDULE_LIST.equals(sectionId)) {
			sectionNameValue = TEXTS.schedule();
		} else if (CRONIOBOIProject.PROJECT_PROCESS_EXTRA_OPTIONS.equals(sectionId)) {
			sectionNameValue = TEXTS.properties();
		} else if (sectionId.contains(CRONIOBOIProject.PROJECT_PROCESS_NODE_LISTS)) {
			String[] sectionIdSplit = sectionId.split("\\.");
			sectionNameValue = TEXTS.enviroment() + "(" + sectionIdSplit[2] +")";
		} else if (CRONIOBOIProject.PROJECT_EXECUTION_CONSOLE.equals(sectionId)) {
			sectionNameValue = TEXTS.console_label();
		} else if (sectionId.matches(dateRegEx)) {
			sectionNameValue = TEXTS.log() + " " + sectionId;
		}
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
