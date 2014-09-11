package com.imotion.dslam.backend.persistence.log;

import java.util.Date;
import java.util.List;

import com.imotion.dslam.backend.persistence.jpa.DSLAMBKPersistenceModuleJPA;
import com.imotion.dslam.backend.persistence.jpa.DSLAMBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.CRONIOBOILog;
import com.imotion.dslam.bom.data.CRONIOBOLog;
import com.imotion.dslam.bom.data.CRONIOBOLogFilter;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;

public class CRONIOBKLogPersistenceServiceJPA extends DSLAMBKPersistenceServiceBaseJPA<CRONIOBOILog, CRONIOBOLog, String> implements CRONIOBKILogPersistenceService{

	private static final long serialVersionUID = 1L;

	@Override
	public List<CRONIOBOILog> getLogsByfilter(CRONIOBOLogFilter filterData) {

		
		
		
		
		String 	text			= filterData.getFilterText();
		String 	executionID		= filterData.getExecutionID();
		Date   	timestamp		= filterData.getMaxTimestamp();
		String 	level			= filterData.getLevel();
		int 	offset			= filterData.getOffset();
		int 	size			= filterData.getSize();	
		
		if (text == null) {
			text = "";
		}
		


		
		
		String customQuery	= "SELECT o FROM CRONIOBOLog o "
							+ "WHERE (o.message LIKE '%"+ executionID +"%' "
							+ "AND o.message LIKE '%"+ text +"%' "
							+ "AND o.timestamp < :"+ CRONIOBOLogFilter.MAX_TIMESTAMP +" )";
		
		List<CRONIOBOLog> logsListJpa = getPersistenceModule().queryDate(customQuery, CRONIOBOLogFilter.MAX_TIMESTAMP, timestamp, offset, size);


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
