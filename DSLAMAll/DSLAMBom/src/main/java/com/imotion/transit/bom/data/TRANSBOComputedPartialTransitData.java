package com.imotion.transit.bom.data;

import com.imotion.transit.bom.TRANSBOIComputedPartialTransitData;

public class TRANSBOComputedPartialTransitData extends TRANSBOComputedTransitData implements TRANSBOIComputedPartialTransitData {

	private static final long serialVersionUID = 1004748262454875063L;

	private 	int		partialIncomingTransit;
	private 	int		partialTotalTransit;
	private 	int		partialPassingTransit;
	
	public TRANSBOComputedPartialTransitData() {}

	@Override
	public void setPartialIncomingTransit(int partialIncomingTransit) {
		this.partialIncomingTransit = partialIncomingTransit;
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
