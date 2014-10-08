package com.imotion.dslam.backend.persistence.service.file;

import java.util.Date;
import java.util.List;

import com.imotion.dslam.bom.CRONIOBOIFile;



public interface CRONIOBKIFilePersistenceService {

	CRONIOBOIFile addFile(CRONIOBOIFile file);

	CRONIOBOIFile updateFileContent(Long fileId, String content, Date date);
	
	CRONIOBOIFile updateFileContent(Long fileId, String content, String compiledContent, Date date);
	
	CRONIOBOIFile updateFileName(Long fileId, String filename);

	List<CRONIOBOIFile> getAllFiles();
	
	CRONIOBOIFile getFile(Long fileIdAsLong);

	void removeFile(Long fileIdAsLong);

}
