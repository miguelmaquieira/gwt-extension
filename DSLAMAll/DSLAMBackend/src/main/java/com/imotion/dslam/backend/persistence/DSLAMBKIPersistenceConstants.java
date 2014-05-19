package com.imotion.dslam.backend.persistence;

import com.imotion.dslam.backend.DSLAMBKIConstants;
import com.imotion.dslam.backend.persistence.service.computedtransitdata.DSLAMBKComputedTransitDataPersistenceServiceObjectify;
import com.imotion.dslam.backend.persistence.service.transitdata.DSLAMBKTransitDataPersistenceServiceObjectify;
import com.selene.arch.base.MFTIConstant;

public interface DSLAMBKIPersistenceConstants {

	// Persistence subtypes
	public final static long 	CTE_DSLAM_PERSISTENCE_BASE_SERVICE_SUBTYPE 		= 0x0000000001000000L;
	public final static long 	CTE_DSLAM_PERSISTENCE_LOGIN_SERVICE_SUBTYPE 		= 0x0000000002000000L;

	// 0x0000000001000000L | 0x0021130000000000L = 0x0021130001000000L
	public final static long 		CTE_DSLAM_PERSISTENCE_BASE_SERVICE_TYPE					= CTE_DSLAM_PERSISTENCE_BASE_SERVICE_SUBTYPE | DSLAMBKIConstants.CTE_DSLAM_BACKEND_SERVICE_TYPE;
	public final static String 	CTE_DSLAM_PERSISTENCE_BASE_SERVICE_HEX_TYPE				= Long.toHexString(CTE_DSLAM_PERSISTENCE_BASE_SERVICE_TYPE);
	public final static String 	CTE_DSLAM_PERSISTENCE_BASE_SERVICE_NAME					= "DSLAMBKPersistenceModuleBase";

	// Constants
	public final static long 	CTE_DSLAM_PERSISTENCE_BASE_SERVICE_RANGE_TRACE				= CTE_DSLAM_PERSISTENCE_BASE_SERVICE_TYPE | MFTIConstant.CTE_MFT_RANGE_TRACE;

	//TransitData
	public static final String CFG_DSLAM_PERSISTENCE_DSLAMIT_DATA_PERSISTENCE_IMPL				= "BACKEND.DSLAMIT_DATA_PERSISTENCE_IMPL";
	public static final String CTE_DSLAM_PERSISTENCE_DSLAMIT_DATA_PERSISTENCE_DEFAULT_IMPL		= DSLAMBKTransitDataPersistenceServiceObjectify.class.getName();

	//ComputedTransitData
	public static final String CFG_DSLAM_PERSISTENCE_COMPUTED_DSLAMIT_DATA_PERSISTENCE_IMPL				= "BACKEND.COMPUTED_DSLAMIT_DATA_PERSISTENCE_IMPL";
	public static final String CTE_DSLAM_PERSISTENCE_COMPUTED_DSLAMIT_DATA_PERSISTENCE_DEFAULT_IMPL		= DSLAMBKComputedTransitDataPersistenceServiceObjectify.class.getName();


}
