package com.imotion.dslam.bom;

import java.io.Serializable;
import java.util.Date;

public interface CRONIOBOIExecution extends Serializable, CRONIOBOIExecutionDataConstants {

	Long getExecutionId();
	
	void setExecutionId(Long executionId);
	
	DSLAMBOIProject getProject();
	
	void setProject(DSLAMBOIProject project);
	
	String getDestinationLogs();
	
	void setDestinationLogs(String destinationLogs);

	Date getCreationTime();

	void setCreationTime(Date creationTime);

}
