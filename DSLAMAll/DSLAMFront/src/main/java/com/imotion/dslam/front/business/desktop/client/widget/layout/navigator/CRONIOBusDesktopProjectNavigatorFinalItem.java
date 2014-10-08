package com.imotion.dslam.front.business.desktop.client.widget.layout.navigator;

import com.imotion.dslam.bom.CRONIOBOIProjectDataConstants;
import com.imotion.dslam.front.business.client.CRONIOBusCommonConstants;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEventTypes.EVENT_TYPE;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenuFinalItem;

public class CRONIOBusDesktopProjectNavigatorFinalItem extends AEGWTBootstrapTreeMenuFinalItem {
	
	public static final String NAME  = "CRONIOBusDesktopProjectNavigatorFinalItem";
	
	private String mainSectionId;
	private String text;

	public CRONIOBusDesktopProjectNavigatorFinalItem(String proyectId, String mainSectionId, String finalSectionId, String text, AEGWTICompositePanel parentWidget) {
		super(proyectId, finalSectionId, text, parentWidget, true);
		this.mainSectionId = mainSectionId;
		this.text = text;
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
		String projectId		= getContainerId();
		String finalSectionId	= getId();
		
		if (CRONIOBOIProjectDataConstants.PROJECT_EXECUTION_LOG.equals(finalSectionId)) {
			finalSectionId = text;
		} else if (CRONIOBOIProjectDataConstants.PROJECT_PROCESS_NODE_LISTS.equals(finalSectionId)) {
			finalSectionId = finalSectionId + CRONIOBusCommonConstants.ELEMENT_SEPARATOR + text;
		}
		
		CRONIOBusDesktopProjectEvent openFinalSectionEvent = new CRONIOBusDesktopProjectEvent(getWindowName(), getName());
		openFinalSectionEvent.setEventType(EVENT_TYPE.OPEN_FINAL_SECTION_EVENT);
		openFinalSectionEvent.setProjectId(projectId);
		openFinalSectionEvent.setMainSectionId(mainSectionId);
		openFinalSectionEvent.setFinalSectionId(finalSectionId);
		
		getLogicalEventHandlerManager().fireEvent(openFinalSectionEvent);
	}
}
