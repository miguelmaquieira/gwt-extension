package com.imotion.dslam.business.service.impl;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.imotion.dslam.bom.DSLAMBOIComputedDailyTransitData;
import com.imotion.dslam.bom.DSLAMBOIComputedTransitData;
import com.imotion.dslam.bom.DSLAMBOITransitData;
import com.imotion.dslam.bom.data.DSLAMBOComputedDailyTransitData;
import com.imotion.dslam.bom.data.DSLAMBOTransitData;
import com.imotion.dslam.business.service.DSLAMBUBusinessServiceBase;
import com.imotion.dslam.business.service.DSLAMBUIBusinessCronServiceTrace;
import com.imotion.dslam.business.service.DSLAMBUICronBusinessService;
import com.imotion.dslam.business.service.DSLAMBUICronBusinessServiceConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.core.appli.http.AEMFTHTTPJSONClientUtils;
import com.selene.arch.exe.core.appli.http.AEMFTHTTPJSONException;
import com.selene.arch.exe.core.appli.metadata.element.factory.AEMFTMetadataElementReflectionBasedFactory;
import com.selene.arch.exe.core.common.AEMFTCommonUtils;

public class DSLAMBUCronBusinessServiceImpl extends DSLAMBUBusinessServiceBase implements DSLAMBUICronBusinessService, DSLAMBUICronBusinessServiceConstants, DSLAMBUIBusinessCronServiceTrace {

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
		DSLAMBOITransitData lastTransitData = getTransitDataPersistence().getLastTransitData();
		if (lastTransitData != null) {
			Date lastTransitDataDate = lastTransitData.getTimeEnd();
			initDate = new DateTime(lastTransitDataDate.getTime());
		} else {
			initDate = new DateTime(0);
		}
		
		AEMFTMetadataElementComposite query = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		getElementDataController().setElement(QUERY_TIME_TABLE	, query	, "demo_1_0_1_metrics");
		getElementDataController().setElement(QUERY_TIME_INIT	, query	, initDate.toString(DSLAMIT_DATE_FORMAT));
		getElementDataController().setElement(QUERY_TIME_END	, query	, endDate.toString(DSLAMIT_DATE_FORMAT));

		try {
			AEMFTHTTPJSONClientUtils		httpJsonUtils	= new AEMFTHTTPJSONClientUtils("http://ec2-54-229-98-238.eu-west-1.compute.amazonaws.com:8189/1.0/event/request", "SELENE");
			AEMFTMetadataElementComposite 	response		= httpJsonUtils.postMetadata(query);
			if (response != null) {
				List<AEMFTMetadataElement> dataElements = response.getSortedElementList();
				for (AEMFTMetadataElement dataElement : dataElements) {
					String passingTransitStr	= getElementDataController().getElementAsString(DSLAMIT_PASSING, 	dataElement);
					String enteringTransitStr	= getElementDataController().getElementAsString(DSLAMIT_IN, 		dataElement);
					String totalTransitStr		= getElementDataController().getElementAsString(DSLAMIT_TOTAL, 		dataElement);
					String timeInitStr			= getElementDataController().getElementAsString(DSLAMIT_INIT, 		dataElement);
					String timeEndStr			= getElementDataController().getElementAsString(DSLAMIT_END, 		dataElement);
					
					int passingTransit		= AEMFTCommonUtils.getIntegerFromString(passingTransitStr);
					int enteringTransit		= AEMFTCommonUtils.getIntegerFromString(enteringTransitStr);
					int totalTransit		= AEMFTCommonUtils.getIntegerFromString(totalTransitStr);
					Date timeInit			= AEMFTCommonUtils.getDateFromFormattedString(timeInitStr	, DSLAMIT_DATE_FORMAT);
					Date timeEnd			= AEMFTCommonUtils.getDateFromFormattedString(timeEndStr	, DSLAMIT_DATE_FORMAT);
					
					DSLAMBOITransitData dslamdata = new DSLAMBOTransitData();
					dslamdata.setPassingTransit(passingTransit);
					dslamdata.setIncomingTransit(enteringTransit);
					dslamdata.setTotalTransit(totalTransit);
					dslamdata.setTimeInit(timeInit);
					dslamdata.setTimeEnd(timeEnd);
					dslamdata.setTimestamp(new Date());
					
					getTransitDataPersistence().addTransitData(dslamdata);
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
		List<DSLAMBOITransitData> todayTransitDataList = getTransitDataPersistence().getTransitData(init.toDate(), now.toDate());
		
		if (!AEMFTCommonUtils.isEmptyList(todayTransitDataList)) {
			
			DSLAMBOIComputedDailyTransitData computedDailyData = new DSLAMBOComputedDailyTransitData();
			
			computedDailyData = setupTodayTransitData(computedDailyData, todayTransitDataList);
			
			DateTime yesterday	= new DateTime().minusDays(1);
			DSLAMBOIComputedTransitData	yesterdayComputedTransitData 				= getComputedTransitDataPersistence().getComputedTransitData(yesterday.toDate(), DSLAMBOIComputedTransitData.FREQUENCY_TYPE_DAILY);
			
			computedDailyData = setupLastWeekTransitData(computedDailyData, yesterdayComputedTransitData, now);
			
			DateTime thirtyOneDaysAgo	= now.minusDays(31);
			DSLAMBOIComputedTransitData	thirtyOneDaysAgoComputedTransitData 		= getComputedTransitDataPersistence().getComputedTransitData(thirtyOneDaysAgo.toDate(), DSLAMBOIComputedTransitData.FREQUENCY_TYPE_DAILY);
			
			DateTime threeHundredSixDaysAgo	= now.minusDays(366);
			DSLAMBOIComputedTransitData	threeHundredSixDaysAgoComputedTransitData 	= getComputedTransitDataPersistence().getComputedTransitData(threeHundredSixDaysAgo.toDate(), DSLAMBOIComputedTransitData.FREQUENCY_TYPE_DAILY);
			
			if (yesterdayComputedTransitData != null) {
				
			}
			
		}
		
	}

	private DSLAMBOIComputedDailyTransitData setupLastWeekTransitData(DSLAMBOIComputedDailyTransitData computedDailyData, DSLAMBOIComputedTransitData yesterdayComputedTransitData, DateTime now) {

		DateTime eightDaysAgo		= now.minusDays(8);
		DSLAMBOIComputedTransitData	eightDaysAgoComputedTransitData 			= getComputedTransitDataPersistence().getComputedTransitData(eightDaysAgo.toDate(), DSLAMBOIComputedTransitData.FREQUENCY_TYPE_DAILY);
		
		return computedDailyData;
	}

	protected DSLAMBOIComputedDailyTransitData setupTodayTransitData(DSLAMBOIComputedDailyTransitData computedDailyData, List<DSLAMBOITransitData> todayTransitDataList) {
		int todayIncomingTransit	= 0;
		int todayPassingTransit		= 0;
		int todayTotalTransit		= 0;
		
		for (DSLAMBOITransitData dslamData : todayTransitDataList) {
			todayIncomingTransit	+= dslamData.getIncomingTransit();
			todayPassingTransit		+= dslamData.getPassingTransit();
			todayTotalTransit		+= dslamData.getTotalTransit();
		}
		
		computedDailyData.setPartialIncomingTransit(todayIncomingTransit);
		computedDailyData.setPartialPassingTransit(todayPassingTransit);
		computedDailyData.setPartialTotalTransit(todayTotalTransit);
		
		return computedDailyData;
	}

}
