package com.imotion.cronio.backend.persistence.service.node;

import java.util.List;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.DSLAMBOIVariable;



public interface CRONIOBKINodePersistenceService {

	CRONIOBOINode addNode(CRONIOBOINode node);

	List<CRONIOBOINode> getAllNodes();

	void removeNode(Long nodeIdAsLong);
	
	CRONIOBOINode getNode(Long nodeIdAsLong);

	CRONIOBOINode updateNodeType(Long nodeIdAsLong, int nodeType);

	CRONIOBOINode updateNodeIp(Long nodeIdAsLong, String nodeIp);

	CRONIOBOINode updateNodeName(Long nodeIdAsLong, String nodeName);

	CRONIOBOINode updateNodeVariableList(Long nodeIdAsLong,List<DSLAMBOIVariable> variableList);

}
