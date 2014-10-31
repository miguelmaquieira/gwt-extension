package com.imotion.dslam.business.service.impl;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imotion.dslam.bom.CRONIOBOIFile;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.CRONIOBOINodeList;
import com.imotion.dslam.bom.CRONIOBOINodeListDataConstants;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.bom.CRONIOBOIProcess;
import com.imotion.dslam.bom.CRONIOBOIProject;
import com.imotion.dslam.bom.CRONIOBOIProjectDataConstants;
import com.imotion.dslam.bom.CRONIOBOIUser;
import com.imotion.dslam.bom.data.CRONIOBOFile;
import com.imotion.dslam.bom.data.CRONIOBONodeList;
import com.imotion.dslam.bom.data.CRONIOBOProcess;
import com.imotion.dslam.bom.data.CRONIOBOProject;
import com.imotion.dslam.business.service.CRONIOBUIProjectBusinessService;
import com.imotion.dslam.business.service.CRONIOBUIProjectBusinessServiceConstants;
import com.imotion.dslam.business.service.CRONIOBUIProjectBusinessServiceTrace;
import com.imotion.dslam.business.service.base.CRONIOBUServiceBase;
import com.imotion.dslam.business.service.utils.CRONIOBUBomToMetadataConversor;
import com.imotion.dslam.business.service.utils.CRONIOBUCSVToBomConversor;
import com.imotion.dslam.business.service.utils.CRONIOBUMetadataToBomConversor;
import com.selene.arch.base.bom.AEMFTILoginDataConstants;
import com.selene.arch.base.exe.bus.comm.AEMFTIFileUploadServerCommConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.composite.AEMFTMetadataElementCompositeRecordSet;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.core.appli.metadata.element.factory.AEMFTMetadataElementReflectionBasedFactory;
import com.selene.arch.exe.core.envi.io.AEMFTIIOConstant;

public class CRONIOBUProjectBusinessServiceImpl extends CRONIOBUServiceBase implements CRONIOBUIProjectBusinessService, CRONIOBUIProjectBusinessServiceConstants, CRONIOBUIProjectBusinessServiceTrace {

	private static final long serialVersionUID = -3287573002047034251L;
	
	@Override
	public void addProject() {
		//ContextIn
		AEMFTMetadataElementComposite 	contextIn 		= getContext().getContextDataIN();
		String 							projectName		= getElementDataController().getElementAsString(CRONIOBOIProjectDataConstants.PROJECT_NAME			, contextIn);
		int 							machineType		= getElementDataController().getElementAsInt(CRONIOBOIProjectDataConstants.PROJECT_MACHINE_TYPE		, contextIn);
		Date 							creationTime 	= new Date();
		
		String 							userIdStr		= getElementDataController().getElementAsString(CRONIOBOIUser.USER_ID			, contextIn);
		long							userId			= AEMFTCommonUtilsBase.getLongFromString(userIdStr);
		
		//MainScript
		CRONIOBOIFile mainScript = new CRONIOBOFile();
		mainScript.setContentType(machineType);
		mainScript.setSavedTime(creationTime);
		mainScript.setCreationTime(creationTime);
		mainScript.setFilename(CRONIOBOIProject.PROJECT_MAIN_SCRIPT_DEFAULT_NAME);
		
		//RollbackScript
		CRONIOBOIFile rollBackScript = new CRONIOBOFile();
		rollBackScript.setContentType(machineType);
		rollBackScript.setSavedTime(creationTime);
		rollBackScript.setCreationTime(creationTime);
		rollBackScript.setFilename(CRONIOBOIProject.PROJECT_ROLLBACK_SCRIPT_DEFAULT_NAME);
		
		//Process
		CRONIOBOIProcess process = new CRONIOBOProcess();
		process.setProcessName(CRONIOBOIProject.PROJECT_PROCESS_DEFAULT_NAME);
		process.setCreationTime(creationTime);
		process.setSavedTime(creationTime);

		//Project
		CRONIOBOIProject project = new CRONIOBOProject();
		project.setProjectName(projectName);
		project.setMachineType(machineType);
		project.setMainScript(mainScript);
		project.setRollBackScript(rollBackScript);
		project.setProcess(process);
		project.setCreationTime(creationTime);
		project.setSavedTime(creationTime);
		project = getProjectPersistence().addProject(project);
		
		getUserPersistence().addProjectToUser(userId, project.getProjectId());

		//init-trace
		traceNewItemPersistent(METHOD_ADD_PROJECT, CRONIOBOIProject.class.getSimpleName(), String.valueOf(project.getProjectId()));
		//end-trace

		//ContextOut
		AEMFTMetadataElementComposite projectDataElement = CRONIOBUBomToMetadataConversor.fromProjectFull(project, getSession().getCurrentLocale());
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(PROJECT_DATA, projectDataElement);
	}

	@Override
	public void updateProject() {
		//ContextIn
		AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();

		AEMFTMetadataElementComposite projectData = updateProject(contextIn);

		//ContextOut
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(PROJECT_DATA, projectData);
	}

	@Override
	public void updateProjects() {
		//ContextIn
		AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();
		AEMFTMetadataElementComposite projectsData = getElementDataController().getElementAsComposite(PROJECTS_DATA, contextIn);
		List<AEMFTMetadataElement> projectsDataList = projectsData.getElementList();
		
		AEMFTMetadataElementComposite updatedProjectsData = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		for (AEMFTMetadataElement projectData : projectsDataList) {
			AEMFTMetadataElementComposite updatedProjectData = updateProject((AEMFTMetadataElementComposite) projectData);
			String projectId = getElementDataController().getElementAsString(CRONIOBOIProjectDataConstants.PROJECT_ID, updatedProjectData);
			updatedProjectsData.addElement(projectId, updatedProjectData);
		}
		
		//ContextOut
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(PROJECTS_DATA, updatedProjectsData);
	}

	@Override
	public void removeProject() {
		//ContextIn
		AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();
		String projectId		= getElementDataController().getElementAsString(CRONIOBOIProjectDataConstants.PROJECT_ID		, contextIn);

		Long projectIdAsLong 	= AEMFTCommonUtilsBase.getLongFromString(projectId);
		getProjectPersistence().removeProject(projectIdAsLong);

		//init-trace
		traceItemRemovedFromPersistence(METHOD_REMOVE_PROJECT, CRONIOBOIProject.class.getSimpleName(), projectId);
		//end-trace

		//ContextOut
		AEMFTMetadataElementComposite projectDataElement = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		projectDataElement.addElement(CRONIOBOIProjectDataConstants.PROJECT_ID, projectIdAsLong);

		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(PROJECT_DATA, projectDataElement);
	}


	@Override
	public void getAllProjectsByUser() {
		//ContextIn
		AEMFTMetadataElementComposite 	contextIn 	= getContext().getContextDataIN();
		String 							userId 		= getElementDataController().getElementAsString(AEMFTILoginDataConstants.USER_ID, contextIn);
		long 							userIdLong 	= Long.valueOf(userId);
		CRONIOBOIUser 					user 		= getUserPersistence().getUserById(userIdLong);
		List<CRONIOBOIProject> 			projectList = user.getProjectList();
		
		//trace-init
		int resultsNumber = 0;
		if (!AEMFTCommonUtilsBase.isEmptyList(projectList)) {
			resultsNumber = projectList.size();
		}
		traceNumberOfResults(METHOD_GET_ALL_PROJECTS_BY_USER, CRONIOBOIProject.class.getSimpleName(), resultsNumber);
		//end-trace

		//ContextOut
		AEMFTMetadataElementComposite projectDataElement = CRONIOBUBomToMetadataConversor.fromProjectList(projectList, getSession().getCurrentLocale());
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(PROJECT_DATA_LIST, projectDataElement);
	}

	@Override
	public void getCsvNodes() {
		AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();
		byte[] 	fileByte 	= (byte[]) getElementDataController().getElementAsSerializable(AEMFTIFileUploadServerCommConstants.CTE_MFT_AE_BUS_COMM_FILE_DATA, contextIn);
		String 	fileString 	= new String(fileByte, Charset.forName(AEMFTIIOConstant.CTE_MFT_AE_CORE_ENTO_IO_DEFAULT_ENCODING));

		List<CRONIOBOINode> nodeList = CRONIOBUCSVToBomConversor.convertCsvToNode(fileString,";");

		//trace-init
		int resultsNumber = 0;
		if (!AEMFTCommonUtilsBase.isEmptyList(nodeList)) {
			resultsNumber = nodeList.size();
		}
		traceNumberOfResults(METHOD_GET_CSV_NODES, CRONIOBOINode.class.getSimpleName(), resultsNumber);
		//end-trace

		//ContextOut
		
		AEMFTMetadataElementComposite nodesData = CRONIOBUBomToMetadataConversor.fromNodesCSVList(nodeList);
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(NODES_DATA_LIST, nodesData);
	}
	
	@Override
	public void addNodeList() {
		//ContextIn
		AEMFTMetadataElementComposite 	contextIn 			= getContext().getContextDataIN();
		String 							nodeListName		= getElementDataController().getElementAsString(CRONIOBOINodeListDataConstants.NODELIST_NAME	, contextIn);
		String 							currentProjectIdStr	= getElementDataController().getElementAsString(CRONIOBOIProjectDataConstants.PROJECT_ID		, contextIn);
		long							currentProjectId	= AEMFTCommonUtilsBase.getLongFromString(currentProjectIdStr);
		Date 							creationTime 		= new Date();
		boolean							error				= false;
		
		CRONIOBOIProject project = getProjectPersistence().getProject(currentProjectId);
		CRONIOBOIProcess process = project.getProcess();
		
		List<CRONIOBOINodeList> nodeLists = process.getListNodeList();
		if (!AEMFTCommonUtilsBase.isEmptyList(nodeLists)) {
			for (CRONIOBOINodeList nodeList : nodeLists) {
				if (nodeListName.equals(nodeList.getNodeListName())){
					error = true;
				}
			}
		}
		
		if (error == true) {
			AEMFTMetadataElementCompositeRecordSet errorData = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			errorData.addElement(CRONIOBOINodeList.NODELIST_NAME, nodeListName);
			errorData.addElement(CRONIOBOIProjectDataConstants.PROJECT_ID, currentProjectId);
			getContext().getContextOUT().addElement(CRONIOBUIProjectBusinessServiceConstants.KEY_EXIST_ERRORS,errorData);
		} else {
			//nodeList
			CRONIOBOINodeList nodeList = new CRONIOBONodeList();
			nodeList.setNodeListName(nodeListName);
			nodeList.setProcess(process);
			nodeList.setSavedTime(creationTime);
			nodeList.setCreationTime(creationTime);
			
			long processId = process.getProcessId();
			nodeList 	= getNodeListPersistence().addNodeList(nodeList,processId);
			
			getProcessPersistence().addNodeListUpdateProcess(processId, nodeList);
			
			//init-trace
			traceNewItemPersistent(METHOD_ADD_NODELIST, CRONIOBOINodeList.class.getSimpleName(), String.valueOf(nodeList.getNodeListId()));
			//end-trace

			//ContextOut
			AEMFTMetadataElementComposite nodeListDataElement = CRONIOBUBomToMetadataConversor.fromNodeList(nodeList);
			AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
			contextOut.addElement(NODELIST_DATA, nodeListDataElement);
			contextOut.addElement(CRONIOBOIProjectDataConstants.PROJECT_ID, currentProjectIdStr);
		}
	}
	
	@Override
	public void getAllNodeListsByProjectId() {
		//ContextIn
		AEMFTMetadataElementComposite 	contextIn 		= getContext().getContextDataIN();
		int 							resultsNumber 	= 0;
		Long 							projectIdAsLong = null;
				
		List<AEMFTMetadataElement> projectList = contextIn.getSortedElementList();
		List<String> projectIdList = new ArrayList<>();
		for (AEMFTMetadataElement project : projectList) {
			String projectId = project.getKey();
			projectIdList.add(projectId);
		}
		
		AEMFTMetadataElementComposite nodeListsData = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		
		for (String projectId : projectIdList) {
			projectIdAsLong = Long.valueOf(projectId).longValue();
			List<CRONIOBOINodeList> listNodeList = getNodeListPersistence().getAllNodeListsByProject(projectIdAsLong);
			if (!AEMFTCommonUtilsBase.isEmptyList(listNodeList)) {
				resultsNumber = resultsNumber + listNodeList.size();
				AEMFTMetadataElementComposite nodeListData = CRONIOBUBomToMetadataConversor.fromListNodeList(listNodeList);
				nodeListsData.addElement(projectId, nodeListData);
			}
		}
		
		//init-trace
		traceNumberOfResults(METHOD_GET_ALL_NODELISTS_BY_PROJECT_ID, CRONIOBOINodeList.class.getSimpleName(), resultsNumber);
		//end-trace

		//ContextOut
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(LIST_NODELIST_DATA, nodeListsData);
		
	}

	/**
	 * PRIVATE
	 */

	private AEMFTMetadataElementComposite updateProject(AEMFTMetadataElementComposite projectData) {
		String					userIdStr		= getElementDataController().getElementAsString(CRONIOBOIUser.USER_ID			, projectData);
		List<String>			modifyNodeLists	= (List<String>) getElementDataController().getElementAsSerializable(CRONIOBOINodeList.MODIFY_NODELISTS		, projectData);
		long					userId			= AEMFTCommonUtilsBase.getLongFromString(userIdStr);
		CRONIOBOIUser 			user 			= getUserPersistence().getUserById(userId);
		CRONIOBOIPreferences 	userPreferences = user.getPreferences();
		long					preferencesId	= userPreferences.getPreferencesId();
		
		CRONIOBOIProject project = CRONIOBUMetadataToBomConversor.fromProjectData(projectData);
		Date date = new Date();

		//MainScript
		CRONIOBOIFile mainScript 			= project.getMainScript();
		mainScript = addCompiledCode(mainScript, date);
		project.setMainScript(mainScript);

		//RollbackScript
		CRONIOBOIFile rollbackScript = project.getRollBackScript();
		rollbackScript = addCompiledCode(rollbackScript, date);
		project.setRollBackScript(rollbackScript);

		//Process
		CRONIOBOIProcess process = project.getProcess();
		process = getProcessPersistence().updateProcess(process.getProcessId(), process, preferencesId, modifyNodeLists, date);
		project.setProcess(process);
		project.setSavedTime(date);

		getProjectPersistence().updateProject(project.getProjectId(), project);
		AEMFTMetadataElementComposite projectDataElement = CRONIOBUBomToMetadataConversor.fromProjectFull(project, getSession().getCurrentLocale());

		return projectDataElement;
	}

}
