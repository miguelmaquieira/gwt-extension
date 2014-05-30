package com.imotion.dslam.bom;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.imotion.dslam.bom.data.DSLAMBOVariable;

public interface DSLAMBOIProcess extends Serializable, DSLAMBOIProcessDataConstants {

	Long getProcessId();

	void setProcessId(Long processId);

	String getProcessName();

	void setProcessName(String processName);

	boolean getSynchronous();

	void setSynchronous(boolean synchronous);

	List<Date> getScheduleList();

	void setScheduleList(List<Date> scheduleList);
	
	List<DSLAMBOVariable> getVariableList();

	void setVariableList(List<DSLAMBOVariable> variableList);
	
	Date getSavedTime();

	void setSavedTime(Date savedTime);

	Date getCreationTime();

	void setCreationTime(Date creationTime);

	Long getVersion();

	void setVersion(Long version);

}
