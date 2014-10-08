package com.imotion.dslam.bom.data;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.imotion.dslam.bom.CRONIOBOIProjectDataConstants;
import com.imotion.dslam.bom.CRONIOBOIFile;
import com.imotion.dslam.bom.CRONIOBOIProcess;
import com.imotion.dslam.bom.CRONIOBOIProject;

@Entity(name="Project")
public class CRONIOBOProject implements CRONIOBOIProject {

	private static final long serialVersionUID = -5386591730111246458L;
	
	private Long 				projectId;
	private String 				projectName;
	private int				machineType;
	private CRONIOBOIFile 		mainScript;
	private CRONIOBOIFile 		rollBackScript;
	private CRONIOBOIProcess		process;
	private Date 				savedTime;
	private Date 				creationTime;
	private Long				version; 

	public CRONIOBOProject() {}

	@Id
	@SequenceGenerator(name = "ProjectIdGenerator", sequenceName = "ProjectSeq") //It only takes effect for databases providing identifier generators.
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ProjectIdGenerator")	
	@Override
	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public int getMachineType() {
		return machineType;
	}

	public void setMachineType(int machineType) {
		this.machineType = machineType;
	}
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, targetEntity=CRONIOBOFile.class)
	@JoinColumn(name=CRONIOBOIProjectDataConstants.MAIN_SCRIPT_ID)
	public CRONIOBOIFile getMainScript() {
		return mainScript;
	}

	public void setMainScript(CRONIOBOIFile mainScript) {
		this.mainScript = mainScript;
	}
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, targetEntity=CRONIOBOFile.class)
	@JoinColumn(name=CRONIOBOIProjectDataConstants.ROLLBACK_SCRIPT_ID)
	public CRONIOBOIFile getRollBackScript() {
		return rollBackScript;
	}

	public void setRollBackScript(CRONIOBOIFile rollBackScript) {
		this.rollBackScript = rollBackScript;
	}
	
	@OneToOne(cascade ={CascadeType.PERSIST, CascadeType.REMOVE}, targetEntity=CRONIOBOProcess.class)
	@JoinColumn(name=CRONIOBOProcess.PROCESS_ID)
	public CRONIOBOIProcess getProcess() {
		return process;
	}

	public void setProcess(CRONIOBOIProcess process) {
		this.process = process;
		this.process.setProject(this);
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
