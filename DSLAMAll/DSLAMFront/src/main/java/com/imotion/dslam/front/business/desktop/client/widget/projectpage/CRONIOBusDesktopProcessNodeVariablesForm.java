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
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapFormFieldTextBox;
import com.selene.arch.exe.gwt.client.ui.widget.button.AEGWTButton;
import com.selene.arch.exe.gwt.client.ui.widget.popup.AEGWTPopup;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopProcessNodeVariablesForm extends AEGWTPopup {

	public static final String NAME = "CRONIOBusDesktopProcessNodeVariablesForm";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private AEGWTBootstrapFormFieldTextBox  	nodeVariableIdTextBox;
	private AEGWTBootstrapFormFieldTextBox  	nodeVariableValueTextBox; 
	private AEGWTButton 						saveButton;
	private AEGWTButton							cancelButton;
	private boolean							editMode;

	public CRONIOBusDesktopProcessNodeVariablesForm(AEGWTICompositePanel parent) {
		super(true, parent);
		FlowPanel root = new FlowPanel();
		setWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.POPUP_VARIABLES_FORM_CONTAINER);


		nodeVariableIdTextBox 			= new AEGWTBootstrapFormFieldTextBox(null	, TEXTS.variable());
		nodeVariableValueTextBox 		= new AEGWTBootstrapFormFieldTextBox(null	, TEXTS.value());
		
		root.add(nodeVariableIdTextBox);
		root.add(nodeVariableValueTextBox);
		
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
				
				if (AEMFTCommonUtilsBase.isEmptyString(nodeVariableIdTextBox.getTextBox().getValue())) {
					errors = true;
					nodeVariableIdTextBox.setErrorLabelText(TEXTS.empty_textbox());
				}
				
				if (AEMFTCommonUtilsBase.isEmptyString(nodeVariableValueTextBox.getTextBox().getValue())) {
					errors = true;
					nodeVariableValueTextBox.setErrorLabelTextAndShow(TEXTS.empty_textbox());
				}
				
				if (errors == false) {
					AEGWTLogicalEvent evt = new AEGWTLogicalEvent(getWindowName(), getName());
					evt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
					evt.setSourceWidgetId(getId());
					evt.addElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_NAME		, nodeVariableIdTextBox.getText());
					evt.addElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE	, nodeVariableValueTextBox.getText());
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
			
			nodeVariableIdTextBox.setEnabled(false);
	
			nodeVariableIdTextBox.setText(variableId);
			nodeVariableValueTextBox.setText(variableValue);
		}
	}

	
	public AEMFTMetadataElementComposite getData() {

		AEMFTMetadataElementComposite formData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();

		getElementController().setElement(DSLAMBOIVariablesDataConstants.VARIABLE_NAME		, formData	, nodeVariableIdTextBox.getText());
		getElementController().setElement(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE		, formData	, nodeVariableValueTextBox.getText());
		
		return formData;
	}

	protected void resetForm() {
		nodeVariableIdTextBox.setText("");
		nodeVariableValueTextBox.setText("");
		nodeVariableIdTextBox.setEnabled(true);
		resetErrors();
		hide();
	}
	
	protected void resetErrors() {
		nodeVariableIdTextBox.setErrorLabelVisible(false);
		nodeVariableValueTextBox.setErrorLabelVisible(false); 
	}
	
	protected void setErrorVariableExist() {
		nodeVariableIdTextBox.setErrorLabelText(TEXTS.error_variable_exist());
		nodeVariableIdTextBox.setErrorLabelVisible(true);
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
			nodeVariableIdTextBox.setEnabled(true);
		} else {
			nodeVariableIdTextBox.setEnabled(false);
		}
	}
}
