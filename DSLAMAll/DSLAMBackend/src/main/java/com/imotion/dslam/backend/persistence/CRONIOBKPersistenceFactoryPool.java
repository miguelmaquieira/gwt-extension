package com.imotion.dslam.backend.persistence;

import com.imotion.cronio.backend.persistence.service.node.CRONIOBKINodePersistenceService;
import com.imotion.cronio.backend.persistence.service.nodelist.CRONIOBKINodeListPersistenceService;
import com.imotion.dslam.backend.persistence.log.CRONIOBKILogPersistenceService;
import com.imotion.dslam.backend.persistence.login.CRONIOBKILoginPersistenceService;
import com.imotion.dslam.backend.persistence.service.execution.CRONIOBKIExecutionPersistenceService;
import com.imotion.dslam.backend.persistence.service.file.CRONIOBKIFilePersistenceService;
import com.imotion.dslam.backend.persistence.service.machineproperties.CRONIOBKIMachinePropertiesPersistenceService;
import com.imotion.dslam.backend.persistence.service.preferences.CRONIOBKIPreferencesPersistenceService;
import com.imotion.dslam.backend.persistence.service.process.CRONIOBKIProcessPersistenceService;
import com.imotion.dslam.backend.persistence.service.project.CRONIOBKIProjectPersistenceService;
import com.imotion.dslam.backend.persistence.service.userpreferences.CRONIOBKIUserPreferencesPersistenceService;
import com.selene.arch.exe.back.persistence.AEMFTPersistenceFactoryPool;
import com.selene.arch.exe.core.AEMFTICoreProxyService;
import com.selene.arch.exe.core.envi.config.AEMFTIConfigurationService;

public class CRONIOBKPersistenceFactoryPool extends AEMFTPersistenceFactoryPool implements CRONIOBKIPersistenceFactory {

	public CRONIOBKPersistenceFactoryPool(AEMFTICoreProxyService coreProxy) {
		super(coreProxy);
	}
	
	@Override
	public CRONIOBKIFilePersistenceService newFilePersistence(String sessionId) {
		String impl = CRONIOBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_FILE_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					CRONIOBKIPersistenceConstants.CFG_CRONIO_PERSISTENCE_FILE_PERSISTENCE_IMPL,
					CRONIOBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_FILE_PERSISTENCE_DEFAULT_IMPL);
		}
		return (CRONIOBKIFilePersistenceService) newPersistenceService(impl, sessionId);
	}
	
	@Override
	public CRONIOBKIProcessPersistenceService newProcessPersistence(String sessionId) {
		String impl = CRONIOBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_PROCESS_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					CRONIOBKIPersistenceConstants.CFG_CRONIO_PERSISTENCE_PROCESS_PERSISTENCE_IMPL,
					CRONIOBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_PROCESS_PERSISTENCE_DEFAULT_IMPL);
		}
		return (CRONIOBKIProcessPersistenceService) newPersistenceService(impl, sessionId);
	}
	
	@Override
	public CRONIOBKIProjectPersistenceService newProjectPersistence(String sessionId) {
		String impl = CRONIOBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_PROJECT_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					CRONIOBKIPersistenceConstants.CFG_CRONIO_PERSISTENCE_PROJECT_PERSISTENCE_IMPL,
					CRONIOBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_PROJECT_PERSISTENCE_DEFAULT_IMPL);
		}
		return (CRONIOBKIProjectPersistenceService) newPersistenceService(impl, sessionId);
	}
	
	@Override
	public CRONIOBKINodePersistenceService newNodePersistence(String sessionId) {
		String impl = CRONIOBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_NODE_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					CRONIOBKIPersistenceConstants.CFG_CRONIO_PERSISTENCE_NODE_PERSISTENCE_IMPL,
					CRONIOBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_NODE_PERSISTENCE_DEFAULT_IMPL);
		}
		return (CRONIOBKINodePersistenceService) newPersistenceService(impl, sessionId);
	}
	
	@Override
	public CRONIOBKINodeListPersistenceService newNodeListPersistence(String sessionId) {
		String impl = CRONIOBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_NODELIST_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					CRONIOBKIPersistenceConstants.CFG_CRONIO_PERSISTENCE_NODELIST_PERSISTENCE_IMPL,
					CRONIOBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_NODELIST_PERSISTENCE_DEFAULT_IMPL);
		}
		return (CRONIOBKINodeListPersistenceService) newPersistenceService(impl, sessionId);
	}
	
	@Override
	public CRONIOBKIPreferencesPersistenceService newPreferencesPersistence(String sessionId) {
		String impl = CRONIOBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_PREFERENCES_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					CRONIOBKIPersistenceConstants.CFG_CRONIO_PERSISTENCE_PREFERENCES_PERSISTENCE_IMPL,
					CRONIOBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_PREFERENCES_PERSISTENCE_DEFAULT_IMPL);
		}
		return (CRONIOBKIPreferencesPersistenceService) newPersistenceService(impl, sessionId);
	}
	
	@Override
	public CRONIOBKIMachinePropertiesPersistenceService newMachinePropertiesPersistence(String sessionId) {
		String impl = CRONIOBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_MACHINE_PROPERTIES_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					CRONIOBKIPersistenceConstants.CFG_CRONIO_PERSISTENCE_MACHINE_PROPERTIES_PERSISTENCE_IMPL,
					CRONIOBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_MACHINE_PROPERTIES_PERSISTENCE_DEFAULT_IMPL);
		}
		return (CRONIOBKIMachinePropertiesPersistenceService) newPersistenceService(impl, sessionId);
	}
	
	@Override
	public CRONIOBKILoginPersistenceService newUserPersistence(String sessionId) {
		String impl = CRONIOBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_USER_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					CRONIOBKIPersistenceConstants.CFG_CRONIO_PERSISTENCE_USER_PERSISTENCE_IMPL,
					CRONIOBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_USER_PERSISTENCE_DEFAULT_IMPL);
		}
		return (CRONIOBKILoginPersistenceService) newPersistenceService(impl, sessionId);
	}
	
	@Override
	public CRONIOBKIUserPreferencesPersistenceService newUserPreferencesPersistence(String sessionId) {
		String impl = CRONIOBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_USER_PREFERENCES_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					CRONIOBKIPersistenceConstants.CFG_CRONIO_PERSISTENCE_USER_PREFERENCES_PERSISTENCE_IMPL,
					CRONIOBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_USER_PREFERENCES_PERSISTENCE_DEFAULT_IMPL);
		}
		return (CRONIOBKIUserPreferencesPersistenceService) newPersistenceService(impl, sessionId);
	}
	
	@Override
	public CRONIOBKIExecutionPersistenceService newExecutionPersistence(String sessionId) {
		String impl = CRONIOBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_EXECUTION_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					CRONIOBKIPersistenceConstants.CFG_CRONIO_PERSISTENCE_EXECUTION_PERSISTENCE_IMPL,
					CRONIOBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_EXECUTION_PERSISTENCE_DEFAULT_IMPL);
		}
		return (CRONIOBKIExecutionPersistenceService) newPersistenceService(impl, sessionId);
	}
	
	
	@Override
	public CRONIOBKILogPersistenceService newLogPersistence(String sessionId) {
		String impl = CRONIOBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_LOG_PERSISTENCE_DEFAULT_IMPL;
		if (getConfigSrv() != null) {
			impl = getConfigSrv().getProperty(
					CRONIOBKIPersistenceConstants.CFG_CRONIO_PERSISTENCE_LOG_PERSISTENCE_IMPL,
					CRONIOBKIPersistenceConstants.CTE_CRONIO_PERSISTENCE_LOG_PERSISTENCE_DEFAULT_IMPL);
		}
		return (CRONIOBKILogPersistenceService) newPersistenceService(impl, sessionId);
	}


/***********************************************************************
	 * 					      PRIVATE FUNCTION                             *
	 ***********************************************************************/
	private AEMFTIConfigurationService getConfigSrv() {
		return getCoreProxy().getConfigurationService();
	}
	
}
