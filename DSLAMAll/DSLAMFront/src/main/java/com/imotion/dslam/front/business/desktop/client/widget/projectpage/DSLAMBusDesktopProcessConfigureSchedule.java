package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

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
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class DSLAMBusDesktopProcessConfigureSchedule extends AEGWTCompositePanel implements AEGWTHasLogicalEventHandlers {

	public static final String NAME = "DSLAMBusDesktopProcessConfigureSchedule";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private FlowPanel 										root;
	private FlowPanel 										scheduleListZone;
	private	 DSLAMBusDesktopScheduleList    				scheduleList;
	private DSLAMBusDesktopProcessConfigureScheduleForm		scheduleForm;
	private	 AEMFTMetadataElementComposite					schedulesData;
	private boolean 										addJS;							

	public DSLAMBusDesktopProcessConfigureSchedule() {
		schedulesData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite(); 
		root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_CONTENT_IN_BOX);
		
		addJS = false;

		CRONIOBusDesktopHeaderListActions header = new CRONIOBusDesktopHeaderListActions(null);
		root.add(header);
		
		header.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				getSchedulePopup().resetForm();
				getSchedulePopup().setEditMode(DSLAMBOIProcessDataConstants.SAVE_MODE);
				getSchedulePopup().center();
				if (addJS == false) {
					scheduleForm.postDisplay();
					addJS = true;
				}
			}
		});

		scheduleListZone = new FlowPanel();
		scheduleListZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_SCHEDULE_LIST);
		root.add(scheduleListZone);
		scheduleList = new DSLAMBusDesktopScheduleList(header.getDeleteButton());
		scheduleListZone.add(scheduleList);
	}
	
	public void reset() {
		scheduleList.reset();
		schedulesData.removeAll();
		getSchedulePopup().resetForm();
	}
	
	/**
	 * AEGWTCompositePanel
	 */

	@Override
	public void postDisplay() {
		super.postDisplay();
		getLogicalEventHandlerManager().addLogicalEventHandler(this);
	}

	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		schedulesData = data;
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
		if (DSLAMBusDesktopProcessConfigureScheduleForm.NAME.equals(evt.getSourceWidget()) && LOGICAL_TYPE.SAVE_EVENT.equals(evt.getEventType())) {
			String schedule  		= evt.getElementAsString(DSLAMBOIProcessDataConstants.SCHEDULE_VALUE);
			String removeLastEdit 	= evt.getElementAsString(DSLAMBOIProcessDataConstants.SCHEDULE_LAST_EDIT); 
			
			AEMFTMetadataElementSingle data = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getSingleElement();
			data.setValueAs(schedule);
			
			if (getSchedulePopup().getEditMode() || !schedulesData.contains(schedule)) {
				addSchedules(schedule,removeLastEdit ,data);
			} else {
				getSchedulePopup().setErrorScheduleExist();
			}
			
			AEGWTLogicalEvent saveEvt = new AEGWTLogicalEvent(getWindowName(), getName());
			saveEvt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
			saveEvt.addElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_SCHEDULE_DATA, schedulesData);
			getLogicalEventHandlerManager().fireEvent(saveEvt);
		} else if(DSLAMBusDesktopScheduleList.NAME.equals(evt.getSourceWidget()) && LOGICAL_TYPE.EDIT_EVENT.equals(evt.getEventType())) {
			
			AEMFTMetadataElement scheduleDataSingle = schedulesData.getElement(evt.getSourceWidgetId());
			AEMFTMetadataElementComposite scheduleDataComposite = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
			getElementController().setElement(DSLAMBOIProcessDataConstants.PROCESS_SCHEDULE_DATA, scheduleDataComposite, scheduleDataSingle);
			getSchedulePopup().setData(scheduleDataComposite);
			getSchedulePopup().setEditMode(DSLAMBOIProcessDataConstants.EDIT_MODE);
			getSchedulePopup().center();
		} else 	if(DSLAMBusDesktopScheduleList.NAME.equals(evt.getSourceWidget()) && LOGICAL_TYPE.DELETE_EVENT.equals(evt.getEventType())) {
			AEMFTMetadataElementSingle data = (AEMFTMetadataElementSingle) evt.getElementAsDataValue();
			List<String> rowIds = (List<String>) data.getValueAsSerializable();
		
			for (String rowId : rowIds) {
				schedulesData.removeElement(rowId);
			}	
			
			AEGWTLogicalEvent deleteEvt = new AEGWTLogicalEvent(getWindowName(), getName());
			deleteEvt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
			deleteEvt.setSourceWidget(getName());
			deleteEvt.addElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_VARIABLES_DATA, schedulesData);
			getLogicalEventHandlerManager().fireEvent(deleteEvt);
		}	
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return LOGICAL_TYPE.SAVE_EVENT.equals(type) || LOGICAL_TYPE.EDIT_EVENT.equals(type) || LOGICAL_TYPE.DELETE_EVENT.equals(type);	
	}
	
	/**
	 * PRIVATE
	 */
	
	private void addSchedules(String id, String removeLastEdit, AEMFTMetadataElement data) {
		scheduleList.clearList();
		if (removeLastEdit != null){
			schedulesData.removeElement(removeLastEdit);
		}
		schedulesData.addElement(id,data);
		scheduleList.setData(schedulesData);
		getSchedulePopup().resetForm();	
	}
	
	private DSLAMBusDesktopProcessConfigureScheduleForm getSchedulePopup() {
		if (scheduleForm == null) {
			scheduleForm = new DSLAMBusDesktopProcessConfigureScheduleForm(this);
			
		}
		return scheduleForm;
	}
}
