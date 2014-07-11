package com.imotion.dslam.front.business.desktop.client.widget.preferences;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopPreferencesEvent;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopPreferencesEventTypes.EVENT_TYPE;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapFormFieldTextBox;
import com.selene.arch.exe.gwt.client.ui.widget.button.AEGWTButton;
import com.selene.arch.exe.gwt.client.ui.widget.popup.AEGWTPopup;
import com.selene.arch.exe.gwt.client.utils.AEGWTStringUtils;

public class CRONIOBusDesktopNewConnectionPopupForm extends AEGWTPopup {

	public static final String NAME = "CRONIOBusDesktopNewConnectionPopupForm";
	
	private DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	public static final int MODE_NEW_CONNECTION		= 1;
	public static final int MODE_RENAME_CONNECTION	= 2;

	private AEGWTBootstrapFormFieldTextBox	connectionNameField;
	private AEGWTButton						saveButton;
	private AEGWTButton						cancelButton;

	private int mode = MODE_NEW_CONNECTION;

	public CRONIOBusDesktopNewConnectionPopupForm(AEGWTICompositePanel parent) {
		super(true, parent);
		FlowPanel root = new FlowPanel();
		setWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.NEW_CONNECTION_FORM);

		//CONNECTIONNAME
		SimplePanel inputZone = new SimplePanel();
		root.add(inputZone);
		inputZone.addStyleName(DSLAMBusDesktopIStyleConstants.NEW_CONNECTION_FORM_INPUT_ZONE);

		connectionNameField = new AEGWTBootstrapFormFieldTextBox("", TEXTS.connectionname_input_placeholder());
		inputZone.add(connectionNameField);

		//SAVE
		FlowPanel saveButtonZone = new FlowPanel();
		root.add(saveButtonZone);
		saveButtonZone.addStyleName(DSLAMBusDesktopIStyleConstants.NEW_CONNECTION_FORM_SAVE_ZONE);

		saveButton = new AEGWTButton(TEXTS.create());
		saveButtonZone.add(saveButton);
		saveButton.addStyleName(AEGWTIBoostrapConstants.BTN);
		saveButton.addStyleName(AEGWTIBoostrapConstants.BTN_PRIMARY);

		//SAVE
		cancelButton = new AEGWTButton(TEXTS.cancel());
		saveButtonZone.add(cancelButton);
		cancelButton.addStyleName(AEGWTIBoostrapConstants.BTN);
		cancelButton.addStyleName(AEGWTIBoostrapConstants.BTN_LINK);

		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (AEGWTStringUtils.isEmptyString(connectionNameField.getText())) {
					connectionNameField.setErrorLabelText(TEXTS.empty_textbox());
				} else {
					
					CRONIOBusDesktopPreferencesEvent saveConnectionEvent = new CRONIOBusDesktopPreferencesEvent(getWindowName(), getName());
					if (mode == MODE_NEW_CONNECTION) {
						saveConnectionEvent.setEventType(EVENT_TYPE.NEW_CONNECTION);
					} else {
						saveConnectionEvent.setEventType(EVENT_TYPE.EDIT_CONNECTION);
						saveConnectionEvent.setConnectionName(getId());
					}
					saveConnectionEvent.addElementAsString(CRONIOBOIMachineProperties.MACHINE_NAME			, connectionNameField.getText());
					
					getLogicalEventHandlerManager().fireEvent(saveConnectionEvent);
				}
			}
		});

		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});

		connectionNameField.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				connectionNameField.setErrorLabelVisible(false);
			}
		});
	}

	public void setError(String error) {
		connectionNameField.setErrorLabelText(error);
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	@Override
	public void center() {
		connectionNameField.setText("");
		connectionNameField.setErrorLabelVisible(false);
		if (mode == MODE_NEW_CONNECTION) {
			saveButton.setText(TEXTS.create());
		} 
		super.center();
		connectionNameField.setFocus(true);
	}


	/**
	 * AEGWTICompositePanel
	 */

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		if (data != null) {
			String 	connectionName 	= getElementController().getElementAsString(CRONIOBOIMachineProperties.MACHINE_NAME	, data);
			setId(connectionName);
			connectionNameField.setText(connectionName);
		}
	}
}
