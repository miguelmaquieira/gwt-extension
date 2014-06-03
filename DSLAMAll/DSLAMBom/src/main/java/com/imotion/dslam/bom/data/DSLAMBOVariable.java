package com.imotion.dslam.bom.data;

import javax.persistence.Embeddable;

import com.imotion.dslam.bom.DSLAMBOIVariable;

@Embeddable
public class DSLAMBOVariable implements DSLAMBOIVariable {
	
	private static final long serialVersionUID = -4302199816482965056L;
	
	private String variableName;
	private String 	variableValue;
	private String 	variableType;

	public DSLAMBOVariable() {}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getVariableValue() {
		return variableValue;
	}

	public void setVariableValue(String variableValue) {
		this.variableValue = variableValue;
	}

	public String getVariableType() {
		return variableType;
	}

	public void setVariableType(String variableType) {
		this.variableType = variableType;
	}

}
