package com.imotion.dslam.bom.data.objectify;

import com.googlecode.objectify.annotation.EntitySubclass;
import com.imotion.dslam.bom.DSLAMBOIComputedWeeklyTransitData;

@EntitySubclass(index=true, name="ComputedWeeklyTransit")
public class DSLAMBOComputedWeeklyTransitDataObjectify extends DSLAMBOComputedPartialTransitDataObjectify implements DSLAMBOIComputedWeeklyTransitData {

	private static final long serialVersionUID = 7924960343639928557L;

	public DSLAMBOComputedWeeklyTransitDataObjectify() {
		super();
	}

}
