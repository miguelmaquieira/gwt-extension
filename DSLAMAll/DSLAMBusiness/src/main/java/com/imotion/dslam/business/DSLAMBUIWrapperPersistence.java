package com.imotion.dslam.business;

import com.imotion.dslam.backend.persistence.DSLAMBKIPersistenceFactory;
import com.selene.arch.exe.bus.AEMFTIBusinessWrapperPersistence;

public interface DSLAMBUIWrapperPersistence extends AEMFTIBusinessWrapperPersistence {
	
	public DSLAMBKIPersistenceFactory getAppFactoryPersistence();

}
