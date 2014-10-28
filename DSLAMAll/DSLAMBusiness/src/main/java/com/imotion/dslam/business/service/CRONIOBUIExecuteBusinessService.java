package com.imotion.dslam.business.service;

import com.imotion.dslam.business.service.base.CRONIOBUIBusinessService;



public interface CRONIOBUIExecuteBusinessService extends CRONIOBUIBusinessService {

	void executeProject();
	
	void updateFinishExecution();
	
	void addExecution();

	void getAllExecutionsByProjectId();

}
