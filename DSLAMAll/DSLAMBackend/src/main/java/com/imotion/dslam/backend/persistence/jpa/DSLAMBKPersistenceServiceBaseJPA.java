package com.imotion.dslam.backend.persistence.jpa;

import java.io.Serializable;

import com.imotion.dslam.backend.persistence.DSLAMBKPersistenceServiceBase;
import com.imotion.dslam.backend.persistence.service.file.DSLAMBKFilePersistenceServiceJPA;
import com.imotion.dslam.backend.persistence.service.process.DSLAMBKProcessPersistenceServiceJPA;
import com.selene.arch.exe.back.persistence.AEMFTIPersistenceService;
import com.selene.arch.exe.back.persistence.module.AEMFTIPersistenceModule;
import com.selene.arch.exe.core.AEMFTICoreProxyService;

public abstract class DSLAMBKPersistenceServiceBaseJPA<T, Q extends T, Id extends Serializable> extends DSLAMBKPersistenceServiceBase<T, Q, Id> {

	private static final long serialVersionUID = -3861653720997741685L;

	private DSLAMBKPersistenceModuleJPA<Q, Id> persistenceModule;
	
	private DSLAMBKFilePersistenceServiceJPA 		filePersistence;
	private DSLAMBKProcessPersistenceServiceJPA 	processPersistence;
	
	
	@Override
	public DSLAMBKPersistenceModuleJPA<Q, Id> getPersistenceModule() {
		if (persistenceModule == null) {
			persistenceModule = new DSLAMBKPersistenceModuleJPA<Q, Id>();
			persistenceModule.initialize(new Object[] { getPersistenceCoreService(), getPersistenceClass()});
		}
		return persistenceModule;
	}
	
	@Override
	public void releaseModule() {
		if (persistenceModule != null) {
			persistenceModule.destroyCurrentEntityManager();
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
		getPersistenceModule().createEntityManager();
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
