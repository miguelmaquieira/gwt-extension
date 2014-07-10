package com.imotion.dslam.backend.persistence;

import com.imotion.cronio.backend.persistence.service.node.CRONIOBKNodePersistenceServiceJPA;
import com.imotion.dslam.backend.DSLAMBKIConstants;
import com.imotion.dslam.backend.persistence.service.file.DSLAMBKFilePersistenceServiceJPA;
import com.imotion.dslam.backend.persistence.service.machineproperties.CRONIOBKMachinePropertiesPersistenceServiceJPA;
import com.imotion.dslam.backend.persistence.service.preferences.CRONIOBKPreferencesPersistenceServiceJPA;
import com.imotion.dslam.backend.persistence.service.process.DSLAMBKProcessPersistenceServiceJPA;
import com.imotion.dslam.backend.persistence.service.project.DSLAMBKProjectPersistenceServiceJPA;
import com.selene.arch.base.MFTIConstant;

public interface DSLAMBKIPersistenceConstants {

	// Persistence subtypes
	public final static long 	CTE_DSLAM_PERSISTENCE_BASE_SERVICE_SUBTYPE 		= 0x0000000001000000L;
	public final static long 	CTE_DSLAM_PERSISTENCE_LOGIN_SERVICE_SUBTYPE 	= 0x0000000002000000L;

	// 0x0000000001000000L | 0x0021130000000000L = 0x0021130001000000L
	public final static long 		CTE_DSLAM_PERSISTENCE_BASE_SERVICE_TYPE					= CTE_DSLAM_PERSISTENCE_BASE_SERVICE_SUBTYPE | DSLAMBKIConstants.CTE_DSLAM_BACKEND_SERVICE_TYPE;
	public final static String 	CTE_DSLAM_PERSISTENCE_BASE_SERVICE_HEX_TYPE				= Long.toHexString(CTE_DSLAM_PERSISTENCE_BASE_SERVICE_TYPE);
	public final static String 	CTE_DSLAM_PERSISTENCE_BASE_SERVICE_NAME					= "DSLAMBKPersistenceModuleBase";

	// Constants
	public final static long 	CTE_DSLAM_PERSISTENCE_BASE_SERVICE_RANGE_TRACE				= CTE_DSLAM_PERSISTENCE_BASE_SERVICE_TYPE | MFTIConstant.CTE_MFT_RANGE_TRACE;

	//FILE
	public static final String CFG_DSLAM_PERSISTENCE_FILE_PERSISTENCE_IMPL				= "BACKEND.FILE_PERSISTENCE_IMPL";
	public static final String CTE_DSLAM_PERSISTENCE_FILE_PERSISTENCE_DEFAULT_IMPL		= DSLAMBKFilePersistenceServiceJPA.class.getName();

	//PROCESS
	public static final String CFG_DSLAM_PERSISTENCE_PROCESS_PERSISTENCE_IMPL				= "BACKEND.PROCESS_PERSISTENCE_IMPL";
	public static final String CTE_DSLAM_PERSISTENCE_PROCESS_PERSISTENCE_DEFAULT_IMPL		= DSLAMBKProcessPersistenceServiceJPA.class.getName();

	//PROJECT
	public static final String CFG_DSLAM_PERSISTENCE_PROJECT_PERSISTENCE_IMPL				= "BACKEND.PROJECT_PERSISTENCE_IMPL";
	public static final String CTE_DSLAM_PERSISTENCE_PROJECT_PERSISTENCE_DEFAULT_IMPL		= DSLAMBKProjectPersistenceServiceJPA.class.getName();

	//NODE
	public static final String CFG_CRONIO_PERSISTENCE_NODE_PERSISTENCE_IMPL				= "BACKEND.NODE_PERSISTENCE_IMPL";
	public static final String CTE_CRONIO_PERSISTENCE_NODE_PERSISTENCE_DEFAULT_IMPL		= CRONIOBKNodePersistenceServiceJPA.class.getName();
	

	//PREFERENCES
	public static final String CFG_CRONIO_PERSISTENCE_PREFERENCES_PERSISTENCE_IMPL			= "BACKEND.PREFERENCES_PERSISTENCE_IMPL";
	public static final String CTE_CRONIO_PERSISTENCE_PREFERENCES_PERSISTENCE_DEFAULT_IMPL	= CRONIOBKPreferencesPersistenceServiceJPA.class.getName();

	//MACHINE PROPERTIES
	public static final String CFG_CRONIO_PERSISTENCE_MACHINE_PROPERTIES_PERSISTENCE_IMPL			= "BACKEND.MACHINE_PROPERTIES_PERSISTENCE_IMPL";
	public static final String CTE_CRONIO_PERSISTENCE_MACHINE_PROPERTIES_PERSISTENCE_DEFAULT_IMPL	= CRONIOBKMachinePropertiesPersistenceServiceJPA.class.getName();

}
