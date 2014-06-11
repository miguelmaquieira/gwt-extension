package com.imotion.dslam.backend.persistence.service.file;

import java.util.Date;
import java.util.List;

import com.imotion.dslam.backend.persistence.jpa.DSLAMBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.data.DSLAMBOFile;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;

public class DSLAMBKFilePersistenceServiceJPA extends DSLAMBKPersistenceServiceBaseJPA<DSLAMBOIFile, DSLAMBOFile, Long> implements DSLAMBKIFilePersistenceService {

	private static final long serialVersionUID = -3323885263942965150L;
	
	@Override
	public DSLAMBOIFile addFile(DSLAMBOIFile file) {
		DSLAMBOFile fileJPA = (DSLAMBOFile) file;
		fileJPA = getPersistenceModule().create(fileJPA);
		return fileJPA;
	}
	
	@Override
	public DSLAMBOIFile updateFileContent(Long fileId, String content) {
		DSLAMBOFile file = getPersistenceModule().get(fileId);
		file.setContent(content);
		file.setSavedTime(new Date());
		file = getPersistenceModule().update(file);
		return file;
	}
	
	@Override
	public DSLAMBOIFile updateFileName(Long fileId, String filename) {
		DSLAMBOFile file = getPersistenceModule().get(fileId);
		file.setFilename(filename);
		file.setSavedTime(new Date());
		file = getPersistenceModule().update(file);
		return file;
	}
	
	@Override
	public List<DSLAMBOIFile> getAllFiles() {
		List<DSLAMBOFile> fileListJpa = getPersistenceModule().findAll();
		return AEMFTCommonUtilsBase.castList(fileListJpa);
	}
	
	@Override
	public DSLAMBOIFile getFile(Long fileIdAsLong) {
		DSLAMBOFile fileJpa = getPersistenceModule().get(fileIdAsLong);
		return fileJpa;
	}
	
	@Override
	public void removeFile(Long fileIdAsLong) {
		getPersistenceModule().remove(fileIdAsLong);
	}

	/**
	 * AEMFTIHasPersistenceModule
	 */
	@Override
	public Class<DSLAMBOFile> getPersistenceClass() {
		return DSLAMBOFile.class;
	}

}
