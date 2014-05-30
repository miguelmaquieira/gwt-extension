package com.imotion.dslam.bom;

import java.io.Serializable;

public interface DSLAMBOIVariable extends Serializable, DSLAMBOIVariablesDataConstants {

	Long getVariableId();

	void setVariableId(Long variableId);

	String getVariableName();

	void setVariableName(String variableName);

	String getVariableValue();

	void setVariableValue(String variableValue);

	Long getVersion();

	void setVersion(Long version);

}
