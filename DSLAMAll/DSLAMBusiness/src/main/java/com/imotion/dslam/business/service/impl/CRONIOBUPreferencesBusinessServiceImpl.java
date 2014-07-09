package com.imotion.dslam.business.service.impl;

import com.imotion.dslam.business.service.CRONIOBUIPreferencesBusinessService;
import com.imotion.dslam.business.service.CRONIOBUIPreferencesBusinessServiceConstants;
import com.imotion.dslam.business.service.CRONIOBUIPreferencesBusinessServiceTrace;
import com.imotion.dslam.business.service.base.DSLAMBUServiceBase;

public class CRONIOBUPreferencesBusinessServiceImpl extends DSLAMBUServiceBase implements CRONIOBUIPreferencesBusinessService, CRONIOBUIPreferencesBusinessServiceConstants, CRONIOBUIPreferencesBusinessServiceTrace {

	private static final long serialVersionUID = -5226864913741297047L;

	@Override
	public void addPreferences() {
//		//ContextIn
//		AEMFTMetadataElementComposite 	contextIn 		= getContext().getContextDataIN();
//		String 							projectName		= getElementDataController().getElementAsString(CRONIOBOIProjectDataConstants.PROJECT_NAME			, contextIn);
//		int 							machineType		= getElementDataController().getElementAsInt(CRONIOBOIProjectDataConstants.PROJECT_MACHINE_TYPE	, contextIn);
//		Date 							creationTime 	= new Date();
//		
//		//MainScript
//		DSLAMBOIFile mainScript = new DSLAMBOFile();
//		mainScript.setContentType(machineType);
//		mainScript.setSavedTime(creationTime);
//		mainScript.setCreationTime(creationTime);
//		mainScript.setFilename(DSLAMBOIProject.PROJECT_MAIN_SCRIPT_DEFAULT_NAME);
//		
//		//RollbackScript
//		DSLAMBOIFile rollBackScript = new DSLAMBOFile();
//		rollBackScript.setContentType(machineType);
//		rollBackScript.setSavedTime(creationTime);
//		rollBackScript.setCreationTime(creationTime);
//		rollBackScript.setFilename(DSLAMBOIProject.PROJECT_ROLLBACK_SCRIPT_DEFAULT_NAME);
//		
//		//Process
//		DSLAMBOIProcess process = new DSLAMBOProcess();
//		process.setProcessName(DSLAMBOIProject.PROJECT_PROCESS_DEFAULT_NAME);
//		process.setCreationTime(creationTime);
//		process.setSavedTime(creationTime);
//
//		//Project
//		DSLAMBOIProject project = new DSLAMBOProject();
//		project.setProjectName(projectName);
//		project.setMachineType(machineType);
//		project.setMainScript(mainScript);
//		project.setRollBackScript(rollBackScript);
//		project.setProcess(process);
//		project.setCreationTime(creationTime);
//		project.setSavedTime(creationTime);
//		project = getProjectPersistence().addProject(project);
//
//		//init-trace
//		traceNewItemPersistent(METHOD_ADD_PROJECT, DSLAMBOIProject.class.getSimpleName(), String.valueOf(project.getProjectId()));
//		//end-trace
//
//		//ContextOut
//		AEMFTMetadataElementComposite projectDataElement = DSLAMBUBomToMetadataConversor.fromProjectFull(project);
//		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
//		contextOut.addElement(PROJECT_DATA, projectDataElement);
	}

	@Override
	public void updatePreferences() {
//		//ContextIn
//		AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();	
//
//		AEMFTMetadataElementComposite projectData = updateProject(contextIn);
//
//		//ContextOut
//		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
//		contextOut.addElement(PROJECT_DATA, projectData);
	}

//	@Override
//	public void updateProjects() {
//		//ContextIn
//		AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();
//		AEMFTMetadataElementComposite projectsData = getElementDataController().getElementAsComposite(PROJECTS_DATA, contextIn);
//		List<AEMFTMetadataElement> projectsDataList = projectsData.getElementList();
//		
//		AEMFTMetadataElementComposite updatedProjectsData = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
//		for (AEMFTMetadataElement projectData : projectsDataList) {
//			AEMFTMetadataElementComposite updatedProjectData = updateProject((AEMFTMetadataElementComposite) projectData);
//			String projectId = getElementDataController().getElementAsString(CRONIOBOIProjectDataConstants.PROJECT_ID, updatedProjectData);
//			updatedProjectsData.addElement(projectId, updatedProjectData);
//		}
//		
//		//ContextOut
//		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
//		contextOut.addElement(PROJECTS_DATA, updatedProjectsData);
//	}

	@Override
	public void removePreferences() {
//		//ContextIn
//		AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();
//		String projectId		= getElementDataController().getElementAsString(CRONIOBOIProjectDataConstants.PROJECT_ID		, contextIn);
//
//		Long projectIdAsLong 	= AEMFTCommonUtilsBase.getLongFromString(projectId);
//		getProjectPersistence().removeProject(projectIdAsLong);
//
//		//init-trace
//		traceItemRemovedFromPersistence(METHOD_REMOVE_PROJECT, DSLAMBOIProject.class.getSimpleName(), projectId);
//		//end-trace
//
//		//ContextOut
//		AEMFTMetadataElementComposite projectDataElement = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
//		projectDataElement.addElement(CRONIOBOIProjectDataConstants.PROJECT_ID, projectIdAsLong);
//
//		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
//		contextOut.addElement(PROJECT_DATA, projectDataElement);
	}


//	@Override
//	public void getAllProjects() {
//		List<DSLAMBOIProject> 	projectList = getProjectPersistence().getAllProjects();
//
//		//trace-init
//		int resultsNumber = 0;
//		if (!AEMFTCommonUtilsBase.isEmptyList(projectList)) {
//			resultsNumber = projectList.size();
//		}
//		traceNumberOfResults(METHOD_GET_ALL_PROJECTS, DSLAMBOIProject.class.getSimpleName(), resultsNumber);
//		//end-trace
//
//		//ContextOut
//		AEMFTMetadataElementComposite projectDataElement = DSLAMBUBomToMetadataConversor.fromProjectList(projectList);
//		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
//		contextOut.addElement(PROJECT_DATA_LIST, projectDataElement);
//	}
//
//	@Override
//	public void getCsvNodes() {
//		AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();
//		byte[] 	fileByte 	= (byte[]) getElementDataController().getElementAsSerializable(AEMFTIFileUploadServerCommConstants.CTE_MFT_AE_BUS_COMM_FILE_DATA, contextIn);
//		String 	fileString 	= new String(fileByte, Charset.forName(AEMFTIIOConstant.CTE_MFT_AE_CORE_ENTO_IO_DEFAULT_ENCODING));
//
//		List<CRONIOBOINode> nodeList = CRONIOBUCSVToBomConversor.convertCsvToNode(fileString,";");
//
//		//trace-init
//		int resultsNumber = 0;
//		if (!AEMFTCommonUtilsBase.isEmptyList(nodeList)) {
//			resultsNumber = nodeList.size();
//		}
//		traceNumberOfResults(METHOD_GET_CSV_NODES, CRONIOBOINode.class.getSimpleName(), resultsNumber);
//		//end-trace
//
//		//ContextOut
//		AEMFTMetadataElementComposite nodesData = DSLAMBUBomToMetadataConversor.fromNodeList(nodeList);
//		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
//		contextOut.addElement(NODES_DATA_LIST, nodesData);
//	}

	
	/**
	 * PRIVATE
	 */

//	private AEMFTMetadataElementComposite updateProject(AEMFTMetadataElementComposite projectData) {
//		DSLAMBOIProject project = CRONIOMetadataToBom.fromProjectData(projectData);
//
//		//MainScript
//		DSLAMBOIFile mainScript = project.getMainScript();
//		mainScript = getFilePersistence().updateFileContent(mainScript.getFileId(), mainScript.getContent());
//		project.setMainScript(mainScript);
//
//		//RollbackScript
//		DSLAMBOIFile rollbackScript = project.getRollBackScript();
//		rollbackScript = getFilePersistence().updateFileContent(rollbackScript.getFileId(), rollbackScript.getContent());
//		project.setRollBackScript(rollbackScript);
//
//		//Process
//		DSLAMBOIProcess process = project.getProcess();
//		process = getProcessPersistence().updateProcess(process.getProcessId(), process);
//		project.setProcess(process);
//
//		getProjectPersistence().updateProject(project.getProjectId(), project);
//		AEMFTMetadataElementComposite projectDataElement = DSLAMBUBomToMetadataConversor.fromProjectFull(project);
//
//		return projectDataElement;
//	}

}
