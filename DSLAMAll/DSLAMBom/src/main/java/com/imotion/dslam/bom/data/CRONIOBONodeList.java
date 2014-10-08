package com.imotion.dslam.bom.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.CRONIOBOINodeList;
import com.imotion.dslam.bom.CRONIOBOIProcess;

@Entity(name="NodeList")
public class CRONIOBONodeList implements CRONIOBOINodeList {

	private static final long serialVersionUID = 6443385144140537644L;
	
	private Long 					nodeListId;
	private String 					nodeListName;
	private List<CRONIOBOINode>		nodeList;
	private CRONIOBOIProcess			process;
	private Date 					savedTime;
	private Date 					creationTime;
	private Long					version; 

	public CRONIOBONodeList() {}

	@Id
	@SequenceGenerator(name = "NodeListIdGenerator", sequenceName = "NodeListSeq") //It only takes effect for databases providing identifier generators.
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "NodeListIdGenerator")
	@Override
	public Long getNodeListId() {
		return nodeListId;
	}

	@Override
	public void setNodeListId(Long nodeListId) {
		this.nodeListId = nodeListId;
	}

	@Override
	public String getNodeListName() {
		return nodeListName;
	}

	@Override
	public void setNodeListName(String nodeListName) {
		this.nodeListName = nodeListName;
	}

	@OneToMany(mappedBy=CRONIOBOINode.NODE_NODELIST, targetEntity=CRONIOBONode.class, cascade ={CascadeType.PERSIST, CascadeType.REMOVE})
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
	
	@ManyToOne(targetEntity=CRONIOBOProcess.class)
	@JoinColumn(name=CRONIOBOIProcess.PROCESS_ID)
	@Override
	public CRONIOBOIProcess getProcess() {
		return process;
	}

	@Override
	public void setProcess(CRONIOBOIProcess process) {
		this.process = process;
	}

	@Override
	public void addNode(CRONIOBOINode node) {
		if (nodeList == null) {
			nodeList = new ArrayList<>();
		}
		nodeList.add(node);
		node.setNodeList(this);
	}

	@Override
	public void removeNode(CRONIOBOINode node) {
		if (nodeList != null) {
			nodeList.remove(node);
			node.setNodeList(null);
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

	@Version
	protected Long getVersion() {
		return version;
	}

	protected void setVersion(Long version) {
		this.version = version;
	}

}
