package com.imotion.cronio.backend.persistence.service.node;

import com.imotion.dslam.bom.CRONIOBOINode;



public interface CRONIOBKINodePersistenceService {

	CRONIOBOINode addNode(CRONIOBOINode node);

	void removeNode(Long nodeIdAsLong);
	
	CRONIOBOINode getNode(Long nodeIdAsLong);


}
