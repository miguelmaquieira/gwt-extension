package com.imotion.dslam.front.business.desktop.client.widget.toolbar;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;

public class CRONIOBusDesktopPreferencesToolbar extends AEGWTCompositePanel {
	
	public static final String NAME = "CRONIOBusDesktopPreferencesToolbar";
	
	private CRONIOBusDesktopPreferencesToolbarActions 	preferencesActions;

	public CRONIOBusDesktopPreferencesToolbar() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(CRONIOBusDesktopIStyleConstants.TOOLBAR);
	
		//ACTIONS
		SimplePanel preferencesActionsZone = new SimplePanel();
		root.add(preferencesActionsZone);
		preferencesActionsZone.addStyleName(CRONIOBusDesktopIStyleConstants.TOOLBAR_ACTIONS_ZONE);
	
		preferencesActions = new CRONIOBusDesktopPreferencesToolbarActions();
		preferencesActionsZone.add(preferencesActions);
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
	}
	
	public CRONIOBusDesktopPreferencesToolbarActions getActions() {
		return preferencesActions;	
	}
	
	public void setSavePreferencesEnabled(boolean enabled) {
		preferencesActions.setSaveEnabled(enabled);
	}
	
	public void hideConnectionForm() {
		preferencesActions.hideConnectionForm();
	}
	
	public void reset() {
		preferencesActions.reset();
	}
	
	public void setModified(boolean modified) {
		preferencesActions.setSaveEnabled(modified);
	}

	/**
	 *	AEGWTICompositePanel 
	 */
	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		
	}
}
