package com.imotion.dslam.backend.persistence.objectify;

import java.io.Serializable;

import com.googlecode.objectify.ObjectifyService;
import com.imotion.dslam.bom.data.objectify.DSLAMBOComputedAnnualTransitDataObjectify;
import com.imotion.dslam.bom.data.objectify.DSLAMBOComputedDailyTransitDataObjectify;
import com.imotion.dslam.bom.data.objectify.DSLAMBOComputedMonthlyTransitDataObjectify;
import com.imotion.dslam.bom.data.objectify.DSLAMBOComputedTransitDataObjectify;
import com.imotion.dslam.bom.data.objectify.DSLAMBOComputedWeekDayTransitDataObjectify;
import com.imotion.dslam.bom.data.objectify.DSLAMBOComputedWeeklyTransitDataObjectify;
import com.imotion.dslam.bom.data.objectify.DSLAMBOTransitDataObjectify;
import com.selene.arch.exe.back.persistence.module.datastore.AEMFTPersistenceDatastoreObjectifyModule;

public class DSLAMBKPersistenceModuleObjectify<Q, Id extends Serializable> extends AEMFTPersistenceDatastoreObjectifyModule<Q, Id> {

	private static final long serialVersionUID = -2140343762818927302L;
	
	@Override
	public void registerEntities() {
		ObjectifyService.register(DSLAMBOTransitDataObjectify.class);
		ObjectifyService.register(DSLAMBOComputedTransitDataObjectify.class);
		ObjectifyService.register(DSLAMBOComputedDailyTransitDataObjectify.class);
		ObjectifyService.register(DSLAMBOComputedWeekDayTransitDataObjectify.class);
		ObjectifyService.register(DSLAMBOComputedWeeklyTransitDataObjectify.class);
		ObjectifyService.register(DSLAMBOComputedMonthlyTransitDataObjectify.class);
		ObjectifyService.register(DSLAMBOComputedAnnualTransitDataObjectify.class);
	}
	
	/**************************************************************
     *               PROTECTED EXTENDED FUNCTIONS                 *
     **************************************************************/
	
	/**************************************************************
     *                	   PRIVATE FUNCTIONS                      *
     **************************************************************/
}
