package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOINodeDataConstants;
import com.imotion.dslam.bom.CRONIOBOIVariablesDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapDropdownButton;
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
				
				String 		numberpRegEx 	= "25[0-5]|2[0-4][0-9]|[0-9]|[1-9][0-9]|1[0-9][0-9]";
				String 		ip 				= ipTextBox.getTextBox().getValue();
				String[] 	ipSplit 		= ip.split ("\\.");
				int         ipSplitSize    	= ipSplit.length;
				
				if (ipSplitSize == 1 && !TEXTS.localhost().equalsIgnoreCase(ip)) {
					errors = true;
					ipTextBox.setErrorLabelText(TEXTS.ip_error_textbox());
				}
				
				if (ipSplitSize != 4 && ipSplitSize > 1) {
					errors = true;
					ipTextBox.setErrorLabelText(TEXTS.ip_error_textbox());
				} else if (ipSplitSize == 4){
					for (int i = 0;i < 4;i++) {
						if(!(ipSplit[i].matches(numberpRegEx))) {
							errors = true;
							ipTextBox.setErrorLabelText(TEXTS.ip_error_textbox());
						}
					}
				}
				
				if (errors == false) {
					AEGWTLogicalEvent evt = new AEGWTLogicalEvent(getWindowName(), getName());
					evt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
					evt.setSourceWidgetId(getId());
					evt.addElementAsString(CRONIOBOINodeDataConstants.NODE_NAME				, nameTextBox.getText());
					evt.addElementAsString(CRONIOBOINodeDataConstants.NODE_IP				, ipTextBox.getText());
					evt.addElementAsString(CRONIOBOINodeDataConstants.NODE_TYPE				, machineTypeDropdownButton.getSelectedId());
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
		resetErrors();
		hide();
	}
	
	public void setEditMode(String mode) {
		if(CRONIOBOIVariablesDataConstants.EDIT_MODE.equals(mode)) {
			editMode = true;
		} else {
			editMode = false;
		}
	}
	
	public boolean getEditMode() {
		return editMode;
	}
	
	public void setErrorNodeExist() {
		nameTextBox.setErrorLabelText(TEXTS.error_node_exist());
		nameTextBox.setErrorLabelVisible(true);
	}

	public void setMachineTypes(List<String> machineList) {
		
		for (String machine : machineList) {
			machineTypeDropdownButton.addElement(machine		, machine);
		}
	}
	
	/**
	 * AEGWTCompositePanel
	 */

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public void postDisplay() {
		super.postDisplay();
	}

	@Override
	public void setData(AEMFTMetadataElementComposite variableData) {
		if (variableData != null) {

		}
	}
	
	/**
	 * PRIVATE
	 */
	
	private void resetErrors() {
		nameTextBox.setErrorLabelVisible(false);
		ipTextBox.setErrorLabelVisible(false); 
	}
}
