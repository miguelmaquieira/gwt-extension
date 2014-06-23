package com.imotion.dslam.business.service.utils;

import java.util.Date;

import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.bom.data.DSLAMBOFile;
import com.imotion.dslam.bom.data.DSLAMBOProcess;
import com.imotion.dslam.bom.data.DSLAMBOProject;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTIMetadataElementController;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.controller.AEMFTMetadataElementControllerImpl;
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
			boolean		sinchronous		= getElementController().getElementAsBoolean(DSLAMBOIProcess.PROCESS_SYNC_OPTION		, processData);
			Date		creationTime	= (Date) getElementController().getElementAsSerializable(DSLAMBOIProcess.CREATION_TIME	, processData);
			Date		savedTime		= (Date) getElementController().getElementAsSerializable(DSLAMBOIProcess.SAVED_TIME		, processData);
			
			//process variables
			AEMFTMetadataElementComposite variablesData = getElementController().getElementAsComposite(DSLAMBOProcess.PROCESS_VARIABLE_LIST, processData);
//			getElementController().
		}
		return process;
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
