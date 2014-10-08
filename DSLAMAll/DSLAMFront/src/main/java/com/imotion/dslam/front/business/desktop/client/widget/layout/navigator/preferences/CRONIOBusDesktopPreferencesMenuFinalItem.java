package com.imotion.dslam.front.business.desktop.client.widget.layout.navigator.preferences;

import com.imotion.dslam.front.business.client.CRONIOBusCommonConstants;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopPreferencesEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopPreferencesEventTypes.EVENT_TYPE;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenuFinalItem;

public class CRONIOBusDesktopPreferencesMenuFinalItem extends AEGWTBootstrapTreeMenuFinalItem {
	
	public static final String NAME  = "CRONIOBusDesktopPreferencesMenuFinalItem";
	
	private String finalSectionPath;

	public CRONIOBusDesktopPreferencesMenuFinalItem(String parentPath, String finalSectionId, String text, AEGWTICompositePanel parentWidget) {
		super(null, finalSectionId, text, parentWidget, true);
		this.finalSectionPath	= parentPath + CRONIOBusCommonConstants.ELEMENT_SEPARATOR + finalSectionId;
		String[] mainSectionIdSplit = parentPath.split("\\.");
		String mainSectionId 	= mainSectionIdSplit[0];
		setContainerId(mainSectionId);
		setId(finalSectionPath);
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
		openFinalSectionEvent.setMainSectionId(getContainerId());
		openFinalSectionEvent.setFinalSectionId(getId());
		openFinalSectionEvent.setFinalSectionPath(finalSectionPath);
		getLogicalEventHandlerManager().fireEvent(openFinalSectionEvent);
	}
}
