package com.imotion.dslam.backend.persistence.jpa;

import java.io.Serializable;

import com.imotion.cronio.backend.persistence.service.node.CRONIOBKINodePersistenceService;
import com.imotion.dslam.backend.persistence.DSLAMBKPersistenceServiceBase;
import com.imotion.dslam.backend.persistence.service.file.DSLAMBKIFilePersistenceService;
import com.imotion.dslam.backend.persistence.service.machineproperties.CRONIOBKIMachinePropertiesPersistenceService;
import com.imotion.dslam.backend.persistence.service.preferences.CRONIOBKIPreferencesPersistenceService;
import com.imotion.dslam.backend.persistence.service.process.DSLAMBKIProcessPersistenceService;
import com.selene.arch.exe.back.persistence.AEMFTIPersistenceService;

public abstract class DSLAMBKPersistenceServiceBaseJPA<T, Q extends T, Id extends Serializable> extends DSLAMBKPersistenceServiceBase<T, Q, Id> {

	private static final long serialVersionUID = -3861653720997741685L;

	private DSLAMBKPersistenceModuleJPA<Q, Id> persistenceModule;
	
	private DSLAMBKIFilePersistenceService 						filePersistence;
	private DSLAMBKIProcessPersistenceService 					processPersistence;
	private CRONIOBKINodePersistenceService						nodePersistence;
	private CRONIOBKIPreferencesPersistenceService	 			preferencesPersistence;
	private CRONIOBKIMachinePropertiesPersistenceService		machinePropertiesPersistence;
	
	@Override
	public DSLAMBKPersistenceModuleJPA<Q, Id> getPersistenceModule() {
		if (persistenceModule == null) {
			persistenceModule = new DSLAMBKPersistenceModuleJPA<Q, Id>();
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
		if (preferencesPersistence != null) {
			getFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) preferencesPersistence);
			preferencesPersistence = null;
		}
		if (machinePropertiesPersistence != null) {
			getFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) machinePropertiesPersistence);
			machinePropertiesPersistence = null;
		}
	}
	
	/**************************************************************
     *                   PROTECTED FUNCTIONS                      *
     **************************************************************/
	
	protected DSLAMBKIFilePersistenceService getFilePersistence() {
		if (filePersistence == null) {
			filePersistence = (DSLAMBKIFilePersistenceService) getFactoryPersistence().newFilePersistence(getSessionId());
		}
		return filePersistence;
	}

	protected DSLAMBKIProcessPersistenceService getProcessPersistence() {
		if (processPersistence == null) {
			processPersistence = (DSLAMBKIProcessPersistenceService) getFactoryPersistence().newProcessPersistence(getSessionId());
		}
		return processPersistence;
	}
	
	protected CRONIOBKINodePersistenceService getNodePersistence() {
		if (nodePersistence == null) {
			nodePersistence = (CRONIOBKINodePersistenceService) getFactoryPersistence().newNodePersistence(getSessionId());
		}
		return nodePersistence;
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
}
