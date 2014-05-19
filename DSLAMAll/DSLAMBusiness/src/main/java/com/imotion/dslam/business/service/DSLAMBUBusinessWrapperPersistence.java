package com.imotion.dslam.business.service;

import com.imotion.dslam.backend.persistence.DSLAMBKIPersistenceFactory;
import com.imotion.dslam.backend.persistence.DSLAMBKPersistenceServiceBase;
import com.imotion.dslam.business.DSLAMBUIWrapperPersistence;
import com.selene.arch.exe.bus.service.AEMFTBusinessServiceWrapperPersistence;
import com.selene.arch.exe.core.appli.businesswrapper.AEMFTIBusinessWrapperService;

public class DSLAMBUBusinessWrapperPersistence extends AEMFTBusinessServiceWrapperPersistence implements DSLAMBUIWrapperPersistence {

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

	@Override
	public void setWrapperService(AEMFTIBusinessWrapperService wrapperService) {
		super.setWrapperService(wrapperService);
		factoryPool = (DSLAMBKIPersistenceFactory) DSLAMBKPersistenceServiceBase.getFactoryPersistence(wrapperService.getProxyCore());
	}
}
