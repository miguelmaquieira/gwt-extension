package com.imotion.dslam.backend.persistence.service.project;

import java.util.Date;
import java.util.List;

import com.imotion.dslam.backend.persistence.jpa.DSLAMBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.bom.data.DSLAMBOProject;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;

public class DSLAMBKProjectPersistenceServiceJPA extends DSLAMBKPersistenceServiceBaseJPA<DSLAMBOIProject, DSLAMBOProject, Long> implements DSLAMBKIProjectPersistenceService {

	private static final long serialVersionUID = -1754757807591412638L;

	@Override
	public DSLAMBOIProject addProject(DSLAMBOIProject project) {
		DSLAMBOProject projectJPA = (DSLAMBOProject) project;
		projectJPA = getPersistenceModule().create(projectJPA);
		return projectJPA;
	}
	
	@Override
	public DSLAMBOIProject updateProject(Long projectId, DSLAMBOIProject updatedProject) {
		DSLAMBOProject originalProject = getPersistenceModule().get(projectId);
		if (originalProject != null) {
			originalProject.setSavedTime(new Date());
			getPersistenceModule().update(originalProject);
		}
		return originalProject;
	}

	@Override
	public DSLAMBOIProject updateProjectName(Long projectId, String projectName) {
		DSLAMBOProject project = getPersistenceModule().get(projectId);
		project.setProjectName(projectName);
		project.setSavedTime(new Date());
		project = getPersistenceModule().update(project);
		return project;
	}
	
	@Override
	public List<DSLAMBOIProject> getAllProjects() {
		List<DSLAMBOProject> projectListJpa = getPersistenceModule().findAll();
		return AEMFTCommonUtilsBase.castList(projectListJpa);
	}
	
	@Override
	public void removeProject(Long projectIdAsLong) {
		getPersistenceModule().remove(projectIdAsLong);
	}

	/**
	 * AEMFTIHasPersistenceModule
	 */
	@Override
	public Class<DSLAMBOProject> getPersistenceClass() {
		return DSLAMBOProject.class;
	}

}
