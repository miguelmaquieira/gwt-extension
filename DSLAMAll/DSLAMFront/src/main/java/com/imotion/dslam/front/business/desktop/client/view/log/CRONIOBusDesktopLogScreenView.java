package com.imotion.dslam.front.business.desktop.client.view.log;

import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOIExecution;
import com.imotion.dslam.business.service.CRONIOBUILogBusinessServiceConstants;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopHasProjectEventHandlers;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.presenter.log.CRONIOBusDesktopLogDisplay;
import com.imotion.dslam.front.business.desktop.client.view.CRONIOBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.widget.execution.CRONIOBusDesktopAccordionLoggerContainer;
import com.imotion.dslam.front.business.desktop.client.widget.execution.CRONIOBusDesktopLoggerSectionsDeckPanel;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopLogScreenView extends CRONIOBusDesktopPanelBaseView implements CRONIOBusDesktopLogDisplay, CRONIOBusDesktopHasProjectEventHandlers {

	public static final String NAME = "CRONIOBusDesktopLogScreenView";
	
	private FlowPanel	root;
	private CRONIOBusDesktopAccordionLoggerContainer 	logger;
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
		getLogicalEventHandlerManager().addEventHandler(CRONIOBusDesktopHasProjectEventHandlers.TYPE, this);
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		if (data != null) {
			AEMFTMetadataElementComposite executionLogsData = (AEMFTMetadataElementComposite) data.getElement(CRONIOBUILogBusinessServiceConstants.EXECUTION_LOGS_DATA);
			AEMFTMetadataElementComposite filteredLogsData = (AEMFTMetadataElementComposite) data.getElement(CRONIOBUILogBusinessServiceConstants.FILTERED_LOGS_DATA);
			
			if (executionLogsData == null && filteredLogsData == null) {
				root.clear();
				
				AEMFTMetadataElementSingle projectIdData = (AEMFTMetadataElementSingle) data.getElement(CRONIOBOIExecution.PROJECT_ID);
				String projectId = projectIdData.getValueAsString();
					
				logger = new CRONIOBusDesktopAccordionLoggerContainer(projectId);
				root.add(logger);
				logger.postDisplay();
				logger.setSize("100%", "100%");
				
				AEGWTLogicalEvent getLogEvt = new AEGWTLogicalEvent(getWindowName(), getName());
				getLogEvt.addElement(CRONIOBOIExecution.EXECUTION_DATA, data);
				getLogEvt.setEventType(LOGICAL_TYPE.GET_EVENT);
				getLogicalEventHandlerManager().fireEvent(getLogEvt);
			} else {
				logger.setData(data);
			}	
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
}
