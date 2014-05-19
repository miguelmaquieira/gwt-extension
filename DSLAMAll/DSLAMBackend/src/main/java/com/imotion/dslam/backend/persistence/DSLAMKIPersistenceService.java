package com.imotion.dslam.backend.persistence;

import java.io.Serializable;

import com.selene.arch.exe.back.persistence.AEMFTIPersistenceService;


public interface DSLAMKIPersistenceService<T, Q extends T, Id extends Serializable> extends AEMFTIPersistenceService<T, Q, Id> {

}
