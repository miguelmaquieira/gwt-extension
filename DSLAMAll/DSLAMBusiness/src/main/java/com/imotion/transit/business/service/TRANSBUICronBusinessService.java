package com.imotion.transit.business.service;



public interface TRANSBUICronBusinessService extends TRANSBUIBusinessService {

	void storeLastData();

	void computeDailyData();
	
}
