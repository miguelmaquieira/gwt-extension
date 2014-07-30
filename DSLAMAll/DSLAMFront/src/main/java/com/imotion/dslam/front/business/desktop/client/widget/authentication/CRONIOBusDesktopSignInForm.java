package com.imotion.dslam.front.business.desktop.client.widget.authentication;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.imotion.dslam.bom.CRONIOBOIUserDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.bom.AEMFTILoginDataConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.validation.AEGWTIValidationChangeHandler;
import com.selene.arch.exe.gwt.client.ui.validation.AEGWTValidationUtils;
import com.selene.arch.exe.gwt.client.ui.widget.textbox.AEGWTTextBox;
import com.selene.arch.exe.gwt.client.utils.AEGWTStringUtils;
import com.selene.arch.exe.gwt.mvp.event.authentication.AEGWTAuthenticationEvent;
import com.selene.arch.exe.gwt.mvp.event.authentication.AEGWTAuthenticationEventTypes.AUTHENTICATION_TYPE;
import com.selene.arch.exe.gwt.mvp.event.authentication.AEGWTHasAuthenticationEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.navigator.AEGWTNavigationEvent;
import com.selene.arch.exe.gwt.mvp.event.navigator.AEGWTNavigationEventTypes.NAVIGATOR_TYPE;

public  class CRONIOBusDesktopSignInForm extends CRONIOBusDesktopAuthenticationFormBase implements AEGWTHasAuthenticationEventHandlers {

	public 	static final String	NAME	= "CRONIOBusDesktopSignInForm";
	private static final DSLAMBusI18NTexts 	TEXTS 	= GWT.create(DSLAMBusI18NTexts.class);

	private AEGWTTextBox						userNameBox;
	private CRONIOBusDesktopPasswordTextBox		passwordTextBox;
	
	public CRONIOBusDesktopSignInForm(String description) {
		super(description);		
		addStyleName(DSLAMBusDesktopIStyleConstants.SIGN_FORM);

		userNameBox = new AEGWTTextBox();
		getFieldsZone().add(userNameBox);
		userNameBox.setDefaultText(TEXTS.email_placeholder());
		addChangeHandler(userNameBox);
				
		passwordTextBox = new CRONIOBusDesktopPasswordTextBox();
		getFieldsZone().add(passwordTextBox);
		passwordTextBox.setDefaultText(TEXTS.password_placeholder());
		addChangeHandler(passwordTextBox.getTextBox());
				

		getActionButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (!checkErrors(-1)) {
					AEGWTAuthenticationEvent evt = new AEGWTAuthenticationEvent(getWindowName(), getName());
					evt.setEventType(getAuthenticationEventType());
					evt.addElementAsString(CRONIOBOIUserDataConstants.EMAIL				, getUserName());
					evt.addElementAsString(CRONIOBOIUserDataConstants.HASH				, AEGWTStringUtils.encodeMd5(getPassword()));
					getLogicalEventHandlerManager().fireEvent(evt);
				}
			}

		});
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
	}
	
	@Override
	public AEMFTMetadataElementComposite getData() {
		return null;
	}

	/****************************************************************************
	 *                  CRONIOBusDesktopAuthenticationFormBase
	 ****************************************************************************/

	@Override
	protected AUTHENTICATION_TYPE getAuthenticationEventType() {
		return AUTHENTICATION_TYPE.SIGN_IN_ATTEMPT;
	}

	@Override
	protected boolean checkErrors(int errorCode) {
		boolean errors = true;
		if (errorCode > -1 ) {
			if (errorCode == AEMFTILoginDataConstants.ERROR_TYPE_SIGNIN_ACCOUNT_NO_EXISTS) {
				setError(TEXTS.sign_in_mail_not_exists());

			} else {
				if (errorCode == AEMFTILoginDataConstants.ERROR_TYPE_SIGNIN_INCORRECT_PASSWORD) {
					setError(TEXTS.sign_in_incorrect_password());						
				}
			}
		} else {
			errors = checkBasicErrors(errors);
		}
		return errors;
	}

	/****************************************************************************
	 *                     AEGWTHasAuthenticationEventHandlers
	 ****************************************************************************/

	@Override
	public void postDisplay() {
		super.postDisplay();
		getLogicalEventHandlerManager().addEventHandler(AEGWTHasAuthenticationEventHandlers.TYPE, this);
	}

	@Override
	public void dispatchEvent(AEGWTAuthenticationEvent evt) {
		if (AUTHENTICATION_TYPE.SIGN_IN_RESPONSE.equals(evt.getEventType())) {
			int			errorCode 	= evt.getElementAsIntDataValue();
			boolean		errors 		= checkErrors(errorCode);
			if (!errors) {
				gotoLoginSuccessfullStatus();
			}
		}
	}

	@Override
	public boolean isDispatchEventType(AUTHENTICATION_TYPE type) {
		return AUTHENTICATION_TYPE.SIGN_IN_RESPONSE.equals(type);
	}

	/****************************************************************************
	 *                     AEGWTFormContainerPanelBase
	 ****************************************************************************/
	
	@Override
	public void initFormValidation(AEGWTIValidationChangeHandler formValidationhandler) {
		// TODO Auto-generated method stub
		
	}

	/****************************************************************************
	 *                     		PRIVATE FUNCTION
	 ****************************************************************************/

	private String getUserName() {
		return userNameBox.getText();
	}

	private String getPassword() {
		return passwordTextBox.getText();
	}

	private boolean checkBasicErrors(boolean errors) {
		String userName = getUserName();
		String password = getPassword();
		boolean validEmail = !getFieldsZone().isVisible() || !userNameBox.isVisible() || AEGWTValidationUtils.isValidEmail(userName);
		if (validEmail) {
			boolean goodPassword = !getFieldsZone().isVisible() || !passwordTextBox.isVisible() ||  AEGWTValidationUtils.hasMinLength(6, password);
			if (goodPassword) {
				errors = false;
			} else {
				setError(TEXTS.login_error_password_short());
			}
		} else {
			setError(TEXTS.login_error_invalid_email());
		}
		return errors;
	}

	public void gotoLoginSuccessfullStatus() {
		AEGWTNavigationEvent evt = new AEGWTNavigationEvent(getWindowName(), getName());
		evt.setEventType(NAVIGATOR_TYPE.OK_EVENT);
		getLogicalEventHandlerManager().fireEvent(evt);
	}

	private void addChangeHandler(AEGWTTextBox textBox) {
		textBox.addKeyUpHandler(new KeyUpHandler() {			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				getErrorWidget().setVisibility(Visibility.HIDDEN);				
			}
		});
	}	
}
