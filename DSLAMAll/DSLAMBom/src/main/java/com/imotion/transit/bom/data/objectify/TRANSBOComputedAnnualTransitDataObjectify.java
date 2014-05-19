package com.imotion.transit.bom.data.objectify;

import com.googlecode.objectify.annotation.EntitySubclass;
import com.imotion.transit.bom.TRANSBOIComputedAnnualTransitData;

@EntitySubclass(index=true, name="ComputedAnnualTransit")
public class TRANSBOComputedAnnualTransitDataObjectify extends TRANSBOComputedPartialTransitDataObjectify implements TRANSBOIComputedAnnualTransitData {

	private static final long serialVersionUID = 7924960343639928557L;

	public TRANSBOComputedAnnualTransitDataObjectify() {
		super();
	}

}
