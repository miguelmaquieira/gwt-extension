package com.imotion.dslam.front.business.desktop.client.widget.preferences;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOIMachinePropertiesDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.presenter.preferences.connection.CRONIOBusDesktopPreferencesConnectionPresenter;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopPreferencesEvent;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopPreferencesEventTypes.EVENT_TYPE;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.ui.validation.AEGWTIValidationChangeHandler;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapDropdownButton;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapForm;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapFormFieldTextBox;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;

public class CRONIOBusDesktopPreferencesMachineConfigureForm extends AEGWTBootstrapForm {

	public static final String NAME = "CRONIOBusDesktopPreferencesMachineConfigureForm";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private AEGWTBootstrapFormFieldTextBox  		userNameTextBox;
	private AEGWTBootstrapFormFieldTextBox  		passwordTextBox; 
	private AEGWTBootstrapFormFieldTextBox  		timeOutTextBox;
	private AEGWTBootstrapFormFieldTextBox  		promptTextBox; 
	private AEGWTBootstrapDropdownButton  			protocolTypeDropdownButton;

	public CRONIOBusDesktopPreferencesMachineConfigureForm() {
		setButtonText(BUTTON_SUBMIT, TEXTS.save());

		userNameTextBox 			= new AEGWTBootstrapFormFieldTextBox(null	, TEXTS.user_placeholder());
		passwordTextBox 			= new AEGWTBootstrapFormFieldTextBox(null	, TEXTS.password_placeholder());
		timeOutTextBox 				= new AEGWTBootstrapFormFieldTextBox(null	, TEXTS.timeout_placeholder());
		promptTextBox 				= new AEGWTBootstrapFormFieldTextBox(null	, TEXTS.prompt_placeholder());
		protocolTypeDropdownButton 	= new AEGWTBootstrapDropdownButton();
		
		protocolTypeDropdownButton.addElement(String.valueOf(CRONIOBOIMachinePropertiesDataConstants.PROTOCOL_TYPE_SSH)	, TEXTS.ssh());
		protocolTypeDropdownButton.addElement(String.valueOf(CRONIOBOIMachinePropertiesDataConstants.PROTOCOL_TYPE_TELNET)	, TEXTS.telnet());

		FlowPanel textBoxesZone = new FlowPanel();
		textBoxesZone.add(protocolTypeDropdownButton);
		textBoxesZone.add(userNameTextBox);
		textBoxesZone.add(passwordTextBox);
		textBoxesZone.add(timeOutTextBox);
		textBoxesZone.add(promptTextBox);
		
		addWidget(textBoxesZone);
		
		userNameTextBox.addBlurHandler(new BlurHandler(){
			@Override
			public void onBlur(BlurEvent event) {
				AEMFTMetadataElementComposite formData = getData();
				
				CRONIOBusDesktopPreferencesEvent connectionConfigureFormEvt = new CRONIOBusDesktopPreferencesEvent(getName(), getName());
				connectionConfigureFormEvt.setEventType(EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT);
				connectionConfigureFormEvt.addElementAsComposite(getId() ,formData);
				connectionConfigureFormEvt.setConnectionName(getId());
				getLogicalEventHandlerManager().fireEvent(connectionConfigureFormEvt);
				
			}
		});
		
		passwordTextBox.addBlurHandler(new BlurHandler(){
			@Override
			public void onBlur(BlurEvent event) {
				AEMFTMetadataElementComposite formData = getData();
				
				CRONIOBusDesktopPreferencesEvent connectionConfigureFormEvt = new CRONIOBusDesktopPreferencesEvent(getId(), getName());
				connectionConfigureFormEvt.setEventType(EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT);
				connectionConfigureFormEvt.addElementAsComposite(getId() ,formData);
				connectionConfigureFormEvt.setConnectionName(getId());
				getLogicalEventHandlerManager().fireEvent(connectionConfigureFormEvt);
				
			}
		});
		
		timeOutTextBox.addBlurHandler(new BlurHandler(){
			@Override
			public void onBlur(BlurEvent event) {
				AEMFTMetadataElementComposite formData = getData();
				
				CRONIOBusDesktopPreferencesEvent connectionConfigureFormEvt = new CRONIOBusDesktopPreferencesEvent(getId(), getName());
				connectionConfigureFormEvt.setEventType(EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT);
				connectionConfigureFormEvt.addElementAsComposite(getId() ,formData);
				connectionConfigureFormEvt.setConnectionName(getId());
				getLogicalEventHandlerManager().fireEvent(connectionConfigureFormEvt);
				
			}
		});
		
		promptTextBox.addBlurHandler(new BlurHandler(){
			@Override
			public void onBlur(BlurEvent event) {
				AEMFTMetadataElementComposite formData = getData();
				
				CRONIOBusDesktopPreferencesEvent connectionConfigureFormEvt = new CRONIOBusDesktopPreferencesEvent(getId(), getName());
				connectionConfigureFormEvt.setEventType(EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT);
				connectionConfigureFormEvt.addElementAsComposite(getId() ,formData);
				connectionConfigureFormEvt.setConnectionName(getId());
				getLogicalEventHandlerManager().fireEvent(connectionConfigureFormEvt);
				
			}
		});
	}
	
	public boolean showValidateClientErrors() {
		boolean showErrors = false;
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
			int 			timeout 			= getElementController().getElementAsInt(CRONIOBOIMachineProperties.TIMEOUT				, connectionConfigureData);
			String 			prompt 				= getElementController().getElementAsString(CRONIOBOIMachineProperties.PROMPT			, connectionConfigureData);
			int 			protocolType 		= getElementController().getElementAsInt(CRONIOBOIMachineProperties.PROTOCOL_TYPE		, connectionConfigureData);

			userNameTextBox.setText(userName);
			passwordTextBox.setText(password);
			timeOutTextBox.setText(String.valueOf(timeout));
			promptTextBox.setText(prompt);
			protocolTypeDropdownButton.setItemSelected(String.valueOf(protocolType));	
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
			getElementController().setElement(CRONIOBOIMachineProperties.MACHINE_NAME	, formData	, getId());
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
		protocolTypeDropdownButton.setItemSelected(String.valueOf(CRONIOBOIMachineProperties.PROTOCOL_TYPE_SSH));
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

	}
}
