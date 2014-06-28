package com.imotion.dslam.business.service.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.CRONIOBOIProjectDataConstants;
import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.bom.DSLAMBOIVariable;
import com.imotion.dslam.bom.data.CRONIOBONode;
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

public class CRONIOMetadataToBom {

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
				if (!DSLAMBOIProject.IS_MODIFIED.equals(nodeDataElement.getKey())) {
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

			String	nodeId			= getElementController().getElementAsString(CRONIOBOINode.NODE_ID						, nodeDataElement);
			long	nodeIdAsLong	= AEMFTCommonUtils.getLongFromString(nodeId);
			String	nodeName		= getElementController().getElementAsString(CRONIOBOINode.NODE_NAME						, nodeDataElement);
			String	nodeIp			= getElementController().getElementAsString(CRONIOBOINode.NODE_IP						, nodeDataElement);
			int		nodeType		= getElementController().getElementAsInt(CRONIOBOINode.NODE_MACHINE_TYPE				, nodeDataElement);
			//Date	savedTime		= (Date) getElementController().getElementAsSerializable(CRONIOBOINode.SAVED_TIME		, nodeDataElement);
			Date	creationTime	= (Date) getElementController().getElementAsSerializable(CRONIOBOINode.CREATION_TIME	, nodeDataElement);

			node.setNodeId(nodeIdAsLong);
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
				if (!CRONIOBOIProjectDataConstants.IS_MODIFIED.equals(dataKey)) {
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
				if (!CRONIOBOIProjectDataConstants.IS_MODIFIED.equals(dataKey)) {
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
			String 	variableName	= getElementController().getElementAsString(DSLAMBOIVariable.VARIABLE_NAME	, variableData);
			String 	variableValue	= getElementController().getElementAsString(DSLAMBOIVariable.VARIABLE_VALUE	, variableData);
			int		variableType	= getElementController().getElementAsInt(DSLAMBOIVariable.VARIABLE_TYPE		, variableData);

			variable = new DSLAMBOVariable();
			variable.setVariableName(variableName);
			variable.setVariableValue(variableValue);
			variable.setVariableType(variableType);
		}
		return variable;
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
