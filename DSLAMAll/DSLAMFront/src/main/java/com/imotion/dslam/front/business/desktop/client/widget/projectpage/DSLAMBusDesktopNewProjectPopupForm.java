package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.imotion.dslam.bom.CRONIOBOIProjectDataConstants;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopProjectEvent;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopProjectEventTypes.EVENT_TYPE;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapDropdownAndLabelTextBox;
import com.selene.arch.exe.gwt.client.ui.widget.button.AEGWTButton;
import com.selene.arch.exe.gwt.client.ui.widget.popup.AEGWTPopup;
import com.selene.arch.exe.gwt.client.utils.AEGWTStringUtils;

public class DSLAMBusDesktopNewProjectPopupForm extends AEGWTPopup {

	public static final String NAME = "DSLAMBusDesktopNewProjectPopupForm";
	
	private DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	public static final int MODE_NEW_PROJECT		= 1;
	public static final int MODE_RENAME_PROJECT	= 2;

	private AEGWTBootstrapDropdownAndLabelTextBox	projectNameField;
	private AEGWTButton								saveButton;
	private AEGWTButton								cancelButton;

	private int mode = MODE_NEW_PROJECT;

	public DSLAMBusDesktopNewProjectPopupForm(AEGWTICompositePanel parent) {
		super(true, parent);
		FlowPanel root = new FlowPanel();
		setWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.NEW_PROJECT_FORM);

		//PROJECTNAME
		SimplePanel inputZone = new SimplePanel();
		root.add(inputZone);
		inputZone.addStyleName(DSLAMBusDesktopIStyleConstants.NEW_PROJECT_FORM_INPUT_ZONE);

		projectNameField = new AEGWTBootstrapDropdownAndLabelTextBox(TEXTS.projectname_input_placeholder());
		inputZone.add(projectNameField);
		projectNameField.addElement(String.valueOf(CRONIOBOIProjectDataConstants.PROJECT_MACHINE_TYPE_DSLAM), TEXTS.project_type_dslam());

		//SAVE
		FlowPanel saveButtonZone = new FlowPanel();
		root.add(saveButtonZone);
		saveButtonZone.addStyleName(DSLAMBusDesktopIStyleConstants.NEW_PROJECT_FORM_SAVE_ZONE);

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
				if (AEGWTStringUtils.isEmptyString(projectNameField.getText())) {
					projectNameField.setErrorLabelText(TEXTS.empty_textbox());
				} else {
					
					CRONIOBusDesktopProjectEvent saveProjectEvent = new CRONIOBusDesktopProjectEvent(getWindowName(), getName());
					if (mode == MODE_NEW_PROJECT) {
						saveProjectEvent.setEventType(EVENT_TYPE.NEW_PROJECT);
					} else {
						saveProjectEvent.setEventType(EVENT_TYPE.EDIT_PROJECT_PROPERTIES);
						saveProjectEvent.setProjectId(getId());
					}
					saveProjectEvent.addElementAsString(CRONIOBOIProjectDataConstants.PROJECT_NAME			, projectNameField.getText());
					saveProjectEvent.addElementAsString(CRONIOBOIProjectDataConstants.PROJECT_MACHINE_TYPE	, projectNameField.getSelectedId());
					getLogicalEventHandlerManager().fireEvent(saveProjectEvent);
				}
			}
		});

		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});

		projectNameField.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				projectNameField.setErrorLabelVisible(false);
			}
		});
	}

	public void setError(String error) {
		projectNameField.setErrorLabelText(error);
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	@Override
	public void center() {
		projectNameField.setText("");
		projectNameField.setErrorLabelVisible(false);
		if (mode == MODE_NEW_PROJECT) {
			setContentTypeEnabled(true);
			saveButton.setText(TEXTS.create());
		} 
		super.center();
		projectNameField.setFocus(true);
	}

	public void setContentTypeEnabled(boolean enabled) {
		projectNameField.setDropdownEnabled(enabled);
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
			String 	projectId 		= getElementController().getElementAsString(DSLAMBOIProject.PROJECT_ID		, data);
			String 	projectName 	= getElementController().getElementAsString(DSLAMBOIProject.PROJECT_NAME	, data);
			setId(projectId);
			projectNameField.setText(projectName);
		}
	}
}
