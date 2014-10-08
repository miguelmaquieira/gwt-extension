package com.imotion.dslam.bom;

import java.io.Serializable;
import java.util.Date;

public interface CRONIOBOIExecution extends Serializable, CRONIOBOIExecutionDataConstants {

	Long getExecutionId();
	
	void setExecutionId(Long executionId);
	
	CRONIOBOIProject getProject();
	
	void setProject(CRONIOBOIProject project);
	
	String getDestinationLogs();
	
	void setDestinationLogs(String destinationLogs);

	Date getCreationTime();

	void setCreationTime(Date creationTime);

}
