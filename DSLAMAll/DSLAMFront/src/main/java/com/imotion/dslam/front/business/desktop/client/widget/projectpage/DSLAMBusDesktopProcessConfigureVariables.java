package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOIProcessDataConstants;
import com.imotion.dslam.bom.CRONIOBOIVariablesDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class DSLAMBusDesktopProcessConfigureVariables extends AEGWTCompositePanel implements AEGWTHasLogicalEventHandlers {

	public static final String NAME = "DSLAMBusDesktopPreferencesMachineVariables";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private FlowPanel 											root;
	private FlowPanel											variableListZone;
	private	 DSLAMBusDesktopVariablesList   					variableList;
	private DSLAMBusDesktopProcessConfigureVariablesForm		variablesForm;
	private	 AEMFTMetadataElementComposite						variablesData;

	public DSLAMBusDesktopProcessConfigureVariables() {
		root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_CONTENT_IN_BOX);

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
		variableListZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_VARIABLES_LIST);
		root.add(variableListZone);
		variableList = new DSLAMBusDesktopVariablesList(header.getDeleteButton());
		variableListZone.add(variableList);
	}

	public void reset() {
		variableList.reset();
		variablesData.removeAll();
		variablesForm.resetForm(CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE_PROCESS, CRONIOBOIVariablesDataConstants.VARIABLE_TYPE_TEXT);
	}
	
	/**
	 * AEGWTICompositePanel
	 */
	
	@Override
	public void postDisplay() {
		super.postDisplay();
		variablesForm = new DSLAMBusDesktopProcessConfigureVariablesForm(this);
		variablesForm.addVariableScope(CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE_PROCESS	, TEXTS.process_variable());
		variablesForm.addVariableScope(CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE_EXTERNAL	, TEXTS.external_variable());
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
			String 	name		= evt.getElementAsString(CRONIOBOIVariablesDataConstants.VARIABLE_NAME);
			String 	value 		= evt.getElementAsString(CRONIOBOIVariablesDataConstants.VARIABLE_VALUE);
			String 	scope 		= evt.getElementAsString(CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE);
			String 	type 		= evt.getElementAsString(CRONIOBOIVariablesDataConstants.VARIABLE_TYPE);
			int 	scopeAsInt 	= AEMFTCommonUtilsBase.getIntegerFromString(scope);
			int 	typeAsInt 	= AEMFTCommonUtilsBase.getIntegerFromString(type);
			
			AEMFTMetadataElementComposite data = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
			getElementController().setElement(CRONIOBOIVariablesDataConstants.VARIABLE_NAME		, data	, name);
			getElementController().setElement(CRONIOBOIVariablesDataConstants.VARIABLE_VALUE		, data	, value);
			getElementController().setElement(CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE		, data	, scopeAsInt);
			getElementController().setElement(CRONIOBOIVariablesDataConstants.VARIABLE_TYPE		, data	, typeAsInt);
			
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
			saveEvt.addElementAsComposite(CRONIOBOIProcessDataConstants.PROCESS_VARIABLES_DATA, variablesData);
			getLogicalEventHandlerManager().fireEvent(saveEvt);
			
		} else if(DSLAMBusDesktopVariablesList.NAME.equals(evt.getSourceWidget()) && LOGICAL_TYPE.EDIT_EVENT.equals(evt.getEventType())) {
			
			AEMFTMetadataElement variableData = variablesData.getElement(evt.getSourceWidgetId());
			variablesForm.setData((AEMFTMetadataElementComposite) variableData);
			variablesForm.setEditMode(CRONIOBOIVariablesDataConstants.EDIT_MODE);
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
			deleteEvt.addElementAsComposite(CRONIOBOIProcessDataConstants.PROCESS_VARIABLES_DATA, variablesData);
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
		variablesForm.resetForm(CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE_PROCESS, CRONIOBOIVariablesDataConstants.VARIABLE_TYPE_TEXT);	
	}
}
