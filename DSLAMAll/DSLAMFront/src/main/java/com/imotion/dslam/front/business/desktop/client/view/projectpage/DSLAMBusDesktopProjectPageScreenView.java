package com.imotion.dslam.front.business.desktop.client.view.projectpage;

import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.desktop.client.presenter.projectpage.DSLAMBusDesktopProjectPageDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class DSLAMBusDesktopProjectPageScreenView extends DSLAMBusDesktopPanelBaseView implements DSLAMBusDesktopProjectPageDisplay {

	public static final String NAME = "DSLAMBusDesktopProjectPageScreenView";
	
	private FlowPanel root;
	
	public DSLAMBusDesktopProjectPageScreenView() {
		root = new FlowPanel();
		initContentPanel(root);
		
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
	}

	/**
	 * CRONIOBusProjectBaseDisplay
	 */
	
	@Override
	public void beforeExitSection() {
		// TODO Auto-generated method stub
		
	}
}
