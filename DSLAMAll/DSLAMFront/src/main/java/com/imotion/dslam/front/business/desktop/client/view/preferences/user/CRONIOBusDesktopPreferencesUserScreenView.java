package com.imotion.dslam.front.business.desktop.client.view.preferences.user;

import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.preferences.user.CRONIOBusDesktopPreferencesUserDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.widget.preferences.CRONIOBusDesktopPreferencesUserConfigureForm;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopPreferencesUserScreenView extends DSLAMBusDesktopPanelBaseView implements CRONIOBusDesktopPreferencesUserDisplay, AEGWTHasLogicalEventHandlers {

	public	static final String NAME = "CRONIOBusDesktopPreferencesUserScreenView";

	private FlowPanel 												root;
	private CRONIOBusDesktopPreferencesUserConfigureForm			form;


	public CRONIOBusDesktopPreferencesUserScreenView() {
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PREFERENCES_USER_VIEW);
		
		form = new CRONIOBusDesktopPreferencesUserConfigureForm();
		root.add(form);
		form.setVisible(false);
	}

	@Override
	public void openUserSection(String sectionId ,AEMFTMetadataElementComposite finalSectionData) {
		if (!AEMFTCommonUtilsBase.isEmptyString(sectionId)) {
			form.setData(finalSectionData);
			form.setVisible(true);	
			}
	}

	/**
	 * AEGWTICompositePanel
	 */
	public String getName() {
		return NAME;
	}

	@Override
	public void postDisplay() {
		super.postDisplay();
		getLogicalEventHandlerManager().addLogicalEventHandler(this);
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		form.setData(data);

	}

	/****************************************************************************
	 *                      AEGWTHasLogicalEventHandlers
	 ****************************************************************************/
	@Override
	public void dispatchEvent(AEGWTLogicalEvent evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updatePreferences(AEMFTMetadataElementComposite preferencesData) {
		// TODO Auto-generated method stub

	}

	/************************************************************************
	 *                        PROTECTED FUNCTIONS
	 ************************************************************************/


	/************************************************************************
	 *                        PRIVATE FUNCTIONS
	 ************************************************************************/
}
