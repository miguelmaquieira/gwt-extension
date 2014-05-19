package com.imotion.transit.backend.persistence;

import java.io.Serializable;

import com.selene.arch.exe.back.persistence.AEMFTIPersistenceService;


public interface TRANSKIPersistenceService<T, Q extends T, Id extends Serializable> extends AEMFTIPersistenceService<T, Q, Id> {

}
