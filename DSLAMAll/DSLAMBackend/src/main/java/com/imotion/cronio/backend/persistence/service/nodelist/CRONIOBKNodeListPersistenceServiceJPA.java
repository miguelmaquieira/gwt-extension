package com.imotion.cronio.backend.persistence.service.nodelist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imotion.dslam.backend.persistence.jpa.CRONIOBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.CRONIOBOINodeList;
import com.imotion.dslam.bom.CRONIOBOIProcess;
import com.imotion.dslam.bom.data.CRONIOBONodeList;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.core.common.AEMFTCommonUtils;

public class CRONIOBKNodeListPersistenceServiceJPA extends CRONIOBKPersistenceServiceBaseJPA<CRONIOBOINodeList, CRONIOBONodeList, Long> implements CRONIOBKINodeListPersistenceService {

	private static final long serialVersionUID = 1891855848752705994L;

	@Override
	public CRONIOBOINodeList addNodeList(CRONIOBOINodeList nodeList, long processId) {
		CRONIOBONodeList nodeListJPA = (CRONIOBONodeList) nodeList;
		
		CRONIOBOIProcess process = nodeList.getProcess();
		
		if (process != null && !AEMFTCommonUtils.isNullLong(process.getProcessId())) {
			process = getProcessPersistence().getProcess(process.getProcessId());
			nodeList.setProcess(process);
		}
		
		//CRONIOBOIProcess process = getProcessPersistence().getProcess(processId);
		nodeListJPA.setProcess(process);
		nodeListJPA = getPersistenceModule().create(nodeListJPA);
		return nodeListJPA;
	}
	
	@Override
	public void removeNodeList(Long nodeListIdAsLong) {
		getPersistenceModule().remove(nodeListIdAsLong);
	}
	
	@Override
	public CRONIOBOINodeList getNodeList(Long nodeListIdAsLong) {
		CRONIOBONodeList nodeListJpa = getPersistenceModule().get(nodeListIdAsLong);
		return nodeListJpa;
	}
	
	@Override
	public CRONIOBOINodeList updateNodeList(Long nodeListId, CRONIOBOINodeList nodeList) {
		CRONIOBONodeList nodeListFromDb = getPersistenceModule().get(nodeListId);
		
	
		if (nodeListFromDb != null) {
			CRONIOBOIProcess process = nodeList.getProcess();
			
			if (process != null && !AEMFTCommonUtils.isNullLong(process.getProcessId())) {
				process = getProcessPersistence().getProcess(process.getProcessId());
				nodeList.setProcess(process);
			}
			
			nodeListFromDb.setProcess(nodeList.getProcess());
			nodeListFromDb.setNodeListId(nodeList.getNodeListId());
			nodeListFromDb.setNodeList(nodeList.getNodeList());
			nodeListFromDb.setSavedTime(new Date());
			nodeListFromDb = getPersistenceModule().update(nodeListFromDb);
		}
		
		return nodeListFromDb;
	}
	
	@Override
	public List<CRONIOBOINodeList> getAllNodeListsByProject(long projectId) {
		List<CRONIOBONodeList> nodeListsJpa = getPersistenceModule().findAll();
		List<CRONIOBONodeList> nodeListsJpaByProject = new ArrayList<>();
		for (CRONIOBONodeList nodeList : nodeListsJpa) {
			if (nodeList.getProcess().getProject().getProjectId() == projectId) {
				nodeListsJpaByProject.add(nodeList);
			}
		}
		return AEMFTCommonUtilsBase.castList(nodeListsJpaByProject);
	}

	/**
	 * AEMFTIHasPersistenceModule
	 */
	
	@Override
	public Class<CRONIOBONodeList> getPersistenceClass() {
		return CRONIOBONodeList.class;
	}

}
