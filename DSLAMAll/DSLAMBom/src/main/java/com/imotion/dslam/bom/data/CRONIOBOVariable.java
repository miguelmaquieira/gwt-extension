package com.imotion.dslam.bom.data;

import javax.persistence.Embeddable;

import com.imotion.dslam.bom.CRONIOBOIVariable;

@Embeddable
public class CRONIOBOVariable implements CRONIOBOIVariable {
	
	private static final long serialVersionUID = -4302199816482965056L;
	
	private String variableName;
	private String 	variableValue;
	private int 	variableScope;
	private int	variableType;

	public CRONIOBOVariable() {}

	@Override
	public String getVariableName() {
		return variableName;
	}

	@Override
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	@Override
	public String getVariableValue() {
		return variableValue;
	}

	@Override
	public void setVariableValue(String variableValue) {
		this.variableValue = variableValue;
	}

	@Override
	public int getVariableScope() {
		return variableScope;
	}

	@Override
	public void setVariableScope(int variableScope) {
		this.variableScope = variableScope;
	}

	@Override
	public int getVariableType() {
		return variableType;
	}

	@Override
	public void setVariableType(int variableType) {
		this.variableType = variableType;
	}

}
