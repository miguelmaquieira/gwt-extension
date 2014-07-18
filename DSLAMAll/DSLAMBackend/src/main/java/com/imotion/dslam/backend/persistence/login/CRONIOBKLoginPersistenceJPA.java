package com.imotion.dslam.backend.persistence.login;

import java.util.List;

import com.imotion.dslam.backend.persistence.jpa.DSLAMBKPersistenceModuleJPA;
import com.imotion.dslam.bom.CRONIOBOIUser;
import com.imotion.dslam.bom.data.CRONIOBOUser;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.core.AEMFTICoreProxyService;
import com.selene.arch.exe.core.envi.login.persistence.implbase.AEMFTLoginPersistenceBase;

public class CRONIOBKLoginPersistenceJPA extends AEMFTLoginPersistenceBase<CRONIOBOIUser, CRONIOBOUser> implements CRONIOBKILoginPersistenceService {

	private static final long serialVersionUID = -488472600386070256L;
	
	private DSLAMBKPersistenceModuleJPA<CRONIOBOUser, Long> persistenceModule;

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


	/**************************************************************
	 *                	   PRIVATE FUNCTIONS                      *
	 **************************************************************/

}
