package com.imotion.dslam.bom;

import java.io.Serializable;
import java.util.Date;

public interface CRONIOBOIProject extends Serializable, CRONIOBOIProjectDataConstants {

	Long getProjectId();
	
	void setProjectId(Long projectId);
	
	String getProjectName();
	
	void setProjectName(String projectName);
	
	int getMachineType();

	void setMachineType(int machineType);
	
	CRONIOBOIFile getMainScript();
	
	void setMainScript(CRONIOBOIFile mainScript);
	
	CRONIOBOIFile getRollBackScript();

	void setRollBackScript(CRONIOBOIFile rollBackScript);
	
	CRONIOBOIProcess getProcess();
	
	void setProcess(CRONIOBOIProcess process);
	
	Date getSavedTime();

	void setSavedTime(Date savedTime);

	Date getCreationTime();

	void setCreationTime(Date creationTime);

}
