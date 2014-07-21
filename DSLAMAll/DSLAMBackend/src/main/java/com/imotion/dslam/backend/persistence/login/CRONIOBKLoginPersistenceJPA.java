package com.imotion.dslam.backend.persistence.login;

import java.util.List;

import com.imotion.dslam.backend.persistence.DSLAMBKIPersistenceFactory;
import com.imotion.dslam.backend.persistence.DSLAMBKPersistenceFactoryPool;
import com.imotion.dslam.backend.persistence.jpa.DSLAMBKPersistenceModuleJPA;
import com.imotion.dslam.backend.persistence.service.project.DSLAMBKIProjectPersistenceService;
import com.imotion.dslam.bom.CRONIOBOIUser;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.bom.data.CRONIOBOUser;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.back.persistence.AEMFTIPersistenceService;
import com.selene.arch.exe.core.AEMFTICoreProxyService;
import com.selene.arch.exe.core.envi.login.persistence.implbase.AEMFTLoginPersistenceBase;

public class CRONIOBKLoginPersistenceJPA extends AEMFTLoginPersistenceBase<CRONIOBOIUser, CRONIOBOUser> implements CRONIOBKILoginPersistenceService {

	private static final long serialVersionUID = -488472600386070256L;
	
	private DSLAMBKPersistenceModuleJPA<CRONIOBOUser, Long> 	persistenceModule;
	private DSLAMBKIProjectPersistenceService					projectPersistenceService;
	
	private static DSLAMBKIPersistenceFactory 					factoryPool;

	public CRONIOBKLoginPersistenceJPA() {}
	
	public CRONIOBKLoginPersistenceJPA(AEMFTICoreProxyService proxyCore) {
		super(proxyCore);
	}
	
	@Override
	public CRONIOBOIUser getUserById(long userId) {
		CRONIOBOIUser		user		= null;
		List<CRONIOBOUser>	userList	= getPersistenceModule().query(CRONIOBOIUser.USER_ID, userId);
		if (!AEMFTCommonUtilsBase.isEmptyList(userList)) {
			user = userList.get(0);
		}
		return user;
	}
	
	@Override
	public void addProjectToUser(long userId, Long projectId) {
		DSLAMBOIProject project = getProjectPersistence().getProject(projectId);
		CRONIOBOUser user = (CRONIOBOUser) getUserById(userId);
		user.addProject(project);
		getPersistenceModule().update(user);
	}
	
	public static DSLAMBKIPersistenceFactory getFactoryPersistence(AEMFTICoreProxyService coreProxy) {
		if (factoryPool == null) {
			factoryPool = new DSLAMBKPersistenceFactoryPool(coreProxy);
		}
		return factoryPool;
	}
	
	/**************************************************************
     *                AEMFTIFACTORABLE FUNCTIONS                  *
     **************************************************************/

	@Override
	public void initialize(Object[] args) {
		super.initialize(args);
	}

	@Override
	public void releaseInstance() {
		super.releaseInstance();
		if (projectPersistenceService != null) {
			getFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) projectPersistenceService);
			projectPersistenceService = null;
		}
	}
	
	/**
	 *	AEMFTIHasPersistenceModule 
	 */
	
	@Override
	public DSLAMBKPersistenceModuleJPA<CRONIOBOUser, Long> getPersistenceModule() {
		if (persistenceModule == null) {
			persistenceModule = new DSLAMBKPersistenceModuleJPA<CRONIOBOUser, Long>();
			persistenceModule.initialize(new Object[] { getPersistenceCoreService(), getPersistenceClass(),  getSessionId() });
		}
		return persistenceModule;
	}

	@Override
	public void releaseModule() {
		if (persistenceModule != null) {
			persistenceModule = null;
		}
	}

	@Override
	public Class<CRONIOBOUser> getPersistenceClass() {
		return CRONIOBOUser.class;
	}

	/**************************************************************
	 *               PROTECTED EXTENDED FUNCTIONS                 *
	 **************************************************************/
	/**
	 * AEMTLoginPersistenceBase
	 */
	@Override
	protected Long generateId() {
		//nothing todo
		return null;
	}
	
	@Override
	protected void initFactoryPersistence(AEMFTICoreProxyService coreProxy) {
		getFactoryPersistence(coreProxy);
	}

	/**************************************************************
	 *                	   PRIVATE FUNCTIONS                      *
	 **************************************************************/
	
	private DSLAMBKIProjectPersistenceService getProjectPersistence() {
		if (projectPersistenceService == null) {
			projectPersistenceService = (DSLAMBKIProjectPersistenceService) getFactoryPersistence().newProjectPersistence(getSessionId());
		}
		return projectPersistenceService;
	}
	
	private DSLAMBKIPersistenceFactory getFactoryPersistence() {
		return factoryPool;
	}

}
