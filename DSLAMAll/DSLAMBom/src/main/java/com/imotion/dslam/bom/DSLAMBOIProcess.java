package com.imotion.dslam.bom;

import java.io.Serializable;
import java.util.Date;

public interface DSLAMBOIProcess extends Serializable, DSLAMBOIProcessDataConstants {

	Long getProcessId();

	void setProcessId(Long processId);

	String getProcessName();

	void setProcessName(String processName);

	String getSynchronous();

	void setContent(String content);

	String getContentType();

	void setContentType(String contentType);
	
	Date getSavedTime();

	void setSavedTime(Date savedTime);

	Date getCreationTime();

	void setCreationTime(Date creationTime);

	Long getVersion();

	void setVersion(Long version);

}
