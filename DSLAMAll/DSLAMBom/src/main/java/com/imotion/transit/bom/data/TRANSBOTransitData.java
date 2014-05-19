package com.imotion.transit.bom.data;

import java.util.Date;

import com.imotion.transit.bom.TRANSBOITransitData;

public class TRANSBOTransitData implements TRANSBOITransitData {
	
	private static final long serialVersionUID = 3729631886077317904L;
	
	private Long 	transitDataId;
	private String 	nodeId;
	private Date	timestamp;
	private int	totalTransit;
	private int	incomingTransit;
	private int	passingTransit;
	private Date	timeInit;
	private Date	timeEnd;

	public TRANSBOTransitData() {}

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
	public void setIncomingTransit(int enteringTransit) {
		this.incomingTransit = enteringTransit;
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
