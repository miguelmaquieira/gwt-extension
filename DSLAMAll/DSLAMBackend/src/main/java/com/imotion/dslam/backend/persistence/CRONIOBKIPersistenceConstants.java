package com.imotion.dslam.backend.persistence;

import com.imotion.cronio.backend.persistence.service.node.CRONIOBKNodePersistenceServiceJPA;
import com.imotion.cronio.backend.persistence.service.nodelist.CRONIOBKNodeListPersistenceServiceJPA;
import com.imotion.dslam.backend.CRONIOBKIConstants;
import com.imotion.dslam.backend.persistence.log.CRONIOBKLogPersistenceServiceJPA;
import com.imotion.dslam.backend.persistence.login.CRONIOBKLoginPersistenceJPA;
import com.imotion.dslam.backend.persistence.service.execution.CRONIOBKExecutionPersistenceServiceJPA;
import com.imotion.dslam.backend.persistence.service.file.CRONIOBKFilePersistenceServiceJPA;
import com.imotion.dslam.backend.persistence.service.machineproperties.CRONIOBKMachinePropertiesPersistenceServiceJPA;
import com.imotion.dslam.backend.persistence.service.preferences.CRONIOBKPreferencesPersistenceServiceJPA;
import com.imotion.dslam.backend.persistence.service.process.CRONIOBKProcessPersistenceServiceJPA;
import com.imotion.dslam.backend.persistence.service.project.CRONIOBKProjectPersistenceServiceJPA;
import com.imotion.dslam.backend.persistence.service.userpreferences.CRONIOBKUserPreferencesPersistenceServiceJPA;
import com.selene.arch.base.MFTIConstant;

public interface CRONIOBKIPersistenceConstants {

	// Persistence subtypes
	public final static long 	CTE_CRONIO_PERSISTENCE_BASE_SERVICE_SUBTYPE 	= 0x0000000001000000L;
	public final static long 	CTE_CRONIO_PERSISTENCE_LOGIN_SERVICE_SUBTYPE 	= 0x0000000002000000L;

	// 0x0000000001000000L | 0x0021130000000000L = 0x0021130001000000L
	public final static long 		CTE_CRONIO_PERSISTENCE_BASE_SERVICE_TYPE				= CTE_CRONIO_PERSISTENCE_BASE_SERVICE_SUBTYPE | CRONIOBKIConstants.CTE_CRONIO_BACKEND_SERVICE_TYPE;
	public final static String 	CTE_CRONIO_PERSISTENCE_BASE_SERVICE_HEX_TYPE			= Long.toHexString(CTE_CRONIO_PERSISTENCE_BASE_SERVICE_TYPE);
	public final static String 	CTE_CRONIO_PERSISTENCE_BASE_SERVICE_NAME				= "CRONIOBKPersistenceModuleBase";

	// Constants
	public final static long 	CTE_CRONIO_PERSISTENCE_BASE_SERVICE_RANGE_TRACE				= CTE_CRONIO_PERSISTENCE_BASE_SERVICE_TYPE | MFTIConstant.CTE_MFT_RANGE_TRACE;

	//FILE
	public static final String CFG_CRONIO_PERSISTENCE_FILE_PERSISTENCE_IMPL				= "BACKEND.FILE_PERSISTENCE_IMPL";
	public static final String CTE_CRONIO_PERSISTENCE_FILE_PERSISTENCE_DEFAULT_IMPL		= CRONIOBKFilePersistenceServiceJPA.class.getName();

	//PROCESS
	public static final String CFG_CRONIO_PERSISTENCE_PROCESS_PERSISTENCE_IMPL				= "BACKEND.PROCESS_PERSISTENCE_IMPL";
	public static final String CTE_CRONIO_PERSISTENCE_PROCESS_PERSISTENCE_DEFAULT_IMPL		= CRONIOBKProcessPersistenceServiceJPA.class.getName();

	//PROJECT
	public static final String CFG_CRONIO_PERSISTENCE_PROJECT_PERSISTENCE_IMPL				= "BACKEND.PROJECT_PERSISTENCE_IMPL";
	public static final String CTE_CRONIO_PERSISTENCE_PROJECT_PERSISTENCE_DEFAULT_IMPL		= CRONIOBKProjectPersistenceServiceJPA.class.getName();

	//NODE
	public static final String CFG_CRONIO_PERSISTENCE_NODE_PERSISTENCE_IMPL				= "BACKEND.NODE_PERSISTENCE_IMPL";
	public static final String CTE_CRONIO_PERSISTENCE_NODE_PERSISTENCE_DEFAULT_IMPL		= CRONIOBKNodePersistenceServiceJPA.class.getName();
	
	//NODE_LIST
	public static final String CFG_CRONIO_PERSISTENCE_NODELIST_PERSISTENCE_IMPL				= "BACKEND.NODELIST_PERSISTENCE_IMPL";
	public static final String CTE_CRONIO_PERSISTENCE_NODELIST_PERSISTENCE_DEFAULT_IMPL		= CRONIOBKNodeListPersistenceServiceJPA.class.getName();


	//PREFERENCES
	public static final String CFG_CRONIO_PERSISTENCE_PREFERENCES_PERSISTENCE_IMPL			= "BACKEND.PREFERENCES_PERSISTENCE_IMPL";
	public static final String CTE_CRONIO_PERSISTENCE_PREFERENCES_PERSISTENCE_DEFAULT_IMPL	= CRONIOBKPreferencesPersistenceServiceJPA.class.getName();

	//MACHINE PROPERTIES
	public static final String CFG_CRONIO_PERSISTENCE_MACHINE_PROPERTIES_PERSISTENCE_IMPL			= "BACKEND.MACHINE_PROPERTIES_PERSISTENCE_IMPL";
	public static final String CTE_CRONIO_PERSISTENCE_MACHINE_PROPERTIES_PERSISTENCE_DEFAULT_IMPL	= CRONIOBKMachinePropertiesPersistenceServiceJPA.class.getName();

	//User
	public static final String CFG_CRONIO_PERSISTENCE_USER_PERSISTENCE_IMPL				= "BACKEND.USER_PERSISTENCE_IMPL";
	public static final String CTE_CRONIO_PERSISTENCE_USER_PERSISTENCE_DEFAULT_IMPL		= CRONIOBKLoginPersistenceJPA.class.getName();

	//User preferences
	public static final String CFG_CRONIO_PERSISTENCE_USER_PREFERENCES_PERSISTENCE_IMPL				= "BACKEND.USER_PREFERENCES_PERSISTENCE_IMPL";
	public static final String CTE_CRONIO_PERSISTENCE_USER_PREFERENCES_PERSISTENCE_DEFAULT_IMPL		= CRONIOBKUserPreferencesPersistenceServiceJPA.class.getName();
	
	//Execution
	public static final String CFG_CRONIO_PERSISTENCE_EXECUTION_PERSISTENCE_IMPL				= "BACKEND.EXECUTION_PERSISTENCE_IMPL";
	public static final String CTE_CRONIO_PERSISTENCE_EXECUTION_PERSISTENCE_DEFAULT_IMPL		= CRONIOBKExecutionPersistenceServiceJPA.class.getName();

	//Log
	public static final String CFG_CRONIO_PERSISTENCE_LOG_PERSISTENCE_IMPL				= "BACKEND.LOG_PERSISTENCE_IMPL";
	public static final String CTE_CRONIO_PERSISTENCE_LOG_PERSISTENCE_DEFAULT_IMPL		= CRONIOBKLogPersistenceServiceJPA.class.getName();
	
}
