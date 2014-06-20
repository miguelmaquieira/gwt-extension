package com.imotion.dslam.front.business.desktop.client.widget.toolbar;

import java.util.Date;

import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;

public class DSLAMBusDesktopToolbar extends AEGWTCompositePanel {
	
	public static final String NAME = "DSLAMBusDesktopToolbar";
	
	private DSLAMBusDesktopToolbarActions 	projectActions;
	private DSLAMBusDesktopToolbarInfo	 	projectInfo;

	public DSLAMBusDesktopToolbar() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.TOOLBAR);
	
		//ACTIONS
		SimplePanel projectActionsZone = new SimplePanel();
		root.add(projectActionsZone);
		projectActionsZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_3);
		projectActionsZone.addStyleName(DSLAMBusDesktopIStyleConstants.TOOLBAR_ACTIONS_ZONE);
	
		projectActions = new DSLAMBusDesktopToolbarActions();
		projectActionsZone.add(projectActions);
		
		//INFO
		SimplePanel projectInfoZone = new SimplePanel();
		root.add(projectInfoZone);
		projectInfoZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_9);
		projectInfoZone.addStyleName(DSLAMBusDesktopIStyleConstants.TOOLBAR_INFO_ZONE);
		
		projectInfo = new DSLAMBusDesktopToolbarInfo();
		projectInfoZone.add(projectInfo);
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
	}
	
	public void setSaveProjectEnabled(boolean enabled) {
		projectActions.setSaveEnabled(enabled);
	}

	public void setSaveAllProjectsEnabled(boolean enabled) {
		projectActions.setSaveAllEnabled(enabled);
	}
	
	public void setModified(boolean modified) {
		projectActions.setSaveEnabled(modified);
		projectActions.setSaveAllEnabled(modified);
		projectInfo.setModified(modified);
	}
	
	public boolean isModified() {
		return projectInfo.isModified();
	}
	
	public void setLastSaved(Date date) {
		projectInfo.setLastSaved(date);
	}
	
	public void setMainTitleText(String text) {
		projectInfo.setMainTitleText(text);
	}
	
	public void setSecondaryTitleText(String text) {
		projectInfo.setSecondaryTitleText(text);
	}
	
	public void setFileInfoVisible(boolean visible) {
		if (visible) {
			projectInfo.setVisibility(Visibility.VISIBLE);
		} else {
			projectInfo.setVisibility(Visibility.HIDDEN);
		}
	}
	
	public DSLAMBusDesktopToolbarInfo getInfo() {
		return projectInfo;	
	}
	
	public DSLAMBusDesktopToolbarActions getActions() {
		return projectActions;	
	}
	
	public void reset() {
		projectActions.reset();
		projectInfo.reset();
	}

	/**
	 *	AEGWTICompositePanel 
	 */
	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public void setId(String id) {
		super.setId(id);
		projectActions.setId(id);
	}
	
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		if (data != null) {
			String	projectId	 	= getElementController().getElementAsString(DSLAMBOIProject.PROJECT_ID				, data);
			String	projectName 	= getElementController().getElementAsString(DSLAMBOIProject.PROJECT_NAME			, data);
			Date	lastSaved		= (Date) getElementController().getElementAsSerializable(DSLAMBOIProject.SAVED_TIME	, data);
			setId(projectId);
			
			String processIdStr = String.valueOf(projectId);
			setId(processIdStr);
			setLastSaved(lastSaved);
			setSecondaryTitleText(projectName);
			getInfo().setVisible(true);
		}
	}

}
