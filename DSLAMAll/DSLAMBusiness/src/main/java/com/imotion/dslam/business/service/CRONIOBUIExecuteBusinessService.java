package com.imotion.dslam.business.service;

import com.imotion.dslam.business.service.base.CRONIOBUIBusinessService;



public interface CRONIOBUIExecuteBusinessService extends CRONIOBUIBusinessService {

	void executeProject();
	
	void addExecution();
	
	void getExecution();

	void getAllExecutionsByProjectId();

}
