package com.imotion.dslam.business.service;

import com.imotion.dslam.business.service.base.CRONIOBUIBusinessService;



public interface DSLAMBUIExecuteBusinessService extends CRONIOBUIBusinessService {

	void executeProject();
	
	void addExecution();

	void getAllExecutionsByProjectId();

}
