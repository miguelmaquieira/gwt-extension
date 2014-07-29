package com.imotion.dslam.backend.persistence.service.userpreferences;

import com.imotion.dslam.bom.CRONIOBOIUserPreferences;


public interface CRONIOBKIUserPreferencesPersistenceService {

	CRONIOBOIUserPreferences addUserPreferences(CRONIOBOIUserPreferences userPreferences, Long preferencesId);

	CRONIOBOIUserPreferences updateUserPreferences(Long userPreferencesId, CRONIOBOIUserPreferences userPreferences);
	
	CRONIOBOIUserPreferences getUserPreferences(Long preferencesId, String userPreferencesId);

}
