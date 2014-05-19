package com.imotion.dslam.backend.persistence;

import java.io.Serializable;

import com.selene.arch.exe.back.persistence.AEMFTPersistenceServiceBase;
import com.selene.arch.exe.core.AEMFTICoreProxyService;

public abstract class DSLAMBKPersistenceServiceBase<T, Q extends T, Id extends Serializable> extends AEMFTPersistenceServiceBase<T, Q, Id> implements DSLAMKIPersistenceService<T, Q, Id> {

	// serial number
	private static final long serialVersionUID = 8280070649635544872L;
	
	private static DSLAMBKIPersistenceFactory 	factoryPool;

	public static DSLAMBKIPersistenceFactory getFactoryPersistence(AEMFTICoreProxyService coreProxy) {
		if (factoryPool == null) {
			factoryPool = new DSLAMBKPersistenceFactoryPool(coreProxy);
		}
		return factoryPool;
	}
	
	/**
	 * PROTECTED
	 */
	
	protected DSLAMBKIPersistenceFactory getFactoryPersistence() {
		return factoryPool;
	}
	
}
