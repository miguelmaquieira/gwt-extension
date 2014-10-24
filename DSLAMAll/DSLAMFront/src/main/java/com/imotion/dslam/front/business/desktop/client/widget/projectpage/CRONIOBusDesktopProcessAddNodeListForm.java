package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOINodeListDataConstants;
import com.imotion.dslam.bom.CRONIOBOIProjectDataConstants;
import com.imotion.dslam.bom.CRONIOBOIVariablesDataConstants;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapFormFieldTextBox;
import com.selene.arch.exe.gwt.client.ui.widget.button.AEGWTButton;
import com.selene.arch.exe.gwt.client.ui.widget.popup.AEGWTPopup;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopProcessAddNodeListForm extends AEGWTPopup {

	public static final String NAME = "CRONIOBusDesktopProcessAddNodeListForm";
	private static CRONIOBusI18NTexts TEXTS = GWT.create(CRONIOBusI18NTexts.class);

	private AEGWTBootstrapFormFieldTextBox  	nameNodeListTextBox;
	private AEGWTButton 						saveButton;
	private AEGWTButton							cancelButton;
	private boolean							editMode;
	private String 								projectId;
	

	public CRONIOBusDesktopProcessAddNodeListForm(AEGWTICompositePanel parent) {
		super(true, parent);
		FlowPanel root = new FlowPanel();
		setWidget(root);
		root.addStyleName(CRONIOBusDesktopIStyleConstants.POPUP_NODE_LIST_FORM_CONTAINER);
		
		nameNodeListTextBox = new AEGWTBootstrapFormFieldTextBox(null, TEXTS.node_list_name());
		root.add(nameNodeListTextBox);

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
				
				if (nameNodeListTextBox.getTextBox().getValue() == null || nameNodeListTextBox.getTextBox().getValue() == "") {
					errors = true;
					nameNodeListTextBox.setErrorLabelText(TEXTS.empty_textbox());
				}
				
				if (errors == false) {
					AEGWTLogicalEvent evt = new AEGWTLogicalEvent(getWindowName(), getName());
					evt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
					evt.setSourceWidgetId(getId());
					evt.addElementAsString(CRONIOBOIProjectDataConstants.PROJECT_ID		,	projectId);
					evt.addElementAsString(CRONIOBOINodeListDataConstants.NODELIST_NAME	, nameNodeListTextBox.getText());
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
				resetForm();
			}
		});
	}
	
	public void resetForm() {
		nameNodeListTextBox.setText("");
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
	
	public void setErrorNodeListExist() {
		nameNodeListTextBox.setErrorLabelText(TEXTS.error_node_exist());
		nameNodeListTextBox.setErrorLabelVisible(true);
	}
	

	public void showDuplicateNodeListNameError(String nodeListName) {
		nameNodeListTextBox.setErrorLabelTextAndShow(TEXTS.nodelist_exist_error() + nodeListName);
	}
	
	public void setProjectId (String projectId) {
		this.projectId = projectId;
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
		
	}
	
	/**
	 * PRIVATE
	 */
	
	private void resetErrors() {
		nameNodeListTextBox.setErrorLabelVisible(false);
	}
	
}
