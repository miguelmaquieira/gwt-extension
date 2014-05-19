package com.imotion.transit.bom.data.objectify;

import com.googlecode.objectify.annotation.EntitySubclass;
import com.googlecode.objectify.annotation.Index;
import com.imotion.transit.bom.TRANSBOIComputedWeekDayTransitData;

@EntitySubclass(index=true, name="ComputedWeekDayTransit")
public class TRANSBOComputedWeekDayTransitDataObjectify extends TRANSBOComputedTransitDataObjectify implements TRANSBOIComputedWeekDayTransitData {

	private static final long serialVersionUID = 1004748262454875063L;

	@Index
	private 	long	accumulatedEnteringTransit;
	@Index
	private 	long	accumulatedTotalTransit;
	@Index
	private 	long	accumulatedPassingTransit;
	
	public TRANSBOComputedWeekDayTransitDataObjectify() {}

	@Override
	public void setAccumulatedIncomingTransit(long accumulatedEnteringTransit) {
		this.accumulatedEnteringTransit = accumulatedEnteringTransit;
	}

	@Override
	public long getAccumulatedIncomingTransit() {
		return accumulatedEnteringTransit;
	}

	@Override
	public void setAccumulatedTotalTransit(long accumulatedTotalTransit) {
		this.accumulatedTotalTransit = accumulatedTotalTransit;
	}

	@Override
	public long getAccumulatedTotalTransit() {
		return accumulatedTotalTransit;
	}

	@Override
	public void setAccumulatedPassingTransit(long accumulatedPassingTransit) {
		this.accumulatedPassingTransit = accumulatedPassingTransit;
	}

	@Override
	public long getAccumulatedPassingTransit() {
		return accumulatedPassingTransit;
	}

}
