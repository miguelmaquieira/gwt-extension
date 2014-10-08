package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOIProcessDataConstants;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.business.ui.utils.AEGWTBusinessUtils;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapDateTimePickerTextBox;
import com.selene.arch.exe.gwt.client.ui.widget.button.AEGWTButton;
import com.selene.arch.exe.gwt.client.ui.widget.popup.AEGWTPopup;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopProcessConfigureScheduleForm extends AEGWTPopup {

	public static final String NAME			= "CRONIOBusDesktopProcessConfigureScheduleForm";
	public static final String DATE_FORMAT_PICKER	= "d/m/Y H:i";
	public static final String DATE_FORMAT		= "d/MM/yyyy H:mm";
	private static CRONIOBusI18NTexts TEXTS = GWT.create(CRONIOBusI18NTexts.class);

	private AEGWTBootstrapDateTimePickerTextBox  	scheduleTextBox; 
	private AEGWTButton 							saveButton;
	private AEGWTButton								cancelButton;
	private boolean								editMode;
	private String									originalSchedule;

	public CRONIOBusDesktopProcessConfigureScheduleForm(AEGWTICompositePanel parent) {
		super(false, false, true, parent);
		FlowPanel root = new FlowPanel();
		setWidget(root);
		root.addStyleName(CRONIOBusDesktopIStyleConstants.POPUP_SCHEDULE_FORM_CONTAINER);
		
		scheduleTextBox 			= new AEGWTBootstrapDateTimePickerTextBox(null);
		root.add(scheduleTextBox);
		
		FlowPanel saveButtonZone = new FlowPanel();
		root.add(saveButtonZone);
		saveButtonZone.addStyleName(CRONIOBusDesktopIStyleConstants.POPUP_SCHEDULE_FORM_SAVE_ZONE);
		
		saveButton = new AEGWTButton(TEXTS.save());
		saveButtonZone.add(saveButton);
		saveButton.setStyleName(AEGWTIBoostrapConstants.BTN);
		saveButton.addStyleName(AEGWTIBoostrapConstants.BTN_PRIMARY);
		
		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				boolean errors = false;
				
				resetErrors();
				
				if (AEMFTCommonUtilsBase.isEmptyString(scheduleTextBox.getDateText())) {
					errors = true;
					scheduleTextBox.setEmptyError(TEXTS.empty_textbox());
				}
				
				if (errors == false) {
					AEGWTLogicalEvent evt = new AEGWTLogicalEvent(getWindowName(), getName());
					evt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
					evt.setSourceWidgetId(getId());
					evt.addElementAsString(CRONIOBOIProcessDataConstants.SCHEDULE_VALUE			, scheduleTextBox.getDateText());
					if (editMode) {
						evt.addElementAsString(CRONIOBOIProcessDataConstants.SCHEDULE_ORIGINAL_VALUE	, originalSchedule);
					}
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
	public void setData(AEMFTMetadataElementComposite scheduleData) {
		if (scheduleData != null) {
			Date schedule			= (Date) getElementController().getElementAsSerializable(CRONIOBOIProcessDataConstants.PROCESS_SCHEDULE_DATA			, scheduleData);
			originalSchedule		= AEGWTBusinessUtils.getFormattedTimeMessage(schedule, DATE_FORMAT);
			scheduleTextBox.setDateText(originalSchedule);
		}
	}

	
	public AEMFTMetadataElementComposite getData() {
		AEMFTMetadataElementComposite formData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		
		getElementController().setElement(CRONIOBOIProcessDataConstants.SCHEDULE_VALUE		, formData	, scheduleTextBox.getDateText());
		return formData;
	}

	protected void resetForm() {
		scheduleTextBox.setDateText("");
		resetErrors();
		hide();
	}
	
	protected void resetErrors() {
		scheduleTextBox.resetErrorLabel();
	}
	
	protected void setErrorScheduleExist() {
		scheduleTextBox.setErrorLabel(TEXTS.error_schedule_exist());
		scheduleTextBox.setErrorLabelVisible(true);
	}
	
	protected void setEditMode(String mode) {
		if(CRONIOBOIProcessDataConstants.EDIT_MODE.equals(mode)) {
			editMode = true;
		} else {
			editMode = false;
		}
	}
	
	protected boolean getEditMode() {
		return editMode;
	}
	
	@Override
	public void postDisplay() {
		scheduleTextBox.addJS(getId(), DATE_FORMAT_PICKER);
	}
}
