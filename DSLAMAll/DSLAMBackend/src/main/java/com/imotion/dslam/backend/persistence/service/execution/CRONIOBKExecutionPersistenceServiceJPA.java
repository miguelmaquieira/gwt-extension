package com.imotion.dslam.backend.persistence.service.execution;

import java.util.ArrayList;
import java.util.List;

import com.imotion.dslam.backend.persistence.jpa.CRONIOBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.CRONIOBOIExecution;
import com.imotion.dslam.bom.CRONIOBOIProject;
import com.imotion.dslam.bom.data.CRONIOBOExecution;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.core.common.AEMFTCommonUtils;

public class CRONIOBKExecutionPersistenceServiceJPA extends CRONIOBKPersistenceServiceBaseJPA<CRONIOBOIExecution, CRONIOBOExecution, Long> implements CRONIOBKIExecutionPersistenceService {

	private static final long serialVersionUID = 7616081258676466890L;

	@Override
	public CRONIOBOIExecution getExecution(long executionId) {
		CRONIOBOIExecution execution = getPersistenceModule().get(executionId);
		return execution;
	}
	
	@Override
	public CRONIOBOIExecution addExecution(CRONIOBOIExecution execution) {
		CRONIOBOExecution executionJPA = (CRONIOBOExecution) execution;
		CRONIOBOIProject project = execution.getProject();
		if (project != null && !AEMFTCommonUtils.isNullLong(project.getProjectId())) {
			project = getProjectPersistence().getProject(project.getProjectId());
			execution.setProject(project);
		}
		
		executionJPA = getPersistenceModule().create(executionJPA);
		return executionJPA;
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
