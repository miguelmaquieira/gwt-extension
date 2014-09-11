package com.imotion.dslam.backend.persistence.log;

import java.util.List;

import com.imotion.dslam.bom.CRONIOBOILog;
import com.imotion.dslam.bom.data.CRONIOBOLogFilter;

public interface CRONIOBKILogPersistenceService {
	
	public List<CRONIOBOILog> getLogsByfilter(CRONIOBOLogFilter filterData);
	public List<CRONIOBOILog> getExecutionLogs(String executionId);

}
