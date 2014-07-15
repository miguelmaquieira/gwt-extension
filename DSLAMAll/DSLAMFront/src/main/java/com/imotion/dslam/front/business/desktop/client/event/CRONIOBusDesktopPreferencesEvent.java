package com.imotion.dslam.front.business.desktop.client.event;

import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopPreferencesEventTypes.EVENT_TYPE;
import com.selene.arch.exe.gwt.mvp.event.AEGWTEvent;

public class CRONIOBusDesktopPreferencesEvent extends AEGWTEvent<CRONIOBusDesktopHasPreferencesEventHandlers> {

	private static final long serialVersionUID = 6927941318226383276L;
	
	private EVENT_TYPE eventType;
	private String		preferencesId;
	private String		connectionName;
	private String		mainSectionId;
	private String		finalSectionId;
	
	public CRONIOBusDesktopPreferencesEvent(String sourceWindow, String sourceWidget) {
		super(sourceWindow, sourceWidget, null);
	}
	
	public String getPreferencesId() {
		return preferencesId;
	}

	public void setPreferencesId(String preferencesId) {
		this.preferencesId = preferencesId;
	}
	
	public String getConnectionName() {
		return connectionName;
	}

	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
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
	public Type<CRONIOBusDesktopHasPreferencesEventHandlers> getAssociatedType() {
		return CRONIOBusDesktopHasPreferencesEventHandlers.TYPE;
	}

	@Override
	protected void dispatch(CRONIOBusDesktopHasPreferencesEventHandlers handler) {
		if (handler.isDispatchEventType(eventType)) {
			handler.dispatchEvent(this);
		}
	}

	public CRONIOBusDesktopPreferencesEventTypes.EVENT_TYPE getEventType() {
		return eventType;
	}

	public void setEventType(CRONIOBusDesktopPreferencesEventTypes.EVENT_TYPE eventType) {
		this.eventType = eventType;
	}

}
