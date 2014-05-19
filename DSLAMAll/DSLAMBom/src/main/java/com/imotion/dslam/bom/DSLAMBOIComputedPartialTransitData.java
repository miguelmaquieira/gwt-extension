package com.imotion.dslam.bom;

public interface DSLAMBOIComputedPartialTransitData extends DSLAMBOIComputedTransitData {
	
	void	setPartialIncomingTransit(int partialIncomingTransit);
	int		getPartialIncomingTransit();
	
	void	setPartialTotalTransit(int partialTotalTransit);
	int		getPartialTotalTransit();
	
	void	setPartialPassingTransit(int partialPassingTransit);
	int		getPartialPassingTransit();
	
}
