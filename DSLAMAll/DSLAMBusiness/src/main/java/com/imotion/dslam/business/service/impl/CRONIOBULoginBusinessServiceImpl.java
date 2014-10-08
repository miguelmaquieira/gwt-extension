package com.imotion.dslam.business.service.impl;


import java.util.Locale;

import com.imotion.dslam.business.service.CRONIOBUIExecuteBusinessServiceConstants;
import com.imotion.dslam.business.service.CRONIOBUILoginBusinessService;
import com.imotion.dslam.business.service.CRONIOBUILoginBusinessServiceConstants;
import com.imotion.dslam.business.service.CRONIOBUIPreferencesBusinessServiceConstants;
import com.imotion.dslam.business.service.CRONIOBUIProjectBusinessServiceConstants;
import com.imotion.dslam.business.service.base.CRONIOBUICommonServiceConstants;
import com.imotion.dslam.business.service.base.CRONIOBUICommonServiceTrace;
import com.imotion.dslam.business.service.base.CRONIOBUIServiceIdConstant;
import com.imotion.dslam.business.service.base.CRONIOBUWrapperPersistence;
import com.selene.arch.base.bom.AEMFTILoginData;
import com.selene.arch.base.bom.AEMFTILoginDataConstants;
import com.selene.arch.base.exe.bus.login.AEMFTIBusinessLoginServiceConstants;
import com.selene.arch.base.exe.core.appli.businesswrapper.AEMFTIBusinessModelConstant;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTIMetadataElementController;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.bus.login.impl.AEMFTBusinessLoginServiceImpl;
import com.selene.arch.exe.core.appli.metadata.element.factory.AEMFTMetadataElementReflectionBasedFactory;

@SuppressWarnings("serial")
public class CRONIOBULoginBusinessServiceImpl extends AEMFTBusinessLoginServiceImpl<CRONIOBUWrapperPersistence> implements CRONIOBUILoginBusinessService, CRONIOBUILoginBusinessServiceConstants, CRONIOBUICommonServiceConstants {

	/**
	 * AEMFTBusinessLoginServiceImpl
	 */

	@Override
	public void processNewUser() {
		super.processNewUser();
	}

	@Override
	public void processActivateUser() {
		super.processActivateUser();
	}

	@Override
	public void processLogin() {
		super.processLogin();
		String error = getError();
		if (error == null) {		
			loadUserData();
		}
	}

	@Override
	public void processSocialLogin() {
		super.processSocialLogin();
		String error = getError();
		if (error == null) {
			loadUserData();
		}
	}

	@Override
	public void isValidSession() {
		super.isValidSession();
		String error = getError();
		if (error == null) {
			loadUserData();
		}
	}

	@Override
	public  void processForgotPassword() {
		super.processForgotPassword();
	}

	@Override
	public void processResetPassword() {
		super.processResetPassword();
	}
	
	@Override
	public void updateUserSocialData() {
		// TODO Auto-generated method stub
		
	}

	/******************************************************************
	 * 					      AEMFTIFactorable                        *
	 ******************************************************************/

	@Override
	public void initialize(Object[] args) {
		super.initialize(args);
	}

	@Override
	public void releaseInstance() {
		super.releaseInstance();
	}
	
	/**
	 * AEMFTIBusinessService
	 */
	@Override
	public String getName() {
		return CRONIOBUILoginBusinessServiceConstants.CTE_BUSINESS_LOGIN_SERVICE_NAME;
	}


	/********************************************************************
	 * 								TRACES								*
	 ********************************************************************/
	@Override
	public void traceNullElement(String serviceName, String methodName) {
		traceNullElement(serviceName, methodName, "unknown");
	}

	public void traceNullElement(String serviceName, String methodName , String elementType ) {
		Object[] params = new Object[3];
		params[0] = serviceName;
		params[1] = methodName;
		params[2] = elementType;
		getTrace().trace(CRONIOBUICommonServiceTrace.CTE_BUSINESS_SERVICE_NULL_ELEMENT_TRACE, params);
	}

	/**
	 * PROTECTED
	 */

	@Override
	protected void setLoginContextOut(AEMFTILoginData loginData) {
		super.setLoginContextOut(loginData);
	}


	@Override
	protected AEMFTILoginData newUserPostProcess(AEMFTILoginData loginData) {
		return loginData;
	}

	@Override
	protected String getDataBaseVersion() {
		String version = null;
		if (version == null) {
			// ini-trace
			// TODO no se ha podido recuperar la version de la BD, commonPersistence = ?, CommonProperties = ?, Clave de b�squeda = AEMFTICommonPropertiesDataConstant.KEY_VERSION
			// end-trace
		}
		return version;
	}

	@Override
	protected void sendEmailNewUserRegistered(AEMFTILoginData userInfo, Locale locale){
		//TODO: SendMAIL
	}

	@Override
	protected CRONIOBUWrapperPersistence createPersistenceWrapper() {
		return new CRONIOBUWrapperPersistence();
	}

	@Override
	protected void sendChangePasswordEmail(String key, String username, String email, Locale locale) {
		String subject 			= getLiteral().getLiteral(EMAIL_SUBJECT_RESET_PWD,	getSession().getCurrentLocaleName(), new Object[]{});
		String webUrl 			= getSession().getUrlServer();
		String urlReset			= webUrl + "/"
				+ AEMFTIBusinessLoginServiceConstants.TOKEN_START_URI
				+ AEMFTIBusinessLoginServiceConstants.URI_RESET_PASSWORD
				+ AEMFTIBusinessLoginServiceConstants.TOKEN_RESET_PASSWORD_KEY
				+ key;

		String body = getLiteral().getLiteral(EMAIL_BODY_RESET_PWD,	getSession().getCurrentLocaleName(), new Object[]{urlReset});
		sendEmail(username, subject, body, email);
	}

	@Override
	protected void sendActivateAccountEmail(String key, String username, String email, Locale locale) {

		String subject  = getLiteral().getLiteral(EMAIL_SUBJECT_ACTIVATE_ACCOUNT,	getSession().getCurrentLocaleName(), new Object[]{});
		String webUrl 			= getSession().getUrlServer();
		String url 				=  webUrl + "/"
				+ AEMFTIBusinessLoginServiceConstants.TOKEN_START_URI
				+ AEMFTIBusinessLoginServiceConstants.URI_ACTIVATE_USER
				+ AEMFTIBusinessLoginServiceConstants.TOKEN_ACTIVATION_USER_KEY
				+ key;

		String body = getLiteral().getLiteral(EMAIL_BODY_ACTIVATE_ACCOUNT,	getSession().getCurrentLocaleName(), new Object[]{url});

		sendEmail(username, subject, body, email);
	}

	/********************************************************************
	 * 				       PRIVATE FUNCTIONS				    	    *
	 ********************************************************************/

	private void loadUserData() {
		//ContextOut
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		AEMFTIMetadataElementController elementController	= getContext().getElementController();

		String userId = elementController.getElementAsString(AEMFTILoginDataConstants.SESSION_USER_ID, contextOut);
		
		AEMFTMetadataElementComposite projectsDataContextIn = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		elementController.setElement(AEMFTILoginDataConstants.USER_ID, projectsDataContextIn, userId);
		
		AEMFTMetadataElementComposite 	projectsDataContextOut 	= getController().executeService(projectsDataContextIn, CRONIOBUIServiceIdConstant.CTE_CRONIO_BU_SRV_PROJECT_GET_ALL_PROJECTS_BY_USER_ID);
		AEMFTMetadataElementComposite	projectsData			= elementController.getElementAsComposite(CRONIOBUIProjectBusinessServiceConstants.PROJECT_DATA_LIST, projectsDataContextOut);
		
		AEMFTMetadataElementComposite projectsDataClone = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		AEMFTMetadataElementComposite projectsDataClone2 = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		
		if (projectsData != null) {
			projectsDataClone = (AEMFTMetadataElementComposite) projectsData.cloneObject();
			projectsDataClone2 = (AEMFTMetadataElementComposite) projectsData.cloneObject();
		}
		
		AEMFTMetadataElementComposite 	executionsDataContextOut 	= getController().executeService(projectsDataContextOut, CRONIOBUIServiceIdConstant.CTE_CRONIO_BU_SRV_EXECUTE_GET_ALL_EXECUTIONS_BY_PROJECT_ID);
		AEMFTMetadataElementComposite	executionsData				= elementController.getElementAsComposite(CRONIOBUIExecuteBusinessServiceConstants.EXECUTIONS_DATA, executionsDataContextOut);
		
		AEMFTMetadataElementComposite 	nodeListsDataContextOut 	= getController().executeService(projectsDataClone2, CRONIOBUIServiceIdConstant.CTE_CRONIO_BU_SRV_PROJECT_GET_ALL_NODELISTS_BY_PROJECT_ID);
		AEMFTMetadataElementComposite	nodeListsData				= elementController.getElementAsComposite(CRONIOBUIProjectBusinessServiceConstants.LIST_NODELIST_DATA, nodeListsDataContextOut);
		
		AEMFTMetadataElementComposite preferencesDataContextIn = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		elementController.setElement(AEMFTILoginDataConstants.USER_ID, preferencesDataContextIn, userId);
		
		AEMFTMetadataElementComposite 	preferencesDataContextOut 	= getController().executeService(preferencesDataContextIn, CRONIOBUIServiceIdConstant.CTE_CRONIO_BU_SRV_PREFERENCES_GET_PREFERENCES_ID);
		AEMFTMetadataElementComposite	preferencesData				= elementController.getElementAsComposite(CRONIOBUIPreferencesBusinessServiceConstants.PREFERENCES_DATA, preferencesDataContextOut);

		//ContextOut
		contextOut.addElement(CRONIOBUIProjectBusinessServiceConstants.PROJECT_DATA_LIST		, projectsDataClone);
		contextOut.addElement(CRONIOBUIPreferencesBusinessServiceConstants.PREFERENCES_DATA	, preferencesData);
		contextOut.addElement(CRONIOBUIExecuteBusinessServiceConstants.EXECUTIONS_DATA		, executionsData);
		contextOut.addElement(nodeListsData);
		
	}

	private String getError() {
		return getElementDataController().getElementAsString(AEMFTIBusinessModelConstant.CTE_MFT_AE_SERVER_SERVICE_COMM_CONTEXT_ERROR_LITERAL ,getContext().getContextOUT());
	}

}
