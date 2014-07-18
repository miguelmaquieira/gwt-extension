package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIVariablesDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapDropdownAndLabelTextBox;
import com.selene.arch.exe.gwt.client.ui.widget.button.AEGWTButton;
import com.selene.arch.exe.gwt.client.ui.widget.popup.AEGWTPopup;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class DSLAMBusDesktopProcessConfigureVariablesForm extends AEGWTPopup {

	public static final String NAME = "DSLAMBusDesktopProcessConfigureVariablesForm";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private AEGWTBootstrapDropdownAndLabelTextBox  	variableIdTextBox;
	private AEGWTBootstrapDropdownAndLabelTextBox	variableValueTextBox; 
	private AEGWTButton 							saveButton;
	private AEGWTButton								cancelButton;
	private boolean								editMode;

	public DSLAMBusDesktopProcessConfigureVariablesForm(AEGWTICompositePanel parent) {
		super(true, parent);
		FlowPanel root = new FlowPanel();
		setWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.POPUP_VARIABLES_FORM_CONTAINER);


		variableIdTextBox = new AEGWTBootstrapDropdownAndLabelTextBox(null, TEXTS.variable());
		root.add(variableIdTextBox);
		
		variableValueTextBox = new AEGWTBootstrapDropdownAndLabelTextBox(null, TEXTS.value());
		root.add(variableValueTextBox);
		variableValueTextBox.addElement(String.valueOf(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE_TEXT)		, TEXTS.text_variable());
		variableValueTextBox.addElement(String.valueOf(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE_NUMERIC)	, TEXTS.numeric_variable());
		
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
					variableValueTextBox.setErrorLabelText(TEXTS.empty_textbox());
				}
				
				if (errors == false) {
					AEGWTLogicalEvent evt = new AEGWTLogicalEvent(getWindowName(), getName());
					evt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
					evt.setSourceWidgetId(getId());
					evt.addElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_NAME		, variableIdTextBox.getText());
					evt.addElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE	, variableValueTextBox.getText());
					evt.addElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_SCOPE	, variableIdTextBox.getSelectedId());
					evt.addElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE		, variableValueTextBox.getSelectedId());
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
	
	public void resetForm() {
		variableIdTextBox.setText("");
		variableValueTextBox.setText("");
		variableIdTextBox.setEnabled(true);
		resetErrors();
		hide();
	}
	
	public void resetForm(int variableScopeDefault, int variableTypeDefault ) {
		variableIdTextBox.setItemSelected(String.valueOf(variableScopeDefault));
		variableValueTextBox.setItemSelected(String.valueOf(variableTypeDefault));
		resetForm();
	}
	
	public void setEditMode(String mode) {
		if(DSLAMBOIVariablesDataConstants.EDIT_MODE.equals(mode)) {
			editMode = true;
		} else {
			editMode = false;
		}
	}
	
	public boolean getEditMode() {
		return editMode;
	}
	
	public void setErrorVariableExist() {
		variableIdTextBox.setErrorLabelText(TEXTS.error_variable_exist());
		variableIdTextBox.setErrorLabelVisible(true);
	}
	
	public void addVariableScope(int variableScope , String text) {
		variableIdTextBox.addElement(String.valueOf(variableScope)	, text);
	}
	
	/**
	 * AEGWTCompositePanel
	 */

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite variableData) {
		if (variableData != null) {
			String 	variableName 		= getElementController().getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_NAME	, variableData);
			String 	variableValue 		= getElementController().getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE	, variableData);
			int 	variableScope 		= getElementController().getElementAsInt(DSLAMBOIVariablesDataConstants.VARIABLE_SCOPE		, variableData);
			int 	variableType 		= getElementController().getElementAsInt(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE		, variableData);
			String	variableScopeStr	= String.valueOf(variableScope);
			String	variableTypeStr		= String.valueOf(variableType);
			
			variableIdTextBox.setEnabled(false);
			variableIdTextBox.setDropdownEnabled(true);
			variableIdTextBox.setItemSelected(variableScopeStr);	
			variableIdTextBox.setText(variableName);
			
			variableValueTextBox.setItemSelected(variableTypeStr);	
			variableValueTextBox.setText(variableValue);
		}
	}
	
	/**
	 * PRIVATE
	 */
	
	private void resetErrors() {
		variableIdTextBox.setErrorLabelVisible(false);
		variableValueTextBox.setErrorLabelVisible(false); 
	}
}
