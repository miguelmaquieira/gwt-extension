package com.imotion.dslam.front.business.desktop.client.widget.layout.navigator;

import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.CRONIOBusDesktopProcessAddNodeListForm;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenuItem;

public class CRONIOBusDesktopProjectNavigatorTreeMenuItem extends AEGWTBootstrapTreeMenuItem{

	public static final String NAME  = "CRONIOBusDesktopProjectNavigatorTreeMenuItem";
	
	private String projectId;
	private String mainSectionId;
	
	private CRONIOBusDesktopProcessAddNodeListForm addNodeListPopupForm;
	
	public CRONIOBusDesktopProjectNavigatorTreeMenuItem(String projectId,String mainSectionId, String text, AEGWTICompositePanel parentWidget) {
		super(text, parentWidget);
		this.projectId 		= projectId;
		this.mainSectionId 	= mainSectionId;	
	}
	
	public CRONIOBusDesktopProcessAddNodeListForm getNodeListPopup() {
		if (addNodeListPopupForm == null) {
			addNodeListPopupForm = new CRONIOBusDesktopProcessAddNodeListForm(this);
		}
		return addNodeListPopupForm;
	}
	
	public void setErrorNodeExist() {
		addNodeListPopupForm.setErrorNodeListExist();
	}
	
	public void resetForm() {
		addNodeListPopupForm.resetForm();
	}

	public void showDuplicateNodeListNameError(String nodeListName) {
		addNodeListPopupForm.showDuplicateNodeListNameError(nodeListName);
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
	
	@Override
	protected void fireButtonClick() {
		getNodeListPopup().center();
	}
}
