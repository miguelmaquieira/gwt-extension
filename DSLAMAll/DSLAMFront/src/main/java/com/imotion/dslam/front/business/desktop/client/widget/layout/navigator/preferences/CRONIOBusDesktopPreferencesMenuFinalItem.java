package com.imotion.dslam.front.business.desktop.client.widget.layout.navigator.preferences;

import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopProjectEvent;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopProjectEventTypes.EVENT_TYPE;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenuFinalItem;

public class CRONIOBusDesktopPreferencesMenuFinalItem extends AEGWTBootstrapTreeMenuFinalItem {
	
	public static final String NAME  = "CRONIOBusDesktopPreferencesMenuFinalItem";
	
	private String mainSectionId;

	public CRONIOBusDesktopPreferencesMenuFinalItem(String mainSectionId, String finalSectionId, String text, AEGWTICompositePanel parentWidget) {
		super(null, finalSectionId, text, parentWidget);
		this.mainSectionId = mainSectionId;
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
		//String projectId		= getContainerId();
		String finalSectionId	= getId();
		
		CRONIOBusDesktopProjectEvent openFinalSectionEvent = new CRONIOBusDesktopProjectEvent(getWindowName(), getName());
		openFinalSectionEvent.setEventType(EVENT_TYPE.OPEN_FINAL_SECTION_EVENT);
		//openFinalSectionEvent.setProjectId(projectId);
		openFinalSectionEvent.setMainSectionId(mainSectionId);
		openFinalSectionEvent.setFinalSectionId(finalSectionId);
		
		getLogicalEventHandlerManager().fireEvent(openFinalSectionEvent);
	}

}
