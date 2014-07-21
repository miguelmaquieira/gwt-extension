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

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.bom.DSLAMBOIVariable;

@Entity(name="Process")
public class DSLAMBOProcess implements DSLAMBOIProcess {

	private static final long serialVersionUID = 7636685992356098248L;

	private Long 					processId;
	private String 					processName;
	private boolean 				synchronous;
	private List<Date>				scheduleList;
	private List<DSLAMBOIVariable> 	variableList;
	private List<CRONIOBOINode>		nodeList;
	private Date 					savedTime;
	private Date 					creationTime;
	private DSLAMBOIProject			project;
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

	@ElementCollection(targetClass=DSLAMBOVariable.class)
	@Override
	public List<DSLAMBOIVariable> getVariableList() {
		return variableList;
	}

	@Override
	public void setVariableList(List<DSLAMBOIVariable> variableList) {
		this.variableList = variableList;
	}

	@Override
	public void addVariable(DSLAMBOIVariable variable) {
		if (variableList == null) {
			variableList = new ArrayList<>();
		}
		variableList.add(variable);
	}

	@OneToMany(mappedBy=CRONIOBOINode.NODE_PROCESS, targetEntity=CRONIOBONode.class, cascade ={CascadeType.PERSIST, CascadeType.REMOVE})
	@Override
	public List<CRONIOBOINode> getNodeList() {
		return nodeList;
	}

	@Override
	public void setNodeList(List<CRONIOBOINode> nodeList) {
		this.nodeList = null;
		if (nodeList != null) {
			for (CRONIOBOINode node : nodeList) {
				addNode(node);
			}
		}
	}

	@Override
	public void addNode(CRONIOBOINode node) {
		if (nodeList == null) {
			nodeList = new ArrayList<>();
		}
		nodeList.add(node);
		node.setProcess(this);
	}

	@Override
	public void removeNode(CRONIOBOINode node) {
		if (nodeList != null) {
			nodeList.remove(node);
			node.setProcess(null);
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
	
	@OneToOne(mappedBy = DSLAMBOIProject.PROJECT_PROCESS , targetEntity = DSLAMBOProject.class)
	@Override
	public DSLAMBOIProject getProject() {
		return project;
	}

	@Override
	public void setProject(DSLAMBOIProject project) {
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
