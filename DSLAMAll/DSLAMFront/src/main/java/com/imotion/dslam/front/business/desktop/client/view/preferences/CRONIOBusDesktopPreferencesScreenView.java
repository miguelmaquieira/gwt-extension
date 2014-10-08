package com.imotion.dslam.front.business.desktop.client.view.preferences;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.preferences.CRONIOBusDesktopPreferencesDisplay;
import com.imotion.dslam.front.business.desktop.client.view.CRONIOBusDesktopPanelBaseView;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopPreferencesScreenView extends CRONIOBusDesktopPanelBaseView implements CRONIOBusDesktopPreferencesDisplay, AEGWTHasLogicalEventHandlers {

	public		static final String				NAME = "CRONIOBusDesktopPreferencesScreenView";
	private	 	static final CRONIOBusI18NTexts 	TEXTS = GWT.create(CRONIOBusI18NTexts.class);
	
	private FlowPanel 									root;
	
	
	public CRONIOBusDesktopPreferencesScreenView() {
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(CRONIOBusDesktopIStyleConstants.PREFERENCES_VIEW);
	}
	
	/**
	 * AEGWTICompositePanel
	 */
	public String getName() {
		return NAME;
	}

	@Override
	public void postDisplay() {

	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		
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
