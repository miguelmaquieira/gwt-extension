package com.imotion.dslam.business.service.impl;

import java.util.Date;

import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIFileDataConstants;
import com.imotion.dslam.bom.data.DSLAMBOFile;
import com.imotion.dslam.business.service.DSLAMBUBomToMetadataConversor;
import com.imotion.dslam.business.service.DSLAMBUBusinessServiceBase;
import com.imotion.dslam.business.service.DSLAMBUIBusinessFileServiceTrace;
import com.imotion.dslam.business.service.DSLAMBUIFileBusinessService;
import com.imotion.dslam.business.service.DSLAMBUIFileBusinessServiceConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

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
		traceNewItemPersistent(DSLAMBUIFileBusinessServiceConstants.METHOD_ADD_FILE, DSLAMBOIFile.class.getSimpleName(), String.valueOf(file.getFileId()));
		//end-trace
		
		//ContextOut
		AEMFTMetadataElementComposite fileDataElement = DSLAMBUBomToMetadataConversor.fromFile(file);
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(FILE_DATA, fileDataElement);
	}

}
