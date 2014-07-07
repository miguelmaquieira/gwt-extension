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
		return machinePropertiesJPA;
	}
	
	@Override
	public CRONIOBOIMachineProperties updateMachineProperties(Long machinePropertiesId, CRONIOBOIMachineProperties machineProperties) {
		CRONIOBOMachineProperties machinePropertiesFromDb = getPersistenceModule().get(machinePropertiesId);
		if (machinePropertiesFromDb != null) {
			machinePropertiesFromDb.setEnableCommandScript(machineProperties.getEnableCommandScript());
			machinePropertiesFromDb.setEnablePasswordPrompt(machineProperties.getEnablePasswordPrompt());
			machinePropertiesFromDb.setEnablePasswordScript(machineProperties.getEnablePasswordScript());
			machinePropertiesFromDb.setEnablePrompt(machineProperties.getEnablePrompt());
			machinePropertiesFromDb.setFinishConnectionScript(machineProperties.getFinishConnectionScript());
			machinePropertiesFromDb.setGeneralTimeout(machineProperties.getGeneralTimeout());
			machinePropertiesFromDb.setInitConnectionScript(machineProperties.getInitConnectionScript());
			machinePropertiesFromDb.setInitConnectionTimeout(machineProperties.getInitConnectionTimeout());
			machinePropertiesFromDb.setMachineDescription(machineProperties.getMachineDescription());
			machinePropertiesFromDb.setMachineName(machineProperties.getMachineName());
			machinePropertiesFromDb.setPassword(machineProperties.getPassword());
			machinePropertiesFromDb.setPasswordPrompt(machineProperties.getPasswordPrompt());
			machinePropertiesFromDb.setPrompt(machineProperties.getPrompt());
			machinePropertiesFromDb.setProtocolType(machineProperties.getProtocolType());
			machinePropertiesFromDb.setSetupTerminalScript(machineProperties.getSetupTerminalScript());
			machinePropertiesFromDb.setUsername(machineProperties.getUsername());
			machinePropertiesFromDb.setUserPrompt(machineProperties.getUserPrompt());
			machinePropertiesFromDb.setSaveTime(new Date());
			getPersistenceModule().update(machinePropertiesFromDb);
		}
		return machinePropertiesFromDb;
	}
	
	@Override
	public CRONIOBOIMachineProperties getMachineProperties(Long preferencesId, int machineType) {
		CRONIOBOMachineProperties machineProperties = null;
		
		CRONIOBOIPreferences preferences = getPreferencesPersistence().getPreferences(preferencesId);
		AEMFTPersistenceParamsContainer params = new AEMFTPersistenceParamsContainer();
		params.addQueryParam(CRONIOBOIMachineProperties.PREFERENCES, preferences);
		params.addQueryParam(CRONIOBOIMachineProperties.MACHINE_TYPE, machineType);
		
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
