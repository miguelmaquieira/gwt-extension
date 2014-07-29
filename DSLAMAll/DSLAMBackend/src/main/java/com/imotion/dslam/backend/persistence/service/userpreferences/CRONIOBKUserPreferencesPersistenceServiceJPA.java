package com.imotion.dslam.backend.persistence.service.userpreferences;

import java.util.Date;
import java.util.List;

import com.imotion.dslam.backend.persistence.jpa.DSLAMBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.bom.CRONIOBOIUserPreferences;
import com.imotion.dslam.bom.data.CRONIOBOUserPreferences;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.back.persistence.AEMFTPersistenceParamsContainer;

public class CRONIOBKUserPreferencesPersistenceServiceJPA extends DSLAMBKPersistenceServiceBaseJPA<CRONIOBOIUserPreferences, CRONIOBOUserPreferences, Long> implements CRONIOBKIUserPreferencesPersistenceService {

	private static final long serialVersionUID = 4255979832912378431L;

	@Override
	public CRONIOBOIUserPreferences addUserPreferences(CRONIOBOIUserPreferences userPreferences, Long preferencesId) {
		Date						currentDate				= new Date();
		CRONIOBOIPreferences		preferences				= getPreferencesPersistence().getPreferences(preferencesId);
		
		CRONIOBOUserPreferences	userPreferencesJPA	= (CRONIOBOUserPreferences) userPreferences;
		userPreferencesJPA.setPreferences(preferences);
		userPreferencesJPA.setSavedTime(currentDate);
		userPreferencesJPA.setCreationTime(currentDate);
		userPreferencesJPA = getPersistenceModule().create(userPreferencesJPA);
		
		preferences.setUserPreferences(userPreferencesJPA);
		preferences.setSavedTime(currentDate);
		preferences = getPreferencesPersistence().updatePreferences(preferencesId, preferences);
		return userPreferencesJPA;
	}
	
	@Override
	public CRONIOBOIUserPreferences updateUserPreferences(Long userPreferencesId, CRONIOBOIUserPreferences userPreferences) {
		CRONIOBOUserPreferences userPreferencesFromDb = getPersistenceModule().get(userPreferencesId);
		if (userPreferencesFromDb != null) {
			userPreferencesFromDb.setDownTime(userPreferences.getDownTime());
			userPreferencesFromDb.setSavedTime(new Date());
			userPreferencesFromDb = getPersistenceModule().update(userPreferencesFromDb);
		}
		return userPreferencesFromDb;
	}
	
	@Override
	public CRONIOBOIUserPreferences getUserPreferences(Long preferencesId, String userpreferencesId) {
		CRONIOBOUserPreferences userPreferences = null;
		
		CRONIOBOIPreferences preferences = getPreferencesPersistence().getPreferences(preferencesId);
		AEMFTPersistenceParamsContainer params = new AEMFTPersistenceParamsContainer();
		params.addQueryParam(CRONIOBOIUserPreferences.PREFERENCES, preferences);
		params.addQueryParam(CRONIOBOIUserPreferences.USER_PREFERENCES_ID, userpreferencesId);
		
		List<CRONIOBOUserPreferences> userPreferencesList = getPersistenceModule().query(params.getQueryParams());
		if (!AEMFTCommonUtilsBase.isEmptyList(userPreferencesList)) {
			userPreferences = userPreferencesList.get(0);
		}
		return userPreferences;
	}

	/**
	 * AEMFTIHasPersistenceModule
	 */
	@Override
	public Class<CRONIOBOUserPreferences> getPersistenceClass() {
		return CRONIOBOUserPreferences.class;
	}

}
