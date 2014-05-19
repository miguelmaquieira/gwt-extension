package com.imotion.dslam.backend.persistence.service.transitdata;

import java.util.Date;
import java.util.List;

import com.imotion.dslam.bom.DSLAMBOITransitData;


public interface DSLAMBKITransitDataPersistenceService {

	DSLAMBOITransitData addTransitData(DSLAMBOITransitData dslamData);

	DSLAMBOITransitData getLastTransitData();
	
	List<DSLAMBOITransitData>	getTransitData(Date ini, Date end);

}
