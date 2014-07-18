package com.imotion.dslam.bom;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface DSLAMBOIProcess extends Serializable, DSLAMBOIProcessDataConstants {

	Long getProcessId();

	void setProcessId(Long processId);

	String getProcessName();

	void setProcessName(String processName);

	boolean isSynchronous();

	void setSynchronous(boolean synchronous);

	List<Date> getScheduleList();

	void setScheduleList(List<Date> scheduleList);
	
	void addSchedule(Date schedule);
	
	List<DSLAMBOIVariable> getVariableList();

	void setVariableList(List<DSLAMBOIVariable> variableList);
	
	void addVariable(DSLAMBOIVariable variable);
	
	List<CRONIOBOINode> getNodeList();

	void setNodeList(List<CRONIOBOINode> nodeList);
	
	void addNode(CRONIOBOINode node);
	
	void removeNode(CRONIOBOINode node);
	
	Date getSavedTime();

	void setSavedTime(Date savedTime);

	Date getCreationTime();

	void setCreationTime(Date creationTime);

	DSLAMBOIProject getProject();

	void setProject(DSLAMBOIProject project);

}
