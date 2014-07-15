package com.imotion.dslam.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.bom.CRONIOBOIPreferencesDataConstants;
import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIVariable;
import com.imotion.dslam.bom.data.CRONIOBOMachineProperties;
import com.imotion.dslam.bom.data.DSLAMBOFile;
import com.imotion.dslam.business.service.CRONIOBUIPreferencesBusinessService;
import com.imotion.dslam.business.service.CRONIOBUIPreferencesBusinessServiceConstants;
import com.imotion.dslam.business.service.CRONIOBUIPreferencesBusinessServiceTrace;
import com.imotion.dslam.business.service.base.DSLAMBUServiceBase;
import com.imotion.dslam.business.service.utils.CRONIOBUMetadataToBomConversor;
import com.imotion.dslam.business.service.utils.DSLAMBUBomToMetadataConversor;
import com.selene.arch.base.bom.AEMFTILoginDataConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;

public class CRONIOBUPreferencesBusinessServiceImpl extends DSLAMBUServiceBase implements CRONIOBUIPreferencesBusinessService, CRONIOBUIPreferencesBusinessServiceConstants, CRONIOBUIPreferencesBusinessServiceTrace {

	private static final long serialVersionUID = -5226864913741297047L;

	@Override
	public void getPreferences() {
		//ContextIn
		AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();
		String userId			= getElementDataController().getElementAsString(AEMFTILoginDataConstants.USER_ID		, contextIn);
		long preferencesId   	= CRONIOBOIPreferencesDataConstants.PREFERENCES_DEFAULT_ID;
		String preferencesIdStr = String.valueOf(preferencesId);
		
		if (AEMFTCommonUtilsBase.isEmptyString(preferencesIdStr) ) {
			traceNullParameter(METHOD_GET_PREFERENCES, CRONIOBOIPreferencesDataConstants.PREFERENCES_ID);
		} else {

			CRONIOBOIPreferences 	preferences = getPreferencesPersistence().getPreferences(preferencesId);

			//ContextOut
			AEMFTMetadataElementComposite preferencesData = DSLAMBUBomToMetadataConversor.fromPreferences(preferences);
			AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
			contextOut.addElement(PREFERENCES_DATA, preferencesData);
		}
	}

	@Override
	public void addConnection() {
		//ContextIn
		AEMFTMetadataElementComposite 	contextIn 		= getContext().getContextDataIN();
		String 							connectionName	= getElementDataController().getElementAsString(CRONIOBOIMachineProperties.MACHINE_NAME			, contextIn);
		Date 							creationTime 	= new Date();

		//Debemos conseguir el preferences Id del usuario, de momento puesto a pelo
		long preferencesId = 1L;

		//InitScript
		DSLAMBOIFile connectionScript = new DSLAMBOFile();
		connectionScript.setSavedTime(creationTime);
		connectionScript.setCreationTime(creationTime);
		connectionScript.setFilename(CRONIOBOIMachineProperties.CONNECTION_SCRIPT_DEFAULT_NAME);

		//disconnectionScript
		DSLAMBOIFile disconnectionScript = new DSLAMBOFile();
		disconnectionScript.setSavedTime(creationTime);
		disconnectionScript.setCreationTime(creationTime);
		disconnectionScript.setFilename(CRONIOBOIMachineProperties.DISCONNECTION_SCRIPT_DEFAULT_NAME);

		//VariableList
		List<DSLAMBOIVariable> variableList = new ArrayList<>();

		//MachineProperties
		CRONIOBOIMachineProperties machineProperties = new CRONIOBOMachineProperties();
		machineProperties.setMachineName(connectionName);
		machineProperties.setInitConnectionScript(connectionScript);
		machineProperties.setCloseConnectionScript(disconnectionScript);
		machineProperties.setConnectionVariables(variableList);
		machineProperties.setCreationTime(creationTime);
		machineProperties.setSaveTime(creationTime);
		machineProperties = getMachinePropertiesPersistence().addMachineProperties(machineProperties, preferencesId);

		//init-trace
		traceNewItemPersistent(METHOD_ADD_CONNECTION, CRONIOBOIMachineProperties.class.getSimpleName(), machineProperties.getMachineName());
		//end-trace

		//ContextOut
		AEMFTMetadataElementComposite connectionDataElement = DSLAMBUBomToMetadataConversor.fromMachineProperties(machineProperties);
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(CONNECTION_DATA, connectionDataElement);
	}
	
	@Override
	public void updateMachineConfig() {
		//ContextIn
		AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();	
		
		String machineName = getElementDataController().getElementAsString(CRONIOBOIMachineProperties.MACHINE_NAME, contextIn);
		CRONIOBOIMachineProperties currentMachineDB = getMachinePropertiesPersistence().getMachineProperties(CRONIOBOIPreferencesDataConstants.PREFERENCES_DEFAULT_ID, machineName);
		CRONIOBOIMachineProperties machineConfig = CRONIOBUMetadataToBomConversor.fromMachineConfigData(contextIn);
		long currentMachineDBId = currentMachineDB.getMachinePropertiesId();
		//preferencesId sustituir cuando usuarios
		getMachinePropertiesPersistence().updateMachineProperties(currentMachineDBId, machineConfig);
		
		//ContextOut
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
//		contextOut.addElement(PROJECT_DATA, machineConfigData);
	}
//	@Override
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

//		private AEMFTMetadataElementComposite updateMachineConfigFull(AEMFTMetadataElementComposite machineData) {
//		
////			//MainScript
////			DSLAMBOIFile mainScript = project.getMainScript();
////			mainScript = getFilePersistence().updateFileContent(mainScript.getFileId(), mainScript.getContent());
////			project.setMainScript(mainScript);
////	
////			//RollbackScript
////			DSLAMBOIFile rollbackScript = project.getRollBackScript();
////			rollbackScript = getFilePersistence().updateFileContent(rollbackScript.getFileId(), rollbackScript.getContent());
////			project.setRollBackScript(rollbackScript);
////	
////			//Process
////			DSLAMBOIProcess process = project.getProcess();
////			process = getProcessPersistence().updateProcess(process.getProcessId(), process);
////			project.setProcess(process);
////	
////			getProjectPersistence().updateProject(project.getProjectId(), project);
////			AEMFTMetadataElementComposite projectDataElement = DSLAMBUBomToMetadataConversor.fromProjectFull(project);
//	
//			return machineConfigData;
//		}

}
