package com.imotion.dslam.business.service.utils;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIProcess;
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

	public  static AEMFTMetadataElementComposite fromProcessFull(DSLAMBOIProcess process) {
		AEMFTMetadataElementComposite data = null;
		if (process != null) {
			data =fromProcess(process);

			AEMFTMetadataElementComposite 	scheduleListData = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			List<Date> 						scheduleList = process.getScheduleList();
			
			if (scheduleList != null) {
				for (int i = 0; i < scheduleList.size(); i++) {
					Date date = scheduleList.get(i);
					scheduleListData.addElement(date.toString(), date);	
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
				dataProcessList.addElement(process.getProcessName(), fromProcessFull(process));
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
	 * Variable
	 */
	
	public  static AEMFTMetadataElementComposite fromVariable(DSLAMBOIVariable variable) {
		AEMFTMetadataElementComposite variableData = null;
		if (variable != null) {
			variableData 	= AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
			variableData.addElement(DSLAMBOIVariablesDataConstants.VARIABLE_NAME	, variable.getVariableName());
			variableData.addElement(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE	, variable.getVariableValue());
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

	public  static AEMFTMetadataElementComposite fromProjectFull(DSLAMBOIProject project) {
		AEMFTMetadataElementComposite data = null;
		if (project != null) {
			data = fromProject(project);
			
			DSLAMBOIFile 	mainScript 		= project.getMainScript();
			DSLAMBOIFile 	rollBackScript 	= project.getRollBackScript();
			DSLAMBOIProcess process 		= project.getProcess();
			AEMFTMetadataElementComposite mainScriptData 		= DSLAMBUBomToMetadataConversor.fromFile(mainScript);
			AEMFTMetadataElementComposite rollBackScriptData 	= DSLAMBUBomToMetadataConversor.fromFile(rollBackScript);
			AEMFTMetadataElementComposite processData 			= DSLAMBUBomToMetadataConversor.fromProcessFull(process);
			
			data.addElement(DSLAMBOIProject.PROJECT_MAIN_SCRIPT			, mainScriptData);
			data.addElement(DSLAMBOIProject.PROJECT_ROLLBACK_SCRIPT		, rollBackScriptData);
			data.addElement(DSLAMBOIProject.PROJECT_PROCESS				, processData);
		}
		return data;
	}

	public  static AEMFTMetadataElementComposite fromProjectList(List<DSLAMBOIProject> projectList) {
		AEMFTMetadataElementComposite dataProjectList = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		if (!AEMFTCommonUtilsBase.isEmptyList(projectList)) {
			for (DSLAMBOIProject project : projectList) {
				dataProjectList.addElement(String.valueOf(project.getProjectId()), fromProjectFull(project));
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
			data.addElement(CRONIOBOINode.NODE_MACHINE_TYPE		, node.getNodeType());
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
	 * PRIVATE
	 */

	private AEMFTIMetadataElementController getElementController() {
		if (elementController == null) {
			elementController = new AEMFTMetadataElementControllerImpl();
		}
		return elementController;
	}


}
