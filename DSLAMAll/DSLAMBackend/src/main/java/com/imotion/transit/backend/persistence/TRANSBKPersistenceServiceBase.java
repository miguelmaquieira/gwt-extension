package com.imotion.transit.backend.persistence;

import java.io.Serializable;

import com.selene.arch.exe.back.persistence.AEMFTPersistenceServiceBase;
import com.selene.arch.exe.core.AEMFTICoreProxyService;

public abstract class TRANSBKPersistenceServiceBase<T, Q extends T, Id extends Serializable> extends AEMFTPersistenceServiceBase<T, Q, Id> implements TRANSKIPersistenceService<T, Q, Id> {

	// serial number
	private static final long serialVersionUID = 8280070649635544872L;
	
	private static TRANSBKIPersistenceFactory 	factoryPool;

	public static TRANSBKIPersistenceFactory getFactoryPersistence(AEMFTICoreProxyService coreProxy) {
		if (factoryPool == null) {
			factoryPool = new TRANSBKPersistenceFactoryPool(coreProxy);
		}
		return factoryPool;
	}
	
	/**
	 * PROTECTED
	 */
	
	protected TRANSBKIPersistenceFactory getFactoryPersistence() {
		return factoryPool;
	}
	
}
