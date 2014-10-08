package com.imotion.dslam.front.business.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window.Location;
import com.imotion.dslam.business.DSLAMBUIServiceConstant;
import com.selene.arch.exe.gwt.client.common.AEGWTBaseAppContextMapper;
import com.selene.arch.exe.gwt.client.common.AEGWTMessageWrapper;
import com.selene.arch.exe.gwt.client.phonegap.AEGWTBasePhoneGapConstants;
import com.selene.arch.exe.gwt.client.presenter.base.AEGWTBasePresenter;
import com.selene.arch.exe.gwt.client.presenter.controller.AEGWTIControllerPresenter;
import com.selene.arch.exe.gwt.client.presenter.controller.AEGWTLoginAppController;
import com.selene.arch.exe.gwt.client.presenter.flow.AEGWTIFlowController;
import com.selene.arch.exe.gwt.mvp.AEGWTCompositePanelViewDisplay;
import com.selene.arch.exe.gwt.mvp.context.ContextRetriever;

public abstract class DSLAMBusBaseAppController extends AEGWTLoginAppController {

	private AEGWTIFlowController			flowController;
	private AEGWTBaseAppContextMapper		contextMapper;
	private DSLAMBusMessageWrapper			messageWrapper;

	@Override
	protected String getModuleBaseUrl() {
		return GWT.getModuleBaseURL();
	}

	@Override
	public String getApplicationId() {
		return DSLAMBusPresenterBaseConstants.APPLICATION_ID;
	}
	
	@Override
	public String getModuleId() {
		return DSLAMBusPresenterBaseConstants.MODULE_ID;
	}
	
	@Override
	public String getApplicationName() {
		return DSLAMBusPresenterBaseConstants.APPLICATION_NAME;
	}

	@Override
	protected AEGWTBasePresenter<AEGWTCompositePanelViewDisplay> getLogoutPresenter() {
		return null;
	}

	@Override
	public AEGWTMessageWrapper getMessages() {
		if (messageWrapper == null) {
			messageWrapper = new DSLAMBusMessageWrapper();
		}
		return messageWrapper;
	}

	@Override
	public String getApplicationStoreName() {
		return DSLAMBusBaseAppControllerConstants.DSLAM_STORE;
	}

	@Override
	public AEGWTIFlowController getFlowController() {
		if (flowController == null) {
			flowController = createFlowController();
		}
		return flowController;
	}
	
	public String getBusinessLoginService() {
		return DSLAMBUIServiceConstant.CTE_SRV_BUSINESS_LOGIN_SERVICE_DEFAULT_IMPL;
	}

	@Override
	protected AEGWTIControllerPresenter<?> getControllerPresenter() {
		return createPresenterController();
	}

	@Override
	protected String getServiceRelativeUrl() {
		return AEGWTBasePhoneGapConstants.CTE_PHONEGAP_RELATIVE_SERVICE_URL;
	}

	@Override
	protected ContextRetriever instantiateContextRetriever(final String retrieverId, final String arg) {
		ContextRetriever rc = null;

		
		return rc;
	}

	@Override
	protected AEGWTBaseAppContextMapper getContextMapper() {
		if (contextMapper == null) {
			contextMapper = createContextMapper();
		}
		return contextMapper;
	}

	@Override
	protected void redirectToWebVersion (WEB_VERSION webVersion) {
		String url = "";
		Location.assign(url);
	}
	
	
	@Override
	protected Date getCookiesExpirationDate() {
		long DURATION = 0;
		Date expires = new Date(DURATION);
		return expires;
	}
	
	/**********************************************************************
	 *                           PRIVATE FUNCTIONS
	 **********************************************************************/

}

