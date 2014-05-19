package com.imotion.dslam.bom;

import java.util.Date;


public interface DSLAMBOIComputedTransitData extends DSLAMBOIComputedTransitDataConstants {
	
	void 	setCounterId(long counterId);
	long	getCounterId();
	
	void	setFrequenzyType(int freqType);
	int		getFrequenzyType();
	
	void	setTimestamp(Date timestamp);
	Date	getTimestamp();
	
}
