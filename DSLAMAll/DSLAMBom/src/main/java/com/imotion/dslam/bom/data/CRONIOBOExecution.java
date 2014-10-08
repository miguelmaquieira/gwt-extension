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
import com.imotion.dslam.bom.CRONIOBOIProject;

@Entity(name="Execution")
public class CRONIOBOExecution implements CRONIOBOIExecution {

	private static final long serialVersionUID = -3324713164638338747L;
	
	private Long 				executionId;
	private CRONIOBOIProject 	project;
	private String				destinationLogs;
	private Date 				creationTime;
	private Long				version; 

	public CRONIOBOExecution() {}

	@Id
	@SequenceGenerator(name = "executionIdGenerator", sequenceName = "executionSeq") //It only takes effect for databases providing identifier generators.
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "executionIdGenerator")	
	@Override
	public Long getExecutionId() {
		return executionId;
	}

	@Override
	public void setExecutionId(Long executionId) {
		this.executionId = executionId;
	}
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, targetEntity=CRONIOBOProject.class)
	@JoinColumn(name=CRONIOBOIProject.PROJECT_ID)
	@Override
	public CRONIOBOIProject getProject() {
		return project;
	}

	@Override
	public void setProject(CRONIOBOIProject project) {
		this.project = project;
	}
	
	@Override
	public String getDestinationLogs() {
		return destinationLogs;
	}

	@Override
	public void setDestinationLogs(String destinationLogs) {
		this.destinationLogs = destinationLogs;
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
