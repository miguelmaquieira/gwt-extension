package com.imotion.dslam.front.business.desktop.client.view.log;

import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOIExecution;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.log.CRONIOBusDesktopLogDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.widget.execution.CRONIOBusDesktopAccordionLoggerContainer;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopLogScreenView extends DSLAMBusDesktopPanelBaseView implements CRONIOBusDesktopLogDisplay {

	public static final String NAME = "CRONIOBusDesktopLogScreenView";
	
	private FlowPanel	root;
	private CRONIOBusDesktopAccordionLoggerContainer logger;
	
	public CRONIOBusDesktopLogScreenView() {
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
				
//				AEMFTMetadataElementComposite logData = getElementController().getElementAsComposite(DSLAMBOIProject.PROJECT_EXECUTION_LOG, data);
//				if (logData != null) {
//					logger.setData(logData);
//				}
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
