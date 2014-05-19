package com.imotion.transit.bom.data.objectify;

import com.googlecode.objectify.annotation.EntitySubclass;
import com.imotion.transit.bom.TRANSBOIComputedWeeklyTransitData;

@EntitySubclass(index=true, name="ComputedWeeklyTransit")
public class TRANSBOComputedWeeklyTransitDataObjectify extends TRANSBOComputedPartialTransitDataObjectify implements TRANSBOIComputedWeeklyTransitData {

	private static final long serialVersionUID = 7924960343639928557L;

	public TRANSBOComputedWeeklyTransitDataObjectify() {
		super();
	}

}
