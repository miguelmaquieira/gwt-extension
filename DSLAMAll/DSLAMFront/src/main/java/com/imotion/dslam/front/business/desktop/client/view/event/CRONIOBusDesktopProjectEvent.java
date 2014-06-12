package com.imotion.dslam.front.business.desktop.client.view.event;

import com.selene.arch.exe.gwt.mvp.event.AEGWTEvent;

public class CRONIOBusDesktopProjectEvent extends AEGWTEvent<CRONIOBusDesktopHasProjectEventHandlers> {

	private static final long serialVersionUID = 9067113351135505403L;
	
	private CRONIOBusDesktopProjectEventTypes.EVENT_TYPE eventType;
	
	public CRONIOBusDesktopProjectEvent(String sourceWindow, String sourceWidget) {
		super(sourceWindow, sourceWidget, null);
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
