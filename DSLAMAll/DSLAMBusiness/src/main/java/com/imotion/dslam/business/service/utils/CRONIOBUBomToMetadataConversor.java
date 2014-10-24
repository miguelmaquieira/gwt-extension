package com.imotion.dslam.business.service.utils;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.imotion.dslam.bom.CRONIOBOIExecution;
import com.imotion.dslam.bom.CRONIOBOIFile;
import com.imotion.dslam.bom.CRONIOBOILog;
import com.imotion.dslam.bom.CRONIOBOILogNode;
import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.CRONIOBOINodeList;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.bom.CRONIOBOIProcess;
import com.imotion.dslam.bom.CRONIOBOIProcessDataConstants;
import com.imotion.dslam.bom.CRONIOBOIProject;
import com.imotion.dslam.bom.CRONIOBOIUser;
import com.imotion.dslam.bom.CRONIOBOIUserPreferences;
import com.imotion.dslam.bom.CRONIOBOIVariable;
import com.imotion.dslam.bom.CRONIOBOIVariablesDataConstants;
import com.imotion.dslam.business.service.CRONIOBUIProjectBusinessServiceConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTIMetadataElementController;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.controller.AEMFTMetadataElementControllerImpl;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.core.appli.metadata.element.factory.AEMFTMetadataElementReflectionBasedFactory;
import com.selene.arch.exe.core.common.AEMFTCommonUtils;

public class CRONIOBUBomToMetadataConversor {

	private AEMFTIMetadataElementController elementController = null;
	
	/**
	 * PROCESS
	 */
	
	public  static AEMFTMetadataElementComposite fromProcess(CRONIOBOIProcess process) {
		AEMFTMetadataElementComposite data = null;
		if (process != null) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			
			data.addElement(CRONIOBOIProcess.PROCESS_ID				, String.valueOf(process.getProcessId()));
			data.addElement(CRONIOBOIProcess.PROCESS_NAME			, process.getProcessName());
			data.addElement(CRONIOBOIProcess.CREATION_TIME			, process.getCreationTime());
			data.addElement(CRONIOBOIProcess.SAVED_TIME				, process.getSavedTime());
		}
		return data;
	}

	public  static AEMFTMetadataElementComposite fromProcessFull(CRONIOBOIProcess process, Locale locale) {
		AEMFTMetadataElementComposite data = null;
		if (process != null) {
			data =fromProcess(process);

			AEMFTMetadataElementComposite 	scheduleListData = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			List<Date> 						scheduleList = process.getScheduleList();
			
			if (scheduleList != null) {
				for (int i = 0; i < scheduleList.size(); i++) {
					Date date = scheduleList.get(i);
					
					String dateFormat = AEMFTCommonUtils.formatDate(date, CRONIOBOIProcessDataConstants.DATE_FORMAT, locale);
					scheduleListData.addElement(dateFormat, date);	
				}
			}
				
			AEMFTMetadataElementComposite variableListData	= fromVariableList(process.getVariableList());
			AEMFTMetadataElementComposite extraOptions		= AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			extraOptions.addElement(CRONIOBOIProcess.PROCESS_SYNC_OPTION, process.isSynchronous());
			AEMFTMetadataElementComposite listNodeListData	= fromListNodeList(process.getListNodeList());
			
			data.addElement(CRONIOBOIProcess.PROCESS_EXTRA_OPTIONS	, extraOptions);
			data.addElement(CRONIOBOIProcess.PROCESS_SCHEDULE_LIST	, scheduleListData);
			data.addElement(CRONIOBOIProcess.PROCESS_VARIABLE_LIST	, variableListData);
			data.addElement(CRONIOBOIProcess.PROCESS_NODELIST_LIST	, listNodeListData);
		}
		return data;
	}

	public  static AEMFTMetadataElementComposite fromProcessList(List<CRONIOBOIProcess> processList, List<CRONIOBOIFile> fileList, Locale locale) {
		AEMFTMetadataElementComposite data = null;
		data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		if (!AEMFTCommonUtilsBase.isEmptyList(processList)) {
			AEMFTMetadataElementComposite dataProcessList = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			for (CRONIOBOIProcess process : processList) {
				dataProcessList.addElement(process.getProcessName(), fromProcessFull(process, locale));
			}
			data.addElement(CRONIOBUIProjectBusinessServiceConstants.PROCESS_DATA_LIST,dataProcessList);
		}
		
		if (!AEMFTCommonUtilsBase.isEmptyList(fileList)) {
			AEMFTMetadataElementComposite dataFilesList = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			
			for (CRONIOBOIFile file : fileList) {
				dataFilesList.addElement(file.getFilename(),fromFileIdName(file));
			}
			data.addElement(CRONIOBUIProjectBusinessServiceConstants.PROCESS_FILE_DATA_LIST,dataFilesList);
		}
		
		return data;
	}
	
	/**
	 * FILE
	 */
	
	public  static AEMFTMetadataElementComposite fromFile(CRONIOBOIFile file) {
		AEMFTMetadataElementComposite data = null;
		if (file != null) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();

			data.addElement(CRONIOBOIFile.FILE_ID				, String.valueOf(file.getFileId()));
			data.addElement(CRONIOBOIFile.FILE_NAME				, file.getFilename());
			data.addElement(CRONIOBOIFile.CONTENT_TYPE			, file.getContentType());
			data.addElement(CRONIOBOIFile.CONTENT				, file.getContent());
			data.addElement(CRONIOBOIFile.CREATION_TIME			, file.getCreationTime());
			data.addElement(CRONIOBOIFile.SAVED_TIME				, file.getSavedTime());
		}
		return data;
	}

	public  static AEMFTMetadataElementComposite fromFileList(List<CRONIOBOIFile> fileList) {
		AEMFTMetadataElementComposite data = null;
		if (!AEMFTCommonUtilsBase.isEmptyList(fileList)) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			for (CRONIOBOIFile file : fileList) {
				data.addElement(file.getFilename(), fromFile(file));
			}
		}
		return data;
	}
	
	public  static AEMFTMetadataElementComposite fromFileIdName(CRONIOBOIFile file) {
		AEMFTMetadataElementComposite data = null;
		if (file != null) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();

			data.addElement(CRONIOBOIFile.FILE_ID				, file.getFileId());
			data.addElement(CRONIOBOIFile.FILE_NAME				, file.getFilename());
		}
		return data;
	}
	
	/**
	 * VARIABLE
	 */
	
	public  static AEMFTMetadataElementComposite fromVariable(CRONIOBOIVariable variable) {
		AEMFTMetadataElementComposite variableData = null;
		if (variable != null) {
			variableData 	= AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
			variableData.addElement(CRONIOBOIVariablesDataConstants.VARIABLE_NAME	, variable.getVariableName());
			variableData.addElement(CRONIOBOIVariablesDataConstants.VARIABLE_VALUE	, variable.getVariableValue());
			variableData.addElement(CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE	, variable.getVariableScope());
			variableData.addElement(CRONIOBOIVariablesDataConstants.VARIABLE_TYPE	, variable.getVariableType());
		}
		return variableData;
	}
	
	public  static AEMFTMetadataElementComposite fromVariableList(List<CRONIOBOIVariable> variableList) {
		AEMFTMetadataElementComposite 	variableListData = null;
		if (!AEMFTCommonUtils.isEmptyList(variableList)) {
			variableListData = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			for (CRONIOBOIVariable variable : variableList) {
				AEMFTMetadataElementComposite variableData = fromVariable(variable);
				variableListData.addElement(variable.getVariableName()	, variableData);
			}
		}
		return variableListData;
	}
	
	/**
	 * PROJECT
	 */
	
	public  static AEMFTMetadataElementComposite fromProject(CRONIOBOIProject project) {
		AEMFTMetadataElementComposite data = null;
		if (project != null) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			
			data.addElement(CRONIOBOIProject.PROJECT_ID				, String.valueOf(project.getProjectId()));
			data.addElement(CRONIOBOIProject.PROJECT_NAME			, project.getProjectName());
			data.addElement(CRONIOBOIProject.PROJECT_MACHINE_TYPE	, project.getMachineType());
			data.addElement(CRONIOBOIProcess.CREATION_TIME			, project.getCreationTime());
			data.addElement(CRONIOBOIProcess.SAVED_TIME				, project.getSavedTime());
		}
		return data;
	}

	public  static AEMFTMetadataElementComposite fromProjectFull(CRONIOBOIProject project, Locale locale) {
		AEMFTMetadataElementComposite data = null;
		if (project != null) {
			data = fromProject(project);
			
			CRONIOBOIFile 	mainScript 		= project.getMainScript();
			CRONIOBOIFile 	rollBackScript 	= project.getRollBackScript();
			CRONIOBOIProcess process 		= project.getProcess();
			AEMFTMetadataElementComposite mainScriptData 		= CRONIOBUBomToMetadataConversor.fromFile(mainScript);
			AEMFTMetadataElementComposite rollBackScriptData 	= CRONIOBUBomToMetadataConversor.fromFile(rollBackScript);
			AEMFTMetadataElementComposite processData 			= CRONIOBUBomToMetadataConversor.fromProcessFull(process, locale);
			
			data.addElement(CRONIOBOIProject.PROJECT_MAIN_SCRIPT			, mainScriptData);
			data.addElement(CRONIOBOIProject.PROJECT_ROLLBACK_SCRIPT		, rollBackScriptData);
			data.addElement(CRONIOBOIProject.PROJECT_PROCESS				, processData);
		}
		return data;
	}

	public  static AEMFTMetadataElementComposite fromProjectList(List<CRONIOBOIProject> projectList, Locale locale) {
		AEMFTMetadataElementComposite dataProjectList = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		if (!AEMFTCommonUtilsBase.isEmptyList(projectList)) {
			for (CRONIOBOIProject project : projectList) {
				dataProjectList.addElement(String.valueOf(project.getProjectId()), fromProjectFull(project, locale));
			}
		}
		return dataProjectList;
	}
	
	/**
	 * NODE
	 */
	
	public  static AEMFTMetadataElementComposite fromNode(CRONIOBOINode node) {
		AEMFTMetadataElementComposite data = null;
		if (node != null) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			
			data.addElement(CRONIOBOINode.NODE_ID				, String.valueOf(node.getNodeId()));
			data.addElement(CRONIOBOINode.NODE_NAME				, node.getNodeName());
			data.addElement(CRONIOBOINode.NODE_IP				, node.getNodeIp());
			data.addElement(CRONIOBOINode.NODE_TYPE				, node.getNodeType());
			data.addElement(CRONIOBOINode.NODE_NODELIST			, node.getNodeList().getNodeListId());
			data.addElement(CRONIOBOIProcess.CREATION_TIME		, node.getCreationTime());
			data.addElement(CRONIOBOIProcess.SAVED_TIME			, node.getSavedTime());
			
			AEMFTMetadataElementComposite variableListData	= fromVariableList(node.getVariableList());
			data.addElement(CRONIOBOINode.NODE_VARIABLE_LIST	, variableListData);
		}
		return data;
	}
	
	public  static AEMFTMetadataElementComposite fromNodesList(List<CRONIOBOINode> nodeList) {
		AEMFTMetadataElementComposite dataNodeList = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		if (!AEMFTCommonUtilsBase.isEmptyList(nodeList)) {
			for (CRONIOBOINode node : nodeList) {
				dataNodeList.addElement(node.getNodeName(), fromNode(node));
			}
		}
		return dataNodeList;
	}
	
	/**
	 * LOGNODE
	 */
	
	public  static AEMFTMetadataElementComposite fromLogNode(CRONIOBOILogNode logNode) {
		AEMFTMetadataElementComposite data = null;
		if (logNode != null) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			
			data.addElement(CRONIOBOILogNode.NODE_ID				, String.valueOf(logNode.getNodeId()));
			data.addElement(CRONIOBOILogNode.NODE_NAME				, logNode.getNodeName());
			data.addElement(CRONIOBOILogNode.NODE_IP				, logNode.getNodeIp());
			data.addElement(CRONIOBOILogNode.NODE_TYPE				, logNode.getNodeType());
			data.addElement(CRONIOBOILogNode.STATE					, logNode.getState());
			data.addElement(CRONIOBOILogNode.CREATION_TIME			, logNode.getCreationTime());
		
		}
		return data;
	}
	
	public  static AEMFTMetadataElementComposite fromLogNodeList(List<CRONIOBOILogNode> logNodeList) {
		AEMFTMetadataElementComposite dataLogNodeList = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		if (!AEMFTCommonUtilsBase.isEmptyList(logNodeList)) {
			for (CRONIOBOILogNode logNode : logNodeList) {
				dataLogNodeList.addElement(logNode.getNodeName(), fromLogNode(logNode));
			}
		}
		return dataLogNodeList;
	}
	
	/**
	 * NODECSV
	 */
	
	public  static AEMFTMetadataElementComposite fromNodeCSV(CRONIOBOINode node) {
		AEMFTMetadataElementComposite data = null;
		if (node != null) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			
			data.addElement(CRONIOBOINode.NODE_ID				, String.valueOf(node.getNodeId()));
			data.addElement(CRONIOBOINode.NODE_NAME				, node.getNodeName());
			data.addElement(CRONIOBOINode.NODE_IP				, node.getNodeIp());
			data.addElement(CRONIOBOINode.NODE_TYPE				, node.getNodeType());
			data.addElement(CRONIOBOIProcess.CREATION_TIME		, node.getCreationTime());
			data.addElement(CRONIOBOIProcess.SAVED_TIME			, node.getSavedTime());
			
			AEMFTMetadataElementComposite variableListData	= fromVariableList(node.getVariableList());
			data.addElement(CRONIOBOINode.NODE_VARIABLE_LIST	, variableListData);
		}
		return data;
	}

	public  static AEMFTMetadataElementComposite fromNodesCSVList(List<CRONIOBOINode> nodeList) {
		AEMFTMetadataElementComposite dataNodeList = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		if (!AEMFTCommonUtilsBase.isEmptyList(nodeList)) {
			for (CRONIOBOINode node : nodeList) {
				dataNodeList.addElement(node.getNodeName(), fromNodeCSV(node));
			}
		}
		return dataNodeList;
	}
	
	/**
	 * NODELIST
	 */
	
	public  static AEMFTMetadataElementComposite fromNodeList(CRONIOBOINodeList nodeList) {
		AEMFTMetadataElementComposite data = null;
		if (nodeList != null) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			
			data.addElement(CRONIOBOINodeList.NODELIST_ID		, String.valueOf(nodeList.getNodeListId()));
			data.addElement(CRONIOBOINodeList.NODELIST_NAME		, nodeList.getNodeListName());
			data.addElement(CRONIOBOINodeList.NODELIST_PROCESS	, nodeList.getProcess().getProcessId());
			data.addElement(CRONIOBOINodeList.CREATION_TIME		, nodeList.getCreationTime());
			data.addElement(CRONIOBOINodeList.SAVED_TIME		, nodeList.getSavedTime());
			
			AEMFTMetadataElementComposite nodeListData	= fromNodesList(nodeList.getNodeList());
			data.addElement(CRONIOBOINodeList.NODELIST_NODE_LIST	, nodeListData);
		}
		return data;
	}
	
	public  static AEMFTMetadataElementComposite fromListNodeList(List<CRONIOBOINodeList> listNodeList) {
		AEMFTMetadataElementComposite dataListNodeList = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		if (!AEMFTCommonUtilsBase.isEmptyList(listNodeList)) {
			for (CRONIOBOINodeList nodeList : listNodeList) {
				dataListNodeList.addElement(nodeList.getNodeListName(), fromNodeList(nodeList));
			}
		}
		return dataListNodeList;
	}
	
	/**
	 * MACHINE PROPERTIES
	 */
	
	public  static AEMFTMetadataElementComposite fromMachineProperties(CRONIOBOIMachineProperties connection) {
		AEMFTMetadataElementComposite data = null;
		if (connection != null) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			
			data.addElement(CRONIOBOIMachineProperties.MACHINE_ID		, connection.getMachinePropertiesId());
			data.addElement(CRONIOBOIMachineProperties.MACHINE_NAME		, connection.getMachineName());
			data.addElement(CRONIOBOIMachineProperties.CREATION_TIME	, connection.getCreationTime());
			data.addElement(CRONIOBOIMachineProperties.SAVED_TIME		, connection.getSaveTime());
			AEMFTMetadataElementComposite connectionConfigData = fromConnectionConfig(connection); 
			data.addElement(CRONIOBOIMachineProperties.MACHINE_CONNECTION_CONFIG, connectionConfigData);
			
		}
		return data;
	}
	
	public  static AEMFTMetadataElementComposite fromConnectionConfig(CRONIOBOIMachineProperties connection) {
		AEMFTMetadataElementComposite data = null;
		if (connection != null) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			data.addElement(CRONIOBOIMachineProperties.MACHINE_NAME						, connection.getMachineName());
			data.addElement(CRONIOBOIMachineProperties.MACHINE_DESCRIPTION				, connection.getMachineDescription());
			data.addElement(CRONIOBOIMachineProperties.PROTOCOL_TYPE					, connection.getProtocolType());
			data.addElement(CRONIOBOIMachineProperties.USERNAME							, connection.getUsername());
			data.addElement(CRONIOBOIMachineProperties.PASSWORD							, connection.getPassword());
			data.addElement(CRONIOBOIMachineProperties.TIMEOUT							, connection.getTimeout());
			data.addElement(CRONIOBOIMachineProperties.PROMPT_REGEX						, connection.getPromptRegEx());
			data.addElement(CRONIOBOIMachineProperties.USERNAME_PROMPT_REGEX			, connection.getUsernamePromptRegEx());
			data.addElement(CRONIOBOIMachineProperties.PASSWORD_PROMPT_REGEX			, connection.getPasswordPromptRegEx());
			data.addElement(CRONIOBOIMachineProperties.ROLLBACK_CONDITION_REGEX			, connection.getRollbackConditionRegEx());
		}
		return data;
	}
	
	public  static AEMFTMetadataElementComposite fromMachinePropertiesFull(CRONIOBOIMachineProperties connection) {
		AEMFTMetadataElementComposite data = null;
		if (connection != null) {
			data = fromMachineProperties(connection);
			
			CRONIOBOIFile 	connectionScript 		= connection.getInitConnectionScript();
			CRONIOBOIFile 	disconnectionScript 	= connection.getCloseConnectionScript();
			List<CRONIOBOIVariable> variableList 	= connection.getConnectionVariables();
			AEMFTMetadataElementComposite connectionScriptData 		= fromFile(connectionScript);
			AEMFTMetadataElementComposite disconnectionScriptData 	= fromFile(disconnectionScript);
			AEMFTMetadataElementComposite variableListData			= fromVariableList(variableList);
			AEMFTMetadataElementComposite connectionConfigData		= fromConnectionConfig(connection);
			
			data.addElement(CRONIOBOIMachineProperties.MACHINE_CONNECTION_SCRIPT		, connectionScriptData);
			data.addElement(CRONIOBOIMachineProperties.MACHINE_DISCONNECTION_SCRIPT		, disconnectionScriptData);
			data.addElement(CRONIOBOIMachineProperties.MACHINE_VARIABLES				, variableListData);
			data.addElement(CRONIOBOIMachineProperties.MACHINE_CONNECTION_CONFIG		, connectionConfigData);
			
		}
		return data;
	}

	public  static AEMFTMetadataElementComposite fromMachinePropertiesList(List<CRONIOBOIMachineProperties> connectionList) {
		AEMFTMetadataElementComposite dataConnectionList = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		if (!AEMFTCommonUtilsBase.isEmptyList(connectionList)) {
			for (CRONIOBOIMachineProperties connection : connectionList) {
				dataConnectionList.addElement(connection.getMachineName(), fromMachinePropertiesFull(connection));
			}
		}
		return dataConnectionList;
	}
	
	/**
	 * PREFERENCES
	 */
	
	public  static AEMFTMetadataElementComposite fromPreferences(CRONIOBOIPreferences preferences) {
		AEMFTMetadataElementComposite data = null;
		if (preferences != null) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			
			data.addElement(CRONIOBOIPreferences.PREFERENCES_ID	, preferences.getPreferencesId());
			data.addElement(CRONIOBOIPreferences.CREATION_TIME	, preferences.getCreationTime());
			data.addElement(CRONIOBOIPreferences.SAVED_TIME		, preferences.getSavedTime());
			
			List<CRONIOBOIMachineProperties> machinePropertiesList 	= preferences.getMachinePropertiesList();
			AEMFTMetadataElementComposite machinePropertiesListData = fromMachinePropertiesList(machinePropertiesList);
			
			CRONIOBOIUserPreferences userPreferences = preferences.getUserPreferences();
			AEMFTMetadataElementComposite userPreferencesData = fromUserPreferences(userPreferences);
			
			data.addElement(CRONIOBOIPreferences.PREFERENCES_MACHINE_PROPERTIES_LIST		, machinePropertiesListData);
			data.addElement(CRONIOBOIPreferences.PREFERENCES_USER_PROPERTIES				, userPreferencesData);
		}
		return data;
	}
	
	/**
	 * USERPREFERENCES
	 */
	
	public  static AEMFTMetadataElementComposite fromUserPreferences(CRONIOBOIUserPreferences userPreferences) {
		AEMFTMetadataElementComposite data = null;
		if (userPreferences != null) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			
			data.addElement(CRONIOBOIUserPreferences.USER_PREFERENCES_ID	, userPreferences.getUserPreferencesId());
			data.addElement(CRONIOBOIUserPreferences.CREATION_TIME			, userPreferences.getCreationTime());
			data.addElement(CRONIOBOIUserPreferences.SAVED_TIME				, userPreferences.getSavedTime());
			data.addElement(CRONIOBOIUserPreferences.DOWNTIME				, userPreferences.getDownTime());	
		}
		AEMFTMetadataElementComposite userPreferencesData = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		userPreferencesData.addElement(CRONIOBOIPreferences.USER_CONFIG,data);
		return userPreferencesData;
	}
	
	/**
	 * EXECUTION
	 */
	
	public  static AEMFTMetadataElementComposite fromExecution(CRONIOBOIExecution execution) {
		AEMFTMetadataElementComposite data = null;
		if (execution != null) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			
			AEMFTMetadataElementComposite logNodesData = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			List<CRONIOBOILogNode> logNodes = execution.getLogNodes();
			for (CRONIOBOILogNode logNode : logNodes) {
				logNodesData.addElement(logNode.getNodeName(), fromLogNode(logNode));
			}
			
			AEMFTMetadataElementComposite userData = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			CRONIOBOIUser user = execution.getUser();
			
			userData = fromUser(user, userData);
			
			data.addElement(CRONIOBOIExecution.EXECUTION_ID				, execution.getExecutionId());
			data.addElement(CRONIOBOIExecution.PROJECT_ID				, String.valueOf(execution.getProject().getProjectId()));
			data.addElement(CRONIOBOIExecution.CREATION_TIME			, execution.getCreationTime());
			data.addElement(CRONIOBOIExecution.FINISH_EXECUTION_TIME	, execution.getFinishExecutionTime());
			data.addElement(CRONIOBOIExecution.DESTINATION_LOGS			, execution.getDestinationLogs());
			data.addElement(CRONIOBOIExecution.LOGNODES					, logNodesData);
			data.addElement(CRONIOBOIExecution.USER						, userData);
			data.addElement(CRONIOBOIExecution.IS_SYNCHRONOUS			, execution.getIsSynchronous());
			data.addElement(CRONIOBOIExecution.ENVIRONMENT_NAME			, execution.getEnvironmentName());
		}
		return data;
	}
	
	public  static AEMFTMetadataElementComposite fromExecutionsProjectList(List<CRONIOBOIExecution> executionProjectList) {
		AEMFTMetadataElementComposite dataExecutionProjectListData = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		if (!AEMFTCommonUtilsBase.isEmptyList(executionProjectList)) {
			for (CRONIOBOIExecution execution : executionProjectList) {
				dataExecutionProjectListData.addElement(String.valueOf(execution.getExecutionId()), fromExecution(execution));
			}
		}
		return dataExecutionProjectListData;
	}
	
	/**
	 * LOG
	 */
	
	public  static AEMFTMetadataElementComposite fromLog(CRONIOBOILog log) {
		AEMFTMetadataElementComposite data = null;
		if (log != null) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();

			data.addElement(CRONIOBOILog.LOG_ID			, log.getLogId());
			data.addElement(CRONIOBOILog.TIMESTAMP		, String.valueOf(log.getTimestamp()));
			data.addElement(CRONIOBOILog.MESSAGE		, log.getMessage());
			data.addElement(CRONIOBOILog.LEVEL			, log.getLevel());
			data.addElement(CRONIOBOILog.THREAD			, log.getThread());
	
		}
		return data;
	}
	
	public  static AEMFTMetadataElementComposite fromLogList(List<CRONIOBOILog> logList) {
		AEMFTMetadataElementComposite logListData = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		if (!AEMFTCommonUtilsBase.isEmptyList(logList)) {
			int count = 0;
			for (CRONIOBOILog log : logList) {	
				logListData.addElement(String.valueOf(count), fromLog(log));
				count++;
			}
		}
		return logListData;
	}
	
	/**
	 * USER
	 */
	
	public  static AEMFTMetadataElementComposite fromUser(CRONIOBOIUser user, AEMFTMetadataElementComposite data) {
		if (user != null) {
			if (data == null) {
				data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			}
			data.addElement(CRONIOBOIUser.USER_ID				, user.getUserId());
			data.addElement(CRONIOBOIUser.EMAIL					, user.getEmail());
			data.addElement(CRONIOBOIUser.USERNAME				, user.getUsername());
			data.addElement(CRONIOBOIUser.HASH					, user.getHash());
			data.addElement(CRONIOBOIUser.LAST_LOGIN			, user.getLastLogin());
			data.addElement(CRONIOBOIUser.LAST_SESSION_ID		, user.getLastSessionId());
			data.addElement(CRONIOBOIUser.RESET_ID				, user.getResetId());
			data.addElement(CRONIOBOIUser.PENDING_ACCEPT		, user.isPendingAccept());
			data.addElement(CRONIOBOIUser.ROL_TYPE				, user.getRolType());
			data.addElement(CRONIOBOIUser.SOCIAL_PROVIDER_ID	, user.getSocialProviderId());
			data.addElement(CRONIOBOIUser.SOCIAL_VALIDATED_ID	, user.getSocialValidatedId());
			
		}
		return data;
	}

	/**
	 * PRIVATE
	 */

	private AEMFTIMetadataElementController getElementController() {
		if (elementController == null) {
			elementController = new AEMFTMetadataElementControllerImpl();
		}
		return elementController;
	}


}
