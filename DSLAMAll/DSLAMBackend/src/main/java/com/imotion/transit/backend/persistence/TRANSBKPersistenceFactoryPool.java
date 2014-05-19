package com.imotion.transit.backend.persistence;

import com.imotion.transit.backend.persistence.service.computedtransitdata.TRANSBKIComputedTransitDataPersistenceService;
import com.imotion.transit.backend.persistence.service.transitdata.TRANSBKITransitDataPersistenceService;
import com.selene.arch.exe.back.persistence.AEMFTPersistenceFactoryPool;
import com.selene.arch.exe.core.AEMFTICoreProxyService;
import com.selene.arch.exe.core.envi.config.AEMFTIConfigurationService;

public class TRANSBKPersistenceFactoryPool extends AEMFTPersistenceFactoryPool implements TRANSBKIPersistenceFactory {

	public TRANSBKPersistenceFactoryPool(AEMFTICoreProxyService coreProxy) {
		super(coreProxy);
	}
	
	@Override
	public TRANSBKITransitDataPersistenceService newTransitDataPersistence() {
		String impl = TRANSBKIPersistenceConstants.CTE_TRANS_PERSISTENCE_TRANSIT_DATA_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					TRANSBKIPersistenceConstants.CFG_TRANS_PERSISTENCE_TRANSIT_DATA_PERSISTENCE_IMPL,
					TRANSBKIPersistenceConstants.CTE_TRANS_PERSISTENCE_TRANSIT_DATA_PERSISTENCE_DEFAULT_IMPL);
		}
		return (TRANSBKITransitDataPersistenceService) newPersistenceModule(impl);
	}
	
	@Override
	public TRANSBKIComputedTransitDataPersistenceService newComputedTransitDataPersistence() {
		String impl = TRANSBKIPersistenceConstants.CTE_TRANS_PERSISTENCE_TRANSIT_DATA_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					TRANSBKIPersistenceConstants.CFG_TRANS_PERSISTENCE_COMPUTED_TRANSIT_DATA_PERSISTENCE_IMPL,
					TRANSBKIPersistenceConstants.CTE_TRANS_PERSISTENCE_COMPUTED_TRANSIT_DATA_PERSISTENCE_DEFAULT_IMPL);
		}
		return (TRANSBKIComputedTransitDataPersistenceService) newPersistenceModule(impl);
	}

	/***********************************************************************
	 * 					      PRIVATE FUNCTION                             *
	 ***********************************************************************/
	private AEMFTIConfigurationService getConfigSrv() {
		return getCoreProxy().getConfigurationService();
	}


}
