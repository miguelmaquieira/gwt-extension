package com.imotion.dslam.backend.persistence.service.machineproperties;

import com.imotion.dslam.backend.persistence.jpa.DSLAMBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.bom.data.CRONIOBOMachineProperties;

public class CRONIOBKMachinePropertiesPersistenceServiceJPA extends DSLAMBKPersistenceServiceBaseJPA<CRONIOBOIMachineProperties, CRONIOBOMachineProperties, Long> implements CRONIOBKIMachinePropertiesPersistenceService {

	private static final long serialVersionUID = -8955892342600007848L;

	@Override
	public CRONIOBOIMachineProperties addMachineProperties(CRONIOBOIMachineProperties machineProperties, Long preferencesId) {
		CRONIOBOIPreferences		preferences				= getPreferencesPersistence().getPreferences(preferencesId);
		CRONIOBOMachineProperties	machinePropertiesJPA	= (CRONIOBOMachineProperties) machineProperties;
		machinePropertiesJPA.setPreferences(preferences);
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
			getPersistenceModule().update(machinePropertiesFromDb);
		}
		return machinePropertiesFromDb;
	}

	/**
	 * AEMFTIHasPersistenceModule
	 */
	@Override
	public Class<CRONIOBOMachineProperties> getPersistenceClass() {
		return CRONIOBOMachineProperties.class;
	}

}
