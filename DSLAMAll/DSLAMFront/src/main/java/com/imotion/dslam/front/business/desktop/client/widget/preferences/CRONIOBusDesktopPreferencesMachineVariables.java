package com.imotion.dslam.front.business.desktop.client.widget.preferences;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.DSLAMBOIVariablesDataConstants;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.CRONIOBusDesktopHeaderListActions;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.DSLAMBusDesktopProcessConfigureVariablesForm;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.DSLAMBusDesktopVariablesList;
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

	public static final String NAME = "DSLAMBusDesktopProcessConfigureVariables";

	private FlowPanel 											root;
	private FlowPanel											variableListZone;
	private	 CRONIOBusDesktopPreferencesMachineVariablesList   	variableList;
	private CRONIOBusDesktopPreferencesMachineVariablesForm		variablesForm;
	private	 AEMFTMetadataElementComposite						variablesData;

	public CRONIOBusDesktopPreferencesMachineVariables() {
		root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PREFERENCES_LAYOUT_CONTENT_IN_BOX);

		CRONIOBusDesktopHeaderListActions header = new CRONIOBusDesktopHeaderListActions(null);
		root.add(header);
		
		header.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				variablesForm.resetForm();
				variablesForm.setEditMode(DSLAMBOIVariablesDataConstants.SAVE_MODE);
				variablesForm.center();	
			}
		});
		
		variableListZone = new FlowPanel();
		variableListZone.addStyleName(DSLAMBusDesktopIStyleConstants.PREFERENCES_MACHINE_VARIABLES_LIST);
		root.add(variableListZone);
		variableList = new CRONIOBusDesktopPreferencesMachineVariablesList(header.getDeleteButton());
		variableListZone.add(variableList);
	}

	public void reset() {
		variableList.reset();
		variablesData.removeAll();
		variablesForm.resetForm();
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
		if (DSLAMBusDesktopProcessConfigureVariablesForm.NAME.equals(evt.getSourceWidget()) && LOGICAL_TYPE.SAVE_EVENT.equals(evt.getEventType())) {
			String name		=  evt.getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_NAME);
			String value 	=  evt.getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE);
			String type 	=  evt.getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE);
			
			int typeAsInt = AEMFTCommonUtilsBase.getIntegerFromString(type);
			
			AEMFTMetadataElementComposite data = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
			getElementController().setElement(DSLAMBOIVariablesDataConstants.VARIABLE_NAME		, data	, name);
			getElementController().setElement(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE		, data	, value);
			getElementController().setElement(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE		, data	, typeAsInt);
			if (variablesForm.getEditMode()) {
				addVariables(name, data);
			} else if (!variablesData.contains(name)) {
				addVariables(name, data);
			} else {
				variablesForm.setErrorVariableExist();
			}
			AEGWTLogicalEvent saveEvt = new AEGWTLogicalEvent(getWindowName(), getName());
			saveEvt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
			saveEvt.setSourceWidget(getName());
			saveEvt.addElementAsComposite(CRONIOBOIMachineProperties.MACHINE_VARIABLES, variablesData);
			getLogicalEventHandlerManager().fireEvent(saveEvt);
			
		} else if(DSLAMBusDesktopVariablesList.NAME.equals(evt.getSourceWidget()) && LOGICAL_TYPE.EDIT_EVENT.equals(evt.getEventType())) {
			
			AEMFTMetadataElement variableData = variablesData.getElement(evt.getSourceWidgetId());
			variablesForm.setData((AEMFTMetadataElementComposite) variableData);
			variablesForm.setEditMode(DSLAMBOIVariablesDataConstants.EDIT_MODE);
			variablesForm.center();
			
		} else if(DSLAMBusDesktopVariablesList.NAME.equals(evt.getSourceWidget()) && LOGICAL_TYPE.DELETE_EVENT.equals(evt.getEventType())) {
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
		return LOGICAL_TYPE.SAVE_EVENT.equals(type) || LOGICAL_TYPE.EDIT_EVENT.equals(type) || LOGICAL_TYPE.DELETE_EVENT.equals(type);	
	}
	
	/**
	 * PRIVATE
	 */
	
	private void addVariables(String id, AEMFTMetadataElementComposite data) {
		variableList.clearList();
		variablesData.addElement(id,data);
		variableList.setData(variablesData);
		variablesForm.resetForm();	
	}
}
