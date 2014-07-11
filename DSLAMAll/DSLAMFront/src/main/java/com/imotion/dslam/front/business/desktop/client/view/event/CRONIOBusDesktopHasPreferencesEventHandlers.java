package com.imotion.dslam.front.business.desktop.client.view.event;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.selene.arch.exe.gwt.mvp.event.AEGWTHasEventHandler;

public interface CRONIOBusDesktopHasPreferencesEventHandlers extends AEGWTHasEventHandler {
	
	public static final Type<CRONIOBusDesktopHasPreferencesEventHandlers> TYPE = new Type<CRONIOBusDesktopHasPreferencesEventHandlers>(); 

	public void dispatchEvent(CRONIOBusDesktopPreferencesEvent evt);
	
	public boolean isDispatchEventType(CRONIOBusDesktopPreferencesEventTypes.EVENT_TYPE type);
}
