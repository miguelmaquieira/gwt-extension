package com.imotion.dslam.backend.persistence.jpa;

import java.io.Serializable;

import com.selene.arch.exe.back.persistence.module.jpa.AEMFTIPersistenceJPAModule;
import com.selene.arch.exe.back.persistence.module.jpa.AEMFTPersistenceJPAModuleImpl;

public class DSLAMBKPersistenceModuleJPA<Q, Id extends Serializable> extends AEMFTPersistenceJPAModuleImpl<Q, Id> implements AEMFTIPersistenceJPAModule<Q, Id> {

	// serial number
	private static final long serialVersionUID = -7923101269597352273L;
	
	public static final String PERSISTENCE_UNIT_NAME = "dslam";

	@Override
	public String getPersistenceUnitName() {
		return PERSISTENCE_UNIT_NAME;
	}
	
	/**************************************************************
     *               PROTECTED EXTENDED FUNCTIONS                 *
     **************************************************************/

	
	/**************************************************************
     *                	   PRIVATE FUNCTIONS                      *
     **************************************************************/
}
