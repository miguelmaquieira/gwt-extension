package com.imotion.dslam.business.service;

import com.imotion.dslam.bom.DSLAMBOIFile;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTIMetadataElementController;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.controller.AEMFTMetadataElementControllerImpl;
import com.selene.arch.exe.core.appli.metadata.element.factory.AEMFTMetadataElementReflectionBasedFactory;

public class DSLAMBUBomToMetadataConversor {
	
	private AEMFTIMetadataElementController elementController = null;
	
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
