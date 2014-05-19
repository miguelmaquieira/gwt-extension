package com.imotion.dslam.backend.persistence.service.computedtransitdata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.imotion.dslam.backend.persistence.objectify.DSLAMBKPersistenceServiceBaseObjectify;
import com.imotion.dslam.bom.DSLAMBOIComputedAccumulatedTransitData;
import com.imotion.dslam.bom.DSLAMBOIComputedAnnualTransitData;
import com.imotion.dslam.bom.DSLAMBOIComputedDailyTransitData;
import com.imotion.dslam.bom.DSLAMBOIComputedMonthlyTransitData;
import com.imotion.dslam.bom.DSLAMBOIComputedPartialTransitData;
import com.imotion.dslam.bom.DSLAMBOIComputedTransitData;
import com.imotion.dslam.bom.DSLAMBOIComputedWeekDayTransitData;
import com.imotion.dslam.bom.DSLAMBOIComputedWeeklyTransitData;
import com.imotion.dslam.bom.data.objectify.DSLAMBOComputedAnnualTransitDataObjectify;
import com.imotion.dslam.bom.data.objectify.DSLAMBOComputedDailyTransitDataObjectify;
import com.imotion.dslam.bom.data.objectify.DSLAMBOComputedMonthlyTransitDataObjectify;
import com.imotion.dslam.bom.data.objectify.DSLAMBOComputedTransitDataObjectify;
import com.imotion.dslam.bom.data.objectify.DSLAMBOComputedWeekDayTransitDataObjectify;
import com.imotion.dslam.bom.data.objectify.DSLAMBOComputedWeeklyTransitDataObjectify;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.back.persistence.AEMFTIPersistenceQueryConstants;
import com.selene.arch.exe.back.persistence.AEMFTPersistenceQueryParam;


public class DSLAMBKComputedTransitDataPersistenceServiceObjectify extends DSLAMBKPersistenceServiceBaseObjectify<DSLAMBOIComputedTransitData, DSLAMBOComputedTransitDataObjectify, Long> implements DSLAMBKIComputedTransitDataPersistenceService {

	private static final long serialVersionUID = -389789786125617603L;


	public DSLAMBKComputedTransitDataPersistenceServiceObjectify() {
		super();
	}
	
	@Override
	public DSLAMBOIComputedTransitData addComputedTransitData(DSLAMBOIComputedTransitData computedTransitData) {
		if (computedTransitData != null) {
			DSLAMBOComputedTransitDataObjectify computedDataOb = getObjectifyObject(computedTransitData);
			computedDataOb.setTimestamp(new Date());
			computedDataOb.setCounterId(getPersistenceModule().generateId(computedDataOb.getClass().getSimpleName()));
			computedTransitData = getPersistenceModule().create(computedDataOb);
		}
		return computedTransitData;
	}
	
	@Override
	public DSLAMBOIComputedTransitData getComputedTransitData(Date date, int type) {
		DSLAMBOIComputedTransitData computedTransitData = null;
		
		DateTime dayBefore 	= new DateTime(date.getTime()).minusDays(1);
		DateTime dayAfter 	= new DateTime(date.getTime()).plusDays(1);
		
		Date dayBeforeAsDate 	= dayBefore.toDate();
		Date dayAfterAsDate		= dayAfter.toDate();
		
		//QUERY
		List<AEMFTPersistenceQueryParam<?>> queryParams = new ArrayList<>();
		
		AEMFTPersistenceQueryParam<Integer> typeParam = new AEMFTPersistenceQueryParam<>(DSLAMBOIComputedTransitData.FREQUENZY_TYPE, type);
		queryParams.add(typeParam);
		
		AEMFTPersistenceQueryParam<Date> dayBeforeParam = new AEMFTPersistenceQueryParam<>(DSLAMBOIComputedTransitData.TIMESTAMP, AEMFTIPersistenceQueryConstants.GREATER_THAN, dayBeforeAsDate);
		queryParams.add(dayBeforeParam);
		
		AEMFTPersistenceQueryParam<Date> dayAfterParam	= new AEMFTPersistenceQueryParam<>(DSLAMBOIComputedTransitData.TIMESTAMP, AEMFTIPersistenceQueryConstants.LESS_THAN, 	dayAfterAsDate);
		queryParams.add(dayAfterParam);
		
		List<DSLAMBOComputedTransitDataObjectify> computedTransitDataList = getPersistenceModule().query(queryParams.iterator(), 0, 1);
		
		if (!AEMFTCommonUtilsBase.isEmptyList(computedTransitDataList)) {
			computedTransitData = computedTransitDataList.get(0);
		}
		return computedTransitData;
	}

	/**
	 * AEMFTPersistenceServiceBase
	 */
	
	@Override
	public Class<DSLAMBOComputedTransitDataObjectify> getPersistenceClass() {
		return DSLAMBOComputedTransitDataObjectify.class;
	}
	
	/**
	 * UTILS
	 */
	public static DSLAMBOComputedTransitDataObjectify getObjectifyObject(DSLAMBOIComputedTransitData bomObject) {
		DSLAMBOComputedTransitDataObjectify objectifyObject = null;
		if (bomObject != null) {
			if (bomObject instanceof DSLAMBOIComputedWeekDayTransitData) {
				objectifyObject = new DSLAMBOComputedWeekDayTransitDataObjectify();
			} else if (bomObject instanceof DSLAMBOIComputedWeeklyTransitData) {
				objectifyObject = new DSLAMBOComputedWeeklyTransitDataObjectify();
			} else if (bomObject instanceof DSLAMBOIComputedMonthlyTransitData) {
				objectifyObject = new DSLAMBOComputedMonthlyTransitDataObjectify();
			} else if (bomObject instanceof DSLAMBOIComputedAnnualTransitData) {
				objectifyObject = new DSLAMBOComputedAnnualTransitDataObjectify();
			} else if (bomObject instanceof DSLAMBOIComputedDailyTransitData) {
				objectifyObject = new DSLAMBOComputedDailyTransitDataObjectify();
				DSLAMBOIComputedDailyTransitData bomObjectDaily 		= (DSLAMBOIComputedDailyTransitData) bomObject;
				DSLAMBOIComputedDailyTransitData objectifyObjectDaily 	= (DSLAMBOIComputedDailyTransitData) objectifyObject;
				
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
			
			if (bomObject instanceof DSLAMBOIComputedPartialTransitData) {
				DSLAMBOIComputedPartialTransitData bomObjectPartial = (DSLAMBOIComputedPartialTransitData)  bomObject;
				DSLAMBOIComputedPartialTransitData objectifyObjectPartial = (DSLAMBOIComputedPartialTransitData)  objectifyObject;
				objectifyObjectPartial.setPartialIncomingTransit(bomObjectPartial.getPartialIncomingTransit());
				objectifyObjectPartial.setPartialPassingTransit(bomObjectPartial.getPartialPassingTransit());
				objectifyObjectPartial.setPartialTotalTransit(bomObjectPartial.getPartialTotalTransit());
			}
			
			if (bomObject instanceof DSLAMBOIComputedAccumulatedTransitData) {
				DSLAMBOIComputedAccumulatedTransitData bomObjectAccumulated 		= (DSLAMBOIComputedAccumulatedTransitData)  bomObject;
				DSLAMBOIComputedAccumulatedTransitData objectifyObjectAccumulated 	= (DSLAMBOIComputedAccumulatedTransitData)  objectifyObject;
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
