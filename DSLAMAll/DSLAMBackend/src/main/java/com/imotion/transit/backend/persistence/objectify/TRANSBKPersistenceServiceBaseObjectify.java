package com.imotion.transit.backend.persistence.objectify;

import java.io.Serializable;

import com.imotion.transit.backend.persistence.TRANSBKPersistenceServiceBase;

public abstract class TRANSBKPersistenceServiceBaseObjectify<T, Q extends T, Id extends Serializable> extends TRANSBKPersistenceServiceBase<T, Q, Id> {

	private static final long serialVersionUID = 1696084081676617946L;
	
	private TRANSBKPersistenceModuleObjectify<Q, Id> 		persistenceModule;
	

	@Override
	public TRANSBKPersistenceModuleObjectify<Q, Id> getPersistenceModule() {
		if (persistenceModule == null) {
			persistenceModule = new TRANSBKPersistenceModuleObjectify<Q, Id>();
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
