package com.imotion.dslam.bom;

import java.io.Serializable;
import java.util.Date;

public interface DSLAMBOIProject extends Serializable, DSLAMBOIProjectDataConstants {

	Long getProjectId();
	
	void setProjectId(Long projectId);
	
	String getProjectName();
	
	void setProjectName(String projectName);
	
	int getMachineType();

	void setMachineType(int machineType);
	
	DSLAMBOIFile getMainScript();
	
	void setMainScript(DSLAMBOIFile mainScript);
	
	DSLAMBOIFile getRollBackScript();

	void setRollBackScript(DSLAMBOIFile rollBackScript);
	
	DSLAMBOIProcess getProcess();
	
	void setProcess(DSLAMBOIProcess process);
	
	Date getSavedTime();

	void setSavedTime(Date savedTime);

	Date getCreationTime();

	void setCreationTime(Date creationTime);

	Long getVersion();

	void setVersion(Long version);


}
