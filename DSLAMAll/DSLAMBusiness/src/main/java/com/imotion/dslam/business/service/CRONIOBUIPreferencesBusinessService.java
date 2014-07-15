package com.imotion.dslam.business.service;

import com.imotion.dslam.business.service.base.DSLAMBUIBusinessService;

public interface CRONIOBUIPreferencesBusinessService extends DSLAMBUIBusinessService {

	void addConnection();
	
	void getPreferences();

	void updatePreferences();

	void removePreferences();
	
	void updateMachineConfig();

}
