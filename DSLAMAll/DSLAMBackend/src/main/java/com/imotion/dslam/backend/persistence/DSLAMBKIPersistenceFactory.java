package com.imotion.dslam.backend.persistence;

import com.imotion.cronio.backend.persistence.service.node.CRONIOBKINodePersistenceService;
import com.imotion.dslam.backend.persistence.login.CRONIOBKILoginPersistenceService;
import com.imotion.dslam.backend.persistence.service.file.DSLAMBKIFilePersistenceService;
import com.imotion.dslam.backend.persistence.service.machineproperties.CRONIOBKIMachinePropertiesPersistenceService;
import com.imotion.dslam.backend.persistence.service.preferences.CRONIOBKIPreferencesPersistenceService;
import com.imotion.dslam.backend.persistence.service.process.DSLAMBKIProcessPersistenceService;
import com.imotion.dslam.backend.persistence.service.project.DSLAMBKIProjectPersistenceService;
import com.imotion.dslam.backend.persistence.service.userpreferences.CRONIOBKIUserPreferencesPersistenceService;
import com.selene.arch.exe.back.persistence.AEMFTIPersistenceFactory;

public interface DSLAMBKIPersistenceFactory extends AEMFTIPersistenceFactory {

	public	DSLAMBKIFilePersistenceService newFilePersistence(String sessionId);
	
	public	DSLAMBKIProcessPersistenceService newProcessPersistence(String sessionId);
	
	public	DSLAMBKIProjectPersistenceService newProjectPersistence(String sessionId);
	
	public	CRONIOBKINodePersistenceService newNodePersistence(String sessionId);
	
	public CRONIOBKIPreferencesPersistenceService newPreferencesPersistence(String sessionId);

	public CRONIOBKIMachinePropertiesPersistenceService newMachinePropertiesPersistence(String sessionId);

	public CRONIOBKILoginPersistenceService newUserPersistence(String sessionId);
	
	public	CRONIOBKIUserPreferencesPersistenceService newUserPreferencesPersistence(String sessionId);
	
}
