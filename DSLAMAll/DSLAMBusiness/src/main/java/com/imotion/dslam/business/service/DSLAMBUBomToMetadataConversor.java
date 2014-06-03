package com.imotion.dslam.business.service;

import java.util.List;

import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTIMetadataElementController;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.controller.AEMFTMetadataElementControllerImpl;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.core.appli.metadata.element.factory.AEMFTMetadataElementReflectionBasedFactory;

public class DSLAMBUBomToMetadataConversor {

	private AEMFTIMetadataElementController elementController = null;
	
	/**
	 * PROCESS
	 */

	public  static AEMFTMetadataElementComposite fromProcess(DSLAMBOIProcess process) {
		AEMFTMetadataElementComposite data = null;
		if (process != null) {
			data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();

			data.addElement(DSLAMBOIProcess.PROCESS_ID				, process.getProcessId());
			data.addElement(DSLAMBOIProcess.PROCESS_NAME			, process.getProcessName());
			data.addElement(DSLAMBOIProcess.PROCESS_SYNCHRONOUS		, process.isSynchronous());
			//data.addElement(DSLAMBOIProcess.PROCESS_SCHEDULE_LIST	, process.getScheduleList());
			//data.addElement(DSLAMBOIProcess.PROCESS_VARIABLE_LIST	, process.getVariableList());
			data.addElement(DSLAMBOIProcess.CREATION_TIME			, process.getCreationTime());
			data.addElement(DSLAMBOIProcess.SAVED_TIME				, process.getSavedTime());
			data.addElement(DSLAMBOIProcess.PROCESS_SCRIPT			, fromFile(process.getProcessScript()));
		}
		return data;
	}

	public  static AEMFTMetadataElementComposite fromProcessList(List<DSLAMBOIProcess> processList, List<DSLAMBOIFile> fileList) {
		AEMFTMetadataElementComposite data = null;
		data = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		if (!AEMFTCommonUtilsBase.isEmptyList(processList)) {
			AEMFTMetadataElementComposite dataProcessList = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
			for (DSLAMBOIProcess process : processList) {
				dataProcessList.addElement(process.getProcessName(), fromProcess(process));
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
	 * PRIVATE
	 */

	private AEMFTIMetadataElementController getElementController() {
		if (elementController == null) {
			elementController = new AEMFTMetadataElementControllerImpl();
		}
		return elementController;
	}


}
