package com.imotion.cronio.backend.persistence.service.node;

import com.imotion.dslam.backend.persistence.jpa.DSLAMBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.CRONIOBOINodeList;
import com.imotion.dslam.bom.data.CRONIOBONode;
import com.selene.arch.exe.core.common.AEMFTCommonUtils;

public class CRONIOBKNodePersistenceServiceJPA extends DSLAMBKPersistenceServiceBaseJPA<CRONIOBOINode, CRONIOBONode, Long> implements CRONIOBKINodePersistenceService {

	private static final long serialVersionUID = 4679813620335280677L;

	@Override
	public CRONIOBOINode addNode(CRONIOBOINode node) {
		CRONIOBONode nodeJPA = (CRONIOBONode) node;
		CRONIOBOINodeList nodeList = node.getNodeList();
		if (nodeList != null && !AEMFTCommonUtils.isNullLong(nodeList.getNodeListId())) {
			nodeList = getNodeListPersistence().getNodeList(nodeList.getNodeListId());
			node.setNodeList(nodeList);
		}
		nodeJPA = getPersistenceModule().create(nodeJPA);
		return nodeJPA;
	}
	
	@Override
	public void removeNode(Long nodeIdAsLong) {
		getPersistenceModule().remove(nodeIdAsLong);
	}
	
	@Override
	public CRONIOBOINode getNode(Long nodeIdAsLong) {
		CRONIOBONode nodeJpa = getPersistenceModule().get(nodeIdAsLong);
		return nodeJpa;
	}

	/**
	 * AEMFTIHasPersistenceModule
	 */
	
	@Override
	public Class<CRONIOBONode> getPersistenceClass() {
		return CRONIOBONode.class;
	}

}
