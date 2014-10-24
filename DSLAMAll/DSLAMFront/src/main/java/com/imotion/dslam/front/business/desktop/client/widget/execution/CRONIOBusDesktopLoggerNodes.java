package com.imotion.dslam.front.business.desktop.client.widget.execution;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOIExecution;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.CRONIOBOIProject;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopHasProjectEventHandlers;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.view.log.CRONIOBusI18NLogTexts;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.CRONIOBusDesktopHeaderOpcionalListActions;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopLoggerNodes extends AEGWTCompositePanel implements AEGWTHasLogicalEventHandlers, CRONIOBusDesktopHasProjectEventHandlers {
	public static final String NAME = "CRONIOBusDesktopLoggerNodes";
	private static CRONIOBusI18NLogTexts TEXTS = GWT.create(CRONIOBusI18NLogTexts.class);
	
	private FlowPanel 									root;
	private CRONIOBusDesktopLoggerNodesList				loggerNodeList;
	private CRONIOBusDesktopLoggerExecutionInfo			loggerExecutionInfoZone;
	
	private long 	executionId;
	private String projectId;
	
	public CRONIOBusDesktopLoggerNodes() {
		root = new FlowPanel();
		initWidget(root);
		
		loggerExecutionInfoZone = new CRONIOBusDesktopLoggerExecutionInfo();
		root.add(loggerExecutionInfoZone);
		loggerExecutionInfoZone.addStyleName(CRONIOBusDesktopIStyleConstants.EXECUTION_LOGGER_INFOZONE);
		loggerExecutionInfoZone.setVisible(false);
		
		FlowPanel loggerNodeListZone = new FlowPanel();
		loggerNodeListZone.addStyleName(CRONIOBusDesktopIStyleConstants.EXECUTION_LOGGER_NODES_LISTZONE);
		root.add(loggerNodeListZone);
		
		CRONIOBusDesktopHeaderOpcionalListActions header = new CRONIOBusDesktopHeaderOpcionalListActions(TEXTS.logger_node_list(), false);
		loggerNodeListZone.add(header);
		
		loggerNodeList = new CRONIOBusDesktopLoggerNodesList(null);
		loggerNodeListZone.add(loggerNodeList);
		loggerNodeList.addStyleName(CRONIOBusDesktopIStyleConstants.EXECUTION_LOGGER_NODES_LIST);
		
	}
	
	public void reset() {
		loggerNodeList.reset();
		loggerExecutionInfoZone.reset();
		loggerExecutionInfoZone.setVisible(false);
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
		if (data != null) {
			AEMFTMetadataElementComposite logNodesData = data.getCompositeElement(CRONIOBOIExecution.LOGNODES);
			AEMFTMetadataElementSingle executionIdData 	= (AEMFTMetadataElementSingle) data.getElement(CRONIOBOIExecution.EXECUTION_ID);
			executionId = executionIdData.getValueAsLong();
			AEMFTMetadataElementSingle projectIdData 	= (AEMFTMetadataElementSingle) data.getElement(CRONIOBOIProject.PROJECT_ID);
			projectId = projectIdData.getValueAsString();
			loggerNodeList.setData(logNodesData);
			loggerExecutionInfoZone.setData(data);
			loggerNodeList.setVisible(true);
			loggerExecutionInfoZone.setVisible(true);
			//loggerNodeList.sort(null, false);
		}
	
	}
	
	@Override
	public void postDisplay() {
		super.postDisplay();
		getLogicalEventHandlerManager().addLogicalEventHandler(this);

	}
	
	/**
	 * AEGWTHasLogicalEventHandlers
	 */
	
	@Override
	public void dispatchEvent(AEGWTLogicalEvent evt) {
		LOGICAL_TYPE evtTyp = evt.getEventType();
		String sourceWidget = evt.getSourceWidget();
		if (LOGICAL_TYPE.GET_EVENT.equals(evtTyp) && CRONIOBusDesktopLoggerNodesList.NAME.equals(sourceWidget)) {
			String nodeName = evt.getElementAsString(AEGWTIBoostrapConstants.TR_ID);
			AEGWTLogicalEvent getLogsEvt = new  AEGWTLogicalEvent(getWindowName(), getName(), null);
			getLogsEvt.setEventType(LOGICAL_TYPE.GET_EVENT);
			getLogsEvt.addElementAsString(CRONIOBOINode.NODE_NAME	, nodeName);
			getLogsEvt.addElementAsString(CRONIOBOIExecution.EXECUTION_ID	, String.valueOf(executionId));
			getLogsEvt.addElementAsString(CRONIOBOIProject.PROJECT_ID, projectId);
			getLogicalEventHandlerManager().fireEvent(getLogsEvt);
		} 	
	} 


	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return LOGICAL_TYPE.GET_EVENT.equals(type);

	}
	
//	@Override
//	public void dispatchEvent(AEGWTLogicalEvent evt) {
//		String			srcWidget		= evt.getSourceWidget();
//		LOGICAL_TYPE	type			= evt.getEventType();
//		if (CRONIOBusDesktopHeaderListActions.NAME.equals(srcWidget)) {
//			if (LOGICAL_TYPE.OPEN_EVENT.equals(type)) {
//				evt.stopPropagation();
//				AEMFTMetadataElementComposite nodesData = evt.getElementAsComposite(CRONIOBOINodeList.NODELIST_DATA);
//				AEMFTMetadataElement nodeListIdData = nodesDataList.getElement(CRONIOBOINodeList.NODELIST_ID);
//				String nodeListId = nodeListIdData.toString();
//				String nodeListIdFormat = nodeListId.replace("nodeListId: ", "");
//				List<AEMFTMetadataElement> nodes = nodesData.getSortedElementList();
//				AEMFTMetadataElementComposite nodeComposite = AEMFTMetadataElementConstructorBasedFactory.getInstance().getComposite();
//				for (AEMFTMetadataElement node : nodes) {
//					nodeComposite = (AEMFTMetadataElementComposite) node;
//					nodeComposite.addElement(CRONIOBOINodeList.NODELIST_ID, nodeListIdFormat);
//					node = nodeComposite;
//				}
//				AEMFTMetadataElementComposite cloneNodesData = (AEMFTMetadataElementComposite) nodesData.cloneObject();
//				AEMFTMetadataElementComposite cloneNodesFormatData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
//				getElementController().setElement(CRONIOBOINodeList.NODELIST_NODE_LIST, cloneNodesFormatData, cloneNodesData);
//				//cloneNodesFormatData.addElement(CRONIOBOIPreferencesDataConstants.PREFERENCES_MACHINE_PROPERTIES_LIST, getMachinesFromPreferences());
//				setData(cloneNodesFormatData);
//			}	
//		} else if (CRONIOBusDesktopProcessNodeFinalItem.NAME.equals(srcWidget)) {
//			if (LOGICAL_TYPE.OPEN_EVENT.equals(type)) {
//				String srcWidgetId = evt.getSourceWidgetId();
//				boolean noWarning = evt.getelementAsBooleanDataValue();
//				AEMFTMetadataElementComposite nodeData = getElementController().getElementAsComposite(srcWidgetId, nodesData);
//				nodeData.addElement(CRONIOBOINodeDataConstants.MACHINE_EXISTS, noWarning);
//				AEMFTMetadataElementComposite cloneNodeData = (AEMFTMetadataElementComposite) nodeData.cloneObject();
//				cloneNodeData.setKey(srcWidgetId);
//				nodeInfoZone.setData(cloneNodeData);
//				nodeList.setElementSeleted(srcWidgetId);
//				nodeInfoZone.setVisible(true);
//			}	
//		} else if (CRONIOBusDesktopProcessLoggerInfo.NAME.equals(srcWidget)) {
//			if (LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
//				AEMFTMetadataElementComposite nodeData = evt.getElementAsComposite(evt.getSourceWidgetId());
//				AEMFTMetadataElementComposite cloneNodeData = (AEMFTMetadataElementComposite) nodeData.cloneObject();
//				nodesData.addElement(evt.getSourceWidgetId(), cloneNodeData);
//				AEMFTMetadataElementComposite cloneNodesData = (AEMFTMetadataElementComposite) nodesData.cloneObject();
//				nodesDataList.addElement(CRONIOBOINodeList.NODELIST_NODE_LIST, cloneNodesData);
//				AEGWTLogicalEvent saveEvt = new AEGWTLogicalEvent(getWindowName(), getName());
//				saveEvt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
//				saveEvt.setSourceWidget(getName());
//				saveEvt.addElementAsComposite(CRONIOBOIProcessDataConstants.PROCESS_NODELIST_DATA, nodesDataList);
//				getLogicalEventHandlerManager().fireEvent(saveEvt);
//			}	
//		} else if (CRONIOBusDesktopProcessAddNodeForm.NAME.equals(evt.getSourceWidget()) && LOGICAL_TYPE.SAVE_EVENT.equals(evt.getEventType())) {
//			String 	name		= evt.getElementAsString(CRONIOBOINodeDataConstants.NODE_NAME);
//			String 	ip 			= evt.getElementAsString(CRONIOBOINodeDataConstants.NODE_IP);
//			String 	nodeType 	= evt.getElementAsString(CRONIOBOINodeDataConstants.NODE_TYPE);
//			
//			AEMFTMetadataElementComposite data = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
//			getElementController().setElement(CRONIOBOINodeDataConstants.NODE_NAME			, data	, name);
//			getElementController().setElement(CRONIOBOINodeDataConstants.NODE_IP			, data	, ip);
//			getElementController().setElement(CRONIOBOINodeDataConstants.NODE_TYPE			, data	, nodeType);
//
//			if (!nodesData.contains(name)) {
//				addNode(name, data);
//			} else {
//				nodeList.setErrorNodeExist();
//			}
//			AEGWTLogicalEvent saveEvt = new AEGWTLogicalEvent(getWindowName(), getName());
//			saveEvt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
//			saveEvt.setSourceWidget(getName());
//			saveEvt.addElementAsComposite(CRONIOBOINodeList.NODELIST_DATA, nodesData);
//			getLogicalEventHandlerManager().fireEvent(saveEvt);
//			
//		}	
//	}
//
//	@Override
//	public boolean isDispatchEventType(LOGICAL_TYPE type) {
//		return LOGICAL_TYPE.OPEN_EVENT.equals(type) || LOGICAL_TYPE.SAVE_EVENT.equals(type);
//	}
	
	/**
	 * CRONIOBusDesktopHasProjectEventHandlers
	 */
	
	@Override
	public void dispatchEvent(CRONIOBusDesktopProjectEvent evt) {
		// TODO Auto-generated method stub	
	}

	@Override
	public boolean isDispatchEventType(EVENT_TYPE type) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * PRIVATE
	 */
}
