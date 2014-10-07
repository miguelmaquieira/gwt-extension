package com.imotion.dslam.backend.persistence;

import com.imotion.cronio.backend.persistence.service.node.CRONIOBKINodePersistenceService;
import com.imotion.cronio.backend.persistence.service.nodelist.CRONIOBKINodeListPersistenceService;
import com.imotion.dslam.backend.persistence.log.CRONIOBKILogPersistenceService;
import com.imotion.dslam.backend.persistence.login.CRONIOBKILoginPersistenceService;
import com.imotion.dslam.backend.persistence.service.execution.CRONIOBKIExecutionPersistenceService;
import com.imotion.dslam.backend.persistence.service.file.CRONIOBKIFilePersistenceService;
import com.imotion.dslam.backend.persistence.service.machineproperties.CRONIOBKIMachinePropertiesPersistenceService;
import com.imotion.dslam.backend.persistence.service.preferences.CRONIOBKIPreferencesPersistenceService;
import com.imotion.dslam.backend.persistence.service.process.CRONIOBKIProcessPersistenceService;
import com.imotion.dslam.backend.persistence.service.project.CRONIOBKIProjectPersistenceService;
import com.imotion.dslam.backend.persistence.service.userpreferences.CRONIOBKIUserPreferencesPersistenceService;
import com.selene.arch.exe.back.persistence.AEMFTIPersistenceFactory;

public interface CRONIOBKIPersistenceFactory extends AEMFTIPersistenceFactory {

	public	CRONIOBKIFilePersistenceService newFilePersistence(String sessionId);
	
	public	CRONIOBKIProcessPersistenceService newProcessPersistence(String sessionId);
	
	public	CRONIOBKIProjectPersistenceService newProjectPersistence(String sessionId);
	
	public	CRONIOBKINodePersistenceService newNodePersistence(String sessionId);
	
	public	CRONIOBKINodeListPersistenceService newNodeListPersistence(String sessionId);
	
	public CRONIOBKIPreferencesPersistenceService newPreferencesPersistence(String sessionId);

	public CRONIOBKIMachinePropertiesPersistenceService newMachinePropertiesPersistence(String sessionId);

	public CRONIOBKILoginPersistenceService newUserPersistence(String sessionId);
	
	public	CRONIOBKIUserPreferencesPersistenceService newUserPreferencesPersistence(String sessionId);
	
	public	CRONIOBKIExecutionPersistenceService newExecutionPersistence(String sessionId);

	public CRONIOBKILogPersistenceService newLogPersistence(String sessionId);
}
