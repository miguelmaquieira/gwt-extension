package com.imotion.dslam.backend.persistence.service.process;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imotion.dslam.backend.persistence.jpa.CRONIOBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.CRONIOBOINodeList;
import com.imotion.dslam.bom.CRONIOBOIProcess;
import com.imotion.dslam.bom.data.CRONIOBOProcess;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;

public class CRONIOBKProcessPersistenceServiceJPA extends CRONIOBKPersistenceServiceBaseJPA<CRONIOBOIProcess, CRONIOBOProcess, Long> implements CRONIOBKIProcessPersistenceService {

	private static final long serialVersionUID = 4872791158147475333L;

	@Override
	public CRONIOBOIProcess addProcess(CRONIOBOIProcess process) {
		CRONIOBOProcess processJPA = (CRONIOBOProcess) process;
		processJPA = getPersistenceModule().create(processJPA);
		return processJPA;
	}
	
	@Override
	public CRONIOBOIProcess updateProcess(Long processId, CRONIOBOIProcess processData, Long preferencesId, Date date) {
		CRONIOBOProcess originalProcess = getPersistenceModule().get(processId);
		if (originalProcess != null) {

			originalProcess.setSynchronous(processData.isSynchronous());
			originalProcess.setScheduleList(processData.getScheduleList());
			originalProcess.setVariableList(processData.getVariableList());
			List<CRONIOBOINodeList> originalListNodeList 	= originalProcess.getListNodeList();
			List<CRONIOBOINodeList> nodeListsToRemove 		= new ArrayList<>(); 
			if (!AEMFTCommonUtilsBase.isEmptyList(originalListNodeList)) {
				for (CRONIOBOINodeList nodeList : originalListNodeList) {
					nodeListsToRemove.add(nodeList);
				}
			}
			
			for (CRONIOBOINodeList nodeList : nodeListsToRemove) {
				originalProcess.removeNodeList(nodeList);
			}
			
			List<CRONIOBOINodeList> newListNodeList			= processData.getListNodeList();
			List<CRONIOBOINodeList> persistedListNodeList	= new ArrayList<>();
			if (!AEMFTCommonUtilsBase.isEmptyList(newListNodeList)) {
				for (CRONIOBOINodeList nodeList : newListNodeList) {
					setMachinePropertiesToNodeList(preferencesId, nodeList);
					nodeList = getNodeListPersistence().addNodeList(nodeList, processId);
					persistedListNodeList.add(nodeList);
				}
			}
			originalProcess.setListNodeList(persistedListNodeList);
			
			if (date == null) {
				date = new Date();
			}
			originalProcess.setSavedTime(date);

			getPersistenceModule().update(originalProcess);
			
			//orphan nodes
			for (CRONIOBOINodeList nodeList : nodeListsToRemove) {
				getNodeListPersistence().removeNodeList(nodeList.getNodeListId());
			}
		}
		return originalProcess;
	}
	
	@Override
	public CRONIOBOIProcess addNodeListUpdateProcess(Long processId, CRONIOBOINodeList nodeList) {
		CRONIOBOProcess originalProcess = getPersistenceModule().get(processId);
		if (originalProcess != null) {
			originalProcess.addNodeList(nodeList);
			Date date = new Date();
			originalProcess.setSavedTime(date);
			getPersistenceModule().update(originalProcess);
		}
		return originalProcess;
	}
	
	@Override
	public List<CRONIOBOIProcess> getAllProcesses() {
		List<CRONIOBOProcess> processListJpa = getPersistenceModule().findAll();
		return AEMFTCommonUtilsBase.castList(processListJpa);
	}
	
	@Override
	public void removeProcess(Long processIdAsLong) {
		getPersistenceModule().remove(processIdAsLong);
	}
	
	@Override
	public CRONIOBOIProcess getProcess(Long processIdAsLong) {
		CRONIOBOProcess processJpa = getPersistenceModule().get(processIdAsLong);
		return processJpa;
	}

	/**
	 * AEMFTIHasPersistenceModule
	 */
	@Override
	public Class<CRONIOBOProcess> getPersistenceClass() {
		return CRONIOBOProcess.class;
	}
	
	/**
	 * PRIVATE
	 */

	private CRONIOBOINodeList setMachinePropertiesToNodeList(Long preferencesId, CRONIOBOINodeList nodeList) {
		List<CRONIOBOINode> nodesNodeList = nodeList.getNodeList();
		
		if (nodesNodeList != null) {
			for (CRONIOBOINode node : nodesNodeList) {
				String nodeType = node.getNodeType();
				CRONIOBOIMachineProperties machineProperties = getMachinePropertiesPersistence().getMachineProperties(preferencesId, nodeType);
				node.setMachineProperties(machineProperties);
			}
		}
		
		return nodeList;
	}
	
}
