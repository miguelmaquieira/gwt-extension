package com.imotion.dslam.bom;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface CRONIOBOINodeList extends Serializable, CRONIOBOINodeListDataConstants {

	Long getNodeListId();

	void setNodeListId(Long nodeListId);

	String getNodeListName();

	void setNodeListName(String nodeListName);

	List<CRONIOBOINode> getNodeList();

	void setNodeList(List<CRONIOBOINode> nodeList);
	
	void addNode(CRONIOBOINode node);
	
	void removeNode(CRONIOBOINode node);
	
	CRONIOBOIProcess getProcess();

	void setProcess(CRONIOBOIProcess process);
	
	Date getSavedTime();

	void setSavedTime(Date savedTime);

	Date getCreationTime();

	void setCreationTime(Date creationTime);

}
