package com.imotion.dslam.backend.persistence;

import com.imotion.dslam.backend.persistence.service.computedtransitdata.DSLAMBKIComputedTransitDataPersistenceService;
import com.imotion.dslam.backend.persistence.service.transitdata.DSLAMBKITransitDataPersistenceService;
import com.selene.arch.exe.back.persistence.AEMFTIPersistenceFactory;

public interface DSLAMBKIPersistenceFactory extends AEMFTIPersistenceFactory {

	public	DSLAMBKITransitDataPersistenceService			newTransitDataPersistence();
	
	public	DSLAMBKIComputedTransitDataPersistenceService	newComputedTransitDataPersistence();
	
}
