package com.imotion.dslam.backend.persistence.objectify;

import java.io.Serializable;

import com.selene.arch.exe.back.persistence.module.datastore.AEMFTPersistenceDatastoreObjectifyModule;

public class DSLAMBKPersistenceModuleObjectify<Q, Id extends Serializable> extends AEMFTPersistenceDatastoreObjectifyModule<Q, Id> {

	private static final long serialVersionUID = -2140343762818927302L;
	
	@Override
	public void registerEntities() {
//		ObjectifyService.register(DSLAMBOTransitDataObjectify.class);
	}
	
	/**************************************************************
     *               PROTECTED EXTENDED FUNCTIONS                 *
     **************************************************************/
	
	/**************************************************************
     *                	   PRIVATE FUNCTIONS                      *
     **************************************************************/
}
