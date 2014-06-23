package com.imotion.dslam.backend.persistence.service.project;

import java.util.List;

import com.imotion.dslam.bom.DSLAMBOIProject;

public interface DSLAMBKIProjectPersistenceService {
	
	DSLAMBOIProject addProject(DSLAMBOIProject project);
	
	DSLAMBOIProject updateProject(Long projectId, DSLAMBOIProject updatedProject);

	DSLAMBOIProject updateProjectName(Long projectId, String projectName);
	
	List<DSLAMBOIProject> getAllProjects();

	void removeProject(Long projectIdAsLong);

}
