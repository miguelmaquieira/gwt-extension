package com.imotion.dslam.front.business.desktop.client.widget.editor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.imotion.dslam.bom.DSLAMBOIFileDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.client.view.studio.DSLAMBusI18NStudioTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapDropdownAndLabelTextBox;
import com.selene.arch.exe.gwt.client.ui.widget.button.AEGWTButton;
import com.selene.arch.exe.gwt.client.ui.widget.popup.AEGWTPopup;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class DSLAMBusDesktopNewScriptPopupForm extends AEGWTPopup {
	
	public static final String NAME = "DSLAMBusDesktopNewScriptForm";
	
	private static final DSLAMBusI18NStudioTexts	STUDIO_TEXTS = GWT.create(DSLAMBusI18NStudioTexts.class);
	private static final DSLAMBusI18NTexts		COMMON_TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private AEGWTBootstrapDropdownAndLabelTextBox	filenameField;
	private AEGWTButton								saveButton;
	private AEGWTButton								cancelButton;
	
	public DSLAMBusDesktopNewScriptPopupForm(AEGWTICompositePanel parent) {
		super(true, parent);
		FlowPanel root = new FlowPanel();
		setWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.NEW_SCRIPT_FORM);
		
		//FILENAME
		SimplePanel inputZone = new SimplePanel();
		root.add(inputZone);
		inputZone.addStyleName(DSLAMBusDesktopIStyleConstants.NEW_SCRIPT_FORM_INPUT_ZONE);
		
		filenameField = new AEGWTBootstrapDropdownAndLabelTextBox(STUDIO_TEXTS.filename_input_placeholder());
		inputZone.add(filenameField);
		filenameField.addElement(DSLAMBOIFileDataConstants.CONTENT_TYPE_DSLAM, STUDIO_TEXTS.file_type_dslam());
		
		//SAVE
		FlowPanel saveButtonZone = new FlowPanel();
		root.add(saveButtonZone);
		saveButtonZone.addStyleName(DSLAMBusDesktopIStyleConstants.NEW_SCRIPT_FORM_SAVE_ZONE);
		
		saveButton = new AEGWTButton(COMMON_TEXTS.create());
		saveButtonZone.add(saveButton);
		saveButton.addStyleName(AEGWTIBoostrapConstants.BTN);
		saveButton.addStyleName(AEGWTIBoostrapConstants.BTN_PRIMARY);
		
		//SAVE
		cancelButton = new AEGWTButton(COMMON_TEXTS.cancel());
		saveButtonZone.add(cancelButton);
		cancelButton.addStyleName(AEGWTIBoostrapConstants.BTN);
		cancelButton.addStyleName(AEGWTIBoostrapConstants.BTN_LINK);
		
		saveButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				AEGWTLogicalEvent evt = new AEGWTLogicalEvent(getWindowName(), getName());
				evt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
				evt.addElementAsString(DSLAMBOIFileDataConstants.FILE_NAME		, filenameField.getText());
				evt.addElementAsString(DSLAMBOIFileDataConstants.CONTENT_TYPE	, filenameField.getSelectedId());
				getLogicalEventHandlerManager().fireEvent(evt);
			}
		});
		
		cancelButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
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
		// TODO Auto-generated method stub

	}

}
