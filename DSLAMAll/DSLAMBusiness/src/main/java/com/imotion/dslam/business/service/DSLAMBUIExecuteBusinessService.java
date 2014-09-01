package com.imotion.dslam.business.service;

import com.imotion.dslam.business.service.base.DSLAMBUIBusinessService;



public interface DSLAMBUIExecuteBusinessService extends DSLAMBUIBusinessService {

	void executeProject();
	
	void addExecution();

	void getAllExecutionsByProjectId();

}
