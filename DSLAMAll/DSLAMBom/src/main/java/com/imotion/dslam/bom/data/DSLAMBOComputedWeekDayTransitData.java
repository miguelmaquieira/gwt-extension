package com.imotion.dslam.bom.data;

import com.imotion.dslam.bom.DSLAMBOIComputedWeekDayTransitData;

public class DSLAMBOComputedWeekDayTransitData extends DSLAMBOComputedTransitData implements DSLAMBOIComputedWeekDayTransitData {

	private static final long serialVersionUID = 1004748262454875063L;

	private 	long	accumulatedEnteringTransit;
	private 	long	accumulatedTotalTransit;
	private 	long	accumulatedPassingTransit;
	
	public DSLAMBOComputedWeekDayTransitData() {}

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
