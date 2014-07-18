package com.imotion.dslam.backend.persistence.service.file;

import java.util.Date;
import java.util.List;

import com.imotion.dslam.bom.DSLAMBOIFile;



public interface DSLAMBKIFilePersistenceService {

	DSLAMBOIFile addFile(DSLAMBOIFile file);

	DSLAMBOIFile updateFileContent(Long fileId, String content, Date date);
	
	DSLAMBOIFile updateFileName(Long fileId, String filename);

	List<DSLAMBOIFile> getAllFiles();
	
	DSLAMBOIFile getFile(Long fileIdAsLong);

	void removeFile(Long fileIdAsLong);

}
