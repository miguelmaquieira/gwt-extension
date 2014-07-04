package com.imotion.dslam.front.business.desktop.client.view.execution;

import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.execution.DSLAMBusDesktopExecutionDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.widget.execution.CRONIOBusDesktopAccordionLoggerContainer;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class DSLAMBusDesktopExecutionScreenView extends DSLAMBusDesktopPanelBaseView implements DSLAMBusDesktopExecutionDisplay {

	public static final String NAME = "DSLAMBusDesktopExecutionScreenView";
	
	private FlowPanel	root;
	
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
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		if (data != null) {
			root.clear();
		
			String processId = getElementController().getElementAsString(DSLAMBOIProcess.PROCESS_ID, data);
			CRONIOBusDesktopAccordionLoggerContainer logger = new CRONIOBusDesktopAccordionLoggerContainer(processId);
			root.add(logger);
			logger.postDisplay();
			logger.setSize("100%", "100%");
			
			AEMFTMetadataElementComposite logData = getElementController().getElementAsComposite(DSLAMBOIProject.PROJECT_EXECUTION_LOG, data);
			if (logData != null) {
				logger.setData(logData);
			}
		}
	}
}
