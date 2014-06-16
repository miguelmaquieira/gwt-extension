package com.imotion.dslam.backend.persistence.service.process;

import java.util.Date;
import java.util.List;

import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIVariable;



public interface DSLAMBKIProcessPersistenceService {

	DSLAMBOIProcess addProcess(DSLAMBOIProcess process);

	DSLAMBOIProcess updateProcessSynchronous(Long processId, boolean synchronous);
	
	DSLAMBOIProcess updateProcessVariableList(Long processId, List<DSLAMBOIVariable> variableList);
	
	DSLAMBOIProcess updateProcessScheduleList(Long processId, List<Date> scheduleList);
	
	DSLAMBOIProcess updateProcessName(Long processId, String processname);

	List<DSLAMBOIProcess> getAllProcesses();

	void removeProcess(Long processIdAsLong);
	
	DSLAMBOIProcess getProcess(Long processIdAsLong);

}
