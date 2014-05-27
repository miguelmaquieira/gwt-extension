package com.imotion.dslam.backend.persistence;

import com.imotion.dslam.backend.persistence.service.file.DSLAMBKIFilePersistenceService;
import com.selene.arch.exe.back.persistence.AEMFTIPersistenceFactory;

public interface DSLAMBKIPersistenceFactory extends AEMFTIPersistenceFactory {

	public	DSLAMBKIFilePersistenceService newFilePersistence();
	
}
