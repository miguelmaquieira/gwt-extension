package com.imotion.dslam.front.business.desktop.client.view.login;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.login.CRONIOBusDesktopLoginDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.widget.authentication.CRONIOBusDesktopSignInForm;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class CRONIOBusDesktopLoginScreenView extends DSLAMBusDesktopPanelBaseView implements  CRONIOBusDesktopLoginDisplay{


	public 	static final String NAME = "CRONIOBusDesktopLoginScreenView";
	private static final DSLAMBusI18NTexts 	TEXTS 	= GWT.create(DSLAMBusI18NTexts.class);
	
	private FlowPanel 					root;
	private CRONIOBusDesktopSignInForm	form;
	
	public CRONIOBusDesktopLoginScreenView() {
		
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.LOGIN_VIEW);
		
		form = new CRONIOBusDesktopSignInForm(TEXTS.sign_in_description_text());
		root.add(form);			
	}
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {		
	}

	@Override
	public void postDisplay() {
		super.postDisplay();
		form.postDisplay();
	}
}
