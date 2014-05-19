package com.imotion.transit.backend.persistence.service.computedtransitdata;

import java.util.Date;

import com.imotion.transit.bom.TRANSBOIComputedTransitData;


public interface TRANSBKIComputedTransitDataPersistenceService {

	TRANSBOIComputedTransitData addComputedTransitData(TRANSBOIComputedTransitData nodeData);

	TRANSBOIComputedTransitData getComputedTransitData(Date date, int type);

}
