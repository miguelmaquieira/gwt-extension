package com.imotion.dslam.bom.data.objectify;

import com.googlecode.objectify.annotation.EntitySubclass;
import com.imotion.dslam.bom.DSLAMBOIComputedAnnualTransitData;

@EntitySubclass(index=true, name="ComputedAnnualTransit")
public class DSLAMBOComputedAnnualTransitDataObjectify extends DSLAMBOComputedPartialTransitDataObjectify implements DSLAMBOIComputedAnnualTransitData {

	private static final long serialVersionUID = 7924960343639928557L;

	public DSLAMBOComputedAnnualTransitDataObjectify() {
		super();
	}

}
