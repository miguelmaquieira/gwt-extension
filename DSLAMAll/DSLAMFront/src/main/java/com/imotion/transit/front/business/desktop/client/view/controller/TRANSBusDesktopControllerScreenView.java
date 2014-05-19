package com.imotion.transit.front.business.desktop.client.view.controller;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.imotion.transit.front.business.client.presenter.controller.TRANSBusControllerDisplay;
import com.imotion.transit.front.business.desktop.client.common.TRANSBusI18NTexts;
import com.imotion.transit.front.business.desktop.client.view.TRANSBusDesktopPanelBaseView;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class TRANSBusDesktopControllerScreenView extends TRANSBusDesktopPanelBaseView implements TRANSBusControllerDisplay {

	public 		static String 				NAME	= "BusinessDesktopControllerScreenView";
	private		static 	TRANSBusI18NTexts	TEXTS 	= GWT.create(TRANSBusI18NTexts.class);


	public TRANSBusDesktopControllerScreenView() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		
		Label label = new Label("CONTROLLER");
		root.add(label);
	}


	@Override
	public HasClickHandlers getSubscribeButton() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public HasClickHandlers getAnchorCreateAccount() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public HasClickHandlers getAnchorLogin() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void invalidSubscriberEmail() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void showSubscriptionPopup() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getSubscriberEmail() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void showAccountActivated() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void showAccountNoActivated() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void showResetPassword(String resetKey) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub
		
	}

}
