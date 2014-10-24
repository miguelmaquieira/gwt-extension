package com.imotion.cronio.backend.persistence.service.lognode;

import com.imotion.dslam.backend.persistence.jpa.CRONIOBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.CRONIOBOILogNode;
import com.imotion.dslam.bom.data.CRONIOBOLogNode;

public class CRONIOBKLogNodePersistenceServiceJPA extends CRONIOBKPersistenceServiceBaseJPA<CRONIOBOILogNode, CRONIOBOLogNode, Long> implements CRONIOBKILogNodePersistenceService {

	private static final long serialVersionUID = 3360089890109187736L;

	@Override
	public CRONIOBOILogNode addLogNode(CRONIOBOILogNode logNode) {
		CRONIOBOLogNode logNodeJPA = (CRONIOBOLogNode) logNode;
		logNodeJPA = getPersistenceModule().create(logNodeJPA);
		return logNodeJPA;
	}
	
	@Override
	public void removeLogNode(Long nodeIdAsLong) {
		getPersistenceModule().remove(nodeIdAsLong);
	}
	
	@Override
	public CRONIOBOILogNode getLogNode(Long nodeIdAsLong) {
		CRONIOBOLogNode logNodeJpa = getPersistenceModule().get(nodeIdAsLong);
		return logNodeJpa;
	}

	/**
	 * AEMFTIHasPersistenceModule
	 */
	
	@Override
	public Class<CRONIOBOLogNode> getPersistenceClass() {
		return CRONIOBOLogNode.class;
	}

}
