package com.imotion.transit.front.business.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window.Location;
import com.imotion.transit.business.TRANSBUIServiceConstant;
import com.selene.arch.exe.gwt.client.common.AEGWTBaseAppContextMapper;
import com.selene.arch.exe.gwt.client.common.AEGWTMessageWrapper;
import com.selene.arch.exe.gwt.client.phonegap.AEGWTBasePhoneGapConstants;
import com.selene.arch.exe.gwt.client.presenter.base.AEGWTBasePresenter;
import com.selene.arch.exe.gwt.client.presenter.controller.AEGWTControllerPresenter;
import com.selene.arch.exe.gwt.client.presenter.controller.AEGWTLoginAppController;
import com.selene.arch.exe.gwt.client.presenter.flow.AEGWTIFlowController;
import com.selene.arch.exe.gwt.mvp.AEGWTCompositePanelViewDisplay;
import com.selene.arch.exe.gwt.mvp.context.ContextRetriever;

public abstract class TRANSBusBaseAppController extends AEGWTLoginAppController {

	private AEGWTIFlowController			flowController;
	private AEGWTBaseAppContextMapper		contextMapper;
	private TRANSBusMessageWrapper				messageWrapper;

	@Override
	protected String getModuleBaseUrl() {
		return GWT.getModuleBaseURL();
	}

	@Override
	public String getApplicationId() {
		return TRANSBusPresenterBaseConstants.APPLICATION_ID;
	}
	
	@Override
	public String getModuleId() {
		return TRANSBusPresenterBaseConstants.MODULE_ID;
	}
	
	@Override
	public String getApplicationName() {
		return TRANSBusPresenterBaseConstants.APPLICATION_NAME;
	}

	@Override
	protected AEGWTBasePresenter<AEGWTCompositePanelViewDisplay> getLogoutPresenter() {
		return null;
	}

	@Override
	public AEGWTMessageWrapper getMessages() {
		if (messageWrapper == null) {
			messageWrapper = new TRANSBusMessageWrapper();
		}
		return messageWrapper;
	}

	@Override
	public String getApplicationStoreName() {
		return TRANSBusBaseAppControllerConstants.TRANS_STORE;
	}

	@Override
	public AEGWTIFlowController getFlowController() {
		if (flowController == null) {
			flowController = createFlowController();
		}
		return flowController;
	}
	
	public String getBusinessLoginService() {
		return TRANSBUIServiceConstant.CTE_SRV_BUSINESS_LOGIN_SERVICE_DEFAULT_IMPL;
	}

	@Override
	protected AEGWTControllerPresenter<?> getControllerPresenter() {
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
//		if (WEB_VERSION.TOUCH.equals(webVersion)) {
//			url = BusinessBaseAppControllerConstants.TOUCH_URI;
//		} else {
//			url = BusinessBaseAppControllerConstants.DESKTOP_URI;
//		}
		Location.assign(url);
	}
	
	/**********************************************************************
	 *                           PRIVATE FUNCTIONS
	 **********************************************************************/

}

