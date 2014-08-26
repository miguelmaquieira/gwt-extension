package com.imotion.dslam.bom;

import java.io.Serializable;
import java.util.Date;

public interface CRONIOBOIExecution extends Serializable, CRONIOBOIExecutionDataConstants {

	Long getExecutionId();
	
	void setExecutionId(Long executionId);
	
	DSLAMBOIProject getProject();
	
	void setProject(DSLAMBOIProject project);
	
	Date getSavedTime();

	void setSavedTime(Date savedTime);

	Date getCreationTime();

	void setCreationTime(Date creationTime);

}
