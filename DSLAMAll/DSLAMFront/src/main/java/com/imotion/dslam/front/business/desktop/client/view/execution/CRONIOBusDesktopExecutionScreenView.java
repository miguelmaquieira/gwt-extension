package com.imotion.dslam.front.business.desktop.client.view.execution;

import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOIExecution;
import com.imotion.dslam.bom.CRONIOBOIProcess;
import com.imotion.dslam.bom.CRONIOBOIProject;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.execution.CRONIOBusDesktopExecutionDisplay;
import com.imotion.dslam.front.business.desktop.client.view.CRONIOBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.widget.execution.CRONIOBusDesktopAccordionLoggerContainer;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopExecutionScreenView extends CRONIOBusDesktopPanelBaseView implements CRONIOBusDesktopExecutionDisplay {

	public static final String NAME = "CRONIOBusDesktopExecutionScreenView";
	
	private FlowPanel	root;
	private CRONIOBusDesktopAccordionLoggerContainer 	logger;	
	
	public CRONIOBusDesktopExecutionScreenView() {
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(CRONIOBusDesktopIStyleConstants.EXECUTION);
		setHeightToDecrease(78);
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
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		if (data != null) {
			root.clear();
		
			AEMFTMetadataElementComposite processData = data.getCompositeElement(CRONIOBOIProject.PROJECT_PROCESS);
			if (processData != null) {
				String processId = getElementController().getElementAsString(CRONIOBOIProcess.PROCESS_ID, processData);
				logger = new CRONIOBusDesktopAccordionLoggerContainer(processId);
				root.add(logger);
				logger.postDisplay();
				logger.setSize("100%", "100%");
				logger.setFilterVisible(false);
				logger.setPagerVisible(false);
			
				AEMFTMetadataElementComposite consoleData = getElementController().getElementAsComposite(CRONIOBOIProject.PROJECT_EXECUTION_CONSOLE, data);
				if (consoleData != null) {
					logger.setData(consoleData);
			
				} 
				
			} else {
				
				AEGWTLogicalEvent getLogEvt = new AEGWTLogicalEvent(getWindowName(), getName());
				getLogEvt.addElement(CRONIOBOIExecution.EXECUTION_DATA, data);
				getLogEvt.setEventType(LOGICAL_TYPE.GET_EVENT);
				getLogicalEventHandlerManager().fireEvent(getLogEvt);
				
			}
		}
	}

//	/**
//	 * CRONIOBusProjectBaseDisplay
//	 */
//
//	@Override
//	public void beforeExitSection() {
//		if (logger != null) {
//			logger.beforeExitSection();	
//		}
//	}
}
