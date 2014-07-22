package com.imotion.dslam.front.business.desktop.client.widget.toolbar;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;

public class DSLAMBusDesktopPreferencesToolbar extends AEGWTCompositePanel {
	
	public static final String NAME = "DSLAMBusDesktopPreferencesToolbar";
	
	private DSLAMBusDesktopPreferencesToolbarActions 	preferencesActions;

	public DSLAMBusDesktopPreferencesToolbar() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.TOOLBAR);
	
		//ACTIONS
		SimplePanel preferencesActionsZone = new SimplePanel();
		root.add(preferencesActionsZone);
		preferencesActionsZone.addStyleName(DSLAMBusDesktopIStyleConstants.TOOLBAR_ACTIONS_ZONE);
	
		preferencesActions = new DSLAMBusDesktopPreferencesToolbarActions();
		preferencesActionsZone.add(preferencesActions);
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
	}
	
	public DSLAMBusDesktopPreferencesToolbarActions getActions() {
		return preferencesActions;	
	}
	
	public void setSavePreferencesEnabled(boolean enabled) {
		preferencesActions.setSaveEnabled(enabled);
	}
	
	public void hideConnectionForm() {
		preferencesActions.hideConnectionForm();
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
