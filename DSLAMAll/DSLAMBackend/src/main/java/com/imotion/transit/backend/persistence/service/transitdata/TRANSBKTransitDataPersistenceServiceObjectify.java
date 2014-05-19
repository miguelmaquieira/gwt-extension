package com.imotion.transit.backend.persistence.service.transitdata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imotion.transit.backend.persistence.objectify.TRANSBKPersistenceServiceBaseObjectify;
import com.imotion.transit.bom.TRANSBOITransitData;
import com.imotion.transit.bom.data.objectify.TRANSBOTransitDataObjectify;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.back.persistence.AEMFTIPersistenceQueryConstants;
import com.selene.arch.exe.back.persistence.AEMFTPersistenceQueryParam;
import com.selene.arch.exe.back.persistence.AEMFTPersistenceSortParam;


public class TRANSBKTransitDataPersistenceServiceObjectify extends TRANSBKPersistenceServiceBaseObjectify<TRANSBOITransitData, TRANSBOTransitDataObjectify, Long> implements TRANSBKITransitDataPersistenceService {

	private static final long serialVersionUID = -389789786125617603L;


	public TRANSBKTransitDataPersistenceServiceObjectify() {
		super();
	}
	
	@Override
	public TRANSBOITransitData addTransitData(TRANSBOITransitData transitData) {
		if (transitData != null) {
			TRANSBOTransitDataObjectify nodeDataOb = getObjectifyObject(transitData);
			transitData = getPersistenceModule().create(nodeDataOb);
		}
		return transitData;
	}
	
	@Override
	public TRANSBOITransitData getLastTransitData() {
		TRANSBOITransitData lastTransitData = null;
		
		//QUERY
		List<AEMFTPersistenceSortParam> sortParams = new ArrayList<>();
		
		AEMFTPersistenceSortParam timeEndSortParam = new AEMFTPersistenceSortParam(TRANSBOITransitData.TIME_END, false);
		sortParams.add(timeEndSortParam);
		
		List<TRANSBOTransitDataObjectify> transitDataList = getPersistenceModule().findAll(0, 1, sortParams.iterator());
		
		if (!AEMFTCommonUtilsBase.isEmptyList(transitDataList)) {
			lastTransitData = transitDataList.get(0);
		}
		return lastTransitData;
	}
	
	@Override
	public List<TRANSBOITransitData> getTransitData(Date ini, Date end) {
		List<AEMFTPersistenceQueryParam<?>> queryParams = new ArrayList<>();
		
		AEMFTPersistenceQueryParam<Date> iniParam	= new AEMFTPersistenceQueryParam<>(TRANSBOITransitData.TIME_INIT, AEMFTIPersistenceQueryConstants.GREATER_THAN,		ini);
		queryParams.add(iniParam);
		
		AEMFTPersistenceQueryParam<Date> endParam	= new AEMFTPersistenceQueryParam<>(TRANSBOITransitData.TIME_END, AEMFTIPersistenceQueryConstants.LESS_EQUALS_THAN,	end);
		queryParams.add(endParam);
		
		List<TRANSBOTransitDataObjectify> 	transitDataObList	= getPersistenceModule().query(queryParams.iterator(), 0, 1);
		List<TRANSBOITransitData>			transitDataList		= AEMFTCommonUtilsBase.castList(transitDataObList);
		return transitDataList;
	}

	/**
	 * AEMFTPersistenceServiceBase
	 */
	
	@Override
	public Class<TRANSBOTransitDataObjectify> getPersistenceClass() {
		return TRANSBOTransitDataObjectify.class;
	}
	
	/**
	 * UTILS
	 */
	public static TRANSBOTransitDataObjectify getObjectifyObject(TRANSBOITransitData bomObject) {
		TRANSBOTransitDataObjectify objectifyObject = null;
		if (bomObject != null) {
			objectifyObject = new TRANSBOTransitDataObjectify();
			objectifyObject.setTransitDataId(bomObject.getTransitDataId());
			objectifyObject.setNodeId(bomObject.getNodeId());
			objectifyObject.setTimestamp(bomObject.getTimestamp());
			objectifyObject.setIncomingTransit(bomObject.getIncomingTransit());
			objectifyObject.setPassingTransit(bomObject.getPassingTransit());
			objectifyObject.setTotalTransit(bomObject.getTotalTransit());
			objectifyObject.setTimeInit(bomObject.getTimeInit());
			objectifyObject.setTimeEnd(bomObject.getTimeEnd());
		}
		return objectifyObject;
	}

}
