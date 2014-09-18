package com.imotion.dslam.front.business.desktop.client.view.process;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.process.CRONIOBusDesktopProcessDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.CRONIOBusDesktopProcessSectionsDeckPanel;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopProcessScreenView extends DSLAMBusDesktopPanelBaseView implements CRONIOBusDesktopProcessDisplay, AEGWTHasLogicalEventHandlers {

	public		static final String				NAME = "CRONIOBusDesktopProcessScreenView";
	private	 	static final DSLAMBusI18NTexts 	TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private FlowPanel 									root;
	private CRONIOBusDesktopProcessSectionsDeckPanel	processSectionsDeckPanel;
	
	public CRONIOBusDesktopProcessScreenView() {
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_VIEW);

		processSectionsDeckPanel = new CRONIOBusDesktopProcessSectionsDeckPanel();
		root.add(processSectionsDeckPanel);
		processSectionsDeckPanel.setVisibility(Visibility.HIDDEN);	
	}

	@Override
	public void openProcessSection(String sectionId ,AEMFTMetadataElementComposite processData) {
		processSectionsDeckPanel.showSection(sectionId, processData);
		processSectionsDeckPanel.setVisibility(Visibility.VISIBLE);	
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
		processSectionsDeckPanel.postDisplay();
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {

	}

	@Override
	public void updateProcess(AEMFTMetadataElementComposite processData) {
		// TODO Auto-generated method stub
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
	
	/**
	 * CRONIOBusProjectBaseDisplay
	 */
	
	@Override
	public void beforeExitSection() {
	}
	
	/************************************************************************
	 *                        PROTECTED FUNCTIONS
	 ************************************************************************/
	/************************************************************************
	 *                        PRIVATE FUNCTIONS
	 ************************************************************************/
}
