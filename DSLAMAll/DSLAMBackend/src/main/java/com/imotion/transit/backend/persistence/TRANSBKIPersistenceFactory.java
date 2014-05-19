package com.imotion.transit.backend.persistence;

import com.imotion.transit.backend.persistence.service.computedtransitdata.TRANSBKIComputedTransitDataPersistenceService;
import com.imotion.transit.backend.persistence.service.transitdata.TRANSBKITransitDataPersistenceService;
import com.selene.arch.exe.back.persistence.AEMFTIPersistenceFactory;

public interface TRANSBKIPersistenceFactory extends AEMFTIPersistenceFactory {

	public	TRANSBKITransitDataPersistenceService			newTransitDataPersistence();
	
	public	TRANSBKIComputedTransitDataPersistenceService	newComputedTransitDataPersistence();
	
}
