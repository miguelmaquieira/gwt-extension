package com.imotion.transit.front.business.desktop.client.view.example;

import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.transit.front.business.desktop.client.presenter.example.TRANSBusDesktopExampleDisplay;
import com.imotion.transit.front.business.desktop.client.view.TRANSBusDesktopPanelBaseView;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class TRANSBusDesktopExampleScreenView extends TRANSBusDesktopPanelBaseView implements TRANSBusDesktopExampleDisplay {

	public static final String NAME = "TRANSDesktopExampleScreenView";
	
	private FlowPanel 						root;
	
	public TRANSBusDesktopExampleScreenView() {
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
