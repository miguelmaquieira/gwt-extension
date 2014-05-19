package com.imotion.transit.bom;

import java.util.Date;


public interface TRANSBOIComputedTransitData extends TRANSBOIComputedTransitDataConstants {
	
	void 	setCounterId(long counterId);
	long	getCounterId();
	
	void	setFrequenzyType(int freqType);
	int		getFrequenzyType();
	
	void	setTimestamp(Date timestamp);
	Date	getTimestamp();
	
}
