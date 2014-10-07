package com.imotion.dslam.business.service;

import com.imotion.dslam.business.service.base.CRONIOBUIBusinessService;

public interface CRONIOBUIPreferencesBusinessService extends CRONIOBUIBusinessService {

	void addConnection();
	
	void getPreferences();

	void updatePreferences();

	void removePreferences();
	
	void updateMachineConfig();
	
	void updateUserPreferences();

}
