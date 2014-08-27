package com.imotion.dslam.front.business.desktop.client.widget.layout;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.label.AEGWTLabel;

public class CRONIOBusDesktopProjectsLayoutItemHeader extends AEGWTCompositePanel {
	
	public		static final String			 NAME	= "CRONIOBusDesktopProjectsLayoutItemHeader";
	private		static final DSLAMBusI18NTexts TEXTS	= GWT.create(DSLAMBusI18NTexts.class);
	
	private AEGWTLabel		sectionName;
	private AEGWTLabel 		projectName;
	private AEGWTLabel 		modifiedIndicator;

	public CRONIOBusDesktopProjectsLayoutItemHeader() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_ZONE_HEADER);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_WORK_ZONE_HEADER);
		
		//INFO
		FlowPanel infoZone = new FlowPanel();
		root.add(infoZone);
		infoZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE);
		
		modifiedIndicator = new AEGWTLabel("");
		infoZone.add(modifiedIndicator);
		modifiedIndicator.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_MOD_INDICATOR);
		
		sectionName = new AEGWTLabel("Section");
		infoZone.add(sectionName);
		sectionName.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_SECTION_NAME);
		
		AEGWTLabel separator = new AEGWTLabel("-");
		infoZone.add(separator);
		separator.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_SEPARATOR);
		
		projectName = new AEGWTLabel("Project");
		infoZone.add(projectName);
		projectName.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_PROJECT_NAME);
	}
	
	public void setProyectName(String projectNameValue) {
		projectName.setText(projectNameValue);
	}
	
	public void setSectionNameFromId(String sectionId) {
		String sectionNameValue = "";
		if (DSLAMBOIProject.PROJECT_MAIN_SCRIPT.equals(sectionId)) {
			sectionNameValue = TEXTS.main_script_label();
		} else if (DSLAMBOIProject.PROJECT_ROLLBACK_SCRIPT.equals(sectionId)) {
			sectionNameValue = TEXTS.rollback_script_label();
		} else if (DSLAMBOIProject.PROJECT_PROCESS_VARIABLE_LIST.equals(sectionId)) {
			sectionNameValue = TEXTS.variables();
		} else if (DSLAMBOIProject.PROJECT_PROCESS_SCHEDULE_LIST.equals(sectionId)) {
			sectionNameValue = TEXTS.schedule();
		} else if (DSLAMBOIProject.PROJECT_PROCESS_EXTRA_OPTIONS.equals(sectionId)) {
			sectionNameValue = TEXTS.properties();
		} else if (DSLAMBOIProject.PROJECT_PROCESS_NODE_LIST.equals(sectionId)) {
			sectionNameValue = TEXTS.nodes();
		} else if (DSLAMBOIProject.PROJECT_EXECUTION_CONSOLE.equals(sectionId)) {
			sectionNameValue = TEXTS.console_label();
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
