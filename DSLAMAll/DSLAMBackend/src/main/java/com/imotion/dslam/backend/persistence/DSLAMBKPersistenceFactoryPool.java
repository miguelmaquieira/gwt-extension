package com.imotion.dslam.backend.persistence;

import com.imotion.cronio.backend.persistence.service.node.CRONIOBKINodePersistenceService;
import com.imotion.dslam.backend.persistence.login.CRONIOBKILoginPersistenceService;
import com.imotion.dslam.backend.persistence.service.execution.CRONIOBKIExecutionPersistenceService;
import com.imotion.dslam.backend.persistence.service.file.DSLAMBKIFilePersistenceService;
import com.imotion.dslam.backend.persistence.service.machineproperties.CRONIOBKIMachinePropertiesPersistenceService;
import com.imotion.dslam.backend.persistence.service.preferences.CRONIOBKIPreferencesPersistenceService;
import com.imotion.dslam.backend.persistence.service.process.DSLAMBKIProcessPersistenceService;
import com.imotion.dslam.backend.persistence.service.project.DSLAMBKIProjectPersistenceService;
import com.imotion.dslam.backend.persistence.service.userpreferences.CRONIOBKIUserPreferencesPersistenceService;
import com.selene.arch.exe.back.persistence.AEMFTPersistenceFactoryPool;
import com.selene.arch.exe.core.AEMFTICoreProxyService;
import com.selene.arch.exe.core.envi.config.AEMFTIConfigurationService;

public class DSLAMBKPersistenceFactoryPool extends AEMFTPersistenceFactoryPool implements DSLAMBKIPersistenceFactory {

	public DSLAMBKPersistenceFactoryPool(AEMFTICoreProxyService coreProxy) {
		super(coreProxy);
	}
	
	@Override
	public DSLAMBKIFilePersistenceService newFilePersistence(String sessionId) {
		String impl = DSLAMBKIPersistenceConstants.CTE_DSLAM_PERSISTENCE_FILE_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					DSLAMBKIPersistenceConstants.CFG_DSLAM_PERSISTENCE_FILE_PERSISTENCE_IMPL,
					DSLAMBKIPersistenceConstants.CTE_DSLAM_PERSISTENCE_FILE_PERSISTENCE_DEFAULT_IMPL);
		}
		return (DSLAMBKIFilePersistenceService) newPersistenceService(impl, sessionId);
	}
	
	@Override
	public DSLAMBKIProcessPersistenceService newProcessPersistence(String sessionId) {
		String impl = DSLAMBKIPersistenceConstants.CTE_DSLAM_PERSISTENCE_PROCESS_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					DSLAMBKIPersistenceConstants.CFG_DSLAM_PERSISTENCE_PROCESS_PERSISTENCE_IMPL,
					DSLAMBKIPersistenceConstants.CTE_DSLAM_PERSISTENCE_PROCESS_PERSISTENCE_DEFAULT_IMPL);
		}
		return (DSLAMBKIProcessPersistenceService) newPersistenceService(impl, sessionId);
	}
	
	@Override
	public DSLAMBKIProjectPersistenceService newProjectPersistence(String sessionId) {
		String impl = DSLAMBKIPersistenceConstants.CTE_DSLAM_PERSISTENCE_PROJECT_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					DSLAMBKIPersistenceConstants.CFG_DSLAM_PERSISTENCE_PROJECT_PERSISTENCE_IMPL,
					DSLAMBKIPersistenceConstants.CTE_DSLAM_PERSISTENCE_PROJECT_PERSISTENCE_DEFAULT_IMPL);
		}
		return (DSLAMBKIProjectPersistenceService) newPersistenceService(impl, sessionId);
	}
	
	@Override
	public CRONIOBKINodePersistenceService newNodePersistence(String sessionId) {
		String impl = DSLAMBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_NODE_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					DSLAMBKIPersistenceConstants.CFG_CRONIO_PERSISTENCE_NODE_PERSISTENCE_IMPL,
					DSLAMBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_NODE_PERSISTENCE_DEFAULT_IMPL);
		}
		return (CRONIOBKINodePersistenceService) newPersistenceService(impl, sessionId);
	}
	
	@Override
	public CRONIOBKIPreferencesPersistenceService newPreferencesPersistence(String sessionId) {
		String impl = DSLAMBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_PREFERENCES_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					DSLAMBKIPersistenceConstants.CFG_CRONIO_PERSISTENCE_PREFERENCES_PERSISTENCE_IMPL,
					DSLAMBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_PREFERENCES_PERSISTENCE_DEFAULT_IMPL);
		}
		return (CRONIOBKIPreferencesPersistenceService) newPersistenceService(impl, sessionId);
	}
	
	@Override
	public CRONIOBKIMachinePropertiesPersistenceService newMachinePropertiesPersistence(String sessionId) {
		String impl = DSLAMBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_MACHINE_PROPERTIES_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					DSLAMBKIPersistenceConstants.CFG_CRONIO_PERSISTENCE_MACHINE_PROPERTIES_PERSISTENCE_IMPL,
					DSLAMBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_MACHINE_PROPERTIES_PERSISTENCE_DEFAULT_IMPL);
		}
		return (CRONIOBKIMachinePropertiesPersistenceService) newPersistenceService(impl, sessionId);
	}
	
	@Override
	public CRONIOBKILoginPersistenceService newUserPersistence(String sessionId) {
		String impl = DSLAMBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_USER_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					DSLAMBKIPersistenceConstants.CFG_CRONIO_PERSISTENCE_USER_PERSISTENCE_IMPL,
					DSLAMBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_USER_PERSISTENCE_DEFAULT_IMPL);
		}
		return (CRONIOBKILoginPersistenceService) newPersistenceService(impl, sessionId);
	}
	
	@Override
	public CRONIOBKIUserPreferencesPersistenceService newUserPreferencesPersistence(String sessionId) {
		String impl = DSLAMBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_USER_PREFERENCES_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					DSLAMBKIPersistenceConstants.CFG_CRONIO_PERSISTENCE_USER_PREFERENCES_PERSISTENCE_IMPL,
					DSLAMBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_USER_PREFERENCES_PERSISTENCE_DEFAULT_IMPL);
		}
		return (CRONIOBKIUserPreferencesPersistenceService) newPersistenceService(impl, sessionId);
	}
	
	@Override
	public CRONIOBKIExecutionPersistenceService newExecutionPersistence(String sessionId) {
		String impl = DSLAMBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_EXECUTION_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					DSLAMBKIPersistenceConstants.CFG_CRONIO_PERSISTENCE_EXECUTION_PERSISTENCE_IMPL,
					DSLAMBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_EXECUTION_PERSISTENCE_DEFAULT_IMPL);
		}
		return (CRONIOBKIExecutionPersistenceService) newPersistenceService(impl, sessionId);
	}


/***********************************************************************
	 * 					      PRIVATE FUNCTION                             *
	 ***********************************************************************/
	private AEMFTIConfigurationService getConfigSrv() {
		return getCoreProxy().getConfigurationService();
	}
	
}
