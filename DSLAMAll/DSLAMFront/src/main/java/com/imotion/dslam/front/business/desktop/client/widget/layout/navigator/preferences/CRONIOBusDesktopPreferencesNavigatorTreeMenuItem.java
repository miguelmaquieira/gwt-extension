package com.imotion.dslam.front.business.desktop.client.widget.layout.navigator.preferences;

import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopPreferencesEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopPreferencesEventTypes.EVENT_TYPE;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenuItem;
;

public class CRONIOBusDesktopPreferencesNavigatorTreeMenuItem extends AEGWTBootstrapTreeMenuItem{

	public static final String NAME  = "CRONIOBusDesktopPreferencesNavigatorTreeMenuItem";

	private String mainSectionId;
	
	public CRONIOBusDesktopPreferencesNavigatorTreeMenuItem(String mainSectionId, String text, AEGWTICompositePanel parentWidget) {
		super(text, parentWidget);
		this.mainSectionId 	= mainSectionId;	
	}
	
	/**
	 * PROTECTED
	 */
	
	@Override
	protected void fireClick() {
		String mainSectionId	= this.mainSectionId;
		
		CRONIOBusDesktopPreferencesEvent openSectionEvent = new CRONIOBusDesktopPreferencesEvent(getWindowName(), getName());
		openSectionEvent.setEventType(EVENT_TYPE.OPEN_FINAL_SECTION_EVENT);
		openSectionEvent.setMainSectionId(mainSectionId);
		
		getLogicalEventHandlerManager().fireEvent(openSectionEvent);
	}
}
