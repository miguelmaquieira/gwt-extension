package com.imotion.dslam.business.service.utils;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.imotion.dslam.bom.CRONIOBOIExecution;
import com.imotion.dslam.bom.CRONIOBOILog;
import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.bom.CRONIOBOIUserPreferences;
import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIProcessDataConstants;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.bom.DSLAMBOIVariable;
import com.imotion.dslam.bom.DSLAMBOIVariablesDataConstants;
import com.imotion.dslam.business.service.DSLAMBUIProjectBusinessServiceConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTIMetadataElementController;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.controller.AEMFTMetadataElementControllerImpl;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.core.appli.metadata.element.factory.AEMFTMetadataElementReflectionBasedFactory;
import com.selene.arch.exe.core.common.AEMFTCommonUtils;

public class DSLAMBUBomToMetadataConversor {

	private AEMFTIMetadataElementController elementController = null;
	
	/**
	 * PROCESS
	 */
	
	public  static AEMFTMetadataElementComposite fromProcess(DSLAMBOIProcess process) {
		AEMFTMetadataElementComposite data = null;
		if (process != null) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			
			data.addElement(DSLAMBOIProcess.PROCESS_ID				, String.valueOf(process.getProcessId()));
			data.addElement(DSLAMBOIProcess.PROCESS_NAME			, process.getProcessName());
			data.addElement(DSLAMBOIProcess.CREATION_TIME			, process.getCreationTime());
			data.addElement(DSLAMBOIProcess.SAVED_TIME				, process.getSavedTime());
		}
		return data;
	}

	public  static AEMFTMetadataElementComposite fromProcessFull(DSLAMBOIProcess process, Locale locale) {
		AEMFTMetadataElementComposite data = null;
		if (process != null) {
			data =fromProcess(process);

			AEMFTMetadataElementComposite 	scheduleListData = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			List<Date> 						scheduleList = process.getScheduleList();
			
			if (scheduleList != null) {
				for (int i = 0; i < scheduleList.size(); i++) {
					Date date = scheduleList.get(i);
					
					String dateFormat = AEMFTCommonUtils.formatDate(date, DSLAMBOIProcessDataConstants.DATE_FORMAT, locale);
					scheduleListData.addElement(dateFormat, date);	
				}
			}
				
			AEMFTMetadataElementComposite variableListData	= fromVariableList(process.getVariableList());
			AEMFTMetadataElementComposite extraOptions		= AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			extraOptions.addElement(DSLAMBOIProcess.PROCESS_SYNC_OPTION, process.isSynchronous());
			AEMFTMetadataElementComposite nodeListData		= fromNodeList(process.getNodeList());
			
			data.addElement(DSLAMBOIProcess.PROCESS_EXTRA_OPTIONS	, extraOptions);
			data.addElement(DSLAMBOIProcess.PROCESS_SCHEDULE_LIST	, scheduleListData);
			data.addElement(DSLAMBOIProcess.PROCESS_VARIABLE_LIST	, variableListData);
			data.addElement(DSLAMBOIProcess.PROCESS_NODE_LIST		, nodeListData);
		}
		return data;
	}

	public  static AEMFTMetadataElementComposite fromProcessList(List<DSLAMBOIProcess> processList, List<DSLAMBOIFile> fileList, Locale locale) {
		AEMFTMetadataElementComposite data = null;
		data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		if (!AEMFTCommonUtilsBase.isEmptyList(processList)) {
			AEMFTMetadataElementComposite dataProcessList = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			for (DSLAMBOIProcess process : processList) {
				dataProcessList.addElement(process.getProcessName(), fromProcessFull(process, locale));
			}
			data.addElement(DSLAMBUIProjectBusinessServiceConstants.PROCESS_DATA_LIST,dataProcessList);
		}
		
		if (!AEMFTCommonUtilsBase.isEmptyList(fileList)) {
			AEMFTMetadataElementComposite dataFilesList = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			
			for (DSLAMBOIFile file : fileList) {
				dataFilesList.addElement(file.getFilename(),fromFileIdName(file));
			}
			data.addElement(DSLAMBUIProjectBusinessServiceConstants.PROCESS_FILE_DATA_LIST,dataFilesList);
		}
		
		return data;
	}
	
	/**
	 * FILE
	 */
	
	public  static AEMFTMetadataElementComposite fromFile(DSLAMBOIFile file) {
		AEMFTMetadataElementComposite data = null;
		if (file != null) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();

			data.addElement(DSLAMBOIFile.FILE_ID				, String.valueOf(file.getFileId()));
			data.addElement(DSLAMBOIFile.FILE_NAME				, file.getFilename());
			data.addElement(DSLAMBOIFile.CONTENT_TYPE			, file.getContentType());
			data.addElement(DSLAMBOIFile.CONTENT				, file.getContent());
			data.addElement(DSLAMBOIFile.CREATION_TIME			, file.getCreationTime());
			data.addElement(DSLAMBOIFile.SAVED_TIME				, file.getSavedTime());
		}
		return data;
	}

	public  static AEMFTMetadataElementComposite fromFileList(List<DSLAMBOIFile> fileList) {
		AEMFTMetadataElementComposite data = null;
		if (!AEMFTCommonUtilsBase.isEmptyList(fileList)) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			for (DSLAMBOIFile file : fileList) {
				data.addElement(file.getFilename(), fromFile(file));
			}
		}
		return data;
	}
	
	public  static AEMFTMetadataElementComposite fromFileIdName(DSLAMBOIFile file) {
		AEMFTMetadataElementComposite data = null;
		if (file != null) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();

			data.addElement(DSLAMBOIFile.FILE_ID				, file.getFileId());
			data.addElement(DSLAMBOIFile.FILE_NAME				, file.getFilename());
		}
		return data;
	}
	
	/**
	 * VARIABLE
	 */
	
	public  static AEMFTMetadataElementComposite fromVariable(DSLAMBOIVariable variable) {
		AEMFTMetadataElementComposite variableData = null;
		if (variable != null) {
			variableData 	= AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
			variableData.addElement(DSLAMBOIVariablesDataConstants.VARIABLE_NAME	, variable.getVariableName());
			variableData.addElement(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE	, variable.getVariableValue());
			variableData.addElement(DSLAMBOIVariablesDataConstants.VARIABLE_SCOPE	, variable.getVariableScope());
			variableData.addElement(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE	, variable.getVariableType());
		}
		return variableData;
	}
	
	public  static AEMFTMetadataElementComposite fromVariableList(List<DSLAMBOIVariable> variableList) {
		AEMFTMetadataElementComposite 	variableListData = null;
		if (!AEMFTCommonUtils.isEmptyList(variableList)) {
			variableListData = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			for (DSLAMBOIVariable variable : variableList) {
				AEMFTMetadataElementComposite variableData = fromVariable(variable);
				variableListData.addElement(variable.getVariableName()	, variableData);
			}
		}
		return variableListData;
	}
	
	/**
	 * PROJECT
	 */
	
	public  static AEMFTMetadataElementComposite fromProject(DSLAMBOIProject project) {
		AEMFTMetadataElementComposite data = null;
		if (project != null) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			
			data.addElement(DSLAMBOIProject.PROJECT_ID				, String.valueOf(project.getProjectId()));
			data.addElement(DSLAMBOIProject.PROJECT_NAME			, project.getProjectName());
			data.addElement(DSLAMBOIProject.PROJECT_MACHINE_TYPE	, project.getMachineType());
			data.addElement(DSLAMBOIProcess.CREATION_TIME			, project.getCreationTime());
			data.addElement(DSLAMBOIProcess.SAVED_TIME				, project.getSavedTime());
		}
		return data;
	}

	public  static AEMFTMetadataElementComposite fromProjectFull(DSLAMBOIProject project, Locale locale) {
		AEMFTMetadataElementComposite data = null;
		if (project != null) {
			data = fromProject(project);
			
			DSLAMBOIFile 	mainScript 		= project.getMainScript();
			DSLAMBOIFile 	rollBackScript 	= project.getRollBackScript();
			DSLAMBOIProcess process 		= project.getProcess();
			AEMFTMetadataElementComposite mainScriptData 		= DSLAMBUBomToMetadataConversor.fromFile(mainScript);
			AEMFTMetadataElementComposite rollBackScriptData 	= DSLAMBUBomToMetadataConversor.fromFile(rollBackScript);
			AEMFTMetadataElementComposite processData 			= DSLAMBUBomToMetadataConversor.fromProcessFull(process, locale);
			
			data.addElement(DSLAMBOIProject.PROJECT_MAIN_SCRIPT			, mainScriptData);
			data.addElement(DSLAMBOIProject.PROJECT_ROLLBACK_SCRIPT		, rollBackScriptData);
			data.addElement(DSLAMBOIProject.PROJECT_PROCESS				, processData);
		}
		return data;
	}

	public  static AEMFTMetadataElementComposite fromProjectList(List<DSLAMBOIProject> projectList, Locale locale) {
		AEMFTMetadataElementComposite dataProjectList = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		if (!AEMFTCommonUtilsBase.isEmptyList(projectList)) {
			for (DSLAMBOIProject project : projectList) {
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
			data.addElement(DSLAMBOIProcess.CREATION_TIME		, node.getCreationTime());
			data.addElement(DSLAMBOIProcess.SAVED_TIME			, node.getSavedTime());
			
			AEMFTMetadataElementComposite variableListData	= fromVariableList(node.getVariableList());
			data.addElement(CRONIOBOINode.NODE_VARIABLE_LIST	, variableListData);
		}
		return data;
	}

	public  static AEMFTMetadataElementComposite fromNodeList(List<CRONIOBOINode> nodeList) {
		AEMFTMetadataElementComposite dataNodeList = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		if (!AEMFTCommonUtilsBase.isEmptyList(nodeList)) {
			for (CRONIOBOINode node : nodeList) {
				dataNodeList.addElement(node.getNodeName(), fromNode(node));
			}
		}
		return dataNodeList;
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
			
			DSLAMBOIFile 	connectionScript 		= connection.getInitConnectionScript();
			DSLAMBOIFile 	disconnectionScript 	= connection.getCloseConnectionScript();
			List<DSLAMBOIVariable> variableList 	= connection.getConnectionVariables();
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

			data.addElement(CRONIOBOIExecution.EXECUTION_ID		, execution.getExecutionId());
			data.addElement(CRONIOBOIExecution.PROJECT_ID		, String.valueOf(execution.getProject().getProjectId()));
			data.addElement(CRONIOBOIExecution.CREATION_TIME	, execution.getCreationTime());
			data.addElement(CRONIOBOIExecution.DESTINATION_LOGS	, execution.getDestinationLogs());
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
			
//			data.addElement(CRONIOBOILog.FILENAME		, log.getFileName());
//			data.addElement(CRONIOBOILog.HOST			, log.getHost());
//			data.addElement(CRONIOBOILog.METHOD			, log.getMethod());
//			data.addElement(CRONIOBOILog.LOGGER_NAME	, log.getLoggerName());
//			data.addElement(CRONIOBOILog.LINENUMBER		, log.getLineNumber());
			
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
	 * PRIVATE
	 */

	private AEMFTIMetadataElementController getElementController() {
		if (elementController == null) {
			elementController = new AEMFTMetadataElementControllerImpl();
		}
		return elementController;
	}


}
