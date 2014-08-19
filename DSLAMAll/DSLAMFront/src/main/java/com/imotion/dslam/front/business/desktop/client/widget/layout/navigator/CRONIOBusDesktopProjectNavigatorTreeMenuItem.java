package com.imotion.dslam.front.business.desktop.client.widget.layout.navigator;

import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEventTypes.EVENT_TYPE;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenuItem;

public class CRONIOBusDesktopProjectNavigatorTreeMenuItem extends AEGWTBootstrapTreeMenuItem{

	public static final String NAME  = "CRONIOBusDesktopProjectNavigatorTreeMenuItem";
	
	private String projectId;
	private String mainSectionId;
	
	public CRONIOBusDesktopProjectNavigatorTreeMenuItem(String projectId,String mainSectionId, String text, AEGWTICompositePanel parentWidget) {
		super(text, parentWidget);
		this.projectId 		= projectId;
		this.mainSectionId 	= mainSectionId;	
	}
	
	/**
	 * PROTECTED
	 */
	
	@Override
	protected void fireClick() {
		String projectId		= this.projectId;
		String mainSectionId	= this.mainSectionId;
		
		CRONIOBusDesktopProjectEvent openSectionEvent = new CRONIOBusDesktopProjectEvent(getWindowName(), getName());
		openSectionEvent.setEventType(EVENT_TYPE.OPEN_FINAL_SECTION_EVENT);
		openSectionEvent.setProjectId(projectId);
		openSectionEvent.setMainSectionId(mainSectionId);
		
		getLogicalEventHandlerManager().fireEvent(openSectionEvent);
	}
}
