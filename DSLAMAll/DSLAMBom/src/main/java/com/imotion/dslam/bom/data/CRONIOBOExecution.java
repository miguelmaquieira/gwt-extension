package com.imotion.dslam.bom.data;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.imotion.dslam.bom.CRONIOBOIExecution;
import com.imotion.dslam.bom.CRONIOBOILogNode;
import com.imotion.dslam.bom.CRONIOBOIProject;
import com.imotion.dslam.bom.CRONIOBOIUser;

@Entity(name="Execution")
public class CRONIOBOExecution implements CRONIOBOIExecution {

	private static final long serialVersionUID = -3324713164638338747L;
	
	private Long 							executionId;
	private CRONIOBOIProject 				project;
	private CRONIOBOIUser					user;
	private boolean						isSynchronous;
	private List<CRONIOBOILogNode>			logNodes;
	private String							destinationLogs;
	private String							environmentName;
	private Date 							creationTime;
	private Date							finishExecutionTime;
	
	private Long							version; 

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
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, targetEntity=CRONIOBOUser.class)
	@JoinColumn(name=CRONIOBOIUser.USER_ID)
	public CRONIOBOIUser getUser() {
		return user;
	}

	public void setUser(CRONIOBOIUser user) {
		this.user = user;
	}
	
	@Override
	public String getDestinationLogs() {
		return destinationLogs;
	}

	@Override
	public void setDestinationLogs(String destinationLogs) {
		this.destinationLogs = destinationLogs;
	}
	
	@Override
	public boolean getIsSynchronous() {
		return isSynchronous;
	}

	@Override
	public void setIsSynchronous(boolean isSynchronous) {
		this.isSynchronous = isSynchronous;
	}
	
	@Override
	public String getEnvironmentName() {
		return environmentName;
	}

	@Override
	public void setEnvironmentName(String environmentName) {
		this.environmentName = environmentName;
	}
	
	@ElementCollection(targetClass=CRONIOBOLogNode.class)
	@Override
	public List<CRONIOBOILogNode> getLogNodes() {
		return logNodes;
	}

	@Override
	public void setLogNodes(List<CRONIOBOILogNode> logNodes) {
		this.logNodes = logNodes;
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Override
	public Date getFinishExecutionTime() {
		return finishExecutionTime;
	}

	@Override
	public void setFinishExecutionTime(Date finishExecutionTime) {
		this.finishExecutionTime = finishExecutionTime;
	}

	@Version
	protected Long getVersion() {
		return version;
	}

	protected void setVersion(Long version) {
		this.version = version;
	}

}
