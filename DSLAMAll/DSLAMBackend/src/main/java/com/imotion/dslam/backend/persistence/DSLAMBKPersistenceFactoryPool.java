package com.imotion.dslam.backend.persistence;

import com.imotion.dslam.backend.persistence.service.file.DSLAMBKIFilePersistenceService;
import com.selene.arch.exe.back.persistence.AEMFTPersistenceFactoryPool;
import com.selene.arch.exe.core.AEMFTICoreProxyService;
import com.selene.arch.exe.core.envi.config.AEMFTIConfigurationService;

public class DSLAMBKPersistenceFactoryPool extends AEMFTPersistenceFactoryPool implements DSLAMBKIPersistenceFactory {

	public DSLAMBKPersistenceFactoryPool(AEMFTICoreProxyService coreProxy) {
		super(coreProxy);
	}
	
	@Override
	public DSLAMBKIFilePersistenceService newFilePersistence() {
		String impl = DSLAMBKIPersistenceConstants.CTE_DSLAM_PERSISTENCE_FILE_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					DSLAMBKIPersistenceConstants.CFG_DSLAM_PERSISTENCE_FILE_PERSISTENCE_IMPL,
					DSLAMBKIPersistenceConstants.CTE_DSLAM_PERSISTENCE_FILE_PERSISTENCE_DEFAULT_IMPL);
		}
		return (DSLAMBKIFilePersistenceService) newPersistenceModule(impl);
	}
	
	/***********************************************************************
	 * 					      PRIVATE FUNCTION                             *
	 ***********************************************************************/
	private AEMFTIConfigurationService getConfigSrv() {
		return getCoreProxy().getConfigurationService();
	}

}
