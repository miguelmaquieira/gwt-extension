package com.imotion.dslam.business.service.impl;

import java.util.Date;
import java.util.List;

import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIFileDataConstants;
import com.imotion.dslam.bom.data.DSLAMBOFile;
import com.imotion.dslam.business.service.DSLAMBUBomToMetadataConversor;
import com.imotion.dslam.business.service.DSLAMBUBusinessServiceBase;
import com.imotion.dslam.business.service.DSLAMBUIBusinessFileServiceTrace;
import com.imotion.dslam.business.service.DSLAMBUIFileBusinessService;
import com.imotion.dslam.business.service.DSLAMBUIFileBusinessServiceConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.core.appli.metadata.element.factory.AEMFTMetadataElementReflectionBasedFactory;

public class DSLAMBUFileBusinessServiceImpl extends DSLAMBUBusinessServiceBase implements DSLAMBUIFileBusinessService, DSLAMBUIFileBusinessServiceConstants, DSLAMBUIBusinessFileServiceTrace {

	private static final long serialVersionUID = -6461242345046034538L;

	@Override
	public void addFile() {
		//ContextIn
		AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();
		String filename		= getElementDataController().getElementAsString(DSLAMBOIFileDataConstants.FILE_NAME		, contextIn);
		String contentType	= getElementDataController().getElementAsString(DSLAMBOIFileDataConstants.CONTENT_TYPE	, contextIn);
		String content		= getElementDataController().getElementAsString(DSLAMBOIFileDataConstants.CONTENT			, contextIn);

		Date creationTime = new Date();
		DSLAMBOIFile file = new DSLAMBOFile();
		file.setFilename(filename);
		file.setContentType(contentType);
		file.setContent(content);
		file.setCreationTime(creationTime);
		file.setSavedTime(creationTime);
		file = getFilePersistence().addFile(file);

		//init-trace
		traceNewItemPersistent(METHOD_ADD_FILE, DSLAMBOIFile.class.getSimpleName(), String.valueOf(file.getFileId()));
		//end-trace

		//ContextOut
		AEMFTMetadataElementComposite fileDataElement = DSLAMBUBomToMetadataConversor.fromFile(file);
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(FILE_DATA, fileDataElement);
	}

	@Override
	public void updateFile() {
		//ContextIn
		AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();
		String fileId		= getElementDataController().getElementAsString(DSLAMBOIFileDataConstants.FILE_ID		, contextIn);
		String filename		= getElementDataController().getElementAsString(DSLAMBOIFileDataConstants.FILE_NAME		, contextIn);
		String content		= getElementDataController().getElementAsString(DSLAMBOIFileDataConstants.CONTENT		, contextIn);
		
		Long fileIdAsLong 	= AEMFTCommonUtilsBase.getLongFromString(fileId);
		DSLAMBOIFile updatedfile = null;
		if (AEMFTCommonUtilsBase.isEmptyString(filename)) {
			updatedfile = getFilePersistence().updateFileContent(fileIdAsLong	, content);
		} else {
			updatedfile = getFilePersistence().updateFileName(fileIdAsLong		, filename);
		}

		//init-trace
		traceItemModifiedInPersistence(METHOD_UPDATE_FILE, DSLAMBOIFile.class.getSimpleName(), fileId);
		//end-trace

		//ContextOut
		AEMFTMetadataElementComposite fileDataElement = DSLAMBUBomToMetadataConversor.fromFile(updatedfile);
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(FILE_DATA, fileDataElement);
	}
	
	@Override
	public void removeFile() {
		//ContextIn
		AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();
		String fileId		= getElementDataController().getElementAsString(DSLAMBOIFileDataConstants.FILE_ID		, contextIn);
		
		Long fileIdAsLong 	= AEMFTCommonUtilsBase.getLongFromString(fileId);
		getFilePersistence().removeFile(fileIdAsLong);
		
		//init-trace
		traceItemRemovedFromPersistence(METHOD_REMOVE_FILE, DSLAMBOIFile.class.getSimpleName(), fileId);
		//end-trace

		//ContextOut
		AEMFTMetadataElementComposite fileDataElement = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		fileDataElement.addElement(DSLAMBOIFileDataConstants.FILE_ID, fileIdAsLong);
		
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(FILE_DATA, fileDataElement);
	}

	
	@Override
	public void getAllFiles() {
		List<DSLAMBOIFile> fileList = getFilePersistence().getAllFiles();
		
		//trace-init
		int resultsNumber = 0;
		if (!AEMFTCommonUtilsBase.isEmptyList(fileList)) {
			resultsNumber = fileList.size();
		}
		traceNumberOfResults(METHOD_GET_ALL_FILES, DSLAMBOIFile.class.getSimpleName(), resultsNumber);
		//end-trace
		
		//ContextOut
		AEMFTMetadataElementComposite fileDataElement = DSLAMBUBomToMetadataConversor.fromFileList(fileList);
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(FILE_DATA, fileDataElement);
	}

}
