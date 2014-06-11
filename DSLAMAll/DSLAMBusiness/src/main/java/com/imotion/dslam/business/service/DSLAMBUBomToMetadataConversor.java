package com.imotion.dslam.business.service;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.bom.DSLAMBOIVariablesDataConstants;
import com.imotion.dslam.bom.data.DSLAMBOVariable;
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
	
	public  static AEMFTMetadataElementComposite fromProcess(DSLAMBOIProcess process, Locale locale) {
		AEMFTMetadataElementComposite data = null;
		if (process != null) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			
			data.addElement(DSLAMBOIProcess.PROCESS_ID				, process.getProcessId());
			data.addElement(DSLAMBOIProcess.PROCESS_NAME			, process.getProcessName());
			data.addElement(DSLAMBOIProcess.CREATION_TIME			, process.getCreationTime());
			data.addElement(DSLAMBOIProcess.SAVED_TIME				, process.getSavedTime());
		}
		return data;
	}

	public  static AEMFTMetadataElementComposite fromProcessFull(DSLAMBOIProcess process, Locale locale) {
		AEMFTMetadataElementComposite data = null;
		if (process != null) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();

			AEMFTMetadataElementComposite 	scheduleListData = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			List<Date> 						scheduleList = process.getScheduleList();
			for (int i = 0; i < scheduleList.size(); i++) {
				Date date = scheduleList.get(i);
				String formatDate = "dd/MM/yyyy HH:mm";
				String dateString = AEMFTCommonUtils.formatDate(date, formatDate, locale);
				scheduleListData.addElement(String.valueOf(i), dateString);	
			}
			
			AEMFTMetadataElementComposite 	variableListData = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			List<DSLAMBOVariable> 			variableList = process.getVariableList();
			for (int i = 0; i < variableList.size(); i++) {
				
				AEMFTMetadataElementComposite 	variableData 	= AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
				DSLAMBOVariable 				variable 		= variableList.get(i);
				variableData.addElement(DSLAMBOIVariablesDataConstants.VARIABLE_ID		, variable.getVariableName());
				variableData.addElement(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE	, variable.getVariableValue());
				variableData.addElement(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE	, String.valueOf(variable.getVariableType()));
				variableListData.addElement(variable.getVariableName()	, variableData);
			}
				
				
			
			data.addElement(DSLAMBOIProcess.PROCESS_ID				, process.getProcessId());
			data.addElement(DSLAMBOIProcess.PROCESS_NAME			, process.getProcessName());
			data.addElement(DSLAMBOIProcess.PROCESS_SYNCHRONOUS		, process.isSynchronous());
			data.addElement(DSLAMBOIProcess.PROCESS_SCHEDULE_LIST	, scheduleListData);
			data.addElement(DSLAMBOIProcess.PROCESS_VARIABLE_LIST	, variableListData);
			data.addElement(DSLAMBOIProcess.CREATION_TIME			, process.getCreationTime());
			data.addElement(DSLAMBOIProcess.SAVED_TIME				, process.getSavedTime());
		}
		return data;
	}

	public  static AEMFTMetadataElementComposite fromProcessList(List<DSLAMBOIProcess> processList, List<DSLAMBOIFile> fileList, Locale locale) {
		AEMFTMetadataElementComposite data = null;
		data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		if (!AEMFTCommonUtilsBase.isEmptyList(processList)) {
			AEMFTMetadataElementComposite dataProcessList = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			for (DSLAMBOIProcess process : processList) {
				dataProcessList.addElement(process.getProcessName(), fromProcessFull(process,locale));
			}
			data.addElement(DSLAMBUIProcessBusinessServiceConstants.PROCESS_DATA_LIST,dataProcessList);
		}
		
		if (!AEMFTCommonUtilsBase.isEmptyList(fileList)) {
			AEMFTMetadataElementComposite dataFilesList = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			
			for (DSLAMBOIFile file : fileList) {
				dataFilesList.addElement(file.getFilename(),fromFileIdName(file));
			}
			data.addElement(DSLAMBUIProcessBusinessServiceConstants.PROCESS_FILE_DATA_LIST,dataFilesList);
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

			data.addElement(DSLAMBOIFile.FILE_ID				, file.getFileId());
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
	 * PROJECT
	 */
	
	public  static AEMFTMetadataElementComposite fromProject(DSLAMBOIProject project, Locale locale) {
		AEMFTMetadataElementComposite data = null;
		if (project != null) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			
			data.addElement(DSLAMBOIProject.PROJECT_ID				, project.getProjectId());
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
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			
			DSLAMBOIFile 	mainScript 		= project.getMainScript();
			DSLAMBOIFile 	rollBackScript 	= project.getRollBackScript();
			DSLAMBOIProcess process 		= project.getProcess();
			AEMFTMetadataElementComposite mainScriptData 		= DSLAMBUBomToMetadataConversor.fromFile(mainScript);
			AEMFTMetadataElementComposite rollBackScriptData 	= DSLAMBUBomToMetadataConversor.fromFile(rollBackScript);
			AEMFTMetadataElementComposite processData 			= DSLAMBUBomToMetadataConversor.fromProcess(process, locale);
			
			data.addElement(DSLAMBOIProject.PROJECT_ID				, project.getProjectId());
			data.addElement(DSLAMBOIProject.PROJECT_NAME			, project.getProjectName());
			data.addElement(DSLAMBOIProject.PROJECT_MACHINE_TYPE	, project.getMachineType());
			data.addElement(DSLAMBOIProject.PROJECT_SCRIPT			, mainScriptData);
			data.addElement(DSLAMBOIProject.PROJECT_ROLLBACK_SCRIPT	, rollBackScriptData);
			data.addElement(DSLAMBOIProject.PROJECT_PROCESS			, processData);
			data.addElement(DSLAMBOIProject.CREATION_TIME			, project.getCreationTime());
			data.addElement(DSLAMBOIProject.SAVED_TIME				, project.getSavedTime());
		}
		return data;
	}

	public  static AEMFTMetadataElementComposite fromProjectList(List<DSLAMBOIProject> projectList,Locale locale) {
		AEMFTMetadataElementComposite dataProjectList = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		if (!AEMFTCommonUtilsBase.isEmptyList(projectList)) {
			for (DSLAMBOIProject project : projectList) {
				dataProjectList.addElement(project.getProjectName(), fromProjectFull(project,locale));
			}
		}
		return dataProjectList;
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
