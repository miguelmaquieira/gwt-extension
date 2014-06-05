package com.imotion.dslam.front.business.desktop.client.widget.proccesspage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.imotion.dslam.bom.DSLAMBOIFileDataConstants;
import com.imotion.dslam.bom.DSLAMBOIProcessDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.widget.navigator.DSLAMBusDesktopNavigatorListElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.utils.AEGWTStringUtils;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class DSLAMBusDesktopNavigatorProcessListElement extends DSLAMBusDesktopNavigatorListElement {
	
	public static final String NAME = "DSLAMBusDesktopNavigatorProcessListElement";
	private DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	public DSLAMBusDesktopNavigatorProcessListElement() {
		super();
	}
	
	/**
	 * AEGWTICompositePanel
	 */
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		super.setData(data);
		Long	processId		= getElementController().getElementAsLong(DSLAMBOIProcessDataConstants.PROCESS_ID			, 	data);
		String	processName		= getElementController().getElementAsString(DSLAMBOIProcessDataConstants.PROCESS_NAME		, 	data);
		
		AEMFTMetadataElementComposite scriptData = getElementController().getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_SCRIPT, data);
		
		String scriptName		= getElementController().getElementAsString(DSLAMBOIFileDataConstants.FILE_NAME				, 	scriptData);
		
		setId(String.valueOf(processId));
		String text =	"<span class='" + DSLAMBusDesktopIStyleConstants.PROCESSES_VIEW_PROCESS_NAME + "'>" + processName + "</span>" +
						"<span> - </span>" +
						"<span class='" + DSLAMBusDesktopIStyleConstants.PROCESSES_VIEW_SCRIPT_NAME + "'>" + scriptName + "</span>";
		setActionText(text);
		setActionTooltip(processName);
		

	}
	
	@Override
	protected void handleClick(String menuActionId) {
		AEGWTLogicalEvent selectedEvent = new AEGWTLogicalEvent(getWindowName(), getName());
		if (!AEGWTStringUtils.isEmptyString(menuActionId)) {
			if (!DELETE_ID.equals(menuActionId) || Window.confirm(TEXTS.delete_confirmation())) {
				selectedEvent.setSourceWidgetId(menuActionId);
				selectedEvent.setSourceContainerId(getId());
			}
		} else {
			selectedEvent.setSourceWidgetId(getId());
		}
		selectedEvent.setEventType(LOGICAL_TYPE.SELECT_EVENT);
		selectedEvent.addElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_DATA	,getData());
		getLogicalEventHandlerManager().fireEvent(selectedEvent);
	}

}
