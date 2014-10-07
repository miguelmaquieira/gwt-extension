package com.imotion.dslam.business.service.base;

import com.imotion.dslam.backend.persistence.CRONIOBKIPersistenceFactory;
import com.imotion.dslam.backend.persistence.CRONIOBKPersistenceServiceBase;
import com.imotion.dslam.business.CRONIOBUIWrapperPersistence;
import com.selene.arch.exe.bus.service.AEMFTBusinessServiceWrapperPersistence;

public class DSLAMBUWrapperPersistence extends AEMFTBusinessServiceWrapperPersistence implements CRONIOBUIWrapperPersistence {

	// serial number
	private static final long serialVersionUID = -5343514754637590997L;
	
	private CRONIOBKIPersistenceFactory factoryPool;

	@Override
	public CRONIOBKIPersistenceFactory getAppFactoryPersistence() {
		if (factoryPool == null) {
			factoryPool = (CRONIOBKIPersistenceFactory) CRONIOBKPersistenceServiceBase.getFactoryPersistence(getBusinessWrapper().getProxyCore());
		}
		return factoryPool;
	}

}
