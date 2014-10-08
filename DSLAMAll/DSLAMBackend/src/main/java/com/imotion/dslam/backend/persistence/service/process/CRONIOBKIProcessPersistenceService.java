package com.imotion.dslam.backend.persistence.service.process;

import java.util.Date;
import java.util.List;

import com.imotion.dslam.bom.CRONIOBOINodeList;
import com.imotion.dslam.bom.CRONIOBOIProcess;



public interface CRONIOBKIProcessPersistenceService {

	CRONIOBOIProcess addProcess(CRONIOBOIProcess process);

	List<CRONIOBOIProcess> getAllProcesses();

	void removeProcess(Long processIdAsLong);
	
	CRONIOBOIProcess getProcess(Long processIdAsLong);

	CRONIOBOIProcess updateProcess(Long processId, CRONIOBOIProcess processData, Long preferencesId, Date date);
	
	CRONIOBOIProcess addNodeListUpdateProcess(Long processId, CRONIOBOINodeList nodeList);

}
