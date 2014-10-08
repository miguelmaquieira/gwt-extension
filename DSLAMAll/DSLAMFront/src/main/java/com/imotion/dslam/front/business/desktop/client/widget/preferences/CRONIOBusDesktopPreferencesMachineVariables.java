package com.imotion.dslam.front.business.desktop.client.widget.preferences;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOIVariablesDataConstants;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopPreferencesEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopPreferencesEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.CRONIOBusDesktopHeaderListActions;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopPreferencesMachineVariables extends AEGWTCompositePanel implements AEGWTHasLogicalEventHandlers {

	public static final String NAME = "CRONIOBusDesktopPreferencesMachineVariables";

	private FlowPanel 											root;
	private FlowPanel											variableListZone;
	private	 CRONIOBusDesktopPreferencesMachineVariablesList   	variableList;
	private CRONIOBusDesktopPreferencesMachineVariablesForm		variablesForm;
	private	 AEMFTMetadataElementComposite						variablesData;

	public CRONIOBusDesktopPreferencesMachineVariables() {
		root = new FlowPanel();
		initWidget(root);
		root.addStyleName(CRONIOBusDesktopIStyleConstants.PREFERENCES_LAYOUT_CONTENT_IN_BOX);

		CRONIOBusDesktopHeaderListActions header = new CRONIOBusDesktopHeaderListActions(null);
		root.add(header);
		
		header.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				variablesForm.resetForm();
				variablesForm.setEditMode(CRONIOBOIVariablesDataConstants.SAVE_MODE);
				variablesForm.center();	
			}
		});
		
		variableListZone = new FlowPanel();
		variableListZone.addStyleName(CRONIOBusDesktopIStyleConstants.PREFERENCES_MACHINE_VARIABLES_LIST);
		root.add(variableListZone);
		variableList = new CRONIOBusDesktopPreferencesMachineVariablesList(header.getDeleteButton());
		variableListZone.add(variableList);
	}

	public void reset() {
		variableList.reset();
		variablesData.removeAll();
		variablesForm.resetForm(CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE_CONNECTION, CRONIOBOIVariablesDataConstants.VARIABLE_TYPE_TEXT);
	}
	
	/**
	 * AEGWTICompositePanel
	 */
	
	@Override
	public void postDisplay() {
		super.postDisplay();
		variablesForm = new CRONIOBusDesktopPreferencesMachineVariablesForm(this);
		getLogicalEventHandlerManager().addLogicalEventHandler(this);
	}
	
	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		variableList.clearList();
		if (data != null) {
			variablesData = data;
			variableList.setData(data);
		} else {
			variablesData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		}
	}
	
	public AEMFTMetadataElementComposite getData() {
		 return variablesData;
	}
	
	/**
	 * AEGWTHasLogicalEventHandlers
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public void dispatchEvent(AEGWTLogicalEvent evt) {
		if (CRONIOBusDesktopPreferencesMachineVariablesForm.NAME.equals(evt.getSourceWidget()) && LOGICAL_TYPE.SAVE_EVENT.equals(evt.getEventType())) {
			String 	name		=  evt.getElementAsString(CRONIOBOIVariablesDataConstants.VARIABLE_NAME);
			String 	value 		=  evt.getElementAsString(CRONIOBOIVariablesDataConstants.VARIABLE_VALUE);
			String 	scope 		=  evt.getElementAsString(CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE);
			String 	type 		=  evt.getElementAsString(CRONIOBOIVariablesDataConstants.VARIABLE_TYPE);
			int 	scopeAsInt 	= AEMFTCommonUtilsBase.getIntegerFromString(scope);
			int 	typeAsInt 	= AEMFTCommonUtilsBase.getIntegerFromString(type);			
			
			AEMFTMetadataElementComposite variableData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
			getElementController().setElement(CRONIOBOIVariablesDataConstants.VARIABLE_NAME		, variableData	, name);
			getElementController().setElement(CRONIOBOIVariablesDataConstants.VARIABLE_VALUE		, variableData	, value);
			getElementController().setElement(CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE		, variableData	, scopeAsInt);
			getElementController().setElement(CRONIOBOIVariablesDataConstants.VARIABLE_TYPE		, variableData	, typeAsInt);
			if (variablesForm.getEditMode()) {
				addVariables(name, variableData);
			} else if (!variablesData.contains(name)) {
				addVariables(name, variableData);
			} else {
				variablesForm.setErrorVariableExist();
			}
			CRONIOBusDesktopPreferencesEvent saveEvt = new CRONIOBusDesktopPreferencesEvent(getWindowName(), getName());
			saveEvt.setEventType(EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT);
			saveEvt.setSourceWidget(getName());
			saveEvt.addElementAsComposite(CRONIOBOIMachineProperties.MACHINE_VARIABLES, variablesData);
			getLogicalEventHandlerManager().fireEvent(saveEvt);
			
		} else if(CRONIOBusDesktopPreferencesMachineVariablesList.NAME.equals(evt.getSourceWidget()) && LOGICAL_TYPE.EDIT_EVENT.equals(evt.getEventType())) {
			AEMFTMetadataElement variableData = variablesData.getElement(evt.getSourceWidgetId());
			variablesForm.setData((AEMFTMetadataElementComposite) variableData);
			variablesForm.setEditMode(CRONIOBOIVariablesDataConstants.EDIT_MODE);
			variablesForm.center();
			
		} else if(CRONIOBusDesktopPreferencesMachineVariablesList.NAME.equals(evt.getSourceWidget()) && LOGICAL_TYPE.DELETE_EVENT.equals(evt.getEventType())) {
			AEMFTMetadataElementSingle data = (AEMFTMetadataElementSingle) evt.getElementAsDataValue();
			List<String> rowIds = (List<String>) data.getValueAsSerializable();
		
			for (String rowId : rowIds) {
				variablesData.removeElement(rowId);
			}
			
			AEGWTLogicalEvent deleteEvt = new AEGWTLogicalEvent(getWindowName(), getName());
			deleteEvt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
			deleteEvt.setSourceWidget(getName());
			deleteEvt.addElementAsComposite(CRONIOBOIMachineProperties.MACHINE_VARIABLES, variablesData);
			getLogicalEventHandlerManager().fireEvent(deleteEvt);
		}	
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return LOGICAL_TYPE.SAVE_EVENT.equals(type) 
				|| 
				LOGICAL_TYPE.EDIT_EVENT.equals(type) 
				|| 
				LOGICAL_TYPE.DELETE_EVENT.equals(type);	
	}
	
	/**
	 * PRIVATE
	 */
	
	private void addVariables(String id, AEMFTMetadataElementComposite data) {
		variableList.clearList();
		variablesData.addElement(id,data);
		variableList.setData(variablesData);
		variablesForm.resetForm(CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE_CONNECTION, CRONIOBOIVariablesDataConstants.VARIABLE_TYPE_TEXT);	
	}
}
