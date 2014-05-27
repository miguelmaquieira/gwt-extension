package com.imotion.dslam.backend.persistence.service.file;

import java.util.List;

import com.imotion.dslam.bom.DSLAMBOIFile;



public interface DSLAMBKIFilePersistenceService {

	DSLAMBOIFile addFile(DSLAMBOIFile file);

	DSLAMBOIFile updateFileContent(Long fileId, String content);
	
	DSLAMBOIFile updateFileName(Long fileId, String filename);

	List<DSLAMBOIFile> getAllFiles();

}
