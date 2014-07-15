package com.imotion.dslam.front.business.desktop.client.widget.preferences;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOIMachinePropertiesDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.presenter.preferences.connection.CRONIOBusDesktopPreferencesConnectionPresenter;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.validation.AEGWTIValidationChangeHandler;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapDropdownButton;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapForm;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapFormFieldTextBox;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;

public class CRONIOBusDesktopPreferencesMachineConfigureForm extends AEGWTBootstrapForm {

	public static final String NAME = "CRONIOBusDesktopPreferencesMachineConfirureForm";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private AEGWTBootstrapFormFieldTextBox  		userNameTextBox;
	private AEGWTBootstrapFormFieldTextBox  		passwordTextBox; 
	private AEGWTBootstrapFormFieldTextBox  		timeOutTextBox;
	private AEGWTBootstrapFormFieldTextBox  		promptTextBox; 
	private AEGWTBootstrapDropdownButton  			protocolTypeDropdownButton; 

	public CRONIOBusDesktopPreferencesMachineConfigureForm() {
		setButtonText(BUTTON_SUBMIT, TEXTS.save());
		addContainerStyle(AEGWTIBoostrapConstants.COL_XS_12);

		userNameTextBox 				= new AEGWTBootstrapFormFieldTextBox(null	, TEXTS.user_placeholder());
		passwordTextBox 				= new AEGWTBootstrapFormFieldTextBox(null	, TEXTS.password_placeholder());
		timeOutTextBox 					= new AEGWTBootstrapFormFieldTextBox(null	, TEXTS.timeout_placeholder());
		promptTextBox 					= new AEGWTBootstrapFormFieldTextBox(null	, TEXTS.prompt_placeholder());
		protocolTypeDropdownButton 	= new AEGWTBootstrapDropdownButton();
		
		protocolTypeDropdownButton.addElement(String.valueOf(CRONIOBOIMachinePropertiesDataConstants.PROTOCOL_TYPE)	, TEXTS.ssh());
		protocolTypeDropdownButton.addElement(String.valueOf(CRONIOBOIMachinePropertiesDataConstants.PROTOCOL_TYPE)	, TEXTS.telnet());

		FlowPanel textBoxesZone = new FlowPanel();
		textBoxesZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_12);
		textBoxesZone.add(protocolTypeDropdownButton);
		textBoxesZone.add(userNameTextBox);
		textBoxesZone.add(passwordTextBox);
		textBoxesZone.add(timeOutTextBox);
		textBoxesZone.add(promptTextBox);
		

		addWidget(textBoxesZone);
	}
	
	public boolean showValidateClientErrors() {
		boolean showErrors = false;
//		if (AEMFTCommonUtilsBase.isEmptyString(authorIdTextBox.getText())) {
//			authorIdTextBox.setErrorLabelTextAndShow(TEXTS.common_error_required());
//			showErrors = true;
//		} else {
//			authorIdTextBox.setErrorLabelVisible(false);
//		}
//
//		if (AEMFTCommonUtilsBase.isEmptyString(authorNameTextBox.getText())) {
//			authorNameTextBox.setErrorLabelTextAndShow(TEXTS.common_error_required());
//			showErrors = true;
//		} else {
//			authorNameTextBox.setErrorLabelVisible(false);
//		}
		
		return showErrors;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite connectionConfigureData) {
		if (connectionConfigureData != null) {
			String 			userName 			= getElementController().getElementAsString(CRONIOBOIMachineProperties.USERNAME			, connectionConfigureData);
			String 			password 			= getElementController().getElementAsString(CRONIOBOIMachineProperties.PASSWORD			, connectionConfigureData);
			String 			timeout 			= getElementController().getElementAsString(CRONIOBOIMachineProperties.TIMEOUT			, connectionConfigureData);
			String 			prompt 				= getElementController().getElementAsString(CRONIOBOIMachineProperties.PROMPT			, connectionConfigureData);
			String 			protocolType 		= getElementController().getElementAsString(CRONIOBOIMachineProperties.PROTOCOL_TYPE	, connectionConfigureData);

			userNameTextBox.setText(userName);
			passwordTextBox.setText(password);
			timeOutTextBox.setText(timeout);
			promptTextBox.setText(prompt);
			protocolTypeDropdownButton.setItemSelected(protocolType);;	
		}
	}

	@Override
	public AEMFTMetadataElementComposite getData() {

		boolean 	errors 	= false;
		
		errors = showValidateClientErrors();


		if (errors == false) {
			AEMFTMetadataElementComposite formData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();

			getElementController().setElement(CRONIOBOIMachineProperties.USERNAME		, formData	, userNameTextBox.getText());
			getElementController().setElement(CRONIOBOIMachineProperties.PASSWORD		, formData	, passwordTextBox.getText());
			getElementController().setElement(CRONIOBOIMachineProperties.TIMEOUT		, formData	, timeOutTextBox.getText());
			getElementController().setElement(CRONIOBOIMachineProperties.PROMPT			, formData	, promptTextBox.getText());
			getElementController().setElement(CRONIOBOIMachineProperties.PROTOCOL_TYPE	, formData	, protocolTypeDropdownButton.getSelectedId());
			
			return formData;
		} else {
			return null;
		}
	}

	@Override
	public void initFormValidation(
			AEGWTIValidationChangeHandler formValidationhandler) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void resetForm() {
		userNameTextBox.setText("");
		passwordTextBox.setText(""); 
		timeOutTextBox.setText("");
		promptTextBox.setText(""); 	
	}

	@Override
	protected void resetErrorLabel() {
		userNameTextBox.setErrorLabelVisible(false);
		passwordTextBox.setErrorLabelVisible(false);
		timeOutTextBox.setErrorLabelVisible(false);
		promptTextBox.setErrorLabelVisible(false);
	}

	@Override
	protected boolean isValidEvent(AEGWTLogicalEvent evt) {
		if (CRONIOBusDesktopPreferencesConnectionPresenter.NAME.equals(evt.getSourceWidget())) {
			return true;
		} else return false;
	}

	@Override
	protected void showErrors(AEMFTMetadataElementComposite dataErrors) {

//		AEMFTMetadataElementComposite authorErrors = getElementController().getElementAsComposite(SNDOBUIManagementBusinessServiceConstants.KEY_ERRORS_AUTHOR_DATA	, dataErrors);
//
//		String sellableListErrors 		= getElementController().getElementAsString(SNDOBUIManagementBusinessServiceConstants.KEY_SELLABLE_LIST_ERROR	, authorErrors);
//		String featuredSellableError 	= getElementController().getElementAsString(SNDOBUIManagementBusinessServiceConstants.KEY_FEATUREDITEM_ERROR	, authorErrors);
//		String weekOfferSellableError 	= getElementController().getElementAsString(SNDOBUIManagementBusinessServiceConstants.KEY_WEEKOFFERITEM_ERROR	, authorErrors);
//		String authorIdExist			= getElementController().getElementAsString(SNDOBUIManagementBusinessServiceConstants.KEY_EXIST_ERROR			, authorErrors);
//
//		if (!AEMFTCommonUtilsBase.isEmptyString(authorIdExist)) {
//			authorIdTextBox.setErrorLabelTextAndShow(TEXTS.common_error_author_exist());
//		}
//
//		if (!AEMFTCommonUtilsBase.isEmptyString(sellableListErrors)) {
//			sellableListTextBox.setErrorLabelTextAndShow(TEXTS.common_error_no_exist() + sellableListErrors);
//		}
//
//		if (!AEMFTCommonUtilsBase.isEmptyString(featuredSellableError)) {
//			featuredItemTextBox.setErrorLabelTextAndShow(TEXTS.common_error_featured_no_exist() + featuredSellableError);
//		}
//
//		if (!AEMFTCommonUtilsBase.isEmptyString(weekOfferSellableError)) {
//			weekOfferTextBox.setErrorLabelTextAndShow(TEXTS.common_error_weekoffer_no_exist() + weekOfferSellableError);
//		}
	}
}
