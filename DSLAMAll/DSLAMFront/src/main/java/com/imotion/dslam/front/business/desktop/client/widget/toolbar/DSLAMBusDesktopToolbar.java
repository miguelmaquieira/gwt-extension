package com.imotion.dslam.front.business.desktop.client.widget.toolbar;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;

public class DSLAMBusDesktopToolbar extends AEGWTCompositePanel {
	
	public static final String NAME = "DSLAMBusDesktopToolbar";
	
	private DSLAMBusDesktopToolbarActions 	projectActions;

	public DSLAMBusDesktopToolbar() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.TOOLBAR);
	
		//ACTIONS
		SimplePanel projectActionsZone = new SimplePanel();
		root.add(projectActionsZone);
		projectActionsZone.addStyleName(DSLAMBusDesktopIStyleConstants.TOOLBAR_ACTIONS_ZONE);
	
		projectActions = new DSLAMBusDesktopToolbarActions();
		projectActionsZone.add(projectActions);
	}
	
	public void hideProjectForm() {
		projectActions.hideProjectForm();
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
		setExecuteEnabled(modified);
	}
	
	public void setExecuteEnabled(boolean enabled) {
		projectActions.setExecuteEnabled(enabled);
	}
	
	public DSLAMBusDesktopToolbarActions getActions() {
		return projectActions;	
	}
	
	public void reset() {
		projectActions.reset();
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
			setId(projectId);
			
			String processIdStr = String.valueOf(projectId);
			setId(processIdStr);
		}
	}

}
