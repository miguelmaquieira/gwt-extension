package com.imotion.transit.business.service.impl;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.imotion.transit.bom.TRANSBOIComputedDailyTransitData;
import com.imotion.transit.bom.TRANSBOIComputedTransitData;
import com.imotion.transit.bom.TRANSBOITransitData;
import com.imotion.transit.bom.data.TRANSBOComputedDailyTransitData;
import com.imotion.transit.bom.data.TRANSBOTransitData;
import com.imotion.transit.business.service.TRANSBUBusinessServiceBase;
import com.imotion.transit.business.service.TRANSBUIBusinessCronServiceTrace;
import com.imotion.transit.business.service.TRANSBUICronBusinessService;
import com.imotion.transit.business.service.TRANSBUICronBusinessServiceConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.core.appli.http.AEMFTHTTPJSONClientUtils;
import com.selene.arch.exe.core.appli.http.AEMFTHTTPJSONException;
import com.selene.arch.exe.core.appli.metadata.element.factory.AEMFTMetadataElementReflectionBasedFactory;
import com.selene.arch.exe.core.common.AEMFTCommonUtils;

public class TRANSBUCronBusinessServiceImpl extends TRANSBUBusinessServiceBase implements TRANSBUICronBusinessService, TRANSBUICronBusinessServiceConstants, TRANSBUIBusinessCronServiceTrace {

	private static final long serialVersionUID = -6461242345046034538L;

	@Override
	public void storeLastData() {
		storeLastTransitData();
	}
	
	@Override
	public void computeDailyData() {
		computeDailyTransitData();
	}

	/**
	 * PRIVATE METHODS
	 */

	private void storeLastTransitData() {
		DateTime endDate 	= new DateTime();
		DateTime initDate	= null;
		TRANSBOITransitData lastTransitData = getTransitDataPersistence().getLastTransitData();
		if (lastTransitData != null) {
			Date lastTransitDataDate = lastTransitData.getTimeEnd();
			initDate = new DateTime(lastTransitDataDate.getTime());
		} else {
			initDate = new DateTime(0);
		}
		
		AEMFTMetadataElementComposite query = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		getElementDataController().setElement(QUERY_TIME_TABLE	, query	, "demo_1_0_1_metrics");
		getElementDataController().setElement(QUERY_TIME_INIT	, query	, initDate.toString(TRANSIT_DATE_FORMAT));
		getElementDataController().setElement(QUERY_TIME_END	, query	, endDate.toString(TRANSIT_DATE_FORMAT));

		try {
			AEMFTHTTPJSONClientUtils		httpJsonUtils	= new AEMFTHTTPJSONClientUtils("http://ec2-54-229-98-238.eu-west-1.compute.amazonaws.com:8189/1.0/event/request", "SELENE");
			AEMFTMetadataElementComposite 	response		= httpJsonUtils.postMetadata(query);
			if (response != null) {
				List<AEMFTMetadataElement> dataElements = response.getSortedElementList();
				for (AEMFTMetadataElement dataElement : dataElements) {
					String passingTransitStr	= getElementDataController().getElementAsString(TRANSIT_PASSING, 	dataElement);
					String enteringTransitStr	= getElementDataController().getElementAsString(TRANSIT_IN, 		dataElement);
					String totalTransitStr		= getElementDataController().getElementAsString(TRANSIT_TOTAL, 		dataElement);
					String timeInitStr			= getElementDataController().getElementAsString(TRANSIT_INIT, 		dataElement);
					String timeEndStr			= getElementDataController().getElementAsString(TRANSIT_END, 		dataElement);
					
					int passingTransit		= AEMFTCommonUtils.getIntegerFromString(passingTransitStr);
					int enteringTransit		= AEMFTCommonUtils.getIntegerFromString(enteringTransitStr);
					int totalTransit		= AEMFTCommonUtils.getIntegerFromString(totalTransitStr);
					Date timeInit			= AEMFTCommonUtils.getDateFromFormattedString(timeInitStr	, TRANSIT_DATE_FORMAT);
					Date timeEnd			= AEMFTCommonUtils.getDateFromFormattedString(timeEndStr	, TRANSIT_DATE_FORMAT);
					
					TRANSBOITransitData transitdata = new TRANSBOTransitData();
					transitdata.setPassingTransit(passingTransit);
					transitdata.setIncomingTransit(enteringTransit);
					transitdata.setTotalTransit(totalTransit);
					transitdata.setTimeInit(timeInit);
					transitdata.setTimeEnd(timeEnd);
					transitdata.setTimestamp(new Date());
					
					getTransitDataPersistence().addTransitData(transitdata);
				}
			}
		} catch (AEMFTHTTPJSONException e) {
			//init-trace
			getTrace().trace(CTE_CRON_BUSINESS_SERVICE_COMMUNICATION_ERROR, new Object[] {e.getMessage()});
			//end-trace
		}
	}
	
	private void computeDailyTransitData() {
		DateTime init	= new DateTime().withMillis(0);
		DateTime now	= new DateTime();
		List<TRANSBOITransitData> todayTransitDataList = getTransitDataPersistence().getTransitData(init.toDate(), now.toDate());
		
		if (!AEMFTCommonUtils.isEmptyList(todayTransitDataList)) {
			
			TRANSBOIComputedDailyTransitData computedDailyData = new TRANSBOComputedDailyTransitData();
			
			computedDailyData = setupTodayTransitData(computedDailyData, todayTransitDataList);
			
			DateTime yesterday	= new DateTime().minusDays(1);
			TRANSBOIComputedTransitData	yesterdayComputedTransitData 				= getComputedTransitDataPersistence().getComputedTransitData(yesterday.toDate(), TRANSBOIComputedTransitData.FREQUENCY_TYPE_DAILY);
			
			computedDailyData = setupLastWeekTransitData(computedDailyData, yesterdayComputedTransitData, now);
			
			DateTime thirtyOneDaysAgo	= now.minusDays(31);
			TRANSBOIComputedTransitData	thirtyOneDaysAgoComputedTransitData 		= getComputedTransitDataPersistence().getComputedTransitData(thirtyOneDaysAgo.toDate(), TRANSBOIComputedTransitData.FREQUENCY_TYPE_DAILY);
			
			DateTime threeHundredSixDaysAgo	= now.minusDays(366);
			TRANSBOIComputedTransitData	threeHundredSixDaysAgoComputedTransitData 	= getComputedTransitDataPersistence().getComputedTransitData(threeHundredSixDaysAgo.toDate(), TRANSBOIComputedTransitData.FREQUENCY_TYPE_DAILY);
			
			if (yesterdayComputedTransitData != null) {
				
			}
			
		}
		
	}

	private TRANSBOIComputedDailyTransitData setupLastWeekTransitData(TRANSBOIComputedDailyTransitData computedDailyData, TRANSBOIComputedTransitData yesterdayComputedTransitData, DateTime now) {

		DateTime eightDaysAgo		= now.minusDays(8);
		TRANSBOIComputedTransitData	eightDaysAgoComputedTransitData 			= getComputedTransitDataPersistence().getComputedTransitData(eightDaysAgo.toDate(), TRANSBOIComputedTransitData.FREQUENCY_TYPE_DAILY);
		
		return computedDailyData;
	}

	protected TRANSBOIComputedDailyTransitData setupTodayTransitData(TRANSBOIComputedDailyTransitData computedDailyData, List<TRANSBOITransitData> todayTransitDataList) {
		int todayIncomingTransit	= 0;
		int todayPassingTransit		= 0;
		int todayTotalTransit		= 0;
		
		for (TRANSBOITransitData transitData : todayTransitDataList) {
			todayIncomingTransit	+= transitData.getIncomingTransit();
			todayPassingTransit		+= transitData.getPassingTransit();
			todayTotalTransit		+= transitData.getTotalTransit();
		}
		
		computedDailyData.setPartialIncomingTransit(todayIncomingTransit);
		computedDailyData.setPartialPassingTransit(todayPassingTransit);
		computedDailyData.setPartialTotalTransit(todayTotalTransit);
		
		return computedDailyData;
	}

}
