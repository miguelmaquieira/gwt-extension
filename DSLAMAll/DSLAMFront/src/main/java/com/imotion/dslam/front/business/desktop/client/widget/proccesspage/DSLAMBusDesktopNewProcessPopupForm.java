package com.imotion.dslam.front.business.desktop.client.widget.proccesspage;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIFileDataConstants;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIProcessDataConstants;
import com.imotion.dslam.business.service.DSLAMBUIProcessBusinessServiceConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBoostrapFormFieldSuggestBox;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapFormFieldTextBox;
import com.selene.arch.exe.gwt.client.ui.widget.button.AEGWTButton;
import com.selene.arch.exe.gwt.client.ui.widget.popup.AEGWTPopup;
import com.selene.arch.exe.gwt.client.utils.AEGWTStringUtils;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class DSLAMBusDesktopNewProcessPopupForm extends AEGWTPopup {

	public static final String NAME = "DSLAMBusDesktopNewProcessPopupForm";
	
	private DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	public static final int MODE_NEW_PROCESS				= 1;
	public static final int MODE_RENAME_PROCESS			= 2;
	public static final int MODE_CHANGE_SCRIPT_PROCESS	= 3;

	private static final DSLAMBusI18NTexts		COMMON_TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private AEGWTBootstrapFormFieldTextBox		processNameField;
	private AEGWTBoostrapFormFieldSuggestBox   scriptNameField;
	private AEGWTButton							saveButton;
	private AEGWTButton							cancelButton;

	private int mode = MODE_NEW_PROCESS;

	public DSLAMBusDesktopNewProcessPopupForm(AEGWTICompositePanel parent) {
		super(true, parent);
		FlowPanel root = new FlowPanel();
		setWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.NEW_PROCESS_FORM);

		
		FlowPanel inputZone = new FlowPanel();
		root.add(inputZone);
		inputZone.addStyleName(DSLAMBusDesktopIStyleConstants.NEW_PROCESS_FORM_INPUT_ZONE);

		processNameField = new AEGWTBootstrapFormFieldTextBox(null,TEXTS.process_name());
		inputZone.add(processNameField);
	
		scriptNameField = new AEGWTBoostrapFormFieldSuggestBox(null,TEXTS.script_name());
		inputZone.add(scriptNameField);
		

		//SAVE
		FlowPanel saveButtonZone = new FlowPanel();
		root.add(saveButtonZone);
		saveButtonZone.addStyleName(DSLAMBusDesktopIStyleConstants.NEW_PROCESS_FORM_SAVE_ZONE);

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
				boolean errors = false;
				processNameField.setErrorLabelVisible(false);
				scriptNameField.setErrorLabelVisible(false);
				if (AEGWTStringUtils.isEmptyString(processNameField.getText())) {
					processNameField.setErrorLabelTextAndShow(TEXTS.empty_textbox());
					errors = true;
				}
				
				if (AEGWTStringUtils.isEmptyString(scriptNameField.getText())) {
					scriptNameField.setErrorLabelTextAndShow(TEXTS.empty_textbox());
					errors = true;
				} else {
					if(!scriptNameField.containsSuggestion(scriptNameField.getText())){
						scriptNameField.setErrorLabelTextAndShow(TEXTS.error_script_no_exist());
						errors = true;	
					}	
				}
				if (errors == false) {
					AEGWTLogicalEvent evt = new AEGWTLogicalEvent(getWindowName(), getName());
					if (mode == MODE_NEW_PROCESS) {
						evt.setEventType(LOGICAL_TYPE.NEW_EVENT);
					} else if (mode == MODE_RENAME_PROCESS) {
						evt.setEventType(LOGICAL_TYPE.CHANGE_EVENT);
					}
					
					scriptNameField.getSuggestItemKey();
					evt.setSourceWidgetId(getId());
					evt.addElementAsString(DSLAMBOIProcessDataConstants.PROCESS_NAME		, processNameField.getText());
					evt.addElementAsString(DSLAMBOIProcessDataConstants.PROCESS_SCRIPT		, scriptNameField.getSuggestItemKey());
					getLogicalEventHandlerManager().fireEvent(evt);
				}
			}
		});

		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});

		processNameField.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				processNameField.setErrorLabelVisible(false);
			}
		});
	}

	public void setError(String error) {
		processNameField.setErrorLabelTextAndShow(error);
	}

	public void setMode(int mode) {
		this.mode = mode;
	}
	
	public String getNameText() {
		return processNameField.getText();
	}

	@Override
	public void center() {
		processNameField.setText("");
		processNameField.setErrorLabelVisible(false);
		processNameField.setEnabled(true);
		scriptNameField.setText("");
		scriptNameField.setErrorLabelVisible(false);
		scriptNameField.setEnabled(true);
		if (mode == MODE_NEW_PROCESS) {
			saveButton.setText(COMMON_TEXTS.create());
		} else if (mode == MODE_RENAME_PROCESS) {
			saveButton.setText(COMMON_TEXTS.rename());
		} else if (mode == MODE_CHANGE_SCRIPT_PROCESS) {
			saveButton.setText(COMMON_TEXTS.change_file());
		} 
		super.center();
		processNameField.setFocus(true);
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
			Long 	processId 	= getElementController().getElementAsLong(DSLAMBOIProcess.PROCESS_ID		, data);
			String 	processName = getElementController().getElementAsString(DSLAMBOIProcess.PROCESS_NAME	, data);
			setId(String.valueOf(processId));
			processNameField.setText(processName);
			
			AEMFTMetadataElementComposite processFileList = getElementController().getElementAsComposite(DSLAMBUIProcessBusinessServiceConstants.PROCESS_FILE_DATA_LIST, data);
			if (processFileList == null) {
				AEMFTMetadataElementComposite processFileRename = getElementController().getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_SCRIPT, data);
				String script = getElementController().getElementAsString(DSLAMBOIFileDataConstants.FILE_NAME, processFileRename);
				scriptNameField.setText(script);
				scriptNameField.setEnabled(false);
			} else {
				List<AEMFTMetadataElement> processFileDataList = processFileList.getSortedElementList();
				for (AEMFTMetadataElement file : processFileDataList) {
					String 	fileName 	= getElementController().getElementAsString(DSLAMBOIFile.FILE_NAME		, file);
					Long 	fileId 		= getElementController().getElementAsLong(DSLAMBOIFile.FILE_ID			, file);
					String 	fileIdStr 	= String.valueOf(fileId);
					scriptNameField.addSuggestItem(fileIdStr,fileName);
				}
			}
		}
	}

}
