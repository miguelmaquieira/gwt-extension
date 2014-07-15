package com.imotion.dslam.front.business.desktop.client.event;

import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEventTypes.EVENT_TYPE;
import com.selene.arch.exe.gwt.mvp.event.AEGWTEvent;

public class CRONIOBusDesktopProjectEvent extends AEGWTEvent<CRONIOBusDesktopHasProjectEventHandlers> {

	private static final long serialVersionUID = 9067113351135505403L;
	
	private EVENT_TYPE eventType;
	private String		projectId;
	private String		mainSectionId;
	private String		finalSectionId;
	
	public CRONIOBusDesktopProjectEvent(String sourceWindow, String sourceWidget) {
		super(sourceWindow, sourceWidget, null);
	}
	
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getMainSectionId() {
		return mainSectionId;
	}

	public void setMainSectionId(String mainSectionId) {
		this.mainSectionId = mainSectionId;
	}

	public String getFinalSectionId() {
		return finalSectionId;
	}

	public void setFinalSectionId(String finalSectionId) {
		this.finalSectionId = finalSectionId;
	}

	@Override
	public Type<CRONIOBusDesktopHasProjectEventHandlers> getAssociatedType() {
		return CRONIOBusDesktopHasProjectEventHandlers.TYPE;
	}

	@Override
	protected void dispatch(CRONIOBusDesktopHasProjectEventHandlers handler) {
		if (handler.isDispatchEventType(eventType)) {
			handler.dispatchEvent(this);
		}
	}

	public CRONIOBusDesktopProjectEventTypes.EVENT_TYPE getEventType() {
		return eventType;
	}

	public void setEventType(CRONIOBusDesktopProjectEventTypes.EVENT_TYPE eventType) {
		this.eventType = eventType;
	}

}
