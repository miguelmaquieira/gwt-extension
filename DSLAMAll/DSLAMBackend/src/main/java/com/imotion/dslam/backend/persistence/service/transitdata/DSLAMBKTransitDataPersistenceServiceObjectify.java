package com.imotion.dslam.backend.persistence.service.transitdata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imotion.dslam.backend.persistence.objectify.DSLAMBKPersistenceServiceBaseObjectify;
import com.imotion.dslam.bom.DSLAMBOITransitData;
import com.imotion.dslam.bom.data.objectify.DSLAMBOTransitDataObjectify;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.back.persistence.AEMFTIPersistenceQueryConstants;
import com.selene.arch.exe.back.persistence.AEMFTPersistenceQueryParam;
import com.selene.arch.exe.back.persistence.AEMFTPersistenceSortParam;


public class DSLAMBKTransitDataPersistenceServiceObjectify extends DSLAMBKPersistenceServiceBaseObjectify<DSLAMBOITransitData, DSLAMBOTransitDataObjectify, Long> implements DSLAMBKITransitDataPersistenceService {

	private static final long serialVersionUID = -389789786125617603L;


	public DSLAMBKTransitDataPersistenceServiceObjectify() {
		super();
	}
	
	@Override
	public DSLAMBOITransitData addTransitData(DSLAMBOITransitData dslamData) {
		if (dslamData != null) {
			DSLAMBOTransitDataObjectify nodeDataOb = getObjectifyObject(dslamData);
			dslamData = getPersistenceModule().create(nodeDataOb);
		}
		return dslamData;
	}
	
	@Override
	public DSLAMBOITransitData getLastTransitData() {
		DSLAMBOITransitData lastTransitData = null;
		
		//QUERY
		List<AEMFTPersistenceSortParam> sortParams = new ArrayList<>();
		
		AEMFTPersistenceSortParam timeEndSortParam = new AEMFTPersistenceSortParam(DSLAMBOITransitData.TIME_END, false);
		sortParams.add(timeEndSortParam);
		
		List<DSLAMBOTransitDataObjectify> dslamDataList = getPersistenceModule().findAll(0, 1, sortParams.iterator());
		
		if (!AEMFTCommonUtilsBase.isEmptyList(dslamDataList)) {
			lastTransitData = dslamDataList.get(0);
		}
		return lastTransitData;
	}
	
	@Override
	public List<DSLAMBOITransitData> getTransitData(Date ini, Date end) {
		List<AEMFTPersistenceQueryParam<?>> queryParams = new ArrayList<>();
		
		AEMFTPersistenceQueryParam<Date> iniParam	= new AEMFTPersistenceQueryParam<>(DSLAMBOITransitData.TIME_INIT, AEMFTIPersistenceQueryConstants.GREATER_THAN,		ini);
		queryParams.add(iniParam);
		
		AEMFTPersistenceQueryParam<Date> endParam	= new AEMFTPersistenceQueryParam<>(DSLAMBOITransitData.TIME_END, AEMFTIPersistenceQueryConstants.LESS_EQUALS_THAN,	end);
		queryParams.add(endParam);
		
		List<DSLAMBOTransitDataObjectify> 	dslamDataObList	= getPersistenceModule().query(queryParams.iterator(), 0, 1);
		List<DSLAMBOITransitData>			dslamDataList		= AEMFTCommonUtilsBase.castList(dslamDataObList);
		return dslamDataList;
	}

	/**
	 * AEMFTPersistenceServiceBase
	 */
	
	@Override
	public Class<DSLAMBOTransitDataObjectify> getPersistenceClass() {
		return DSLAMBOTransitDataObjectify.class;
	}
	
	/**
	 * UTILS
	 */
	public static DSLAMBOTransitDataObjectify getObjectifyObject(DSLAMBOITransitData bomObject) {
		DSLAMBOTransitDataObjectify objectifyObject = null;
		if (bomObject != null) {
			objectifyObject = new DSLAMBOTransitDataObjectify();
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
