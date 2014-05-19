package com.imotion.transit.business.service;

import com.imotion.transit.backend.persistence.TRANSBKIPersistenceFactory;
import com.imotion.transit.backend.persistence.TRANSBKPersistenceServiceBase;
import com.imotion.transit.business.TRANSBUIWrapperPersistence;
import com.selene.arch.exe.bus.service.AEMFTBusinessServiceWrapperPersistence;
import com.selene.arch.exe.core.appli.businesswrapper.AEMFTIBusinessWrapperService;

public class TRANSBUBusinessWrapperPersistence extends AEMFTBusinessServiceWrapperPersistence implements TRANSBUIWrapperPersistence {

	// serial number
	private static final long serialVersionUID = -5343514754637590997L;
	
	private TRANSBKIPersistenceFactory factoryPool;

	@Override
	public TRANSBKIPersistenceFactory getAppFactoryPersistence() {
		if (factoryPool == null) {
			factoryPool = (TRANSBKIPersistenceFactory) TRANSBKPersistenceServiceBase.getFactoryPersistence(getBusinessWrapper().getProxyCore());
		}
		return factoryPool;
	}

	@Override
	public void setWrapperService(AEMFTIBusinessWrapperService wrapperService) {
		super.setWrapperService(wrapperService);
		factoryPool = (TRANSBKIPersistenceFactory) TRANSBKPersistenceServiceBase.getFactoryPersistence(wrapperService.getProxyCore());
	}
}
