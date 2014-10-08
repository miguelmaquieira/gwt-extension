package com.imotion.dslam.front.business.desktop.client.widget.preferences;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.bom.CRONIOBOIUserPreferences;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopPreferencesEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopPreferencesEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.view.preferences.user.CRONIOBusI18NPreferencesUserTexts;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.validation.AEGWTIValidationChangeHandler;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapForm;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapFormFieldTextBoxLabelTop;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;

public class CRONIOBusDesktopPreferencesUserConfigureForm extends AEGWTBootstrapForm {

	public static final String NAME = "CRONIOBusDesktopPreferencesUserConfigureForm";
	private static CRONIOBusI18NTexts 							TEXTS 				= GWT.create(CRONIOBusI18NTexts.class);
	private static CRONIOBusI18NPreferencesUserTexts 	PREFERENCES_TEXTS 	= GWT.create(CRONIOBusI18NPreferencesUserTexts.class);

	private AEGWTBootstrapFormFieldTextBoxLabelTop			downTimeTextBox;


	public CRONIOBusDesktopPreferencesUserConfigureForm() {
		setButtonText(BUTTON_SUBMIT, TEXTS.save());
//		//setButtonStyle(BUTTON_SUBMIT, AEGWTIBoostrapConstants.);
		addButtonStyle(BUTTON_SUBMIT, AEGWTIBoostrapConstants.BTN);
//		// invisible, no need
		setButtonVisible(BUTTON_SUBMIT, false);

		FlowPanel textBoxesZone = new FlowPanel();
		addWidget(textBoxesZone);
		textBoxesZone.addStyleName(CRONIOBusDesktopIStyleConstants.PREFERENCES_USER_CONFIGURE_FORM);
		textBoxesZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_4);

		
		//downTime
		downTimeTextBox 			= new AEGWTBootstrapFormFieldTextBoxLabelTop(PREFERENCES_TEXTS.downtime_placeholder(), PREFERENCES_TEXTS.downtime_placeholder());
		textBoxesZone.add(downTimeTextBox);
		
		downTimeTextBox.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				AEMFTMetadataElementComposite formData = getData();

				CRONIOBusDesktopPreferencesEvent userPreferencesFormEvt = new CRONIOBusDesktopPreferencesEvent(getId(), getName());
				userPreferencesFormEvt.setEventType(EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT);
				userPreferencesFormEvt.addElementAsComposite(CRONIOBOIUserPreferences.DOWNTIME ,formData);
				//userPreferencesFormEvt.setConnectionName(getId());
				getLogicalEventHandlerManager().fireEvent(userPreferencesFormEvt);
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
	public void setData(AEMFTMetadataElementComposite userPreferencesData) {
		if (userPreferencesData != null) {
			
			int 	downTime 	= getElementController().getElementAsInt(CRONIOBOIUserPreferences.DOWNTIME				, userPreferencesData);

			if (downTime > 0) {
				downTimeTextBox.setText(String.valueOf(downTime));
			}
		}
	}

	@Override
	public AEMFTMetadataElementComposite getData() {
		boolean errors = showValidateClientErrors();
		if (errors == false) {
			String	downTimeStr		= downTimeTextBox.getText();
			
			int		downTimeInt		= AEMFTCommonUtilsBase.getIntegerFromString(downTimeStr);
			
			AEMFTMetadataElementComposite formData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
			getElementController().setElement(CRONIOBOIPreferences.DOWNTIME					, formData	, downTimeInt);
			
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

	/**
	 * PROTECTED
	 */

	@Override
	protected void resetForm() {
		downTimeTextBox.setText("");
	}

	@Override
	protected void resetErrorLabel() {
		downTimeTextBox.setErrorLabelVisible(false);
	}

	@Override
	protected boolean isValidEvent(AEGWTLogicalEvent evt) {
//		if (CRONIOBusDesktopPreferencesConnectionPresenter.NAME.equals(evt.getSourceWidget())) {
//			return true;
//		} else return false;
		return false;
	}

	@Override
	protected void showErrors(AEMFTMetadataElementComposite dataErrors) {

	}

	/**
	 * PRIVATE
	 */
}
