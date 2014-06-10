package com.imotion.dslam.backend.persistence;

import com.imotion.dslam.backend.persistence.service.file.DSLAMBKIFilePersistenceService;
import com.imotion.dslam.backend.persistence.service.process.DSLAMBKIProcessPersistenceService;
import com.imotion.dslam.backend.persistence.service.project.DSLAMBKIProjectPersistenceService;
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
	
	@Override
	public DSLAMBKIProcessPersistenceService newProcessPersistence() {
		String impl = DSLAMBKIPersistenceConstants.CTE_DSLAM_PERSISTENCE_PROCESS_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					DSLAMBKIPersistenceConstants.CFG_DSLAM_PERSISTENCE_PROCESS_PERSISTENCE_IMPL,
					DSLAMBKIPersistenceConstants.CTE_DSLAM_PERSISTENCE_PROCESS_PERSISTENCE_DEFAULT_IMPL);
		}
		return (DSLAMBKIProcessPersistenceService) newPersistenceModule(impl);
	}
	
	@Override
	public DSLAMBKIProjectPersistenceService newProjectPersistence() {
		String impl = DSLAMBKIPersistenceConstants.CTE_DSLAM_PERSISTENCE_PROJECT_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					DSLAMBKIPersistenceConstants.CFG_DSLAM_PERSISTENCE_PROJECT_PERSISTENCE_IMPL,
					DSLAMBKIPersistenceConstants.CTE_DSLAM_PERSISTENCE_PROJECT_PERSISTENCE_DEFAULT_IMPL);
		}
		return (DSLAMBKIProjectPersistenceService) newPersistenceModule(impl);
	}
	
	/***********************************************************************
	 * 					      PRIVATE FUNCTION                             *
	 ***********************************************************************/
	private AEMFTIConfigurationService getConfigSrv() {
		return getCoreProxy().getConfigurationService();
	}
}
