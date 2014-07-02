package com.imotion.dslam.front.business.desktop.client.view.execution;

import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.execution.DSLAMBusDesktopExecutionDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.widget.execution.CRONIOBusDesktopLoggersContainer;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class DSLAMBusDesktopExecutionScreenView extends DSLAMBusDesktopPanelBaseView implements DSLAMBusDesktopExecutionDisplay {

	public static final String NAME = "DSLAMBusDesktopExecutionScreenView";
	
	private FlowPanel							root;
	private CRONIOBusDesktopLoggersContainer	loggersContainer;
	
	public DSLAMBusDesktopExecutionScreenView() {
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.EXECUTION);
		
		loggersContainer = new CRONIOBusDesktopLoggersContainer();
		root.add(loggersContainer);
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
		loggersContainer.clear();
		if (data != null) {
			
		}
	}

}
