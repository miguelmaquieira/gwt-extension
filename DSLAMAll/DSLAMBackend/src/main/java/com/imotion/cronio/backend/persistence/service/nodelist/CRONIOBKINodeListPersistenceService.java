package com.imotion.cronio.backend.persistence.service.nodelist;


import java.util.List;

import com.imotion.dslam.bom.CRONIOBOINodeList;



public interface CRONIOBKINodeListPersistenceService {

	CRONIOBOINodeList addNodeList(CRONIOBOINodeList nodeList, long processId);

	void removeNodeList(Long nodeListIdAsLong);
	
	CRONIOBOINodeList getNodeList(Long nodeListIdAsLong);

	List<CRONIOBOINodeList> getAllNodeListsByProject(long projectId);
	
	CRONIOBOINodeList updateNodeList(Long nodeListId, CRONIOBOINodeList nodeList);
	
}
