package com.imotion.dslam.front.business.desktop.client.widget.editor;

import com.google.gwt.core.shared.GWT;
import com.imotion.dslam.bom.DSLAMBOIFileDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapSplitButtonDropdown;
import com.selene.arch.exe.gwt.client.utils.AEGWTStringUtils;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class DSLAMBusDesktopEditorFileListElement extends AEGWTBootstrapSplitButtonDropdown {

	public static final String OPEN_FILE_ID	= "open";
	public static final String RENAME_FILE_ID	= "rename";
	public static final String DELETE_FILE_ID	= "delete";
	
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private AEMFTMetadataElementComposite filedata; 
	
	public DSLAMBusDesktopEditorFileListElement() {
		super(null, "");
		addStyleName(DSLAMBusDesktopIStyleConstants.FILE_LIST_ELEMENT);
		addMenuElement(OPEN_FILE_ID,	TEXTS.open());
		addMenuElement(RENAME_FILE_ID,	TEXTS.rename());
		addSeparator();
		addMenuElement(DELETE_FILE_ID,	TEXTS.delete());
	}

	public AEMFTMetadataElementComposite getData() {
		return filedata;
	}
	
	/**
	 * AEGWTICompositePanel
	 */
	
	public void setData(AEMFTMetadataElementComposite data) {
		filedata = data;
		Long	fileId		= getElementController().getElementAsLong(DSLAMBOIFileDataConstants.FILE_ID	, 	filedata);
		String	filename	= getElementController().getElementAsString(DSLAMBOIFileDataConstants.FILE_NAME	, 	filedata);
		
		setId(String.valueOf(fileId));
		setActionText(filename);
	}
	
	/**
	 * PROTECTED
	 */
	
	@Override
	protected void handleClick(String menuActionId) {
		AEGWTLogicalEvent selectedEvent = new AEGWTLogicalEvent(getWindowName(), getName());
		if (!AEGWTStringUtils.isEmptyString(menuActionId)) {
			selectedEvent.setSourceWidgetId(menuActionId);
			selectedEvent.setSourceContainerId(getId());
		} else {
			selectedEvent.setSourceWidgetId(getId());
		}
		selectedEvent.setEventType(LOGICAL_TYPE.SELECT_EVENT);
		selectedEvent.addElementAsDataValue(getData());
		getLogicalEventHandlerManager().fireEvent(selectedEvent);
	}

}
