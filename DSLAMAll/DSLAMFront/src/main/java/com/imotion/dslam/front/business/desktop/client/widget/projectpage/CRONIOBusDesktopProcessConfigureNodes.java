package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOINodeDataConstants;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.bom.DSLAMBOIProcessDataConstants;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopProcessConfigureNodes extends AEGWTCompositePanel implements AEGWTHasLogicalEventHandlers {
	public static final String NAME = "CRONIOBusDesktopProcessConfigureNodes";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private FlowPanel 									root;
	private CRONIOBusDesktopProcessNodeList				nodeList;
	private CRONIOBusDesktopProcessConfigureNodesInfo	nodeInfoZone;
	private	 AEMFTMetadataElementComposite				nodesData;
	

	public CRONIOBusDesktopProcessConfigureNodes() {
		root = new FlowPanel();
		initWidget(root);
		
		FlowPanel leftZone 	= new FlowPanel();
		root.add(leftZone);
		leftZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODES_LEFT_ZONE);
		leftZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_4);
		
		FlowPanel rightZone = new FlowPanel();
		root.add(rightZone);
		rightZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODES_RIGHT_ZONE);
		rightZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_8);
		
		nodeList = new CRONIOBusDesktopProcessNodeList();
		leftZone.add(nodeList);
		nodeList.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODES_LIST_ZONE);
		
		nodeInfoZone = new CRONIOBusDesktopProcessConfigureNodesInfo();
		rightZone.add(nodeInfoZone);
		nodeInfoZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODES_INFO_ZONE);
		nodeInfoZone.setVisible(false);
	}
	
	public void reset() {
		nodeList.reset();
		nodeInfoZone.reset();
		nodeInfoZone.setVisible(false);
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
			nodesData = data;
			
			AEMFTMetadataElementComposite machineList = getElementController().getElementAsComposite(CRONIOBOIPreferences.PREFERENCES_MACHINE_PROPERTIES_LIST, data);
			data.removeElement(CRONIOBOIPreferences.PREFERENCES_MACHINE_PROPERTIES_LIST);
			List<AEMFTMetadataElement> elementDataList = data.getSortedElementList();
			for (AEMFTMetadataElement elementData : elementDataList) {
				AEMFTMetadataElementComposite elementDataComposite = (AEMFTMetadataElementComposite) elementData;
				elementDataComposite.addElement(CRONIOBOIPreferences.PREFERENCES_MACHINE_PROPERTIES_LIST, machineList);
				if (!DSLAMBOIProject.INFO.equals(elementData.getKey())) {
					nodeList.addElement(elementDataComposite);
				}
			}
		} else {
			nodesData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		}
	}
	
	@Override
	public void postDisplay() {
		super.postDisplay();
		getLogicalEventHandlerManager().addLogicalEventHandler(this);
		nodeList.postDisplay();
		nodeInfoZone.postDisplay();
	}
	
	/**
	 * AEGWTHasLogicalEventHandlers
	 */
	
	@Override
	public void dispatchEvent(AEGWTLogicalEvent evt) {
		String			srcWidget		= evt.getSourceWidget();
		LOGICAL_TYPE	type			= evt.getEventType();
		if (CRONIOBusDesktopHeaderListActions.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.OPEN_EVENT.equals(type)) {
				AEMFTMetadataElementComposite nodesData = evt.getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_NODES_DATA);
				AEMFTMetadataElementComposite cloneNodesData = (AEMFTMetadataElementComposite) nodesData.cloneObject();
				setData(cloneNodesData);
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
				nodeInfoZone.setVisible(true);
			}	
		} else if (CRONIOBusDesktopProcessConfigureNodesInfo.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
				AEMFTMetadataElementComposite nodeData = evt.getElementAsComposite(evt.getSourceWidgetId());
				AEMFTMetadataElementComposite cloneNodeData = (AEMFTMetadataElementComposite) nodeData.cloneObject();
				nodesData.addElement(evt.getSourceWidgetId(), cloneNodeData);
				AEGWTLogicalEvent saveEvt = new AEGWTLogicalEvent(getWindowName(), getName());
				saveEvt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
				saveEvt.setSourceWidget(getName());
				saveEvt.addElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_NODES_DATA, nodesData);
				getLogicalEventHandlerManager().fireEvent(saveEvt);
			}	
		}	
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return LOGICAL_TYPE.OPEN_EVENT.equals(type) || LOGICAL_TYPE.SAVE_EVENT.equals(type);
	}
}
