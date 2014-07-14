package com.imotion.dslam.backend.persistence.service.preferences;

import javax.persistence.EntityTransaction;

import com.imotion.dslam.backend.persistence.jpa.DSLAMBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.bom.data.CRONIOBOPreferences;

public class CRONIOBKPreferencesPersistenceServiceJPA extends DSLAMBKPersistenceServiceBaseJPA<CRONIOBOIPreferences, CRONIOBOPreferences, Long> implements CRONIOBKIPreferencesPersistenceService {

	private static final long serialVersionUID = 1004389033515052788L;

	@Override
	public CRONIOBOIPreferences getPreferences(long preferencesId) {
		CRONIOBOIPreferences preferences = getPersistenceModule().get(preferencesId);
		return preferences;
	}
	
	@Override
	public CRONIOBOIPreferences addPreferences(CRONIOBOIPreferences preferences) {
		CRONIOBOPreferences preferencesJPA = (CRONIOBOPreferences) preferences;
		preferencesJPA = getPersistenceModule().create(preferencesJPA);
		return preferencesJPA;
	}
	
	@Override
	public CRONIOBOIPreferences updatePreferences(Long preferencesId, CRONIOBOIPreferences updatedPreferences) {
		CRONIOBOPreferences originalPreferences  = getPersistenceModule().get(preferencesId);
		if (originalPreferences != null) {
			EntityTransaction tx = getPersistenceModule().beginTransaction();
			originalPreferences.setSavedTime(updatedPreferences.getSavedTime());
			getPersistenceModule().commit(tx);
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
	public Class<CRONIOBOPreferences> getPersistenceClass() {
		return CRONIOBOPreferences.class;
	}

}
