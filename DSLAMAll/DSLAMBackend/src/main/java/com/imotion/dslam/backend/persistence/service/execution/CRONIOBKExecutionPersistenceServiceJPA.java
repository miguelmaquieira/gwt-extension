package com.imotion.dslam.backend.persistence.service.execution;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imotion.dslam.backend.persistence.jpa.DSLAMBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.CRONIOBOIExecution;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.bom.data.CRONIOBOExecution;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.core.common.AEMFTCommonUtils;

public class CRONIOBKExecutionPersistenceServiceJPA extends DSLAMBKPersistenceServiceBaseJPA<CRONIOBOIExecution, CRONIOBOExecution, Long> implements CRONIOBKIExecutionPersistenceService {

	private static final long serialVersionUID = 7616081258676466890L;

	@Override
	public CRONIOBOIExecution getExecution(long executionId) {
		CRONIOBOIExecution execution = getPersistenceModule().get(executionId);
		return execution;
	}
	
	@Override
	public CRONIOBOIExecution addExecution(CRONIOBOIExecution execution) {
		CRONIOBOExecution executionJPA = (CRONIOBOExecution) execution;
		DSLAMBOIProject project = execution.getProject();
		if (project != null && !AEMFTCommonUtils.isNullLong(project.getProjectId())) {
			project = getProjectPersistence().getProject(project.getProjectId());
			execution.setProject(project);
		}
		
		executionJPA = getPersistenceModule().create(executionJPA);
		return executionJPA;
	}
	
	@Override
	public CRONIOBOIExecution updateExecution(Long executionId, CRONIOBOIExecution updatedExecution) {
		CRONIOBOExecution originalExecution = getPersistenceModule().get(executionId);
		if (originalExecution != null) {
			originalExecution.setSavedTime(new Date());
			getPersistenceModule().update(originalExecution);
		}
		return originalExecution;
	}
	
	@Override
	public List<CRONIOBOIExecution> getAllExecutions() {
		List<CRONIOBOExecution> executionListJpa = getPersistenceModule().findAll();
		return AEMFTCommonUtilsBase.castList(executionListJpa);
	}
	
	@Override
	public List<CRONIOBOIExecution> getAllExecutionsByProject(long projectId) {
		List<CRONIOBOExecution> executionListJpa = getPersistenceModule().findAll();
		List<CRONIOBOExecution> executionListJpaByProject = new ArrayList<>();
		for (CRONIOBOExecution execution : executionListJpa) {
			if (execution.getProject().getProjectId() == projectId) {
				executionListJpaByProject.add(execution);
			}
		}
		return AEMFTCommonUtilsBase.castList(executionListJpaByProject);
	}
	
	@Override
	public void removeExecution(Long executionIdAsLong) {
		getPersistenceModule().remove(executionIdAsLong);
	}

	/**
	 * AEMFTIHasPersistenceModule
	 */
	@Override
	public Class<CRONIOBOExecution> getPersistenceClass() {
		return CRONIOBOExecution.class;
	}


}
