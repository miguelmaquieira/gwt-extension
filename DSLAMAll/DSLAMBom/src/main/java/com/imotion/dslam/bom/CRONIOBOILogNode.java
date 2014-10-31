package com.imotion.dslam.bom;

import java.io.Serializable;
import java.util.Date;

public interface CRONIOBOILogNode extends Serializable, CRONIOBOILogNodeDataConstants {

	Long getNodeId();

	void setNodeId(Long nodeId);

	String getNodeName();

	void setNodeName(String nodeName);

	String getNodeIp();

	void setNodeIp(String nodeIp);

	String getNodeType();

	void setNodeType(String nodeType);
	
	int getState();

	void setState(int state);

	Date getCreationTime();

	void setCreationTime(Date creationTime);

}
