package com.imotion.dslam.front.business.desktop.client.widget.proccesspage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapFormFieldTextBox;
import com.selene.arch.exe.gwt.client.ui.widget.label.AEGWTLabel;

public class DSLAMBusDesktopConnectionToolbar extends AEGWTCompositePanel {
	
	public static final String NAME = "DSLAMBusDesktopConnectionToolbar";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private AEGWTBootstrapFormFieldTextBox serverTextBox;
	private AEGWTBootstrapFormFieldTextBox userTextBox;
	private AEGWTBootstrapFormFieldTextBox passwordTextBox;
	
	public DSLAMBusDesktopConnectionToolbar() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.CONNECTION_TOOLBAR);
	
		FlowPanel conectionZone = new FlowPanel();
		root.add(conectionZone);
		conectionZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_12);
		conectionZone.addStyleName(DSLAMBusDesktopIStyleConstants.TOOLBAR_CONNECTION_ZONE);
	
		AEGWTLabel serverLabel = new AEGWTLabel(TEXTS.server());
		serverLabel.addStyleName(AEGWTIBoostrapConstants.COL_XS_1);
		serverLabel.addStyleName(DSLAMBusDesktopIStyleConstants.SERVER_LABEL);
		conectionZone.add(serverLabel);
		
		serverTextBox = new AEGWTBootstrapFormFieldTextBox();
		serverTextBox.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
		serverTextBox.addStyleName(DSLAMBusDesktopIStyleConstants.SERVER_TEXTBOX);
		conectionZone.add(serverTextBox);
		
		AEGWTLabel userLabel = new AEGWTLabel(TEXTS.user());
		userLabel.addStyleName(AEGWTIBoostrapConstants.COL_XS_1);
		userLabel.addStyleName(DSLAMBusDesktopIStyleConstants.USER_LABEL);
		conectionZone.add(userLabel);
		
		userTextBox = new AEGWTBootstrapFormFieldTextBox();
		userTextBox.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
		userTextBox.addStyleName(DSLAMBusDesktopIStyleConstants.USER_TEXTBOX);
		conectionZone.add(userTextBox);
		
		AEGWTLabel passwordLabel = new AEGWTLabel(TEXTS.password());
		passwordLabel.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
		passwordLabel.addStyleName(DSLAMBusDesktopIStyleConstants.PASSWORD_LABEL);
		conectionZone.add(passwordLabel);
		
		passwordTextBox = new AEGWTBootstrapFormFieldTextBox();
		passwordTextBox.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
		passwordTextBox.addStyleName(DSLAMBusDesktopIStyleConstants.PASSWORD_TEXTBOX);
		conectionZone.add(passwordTextBox);
	}

	/**
	 *	AEGWTICompositePanel 
	 */
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub

	}

}
