package com.imotion.dslam.front.business.desktop.client.view.log;

import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.CRONIOBOIProject;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopHasProjectEventHandlers;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.presenter.log.CRONIOBusDesktopLogDisplay;
import com.imotion.dslam.front.business.desktop.client.view.CRONIOBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.widget.execution.CRONIOBusDesktopLoggerNodes;
import com.imotion.dslam.front.business.desktop.client.widget.execution.CRONIOBusDesktopLoggerSectionsDeckPanel;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopLogScreenView extends CRONIOBusDesktopPanelBaseView implements CRONIOBusDesktopLogDisplay, CRONIOBusDesktopHasProjectEventHandlers, AEGWTHasLogicalEventHandlers  {

	public static final String NAME = "CRONIOBusDesktopLogScreenView";

	private FlowPanel	root;
	private CRONIOBusDesktopLoggerSectionsDeckPanel		loggerSectionsDeckPanel;
	
	public CRONIOBusDesktopLogScreenView() {
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(CRONIOBusDesktopIStyleConstants.LOGS);
		setHeightToDecrease(78);

		loggerSectionsDeckPanel = new CRONIOBusDesktopLoggerSectionsDeckPanel();
		root.add(loggerSectionsDeckPanel);
		loggerSectionsDeckPanel.setVisibility(Visibility.HIDDEN);	
	}

	@Override
	public void openLogSection(String sectionId ,AEMFTMetadataElementComposite logData) {
		loggerSectionsDeckPanel.showSection(sectionId, logData);
		loggerSectionsDeckPanel.setVisibility(Visibility.VISIBLE);	
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
		loggerSectionsDeckPanel.postDisplay();
		getLogicalEventHandlerManager().addEventHandler(CRONIOBusDesktopHasProjectEventHandlers.TYPE, this);
		getLogicalEventHandlerManager().addLogicalEventHandler(this);
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		if (data != null) {
			loggerSectionsDeckPanel.setData(data);	
		}
	}

	/**
	 * CRONIOBusProjectBaseDisplay
	 */

	@Override
	public void beforeExitSection() {
	}

	/**
	 * CRONIOBusDesktopHasProjectEventHandlers
	 */

	@Override
	public void dispatchEvent(CRONIOBusDesktopProjectEvent evt) {
		EVENT_TYPE evtTyp = evt.getEventType();
		if (EVENT_TYPE.ADD_EXECUTION_LOGS.equals(evtTyp) || EVENT_TYPE.ADD_FILTERED_LOGS.equals(evtTyp)) {
			AEMFTMetadataElementComposite executionLogsData = evt.getEventData();
			setData(executionLogsData);
		}

	}

	@Override
	public boolean isDispatchEventType(EVENT_TYPE type) {

		return EVENT_TYPE.ADD_EXECUTION_LOGS.equals(type)
				||
				EVENT_TYPE.ADD_FILTERED_LOGS.equals(type);
	}

	/**
	 * AEGWTHasLogicalEventHandlers
	 */

	@Override
	public void dispatchEvent(AEGWTLogicalEvent evt) {
		LOGICAL_TYPE evtTyp = evt.getEventType();
		String sourceWidget = evt.getSourceWidget();
		if (LOGICAL_TYPE.GET_EVENT.equals(evtTyp) && CRONIOBusDesktopLoggerNodes.NAME.equals(sourceWidget)) {
			String nodeName 	= evt.getElementAsString(CRONIOBOINode.NODE_NAME);
			String projectId	= evt.getElementAsString(CRONIOBOIProject.PROJECT_ID);
			AEMFTMetadataElementComposite filterNode = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
			filterNode.addElement(CRONIOBOIProject.PROJECT_ID		, projectId);
			filterNode.addElement(CRONIOBOINode.NODE_NAME			, nodeName);
			
			loggerSectionsDeckPanel.showSection("LOG." + nodeName, filterNode);
		} 	
	} 

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return LOGICAL_TYPE.GET_EVENT.equals(type);

	}
}