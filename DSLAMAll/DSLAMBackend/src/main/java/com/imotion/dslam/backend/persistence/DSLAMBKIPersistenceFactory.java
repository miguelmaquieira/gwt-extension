package com.imotion.dslam.backend.persistence;

import com.imotion.cronio.backend.persistence.service.node.CRONIOBKINodePersistenceService;
import com.imotion.dslam.backend.persistence.preferences.CRONIOBKIPreferencesPersistenceService;
import com.imotion.dslam.backend.persistence.service.file.DSLAMBKIFilePersistenceService;
import com.imotion.dslam.backend.persistence.service.machineproperties.CRONIOBKIMachinePropertiesPersistenceService;
import com.imotion.dslam.backend.persistence.service.process.DSLAMBKIProcessPersistenceService;
import com.imotion.dslam.backend.persistence.service.project.DSLAMBKIProjectPersistenceService;
import com.selene.arch.exe.back.persistence.AEMFTIPersistenceFactory;

public interface DSLAMBKIPersistenceFactory extends AEMFTIPersistenceFactory {

	public	DSLAMBKIFilePersistenceService newFilePersistence();
	
	public	DSLAMBKIProcessPersistenceService newProcessPersistence();
	
	public	DSLAMBKIProjectPersistenceService newProjectPersistence();
	
	public	CRONIOBKINodePersistenceService newNodePersistence();
	
	public CRONIOBKIPreferencesPersistenceService newPreferencesPersistence();

	public CRONIOBKIMachinePropertiesPersistenceService newMachinePropertiesPersistence();
	
}
