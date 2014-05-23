package com.imotion.dslam.backend.persistence.service.file;

import java.util.Date;

import com.imotion.dslam.backend.persistence.jpa.DSLAMBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.data.DSLAMBOFile;

public class DSLAMBKFilePersistenceServiceJPA extends DSLAMBKPersistenceServiceBaseJPA<DSLAMBOIFile, DSLAMBOFile, Long> implements DSLAMBKIFilePersistenceService {

	private static final long serialVersionUID = -3323885263942965150L;
	
	@Override
	public DSLAMBOIFile addFile(DSLAMBOIFile file) {
		DSLAMBOFile fileJPA = (DSLAMBOFile) file;
		fileJPA = getPersistenceModule().create(fileJPA);
		return fileJPA;
	}
	
	@Override
	public DSLAMBOIFile updateFile(Long fileId, String content) {
		DSLAMBOFile file = getPersistenceModule().get(fileId);
		file.setContent(content);
		file.setSavedTime(new Date());
		file = getPersistenceModule().update(file);
		return file;
	}

	/**
	 * AEMFTIHasPersistenceModule
	 */
	@Override
	public Class<DSLAMBOFile> getPersistenceClass() {
		return DSLAMBOFile.class;
	}

}
