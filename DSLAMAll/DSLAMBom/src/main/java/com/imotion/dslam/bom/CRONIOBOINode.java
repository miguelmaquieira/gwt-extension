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

	String getNodeType();

	void setNodeType(String nodeType);

	List<DSLAMBOIVariable> getVariableList();

	void setVariableList(List<DSLAMBOIVariable> variableList);

	Date getSavedTime();

	void setSavedTime(Date savedTime);

	Date getCreationTime();

	void setCreationTime(Date creationTime);
	
	DSLAMBOIProcess getProcess();

	void setProcess(DSLAMBOIProcess process);
	
	CRONIOBOIMachineProperties getMachineProperties();
	
	void setMachineProperties(CRONIOBOIMachineProperties machineProperties);

}
