package com.imotion.dslam.bom;

import java.io.Serializable;

public interface DSLAMBOIVariable extends Serializable, DSLAMBOIVariablesDataConstants {

	String getVariableName();

	void setVariableName(String variableName);

	String getVariableValue();

	void setVariableValue(String variableValue);
	
	int getVariableType();

	void setVariableType(int variableType);

}
