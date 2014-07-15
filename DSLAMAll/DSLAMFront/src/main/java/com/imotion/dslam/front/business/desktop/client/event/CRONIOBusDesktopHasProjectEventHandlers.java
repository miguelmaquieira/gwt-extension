package com.imotion.dslam.front.business.desktop.client.event;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.selene.arch.exe.gwt.mvp.event.AEGWTHasEventHandler;

public interface CRONIOBusDesktopHasProjectEventHandlers extends AEGWTHasEventHandler {
	
	public static final Type<CRONIOBusDesktopHasProjectEventHandlers> TYPE = new Type<CRONIOBusDesktopHasProjectEventHandlers>(); 

	public void dispatchEvent(CRONIOBusDesktopProjectEvent evt);
	
	public boolean isDispatchEventType(CRONIOBusDesktopProjectEventTypes.EVENT_TYPE type);
}
