package com.imotion.dslam.bom.data;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

@Embeddable
@NoSql(dataFormat=DataFormatType.MAPPED) 
public class CRONIOLoggerData implements Serializable{ 

	private static final long serialVersionUID = -5597314122463993102L;

	String fullyQualifiedClassName; 
	@Field(name = "package")
	String[] packageInfo; 
	String className;
		
	public CRONIOLoggerData() {
	
	}

	public String getFullyQualifiedClassName() {
		return fullyQualifiedClassName;
	}

	public void setFullyQualifiedClassName(String fullyQualifiedClassName) {
		this.fullyQualifiedClassName = fullyQualifiedClassName;
	}

	public String[] getPackageInfo() {
		return packageInfo;
	}

	public void setPackageInfo(String[] packageInfo) {
		this.packageInfo = packageInfo;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	} 
	

} 
