package com.imotion.dslam.business.service.impl;

import java.util.Date;
import java.util.List;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.CRONIOBOINodeDataConstants;
import com.imotion.dslam.bom.data.CRONIOBONode;
import com.imotion.dslam.business.service.CRONIOBUIBusinessNodeServiceTrace;
import com.imotion.dslam.business.service.CRONIOBUINodeBusinessService;
import com.imotion.dslam.business.service.CRONIOBUINodeBusinessServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUBomToMetadataConversor;
import com.imotion.dslam.business.service.DSLAMBUBusinessServiceBase;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.core.appli.metadata.element.factory.AEMFTMetadataElementReflectionBasedFactory;

public class CRONIOBUNodeBusinessServiceImpl extends DSLAMBUBusinessServiceBase implements CRONIOBUINodeBusinessService, CRONIOBUINodeBusinessServiceConstants, CRONIOBUIBusinessNodeServiceTrace {



	@Override
	public void addNode() {
		//ContextIn
		AEMFTMetadataElementComposite 	contextIn 	= getContext().getContextDataIN();
		String 							nodeName	= getElementDataController().getElementAsString(CRONIOBOINodeDataConstants.NODE_NAME				, contextIn);
		String 							nodeIp		= getElementDataController().getElementAsString(CRONIOBOINodeDataConstants.NODE_IP					, contextIn);
		int 							nodeType	= getElementDataController().getElementAsInt(CRONIOBOINodeDataConstants.NODE_MACHINE_TYPE		, contextIn);
		
		Date creationTime = new Date();
		CRONIOBOINode node = new CRONIOBONode();
		node.setNodeName(nodeName);
		node.setNodeIp(nodeIp);
		node.setNodeType(nodeType);
		node.setCreationTime(creationTime);
		node.setSavedTime(creationTime);
		node = getNodePersistence().addNode(node);

		//init-trace
		traceNewItemPersistent(METHOD_ADD_NODE, CRONIOBOINode.class.getSimpleName(), String.valueOf(node.getNodeId()));
		//end-trace

		//ContextOut
		AEMFTMetadataElementComposite nodeDataElement = DSLAMBUBomToMetadataConversor.fromNode(node);
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(NODE_DATA, nodeDataElement);
	}

	@Override
	public void updateNode() {
		//ContextIn
		AEMFTMetadataElementComposite 	contextIn 		= getContext().getContextDataIN();
		
		//contextOut.addElement(PROCESS_DATA, processDataElement);
	}

	@Override
	public void removeNode() {
		//ContextIn
		AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();
		String nodeId		= getElementDataController().getElementAsString(CRONIOBOINodeDataConstants.NODE_ID		, contextIn);

		Long nodeIdAsLong 	= AEMFTCommonUtilsBase.getLongFromString(nodeId);
		getNodePersistence().removeNode(nodeIdAsLong);

		//init-trace
		traceItemRemovedFromPersistence(METHOD_REMOVE_NODE, CRONIOBOINode.class.getSimpleName(), nodeId);
		//end-trace

		//ContextOut
		AEMFTMetadataElementComposite nodeDataElement = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		nodeDataElement.addElement(CRONIOBOINodeDataConstants.NODE_ID, nodeIdAsLong);

		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(NODE_DATA, nodeDataElement);
	}


	@Override
	public void getAllNodes() {
		List<CRONIOBOINode> 		nodeList 	= getNodePersistence().getAllNodes();

		//trace-init
		int resultsNumber = 0;
		if (!AEMFTCommonUtilsBase.isEmptyList(nodeList)) {
			resultsNumber = nodeList.size();
		}
		traceNumberOfResults(METHOD_GET_ALL_NODES, CRONIOBOINode.class.getSimpleName(), resultsNumber);
		//end-trace

		//ContextOut
		AEMFTMetadataElementComposite nodeDataElement = DSLAMBUBomToMetadataConversor.fromNodeList(nodeList);
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(NODE_DATA, nodeDataElement);
	}

}
