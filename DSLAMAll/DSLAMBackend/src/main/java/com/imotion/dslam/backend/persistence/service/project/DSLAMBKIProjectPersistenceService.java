package com.imotion.dslam.backend.persistence.service.project;

import java.util.List;

import com.imotion.dslam.bom.DSLAMBOIProject;

public interface DSLAMBKIProjectPersistenceService {

	DSLAMBOIProject addProject(DSLAMBOIProject project);

	DSLAMBOIProject updateProjectName(Long projectId, String projectName);
	
	DSLAMBOIProject updateMachineType(Long projectId, int machineType);
	
	DSLAMBOIProject updateProjectMainScript(Long projectId, Long projectMainScriptAsLong);
	
	DSLAMBOIProject updateProjectRollBackScript(Long projectId, Long projectRollBackScriptAsLong);
	
	DSLAMBOIProject updateProjectProcess(Long projectId, Long processIdAsLong);

	List<DSLAMBOIProject> getAllProjects();

	void removeProject(Long projectIdAsLong);

}
