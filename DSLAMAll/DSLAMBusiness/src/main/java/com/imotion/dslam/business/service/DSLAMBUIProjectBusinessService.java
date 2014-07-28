package com.imotion.dslam.business.service;

import com.imotion.dslam.business.service.base.DSLAMBUIBusinessService;



public interface DSLAMBUIProjectBusinessService extends DSLAMBUIBusinessService {

	void addProject();

	void getAllProjectsByUser();

	void updateProject();
	
	void updateProjects();

	void removeProject();
	
	void getCsvNodes();

}
