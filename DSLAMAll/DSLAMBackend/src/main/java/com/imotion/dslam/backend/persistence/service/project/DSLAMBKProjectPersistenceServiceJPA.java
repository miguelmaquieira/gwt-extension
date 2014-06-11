package com.imotion.dslam.backend.persistence.service.project;

import java.util.Date;
import java.util.List;

import com.imotion.dslam.backend.persistence.jpa.DSLAMBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIProcess;
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
	public DSLAMBOIProject updateProjectName(Long projectId, String projectName) {
		DSLAMBOProject project = getPersistenceModule().get(projectId);
		project.setProjectName(projectName);
		project.setSavedTime(new Date());
		project = getPersistenceModule().update(project);
		return project;
	}
	
	@Override
	public DSLAMBOIProject updateMachineType(Long projectId, int machineType) {
		DSLAMBOProject project = getPersistenceModule().get(projectId);
		project.setMachineType(machineType);
		project.setSavedTime(new Date());
		project = getPersistenceModule().update(project);
		return project;
	}
	
	@Override
	public DSLAMBOIProject updateProjectMainScript(Long projectId, Long projectMainScriptAsLong) {
		DSLAMBOProject 	project = getPersistenceModule().get(projectId);
		DSLAMBOIFile 	mainScript 	= getFilePersistence().getFile(projectMainScriptAsLong);
		project.setMainScript(mainScript);
		project.setSavedTime(new Date());
		project = getPersistenceModule().update(project);
		return project;
	}
	
	@Override
	public DSLAMBOIProject updateProjectRollBackScript(Long projectId, Long projectRollBackScriptAsLong) {
		DSLAMBOProject 	project 		= getPersistenceModule().get(projectId);
		DSLAMBOIFile 	rollBackScript 	= getFilePersistence().getFile(projectRollBackScriptAsLong);
		project.setRollBackScript(rollBackScript);
		project.setSavedTime(new Date());
		project = getPersistenceModule().update(project);
		return project;
	}
	
	@Override
	public DSLAMBOIProject updateProjectProcess(Long projectId, Long processIdAsLong) {
		DSLAMBOProject 	project 		= getPersistenceModule().get(projectId);
		DSLAMBOIProcess process 		= getProcessPersistence().getProcess(processIdAsLong);
		project.setProcess(process);
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
