package com.imotion.transit.business;

import com.imotion.transit.backend.persistence.TRANSBKIPersistenceFactory;
import com.selene.arch.exe.bus.AEMFTIBusinessWrapperPersistence;

public interface TRANSBUIWrapperPersistence extends AEMFTIBusinessWrapperPersistence {
	
	public TRANSBKIPersistenceFactory getAppFactoryPersistence();

}
