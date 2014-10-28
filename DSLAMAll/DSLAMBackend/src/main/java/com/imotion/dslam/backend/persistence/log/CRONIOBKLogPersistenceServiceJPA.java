package com.imotion.dslam.backend.persistence.log;

import java.util.Date;
import java.util.List;

import com.imotion.dslam.backend.persistence.jpa.CRONIOBKPersistenceModuleJPA;
import com.imotion.dslam.backend.persistence.jpa.CRONIOBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.CRONIOBOILog;
import com.imotion.dslam.bom.data.CRONIOBOLog;
import com.imotion.dslam.bom.data.CRONIOBOLogFilter;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;

public class CRONIOBKLogPersistenceServiceJPA extends CRONIOBKPersistenceServiceBaseJPA<CRONIOBOILog, CRONIOBOLog, String> implements CRONIOBKILogPersistenceService{

	private static final long serialVersionUID = 1L;


	@Override
	public List<CRONIOBOILog> getLogsByfilter(CRONIOBOLogFilter filterData) {
	
		String 	text			= filterData.getFilterText();
		String 	executionID		= filterData.getExecutionID();
		Date   	beforeDate		= filterData.getMaxTimestamp();
		String 	level			= filterData.getLevel();
		int 	offset			= filterData.getOffset();
		int 	size			= filterData.getSize();
		
		String 		textDeleteSpaces 	= text.replace(" ", "");
		String[] 	textSplit 			= textDeleteSpaces.split("\\,");
	
		String 		textQuery 	= "";
		for (int i = 0; i < textSplit.length; i++) {
			textQuery = textQuery + "AND o.message LIKE '%"+ textSplit[i] +"%' ";
		}
		
		String customQuery	= "SELECT o FROM CRONIOBOLog o "
							+ "WHERE (o.message LIKE '"+ executionID +":%' "
							+ textQuery
							+ "AND o.timestamp < :"+ CRONIOBOLog.TIMESTAMP +")";
		
		List<CRONIOBOLog> logsListJpa = getPersistenceModule().queryDate(customQuery, CRONIOBOLog.TIMESTAMP, beforeDate, offset, size);		
		
		//Temporal solution(bug:eclipselink query methods setMaxRows and setFirstResult don't work together)		
		List<CRONIOBOLog> subLogList = getSublist(logsListJpa, size); 
			
		return AEMFTCommonUtilsBase.castList(subLogList);
	}
	
	@Override
	public int getTotalLogsByfilter(CRONIOBOLogFilter filterData) {
	
		String 	text			= filterData.getFilterText();
		String 	executionID		= filterData.getExecutionID();
		Date   	beforeDate		= filterData.getMaxTimestamp();
		String 	level			= filterData.getLevel();
		
		String 		textDeleteSpaces 	= text.replace(" ", "");
		String[] 	textSplit 			= textDeleteSpaces.split("\\,");
	
		String 		textQuery 	= "";
		for (int i = 0; i < textSplit.length; i++) {
			textQuery = textQuery + "AND o.message LIKE '%"+ textSplit[i] +"%' ";
		}
		
		String customQuery	= "SELECT o FROM CRONIOBOLog o "
							+ "WHERE (o.message LIKE '"+ executionID +":%' "
							+ textQuery
							+ "AND o.timestamp < :"+ CRONIOBOLog.TIMESTAMP +")";
		
		List<CRONIOBOLog> logsListJpa = getPersistenceModule().queryDate(customQuery, CRONIOBOLog.TIMESTAMP, beforeDate);

		return logsListJpa.size();
	}
	
	@Override
	public List<CRONIOBOILog> getExecutionLogs(String executionId, String nodeName, int offset, int numberResults) {

		String node = "";
		if (!AEMFTCommonUtilsBase.isEmptyString(nodeName)) {
			node = nodeName;
		}
		
		String customQuery	= "SELECT o FROM CRONIOBOLog o "
							+ "WHERE o.message LIKE '"+ executionId +":%'"
							+ "AND o.message LIKE '%"+ node +"%' ";
		List<CRONIOBOLog> logsListJpa = getPersistenceModule().query(customQuery, offset, numberResults);
		//Temporal solution(bug:eclipselink query methods setMaxRows and setFirstResult don't work together)		
		List<CRONIOBOLog> subLogList = getSublist(logsListJpa, numberResults); 
				
		return AEMFTCommonUtilsBase.castList(subLogList);
	}
	
	
	@Override
	public int getTotalExecutionLogs(String executionId, String nodeName) {
		
		String node = "";
		if (!AEMFTCommonUtilsBase.isEmptyString(nodeName)) {
			node = nodeName;
		}
		String customQuery	= "SELECT o FROM CRONIOBOLog o "
							+ "WHERE o.message LIKE '"+ executionId +":%'"
							+ "AND o.message LIKE '%"+ node +"%' ";
		List<CRONIOBOLog> logsListJpa = getPersistenceModule().query(customQuery);
		
		return logsListJpa.size();
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
		getPersistenceModule().setPersitenceUnitName(CRONIOBKPersistenceModuleJPA.MONGO_PERSISTENCE_UNIT_NAME);
	}

	
	/****************************************************************************
	 *                      PRIVATE FUNCTIONS
	 ****************************************************************************/
	
	private List<CRONIOBOLog> getSublist(List<CRONIOBOLog> logsListJpa, int size) {
		int resultSize = logsListJpa.size();
		List<CRONIOBOLog> subLogList; 
		if (resultSize < size) {
			subLogList = logsListJpa;
		} else { 
			subLogList = logsListJpa.subList(0, size);			
		}
		return subLogList;
	}

}
