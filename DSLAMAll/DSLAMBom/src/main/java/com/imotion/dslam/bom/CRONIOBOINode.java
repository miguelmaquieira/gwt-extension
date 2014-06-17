package com.imotion.dslam.bom;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface CRONIOBOINode extends Serializable, CRONIOBOINodeDataConstants {

	Long getNodeId();

	void setNodeId(Long nodeId);

	String getNodeName();

	void setNodeName(String nodeName);

	String getNodeIp();

	void setNodeIp(String nodeIp);

	int getNodeType();

	void setNodeType(int nodeType);

	List<DSLAMBOIVariable> getVariableList();

	void setVariableList(List<DSLAMBOIVariable> variableList);

	Date getSavedTime();

	void setSavedTime(Date savedTime);

	Date getCreationTime();

	void setCreationTime(Date creationTime);

	Long getVersion();

	void setVersion(Long version);



}
