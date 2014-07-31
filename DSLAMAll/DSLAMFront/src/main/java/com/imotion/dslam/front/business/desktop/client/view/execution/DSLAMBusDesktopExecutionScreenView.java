package com.imotion.dslam.front.business.desktop.client.view.execution;

import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.execution.DSLAMBusDesktopExecutionDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.widget.execution.CRONIOBusDesktopAccordionLoggerContainer;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class DSLAMBusDesktopExecutionScreenView extends DSLAMBusDesktopPanelBaseView implements DSLAMBusDesktopExecutionDisplay, AEGWTHasLogicalEventHandlers {

	public static final String NAME = "DSLAMBusDesktopExecutionScreenView";
	
	private FlowPanel	root;
	private CRONIOBusDesktopAccordionLoggerContainer logger;
	
	public DSLAMBusDesktopExecutionScreenView() {
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
		getLogicalEventHandlerManager().addLogicalEventHandler(this);
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		if (data != null) {
			root.clear();
		
			AEMFTMetadataElementComposite processData = data.getCompositeElement(DSLAMBOIProject.PROJECT_PROCESS);
			String processId = getElementController().getElementAsString(DSLAMBOIProcess.PROCESS_ID, processData);
			logger = new CRONIOBusDesktopAccordionLoggerContainer(processId);
			root.add(logger);
			logger.postDisplay();
			logger.setSize("100%", "100%");
			
			AEMFTMetadataElementComposite logData = getElementController().getElementAsComposite(DSLAMBOIProject.PROJECT_EXECUTION_LOG, data);
			if (logData != null) {
				logger.setData(logData);
			}
		}
	}
	
	/**
	 * AEGWTHasLogicalEventHandlers
	 */
	
	@Override
	public void dispatchEvent(AEGWTLogicalEvent evt) {
		LOGICAL_TYPE evtTyp = evt.getEventType();
		if (LOGICAL_TYPE.UPDATE_EVENT.equals(evtTyp)) {
			beforeExitSection();
		}
		
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return  LOGICAL_TYPE.UPDATE_EVENT.equals(type);
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
