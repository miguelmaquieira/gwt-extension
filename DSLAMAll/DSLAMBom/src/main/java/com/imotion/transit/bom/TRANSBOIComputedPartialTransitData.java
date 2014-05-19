package com.imotion.transit.bom;

public interface TRANSBOIComputedPartialTransitData extends TRANSBOIComputedTransitData {
	
	void	setPartialIncomingTransit(int partialIncomingTransit);
	int		getPartialIncomingTransit();
	
	void	setPartialTotalTransit(int partialTotalTransit);
	int		getPartialTotalTransit();
	
	void	setPartialPassingTransit(int partialPassingTransit);
	int		getPartialPassingTransit();
	
}
