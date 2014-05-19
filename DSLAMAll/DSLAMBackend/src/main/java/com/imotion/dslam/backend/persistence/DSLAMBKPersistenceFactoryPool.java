package com.imotion.dslam.backend.persistence;

import com.imotion.dslam.backend.persistence.service.computedtransitdata.DSLAMBKIComputedTransitDataPersistenceService;
import com.imotion.dslam.backend.persistence.service.transitdata.DSLAMBKITransitDataPersistenceService;
import com.selene.arch.exe.back.persistence.AEMFTPersistenceFactoryPool;
import com.selene.arch.exe.core.AEMFTICoreProxyService;
import com.selene.arch.exe.core.envi.config.AEMFTIConfigurationService;

public class DSLAMBKPersistenceFactoryPool extends AEMFTPersistenceFactoryPool implements DSLAMBKIPersistenceFactory {

	public DSLAMBKPersistenceFactoryPool(AEMFTICoreProxyService coreProxy) {
		super(coreProxy);
	}
	
	@Override
	public DSLAMBKITransitDataPersistenceService newTransitDataPersistence() {
		String impl = DSLAMBKIPersistenceConstants.CTE_DSLAM_PERSISTENCE_DSLAMIT_DATA_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					DSLAMBKIPersistenceConstants.CFG_DSLAM_PERSISTENCE_DSLAMIT_DATA_PERSISTENCE_IMPL,
					DSLAMBKIPersistenceConstants.CTE_DSLAM_PERSISTENCE_DSLAMIT_DATA_PERSISTENCE_DEFAULT_IMPL);
		}
		return (DSLAMBKITransitDataPersistenceService) newPersistenceModule(impl);
	}
	
	@Override
	public DSLAMBKIComputedTransitDataPersistenceService newComputedTransitDataPersistence() {
		String impl = DSLAMBKIPersistenceConstants.CTE_DSLAM_PERSISTENCE_DSLAMIT_DATA_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					DSLAMBKIPersistenceConstants.CFG_DSLAM_PERSISTENCE_COMPUTED_DSLAMIT_DATA_PERSISTENCE_IMPL,
					DSLAMBKIPersistenceConstants.CTE_DSLAM_PERSISTENCE_COMPUTED_DSLAMIT_DATA_PERSISTENCE_DEFAULT_IMPL);
		}
		return (DSLAMBKIComputedTransitDataPersistenceService) newPersistenceModule(impl);
	}

	/***********************************************************************
	 * 					      PRIVATE FUNCTION                             *
	 ***********************************************************************/
	private AEMFTIConfigurationService getConfigSrv() {
		return getCoreProxy().getConfigurationService();
	}


}
