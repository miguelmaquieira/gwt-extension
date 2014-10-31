package com.imotion.dslam.front.business.desktop.client.view.projectpage;

import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.desktop.client.presenter.projectpage.CRONIOBusDesktopProjectPageDisplay;
import com.imotion.dslam.front.business.desktop.client.view.CRONIOBusDesktopPanelBaseView;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class CRONIOBusDesktopProjectPageScreenView extends CRONIOBusDesktopPanelBaseView implements CRONIOBusDesktopProjectPageDisplay {

	public static final String NAME = "CRONIOBusDesktopProjectPageScreenView";
	
	private FlowPanel root;
	
	public CRONIOBusDesktopProjectPageScreenView() {
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

//	/**
//	 * CRONIOBusProjectBaseDisplay
//	 */
//	
//	@Override
//	public void beforeExitSection() {
//	}
}
