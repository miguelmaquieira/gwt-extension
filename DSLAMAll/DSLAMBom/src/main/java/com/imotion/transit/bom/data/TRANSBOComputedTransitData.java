package com.imotion.transit.bom.data;

import java.util.Date;

import com.imotion.transit.bom.TRANSBOIComputedTransitData;

public class TRANSBOComputedTransitData implements TRANSBOIComputedTransitData {

	private static final long serialVersionUID = 6341219317647550426L;
	
	private Long	counterId;
	private int	frequenzyType;
	private Date	timestamp;

	public TRANSBOComputedTransitData() {}

	@Override
	public void setCounterId(long counterId) {
		this.counterId = counterId;
	}

	@Override
	public long getCounterId() {
		return this.counterId;
	}

	@Override
	public void setFrequenzyType(int freqType) {
		this.frequenzyType = freqType;
	}

	@Override
	public int getFrequenzyType() {
		return frequenzyType;
	}

	@Override
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public Date getTimestamp() {
		return timestamp;
	}

}
