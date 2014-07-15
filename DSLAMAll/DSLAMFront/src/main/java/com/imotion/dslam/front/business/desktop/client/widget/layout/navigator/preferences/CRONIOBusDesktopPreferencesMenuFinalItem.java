package com.imotion.dslam.front.business.desktop.client.widget.layout.navigator.preferences;

import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopPreferencesEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopPreferencesEventTypes.EVENT_TYPE;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenuFinalItem;

public class CRONIOBusDesktopPreferencesMenuFinalItem extends AEGWTBootstrapTreeMenuFinalItem {
	
	public static final String NAME  = "CRONIOBusDesktopPreferencesMenuFinalItem";
	
	private String mainSectionId;
	private String finalSectionId;

	public CRONIOBusDesktopPreferencesMenuFinalItem(String mainSectionId, String finalSectionId, String text, AEGWTICompositePanel parentWidget) {
		super(mainSectionId, finalSectionId, text, parentWidget);
		this.mainSectionId 		= mainSectionId;
		this.finalSectionId 	= finalSectionId;
	}
	
	/**
	 * AEGWTCompositePanel
	 */
	@Override
	public String getName() {
		return NAME;
	}
	
	/**
	 * PROTECTED
	 */
	
	@Override
	protected void fireClick() {
		CRONIOBusDesktopPreferencesEvent openFinalSectionEvent = new CRONIOBusDesktopPreferencesEvent(getWindowName(), getName());
		openFinalSectionEvent.setEventType(EVENT_TYPE.OPEN_FINAL_SECTION_EVENT);
		openFinalSectionEvent.setMainSectionId(mainSectionId);
		openFinalSectionEvent.setFinalSectionId(finalSectionId);
		getLogicalEventHandlerManager().fireEvent(openFinalSectionEvent);
	}

}
