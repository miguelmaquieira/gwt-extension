package com.imotion.dslam.backend.persistence.service.machineproperties;

import java.util.Date;
import java.util.List;

import com.imotion.dslam.backend.persistence.jpa.DSLAMBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.bom.data.CRONIOBOMachineProperties;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.back.persistence.AEMFTPersistenceParamsContainer;

public class CRONIOBKMachinePropertiesPersistenceServiceJPA extends DSLAMBKPersistenceServiceBaseJPA<CRONIOBOIMachineProperties, CRONIOBOMachineProperties, Long> implements CRONIOBKIMachinePropertiesPersistenceService {

	private static final long serialVersionUID = -8955892342600007848L;

	@Override
	public CRONIOBOIMachineProperties addMachineProperties(CRONIOBOIMachineProperties machineProperties, Long preferencesId) {
		Date						currentDate				= new Date();
		CRONIOBOIPreferences		preferences				= getPreferencesPersistence().getPreferences(preferencesId);
		CRONIOBOMachineProperties	machinePropertiesJPA	= (CRONIOBOMachineProperties) machineProperties;
		machinePropertiesJPA.setPreferences(preferences);
		machinePropertiesJPA.setSaveTime(currentDate);
		machinePropertiesJPA.setCreationTime(currentDate);
		getPersistenceModule().create(machinePropertiesJPA);
		preferences.setSavedTime(currentDate);
		getPreferencesPersistence().updatePreferences(preferencesId, preferences);
		return machinePropertiesJPA;
	}
	
	@Override
	public CRONIOBOIMachineProperties updateMachineProperties(Long machinePropertiesId, CRONIOBOIMachineProperties machineProperties) {
		CRONIOBOMachineProperties machinePropertiesFromDb = getPersistenceModule().get(machinePropertiesId);
		if (machinePropertiesFromDb != null) {
			machinePropertiesFromDb.setProtocolType(machineProperties.getProtocolType());
			machinePropertiesFromDb.setUsername(machineProperties.getUsername());
			machinePropertiesFromDb.setPassword(machineProperties.getPassword());
			machinePropertiesFromDb.setTimeout(machineProperties.getTimeout());
			machinePropertiesFromDb.setPromptRegEx(machineProperties.getPromptRegEx());
			machinePropertiesFromDb.setConnectionVariables(machineProperties.getConnectionVariables());
			machinePropertiesFromDb.setSaveTime(new Date());
			getPersistenceModule().update(machinePropertiesFromDb);
		}
		return machinePropertiesFromDb;
	}
	
	@Override
	public CRONIOBOIMachineProperties getMachineProperties(Long preferencesId, String machineName) {
		CRONIOBOMachineProperties machineProperties = null;
		
		CRONIOBOIPreferences preferences = getPreferencesPersistence().getPreferences(preferencesId);
		AEMFTPersistenceParamsContainer params = new AEMFTPersistenceParamsContainer();
		params.addQueryParam(CRONIOBOIMachineProperties.PREFERENCES, preferences);
		params.addQueryParam(CRONIOBOIMachineProperties.MACHINE_NAME, machineName);
		
		List<CRONIOBOMachineProperties> machinePropertiesList = getPersistenceModule().query(params.getQueryParams());
		if (!AEMFTCommonUtilsBase.isEmptyList(machinePropertiesList)) {
			machineProperties = machinePropertiesList.get(0);
		}
		
		return machineProperties;
	}

	/**
	 * AEMFTIHasPersistenceModule
	 */
	@Override
	public Class<CRONIOBOMachineProperties> getPersistenceClass() {
		return CRONIOBOMachineProperties.class;
	}

}
