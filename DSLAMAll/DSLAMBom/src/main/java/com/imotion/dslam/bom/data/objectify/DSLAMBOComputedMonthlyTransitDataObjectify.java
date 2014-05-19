package com.imotion.dslam.bom.data.objectify;

import com.googlecode.objectify.annotation.EntitySubclass;
import com.imotion.dslam.bom.DSLAMBOIComputedMonthlyTransitData;

@EntitySubclass(index=true, name="ComputedMonthlyTransit")
public class DSLAMBOComputedMonthlyTransitDataObjectify extends DSLAMBOComputedPartialTransitDataObjectify implements DSLAMBOIComputedMonthlyTransitData {

	private static final long serialVersionUID = 7924960343639928557L;

	public DSLAMBOComputedMonthlyTransitDataObjectify() {
		super();
	}

}
