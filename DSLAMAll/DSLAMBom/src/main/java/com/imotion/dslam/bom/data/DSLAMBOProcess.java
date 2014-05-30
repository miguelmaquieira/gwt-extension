package com.imotion.dslam.bom.data;

import java.util.Date;

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

	private static final long serialVersionUID = 4235149869033046131L;
	
	@Id
	@SequenceGenerator(name = "ProcessIdGenerator", sequenceName = "ProcessSeq") //It only takes effect for databases providing identifier generators.
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ProcessIdGenerator")
	private Long 			processId;
	private String 			processName;
	private boolean 		synchronous;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date 		savedTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date 		creationTime;
	@Version
	private 	Long	version; 

	public DSLAMBOProcess() {}

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

//	@Override
//	public String getContent() {
//		return content;
//	}
//
//	@Override
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	@Override
//	public String getContentType() {
//		return contentType;
//	}

//	@Override
//	public void setContentType(String contentType) {
//		this.contentType = contentType;
//	}

	@Override
	public Date getSavedTime() {
		return savedTime;
	}

	@Override
	public void setSavedTime(Date savedTime) {
		this.savedTime = savedTime;
	}

	@Override
	public Date getCreationTime() {
		return creationTime;
	}

	@Override
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public Long getVersion() {
		return version;
	}

	@Override
	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public String getSynchronous() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setContent(String content) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setContentType(String contentType) {
		// TODO Auto-generated method stub
		
	}

}
