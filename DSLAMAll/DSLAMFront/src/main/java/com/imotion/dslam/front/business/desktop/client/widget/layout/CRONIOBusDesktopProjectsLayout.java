package com.imotion.dslam.front.business.desktop.client.widget.layout;

import java.util.Date;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.widget.navigator.DSLAMBusDesktopProjectNavigator;
import com.imotion.dslam.front.business.desktop.client.widget.toolbar.DSLAMBusDesktopToolbar;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;

public class CRONIOBusDesktopProjectsLayout extends AEGWTCompositePanel implements CRONIOBusDesktopIsLayout {

	public 		final static String 	NAME 			= "CRONIOBusDesktopProjectsLayout";
	public	 	final static String	NO_PROJECT_ID 	= "NO_PROJECT_ID";
	
	
	private FlowPanel 									root;
	private DSLAMBusDesktopToolbar						toolbar;
	private DSLAMBusDesktopProjectNavigator				projectListNavigator;
	private CRONIOBusDesktopProjectsLayoutItemHeader	sectionHeader;
	private FlowPanel									projectWorkZone;
	
	public CRONIOBusDesktopProjectsLayout() {
		root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT);

		toolbar = new DSLAMBusDesktopToolbar();
		root.add(toolbar);
		toolbar.setMainTitleText("script1");
		toolbar.setModified(false);
		toolbar.setLastSaved(new Date());
		toolbar.getInfo().setVisible(false);
		toolbar.setId(NO_PROJECT_ID);

		//Bottom Zone
		FlowPanel bottomZone = new FlowPanel();
		root.add(bottomZone);
		bottomZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_BOTTOM_ZONE);

		//Bottom Zone - Projectlist zone
		FlowPanel projectListZone = new FlowPanel();
		bottomZone.add(projectListZone);
		projectListZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_3);
		projectListZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_LIST_ZONE);
		
		projectListNavigator = new DSLAMBusDesktopProjectNavigator();
		projectListZone.add(projectListNavigator);

		//Bottom Zone - Right
		FlowPanel bottomRightZone = new FlowPanel();
		bottomZone.add(bottomRightZone);
		bottomRightZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_BOTTOM_ZONE_RIGHT);
		
		sectionHeader = new CRONIOBusDesktopProjectsLayoutItemHeader();
		bottomRightZone.add(sectionHeader);
		
		projectWorkZone = new FlowPanel();
		bottomRightZone.add(projectWorkZone);
		projectWorkZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_9);
		projectWorkZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_WORK_ZONE);
	}
	
	public void setLayoutContent(Widget content) {
		projectWorkZone.clear();
		projectWorkZone.add(content);
	}
	
	public void setProyectName(String projectNameValue) {
		sectionHeader.setProyectName(projectNameValue);
	}
	
	public void setSectionNameFromId(String sectionId) {
		sectionHeader.setSectionNameFromId(sectionId);
	}
	
	public void setModified(boolean modified) {
		sectionHeader.setModified(modified);
	}

	/**
	 * AEGWTCompositePanel
	 */
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		if (data != null) {
			projectListNavigator.setData(data);
		}
	}
	
	@Override
	public void postDisplay() {
		super.postDisplay();
		setHeightToDecrease(90);
		projectListNavigator.postDisplay();
		sectionHeader.postDisplay();
	}

}
