package com.imotion.dslam.backend.persistence.service.computedtransitdata;

import java.util.Date;

import com.imotion.dslam.bom.DSLAMBOIComputedTransitData;


public interface DSLAMBKIComputedTransitDataPersistenceService {

	DSLAMBOIComputedTransitData addComputedTransitData(DSLAMBOIComputedTransitData nodeData);

	DSLAMBOIComputedTransitData getComputedTransitData(Date date, int type);

}
