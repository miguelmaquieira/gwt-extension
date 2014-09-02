package com.imotion.dslam.front.business.desktop.client.view.execution;

import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOIExecution;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.execution.CRONIOBusDesktopExecutionDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.widget.execution.CRONIOBusDesktopAccordionLoggerContainer;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopExecutionScreenView extends DSLAMBusDesktopPanelBaseView implements CRONIOBusDesktopExecutionDisplay {

	public static final String NAME = "CRONIOBusDesktopExecutionScreenView";
	
	private FlowPanel	root;
	private CRONIOBusDesktopAccordionLoggerContainer logger;
	
	public CRONIOBusDesktopExecutionScreenView() {
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.EXECUTION);
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
		
			AEMFTMetadataElementComposite processData = data.getCompositeElement(DSLAMBOIProject.PROJECT_PROCESS);
			if (processData != null) {
				String processId = getElementController().getElementAsString(DSLAMBOIProcess.PROCESS_ID, processData);
				logger = new CRONIOBusDesktopAccordionLoggerContainer(processId);
				root.add(logger);
				logger.postDisplay();
				logger.setSize("100%", "100%");
			
				AEMFTMetadataElementComposite consoleData = getElementController().getElementAsComposite(DSLAMBOIProject.PROJECT_EXECUTION_CONSOLE, data);
				if (consoleData != null) {
					logger.setData(consoleData);
					logger.setFilterVisible(false);
					logger.setPagerVisible(false);
				} 
				
			} else {
				
				AEGWTLogicalEvent getLogEvt = new AEGWTLogicalEvent(getWindowName(), getName());
				getLogEvt.addElement(CRONIOBOIExecution.EXECUTION_DATA, data);
				getLogEvt.setEventType(LOGICAL_TYPE.GET_EVENT);
				getLogicalEventHandlerManager().fireEvent(getLogEvt);
				
//				AEMFTMetadataElementComposite logData = getElementController().getElementAsComposite(DSLAMBOIProject.PROJECT_EXECUTION_LOG, data);
//				if (logData != null) {
//					logger.setData(logData);
//				}
			}
		}
	}

	/**
	 * CRONIOBusProjectBaseDisplay
	 */
	
	@Override
	public void beforeExitSection() {
		if (logger != null) {
			logger.beforeExitSection();	
		}
	}
}
