package com.imotion.dslam.bom;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface CRONIOBOIProcess extends Serializable, CRONIOBOIProcessDataConstants {

	Long getProcessId();

	void setProcessId(Long processId);

	String getProcessName();

	void setProcessName(String processName);

	boolean isSynchronous();

	void setSynchronous(boolean synchronous);

	List<Date> getScheduleList();

	void setScheduleList(List<Date> scheduleList);
	
	void addSchedule(Date schedule);
	
	List<CRONIOBOIVariable> getVariableList();

	void setVariableList(List<CRONIOBOIVariable> variableList);
	
	void addVariable(CRONIOBOIVariable variable);
	
	List<CRONIOBOINodeList> getListNodeList();

	void setListNodeList(List<CRONIOBOINodeList> listNodeList);
	
	void addNodeList(CRONIOBOINodeList nodeList);
	
	void removeNodeList(CRONIOBOINodeList nodeList);
	
	Date getSavedTime();

	void setSavedTime(Date savedTime);

	Date getCreationTime();

	void setCreationTime(Date creationTime);

	CRONIOBOIProject getProject();

	void setProject(CRONIOBOIProject project);

}
