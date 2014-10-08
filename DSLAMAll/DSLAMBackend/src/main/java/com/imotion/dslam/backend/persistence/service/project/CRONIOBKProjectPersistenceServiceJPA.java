package com.imotion.dslam.backend.persistence.service.project;

import java.util.Date;
import java.util.List;

import com.imotion.dslam.backend.persistence.jpa.CRONIOBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.CRONIOBOIProject;
import com.imotion.dslam.bom.data.CRONIOBOProject;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;

public class CRONIOBKProjectPersistenceServiceJPA extends CRONIOBKPersistenceServiceBaseJPA<CRONIOBOIProject, CRONIOBOProject, Long> implements CRONIOBKIProjectPersistenceService {

	private static final long serialVersionUID = -1754757807591412638L;

	@Override
	public CRONIOBOIProject getProject(long projectId) {
		CRONIOBOIProject project = getPersistenceModule().get(projectId);
		return project;
	}
	
	@Override
	public CRONIOBOIProject addProject(CRONIOBOIProject project) {
		CRONIOBOProject projectJPA = (CRONIOBOProject) project;
		projectJPA = getPersistenceModule().create(projectJPA);
		return projectJPA;
	}
	
	@Override
	public CRONIOBOIProject updateProject(Long projectId, CRONIOBOIProject updatedProject) {
		CRONIOBOProject originalProject = getPersistenceModule().get(projectId);
		if (originalProject != null) {
			originalProject.setSavedTime(new Date());
			getPersistenceModule().update(originalProject);
		}
		return originalProject;
	}

	@Override
	public CRONIOBOIProject updateProjectName(Long projectId, String projectName) {
		CRONIOBOProject project = getPersistenceModule().get(projectId);
		project.setProjectName(projectName);
		project.setSavedTime(new Date());
		project = getPersistenceModule().update(project);
		return project;
	}
	
	@Override
	public List<CRONIOBOIProject> getAllProjects() {
		List<CRONIOBOProject> projectListJpa = getPersistenceModule().findAll();
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
	public Class<CRONIOBOProject> getPersistenceClass() {
		return CRONIOBOProject.class;
	}

}
