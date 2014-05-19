package com.imotion.dslam.backend.persistence.objectify;

import java.io.Serializable;

import com.imotion.dslam.backend.persistence.DSLAMBKPersistenceServiceBase;

public abstract class DSLAMBKPersistenceServiceBaseObjectify<T, Q extends T, Id extends Serializable> extends DSLAMBKPersistenceServiceBase<T, Q, Id> {

	private static final long serialVersionUID = 1696084081676617946L;
	
	private DSLAMBKPersistenceModuleObjectify<Q, Id> 		persistenceModule;
	

	@Override
	public DSLAMBKPersistenceModuleObjectify<Q, Id> getPersistenceModule() {
		if (persistenceModule == null) {
			persistenceModule = new DSLAMBKPersistenceModuleObjectify<Q, Id>();
			persistenceModule.initialize(new Object[] { getPersistenceCoreService(), getPersistenceClass()});
		}
		return persistenceModule;
	}
	
	/**************************************************************
     *                AEMFTIFACTORABLE FUNCTIONS                  *
     **************************************************************/

	@Override
	public void releaseInstance() {
		super.releaseInstance();
		persistenceModule = null;
		
	}
	
	/**************************************************************
     *                   PROTECTED FUNCTIONS                      *
     **************************************************************/
	
	
}
