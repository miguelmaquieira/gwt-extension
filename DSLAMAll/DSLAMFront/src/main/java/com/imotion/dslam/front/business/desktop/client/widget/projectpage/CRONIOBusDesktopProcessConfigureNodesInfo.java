package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.CRONIOBOINodeDataConstants;
import com.imotion.dslam.bom.CRONIOBOIVariablesDataConstants;
import com.imotion.dslam.front.business.client.CRONIOBusCommonConstants;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.selene.arch.base.exe.bus.AEMFTIBusinessConstant;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopProcessConfigureNodesInfo extends AEGWTCompositePanel implements AEGWTHasLogicalEventHandlers {
	public static final String NAME = "CRONIOBusDesktopProcessConfigureNodesInfo";
	private static CRONIOBusI18NTexts TEXTS = GWT.create(CRONIOBusI18NTexts.class);

	private FlowPanel 										root;
	private FlowPanel 										nodeVariableListZone;
	private CRONIOBusDesktopNodeInfoPanel					nodeInfoPanel;
	private CRONIOBusDesktopProcessNodesInfoVariablesList 	nodeVariableList;
	private CRONIOBusDesktopProcessNodeVariablesForm		nodeVariablesForm;
	private	 AEMFTMetadataElementComposite					nodeData;

	public CRONIOBusDesktopProcessConfigureNodesInfo() {
		root = new FlowPanel();
		initWidget(root);

		nodeInfoPanel 		= new CRONIOBusDesktopNodeInfoPanel(TEXTS.node_information());
		root.add(nodeInfoPanel);

		nodeVariableListZone = new FlowPanel();
		nodeVariableListZone.addStyleName(CRONIOBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODE_VARIABLES_LIST);
		root.add(nodeVariableListZone);

		CRONIOBusDesktopHeaderListActions header = new CRONIOBusDesktopHeaderListActions(TEXTS.node_variable_list());
		nodeVariableListZone.add(header);

		header.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				nodeVariablesForm.resetForm(CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE_NODE, CRONIOBOIVariablesDataConstants.VARIABLE_TYPE_TEXT);
				nodeVariablesForm.setEditMode(CRONIOBOIVariablesDataConstants.SAVE_MODE);
				nodeVariablesForm.center();	
			}
		});

		nodeVariableList 	= new CRONIOBusDesktopProcessNodesInfoVariablesList(header.getDeleteButton());
		nodeVariableListZone.add(nodeVariableList);
	}

	public void reset() {
		nodeInfoPanel.reset();
		nodeVariableList.reset();
	}

	/**
	 * AEGWTICompositePanel
	 */

	@Override
	public void postDisplay() {
		super.postDisplay();
		nodeVariablesForm = new CRONIOBusDesktopProcessNodeVariablesForm(this);
		getLogicalEventHandlerManager().addLogicalEventHandler(this);
	}

	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		nodeData = data;
		nodeVariableList.clearList();

		AEMFTMetadataElement nodeVariableData = getElementController().getElement(CRONIOBOINode.NODE_VARIABLE_LIST, data);

		if (nodeVariableData != null) {
			if (nodeVariableData instanceof AEMFTMetadataElementComposite) {
				AEMFTMetadataElementComposite nodeVariableDataComposite = getElementController().getElementAsComposite(CRONIOBOINode.NODE_VARIABLE_LIST, data);
				nodeVariableList.setData(nodeVariableDataComposite);
			}
		}
		nodeInfoPanel.setData(data);
	}

	public AEMFTMetadataElementComposite getData() {
		return nodeData;
	}

	/**
	 * AEGWTHasLogicalEventHandlers
	 */

	@SuppressWarnings("unchecked")
	@Override
	public void dispatchEvent(AEGWTLogicalEvent evt) {
		if (CRONIOBusDesktopProcessNodeVariablesForm.NAME.equals(evt.getSourceWidget()) && LOGICAL_TYPE.SAVE_EVENT.equals(evt.getEventType())) {
			String	id 					= evt.getElementAsString(CRONIOBOIVariablesDataConstants.VARIABLE_NAME);
			String	value 				= evt.getElementAsString(CRONIOBOIVariablesDataConstants.VARIABLE_VALUE);
			String	variableTypeStr		= evt.getElementAsString(CRONIOBOIVariablesDataConstants.VARIABLE_TYPE);
			int		variableType		= AEMFTCommonUtilsBase.getIntegerFromString(variableTypeStr);
			String	variableScopeStr	= evt.getElementAsString(CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE);
			int		variableScope		= AEMFTCommonUtilsBase.getIntegerFromString(variableScopeStr);

			AEMFTMetadataElementComposite data = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
			getElementController().setElement(CRONIOBOIVariablesDataConstants.VARIABLE_NAME		, data	, id);
			getElementController().setElement(CRONIOBOIVariablesDataConstants.VARIABLE_VALUE		, data	, value);
			getElementController().setElement(CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE		, data	, variableScope);
			getElementController().setElement(CRONIOBOIVariablesDataConstants.VARIABLE_TYPE		, data	, variableType);

			if (nodeVariablesForm.getEditMode()) {
				addVariables(id, data);
			} else if (!nodeData.contains(id)) {
				addVariables(id, data);
			} else {
				nodeVariablesForm.setErrorVariableExist();
			}
			AEGWTLogicalEvent saveEvt = new AEGWTLogicalEvent(getWindowName(), getName());
			saveEvt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
			saveEvt.setSourceWidget(getName());
			saveEvt.setSourceWidgetId(nodeData.getKey());
			saveEvt.addElementAsComposite(nodeData.getKey(), nodeData);
			getLogicalEventHandlerManager().fireEvent(saveEvt);

		} else if(CRONIOBusDesktopProcessNodesInfoVariablesList.NAME.equals(evt.getSourceWidget()) && LOGICAL_TYPE.EDIT_EVENT.equals(evt.getEventType())) {
			String variableId	= evt.getSourceWidgetId();
			String variableKey	= CRONIOBOINode.NODE_VARIABLE_LIST + CRONIOBusCommonConstants.ELEMENT_SEPARATOR + variableId; 
			AEMFTMetadataElementComposite variableData = getElementController().getElementAsComposite(variableKey, nodeData);

			nodeVariablesForm.setData(variableData);
			nodeVariablesForm.setEditMode(CRONIOBOIVariablesDataConstants.EDIT_MODE);
			nodeVariablesForm.center();

		} else if(CRONIOBusDesktopProcessNodesInfoVariablesList.NAME.equals(evt.getSourceWidget()) && LOGICAL_TYPE.DELETE_EVENT.equals(evt.getEventType())) {
			AEMFTMetadataElementSingle data = (AEMFTMetadataElementSingle) evt.getElementAsDataValue();
			List<String> rowIds = (List<String>) data.getValueAsSerializable();

			for (String rowId : rowIds) {
				String key =  CRONIOBOINodeDataConstants.NODE_VARIABLE_LIST + AEMFTIBusinessConstant.CTE_MFT_AE_BUS_SERVICE_ID_SEPARATOR + rowId;
				getElementController().removeElement(key, nodeData);
			}	
			AEGWTLogicalEvent deleteEvt = new AEGWTLogicalEvent(getWindowName(), getName());
			deleteEvt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
			deleteEvt.setSourceWidget(getName());
			deleteEvt.setSourceWidgetId(nodeData.getKey());
			deleteEvt.addElementAsComposite(nodeData.getKey(), nodeData);
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
		nodeVariableList.clearList();
		String key = CRONIOBOINodeDataConstants.NODE_VARIABLE_LIST + AEMFTIBusinessConstant.CTE_MFT_AE_BUS_SERVICE_ID_SEPARATOR + id;
		getElementController().setElement(key, nodeData, data);
		AEMFTMetadataElementComposite variablesNodeData = getElementController().getElementAsComposite(CRONIOBOINodeDataConstants.NODE_VARIABLE_LIST, nodeData);
		nodeVariableList.setData(variablesNodeData);
		nodeVariablesForm.resetForm(CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE_NODE, CRONIOBOIVariablesDataConstants.VARIABLE_TYPE_TEXT);	
	}
}
