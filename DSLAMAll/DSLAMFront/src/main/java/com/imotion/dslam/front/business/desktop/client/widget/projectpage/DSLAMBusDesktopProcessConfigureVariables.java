package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIProcessDataConstants;
import com.imotion.dslam.bom.DSLAMBOIVariablesDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapGlyphiconButton;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class DSLAMBusDesktopProcessConfigureVariables extends AEGWTCompositePanel implements AEGWTHasLogicalEventHandlers {

	public static final String NAME = "DSLAMBusDesktopProcessConfigureVariables";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private FlowPanel 											root;
	private FlowPanel											headerZone;
	private FlowPanel											variableListZone;
	private AEGWTBootstrapGlyphiconButton 						addVariableButton;
	private AEGWTBootstrapGlyphiconButton 						deleteVariablesButton;
	private	 DSLAMBusDesktopVariablesList   					variableList;
	private DSLAMBusDesktopProcessConfigureVariablesForm		variablesForm;
	private	 AEMFTMetadataElementComposite						variablesData;
	//private int												numberVariablesData;

	public DSLAMBusDesktopProcessConfigureVariables() {
		root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_CONTENT_IN_BOX);

		//Header
		headerZone 		= new FlowPanel();
		root.add(headerZone);
		headerZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_VARIABLES_HEADER);

		addVariableButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_PLUS, null, TEXTS.add());
		addVariableButton.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
		headerZone.add(addVariableButton);
		
		deleteVariablesButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_REMOVE, null, TEXTS.delete());
		deleteVariablesButton.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
		deleteVariablesButton.setVisible(false);
		headerZone.add(deleteVariablesButton);
	
		addVariableButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				variablesForm.resetForm();
				variablesForm.setEditMode(DSLAMBOIVariablesDataConstants.SAVE_MODE);
				variablesForm.center();
			}
		});	
		
		variableListZone = new FlowPanel();
		variableListZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_VARIABLES_LIST);
		root.add(variableListZone);
		variableList = new DSLAMBusDesktopVariablesList(deleteVariablesButton);
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
		variablesForm = new DSLAMBusDesktopProcessConfigureVariablesForm(this);
		getLogicalEventHandlerManager().addLogicalEventHandler(this);
	}
	
	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		variablesData = data;
		variableList.clearList();
		variableList.setData(data);	
	}
	
	public AEMFTMetadataElementComposite getData() {
		 return variablesData;
	}
	
	/**
	 * AEGWTHasLogicalEventHandlers
	 */
	
	@Override
	public void dispatchEvent(AEGWTLogicalEvent evt) {
		if (DSLAMBusDesktopProcessConfigureVariablesForm.NAME.equals(evt.getSourceWidget()) && LOGICAL_TYPE.SAVE_EVENT.equals(evt.getEventType())) {
			String id 		=  evt.getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_ID);
			String value 	=  evt.getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE);
			String type 	=  evt.getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE);
			AEMFTMetadataElementComposite data = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();

			getElementController().setElement(DSLAMBOIVariablesDataConstants.VARIABLE_ID		, data	, id);
			getElementController().setElement(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE		, data	, value);
			getElementController().setElement(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE		, data	, type);
			if (variablesForm.getEditMode()) {
				addVariables(id,data);
			} else if (!variablesData.contains(id)) {
				addVariables(id,data);
			} else {
				variablesForm.setErrorVariableExist();
			}
			AEGWTLogicalEvent saveEvt = new AEGWTLogicalEvent(getWindowName(), getName());
			saveEvt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
			saveEvt.setSourceWidget(getName());
			saveEvt.addElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_VARIABLES_DATA, variablesData);
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
			deleteEvt.addElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_VARIABLES_DATA, variablesData);
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
