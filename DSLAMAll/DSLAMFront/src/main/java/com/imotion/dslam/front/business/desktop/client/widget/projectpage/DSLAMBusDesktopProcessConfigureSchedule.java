package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIProcessDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapGlyphiconButton;
import com.selene.arch.exe.gwt.client.ui.widget.button.AEGWTButton;
import com.selene.arch.exe.gwt.client.ui.widget.label.AEGWTLabel;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class DSLAMBusDesktopProcessConfigureSchedule extends AEGWTCompositePanel implements AEGWTHasLogicalEventHandlers {

	public static final String NAME = "DSLAMBusDesktopProcessConfigureSchedule";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private FlowPanel 										root;
	private FlowPanel										headerZone;
	private FlowPanel 										scheduleListZone;
	private AEGWTBootstrapGlyphiconButton					addDateTimeButton;
	private AEGWTBootstrapGlyphiconButton 					deleteDateTimeButton;
	private	 DSLAMBusDesktopScheduleList    				scheduleList;
	private DSLAMBusDesktopProcessConfigureScheduleForm		scheduleForm;
	private	 AEMFTMetadataElementComposite					schedulesData;
	private int 							numberAddDateTimePicker;							

	public DSLAMBusDesktopProcessConfigureSchedule() {
		root = new FlowPanel();
		initWidget(root);

		//Header
		headerZone 		= new FlowPanel();
		root.add(headerZone);
		headerZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_SCHEDULE_HEADER);

		AEGWTLabel headerLabel 		= new AEGWTLabel(TEXTS.schedule());
		headerLabel.addStyleName(AEGWTIBoostrapConstants.COL_XS_8);
		headerZone.add(headerLabel);

		deleteDateTimeButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_REMOVE, null, TEXTS.delete());
		deleteDateTimeButton.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
		deleteDateTimeButton.setVisible(false);
		headerZone.add(deleteDateTimeButton);

		addDateTimeButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_PLUS, null, TEXTS.add());
		addDateTimeButton.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
		headerZone.add(addDateTimeButton);

		addDateTimeButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				scheduleForm.resetForm();
				scheduleForm.setEditMode(DSLAMBOIProcessDataConstants.SAVE_MODE);
				scheduleForm.center();
			}
		});

		scheduleListZone = new FlowPanel();
		scheduleListZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_SCHEDULE_LIST);
		root.add(scheduleListZone);
		scheduleList = new DSLAMBusDesktopScheduleList( (AEGWTButton) deleteDateTimeButton);
		scheduleListZone.add(scheduleList);
	}
	
	public void reset() {
		scheduleList.reset();
		schedulesData.removeAll();
		scheduleForm.resetForm();
	}
	
	/**
	 * AEGWTCompositePanel
	 */

	@Override
	public void postDisplay() {
		super.postDisplay();
		scheduleForm = new DSLAMBusDesktopProcessConfigureScheduleForm(this);
		getLogicalEventHandlerManager().addLogicalEventHandler(this);
	}

	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		schedulesData.removeAll();
		schedulesData = getElementController().getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_SCHEDULE_LIST, data);
		scheduleList.clearList();
		scheduleList.setData(schedulesData);	
	}
	
	public AEMFTMetadataElementComposite getData() {
		return schedulesData;
	}

	/**
	 * AEGWTHasLogicalEventHandlers
	 */
	
	@Override
	public void dispatchEvent(AEGWTLogicalEvent evt) {
		if (LOGICAL_TYPE.SAVE_EVENT.equals(evt.getEventType())) {
			String schedule  =  evt.getElementAsString(DSLAMBOIProcessDataConstants.SCHEDULE_VALUE);
			
			AEMFTMetadataElementComposite data = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();

			getElementController().setElement(DSLAMBOIProcessDataConstants.SCHEDULE_VALUE		, data	, schedule);
			
			if (scheduleForm.getEditMode()) {
				addSchedules(schedule,data);
			} else if (!schedulesData.contains(schedule)) {
				addSchedules(schedule,data);
			} else {
				scheduleForm.setErrorScheduleExist();
			}
			
			AEGWTLogicalEvent saveEvt = new AEGWTLogicalEvent(getWindowName(), getName());
			saveEvt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
			saveEvt.addElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_SCHEDULE_DATA, schedulesData);
			getLogicalEventHandlerManager().fireEvent(evt);
		} else if(LOGICAL_TYPE.EDIT_EVENT.equals(evt.getEventType())) {
			
			AEMFTMetadataElement scheduleData = schedulesData.getElement(evt.getSourceWidgetId());
			scheduleForm.setData((AEMFTMetadataElementComposite) schedulesData);
			scheduleForm.setEditMode(DSLAMBOIProcessDataConstants.EDIT_MODE);
			scheduleForm.center();
		} else 	if(DSLAMBusDesktopScheduleList.NAME.equals(evt.getSourceWidget()) && LOGICAL_TYPE.DELETE_EVENT.equals(evt.getEventType())) {
			AEMFTMetadataElementSingle data = (AEMFTMetadataElementSingle) evt.getElementAsDataValue();
			List<String> rowIds = (List<String>) data.getValueAsSerializable();
		
			for (String rowId : rowIds) {
				schedulesData.removeElement(rowId);
			}	
		}	
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return LOGICAL_TYPE.SAVE_EVENT.equals(type) || LOGICAL_TYPE.EDIT_EVENT.equals(type) || LOGICAL_TYPE.DELETE_EVENT.equals(type);	
	}
	
	/**
	 * PRIVATE
	 */
	
	private void addSchedules(String id, AEMFTMetadataElementComposite data) {
		scheduleList.clearList();
		schedulesData.addElement(id,data);
		scheduleList.setData(schedulesData);
		scheduleForm.resetForm();	
	}

	private DSLAMBusDesktopProcessConfigureScheduleLine addDateTimeBox(int numberAddDateTimePicker) {

		DSLAMBusDesktopProcessConfigureScheduleLine line = new DSLAMBusDesktopProcessConfigureScheduleLine(null);
		scheduleListZone.add(line);
		line.setId(String.valueOf(numberAddDateTimePicker));
		line.postDisplay();
		return line;
	}

	private List<String> dateTimePickersEmpty() {
		
		List<String> emptyList = null;
		boolean errors = false;
		
		int count = scheduleListZone.getWidgetCount();
		if (count > 0) {
			for (int i = 0; i < count; i++) {
				DSLAMBusDesktopProcessConfigureScheduleLine lineDateWidget = (DSLAMBusDesktopProcessConfigureScheduleLine) scheduleListZone.getWidget(i);
				String lineDate = lineDateWidget.getDateText();
				if (AEMFTCommonUtilsBase.isEmptyString(lineDate)) {
					if (errors == false) {
						emptyList = new ArrayList<>();
						errors = true;
					}
					emptyList.add(String.valueOf(i));	
				} else {
					lineDateWidget.resetErrorLabel();
				}
			}
		}
		if (errors == true) {
			return emptyList;
		} else {
			return null;
		}
	}
}
