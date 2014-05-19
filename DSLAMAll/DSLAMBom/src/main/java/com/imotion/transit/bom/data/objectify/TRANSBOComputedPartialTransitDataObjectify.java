package com.imotion.transit.bom.data.objectify;

import com.googlecode.objectify.annotation.Index;
import com.imotion.transit.bom.TRANSBOIComputedPartialTransitData;

public class TRANSBOComputedPartialTransitDataObjectify extends TRANSBOComputedTransitDataObjectify implements TRANSBOIComputedPartialTransitData {

	private static final long serialVersionUID = 1004748262454875063L;

	@Index
	private 	int		partialIncomingTransit;
	@Index
	private 	int		partialTotalTransit;
	@Index
	private 	int		partialPassingTransit;
	
	public TRANSBOComputedPartialTransitDataObjectify() {}

	@Override
	public void setPartialIncomingTransit(int partialEnteringTransit) {
		this.partialIncomingTransit = partialEnteringTransit;
	}

	@Override
	public int getPartialIncomingTransit() {
		return partialIncomingTransit;
	}

	@Override
	public void setPartialTotalTransit(int partialTotalTransit) {
		this.partialTotalTransit = partialTotalTransit;
	}

	@Override
	public int getPartialTotalTransit() {
		return partialTotalTransit;
	}

	@Override
	public void setPartialPassingTransit(int partialPassingTransit) {
		this.partialPassingTransit = partialPassingTransit;
	}

	@Override
	public int getPartialPassingTransit() {
		return partialPassingTransit;
	}

}
