package com.imotion.transit.bom.data.objectify;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.imotion.transit.bom.TRANSBOIComputedTransitData;

@Entity(name="ComputedTransit")
public class TRANSBOComputedTransitDataObjectify implements TRANSBOIComputedTransitData {

	private static final long serialVersionUID = 6341219317647550426L;
	
	@Id
	private Long	counterId;
	@Index
	private int	frequenzyType;
	@Index
	private Date	timestamp;

	public TRANSBOComputedTransitDataObjectify() {}

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
