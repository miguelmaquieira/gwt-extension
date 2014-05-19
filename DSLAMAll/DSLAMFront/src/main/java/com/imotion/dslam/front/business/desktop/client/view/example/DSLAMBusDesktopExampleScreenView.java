package com.imotion.dslam.front.business.desktop.client.view.example;

import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.desktop.client.presenter.example.DSLAMBusDesktopExampleDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class DSLAMBusDesktopExampleScreenView extends DSLAMBusDesktopPanelBaseView implements DSLAMBusDesktopExampleDisplay {

	public static final String NAME = "DSLAMDesktopExampleScreenView";
	
	private FlowPanel 						root;
	
	public DSLAMBusDesktopExampleScreenView() {
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

	/************************************************************************
	 *                        PROTECTED FUNCTIONS
	 ************************************************************************/
	
	
	/************************************************************************
	 *                        PRIVATE FUNCTIONS
	 ************************************************************************/

	
}
