package com.imotion.dslam.bom.data;

import java.util.Date;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.imotion.dslam.bom.DSLAMBOIProcess;

@Entity(name="Process")
public class DSLAMBOProcess implements DSLAMBOIProcess {
	
	private static final long serialVersionUID = 7636685992356098248L;
	
	private Long 					processId;
	private String 					processName;
	private boolean 				synchronous;
	private List<Date>				scheduleList;
	private List<DSLAMBOVariable> 	variableList;
	private Date 					savedTime;
	private Date 					creationTime;
	private Long					version; 

	public DSLAMBOProcess() {}

	@Id
	@SequenceGenerator(name = "ProcessIdGenerator", sequenceName = "ProcessSeq") //It only takes effect for databases providing identifier generators.
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ProcessIdGenerator")	
	@Override
	public Long getProcessId() {
		return processId;
	}

	@Override
	public void setProcessId(Long processId) {
		this.processId = processId;
	}

	@Override
	public String getProcessName() {
		return processName;
	}

	@Override
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	
	@Override
	public boolean getSynchronous() {
		return synchronous;
	}

	@Override
	public void setSynchronous(boolean synchronous) {
		this.synchronous = synchronous;
		
	}
	
	@Embedded
	@Override
	public List<Date> getScheduleList() {
		return scheduleList;
	}

	@Override
	public void setScheduleList(List<Date> scheduleList) {
		this.scheduleList = scheduleList;
		
	}

	@Override
	public List<DSLAMBOVariable> getVariableList() {
		return variableList;
	}

	@Override
	public void setVariableList(List<DSLAMBOVariable> variableList) {
		this.variableList = variableList;	
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Override
	public Date getSavedTime() {
		return savedTime;
	}

	@Override
	public void setSavedTime(Date savedTime) {
		this.savedTime = savedTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Override
	public Date getCreationTime() {
		return creationTime;
	}

	@Override
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
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
