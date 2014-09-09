package com.imotion.dslam.backend.persistence.log;

import java.util.List;

import com.imotion.dslam.bom.CRONIOBOILog;

public interface CRONIOBKILogPersistenceService {
	
	public List<CRONIOBOILog> getLogsByfilter();
	public List<CRONIOBOILog> getExecutionLogs(String executionId);

}
