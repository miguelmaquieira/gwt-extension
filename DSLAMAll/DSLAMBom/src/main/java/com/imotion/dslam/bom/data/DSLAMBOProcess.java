package com.imotion.dslam.bom.data;

import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIProcess;

@Entity(name="Process")
public class DSLAMBOProcess implements DSLAMBOIProcess {
	
	private static final long serialVersionUID = 7636685992356098248L;
	
	private Long 					processId;
	private String 					processName;
	private DSLAMBOIFile            processScript;
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
	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}
	
	@ManyToOne(optional=false, targetEntity=DSLAMBOFile.class) 
    @JoinColumn(name=DSLAMBOFile.FILE_ID, nullable=false, updatable=true)
	public DSLAMBOIFile getProcessScript() {
		return processScript;
	}

	public void setProcessScript(DSLAMBOIFile processScript) {
		this.processScript = processScript;
	}

	public boolean isSynchronous() {
		return synchronous;
	}

	public void setSynchronous(boolean synchronous) {
		this.synchronous = synchronous;
	}

	@ElementCollection
	@Temporal(TemporalType.TIMESTAMP)
//	@CollectionTable(
//	        name="PHONE",
//	        joinColumns=@JoinColumn(name="OWNER_ID")
//	  )
//	@Column(name="PHONE_NUMBER")
	public List<Date> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<Date> scheduleList) {
		this.scheduleList = scheduleList;
	}

//	@Embedded
	@ElementCollection
	public List<DSLAMBOVariable> getVariableList() {
		return variableList;
	}

	public void setVariableList(List<DSLAMBOVariable> variableList) {
		this.variableList = variableList;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getSavedTime() {
		return savedTime;
	}

	public void setSavedTime(Date savedTime) {
		this.savedTime = savedTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@Version
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
