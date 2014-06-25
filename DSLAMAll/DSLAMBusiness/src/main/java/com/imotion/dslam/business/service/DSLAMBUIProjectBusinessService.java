package com.imotion.dslam.business.service;

import com.imotion.dslam.business.service.base.DSLAMBUIBusinessService;



public interface DSLAMBUIProjectBusinessService extends DSLAMBUIBusinessService {

	void addProject();

	void getAllProjects();

	void updateProject();

	void removeProject();
	
	void getCsvNodes();

	void addProcess();

	void getAllProcesses();

	void removeProcess();

	void updateProcess();

}
