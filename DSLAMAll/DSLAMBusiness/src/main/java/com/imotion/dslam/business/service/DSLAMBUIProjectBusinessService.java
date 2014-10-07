package com.imotion.dslam.business.service;

import com.imotion.dslam.business.service.base.CRONIOBUIBusinessService;



public interface DSLAMBUIProjectBusinessService extends CRONIOBUIBusinessService {

	void addProject();

	void getAllProjectsByUser();

	void updateProject();
	
	void updateProjects();

	void removeProject();
	
	void getCsvNodes();
	
	void addNodeList();
	
	void getAllNodeListsByProjectId();

}
