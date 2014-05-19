package com.imotion.transit.bom.data.objectify;

import com.googlecode.objectify.annotation.EntitySubclass;
import com.imotion.transit.bom.TRANSBOIComputedMonthlyTransitData;

@EntitySubclass(index=true, name="ComputedMonthlyTransit")
public class TRANSBOComputedMonthlyTransitDataObjectify extends TRANSBOComputedPartialTransitDataObjectify implements TRANSBOIComputedMonthlyTransitData {

	private static final long serialVersionUID = 7924960343639928557L;

	public TRANSBOComputedMonthlyTransitDataObjectify() {
		super();
	}

}
