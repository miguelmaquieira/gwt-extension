package com.imotion.dslam.bom;

import java.io.Serializable;
import java.util.Date;

public interface CRONIOBOIFile extends Serializable, CRONIOBOIFileDataConstants {

	Long getFileId();

	void setFileId(Long fileId);

	String getFilename();

	void setFilename(String filename);

	String getContent();

	void setContent(String content);
	
	String getCompiledContent();

	void setCompiledContent(String compileContent);

	int getContentType();

	void setContentType(int contentType);
	
	Date getSavedTime();

	void setSavedTime(Date savedTime);

	Date getCreationTime();

	void setCreationTime(Date creationTime);

}
