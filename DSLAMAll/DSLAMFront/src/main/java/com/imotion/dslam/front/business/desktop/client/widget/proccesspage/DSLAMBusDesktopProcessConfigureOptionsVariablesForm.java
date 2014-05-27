package com.imotion.dslam.front.business.desktop.client.widget.proccesspage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.DSLAMBOIVariablesDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapFormFieldTextBox;
import com.selene.arch.exe.gwt.client.ui.widget.button.AEGWTButton;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class DSLAMBusDesktopProcessConfigureOptionsVariablesForm extends AEGWTCompositePanel {

	public static final String NAME = "DSLAMBusDesktopProcessConfigureOptionsVariablesForm";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private AEGWTBootstrapFormFieldTextBox  		variableIdTextBox;
	private AEGWTBootstrapFormFieldTextBox  		variableValueTextBox; 
	private AEGWTButton 							saveButton;

	public DSLAMBusDesktopProcessConfigureOptionsVariablesForm() {
		FlowPanel root = new FlowPanel();
		root.addStyleName(AEGWTIBoostrapConstants.COL_XS_12);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.POPUP_VARIABLES_FORM_CONTAINER);
		initWidget(root);

		variableIdTextBox 			= new AEGWTBootstrapFormFieldTextBox(TEXTS.variable()	, TEXTS.variable());
		variableValueTextBox 		= new AEGWTBootstrapFormFieldTextBox(TEXTS.value()		, TEXTS.value());
		
		root.add(variableIdTextBox);
		root.add(variableValueTextBox);
		
		saveButton = new AEGWTButton("Guardar");
		root.add(saveButton);
		saveButton.setStyleName(AEGWTIBoostrapConstants.BTN);
		saveButton.addStyleName(AEGWTIBoostrapConstants.BTN_SUCCESS);
		//saveButton.addStyleName(BusinessDesktopManagementIStyleConstants.MENUPART_POPUP_BUTTONS);
		
		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				boolean errors = false;
				
				resetErrors();
				
				if (variableIdTextBox.getTextBox().getValue() == null || variableIdTextBox.getTextBox().getValue() == "") {
					errors = true;
					variableIdTextBox.setErrorLabelTextAndShow(TEXTS.empty_textbox());
				}
				
				if (variableValueTextBox.getTextBox().getValue() == null || variableValueTextBox.getTextBox().getValue() == "") {
					errors = true;
					variableValueTextBox.setErrorLabelTextAndShow(TEXTS.empty_textbox());
				}
				
				AEMFTMetadataElementComposite data = getData();
				if (data != null && errors == false) {
					AEGWTLogicalEvent evt = new AEGWTLogicalEvent(getWindowName(), getName());
					evt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
					evt.setSourceWidgetId(getId());
					evt.addElementAsDataValue(data);
					getLogicalEventHandlerManager().fireEvent(evt);
				} 
			} 
		});
	}
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite variableData) {
//		if (variableData != null) {
//			String 			variableId 		= getElementController().getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_ID		, variableData);
//			String 			variableValue 	= getElementController().getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE	, variableData);
//			
//			if (!AEGWTStringUtils.isEmptyString(variableId)) {
//				super.setEditMode(true);
//			}
//
//			variableIdTextBox.setText(variableId);
//			variableValueTextBox.setText(variableValue);
//		}
	}
//
	
	public AEMFTMetadataElementComposite getData() {

		AEMFTMetadataElementComposite formData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();

		getElementController().setElement(DSLAMBOIVariablesDataConstants.VARIABLE_ID		, formData	, variableIdTextBox.getText());
		getElementController().setElement(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE		, formData	, variableValueTextBox.getText());
		
		return formData;
	}

	protected void resetForm() {
		variableIdTextBox.setText("");
		variableValueTextBox.setText(""); 
	}
	
	protected void resetErrors() {
		variableIdTextBox.setErrorLabelVisible(false);
		variableValueTextBox.setErrorLabelVisible(false); 
	}
}
