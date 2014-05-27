package com.imotion.dslam.front.business.desktop.client.widget.proccesspage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.DSLAMBOIVariablesDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.processpage.DSLAMBusDesktopProcessPagePresenter;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.validation.AEGWTIValidationChangeHandler;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapForm;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapFormFieldTextBox;
import com.selene.arch.exe.gwt.client.utils.AEGWTStringUtils;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;

public class DSLAMBusDesktopProcessConfigureOptionsVariablesForm extends AEGWTBootstrapForm {

	public static final String NAME = "DSLAMBusDesktopProcessConfigureOptionsVariablesForm";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private AEGWTBootstrapFormFieldTextBox  		variableIdTextBox;
	private AEGWTBootstrapFormFieldTextBox  		variableValueTextBox; 
	

	public DSLAMBusDesktopProcessConfigureOptionsVariablesForm() {
		setButtonText(BUTTON_SUBMIT, TEXTS.save());
		addContainerStyle(AEGWTIBoostrapConstants.COL_XS_12);
		addContainerStyle(DSLAMBusDesktopIStyleConstants.POPUP_VARIABLES_FORM_CONTAINER);

		variableIdTextBox 			= new AEGWTBootstrapFormFieldTextBox(TEXTS.variable()	, TEXTS.variable());
		variableValueTextBox 		= new AEGWTBootstrapFormFieldTextBox(TEXTS.value()		, TEXTS.value());
		
		FlowPanel leftTextBoxes = new FlowPanel();
		leftTextBoxes.addStyleName(AEGWTIBoostrapConstants.COL_XS_12);
		leftTextBoxes.add(variableIdTextBox);
		leftTextBoxes.add(variableValueTextBox);
		
		addWidget(leftTextBoxes);
		addButtonStyle(BUTTON_SUBMIT, DSLAMBusDesktopIStyleConstants.SUBMIT_BUTTON_VARIABLES_FORM);

	}
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite variableData) {
		if (variableData != null) {
			String 			variableId 		= getElementController().getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_ID		, variableData);
			String 			variableValue 	= getElementController().getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE	, variableData);
			
			if (!AEGWTStringUtils.isEmptyString(variableId)) {
				super.setEditMode(true);
			}

			variableIdTextBox.setText(variableId);
			variableValueTextBox.setText(variableValue);
		}
	}

	@Override
	public AEMFTMetadataElementComposite getData() {

		AEMFTMetadataElementComposite formData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();

		getElementController().setElement(DSLAMBOIVariablesDataConstants.VARIABLE_ID		, formData	, variableIdTextBox.getText());
		getElementController().setElement(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE		, formData	, variableValueTextBox.getText());
		
		return formData;
	}

	@Override
	public void initFormValidation(
			AEGWTIValidationChangeHandler formValidationhandler) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void resetForm() {
		variableIdTextBox.setText("");
		variableValueTextBox.setText(""); 
	}


	@Override
	protected void resetErrorLabel() {
		variableIdTextBox.setErrorLabelVisible(false);
		variableValueTextBox.setErrorLabelVisible(false);
	}

	@Override
	protected boolean isValidEvent(AEGWTLogicalEvent evt) {
		if (DSLAMBusDesktopProcessPagePresenter.NAME.equals(evt.getSourceWidget())) {
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
