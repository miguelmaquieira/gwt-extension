package com.imotion.dslam.backend.persistence.service.process;

import java.util.Date;
import java.util.List;

import com.imotion.dslam.bom.DSLAMBOIProcess;



public interface DSLAMBKIProcessPersistenceService {

	DSLAMBOIProcess addProcess(DSLAMBOIProcess process);

	List<DSLAMBOIProcess> getAllProcesses();

	void removeProcess(Long processIdAsLong);
	
	DSLAMBOIProcess getProcess(Long processIdAsLong);

	DSLAMBOIProcess updateProcess(Long processId, DSLAMBOIProcess processData, Long preferencesId, Date date);

}
