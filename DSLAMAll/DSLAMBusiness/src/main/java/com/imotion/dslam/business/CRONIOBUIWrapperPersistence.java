package com.imotion.dslam.business;

import com.imotion.dslam.backend.persistence.CRONIOBKIPersistenceFactory;
import com.selene.arch.exe.bus.AEMFTIBusinessWrapperPersistence;

public interface CRONIOBUIWrapperPersistence extends AEMFTIBusinessWrapperPersistence {
	
	public CRONIOBKIPersistenceFactory getAppFactoryPersistence();

}
