package com.imotion.dslam.backend.persistence.jpa;

import java.io.Serializable;

import com.imotion.cronio.backend.persistence.service.node.CRONIOBKNodePersistenceServiceJPA;
import com.imotion.dslam.backend.persistence.DSLAMBKPersistenceServiceBase;
import com.imotion.dslam.backend.persistence.service.file.DSLAMBKFilePersistenceServiceJPA;
import com.imotion.dslam.backend.persistence.service.process.DSLAMBKProcessPersistenceServiceJPA;
import com.selene.arch.exe.back.persistence.AEMFTIPersistenceService;

public abstract class DSLAMBKPersistenceServiceBaseJPA<T, Q extends T, Id extends Serializable> extends DSLAMBKPersistenceServiceBase<T, Q, Id> {

	private static final long serialVersionUID = -3861653720997741685L;

	private DSLAMBKPersistenceModuleJPA<Q, Id> persistenceModule;
	
	private DSLAMBKFilePersistenceServiceJPA 		filePersistence;
	private DSLAMBKProcessPersistenceServiceJPA 	processPersistence;
	private CRONIOBKNodePersistenceServiceJPA 		nodePersistence;
	
	
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
		if (nodePersistence != null) {
			getFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) nodePersistence);
			nodePersistence = null;
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
	
	protected CRONIOBKNodePersistenceServiceJPA getNodePersistence() {
		if (nodePersistence == null) {
			nodePersistence = (CRONIOBKNodePersistenceServiceJPA) getFactoryPersistence().newNodePersistence();
		}
		return nodePersistence;
	}
}
