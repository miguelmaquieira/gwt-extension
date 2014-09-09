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

		
		String customQuery = "Select o from CRONIOBOLog o where o.message like '99:%'";
//		String customQuery = "Select o from CRONIOBOLog o where o.lineNumber like '10%'";"db.logs.find( { message: /^94:/ } )";
//		String customQuery = "Select o from CRONIOBOLog o where o.level like 'DEBU%'";
		
		List<CRONIOBOLog> logsListJpa = getPersistenceModule().query(customQuery);
		
//		String customQuery = "db.logs.find({ level: 'DEBUG' })";
//		List<CRONIOBOLog> logsListJpa = getPersistenceModule().nativeQuery(customQuery);
		

		return AEMFTCommonUtilsBase.castList(logsListJpa);
	}
	
	@Override
	public List<CRONIOBOILog> getExecutionLogs(String executionId) {

		String customQuery = "Select o from CRONIOBOLog o where o.message like '"+ executionId +":%'";
		List<CRONIOBOLog> logsListJpa = getPersistenceModule().query(customQuery);

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
