package com.imotion.dslam.backend.persistence.service.preferences;

import java.util.Date;

import com.imotion.dslam.backend.persistence.jpa.DSLAMBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.bom.data.DSLAMBOProject;

public class CRONIOBKPreferencesPersistenceServiceJPA extends DSLAMBKPersistenceServiceBaseJPA<CRONIOBOIPreferences, CRONIOBOPreferences, Long> implements CRONIOBKIPreferencesPersistenceService {

	private static final long serialVersionUID = 1004389033515052788L;

	@Override
	public CRONIOBOIPreferences getPreferences(long preferencesId) {
		CRONIOBOIPreferences preferences = getPersistenceModule().get(preferencesId);
		return preferences;
	}
	
	@Override
	public CRONIOBOIPreferences addPreferences(CRONIOBOIPreferences preferences) {
		CRONIOBOIPreferences preferencesJPA = (CRONIOBOIPreferences) preferences;
		preferencesJPA = getPersistenceModule().create(preferencesJPA);
		return preferencesJPA;
	}
	
	@Override
	public CRONIOBOIPreferences updatePreferences(Long preferencesId, CRONIOBOIPreferences updatedPreferences) {
		DSLAMBOProject originalPreferences = getPersistenceModule().get(preferencesId);
		if (originalPreferences != null) {
			originalPreferences.setSavedTime(new Date());
			getPersistenceModule().update(originalPreferences);
		}
		return originalPreferences;
	}
	
	@Override
	public void removePreferences(Long preferencesIdAsLong) {
		getPersistenceModule().remove(preferencesIdAsLong);
	}

	/**
	 * AEMFTIHasPersistenceModule
	 */
	@Override
	public Class<CRONIOBOIPreferences> getPersistenceClass() {
		return CRONIOBOIPreferences.class;
	}

}
