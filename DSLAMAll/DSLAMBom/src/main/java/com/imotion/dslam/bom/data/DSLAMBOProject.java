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

import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIProject;

@Entity(name="Project")
public class DSLAMBOProject implements DSLAMBOIProject {

	
	
	private Long 				projectId;
	private String 				projectName;
	private int				machineType;
	private DSLAMBOIFile 		mainScript;
	private DSLAMBOIFile 		rollBackScript;
	private DSLAMBOIProcess		process;
	private Date 				savedTime;
	private Date 				creationTime;
	private Long				version; 

	public DSLAMBOProject() {}

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
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name=DSLAMBOFile.FILE_ID, nullable=false, updatable=true)
	public DSLAMBOIFile getMainScript() {
		return mainScript;
	}

	public void setMainScript(DSLAMBOIFile mainScript) {
		this.mainScript = mainScript;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name=DSLAMBOFile.FILE_ID, nullable=true, updatable=true)
	public DSLAMBOIFile getRollBackScript() {
		return rollBackScript;
	}

	public void setRollBackScript(DSLAMBOIFile rollBackScript) {
		this.rollBackScript = rollBackScript;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name=DSLAMBOProcess.PROCESS_ID, nullable=false, updatable=true)
	public DSLAMBOIProcess getProcess() {
		return process;
	}

	public void setProcess(DSLAMBOIProcess process) {
		this.process = process;
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
