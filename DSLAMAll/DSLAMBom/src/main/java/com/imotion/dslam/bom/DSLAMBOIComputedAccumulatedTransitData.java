package com.imotion.dslam.bom;

public interface DSLAMBOIComputedAccumulatedTransitData extends DSLAMBOIComputedTransitData {
	
	void	setAccumulatedIncomingTransit(long accumulatedIncomingTransit);
	long	getAccumulatedIncomingTransit();
	
	void	setAccumulatedTotalTransit(long accumulatedTotalTransit);
	long	getAccumulatedTotalTransit();
	
	void	setAccumulatedPassingTransit(long accumulatedPassingTransit);
	long	getAccumulatedPassingTransit();
	
}
