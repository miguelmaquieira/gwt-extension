package com.imotion.transit.backend.persistence.service.computedtransitdata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.imotion.transit.backend.persistence.objectify.TRANSBKPersistenceServiceBaseObjectify;
import com.imotion.transit.bom.TRANSBOIComputedAccumulatedTransitData;
import com.imotion.transit.bom.TRANSBOIComputedAnnualTransitData;
import com.imotion.transit.bom.TRANSBOIComputedDailyTransitData;
import com.imotion.transit.bom.TRANSBOIComputedMonthlyTransitData;
import com.imotion.transit.bom.TRANSBOIComputedPartialTransitData;
import com.imotion.transit.bom.TRANSBOIComputedTransitData;
import com.imotion.transit.bom.TRANSBOIComputedWeekDayTransitData;
import com.imotion.transit.bom.TRANSBOIComputedWeeklyTransitData;
import com.imotion.transit.bom.data.objectify.TRANSBOComputedAnnualTransitDataObjectify;
import com.imotion.transit.bom.data.objectify.TRANSBOComputedDailyTransitDataObjectify;
import com.imotion.transit.bom.data.objectify.TRANSBOComputedMonthlyTransitDataObjectify;
import com.imotion.transit.bom.data.objectify.TRANSBOComputedTransitDataObjectify;
import com.imotion.transit.bom.data.objectify.TRANSBOComputedWeekDayTransitDataObjectify;
import com.imotion.transit.bom.data.objectify.TRANSBOComputedWeeklyTransitDataObjectify;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.back.persistence.AEMFTIPersistenceQueryConstants;
import com.selene.arch.exe.back.persistence.AEMFTPersistenceQueryParam;


public class TRANSBKComputedTransitDataPersistenceServiceObjectify extends TRANSBKPersistenceServiceBaseObjectify<TRANSBOIComputedTransitData, TRANSBOComputedTransitDataObjectify, Long> implements TRANSBKIComputedTransitDataPersistenceService {

	private static final long serialVersionUID = -389789786125617603L;


	public TRANSBKComputedTransitDataPersistenceServiceObjectify() {
		super();
	}
	
	@Override
	public TRANSBOIComputedTransitData addComputedTransitData(TRANSBOIComputedTransitData computedTransitData) {
		if (computedTransitData != null) {
			TRANSBOComputedTransitDataObjectify computedDataOb = getObjectifyObject(computedTransitData);
			computedDataOb.setTimestamp(new Date());
			computedDataOb.setCounterId(getPersistenceModule().generateId(computedDataOb.getClass().getSimpleName()));
			computedTransitData = getPersistenceModule().create(computedDataOb);
		}
		return computedTransitData;
	}
	
	@Override
	public TRANSBOIComputedTransitData getComputedTransitData(Date date, int type) {
		TRANSBOIComputedTransitData computedTransitData = null;
		
		DateTime dayBefore 	= new DateTime(date.getTime()).minusDays(1);
		DateTime dayAfter 	= new DateTime(date.getTime()).plusDays(1);
		
		Date dayBeforeAsDate 	= dayBefore.toDate();
		Date dayAfterAsDate		= dayAfter.toDate();
		
		//QUERY
		List<AEMFTPersistenceQueryParam<?>> queryParams = new ArrayList<>();
		
		AEMFTPersistenceQueryParam<Integer> typeParam = new AEMFTPersistenceQueryParam<>(TRANSBOIComputedTransitData.FREQUENZY_TYPE, type);
		queryParams.add(typeParam);
		
		AEMFTPersistenceQueryParam<Date> dayBeforeParam = new AEMFTPersistenceQueryParam<>(TRANSBOIComputedTransitData.TIMESTAMP, AEMFTIPersistenceQueryConstants.GREATER_THAN, dayBeforeAsDate);
		queryParams.add(dayBeforeParam);
		
		AEMFTPersistenceQueryParam<Date> dayAfterParam	= new AEMFTPersistenceQueryParam<>(TRANSBOIComputedTransitData.TIMESTAMP, AEMFTIPersistenceQueryConstants.LESS_THAN, 	dayAfterAsDate);
		queryParams.add(dayAfterParam);
		
		List<TRANSBOComputedTransitDataObjectify> computedTransitDataList = getPersistenceModule().query(queryParams.iterator(), 0, 1);
		
		if (!AEMFTCommonUtilsBase.isEmptyList(computedTransitDataList)) {
			computedTransitData = computedTransitDataList.get(0);
		}
		return computedTransitData;
	}

	/**
	 * AEMFTPersistenceServiceBase
	 */
	
	@Override
	public Class<TRANSBOComputedTransitDataObjectify> getPersistenceClass() {
		return TRANSBOComputedTransitDataObjectify.class;
	}
	
	/**
	 * UTILS
	 */
	public static TRANSBOComputedTransitDataObjectify getObjectifyObject(TRANSBOIComputedTransitData bomObject) {
		TRANSBOComputedTransitDataObjectify objectifyObject = null;
		if (bomObject != null) {
			if (bomObject instanceof TRANSBOIComputedWeekDayTransitData) {
				objectifyObject = new TRANSBOComputedWeekDayTransitDataObjectify();
			} else if (bomObject instanceof TRANSBOIComputedWeeklyTransitData) {
				objectifyObject = new TRANSBOComputedWeeklyTransitDataObjectify();
			} else if (bomObject instanceof TRANSBOIComputedMonthlyTransitData) {
				objectifyObject = new TRANSBOComputedMonthlyTransitDataObjectify();
			} else if (bomObject instanceof TRANSBOIComputedAnnualTransitData) {
				objectifyObject = new TRANSBOComputedAnnualTransitDataObjectify();
			} else if (bomObject instanceof TRANSBOIComputedDailyTransitData) {
				objectifyObject = new TRANSBOComputedDailyTransitDataObjectify();
				TRANSBOIComputedDailyTransitData bomObjectDaily 		= (TRANSBOIComputedDailyTransitData) bomObject;
				TRANSBOIComputedDailyTransitData objectifyObjectDaily 	= (TRANSBOIComputedDailyTransitData) objectifyObject;
				
				objectifyObjectDaily.setLastWeekEnteringTransit(bomObjectDaily.getLastWeekEnteringTransit());
				objectifyObjectDaily.setLastWeekPassingTransit(bomObjectDaily.getLastWeekPassingTransit());
				objectifyObjectDaily.setLastWeekTotalTransit(bomObjectDaily.getLastWeekTotalTransit());
				
				objectifyObjectDaily.setLastMonthEnteringTransit(bomObjectDaily.getLastMonthEnteringTransit());
				objectifyObjectDaily.setLastMonthPassingTransit(bomObjectDaily.getLastMonthPassingTransit());
				objectifyObjectDaily.setLastMonthTotalTransit(bomObjectDaily.getLastMonthTotalTransit());
				
				objectifyObjectDaily.setLastYearEnteringTransit(bomObjectDaily.getLastYearEnteringTransit());
				objectifyObjectDaily.setLastYearPassingTransit(bomObjectDaily.getLastYearPassingTransit());
				objectifyObjectDaily.setLastYearTotalTransit(bomObjectDaily.getLastYearTotalTransit());
			}
			
			if (bomObject instanceof TRANSBOIComputedPartialTransitData) {
				TRANSBOIComputedPartialTransitData bomObjectPartial = (TRANSBOIComputedPartialTransitData)  bomObject;
				TRANSBOIComputedPartialTransitData objectifyObjectPartial = (TRANSBOIComputedPartialTransitData)  objectifyObject;
				objectifyObjectPartial.setPartialIncomingTransit(bomObjectPartial.getPartialIncomingTransit());
				objectifyObjectPartial.setPartialPassingTransit(bomObjectPartial.getPartialPassingTransit());
				objectifyObjectPartial.setPartialTotalTransit(bomObjectPartial.getPartialTotalTransit());
			}
			
			if (bomObject instanceof TRANSBOIComputedAccumulatedTransitData) {
				TRANSBOIComputedAccumulatedTransitData bomObjectAccumulated 		= (TRANSBOIComputedAccumulatedTransitData)  bomObject;
				TRANSBOIComputedAccumulatedTransitData objectifyObjectAccumulated 	= (TRANSBOIComputedAccumulatedTransitData)  objectifyObject;
				objectifyObjectAccumulated.setAccumulatedIncomingTransit(bomObjectAccumulated.getAccumulatedIncomingTransit());
				objectifyObjectAccumulated.setAccumulatedPassingTransit(bomObjectAccumulated.getAccumulatedPassingTransit());
				objectifyObjectAccumulated.setAccumulatedTotalTransit(bomObjectAccumulated.getAccumulatedTotalTransit());
			}
			objectifyObject.setFrequenzyType(bomObject.getFrequenzyType());
			objectifyObject.setTimestamp(bomObject.getTimestamp());
		}
		return objectifyObject;
	}

}
