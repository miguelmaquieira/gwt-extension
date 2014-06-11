package com.imotion.dslam.backend.persistence.jpa;

import java.io.Serializable;

import com.imotion.dslam.backend.persistence.DSLAMBKPersistenceServiceBase;
import com.imotion.dslam.backend.persistence.service.file.DSLAMBKFilePersistenceServiceJPA;
import com.imotion.dslam.backend.persistence.service.process.DSLAMBKProcessPersistenceServiceJPA;
import com.selene.arch.exe.back.persistence.AEMFTIPersistenceService;
import com.selene.arch.exe.back.persistence.module.AEMFTIPersistenceModule;

public abstract class DSLAMBKPersistenceServiceBaseJPA<T, Q extends T, Id extends Serializable> extends DSLAMBKPersistenceServiceBase<T, Q, Id> {

	private static final long serialVersionUID = -3861653720997741685L;

	private DSLAMBKPersistenceModuleJPA<Q, Id> persistenceModule;
	
	private DSLAMBKFilePersistenceServiceJPA 		filePersistence;
	private DSLAMBKProcessPersistenceServiceJPA 	processPersistence;
	
	
	@Override
	public AEMFTIPersistenceModule<Q, Id> getPersistenceModule() {
		if (persistenceModule == null) {
			persistenceModule = new DSLAMBKPersistenceModuleJPA<Q, Id>();
			persistenceModule.initialize(new Object[] { getPersistenceCoreService(), getPersistenceClass()});
		}
		persistenceModule.createEntityManager();
		return persistenceModule;
	}
	
	@Override
	public void releaseModule() {
		persistenceModule = null;
	}
	
	/**************************************************************
     *                AEMFTIFACTORABLE FUNCTIONS                  *
     **************************************************************/

	@Override
	public void releaseInstance() {
		super.releaseInstance();
		persistenceModule.destroyCurrentEntityManager();
		persistenceModule 		= null;
		if (filePersistence != null) {
			getFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) filePersistence);
			filePersistence = null;
		}
		if (processPersistence != null) {
			getFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) processPersistence);
			processPersistence = null;
		}
	}
	
	/**************************************************************
     *                   PROTECTED FUNCTIONS                      *
     **************************************************************/
	
	protected DSLAMBKFilePersistenceServiceJPA getFilePersistence() {
		if (filePersistence == null) {
			filePersistence = (DSLAMBKFilePersistenceServiceJPA) getFactoryPersistence().newFilePersistence();
		}
		return filePersistence;
	}

	protected DSLAMBKProcessPersistenceServiceJPA getProcessPersistence() {
		if (processPersistence == null) {
			processPersistence = (DSLAMBKProcessPersistenceServiceJPA) getFactoryPersistence().newProcessPersistence();
		}
		return processPersistence;
	}
}
