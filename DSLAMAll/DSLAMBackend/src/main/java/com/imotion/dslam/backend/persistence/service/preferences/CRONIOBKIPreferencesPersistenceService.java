package com.imotion.dslam.backend.persistence.service.preferences;

import com.imotion.dslam.bom.CRONIOBOIPreferences;


public interface CRONIOBKIPreferencesPersistenceService {
	
	CRONIOBOIPreferences getPreferences(long preferencesId);
	
	CRONIOBOIPreferences addPreferences(CRONIOBOIPreferences preferences);
	
	CRONIOBOIPreferences updatePreferences(Long preferencesId, CRONIOBOIPreferences updatedPreferences);

	void removePreferences(Long preferencesIdAsLong);

}
