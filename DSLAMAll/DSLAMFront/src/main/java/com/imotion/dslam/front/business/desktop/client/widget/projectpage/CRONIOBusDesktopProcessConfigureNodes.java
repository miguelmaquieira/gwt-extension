package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOINodeDataConstants;
import com.imotion.dslam.bom.CRONIOBOINodeList;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.bom.CRONIOBOIProcessDataConstants;
import com.imotion.dslam.bom.CRONIOBOIProject;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopHasProjectEventHandlers;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusProjectBasePresenterConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopProcessConfigureNodes extends AEGWTCompositePanel implements AEGWTHasLogicalEventHandlers, CRONIOBusDesktopHasProjectEventHandlers {
	public static final String NAME = "CRONIOBusDesktopProcessConfigureNodes";
	private static CRONIOBusI18NTexts TEXTS = GWT.create(CRONIOBusI18NTexts.class);
	
	private FlowPanel 									root;
	private CRONIOBusDesktopProcessNodeList				nodeList;
	private CRONIOBusDesktopProcessConfigureNodesInfo	nodeInfoZone;
	private List<String>								modifyNodeLists;
	private	 AEMFTMetadataElementComposite				nodesData;
	private	 AEMFTMetadataElementComposite				nodesDataList;
	private AEMFTMetadataElementComposite				machineListData;
	
	public CRONIOBusDesktopProcessConfigureNodes() {
		root = new FlowPanel();
		initWidget(root);
		
		FlowPanel leftZone 	= new FlowPanel();
		root.add(leftZone);
		leftZone.addStyleName(CRONIOBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODES_LEFT_ZONE);
		leftZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_4);
		
		FlowPanel rightZone = new FlowPanel();
		root.add(rightZone);
		rightZone.addStyleName(CRONIOBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODES_RIGHT_ZONE);
		rightZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_8);
		
		nodeList = new CRONIOBusDesktopProcessNodeList();
		leftZone.add(nodeList);
		nodeList.addStyleName(CRONIOBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODES_LIST_ZONE);
		
		nodeInfoZone = new CRONIOBusDesktopProcessConfigureNodesInfo();
		rightZone.add(nodeInfoZone);
		nodeInfoZone.addStyleName(CRONIOBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODES_INFO_ZONE);
		nodeInfoZone.setVisible(false);
	}
	
	public void reset() {
		nodeList.reset();
		nodeInfoZone.reset();
		nodeInfoZone.setVisible(false);
	}
	
	public void setMachineTypes(List<String> machineList) {
		nodeList.setMachineTypes(machineList);
	}
	
	/**
	 * AEGWTCompositePanel
	 */

	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		reset();
		AEMFTMetadataElementComposite nodesNodeList = null;
		AEMFTMetadataElementComposite nodesListData = getElementController().getElementAsComposite(CRONIOBOINodeList.NODELIST_DATA, data);
		if (nodesListData != null) {
			nodesNodeList = getElementController().getElementAsComposite(CRONIOBOINodeList.NODELIST_NODE_LIST, nodesListData);
			AEMFTMetadataElementComposite machineList 	= getElementController().getElementAsComposite(CRONIOBOIPreferences.PREFERENCES_MACHINE_PROPERTIES_LIST, nodesListData);
			if (machineList != null) {
				machineListData = machineList;
				nodesListData.removeElement(CRONIOBOIPreferences.PREFERENCES_MACHINE_PROPERTIES_LIST);
			}
			if (nodesListData != null) {
				nodesDataList = nodesListData;
			}
		}
		
		AEMFTMetadataElementComposite nodes 		= getElementController().getElementAsComposite(CRONIOBOINodeList.NODELIST_NODE_LIST, data);
		
		if (nodes != null) {
			nodesData = nodes;
			
			List<AEMFTMetadataElement> elementDataList = nodes.getSortedElementList();
			for (AEMFTMetadataElement elementData : elementDataList) {
				AEMFTMetadataElementComposite elementDataComposite = (AEMFTMetadataElementComposite) elementData;
				elementDataComposite.addElement(CRONIOBOIPreferences.PREFERENCES_MACHINE_PROPERTIES_LIST, machineListData.cloneObject());

				if (!CRONIOBOIProject.INFO.equals(elementData.getKey())) {
					nodeList.addElement(elementDataComposite);
				}
			}
			nodesDataList.addElement(CRONIOBOINodeList.NODELIST_NODE_LIST, nodes);
		} else {
			nodesData = nodesNodeList;
			if (nodesData != null) {
				List<AEMFTMetadataElement> elementDataList = nodesData.getSortedElementList();
				for (AEMFTMetadataElement elementData : elementDataList) {
					AEMFTMetadataElementComposite elementDataComposite = (AEMFTMetadataElementComposite) elementData;
					elementDataComposite.addElement(CRONIOBOIPreferences.PREFERENCES_MACHINE_PROPERTIES_LIST, machineListData.cloneObject());

					if (!CRONIOBOIProject.INFO.equals(elementData.getKey())) {
						nodeList.addElement(elementDataComposite);
					}
				}
			}
		}
		nodeList.addAddNodeElement();
		
		if (nodes != null) {
			AEGWTLogicalEvent updateContextEvt = new AEGWTLogicalEvent(getWindowName(), getName());
			updateContextEvt.setEventType(LOGICAL_TYPE.UPDATE_EVENT);
			updateContextEvt.setSourceWidget(getName());
			updateContextEvt.addElementAsComposite(CRONIOBOINodeList.NODELIST_DATA, nodesDataList);
			getLogicalEventHandlerManager().fireEvent(updateContextEvt);
		}
		
	}
	
	@Override
	public void postDisplay() {
		super.postDisplay();
		getLogicalEventHandlerManager().addLogicalEventHandler(this);
		getLogicalEventHandlerManager().addEventHandler(CRONIOBusDesktopHasProjectEventHandlers.TYPE, this);
		nodeList.postDisplay();
		nodeInfoZone.postDisplay();
		modifyNodeLists = new ArrayList<>();
	}
	
	/**
	 * AEGWTHasLogicalEventHandlers
	 */
	
	@Override
	public void dispatchEvent(AEGWTLogicalEvent evt) {
		String			srcWidget		= evt.getSourceWidget();
		LOGICAL_TYPE	type			= evt.getEventType();
		if (CRONIOBusDesktopHeaderListFileActions.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.OPEN_EVENT.equals(type)) {
				evt.stopPropagation();
				AEMFTMetadataElementComposite nodesData = evt.getElementAsComposite(CRONIOBOINodeList.NODELIST_DATA);
				AEMFTMetadataElement nodeListIdData = nodesDataList.getElement(CRONIOBOINodeList.NODELIST_ID);
				String nodeListId = nodeListIdData.toString();
				String nodeListIdFormat = nodeListId.replace("nodeListId: ", "");
				if (!modifyNodeLists.contains(nodeListIdFormat)) {
					modifyNodeLists.add(nodeListIdFormat);
					
					CRONIOBusDesktopProjectEvent 	getModifyNodelistsEvt 	= new CRONIOBusDesktopProjectEvent(CRONIOBusProjectBasePresenterConstants.PROJECT_PRESENTER, getName());
					getModifyNodelistsEvt.setEventType(EVENT_TYPE.ADD_MODIFY_NODELISTS_ID);
					getModifyNodelistsEvt.addElementAsSerializableDataValue((Serializable) modifyNodeLists);
					getLogicalEventHandlerManager().fireEvent(getModifyNodelistsEvt);
				}
				List<AEMFTMetadataElement> nodes = nodesData.getSortedElementList();
				AEMFTMetadataElementComposite nodeComposite = AEMFTMetadataElementConstructorBasedFactory.getInstance().getComposite();
				for (AEMFTMetadataElement node : nodes) {
					nodeComposite = (AEMFTMetadataElementComposite) node;
					nodeComposite.addElement(CRONIOBOINodeList.NODELIST_ID, nodeListIdFormat);
					node = nodeComposite;
				}
				AEMFTMetadataElementComposite cloneNodesData = (AEMFTMetadataElementComposite) nodesData.cloneObject();
				AEMFTMetadataElementComposite cloneNodesFormatData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
				getElementController().setElement(CRONIOBOINodeList.NODELIST_NODE_LIST, cloneNodesFormatData, cloneNodesData);
				//cloneNodesFormatData.addElement(CRONIOBOIPreferencesDataConstants.PREFERENCES_MACHINE_PROPERTIES_LIST, getMachinesFromPreferences());
				setData(cloneNodesFormatData);
				
			}	
		} else if (CRONIOBusDesktopProcessNodeFinalItem.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.OPEN_EVENT.equals(type)) {
				String srcWidgetId = evt.getSourceWidgetId();
				boolean noWarning = evt.getelementAsBooleanDataValue();
				AEMFTMetadataElementComposite nodeData = getElementController().getElementAsComposite(srcWidgetId, nodesData);
				nodeData.addElement(CRONIOBOINodeDataConstants.MACHINE_EXISTS, noWarning);
				AEMFTMetadataElementComposite cloneNodeData = (AEMFTMetadataElementComposite) nodeData.cloneObject();
				cloneNodeData.setKey(srcWidgetId);
				nodeInfoZone.setData(cloneNodeData);
				nodeList.setElementSeleted(srcWidgetId);
				nodeInfoZone.setVisible(true);
			}	
		} else if (CRONIOBusDesktopProcessConfigureNodesInfo.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
				AEMFTMetadataElementComposite nodeData = evt.getElementAsComposite(evt.getSourceWidgetId());
				AEMFTMetadataElementComposite cloneNodeData = (AEMFTMetadataElementComposite) nodeData.cloneObject();
				nodesData.addElement(evt.getSourceWidgetId(), cloneNodeData);
				AEMFTMetadataElementComposite cloneNodesData = (AEMFTMetadataElementComposite) nodesData.cloneObject();
				nodesDataList.addElement(CRONIOBOINodeList.NODELIST_NODE_LIST, cloneNodesData);
				AEGWTLogicalEvent saveEvt = new AEGWTLogicalEvent(getWindowName(), getName());
				saveEvt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
				saveEvt.setSourceWidget(getName());
				saveEvt.addElementAsComposite(CRONIOBOIProcessDataConstants.PROCESS_NODELIST_DATA, nodesDataList);
				getLogicalEventHandlerManager().fireEvent(saveEvt);
			}	
		} else if (CRONIOBusDesktopProcessAddNodeForm.NAME.equals(evt.getSourceWidget()) && LOGICAL_TYPE.SAVE_EVENT.equals(evt.getEventType())) {
			String 	name		= evt.getElementAsString(CRONIOBOINodeDataConstants.NODE_NAME);
			String 	ip 			= evt.getElementAsString(CRONIOBOINodeDataConstants.NODE_IP);
			String 	nodeType 	= evt.getElementAsString(CRONIOBOINodeDataConstants.NODE_TYPE);
			
			AEMFTMetadataElementComposite data = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
			getElementController().setElement(CRONIOBOINodeDataConstants.NODE_NAME			, data	, name);
			getElementController().setElement(CRONIOBOINodeDataConstants.NODE_IP			, data	, ip);
			getElementController().setElement(CRONIOBOINodeDataConstants.NODE_TYPE			, data	, nodeType);

			if (!nodesData.contains(name)) {
				addNode(name, data);
				AEMFTMetadataElementSingle nodeListIdData = (AEMFTMetadataElementSingle) nodesDataList.getElement(CRONIOBOINodeList.NODELIST_ID);
				String nodeListId = nodeListIdData.getValueAsString();
				if (!modifyNodeLists.contains(nodeListId)) {
					modifyNodeLists.add(nodeListId);
					
					CRONIOBusDesktopProjectEvent 	getModifyNodelistsEvt 	= new CRONIOBusDesktopProjectEvent(CRONIOBusProjectBasePresenterConstants.PROJECT_PRESENTER, getName());
					getModifyNodelistsEvt.setEventType(EVENT_TYPE.ADD_MODIFY_NODELISTS_ID);
					getModifyNodelistsEvt.addElementAsSerializableDataValue((Serializable) modifyNodeLists);
					getLogicalEventHandlerManager().fireEvent(getModifyNodelistsEvt);
				}
			} else {
				nodeList.setErrorNodeExist();
			}
//			AEGWTLogicalEvent saveEvt = new AEGWTLogicalEvent(getWindowName(), getName());
//			saveEvt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
//			saveEvt.setSourceWidget(getName());
//			saveEvt.addElementAsComposite(CRONIOBOINodeList.NODELIST_DATA, nodesData);
//			getLogicalEventHandlerManager().fireEvent(saveEvt);
			
		}	
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return LOGICAL_TYPE.OPEN_EVENT.equals(type) || LOGICAL_TYPE.SAVE_EVENT.equals(type);
	}
	
	/**
	 * CRONIOBusDesktopHasProjectEventHandlers
	 */
	
	@Override
	public void dispatchEvent(CRONIOBusDesktopProjectEvent evt) {
//		if (EVENT_TYPE.GET_MODIFY_NODELISTS_ID.equals(evt.getEventType())) {
//			evt.stopPropagation();
//			CRONIOBusDesktopProjectEvent 	getModifyNodelistsEvt 	= new CRONIOBusDesktopProjectEvent(CRONIOBusProjectBasePresenterConstants.PROJECT_PRESENTER, getName());
//			getModifyNodelistsEvt.setEventType(EVENT_TYPE.SAVE_PROJECT);
//			getModifyNodelistsEvt.addElementAsSerializableDataValue((Serializable) modifyNodeLists);
//			getLogicalEventHandlerManager().fireEvent(getModifyNodelistsEvt);
//		}
//		
	}

	@Override
	public boolean isDispatchEventType(EVENT_TYPE type) {
		
		return false;
//				EVENT_TYPE.GET_MODIFY_NODELISTS_ID.equals(type);
	}
	
	/**
	 * PRIVATE
	 */
	
	private void addNode(String name, AEMFTMetadataElementComposite data) {
		nodeList.reset();
		nodesData.addElement(name,data);
		AEMFTMetadataElementComposite nodesDataClone = AEMFTMetadataElementConstructorBasedFactory.getInstance().getComposite();
		nodesDataClone.addElement(CRONIOBOINodeList.NODELIST_NODE_LIST, nodesData.cloneObject());
		setData(nodesDataClone);
		nodeList.resetForm();	
	}
}
