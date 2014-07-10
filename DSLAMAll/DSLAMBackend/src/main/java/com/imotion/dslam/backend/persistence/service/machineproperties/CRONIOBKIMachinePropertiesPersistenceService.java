package com.imotion.dslam.backend.persistence.service.machineproperties;

import com.imotion.dslam.bom.CRONIOBOIMachineProperties;


public interface CRONIOBKIMachinePropertiesPersistenceService {

	CRONIOBOIMachineProperties addMachineProperties(CRONIOBOIMachineProperties machineProperties, Long preferencesId);

	CRONIOBOIMachineProperties updateMachineProperties(Long machinePropertiesId, CRONIOBOIMachineProperties machineProperties);
	
	CRONIOBOIMachineProperties getMachineProperties(Long preferencesId, int machineType);

}
