package com.imotion.dslam.front.business.desktop.client.widget.navigator;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapSplitButtonDropdown;
import com.selene.arch.exe.gwt.client.utils.AEGWTStringUtils;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public abstract class DSLAMBusDesktopNavigatorListElement extends AEGWTBootstrapSplitButtonDropdown  {

	public static final String OPEN_ID	= "open";
	public static final String RENAME_ID	= "rename";
	public static final String DELETE_ID	= "delete";
	
	private	 static DSLAMBusI18NTexts TEXTS	 = GWT.create(DSLAMBusI18NTexts.class);
	
	private AEMFTMetadataElementComposite dataElement; 
	
	public DSLAMBusDesktopNavigatorListElement() {
		super(null, "");
		addStyleName(DSLAMBusDesktopIStyleConstants.LIST_ELEMENT);
		addMenuElement(OPEN_ID,	TEXTS.open());
		addMenuElement(RENAME_ID,	TEXTS.rename());
		addSeparator();
		addMenuElement(DELETE_ID,	TEXTS.delete());
	}

	public AEMFTMetadataElementComposite getData() {
		return dataElement;
	}
	
	public String getElementName() {
		return getActionText();
	}
	
	/**
	 * AEGWTICompositePanel
	 */
	
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		dataElement = data;
	}
	
	/**
	 * PROTECTED
	 */
	
	@Override
	protected void handleClick(String menuActionId) {
		AEGWTLogicalEvent selectedEvent = new AEGWTLogicalEvent(getWindowName(), getName());
		if (!AEGWTStringUtils.isEmptyString(menuActionId)) {
			if (!DELETE_ID.equals(menuActionId) || Window.confirm(TEXTS.delete_confirmation())) {
				selectedEvent.setSourceWidgetId(menuActionId);
				selectedEvent.setSourceContainerId(getId());
			}
		} else {
			selectedEvent.setSourceWidgetId(getId());
		}
		selectedEvent.setEventType(LOGICAL_TYPE.SELECT_EVENT);
		selectedEvent.addElementAsDataValue(getData());
		getLogicalEventHandlerManager().fireEvent(selectedEvent);
	}

}
