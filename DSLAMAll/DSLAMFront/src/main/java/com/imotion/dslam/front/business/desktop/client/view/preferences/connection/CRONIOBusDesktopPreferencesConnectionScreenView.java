package com.imotion.dslam.front.business.desktop.client.view.preferences.connection;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.preferences.connection.CRONIOBusDesktopPreferencesConnectionDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopPreferencesConnectionScreenView extends DSLAMBusDesktopPanelBaseView implements CRONIOBusDesktopPreferencesConnectionDisplay, AEGWTHasLogicalEventHandlers {

	public		static final String				NAME = "CRONIOBusDesktopPreferencesConnectionScreenView";
	private	 	static final DSLAMBusI18NTexts 	TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private FlowPanel 									root;
	
	
	public CRONIOBusDesktopPreferencesConnectionScreenView() {
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PREFERENCES_CONNECTION_VIEW);
	}

//	@Override
//	public void openProcessSection(String sectionId ,AEMFTMetadataElementComposite processData) {
//		processSectionsDeckPanel.showSection(sectionId, processData);
//		processSectionsDeckPanel.setVisibility(Visibility.VISIBLE);	
//	}
	
	/**
	 * AEGWTICompositePanel
	 */
	public String getName() {
		return NAME;
	}

	@Override
	public void postDisplay() {
//		super.postDisplay();
//		getLogicalEventHandlerManager().addLogicalEventHandler(this);
//		processSectionsDeckPanel.postDisplay();
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		//processSectionsDeckPanel.setData(data);
		
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
	
//	/**
//	 * CRONIOBusProjectBaseDisplay
//	 */
//	
//	@Override
//	public void beforeExitSection() {
//		// TODO Auto-generated method stub
//		
//	}
	
	/************************************************************************
	 *                        PROTECTED FUNCTIONS
	 ************************************************************************/


	/************************************************************************
	 *                        PRIVATE FUNCTIONS
	 ************************************************************************/
}
