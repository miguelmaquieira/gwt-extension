package com.imotion.dslam.business.service.base;

import com.imotion.dslam.backend.persistence.DSLAMBKIPersistenceFactory;
import com.imotion.dslam.backend.persistence.DSLAMBKPersistenceServiceBase;
import com.imotion.dslam.business.DSLAMBUIWrapperPersistence;
import com.selene.arch.exe.bus.service.AEMFTBusinessServiceWrapperPersistence;

public class DSLAMBUWrapperPersistence extends AEMFTBusinessServiceWrapperPersistence implements DSLAMBUIWrapperPersistence {

	// serial number
	private static final long serialVersionUID = -5343514754637590997L;
	
	private DSLAMBKIPersistenceFactory factoryPool;

	@Override
	public DSLAMBKIPersistenceFactory getAppFactoryPersistence() {
		if (factoryPool == null) {
			factoryPool = (DSLAMBKIPersistenceFactory) DSLAMBKPersistenceServiceBase.getFactoryPersistence(getBusinessWrapper().getProxyCore());
		}
		return factoryPool;
	}

}
