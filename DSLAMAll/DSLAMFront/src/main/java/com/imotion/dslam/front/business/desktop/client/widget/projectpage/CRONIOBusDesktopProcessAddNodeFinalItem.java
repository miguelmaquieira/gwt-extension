package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.shared.GWT;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusProjectBasePresenterConstants;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenuFinalItem;

public class CRONIOBusDesktopProcessAddNodeFinalItem extends AEGWTBootstrapTreeMenuFinalItem {
	
	public static final String 		NAME  	= "CRONIOBusDesktopProcessAddNodeFinalItem";
	private static DSLAMBusI18NTexts 	TEXTS 	= GWT.create(DSLAMBusI18NTexts.class);
	
	private AEGWTICompositePanel parentWidget;

	public CRONIOBusDesktopProcessAddNodeFinalItem(AEGWTICompositePanel parentWidget) {
		super(null, TEXTS.add_node(), TEXTS.add_node_label(), parentWidget, true);
		this.parentWidget = parentWidget;
		setIcon();
		setAnchorStyle();
		
	}
	
	/**
	 * AEGWTCompositePanel
	 */
	@Override
	public String getName() {
		return NAME;
	}
	
	/**
	 * AEGWTBootstrapTreeMenuFinalItem
	 */
	
	public void setIcon(){
		super.setIcon(AEGWTIBoostrapConstants.GLYPHICON_PLUS);
	}
	
	private void setAnchorStyle() {
		super.setAnchorStyle(DSLAMBusDesktopIStyleConstants.NODE_LIST_ADD_ELEMENT);
	}
	
	@Override
	protected void fireClick() {
		 CRONIOBusDesktopProcessAddNodeForm popupForm = ((CRONIOBusDesktopProcessNodeList) parentWidget).getNodePopup();
		 popupForm.center();
		 popupForm.postDisplay();
		
		CRONIOBusDesktopProjectEvent 	getMachineTypesEvt 	= new CRONIOBusDesktopProjectEvent(CRONIOBusProjectBasePresenterConstants.PROJECT_PRESENTER, getName());
		getMachineTypesEvt.setEventType(EVENT_TYPE.GET_MACHINE_TYPES);
		getLogicalEventHandlerManager().fireEvent(getMachineTypesEvt);
	}
	
}
