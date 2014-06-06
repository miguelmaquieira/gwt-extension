package com.imotion.dslam.backend.persistence.service.process;

import java.util.Date;
import java.util.List;

import com.imotion.dslam.backend.persistence.jpa.DSLAMBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.data.DSLAMBOFile;
import com.imotion.dslam.bom.data.DSLAMBOProcess;
import com.imotion.dslam.bom.data.DSLAMBOVariable;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;

public class DSLAMBKProcessPersistenceServiceJPA extends DSLAMBKPersistenceServiceBaseJPA<DSLAMBOIProcess, DSLAMBOProcess, Long> implements DSLAMBKIProcessPersistenceService {

	private static final long serialVersionUID = 4872791158147475333L;

	@Override
	public DSLAMBOIProcess addProcess(DSLAMBOIProcess process) {
		DSLAMBOProcess processJPA = (DSLAMBOProcess) process;
		processJPA = getPersistenceModule().create(processJPA);
		return processJPA;
	}
	
	@Override
	public DSLAMBOIProcess updateProcessSynchronous(Long processId, boolean synchronous) {
		DSLAMBOProcess process = getPersistenceModule().get(processId);
		process.setSynchronous(synchronous);
		process.setSavedTime(new Date());
		process = getPersistenceModule().update(process);
		return process;
	}
	
	@Override
	public DSLAMBOIProcess updateProcessScheduleList(Long processId, List<Date> scheduleList) {
		DSLAMBOProcess process = getPersistenceModule().get(processId);
		process.setScheduleList(scheduleList);
		process.setSavedTime(new Date());
		process = getPersistenceModule().update(process);
		return process;
	}
	
	@Override
	public DSLAMBOIProcess updateProcessVariableList(Long processId, List<DSLAMBOVariable> variableList) {
		DSLAMBOProcess process = getPersistenceModule().get(processId);
		process.setVariableList(variableList);
		process.setSavedTime(new Date());
		process = getPersistenceModule().update(process);
		return process;
	}
	
	@Override
	public DSLAMBOIProcess updateProcessName(Long processId, String processName) {
		DSLAMBOProcess process = getPersistenceModule().get(processId);
		process.setProcessName(processName);
		process.setSavedTime(new Date());
		process = getPersistenceModule().update(process);
		return process;
	}
	
	@Override
	public DSLAMBOIProcess updateProcessScript(Long processId, DSLAMBOFile processScript) {
		DSLAMBOProcess process = getPersistenceModule().get(processId);
		process.setProcessScript(processScript);
		process.setSavedTime(new Date());
		process = getPersistenceModule().update(process);
		return process;
	}
	
	@Override
	public List<DSLAMBOIProcess> getAllProcesses() {
		List<DSLAMBOProcess> processListJpa = getPersistenceModule().findAll();
		return AEMFTCommonUtilsBase.castList(processListJpa);
	}
	
	@Override
	public void removeProcess(Long processIdAsLong) {
		getPersistenceModule().remove(processIdAsLong);
	}

	/**
	 * AEMFTIHasPersistenceModule
	 */
	@Override
	public Class<DSLAMBOProcess> getPersistenceClass() {
		return DSLAMBOProcess.class;
	}

}
