package com.imotion.dslam.backend.persistence.service.project;

import java.util.List;

import com.imotion.dslam.bom.CRONIOBOIProject;

public interface CRONIOBKIProjectPersistenceService {
	
	CRONIOBOIProject getProject(long projectId);
	
	CRONIOBOIProject addProject(CRONIOBOIProject project);
	
	CRONIOBOIProject updateProject(Long projectId, CRONIOBOIProject updatedProject);

	CRONIOBOIProject updateProjectName(Long projectId, String projectName);
	
	List<CRONIOBOIProject> getAllProjects();

	void removeProject(Long projectIdAsLong);

}
