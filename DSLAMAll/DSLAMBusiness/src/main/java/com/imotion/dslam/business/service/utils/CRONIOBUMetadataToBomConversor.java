package com.imotion.dslam.business.service.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.bom.CRONIOBOIPreferencesDataConstants;
import com.imotion.dslam.bom.CRONIOBOIProjectDataConstants;
import com.imotion.dslam.bom.CRONIOBOIUserPreferences;
import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.bom.DSLAMBOIVariable;
import com.imotion.dslam.bom.data.CRONIOBOMachineProperties;
import com.imotion.dslam.bom.data.CRONIOBONode;
import com.imotion.dslam.bom.data.CRONIOBOPreferences;
import com.imotion.dslam.bom.data.CRONIOBOUserPreferences;
import com.imotion.dslam.bom.data.DSLAMBOFile;
import com.imotion.dslam.bom.data.DSLAMBOProcess;
import com.imotion.dslam.bom.data.DSLAMBOProject;
import com.imotion.dslam.bom.data.DSLAMBOVariable;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTIMetadataElementController;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.controller.AEMFTMetadataElementControllerImpl;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;
import com.selene.arch.exe.core.common.AEMFTCommonUtils;

public class CRONIOBUMetadataToBomConversor {

	private static AEMFTIMetadataElementController elementController = null;

	public static DSLAMBOIProject fromProjectData(AEMFTMetadataElementComposite projectData) {
		DSLAMBOIProject	project = null;
		if (projectData != null) {
			project = new DSLAMBOProject();

			//project properties
			String	projectIdStr	= getElementController().getElementAsString(DSLAMBOIProject.PROJECT_ID					, projectData);
			long	projectId		= AEMFTCommonUtils.getLongFromString(projectIdStr);
			String	projectName		= getElementController().getElementAsString(DSLAMBOIProject.PROJECT_NAME				, projectData);
			int 	machineType		= getElementController().getElementAsInt(DSLAMBOIProject.PROJECT_MACHINE_TYPE			, projectData);
			Date	creationTime	= (Date) getElementController().getElementAsSerializable(DSLAMBOIProject.CREATION_TIME	, projectData);
			Date	savedTime		= (Date) getElementController().getElementAsSerializable(DSLAMBOIProject.SAVED_TIME		, projectData);

			project.setProjectId(projectId);
			project.setProjectName(projectName);
			project.setMachineType(machineType);
			project.setCreationTime(creationTime);
			project.setSavedTime(savedTime);

			//Main script
			AEMFTMetadataElementComposite mainScriptData = getElementController().getElementAsComposite(DSLAMBOIProject.PROJECT_MAIN_SCRIPT, projectData);
			DSLAMBOIFile mainScript = fromFileData(mainScriptData);
			project.setMainScript(mainScript);

			//Rollback script
			AEMFTMetadataElementComposite rollbackScriptData = getElementController().getElementAsComposite(DSLAMBOIProject.PROJECT_ROLLBACK_SCRIPT, projectData);
			DSLAMBOIFile rollbackScript = fromFileData(rollbackScriptData);
			project.setRollBackScript(rollbackScript);

			//Process data
			AEMFTMetadataElementComposite processData = getElementController().getElementAsComposite(DSLAMBOIProject.PROJECT_PROCESS, projectData);
			DSLAMBOIProcess process = fromProcessData(processData);
			project.setProcess(process);
		}
		return project;
	}

	public static DSLAMBOIFile fromFileData(AEMFTMetadataElementComposite fileData) {
		DSLAMBOIFile file = null;
		if (fileData != null) {
			file = new DSLAMBOFile();

			String 	fileIdStr 		= getElementController().getElementAsString(DSLAMBOIFile.FILE_ID	, fileData);
			Long	fileId			= AEMFTCommonUtils.getLongFromString(fileIdStr);
			String	fileName		= getElementController().getElementAsString(DSLAMBOIFile.FILE_NAME	, fileData);
			int		contentType		= getElementController().getElementAsInt(DSLAMBOIFile.CONTENT_TYPE	, fileData);
			String	content			= getElementController().getElementAsString(DSLAMBOIFile.CONTENT	, fileData);
			Date	creationTime	= (Date) getElementController().getElementAsSerializable(DSLAMBOIFile.CREATION_TIME	, fileData);
			Date	savedTime		= (Date) getElementController().getElementAsSerializable(DSLAMBOIFile.SAVED_TIME	, fileData);

			file.setFileId(fileId);
			file.setFilename(fileName);
			file.setContentType(contentType);
			file.setContent(content);
			file.setCreationTime(creationTime);
			file.setSavedTime(savedTime);

		}
		return file;
	}

	public static DSLAMBOIProcess fromProcessData(AEMFTMetadataElementComposite processData) {
		DSLAMBOIProcess process = null;
		if (processData != null) {
			process = new DSLAMBOProcess();

			//process properties
			String		processIdStr 	= getElementController().getElementAsString(DSLAMBOIProcess.PROCESS_ID					, processData);
			long		processId		= AEMFTCommonUtils.getLongFromString(processIdStr);
			String		processName		= getElementController().getElementAsString(DSLAMBOIProcess.PROCESS_NAME				, processData);
			Date		creationTime	= (Date) getElementController().getElementAsSerializable(DSLAMBOIProcess.CREATION_TIME	, processData);
			Date		savedTime		= (Date) getElementController().getElementAsSerializable(DSLAMBOIProcess.SAVED_TIME		, processData);
			process.setProcessId(processId);
			process.setProcessName(processName);
			process.setCreationTime(creationTime);
			process.setSavedTime(savedTime);
			
			//extra options
			AEMFTMetadataElementComposite extraOptions = getElementController().getElementAsComposite(DSLAMBOProcess.PROCESS_EXTRA_OPTIONS, processData);
			boolean synchronous = getElementController().getElementAsBoolean(DSLAMBOProcess.PROCESS_SYNC_OPTION, extraOptions);
			process.setSynchronous(synchronous);
			
			//process variables
			AEMFTMetadataElementComposite variablesData = getElementController().getElementAsComposite(DSLAMBOProcess.PROCESS_VARIABLE_LIST, processData);
			List<DSLAMBOIVariable> variableList = fromVariableDataList(variablesData);
			process.setVariableList(variableList);

			//process scheduleList
			AEMFTMetadataElementComposite scheduleDataList = getElementController().getElementAsComposite(DSLAMBOProcess.PROCESS_SCHEDULE_LIST, processData);
			List<Date> scheduleList = fromScheduleDataList(scheduleDataList);
			process.setScheduleList(scheduleList);

			//process nodeList
			AEMFTMetadataElementComposite nodeDataList = getElementController().getElementAsComposite(DSLAMBOProcess.PROCESS_NODE_LIST, processData);
			List<CRONIOBOINode> nodeList = fromNodeDataList(nodeDataList);
			process.setNodeList(nodeList);

		}
		return process;
	}

	private static List<CRONIOBOINode> fromNodeDataList(AEMFTMetadataElementComposite nodeDataList) {
		List<CRONIOBOINode> nodeList = null;
		if (nodeDataList != null) {
			nodeList =  new ArrayList<>();
			List<AEMFTMetadataElement> nodeDataElementList = nodeDataList.getElementList();
			for (AEMFTMetadataElement nodeDataElement: nodeDataElementList) {
				if (!DSLAMBOIProject.INFO.equals(nodeDataElement.getKey())) {
					CRONIOBOINode node = fromNodeData((AEMFTMetadataElementComposite) nodeDataElement);
					nodeList.add(node);
				}
			}
		}
		return nodeList;
	}

	private static CRONIOBOINode fromNodeData(AEMFTMetadataElementComposite nodeDataElement) {
		CRONIOBOINode node = null;
		if (nodeDataElement != null) {
			node = new CRONIOBONode();
			Date actualDate = new Date();

			String	nodeName		= getElementController().getElementAsString(CRONIOBOINode.NODE_NAME						, nodeDataElement);
			String	nodeIp			= getElementController().getElementAsString(CRONIOBOINode.NODE_IP						, nodeDataElement);
			String	nodeType		= getElementController().getElementAsString(CRONIOBOINode.NODE_TYPE				, nodeDataElement);
			Date	creationTime	= (Date) getElementController().getElementAsSerializable(CRONIOBOINode.CREATION_TIME	, nodeDataElement);

			node.setNodeName(nodeName);
			node.setNodeIp(nodeIp);
			node.setNodeType(nodeType);
			node.setSavedTime(actualDate);
			if (creationTime == null) {
				node.setCreationTime(actualDate);
			}

			AEMFTMetadataElementComposite variablesData = getElementController().getElementAsComposite(CRONIOBOINode.NODE_VARIABLE_LIST, nodeDataElement);
			List<DSLAMBOIVariable> nodeVariables = fromVariableDataList(variablesData);
			node.setVariableList(nodeVariables);
		}
		return node;
	}

	public static List<Date> fromScheduleDataList(AEMFTMetadataElementComposite scheduleDataList) {
		List<Date> scheduleList = null;
		if (scheduleDataList != null) {
			scheduleList =  new ArrayList<>();
			List<AEMFTMetadataElement> scheduleDataElementList = scheduleDataList.getElementList();
			for (AEMFTMetadataElement scheduleData : scheduleDataElementList) {
				String dataKey = scheduleData.getKey();
				if (!CRONIOBOIProjectDataConstants.INFO.equals(dataKey)) {
					AEMFTMetadataElementSingle scheduleDataSingle = (AEMFTMetadataElementSingle) scheduleData;
					Date schedule = (Date) scheduleDataSingle.getValueAsSerializable();
					scheduleList.add(schedule);
				}
			}
		}
		return scheduleList;
	}

	public static List<DSLAMBOIVariable> fromVariableDataList(AEMFTMetadataElementComposite variableDataList) {
		List<DSLAMBOIVariable> variableList = null;
		if (variableDataList != null) {
			variableList = new ArrayList<>();
			List<AEMFTMetadataElement> variableDataElementList = variableDataList.getElementList();
			for (AEMFTMetadataElement variableDataelement : variableDataElementList) {
				String dataKey = variableDataelement.getKey();
				if (!CRONIOBOIProjectDataConstants.INFO.equals(dataKey)) {
					DSLAMBOIVariable variable = fromVariableData((AEMFTMetadataElementComposite) variableDataelement);
					variableList.add(variable);
				}
			}
		}
		return variableList;
	}

	public static DSLAMBOIVariable fromVariableData(AEMFTMetadataElementComposite variableData) {
		DSLAMBOIVariable variable = null;
		if (variableData != null) {
			String 	variableName	= getElementController().getElementAsString(DSLAMBOIVariable.VARIABLE_NAME		, variableData);
			String 	variableValue	= getElementController().getElementAsString(DSLAMBOIVariable.VARIABLE_VALUE		, variableData);
			int		variableScope	= getElementController().getElementAsInt(DSLAMBOIVariable.VARIABLE_SCOPE		, variableData);
			int		variableType	= getElementController().getElementAsInt(DSLAMBOIVariable.VARIABLE_TYPE			, variableData);

			variable = new DSLAMBOVariable();
			variable.setVariableName(variableName);
			variable.setVariableValue(variableValue);
			variable.setVariableScope(variableScope);
			variable.setVariableType(variableType);
		}
		return variable;
	}
	
	public static CRONIOBOIMachineProperties fromMachineConfigData(AEMFTMetadataElementComposite machineConfigData) {
		CRONIOBOIMachineProperties machine = null;
		if (machineConfigData != null) {
			machine = new CRONIOBOMachineProperties();
			
			String 	user 					= getElementController().getElementAsString(CRONIOBOIMachineProperties.USERNAME					, machineConfigData);
			String 	password 				= getElementController().getElementAsString(CRONIOBOIMachineProperties.PASSWORD					, machineConfigData);
			int 	timeout 				= getElementController().getElementAsInt(CRONIOBOIMachineProperties.TIMEOUT						, machineConfigData);
			String 	prompt 					= getElementController().getElementAsString(CRONIOBOIMachineProperties.PROMPT_REGEX				, machineConfigData);
			String 	usernamePromptRegEx 	= getElementController().getElementAsString(CRONIOBOIMachineProperties.USERNAME_PROMPT_REGEX	, machineConfigData);
			String 	passwordPromptRegEx 	= getElementController().getElementAsString(CRONIOBOIMachineProperties.PASSWORD_PROMPT_REGEX	, machineConfigData);
			String 	rollbackConditionRegEx 	= getElementController().getElementAsString(CRONIOBOIMachineProperties.ROLLBACK_CONDITION_REGEX	, machineConfigData);
			int 	protocolType 			= getElementController().getElementAsInt(CRONIOBOIMachineProperties.PROTOCOL_TYPE				, machineConfigData);
			
			machine.setProtocolType(protocolType);
			machine.setTimeout(timeout);
			machine.setUsername(user);
			machine.setPassword(password);
			machine.setPromptRegEx(prompt);
			machine.setUsernamePromptRegEx(usernamePromptRegEx);
			machine.setPasswordPromptRegEx(passwordPromptRegEx);
			machine.setRollbackConditionRegEx(rollbackConditionRegEx);
		}
		
		return machine;
	}
	
	public static CRONIOBOIMachineProperties fromMachineProperties(AEMFTMetadataElementComposite machinePropertiesData) {
		CRONIOBOIMachineProperties machine = null;
		if (machinePropertiesData != null) {
			
			String 	machineName 	= getElementController().getElementAsString(CRONIOBOIMachineProperties.MACHINE_NAME	, machinePropertiesData);
			long 	machineId 		= getElementController().getElementAsLong(CRONIOBOIMachineProperties.MACHINE_ID		, machinePropertiesData);
			Date  	creationTime	= (Date) getElementController().getElementAsSerializable(CRONIOBOIMachineProperties.CREATION_TIME, machinePropertiesData);
			
			machine = fromMachineConfigData(machinePropertiesData.getCompositeElement(CRONIOBOIMachineProperties.MACHINE_CONNECTION_CONFIG));
			machine.setMachineName(machineName);
			machine.setMachinePropertiesId(machineId);
			List<DSLAMBOIVariable> variableList = fromVariableDataList(machinePropertiesData.getCompositeElement(CRONIOBOIMachineProperties.MACHINE_VARIABLES));
			machine.setConnectionVariables(variableList);
			DSLAMBOIFile initScript = fromFileData(machinePropertiesData.getCompositeElement(CRONIOBOIMachineProperties.MACHINE_CONNECTION_SCRIPT));
			machine.setInitConnectionScript(initScript);
			DSLAMBOIFile finishScript = fromFileData(machinePropertiesData.getCompositeElement(CRONIOBOIMachineProperties.MACHINE_DISCONNECTION_SCRIPT));
			machine.setCloseConnectionScript(finishScript);
			machine.setCreationTime(creationTime);
		}
		
		return machine;
	}
	
	public static List<CRONIOBOIMachineProperties> fromMachinePropertiesList(AEMFTMetadataElementComposite machinePropertiesListData) {
		List<CRONIOBOIMachineProperties> machinePropertiesList = null;
		if (machinePropertiesListData != null) {
			machinePropertiesList = new ArrayList<>();
			List<AEMFTMetadataElement> machinePropertiesDataElementList = machinePropertiesListData.getElementList();
			for (AEMFTMetadataElement machinePropertiesDataElement : machinePropertiesDataElementList) {
				String dataKey = machinePropertiesDataElement.getKey();
				if (!CRONIOBOIPreferencesDataConstants.INFO.equals(dataKey)) {
					CRONIOBOIMachineProperties machine = fromMachineProperties((AEMFTMetadataElementComposite) machinePropertiesDataElement);
					machinePropertiesList.add(machine);
				}
			}
		}
		return machinePropertiesList;
	}
	
	public static CRONIOBOIPreferences fromPreferencesData(AEMFTMetadataElementComposite preferencesData) {
		CRONIOBOIPreferences preferences = null;
		if (preferencesData != null) {
			preferences = new CRONIOBOPreferences();

			Long	preferencesId		= getElementController().getElementAsLong(CRONIOBOIPreferences.PREFERENCES_ID, preferencesData);
			Long	userPreferencesId	= getElementController().getElementAsLong(CRONIOBOIUserPreferences.USER_PREFERENCES_ID, preferencesData);
			Date	creationTime	= (Date) getElementController().getElementAsSerializable(CRONIOBOIPreferences.CREATION_TIME	, preferencesData);
			Date	savedTime		= (Date) getElementController().getElementAsSerializable(CRONIOBOIPreferences.SAVED_TIME	, preferencesData);
			AEMFTMetadataElementComposite machinePropertiesListData = getElementController().getElementAsComposite(CRONIOBOIPreferences.PREFERENCES_MACHINE_PROPERTIES_LIST	, preferencesData);
			AEMFTMetadataElementComposite userPreferencesData 		= getElementController().getElementAsComposite(CRONIOBOIPreferences.PREFERENCES_USER_PROPERTIES			, preferencesData);
			userPreferencesData.addElement(CRONIOBOIUserPreferences.USER_PREFERENCES_ID, userPreferencesId);
			
			List<CRONIOBOIMachineProperties> 	machinePropertiesList 	= fromMachinePropertiesList(machinePropertiesListData);
			CRONIOBOIUserPreferences         	userPreferences			= fromUserPreferences(userPreferencesData);
			
			preferences.setPreferencesId(preferencesId);
			preferences.setMachinePropertiesList(machinePropertiesList);
			preferences.setUserPreferences(userPreferences);
			preferences.setCreationTime(creationTime);
			preferences.setSavedTime(savedTime);

		}
		return preferences;
	}

	public static CRONIOBOIUserPreferences fromUserPreferences(AEMFTMetadataElementComposite userPreferencesData) {
		CRONIOBOIUserPreferences userPreferences = null;
		if (userPreferencesData != null) {
			userPreferences = new CRONIOBOUserPreferences();
			
			AEMFTMetadataElementComposite userConfigPreferencesData 		= getElementController().getElementAsComposite(CRONIOBOIPreferences.USER_CONFIG		, userPreferencesData);
			
			Long	userPreferencesId	= getElementController().getElementAsLong(CRONIOBOIUserPreferences.USER_PREFERENCES_ID		, userPreferencesData);
			//Date	creationTime		= (Date) getElementController().getElementAsSerializable(CRONIOBOIPreferences.CREATION_TIME	, userConfigPreferencesData);
			Date	savedTime			= new Date();
			int		downTime			= getElementController().getElementAsInt(CRONIOBOIUserPreferences.DOWNTIME, userConfigPreferencesData);

			userPreferences.setUserPreferencesId(userPreferencesId);
			userPreferences.setDownTime(downTime);
			userPreferences.setSavedTime(savedTime);
		}
		return userPreferences;
	}
	
	/**
	 * PRIVATE
	 */

	private static AEMFTIMetadataElementController getElementController() {
		if (elementController == null) {
			elementController = new AEMFTMetadataElementControllerImpl();
		}
		return elementController;
	}

}
