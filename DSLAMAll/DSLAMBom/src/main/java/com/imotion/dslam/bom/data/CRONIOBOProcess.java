package com.imotion.dslam.bom.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.imotion.dslam.bom.CRONIOBOINodeList;
import com.imotion.dslam.bom.CRONIOBOIProcess;
import com.imotion.dslam.bom.CRONIOBOIProject;
import com.imotion.dslam.bom.CRONIOBOIVariable;

@Entity(name="Process")
public class CRONIOBOProcess implements CRONIOBOIProcess {

	private static final long serialVersionUID = -1296222038272480787L;
	
	private Long 						processId;
	private String 						processName;
	private boolean 					synchronous;
	private List<Date>					scheduleList;
	private List<CRONIOBOIVariable> 	variableList;
	private List<CRONIOBOINodeList>		listNodeList;
	private Date 						savedTime;
	private Date 						creationTime;
	private CRONIOBOIProject			project;
	private Long						version; 

	public CRONIOBOProcess() {}

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
	public boolean isSynchronous() {
		return synchronous;
	}

	@Override
	public void setSynchronous(boolean synchronous) {
		this.synchronous = synchronous;
	}


	@ElementCollection
	@Temporal(TemporalType.TIMESTAMP)
	@Override
	public List<Date> getScheduleList() {
		return scheduleList;
	}

	@Override
	public void setScheduleList(List<Date> scheduleList) {
		this.scheduleList = scheduleList;
	}

	@Override
	public void addSchedule(Date schedule) {
		if (scheduleList == null) {
			scheduleList = new ArrayList<>();
		}
		scheduleList.add(schedule);
	}

	@ElementCollection(targetClass=CRONIOBOVariable.class)
	@Override
	public List<CRONIOBOIVariable> getVariableList() {
		return variableList;
	}

	@Override
	public void setVariableList(List<CRONIOBOIVariable> variableList) {
		this.variableList = variableList;
	}

	@Override
	public void addVariable(CRONIOBOIVariable variable) {
		if (variableList == null) {
			variableList = new ArrayList<>();
		}
		variableList.add(variable);
	}

	@OneToMany(mappedBy=CRONIOBOINodeList.NODELIST_PROCESS, targetEntity=CRONIOBONodeList.class, cascade ={CascadeType.PERSIST, CascadeType.REMOVE})
	
	@Override
	public List<CRONIOBOINodeList> getListNodeList() {
		return listNodeList;
	}

	@Override
	public void setListNodeList(List<CRONIOBOINodeList> listNodeList) {
		this.listNodeList = null;
		if (listNodeList != null && listNodeList.size() > 0) {
			for (CRONIOBOINodeList nodeList : listNodeList) {
				addNodeList(nodeList);
			}
		}
	}

	@Override
	public void addNodeList(CRONIOBOINodeList nodeList) {
		if (listNodeList == null) {
			listNodeList = new ArrayList<>();
		}
		listNodeList.add(nodeList);
		nodeList.setProcess(this);
	}

	@Override
	public void removeNodeList(CRONIOBOINodeList nodeList) {
		if (listNodeList != null) {
			listNodeList.remove(nodeList);
			nodeList.setProcess(null);
		}

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
	
	@OneToOne(mappedBy = CRONIOBOIProject.PROJECT_PROCESS , targetEntity = CRONIOBOProject.class)
	@Override
	public CRONIOBOIProject getProject() {
		return project;
	}

	@Override
	public void setProject(CRONIOBOIProject project) {
		this.project = project;
	}

	@Version
	protected Long getVersion() {
		return version;
	}

	protected void setVersion(Long version) {
		this.version = version;
	}

}
