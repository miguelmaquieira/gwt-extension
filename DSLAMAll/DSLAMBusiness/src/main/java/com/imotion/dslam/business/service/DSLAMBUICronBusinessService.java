package com.imotion.dslam.business.service;



public interface DSLAMBUICronBusinessService extends DSLAMBUIBusinessService {

	void storeLastData();

	void computeDailyData();
	
}
