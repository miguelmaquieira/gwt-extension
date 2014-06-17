package com.imotion.dslam.front.business.desktop.client.widget.layout;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.label.AEGWTLabel;

public class CRONIOBusDesktopProjectsLayoutItemHeader extends AEGWTCompositePanel {
	
	public static final String NAME  = "CRONIOBusDesktopProjectsLayoutItemHeader";
	
	private FlowPanel		actionsZone;
	private AEGWTLabel		sectionName;

	private AEGWTLabel projectName;

	public CRONIOBusDesktopProjectsLayoutItemHeader() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_WORK_ZONE_HEADER);
		
		//INFO
		FlowPanel infoZone = new FlowPanel();
		root.add(infoZone);
		infoZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE);
		infoZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_6);
		
		AEGWTLabel modifiedIndicator = new AEGWTLabel("");
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
		infoZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_WORK_ZONE_HEADER_INFO_ZONE_PROJECT_NAME);
		
		//ACTIONS
		actionsZone = new FlowPanel();
		root.add(actionsZone);
		actionsZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_WORK_ZONE_HEADER_ACTIONS_ZONE);
		actionsZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_6);
	}
	
	public void addActionWidget(Widget actionWidget, String containerStyle) {
		FlowPanel container = new FlowPanel();
		actionsZone.add(container);
		container.addStyleName(containerStyle);
		container.add(actionWidget);
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
