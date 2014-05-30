package com.imotion.dslam.bom.data;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Version;

import com.imotion.dslam.bom.DSLAMBOIVariable;

@Entity(name="Variable")
@Embeddable
public class DSLAMBOVariable implements DSLAMBOIVariable {
	
	private static final long serialVersionUID = -4302199816482965056L;
	
	private String variableName;
	private String 	variableValue;
	private Long	version; 

	public DSLAMBOVariable() {}

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

	@Version
	@Override
	public Long getVersion() {
		return version;
	}

	@Override
	public void setVersion(Long version) {
		this.version = version;
	}

}
