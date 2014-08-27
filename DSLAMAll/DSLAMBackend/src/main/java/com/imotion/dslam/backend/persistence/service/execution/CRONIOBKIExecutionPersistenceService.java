package com.imotion.dslam.backend.persistence.service.execution;

import java.util.List;

import com.imotion.dslam.bom.CRONIOBOIExecution;

public interface CRONIOBKIExecutionPersistenceService {
	
	CRONIOBOIExecution getExecution(long executionId);
	
	CRONIOBOIExecution addExecution(CRONIOBOIExecution execution);
	
	List<CRONIOBOIExecution> getAllExecutionsByProject(long projectId);

	void removeExecution(Long executionIdAsLong);

}
