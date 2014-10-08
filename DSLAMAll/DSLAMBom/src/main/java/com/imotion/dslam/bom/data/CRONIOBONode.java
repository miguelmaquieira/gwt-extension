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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.CRONIOBOINodeList;
import com.imotion.dslam.bom.CRONIOBOIVariable;

@Entity(name="Node")
public class CRONIOBONode implements CRONIOBOINode {
	
	private static final long serialVersionUID = 4048739124870495341L;
	
	private Long 							nodeId;
	private String 							nodeName;
	private String 							nodeIp;
	private String 							nodeType;
	private List<CRONIOBOIVariable> 			variableList;
	private CRONIOBOINodeList				nodeList;
	private CRONIOBOIMachineProperties		machineProperties;
	private Date 							savedTime;
	private Date 							creationTime;
	private Long							version;

	public CRONIOBONode() {}
	
	@Id
	@SequenceGenerator(name = "NodeIdGenerator", sequenceName = "NodeSeq") //It only takes effect for databases providing identifier generators.
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "NodeIdGenerator")
	@Override
	public Long getNodeId() {
		return nodeId;
	}

	@Override
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	@Override
	public String getNodeName() {
		return nodeName;
	}

	@Override
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	@Override
	public String getNodeIp() {
		return nodeIp;
	}

	@Override
	public void setNodeIp(String nodeIp) {
		this.nodeIp = nodeIp;
	}

	@Override
	public String getNodeType() {
		return nodeType;
	}

	@Override
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
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

	@ManyToOne(targetEntity=CRONIOBONodeList.class)
	@JoinColumn(name=CRONIOBOINodeList.NODELIST_ID)
	@Override
	public CRONIOBOINodeList getNodeList() {
		return nodeList;
	}

	@Override
	public void setNodeList(CRONIOBOINodeList nodeList) {
		this.nodeList = nodeList;
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
	
	@OneToOne(targetEntity = CRONIOBOMachineProperties.class)
	@JoinColumn(name=MACHINE_PROPERTIES_ID)
	@Override
	public CRONIOBOIMachineProperties getMachineProperties() {
		return machineProperties;
	}

	@Override
	public void setMachineProperties(CRONIOBOIMachineProperties machineProperties) {
		this.machineProperties = machineProperties;
	}

	@Version
	protected Long getVersion() {
		return version;
	}

	protected void setVersion(Long version) {
		this.version = version;
	}
	
}
