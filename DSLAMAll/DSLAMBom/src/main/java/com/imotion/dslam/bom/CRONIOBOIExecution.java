package com.imotion.dslam.bom;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface CRONIOBOIExecution extends Serializable, CRONIOBOIExecutionDataConstants {

	Long getExecutionId();
	
	void setExecutionId(Long executionId);
	
	CRONIOBOIProject getProject();
	
	void setProject(CRONIOBOIProject project);
	
	CRONIOBOIUser getUser();

	void setUser(CRONIOBOIUser user);
	
	String getDestinationLogs();
	
	void setDestinationLogs(String destinationLogs);
	
	boolean getIsSynchronous();
	
	void setIsSynchronous(boolean isSynchronous);
	
	String getEnvironmentName();
	
	void setEnvironmentName(String environmentName);
	
	void setLogNodes(List<CRONIOBOILogNode> logNodes);
	
	List<CRONIOBOILogNode> getLogNodes();

	Date getCreationTime();

	void setCreationTime(Date creationTime);
	
	Date getFinishExecutionTime();

	void setFinishExecutionTime(Date finishExecution);

}
