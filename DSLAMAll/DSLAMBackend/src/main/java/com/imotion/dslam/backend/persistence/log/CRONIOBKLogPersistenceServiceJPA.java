package com.imotion.dslam.backend.persistence.log;

import java.util.List;

import com.imotion.dslam.backend.persistence.jpa.DSLAMBKPersistenceModuleJPA;
import com.imotion.dslam.backend.persistence.jpa.DSLAMBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.CRONIOBOILog;
import com.imotion.dslam.bom.data.CRONIOBOLog;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;

public class CRONIOBKLogPersistenceServiceJPA extends DSLAMBKPersistenceServiceBaseJPA<CRONIOBOILog, CRONIOBOLog, String> implements CRONIOBKILogPersistenceService{


	private static final long serialVersionUID = 1L;

		
	@Override
	public List<CRONIOBOILog> getAllLogs() {
		

		//List<CRONIOBOLog> logsListJpa = getPersistenceModule().findAll();

		
		String customQuery = "Select o from logs o where o.message like '37%'";
//		String customQuery = "Select o from CRONIOBOLog o where o.lineNumber like '10%'";
//		String customQuery = "Select o from CRONIOBOLog o where o.level like 'DEBU%'";
		List<CRONIOBOLog> logsListJpa = getPersistenceModule().query(customQuery);
		
//		String customQuery = "db.logs.find({ level: 'DEBUG' })";
//		List<CRONIOBOLog> logsListJpa = getPersistenceModule().nativeQuery(customQuery);
		

		return AEMFTCommonUtilsBase.castList(logsListJpa);
	}
	
	/**
	 * AEMFTIHasPersistenceModule
	 */
	@Override
	public Class<CRONIOBOLog> getPersistenceClass() {
		return CRONIOBOLog.class;
	}

	@Override
	protected void setPersistenceUnit() {
		getPersistenceModule().setPersitenceUnitName(DSLAMBKPersistenceModuleJPA.MONGO_PERSISTENCE_UNIT_NAME);
	}


}
