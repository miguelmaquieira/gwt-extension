package com.imotion.dslam.front.business.desktop.client.widget.preferences;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIVariablesDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapFormFieldTextBox;
import com.selene.arch.exe.gwt.client.ui.widget.button.AEGWTButton;
import com.selene.arch.exe.gwt.client.ui.widget.popup.AEGWTPopup;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopPreferencesMachineVariablesForm extends AEGWTPopup {

	public static final String NAME = "CRONIOBusDesktopPreferencesMachineVariablesForm";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private AEGWTBootstrapFormFieldTextBox  	machineVariableIdTextBox;
	private AEGWTBootstrapFormFieldTextBox  	machineVariableValueTextBox; 
	private AEGWTButton 						saveButton;
	private AEGWTButton							cancelButton;
	private boolean							editMode;

	public CRONIOBusDesktopPreferencesMachineVariablesForm(AEGWTICompositePanel parent) {
		super(true, parent);
		FlowPanel root = new FlowPanel();
		setWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.POPUP_VARIABLES_FORM_CONTAINER);


		machineVariableIdTextBox 			= new AEGWTBootstrapFormFieldTextBox(null	, TEXTS.variable());
		machineVariableValueTextBox 		= new AEGWTBootstrapFormFieldTextBox(null	, TEXTS.value());
		
		root.add(machineVariableIdTextBox);
		root.add(machineVariableValueTextBox);
		
		FlowPanel saveButtonZone = new FlowPanel();
		root.add(saveButtonZone);
		
		saveButton = new AEGWTButton(TEXTS.save());
		saveButtonZone.add(saveButton);
		saveButton.setStyleName(AEGWTIBoostrapConstants.BTN);
		saveButton.addStyleName(AEGWTIBoostrapConstants.BTN_PRIMARY);
		
		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				boolean errors = false;
				
				resetErrors();
				
				if (AEMFTCommonUtilsBase.isEmptyString(machineVariableIdTextBox.getTextBox().getValue())) {
					errors = true;
					machineVariableIdTextBox.setErrorLabelText(TEXTS.empty_textbox());
				}
				
				if (AEMFTCommonUtilsBase.isEmptyString(machineVariableValueTextBox.getTextBox().getValue())) {
					errors = true;
					machineVariableValueTextBox.setErrorLabelTextAndShow(TEXTS.empty_textbox());
				}
				
				if (errors == false) {
					AEGWTLogicalEvent evt = new AEGWTLogicalEvent(getWindowName(), getName());
					evt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
					evt.setSourceWidgetId(getId());
					evt.addElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_NAME		, machineVariableIdTextBox.getText());
					evt.addElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE	, machineVariableValueTextBox.getText());
					getLogicalEventHandlerManager().fireEvent(evt);
				} 
			} 
		});
		
		cancelButton = new AEGWTButton(TEXTS.cancel());
		saveButtonZone.add(cancelButton);
		cancelButton.addStyleName(AEGWTIBoostrapConstants.BTN);
		cancelButton.addStyleName(AEGWTIBoostrapConstants.BTN_LINK);
		
		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite variableData) {
		if (variableData != null) {
			String 	variableId 		= getElementController().getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_NAME	, variableData);
			String 	variableValue 	= getElementController().getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE	, variableData);
			
			machineVariableIdTextBox.setEnabled(false);
	
			machineVariableIdTextBox.setText(variableId);
			machineVariableValueTextBox.setText(variableValue);
		}
	}

	
	public AEMFTMetadataElementComposite getData() {

		AEMFTMetadataElementComposite formData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();

		getElementController().setElement(DSLAMBOIVariablesDataConstants.VARIABLE_NAME		, formData	, machineVariableIdTextBox.getText());
		getElementController().setElement(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE		, formData	, machineVariableValueTextBox.getText());
		
		return formData;
	}

	protected void resetForm() {
		machineVariableIdTextBox.setText("");
		machineVariableValueTextBox.setText("");
		machineVariableIdTextBox.setEnabled(true);
		resetErrors();
		hide();
	}
	
	protected void resetErrors() {
		machineVariableIdTextBox.setErrorLabelVisible(false);
		machineVariableValueTextBox.setErrorLabelVisible(false); 
	}
	
	protected void setErrorVariableExist() {
		machineVariableIdTextBox.setErrorLabelText(TEXTS.error_variable_exist());
		machineVariableIdTextBox.setErrorLabelVisible(true);
	}
	
	protected void setEditMode(String mode) {
		if(DSLAMBOIVariablesDataConstants.EDIT_MODE.equals(mode)) {
			editMode = true;
		} else {
			editMode = false;
		}
	}
	
	protected boolean getEditMode() {
		return editMode;
	}
	
	protected void setVariableIdTextBoxEnable(boolean enable) {
		if (enable) {
			machineVariableIdTextBox.setEnabled(true);
		} else {
			machineVariableIdTextBox.setEnabled(false);
		}
	}
}
