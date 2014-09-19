package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOINodeDataConstants;
import com.imotion.dslam.bom.DSLAMBOIVariablesDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapDropdownButton;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapFormFieldTextArea;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapFormFieldTextBox;
import com.selene.arch.exe.gwt.client.ui.widget.button.AEGWTButton;
import com.selene.arch.exe.gwt.client.ui.widget.popup.AEGWTPopup;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopProcessAddNodeForm extends AEGWTPopup {

	public static final String NAME = "CRONIOBusDesktopProcessAddNodeForm";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private AEGWTBootstrapFormFieldTextBox  	nameTextBox;
	private AEGWTBootstrapFormFieldTextBox  	ipTextBox;
	private AEGWTBootstrapDropdownButton		machineTypeDropdownButton;
	private AEGWTBootstrapFormFieldTextArea  	variablesTextBox;
	private AEGWTButton 						saveButton;
	private AEGWTButton							cancelButton;
	private boolean							editMode;

	public CRONIOBusDesktopProcessAddNodeForm(AEGWTICompositePanel parent) {
		super(true, parent);
		FlowPanel root = new FlowPanel();
		setWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.POPUP_NODES_FORM_CONTAINER);
		
		nameTextBox = new AEGWTBootstrapFormFieldTextBox(null, TEXTS.node_name());
		root.add(nameTextBox);
		
		ipTextBox = new AEGWTBootstrapFormFieldTextBox(null, TEXTS.ip());
		root.add(ipTextBox);
		
		machineTypeDropdownButton = new AEGWTBootstrapDropdownButton();
		root.add(machineTypeDropdownButton);
	//	machineTypeDropdownButton.addElement(String.valueOf(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE_TEXT)		, TEXTS.text_variable());
	//	machineTypeDropdownButton.addElement(String.valueOf(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE_NUMERIC)	, TEXTS.numeric_variable());
		
		variablesTextBox = new AEGWTBootstrapFormFieldTextArea(null, TEXTS.variables());
		root.add(variablesTextBox);
		
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
				
				if (nameTextBox.getTextBox().getValue() == null || nameTextBox.getTextBox().getValue() == "") {
					errors = true;
					nameTextBox.setErrorLabelText(TEXTS.empty_textbox());
				}
				
				if (ipTextBox.getTextBox().getValue() == null || ipTextBox.getTextBox().getValue() == "") {
					errors = true;
					ipTextBox.setErrorLabelText(TEXTS.empty_textbox());
				}
				
				String ipRegEx 	= "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}";
				
				if (!ipRegEx.matches(ipTextBox.getTextBox().getValue())) {
					errors = true;
					ipTextBox.setErrorLabelText(TEXTS.ip_error_textbox());
				}
				
				if (errors == false) {
					AEGWTLogicalEvent evt = new AEGWTLogicalEvent(getWindowName(), getName());
					evt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
					evt.setSourceWidgetId(getId());
					evt.addElementAsString(CRONIOBOINodeDataConstants.NODE_NAME				, nameTextBox.getText());
					evt.addElementAsString(CRONIOBOINodeDataConstants.NODE_IP				, ipTextBox.getText());
					evt.addElementAsString(CRONIOBOINodeDataConstants.NODE_TYPE				, machineTypeDropdownButton.getSelectedId());
					evt.addElementAsString(CRONIOBOINodeDataConstants.NODE_VARIABLE_LIST	, variablesTextBox.getText());
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
		nameTextBox.setText("");
		ipTextBox.setText("");
		variablesTextBox.setText("");
	//	machineTypeDropdownButton.setItemSelected(id);
		resetErrors();
		hide();
	}
	
//	public void resetForm(int variableScopeDefault, int variableTypeDefault ) {
//		variableIdTextBox.setItemSelected(String.valueOf(variableScopeDefault));
//		variableValueTextBox.setItemSelected(String.valueOf(variableTypeDefault));
//		resetForm();
//	}
	
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
	
//	public void setErrorVariableExist() {
//		variableIdTextBox.setErrorLabelText(TEXTS.error_variable_exist());
//		variableIdTextBox.setErrorLabelVisible(true);
//	}
//	
//	public void addVariableScope(int variableScope , String text) {
//		variableIdTextBox.addElement(String.valueOf(variableScope)	, text);
//	}
	
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
//			String 	nodeName 		= getElementController().getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_NAME	, variableData);
//			String 	nodeIp 		= getElementController().getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE	, variableData);
//			int 	variableScope 		= getElementController().getElementAsInt(DSLAMBOIVariablesDataConstants.VARIABLE_SCOPE		, variableData);
//			int 	variableType 		= getElementController().getElementAsInt(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE		, variableData);
//			String	variableScopeStr	= String.valueOf(variableScope);
//			String	variableTypeStr		= String.valueOf(variableType);
//			
//			variableIdTextBox.setEnabled(false);
//			variableIdTextBox.setDropdownEnabled(true);
//			variableIdTextBox.setItemSelected(variableScopeStr);	
//			variableIdTextBox.setText(variableName);
//			
//			variableValueTextBox.setItemSelected(variableTypeStr);	
//			variableValueTextBox.setText(variableValue);
		}
	}
	
	/**
	 * PRIVATE
	 */
	
	private void resetErrors() {
		nameTextBox.setErrorLabelVisible(false);
		ipTextBox.setErrorLabelVisible(false); 
		variablesTextBox.setErrorLabelVisible(false); 
	}
}
