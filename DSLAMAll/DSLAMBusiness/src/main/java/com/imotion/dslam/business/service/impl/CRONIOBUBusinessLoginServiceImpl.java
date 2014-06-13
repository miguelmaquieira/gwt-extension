package com.imotion.dslam.business.service.impl;


import java.util.Locale;

import com.imotion.dslam.business.service.CRONIOBUIBusinessLoginService;
import com.imotion.dslam.business.service.CRONIOBUIBusinessLoginServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUBusinessWrapperPersistence;
import com.imotion.dslam.business.service.DSLAMBUIBusinessCommonServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUIBusinessCommonServiceTrace;
import com.imotion.dslam.business.service.DSLAMBUIProjectBusinessServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUIServiceIdConstant;
import com.selene.arch.base.bom.AEMFTILoginData;
import com.selene.arch.base.bom.AEMFTILoginDataConstants;
import com.selene.arch.base.exe.bus.login.AEMFTIBusinessLoginServiceConstants;
import com.selene.arch.base.exe.core.appli.businesswrapper.AEMFTIBusinessModelConstant;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTIMetadataElementController;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.bus.login.impl.AEMFTBusinessLoginServiceImpl;
import com.selene.arch.exe.core.appli.metadata.element.factory.AEMFTMetadataElementReflectionBasedFactory;

@SuppressWarnings("serial")
public class CRONIOBUBusinessLoginServiceImpl extends AEMFTBusinessLoginServiceImpl<DSLAMBUBusinessWrapperPersistence> implements CRONIOBUIBusinessLoginService, CRONIOBUIBusinessLoginServiceConstants, DSLAMBUIBusinessCommonServiceConstants {

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
		return CRONIOBUIBusinessLoginServiceConstants.CTE_BUSINESS_LOGIN_SERVICE_NAME;
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
		getTrace().trace(DSLAMBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_NULL_ELEMENT_TRACE, params);
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
	protected DSLAMBUBusinessWrapperPersistence createPersistenceWrapper() {
		return new DSLAMBUBusinessWrapperPersistence();
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
		AEMFTMetadataElementComposite 	contextIn			= getContext().getContextDataIN();
		AEMFTIMetadataElementController elementController	= getContext().getElementController();

		String userId = elementController.getElementAsString(AEMFTILoginDataConstants.SESSION_USER_ID, contextIn);
		
		AEMFTMetadataElementComposite projectsDataContextIn = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		elementController.setElement(AEMFTILoginDataConstants.USER_ID, projectsDataContextIn, userId);
		
		AEMFTMetadataElementComposite 	projectsDataContextOut 	= getController().executeService(projectsDataContextIn, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_PROJECT_GET_ALL_PROJECTS_ID);
		AEMFTMetadataElementComposite	projectsData			= elementController.getElementAsComposite(DSLAMBUIProjectBusinessServiceConstants.PROJECT_DATA_LIST, projectsDataContextOut);
		
		//ContextOut
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(DSLAMBUIProjectBusinessServiceConstants.PROJECT_DATA_LIST, projectsData);
	}

	private String getError() {
		return getElementDataController().getElementAsString(AEMFTIBusinessModelConstant.CTE_MFT_AE_SERVER_SERVICE_COMM_CONTEXT_ERROR_LITERAL ,getContext().getContextOUT());
	}

}
