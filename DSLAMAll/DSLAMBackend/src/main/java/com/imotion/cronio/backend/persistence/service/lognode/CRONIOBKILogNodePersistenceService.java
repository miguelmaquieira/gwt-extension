package com.imotion.cronio.backend.persistence.service.lognode;

import com.imotion.dslam.bom.CRONIOBOILogNode;

public interface CRONIOBKILogNodePersistenceService {

	CRONIOBOILogNode addLogNode(CRONIOBOILogNode node);

	void removeLogNode(Long nodeIdAsLong);
	
	CRONIOBOILogNode getLogNode(Long nodeIdAsLong);

}
