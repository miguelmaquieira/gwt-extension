package com.imotion.dslam.backend.persistence.service.process;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imotion.dslam.backend.persistence.jpa.DSLAMBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.data.DSLAMBOProcess;
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
	public DSLAMBOIProcess updateProcess(Long processId, DSLAMBOIProcess process) {
		DSLAMBOProcess originalProcess = getPersistenceModule().get(processId);
		if (originalProcess != null) {
			//Begin transaction
//			EntityTransaction tx = getPersistenceModule().beginTransaction();
			
			originalProcess.setSynchronous(process.isSynchronous());
			originalProcess.setScheduleList(process.getScheduleList());
			originalProcess.setVariableList(process.getVariableList());
			List<CRONIOBOINode> originalNodeList 	= originalProcess.getNodeList();
			List<CRONIOBOINode> nodesToRemove 		= new ArrayList<>(); 
			if (!AEMFTCommonUtilsBase.isEmptyList(originalNodeList)) {
				for (CRONIOBOINode node : originalNodeList) {
					nodesToRemove.add(node);
				}
			}
			
			for (CRONIOBOINode node : nodesToRemove) {
				originalProcess.removeNode(node);
			}
			
			originalProcess.setNodeList(process.getNodeList());
			
			originalProcess.setSavedTime(new Date());
			
			//Commit transaction
//			getPersistenceModule().commit(tx);
			getPersistenceModule().update(originalProcess);
			
//			//orphan nodes
//			for (CRONIOBOINode node : nodesToRemove) {
//				getNodePersistence().removeNode(node.getNodeId());
//			}
		}
		return originalProcess;
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
	
	@Override
	public DSLAMBOIProcess getProcess(Long processIdAsLong) {
		DSLAMBOProcess processJpa = getPersistenceModule().get(processIdAsLong);
		return processJpa;
	}

	/**
	 * AEMFTIHasPersistenceModule
	 */
	@Override
	public Class<DSLAMBOProcess> getPersistenceClass() {
		return DSLAMBOProcess.class;
	}

}
