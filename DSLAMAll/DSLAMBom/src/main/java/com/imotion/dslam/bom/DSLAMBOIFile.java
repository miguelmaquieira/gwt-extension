package com.imotion.dslam.bom;

import java.io.Serializable;
import java.util.Date;

public interface DSLAMBOIFile extends Serializable, DSLAMBOIFileDataConstants {

	Long getFileId();

	void setFileId(Long fileId);

	String getFilename();

	void setFilename(String filename);

	String getContent();

	void setContent(String content);

	int getContentType();

	void setContentType(int contentType);
	
	Date getSavedTime();

	void setSavedTime(Date savedTime);

	Date getCreationTime();

	void setCreationTime(Date creationTime);

	Long getVersion();

	void setVersion(Long version);

}
