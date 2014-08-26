package com.imotion.dslam.bom.data;

import java.util.Date;

import javax.persistence.CascadeType;
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

import com.imotion.dslam.bom.CRONIOBOIExecution;
import com.imotion.dslam.bom.DSLAMBOIProject;

@Entity(name="Execution")
public class CRONIOBOExecution implements CRONIOBOIExecution {

	private static final long serialVersionUID = -2150526690424573062L;
	
	private Long 				executionId;
	private DSLAMBOIProject 	project;
	private Date 				savedTime;
	private Date 				creationTime;
	private Long				version; 

	public CRONIOBOExecution() {}

	@Id
	@SequenceGenerator(name = "executionIdGenerator", sequenceName = "ExecutionSeq") //It only takes effect for databases providing identifier generators.
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ExecutionIdGenerator")	
	@Override
	public Long getExecutionId() {
		return executionId;
	}

	@Override
	public void setExecutionId(Long executionId) {
		this.executionId = executionId;
	}
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, targetEntity=DSLAMBOProject.class)
	@JoinColumn(name=DSLAMBOIProject.PROJECT_ID)
	@Override
	public DSLAMBOIProject getProject() {
		return project;
	}

	@Override
	public void setProject(DSLAMBOIProject project) {
		this.project = project;
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
	protected Long getVersion() {
		return version;
	}

	protected void setVersion(Long version) {
		this.version = version;
	}

}
