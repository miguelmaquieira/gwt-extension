package com.imotion.dslam.front.business.desktop.client.widget.authentication;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Visibility;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.validation.AEGWTIValidationChangeHandler;
import com.selene.arch.exe.gwt.client.ui.widget.textbox.AEGWTTextBox;
import com.selene.arch.exe.gwt.mvp.event.authentication.AEGWTAuthenticationEventTypes.AUTHENTICATION_TYPE;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;

public  class CRONIOBusDesktopSignForm extends CRONIOBusDesktopAuthenticationFormBase {

	public 	static final String		NAME	= "CRONIOBusDesktopSignForm";
	private DSLAMBusI18NTexts 		TEXTS 	= GWT.create(DSLAMBusI18NTexts.class);

	
	private AEGWTTextBox						userNameBox;
	private CRONIOBusDesktopPasswordTextBox		passwordTextBox;
	
	
	public CRONIOBusDesktopSignForm() {
		
		addStyleName(DSLAMBusDesktopIStyleConstants.SIGN_FORM);
		
		userNameBox = new AEGWTTextBox();
		getFieldsZone().add(userNameBox);
		userNameBox.setDefaultText(TEXTS.email_placeholder());

		passwordTextBox = new CRONIOBusDesktopPasswordTextBox();
		getFieldsZone().add(passwordTextBox);
		passwordTextBox.setDefaultText(TEXTS.password_placeholder());
			
		getErrorWidget().setVisibility(Visibility.HIDDEN);
		setActionButtonHTML(TEXTS.enter());
		setDescriptionHTML(TEXTS.sign_up_description_text());
		
		//TODO:
		
					
	}

	/****************************************************************************
	 *                     		AEGWTICompositePanel
	 ****************************************************************************/
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub
	}

	
	
	@Override
	public void onChangeValue(AEGWTLogicalEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AEMFTMetadataElementComposite getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AUTHENTICATION_TYPE getAuthenticationEventType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initFormValidation(
			AEGWTIValidationChangeHandler formValidationhandler) {
		// TODO Auto-generated method stub
		
	}
}
