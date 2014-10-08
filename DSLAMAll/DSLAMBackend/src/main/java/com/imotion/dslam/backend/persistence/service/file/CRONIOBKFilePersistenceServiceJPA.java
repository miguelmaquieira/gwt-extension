package com.imotion.dslam.backend.persistence.service.file;

import java.util.Date;
import java.util.List;

import com.imotion.dslam.backend.persistence.jpa.CRONIOBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.CRONIOBOIFile;
import com.imotion.dslam.bom.data.CRONIOBOFile;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;

public class CRONIOBKFilePersistenceServiceJPA extends CRONIOBKPersistenceServiceBaseJPA<CRONIOBOIFile, CRONIOBOFile, Long> implements CRONIOBKIFilePersistenceService {

	private static final long serialVersionUID = -3323885263942965150L;

	@Override
	public CRONIOBOIFile addFile(CRONIOBOIFile file) {
		CRONIOBOFile fileJPA = (CRONIOBOFile) file;
		fileJPA = getPersistenceModule().create(fileJPA);
		return fileJPA;
	}

	@Override
	public CRONIOBOIFile updateFileContent(Long fileId, String content, Date date) {
		CRONIOBOFile file = getPersistenceModule().get(fileId);
		if (file != null) {
			file.setContent(content);
			if (date == null) {
				date = new Date();
			}
			file.setSavedTime(date);
			file = getPersistenceModule().update(file);
		}
		return file;
	}
	
	@Override
	public CRONIOBOIFile updateFileContent(Long fileId, String content, String compiledContent,Date date) {
		CRONIOBOFile file = getPersistenceModule().get(fileId);
		if (file != null) {
			file.setContent(content);
			file.setCompiledContent(compiledContent);
			if (date == null) {
				date = new Date();
			}
			file.setSavedTime(date);
			file = getPersistenceModule().update(file);
		}
		return file;
	}

	@Override
	public CRONIOBOIFile updateFileName(Long fileId, String filename) {
		CRONIOBOFile file = getPersistenceModule().get(fileId);
		file.setFilename(filename);
		file.setSavedTime(new Date());
		file = getPersistenceModule().update(file);
		return file;
	}

	@Override
	public List<CRONIOBOIFile> getAllFiles() {
		List<CRONIOBOFile> fileListJpa = getPersistenceModule().findAll();
		return AEMFTCommonUtilsBase.castList(fileListJpa);
	}

	@Override
	public CRONIOBOIFile getFile(Long fileIdAsLong) {
		CRONIOBOFile fileJpa = getPersistenceModule().get(fileIdAsLong);
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
	public Class<CRONIOBOFile> getPersistenceClass() {
		return CRONIOBOFile.class;
	}

}
