package com.imotion.transit.bom.data.objectify;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.imotion.transit.bom.TRANSBOITransitData;

@Entity(name="TransitData")
public class TRANSBOTransitDataObjectify implements TRANSBOITransitData {

	private static final long serialVersionUID = 2264994446680706875L;
	
	@Id
	private Long 	transitDataId;
	@Index
	private String 	nodeId;
	@Index
	private Date	timestamp;
	@Index
	private int	totalTransit;
	@Index
	private int	incomingTransit;
	@Index
	private int	passingTransit;
	@Index
	private Date	timeInit;
	@Index
	private Date	timeEnd;

	public TRANSBOTransitDataObjectify() {}

	@Override
	public Long getTransitDataId() {
		return transitDataId;
	}

	@Override
	public void setTransitDataId(Long nodeDataId) {
		this.transitDataId = nodeDataId;
	}

	@Override
	public String getNodeId() {
		return nodeId;
	}

	@Override
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	@Override
	public Date getTimestamp() {
		return timestamp;
	}

	@Override
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int getTotalTransit() {
		return totalTransit;
	}

	@Override
	public void setTotalTransit(int totalTransit) {
		this.totalTransit = totalTransit;
	}

	@Override
	public int getIncomingTransit() {
		return incomingTransit;
	}

	@Override
	public void setIncomingTransit(int incomingTransit) {
		this.incomingTransit = incomingTransit;
	}

	@Override
	public int getPassingTransit() {
		return passingTransit;
	}

	@Override
	public void setPassingTransit(int passingTransit) {
		this.passingTransit = passingTransit;
	}

	@Override
	public Date getTimeInit() {
		return timeInit;
	}

	@Override
	public void setTimeInit(Date timeInit) {
		this.timeInit = timeInit;
	}

	@Override
	public Date getTimeEnd() {
		return timeEnd;
	}

	@Override
	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

}
