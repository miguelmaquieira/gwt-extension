package com.imotion.dslam.front.business.desktop.client.widget.preferences;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOIMachinePropertiesDataConstants;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopPreferencesEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopPreferencesEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.presenter.preferences.connection.CRONIOBusDesktopPreferencesConnectionPresenter;
import com.imotion.dslam.front.business.desktop.client.view.preferences.connection.CRONIOBusI18NPreferencesConnectionTexts;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.validation.AEGWTIValidationChangeHandler;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapDropdownButton;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapForm;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapFormFieldDropDownButtonLabelTop;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapFormFieldPasswordBoxLabelTop;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapFormFieldTextBoxLabelTop;
import com.selene.arch.exe.gwt.client.ui.widget.jquery.AEGWTJQueryPerfectScrollBar;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopPreferencesMachineConfigureForm extends AEGWTBootstrapForm {

	public static final String NAME = "CRONIOBusDesktopPreferencesMachineConfigureForm";
	private static CRONIOBusI18NTexts 							TEXTS 				= GWT.create(CRONIOBusI18NTexts.class);
	private static CRONIOBusI18NPreferencesConnectionTexts 	PREFERENCES_TEXTS 	= GWT.create(CRONIOBusI18NPreferencesConnectionTexts.class);

	private AEGWTBootstrapFormFieldTextBoxLabelTop			userNameTextBox;
	private AEGWTBootstrapFormFieldPasswordBoxLabelTop 		passwordTextBox; 
	private AEGWTBootstrapFormFieldTextBoxLabelTop			timeOutTextBox;
	private AEGWTBootstrapFormFieldTextBoxLabelTop 			promptTextBox; 
	private AEGWTBootstrapFormFieldTextBoxLabelTop 			userPromptTextBox; 
	private AEGWTBootstrapFormFieldTextBoxLabelTop 			passwordPromptTextBox;
	private AEGWTBootstrapFormFieldTextBoxLabelTop			rollbackConditionPromptTextBox;
	private AEGWTBootstrapFormFieldDropDownButtonLabelTop  	protocolTypeDropdownButton;

	public CRONIOBusDesktopPreferencesMachineConfigureForm() {
		setButtonText(BUTTON_SUBMIT, TEXTS.save());
		//setButtonStyle(BUTTON_SUBMIT, AEGWTIBoostrapConstants.);
		addButtonStyle(BUTTON_SUBMIT, AEGWTIBoostrapConstants.BTN);
		// invisible, no need
		setButtonVisible(BUTTON_SUBMIT, false);

		FlowPanel textBoxesZone = new FlowPanel();
		addWidget(textBoxesZone);
		textBoxesZone.addStyleName(CRONIOBusDesktopIStyleConstants.PREFERENCES_MACHINE_CONFIGURE_FORM);
		textBoxesZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_12);
		
		FlowPanel textBoxesLeftZone = new FlowPanel();
		textBoxesZone.add(textBoxesLeftZone);
		textBoxesLeftZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_6);
		
		FlowPanel textBoxesRightZone = new FlowPanel();
		textBoxesZone.add(textBoxesRightZone);
		textBoxesRightZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_6);

		////LEFTZONE
		//protocol
		protocolTypeDropdownButton 	= new AEGWTBootstrapFormFieldDropDownButtonLabelTop(TEXTS.protocol_placeholder());
		textBoxesLeftZone.add(protocolTypeDropdownButton);
		protocolTypeDropdownButton.setContainerId(NAME);
		protocolTypeDropdownButton.addElement(String.valueOf(CRONIOBOIMachinePropertiesDataConstants.PROTOCOL_TYPE_SSH_1)	, TEXTS.ssh1());
		protocolTypeDropdownButton.addElement(String.valueOf(CRONIOBOIMachinePropertiesDataConstants.PROTOCOL_TYPE_SSH_2)	, TEXTS.ssh2());
		protocolTypeDropdownButton.addElement(String.valueOf(CRONIOBOIMachinePropertiesDataConstants.PROTOCOL_TYPE_TELNET)	, TEXTS.telnet());
		protocolTypeDropdownButton.addElement(String.valueOf(CRONIOBOIMachinePropertiesDataConstants.PROTOCOL_TYPE_TEST)	, TEXTS.test());
		//protocolTypeDropdownButton.addStyleName(AEGWTIBoostrapConstants.COL_XS_12);
		
		//username
		userNameTextBox 			= new AEGWTBootstrapFormFieldTextBoxLabelTop(TEXTS.user_placeholder(), TEXTS.user_placeholder());
		textBoxesLeftZone.add(userNameTextBox);
		
		//prompt
		promptTextBox 				= new AEGWTBootstrapFormFieldTextBoxLabelTop(TEXTS.prompt_regex_placeholder(), TEXTS.prompt_regex_placeholder());
		textBoxesLeftZone.add(promptTextBox);
		//userPrompt
		userPromptTextBox 			= new AEGWTBootstrapFormFieldTextBoxLabelTop(PREFERENCES_TEXTS.userPromptLabel(), PREFERENCES_TEXTS.userPromptPlaceHolder());
		textBoxesLeftZone.add(userPromptTextBox);
		userPromptTextBox.setVisible(false);
				
		////RIGHTZONE
		//timeout
		timeOutTextBox 				= new AEGWTBootstrapFormFieldTextBoxLabelTop(TEXTS.timeout_ms_placeholder(), TEXTS.timeout_ms_placeholder());
		textBoxesRightZone.add(timeOutTextBox);
		
		//password
		passwordTextBox 			= new AEGWTBootstrapFormFieldPasswordBoxLabelTop(TEXTS.password_placeholder(), TEXTS.password_placeholder());
		textBoxesRightZone.add(passwordTextBox);

		//rollbackConditionPrompt
		rollbackConditionPromptTextBox = new AEGWTBootstrapFormFieldTextBoxLabelTop(PREFERENCES_TEXTS.rollbackConditionPromptLabel(), PREFERENCES_TEXTS.rollbackConditionPromptPlaceHolder());
		textBoxesRightZone.add(rollbackConditionPromptTextBox);

		//passwordPromt
		passwordPromptTextBox 		= new AEGWTBootstrapFormFieldTextBoxLabelTop(PREFERENCES_TEXTS.passwordPromptLabel(), PREFERENCES_TEXTS.passwordPromptPlaceHolder());
		textBoxesRightZone.add(passwordPromptTextBox);
		passwordPromptTextBox.setVisible(false);
			
		userNameTextBox.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				AEMFTMetadataElementComposite formData = getData();

				CRONIOBusDesktopPreferencesEvent connectionConfigureFormEvt = new CRONIOBusDesktopPreferencesEvent(getId(), getName());
				connectionConfigureFormEvt.setEventType(EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT);
				connectionConfigureFormEvt.addElementAsComposite(getId() ,formData);
				connectionConfigureFormEvt.setConnectionName(getId());
				getLogicalEventHandlerManager().fireEvent(connectionConfigureFormEvt);
			}
		});

		passwordTextBox.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				AEMFTMetadataElementComposite formData = getData();

				CRONIOBusDesktopPreferencesEvent connectionConfigureFormEvt = new CRONIOBusDesktopPreferencesEvent(getId(), getName());
				connectionConfigureFormEvt.setEventType(EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT);
				connectionConfigureFormEvt.addElementAsComposite(getId() ,formData);
				connectionConfigureFormEvt.setConnectionName(getId());
				getLogicalEventHandlerManager().fireEvent(connectionConfigureFormEvt);
			}
		});

		timeOutTextBox.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				AEMFTMetadataElementComposite formData = getData();

				CRONIOBusDesktopPreferencesEvent connectionConfigureFormEvt = new CRONIOBusDesktopPreferencesEvent(getId(), getName());
				connectionConfigureFormEvt.setEventType(EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT);
				connectionConfigureFormEvt.addElementAsComposite(getId() ,formData);
				connectionConfigureFormEvt.setConnectionName(getId());
				getLogicalEventHandlerManager().fireEvent(connectionConfigureFormEvt);
			}
		});

		promptTextBox.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				AEMFTMetadataElementComposite formData = getData();

				CRONIOBusDesktopPreferencesEvent connectionConfigureFormEvt = new CRONIOBusDesktopPreferencesEvent(getId(), getName());
				connectionConfigureFormEvt.setEventType(EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT);
				connectionConfigureFormEvt.addElementAsComposite(getId() ,formData);
				connectionConfigureFormEvt.setConnectionName(getId());
				getLogicalEventHandlerManager().fireEvent(connectionConfigureFormEvt);
			}
		});
		
		userPromptTextBox.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				AEMFTMetadataElementComposite formData = getData();

				CRONIOBusDesktopPreferencesEvent connectionConfigureFormEvt = new CRONIOBusDesktopPreferencesEvent(getId(), getName());
				connectionConfigureFormEvt.setEventType(EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT);
				connectionConfigureFormEvt.addElementAsComposite(getId() ,formData);
				connectionConfigureFormEvt.setConnectionName(getId());
				getLogicalEventHandlerManager().fireEvent(connectionConfigureFormEvt);
			}
		});
		
		passwordPromptTextBox.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				AEMFTMetadataElementComposite formData = getData();

				CRONIOBusDesktopPreferencesEvent connectionConfigureFormEvt = new CRONIOBusDesktopPreferencesEvent(getId(), getName());
				connectionConfigureFormEvt.setEventType(EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT);
				connectionConfigureFormEvt.addElementAsComposite(getId() ,formData);
				connectionConfigureFormEvt.setConnectionName(getId());
				getLogicalEventHandlerManager().fireEvent(connectionConfigureFormEvt);
			}
		});
		
		rollbackConditionPromptTextBox.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
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
			String 			userName 					= getElementController().getElementAsString(CRONIOBOIMachineProperties.USERNAME					, connectionConfigureData);
			String 			password 					= getElementController().getElementAsString(CRONIOBOIMachineProperties.PASSWORD					, connectionConfigureData);
			int 			timeout 					= getElementController().getElementAsInt(CRONIOBOIMachineProperties.TIMEOUT						, connectionConfigureData);
			String 			prompt 						= getElementController().getElementAsString(CRONIOBOIMachineProperties.PROMPT_REGEX				, connectionConfigureData);
			String 			userPrompt 					= getElementController().getElementAsString(CRONIOBOIMachineProperties.USERNAME_PROMPT_REGEX	, connectionConfigureData);
			String 			passwordPrompt 				= getElementController().getElementAsString(CRONIOBOIMachineProperties.PASSWORD_PROMPT_REGEX	, connectionConfigureData);
			String 			rollbackConditionPrompt 	= getElementController().getElementAsString(CRONIOBOIMachineProperties.ROLLBACK_CONDITION_REGEX	, connectionConfigureData);
			int 			protocolType 				= getElementController().getElementAsInt(CRONIOBOIMachineProperties.PROTOCOL_TYPE				, connectionConfigureData);

			userNameTextBox.setText(userName);
			passwordTextBox.setText(password);
			if (timeout > 0) {
				timeOutTextBox.setText(String.valueOf(timeout));
			}
			promptTextBox.setText(prompt);
			if (protocolType == CRONIOBOIMachineProperties.PROTOCOL_TYPE_TELNET) {
				userPromptTextBox.setText(userPrompt);
				passwordPromptTextBox.setText(passwordPrompt);
			}
			rollbackConditionPromptTextBox.setText(rollbackConditionPrompt);
			handleProtocolType(protocolType, false);
		}
	}

	@Override
	public AEMFTMetadataElementComposite getData() {
		boolean errors = showValidateClientErrors();
		if (errors == false) {
			String	timeoutStr		= timeOutTextBox.getText();
			String	protocoltypeStr	= protocolTypeDropdownButton.getSelectedId();
			int		timeoutInt		= AEMFTCommonUtilsBase.getIntegerFromString(timeoutStr);
			int		protocoltypeInt	= AEMFTCommonUtilsBase.getIntegerFromString(protocoltypeStr);

			AEMFTMetadataElementComposite formData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
			getElementController().setElement(CRONIOBOIMachineProperties.USERNAME					, formData	, userNameTextBox.getText());
			getElementController().setElement(CRONIOBOIMachineProperties.PASSWORD					, formData	, passwordTextBox.getText());
			getElementController().setElement(CRONIOBOIMachineProperties.TIMEOUT					, formData	, timeoutInt);
			getElementController().setElement(CRONIOBOIMachineProperties.PROMPT_REGEX				, formData	, promptTextBox.getText());
			getElementController().setElement(CRONIOBOIMachineProperties.USERNAME_PROMPT_REGEX		, formData	, userPromptTextBox.getText());
			getElementController().setElement(CRONIOBOIMachineProperties.PASSWORD_PROMPT_REGEX		, formData	, passwordPromptTextBox.getText());
			getElementController().setElement(CRONIOBOIMachineProperties.ROLLBACK_CONDITION_REGEX	, formData	, rollbackConditionPromptTextBox.getText());
			getElementController().setElement(CRONIOBOIMachineProperties.PROTOCOL_TYPE				, formData	, protocoltypeInt);
			getElementController().setElement(CRONIOBOIMachineProperties.MACHINE_NAME				, formData	, getId());
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

	/****************************************************************************
	 *                      AEGWTHasLogicalEventHandlers
	 ****************************************************************************/

	@Override
	public void dispatchEvent(AEGWTLogicalEvent evt) {
		super.dispatchEvent(evt);
		LOGICAL_TYPE type = evt.getEventType();
		String sourceWidget = evt.getSourceWidget();
		if (AEGWTBootstrapDropdownButton.NAME.equals(sourceWidget) && LOGICAL_TYPE.CHANGE_EVENT.equals(type)) {
			String sourceContainerId = evt.getSourceContainerId();
			if (NAME.equals(sourceContainerId)) {
				String selectedId = evt.getSourceWidgetId();
				int protocolType = AEMFTCommonUtilsBase.getIntegerFromString(selectedId);
				handleProtocolType(protocolType, true);
			}
		}
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return super.isDispatchEventType(type)
				||
				LOGICAL_TYPE.CHANGE_EVENT.equals(type);
	}

	/**
	 * PROTECTED
	 */

	@Override
	protected void resetForm() {
		userNameTextBox.setText("");
		passwordTextBox.setText(""); 
		timeOutTextBox.setText("");
		promptTextBox.setText(""); 
		userPromptTextBox.setText("");
		passwordPromptTextBox.setText("");
		rollbackConditionPromptTextBox.setText("");
		protocolTypeDropdownButton.setItemSelected(String.valueOf(CRONIOBOIMachineProperties.PROTOCOL_TYPE_SSH_1));
	}

	@Override
	protected void resetErrorLabel() {
		userNameTextBox.setErrorLabelVisible(false);
		passwordTextBox.setErrorLabelVisible(false);
		timeOutTextBox.setErrorLabelVisible(false);
		promptTextBox.setErrorLabelVisible(false);
		userPromptTextBox.setErrorLabelVisible(false);
		passwordPromptTextBox.setErrorLabelVisible(false);
		rollbackConditionPromptTextBox.setErrorLabelVisible(false);
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

	/**
	 * PRIVATE
	 */

	private void handleProtocolType(int protocolType, boolean fireEvent) {
		if (protocolType == CRONIOBOIMachineProperties.PROTOCOL_TYPE_TELNET) {
			protocolTypeDropdownButton.setItemSelected(String.valueOf(CRONIOBOIMachineProperties.PROTOCOL_TYPE_TELNET));
			userPromptTextBox.setVisible(true);
			passwordPromptTextBox.setVisible(true);
		} else if (protocolType == CRONIOBOIMachineProperties.PROTOCOL_TYPE_SSH_1) {
			protocolTypeDropdownButton.setItemSelected(String.valueOf(CRONIOBOIMachineProperties.PROTOCOL_TYPE_SSH_1));
			userPromptTextBox.setVisible(false);
			passwordPromptTextBox.setVisible(false);
		} else if (protocolType == CRONIOBOIMachineProperties.PROTOCOL_TYPE_SSH_2) {
			protocolTypeDropdownButton.setItemSelected(String.valueOf(CRONIOBOIMachineProperties.PROTOCOL_TYPE_SSH_2));
			userPromptTextBox.setVisible(false);
			passwordPromptTextBox.setVisible(false);
		} else {
			protocolTypeDropdownButton.setItemSelected(String.valueOf(CRONIOBOIMachineProperties.PROTOCOL_TYPE_TEST));
			userPromptTextBox.setVisible(false);
			passwordPromptTextBox.setVisible(false);
		}
		
		if (fireEvent) {
			AEMFTMetadataElementComposite formData = getData();

			CRONIOBusDesktopPreferencesEvent connectionConfigureFormEvt = new CRONIOBusDesktopPreferencesEvent(getId(), getName());
			connectionConfigureFormEvt.setEventType(EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT);
			connectionConfigureFormEvt.addElementAsComposite(getId() ,formData);
			connectionConfigureFormEvt.setConnectionName(getId());
			getLogicalEventHandlerManager().fireEvent(connectionConfigureFormEvt);
		}
		
		AEGWTJQueryPerfectScrollBar.updateScroll(CRONIOBusDesktopPreferencesMachineSectionsDeckPanel.NAME);
	}
}
