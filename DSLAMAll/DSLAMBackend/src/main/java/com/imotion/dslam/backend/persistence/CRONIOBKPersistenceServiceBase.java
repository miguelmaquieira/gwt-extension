package com.imotion.dslam.backend.persistence;

import java.io.Serializable;

import com.selene.arch.exe.back.persistence.AEMFTPersistenceServiceBase;
import com.selene.arch.exe.core.AEMFTICoreProxyService;

public abstract class CRONIOBKPersistenceServiceBase<T, Q extends T, Id extends Serializable> extends AEMFTPersistenceServiceBase<T, Q, Id> implements CRONIOKIPersistenceService<T, Q, Id> {

	// serial number
	private static final long serialVersionUID = 8280070649635544872L;
	
	private static CRONIOBKIPersistenceFactory 	factoryPool;

	public static CRONIOBKIPersistenceFactory getFactoryPersistence(AEMFTICoreProxyService coreProxy) {
		if (factoryPool == null) {
			factoryPool = new CRONIOBKPersistenceFactoryPool(coreProxy);
		}
		return factoryPool;
	}
	
	/**
	 * PROTECTED
	 */
	
	protected CRONIOBKIPersistenceFactory getFactoryPersistence() {
		return factoryPool;
	}
	
}
