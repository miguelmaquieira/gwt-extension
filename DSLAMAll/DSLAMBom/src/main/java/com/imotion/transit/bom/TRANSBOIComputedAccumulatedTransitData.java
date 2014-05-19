package com.imotion.transit.bom;

public interface TRANSBOIComputedAccumulatedTransitData extends TRANSBOIComputedTransitData {
	
	void	setAccumulatedIncomingTransit(long accumulatedIncomingTransit);
	long	getAccumulatedIncomingTransit();
	
	void	setAccumulatedTotalTransit(long accumulatedTotalTransit);
	long	getAccumulatedTotalTransit();
	
	void	setAccumulatedPassingTransit(long accumulatedPassingTransit);
	long	getAccumulatedPassingTransit();
	
}
