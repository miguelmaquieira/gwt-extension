package com.imotion.dslam.backend.persistence.login;

import com.imotion.dslam.bom.CRONIOBOIUser;
import com.selene.arch.exe.core.envi.login.persistence.AEMFTILoginPersistenceService;


public interface CRONIOBKILoginPersistenceService extends AEMFTILoginPersistenceService {
		
	CRONIOBOIUser getUserById(long userId);

	void addProjectToUser(long userId, Long projectId);
	
}
