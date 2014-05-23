package com.imotion.dslam.backend.persistence.service.file;

import com.imotion.dslam.bom.DSLAMBOIFile;



public interface DSLAMBKIFilePersistenceService {

	DSLAMBOIFile addFile(DSLAMBOIFile file);

	DSLAMBOIFile updateFile(Long fileId, String content);

}
