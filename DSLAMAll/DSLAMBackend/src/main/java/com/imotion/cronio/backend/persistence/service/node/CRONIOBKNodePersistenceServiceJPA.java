package com.imotion.cronio.backend.persistence.service.node;

import com.imotion.dslam.backend.persistence.jpa.DSLAMBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.data.CRONIOBONode;
import com.selene.arch.exe.core.common.AEMFTCommonUtils;

public class CRONIOBKNodePersistenceServiceJPA extends DSLAMBKPersistenceServiceBaseJPA<CRONIOBOINode, CRONIOBONode, Long> implements CRONIOBKINodePersistenceService {

	private static final long serialVersionUID = 4679813620335280677L;

	@Override
	public CRONIOBOINode addNode(CRONIOBOINode node) {
		CRONIOBONode nodeJPA = (CRONIOBONode) node;
		DSLAMBOIProcess process = node.getProcess();
		if (process != null && !AEMFTCommonUtils.isNullLong(process.getProcessId())) {
			process = getProcessPersistence().getProcess(process.getProcessId());
			node.setProcess(process);
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
