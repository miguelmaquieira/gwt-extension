package com.imotion.transit.backend.persistence;

import com.imotion.transit.backend.TRANSBKIConstants;
import com.imotion.transit.backend.persistence.service.computedtransitdata.TRANSBKComputedTransitDataPersistenceServiceObjectify;
import com.imotion.transit.backend.persistence.service.transitdata.TRANSBKTransitDataPersistenceServiceObjectify;
import com.selene.arch.base.MFTIConstant;

public interface TRANSBKIPersistenceConstants {

	// Persistence subtypes
	public final static long 	CTE_TRANS_PERSISTENCE_BASE_SERVICE_SUBTYPE 		= 0x0000000001000000L;
	public final static long 	CTE_TRANS_PERSISTENCE_LOGIN_SERVICE_SUBTYPE 		= 0x0000000002000000L;

	// 0x0000000001000000L | 0x0021130000000000L = 0x0021130001000000L
	public final static long 		CTE_TRANS_PERSISTENCE_BASE_SERVICE_TYPE					= CTE_TRANS_PERSISTENCE_BASE_SERVICE_SUBTYPE | TRANSBKIConstants.CTE_TRANS_BACKEND_SERVICE_TYPE;
	public final static String 	CTE_TRANS_PERSISTENCE_BASE_SERVICE_HEX_TYPE				= Long.toHexString(CTE_TRANS_PERSISTENCE_BASE_SERVICE_TYPE);
	public final static String 	CTE_TRANS_PERSISTENCE_BASE_SERVICE_NAME					= "TRANSBKPersistenceModuleBase";

	// Constants
	public final static long 	CTE_TRANS_PERSISTENCE_BASE_SERVICE_RANGE_TRACE				= CTE_TRANS_PERSISTENCE_BASE_SERVICE_TYPE | MFTIConstant.CTE_MFT_RANGE_TRACE;

	//TransitData
	public static final String CFG_TRANS_PERSISTENCE_TRANSIT_DATA_PERSISTENCE_IMPL				= "BACKEND.TRANSIT_DATA_PERSISTENCE_IMPL";
	public static final String CTE_TRANS_PERSISTENCE_TRANSIT_DATA_PERSISTENCE_DEFAULT_IMPL		= TRANSBKTransitDataPersistenceServiceObjectify.class.getName();

	//ComputedTransitData
	public static final String CFG_TRANS_PERSISTENCE_COMPUTED_TRANSIT_DATA_PERSISTENCE_IMPL				= "BACKEND.COMPUTED_TRANSIT_DATA_PERSISTENCE_IMPL";
	public static final String CTE_TRANS_PERSISTENCE_COMPUTED_TRANSIT_DATA_PERSISTENCE_DEFAULT_IMPL		= TRANSBKComputedTransitDataPersistenceServiceObjectify.class.getName();


}
