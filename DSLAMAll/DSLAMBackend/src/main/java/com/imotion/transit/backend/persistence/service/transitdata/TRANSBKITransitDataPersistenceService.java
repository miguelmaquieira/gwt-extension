package com.imotion.transit.backend.persistence.service.transitdata;

import java.util.Date;
import java.util.List;

import com.imotion.transit.bom.TRANSBOITransitData;


public interface TRANSBKITransitDataPersistenceService {

	TRANSBOITransitData addTransitData(TRANSBOITransitData transitData);

	TRANSBOITransitData getLastTransitData();
	
	List<TRANSBOITransitData>	getTransitData(Date ini, Date end);

}
