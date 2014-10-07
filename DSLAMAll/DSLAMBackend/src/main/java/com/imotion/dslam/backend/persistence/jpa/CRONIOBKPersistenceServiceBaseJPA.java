package com.imotion.dslam.backend.persistence.jpa;

import java.io.Serializable;

import com.imotion.cronio.backend.persistence.service.node.CRONIOBKINodePersistenceService;
import com.imotion.cronio.backend.persistence.service.nodelist.CRONIOBKINodeListPersistenceService;
import com.imotion.dslam.backend.persistence.CRONIOBKPersistenceServiceBase;
import com.imotion.dslam.backend.persistence.log.CRONIOBKILogPersistenceService;
import com.imotion.dslam.backend.persistence.service.execution.CRONIOBKIExecutionPersistenceService;
import com.imotion.dslam.backend.persistence.service.file.CRONIOBKIFilePersistenceService;
import com.imotion.dslam.backend.persistence.service.machineproperties.CRONIOBKIMachinePropertiesPersistenceService;
import com.imotion.dslam.backend.persistence.service.preferences.CRONIOBKIPreferencesPersistenceService;
import com.imotion.dslam.backend.persistence.service.process.CRONIOBKIProcessPersistenceService;
import com.imotion.dslam.backend.persistence.service.project.CRONIOBKIProjectPersistenceService;
import com.imotion.dslam.backend.persistence.service.userpreferences.CRONIOBKIUserPreferencesPersistenceService;
import com.selene.arch.exe.back.persistence.AEMFTIPersistenceService;
import com.selene.arch.exe.core.AEMFTICoreProxyService;

public abstract class CRONIOBKPersistenceServiceBaseJPA<T, Q extends T, Id extends Serializable> extends CRONIOBKPersistenceServiceBase<T, Q, Id> {

	private static final long serialVersionUID = -3861653720997741685L;

	private CRONIOBKPersistenceModuleJPA<Q, Id> persistenceModule;
	
	private CRONIOBKIProjectPersistenceService					projectPersistence;
	private CRONIOBKIFilePersistenceService 					filePersistence;
	private CRONIOBKIProcessPersistenceService 					processPersistence;
	private CRONIOBKINodePersistenceService						nodePersistence;
	private CRONIOBKINodeListPersistenceService					nodeListPersistence;
	private CRONIOBKIPreferencesPersistenceService	 			preferencesPersistence;
	private CRONIOBKIMachinePropertiesPersistenceService		machinePropertiesPersistence;
	private CRONIOBKIUserPreferencesPersistenceService			userPreferencesPersistence;
	private CRONIOBKIExecutionPersistenceService				executionPersistence;
	private CRONIOBKILogPersistenceService						logPersistence;
	
	@Override
	public CRONIOBKPersistenceModuleJPA<Q, Id> getPersistenceModule() {
		if (persistenceModule == null) {
			persistenceModule = new CRONIOBKPersistenceModuleJPA<Q, Id>();
			setPersistenceUnit();
			persistenceModule.initialize(new Object[] { getPersistenceCoreService(), getPersistenceClass(), getSessionId()});
			
		}
		
		return persistenceModule;
	}
	
	@Override
	public void releaseModule() {
		if (persistenceModule != null) {
			persistenceModule.releaseInstance();
			persistenceModule = null;
		}
	}
	
	/**************************************************************
     *                AEMFTIFACTORABLE FUNCTIONS                  *
     **************************************************************/
	
	/**************************************************************
     *                AEMFTIFACTORABLE FUNCTIONS                  *
     **************************************************************/

	@Override
	public void initialize(Object[] args) {
		super.initialize(args);
	}

	@Override
	public void releaseInstance() {
		super.releaseInstance();
		if (projectPersistence != null) {
			getFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) projectPersistence);
			projectPersistence = null;
		}
		if (filePersistence != null) {
			getFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) filePersistence);
			filePersistence = null;
		}
		if (processPersistence != null) {
			getFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) processPersistence);
			processPersistence = null;
		}
		if (nodePersistence != null) {
			getFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) nodePersistence);
			nodePersistence = null;
		}
		if (nodeListPersistence != null) {
			getFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) nodeListPersistence);
			nodeListPersistence = null;
		}
		if (preferencesPersistence != null) {
			getFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) preferencesPersistence);
			preferencesPersistence = null;
		}
		if (machinePropertiesPersistence != null) {
			getFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) machinePropertiesPersistence);
			machinePropertiesPersistence = null;
		}
		if (userPreferencesPersistence != null) {
			getFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) userPreferencesPersistence);
			userPreferencesPersistence = null;
		}
		if (executionPersistence != null) {
			getFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) executionPersistence);
			executionPersistence = null;
		}
		if (logPersistence != null) {
			getFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) logPersistence);
			logPersistence = null;
		}
	}
	
	/**************************************************************
     *                   PROTECTED FUNCTIONS                      *
     **************************************************************/
	protected void setPersistenceUnit() {
	}

	@Override
	protected void initFactoryPersistence(AEMFTICoreProxyService coreProxy) {
		getFactoryPersistence(coreProxy);
	}
	
	protected CRONIOBKIProjectPersistenceService getProjectPersistence() {
		if (projectPersistence == null) {
			projectPersistence = (CRONIOBKIProjectPersistenceService) getFactoryPersistence().newProjectPersistence(getSessionId());
		}
		return projectPersistence;
	}
	
	protected CRONIOBKIFilePersistenceService getFilePersistence() {
		if (filePersistence == null) {
			filePersistence = (CRONIOBKIFilePersistenceService) getFactoryPersistence().newFilePersistence(getSessionId());
		}
		return filePersistence;
	}

	protected CRONIOBKIProcessPersistenceService getProcessPersistence() {
		if (processPersistence == null) {
			processPersistence = (CRONIOBKIProcessPersistenceService) getFactoryPersistence().newProcessPersistence(getSessionId());
		}
		return processPersistence;
	}
	
	protected CRONIOBKINodePersistenceService getNodePersistence() {
		if (nodePersistence == null) {
			nodePersistence = (CRONIOBKINodePersistenceService) getFactoryPersistence().newNodePersistence(getSessionId());
		}
		return nodePersistence;
	}
	
	protected CRONIOBKINodeListPersistenceService getNodeListPersistence() {
		if (nodeListPersistence == null) {
			nodeListPersistence = (CRONIOBKINodeListPersistenceService) getFactoryPersistence().newNodeListPersistence(getSessionId());
		}
		return nodeListPersistence;
	}
	
	protected CRONIOBKIMachinePropertiesPersistenceService getMachinePropertiesPersistence() {
		if (machinePropertiesPersistence == null) {
			machinePropertiesPersistence = (CRONIOBKIMachinePropertiesPersistenceService) getFactoryPersistence().newMachinePropertiesPersistence(getSessionId());
		}
		return machinePropertiesPersistence;
	}
	
	protected CRONIOBKIPreferencesPersistenceService getPreferencesPersistence() {
		if (preferencesPersistence == null) {
			preferencesPersistence = (CRONIOBKIPreferencesPersistenceService) getFactoryPersistence().newPreferencesPersistence(getSessionId());
		}
		return preferencesPersistence;
	}
	
	protected CRONIOBKIUserPreferencesPersistenceService getUserPreferencesPersistence() {
		if (userPreferencesPersistence == null) {
			userPreferencesPersistence = (CRONIOBKIUserPreferencesPersistenceService) getFactoryPersistence().newUserPreferencesPersistence(getSessionId());
		}
		return userPreferencesPersistence;
	}
	
	protected CRONIOBKIExecutionPersistenceService getExecutionPersistence() {
		if (executionPersistence == null) {
			executionPersistence = (CRONIOBKIExecutionPersistenceService) getFactoryPersistence().newExecutionPersistence(getSessionId());
		}
		return executionPersistence;
	}
	
	protected CRONIOBKILogPersistenceService getLogPersistence() {
		if (logPersistence == null) {
			logPersistence = (CRONIOBKILogPersistenceService) getFactoryPersistence().newLogPersistence(getSessionId());
		}
		return logPersistence;
	}
}
