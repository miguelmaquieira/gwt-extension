package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIVariablesDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapDropdownAndLabelTextBox;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapFormFieldTextBox;
import com.selene.arch.exe.gwt.client.ui.widget.button.AEGWTButton;
import com.selene.arch.exe.gwt.client.ui.widget.popup.AEGWTPopup;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class DSLAMBusDesktopProcessConfigureVariablesForm extends AEGWTPopup {

	public static final String NAME = "DSLAMBusDesktopProcessConfigureVariablesForm";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private AEGWTBootstrapDropdownAndLabelTextBox  	variableIdTextBox;
	private AEGWTBootstrapFormFieldTextBox  		variableValueTextBox; 
	private AEGWTButton 							saveButton;
	private AEGWTButton								cancelButton;
	private boolean								editMode;

	public DSLAMBusDesktopProcessConfigureVariablesForm(AEGWTICompositePanel parent) {
		super(true, parent);
		FlowPanel root = new FlowPanel();
		setWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.POPUP_VARIABLES_FORM_CONTAINER);


		variableIdTextBox 			= new AEGWTBootstrapDropdownAndLabelTextBox(null	, TEXTS.variable());
		variableValueTextBox 		= new AEGWTBootstrapFormFieldTextBox(null			, TEXTS.value());
		
		root.add(variableIdTextBox);
		variableIdTextBox.addElement(String.valueOf(DSLAMBOIVariablesDataConstants.VARIABLE_PROCESS_TYPE), TEXTS.process_variable());
		variableIdTextBox.addElement(String.valueOf(DSLAMBOIVariablesDataConstants.VARIABLE_EXTERNAL_TYPE), TEXTS.external_variable());
		root.add(variableValueTextBox);
		
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
				
				if (variableIdTextBox.getTextBox().getValue() == null || variableIdTextBox.getTextBox().getValue() == "") {
					errors = true;
					variableIdTextBox.setErrorLabelText(TEXTS.empty_textbox());
				}
				
				if (variableValueTextBox.getTextBox().getValue() == null || variableValueTextBox.getTextBox().getValue() == "") {
					errors = true;
					variableValueTextBox.setErrorLabelTextAndShow(TEXTS.empty_textbox());
				}
				
				if (errors == false) {
					AEGWTLogicalEvent evt = new AEGWTLogicalEvent(getWindowName(), getName());
					evt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
					evt.setSourceWidgetId(getId());
					evt.addElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_ID		, variableIdTextBox.getText());
					evt.addElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE	, variableValueTextBox.getText());
					evt.addElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE		, variableIdTextBox.getSelectedId());
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
			String 	variableId 		= getElementController().getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_ID		, variableData);
			String 	variableValue 	= getElementController().getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE	, variableData);
			String 	variableType 	= getElementController().getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE	, variableData);
			
			variableIdTextBox.setEnabled(false);
			variableIdTextBox.setDropdownEnabled(true);
			variableIdTextBox.setItemSelected(variableType);	

			variableIdTextBox.setText(variableId);
			variableValueTextBox.setText(variableValue);
		}
	}

	
	public AEMFTMetadataElementComposite getData() {

		AEMFTMetadataElementComposite formData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();

		getElementController().setElement(DSLAMBOIVariablesDataConstants.VARIABLE_ID		, formData	, variableIdTextBox.getText());
		getElementController().setElement(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE		, formData	, variableValueTextBox.getText());
		
		return formData;
	}

	protected void resetForm() {
		variableIdTextBox.setText("");
		variableValueTextBox.setText("");
		variableIdTextBox.setEnabled(true);
		resetErrors();
		hide();
	}
	
	protected void resetErrors() {
		variableIdTextBox.setErrorLabelVisible(false);
		variableValueTextBox.setErrorLabelVisible(false); 
	}
	
	protected void setErrorVariableExist() {
		variableIdTextBox.setErrorLabelText(TEXTS.error_variable_exist());
		variableIdTextBox.setErrorLabelVisible(true);
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
			variableIdTextBox.setEnabled(true);
		} else {
			variableIdTextBox.setEnabled(false);
		}
	}
}
