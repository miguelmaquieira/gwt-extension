package com.imotion.dslam.backend.persistence.service.process;

import java.util.Date;
import java.util.List;

import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.data.DSLAMBOVariable;



public interface DSLAMBKIProcessPersistenceService {

	DSLAMBOIProcess addProcess(DSLAMBOIProcess process);

	DSLAMBOIProcess updateProcessSynchronous(Long processId, boolean synchronous);
	
	DSLAMBOIProcess updateProcessVariableList(Long processId, List<DSLAMBOVariable> variableList);
	
	DSLAMBOIProcess updateProcessScheduleList(Long processId, List<Date> scheduleList);
	
	DSLAMBOIProcess updateProcessName(Long processId, String processname);

	List<DSLAMBOIProcess> getAllProcesses();

	void removeProcess(Long processIdAsLong);

}
