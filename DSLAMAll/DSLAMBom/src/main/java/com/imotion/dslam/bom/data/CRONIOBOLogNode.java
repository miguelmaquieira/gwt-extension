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

import com.imotion.dslam.bom.CRONIOBOILogNode;

@Entity(name="LogNode")
public class CRONIOBOLogNode implements CRONIOBOILogNode {

	private static final long serialVersionUID = -2112611040027754484L;
	
	private Long 							nodeId;
	private String 							nodeName;
	private String 							nodeIp;
	private String 							nodeType;
	private boolean 						state;
	private Date 							creationTime;
	private Long							version;

	public CRONIOBOLogNode() {}
	
	@Id
	@SequenceGenerator(name = "LogNodeIdGenerator", sequenceName = "LogNodeSeq") //It only takes effect for databases providing identifier generators.
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "LogNodeIdGenerator")
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
	
	@Override
	public boolean getState() {
		return state;
	}

	@Override
	public void setState(boolean state) {
		this.state = state;
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
