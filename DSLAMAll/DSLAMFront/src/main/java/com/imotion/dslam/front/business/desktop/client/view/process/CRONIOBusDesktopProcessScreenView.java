package com.imotion.dslam.front.business.desktop.client.view.process;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopHasProjectEventHandlers;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.presenter.process.CRONIOBusDesktopProcessDisplay;
import com.imotion.dslam.front.business.desktop.client.presenter.process.CRONIOBusDesktopProcessPresenter;
import com.imotion.dslam.front.business.desktop.client.view.CRONIOBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.CRONIOBusDesktopProcessSectionsDeckPanel;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopProcessScreenView extends CRONIOBusDesktopPanelBaseView implements CRONIOBusDesktopProcessDisplay, AEGWTHasLogicalEventHandlers, CRONIOBusDesktopHasProjectEventHandlers {

	public		static final String				NAME = "CRONIOBusDesktopProcessScreenView";
	private	 	static final CRONIOBusI18NTexts 	TEXTS = GWT.create(CRONIOBusI18NTexts.class);
	
	private FlowPanel 									root;
	private CRONIOBusDesktopProcessSectionsDeckPanel	processSectionsDeckPanel;
	
	public CRONIOBusDesktopProcessScreenView() {
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(CRONIOBusDesktopIStyleConstants.PROCESS_VIEW);

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
		getLogicalEventHandlerManager().addEventHandler(CRONIOBusDesktopHasProjectEventHandlers.TYPE, this);
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
	
	/****************************************************************************
	 *                      CRONIOBusDesktopHasProjectEventHandlers
	 ****************************************************************************/
	
	@Override
	public void dispatchEvent(CRONIOBusDesktopProjectEvent evt) {
		EVENT_TYPE evtTyp 	= evt.getEventType();
		String srcWidget	= evt.getSourceWidget();
		
		if (EVENT_TYPE.GET_MACHINE_TYPES.equals(evtTyp) && CRONIOBusDesktopProcessPresenter.NAME.equals(srcWidget)) {
			evt.stopPropagation();
			List<String> machineList = (List<String>) evt.getElementAsSerializableDataValue();
			processSectionsDeckPanel.setMachineTypes(machineList);
		}
		
	}

	@Override
	public boolean isDispatchEventType(EVENT_TYPE type) {
		
		return EVENT_TYPE.GET_MACHINE_TYPES.equals(type);
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
