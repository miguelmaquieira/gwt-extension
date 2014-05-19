package com.imotion.transit.backend.persistence.objectify;

import java.io.Serializable;

import com.googlecode.objectify.ObjectifyService;
import com.imotion.transit.bom.data.objectify.TRANSBOComputedAnnualTransitDataObjectify;
import com.imotion.transit.bom.data.objectify.TRANSBOComputedDailyTransitDataObjectify;
import com.imotion.transit.bom.data.objectify.TRANSBOComputedMonthlyTransitDataObjectify;
import com.imotion.transit.bom.data.objectify.TRANSBOComputedTransitDataObjectify;
import com.imotion.transit.bom.data.objectify.TRANSBOComputedWeekDayTransitDataObjectify;
import com.imotion.transit.bom.data.objectify.TRANSBOComputedWeeklyTransitDataObjectify;
import com.imotion.transit.bom.data.objectify.TRANSBOTransitDataObjectify;
import com.selene.arch.exe.back.persistence.module.datastore.AEMFTPersistenceDatastoreObjectifyModule;

public class TRANSBKPersistenceModuleObjectify<Q, Id extends Serializable> extends AEMFTPersistenceDatastoreObjectifyModule<Q, Id> {

	private static final long serialVersionUID = -2140343762818927302L;
	
	@Override
	public void registerEntities() {
		ObjectifyService.register(TRANSBOTransitDataObjectify.class);
		ObjectifyService.register(TRANSBOComputedTransitDataObjectify.class);
		ObjectifyService.register(TRANSBOComputedDailyTransitDataObjectify.class);
		ObjectifyService.register(TRANSBOComputedWeekDayTransitDataObjectify.class);
		ObjectifyService.register(TRANSBOComputedWeeklyTransitDataObjectify.class);
		ObjectifyService.register(TRANSBOComputedMonthlyTransitDataObjectify.class);
		ObjectifyService.register(TRANSBOComputedAnnualTransitDataObjectify.class);
	}
	
	/**************************************************************
     *               PROTECTED EXTENDED FUNCTIONS                 *
     **************************************************************/
	
	/**************************************************************
     *                	   PRIVATE FUNCTIONS                      *
     **************************************************************/
}
