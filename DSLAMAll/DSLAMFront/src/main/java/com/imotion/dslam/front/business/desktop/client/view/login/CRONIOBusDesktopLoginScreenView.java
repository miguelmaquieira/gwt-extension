package com.imotion.dslam.front.business.desktop.client.view.login;

import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.login.CRONIOBusDesktopLoginDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.widget.authentication.CRONIOBusDesktopSignForm;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class CRONIOBusDesktopLoginScreenView extends DSLAMBusDesktopPanelBaseView implements  CRONIOBusDesktopLoginDisplay{


	public 	static final String NAME = "CRONIOBusDesktopLoginScreenView";
	
	private FlowPanel 					root;
	private CRONIOBusDesktopSignForm	form;
	
	public CRONIOBusDesktopLoginScreenView() {
		
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.LOGIN_VIEW);
		
		form = new CRONIOBusDesktopSignForm();
		root.add(form);			
	}


	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub
		
	}

	
}
