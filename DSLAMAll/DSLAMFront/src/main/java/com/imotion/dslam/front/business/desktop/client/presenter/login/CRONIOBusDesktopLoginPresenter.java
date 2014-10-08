package com.imotion.dslam.front.business.desktop.client.presenter.login;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.imotion.dslam.bom.CRONIOBOIUserDataConstants;
import com.imotion.dslam.business.service.base.CRONIOBUIServiceIdConstant;
import com.imotion.dslam.front.business.desktop.client.presenter.DSLAMBusBasePresenter;
import com.imotion.dslam.front.business.desktop.client.widget.authentication.CRONIOBusDesktopSignInForm;
import com.imotion.dslam.front.business.desktop.client.widget.layout.CRONIOBusDesktopLayoutContainer;
import com.selene.arch.base.bom.AEMFTILoginDataConstants;
import com.selene.arch.base.exe.bus.login.AEMFTIBusinessBaseLoginServiceConstant.LoginResult;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.gwt.client.service.comm.AEGWTCommClientAsynchCallbackRequest;
import com.selene.arch.exe.gwt.mvp.event.authentication.AEGWTAuthenticationEvent;
import com.selene.arch.exe.gwt.mvp.event.authentication.AEGWTAuthenticationEventTypes.AUTHENTICATION_TYPE;
import com.selene.arch.exe.gwt.mvp.event.authentication.AEGWTHasAuthenticationEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.flow.AEGWTFlowEvent;
import com.selene.arch.exe.gwt.mvp.event.navigator.AEGWTHasNavigationEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.navigator.AEGWTNavigationEvent;
import com.selene.arch.exe.gwt.mvp.event.navigator.AEGWTNavigationEventTypes.NAVIGATOR_TYPE;

public class CRONIOBusDesktopLoginPresenter extends DSLAMBusBasePresenter<CRONIOBusDesktopLoginDisplay> implements AEGWTHasAuthenticationEventHandlers, AEGWTHasNavigationEventHandlers{

	public static final String NAME = "CRONIOBusDesktopLoginPresenter";
	
	public CRONIOBusDesktopLoginPresenter(CRONIOBusDesktopLoginDisplay view) {
		super(view);		
	}

	@Override
	public void bind() {
		getLogicalEventHandlerManager().addEventHandler(AEGWTHasAuthenticationEventHandlers.TYPE, this);
		getLogicalEventHandlerManager().addEventHandler(AEGWTHasNavigationEventHandlers.TYPE, this);
	}
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	protected void addView(HasWidgets container) {
		CRONIOBusDesktopLayoutContainer layoutContainer = (CRONIOBusDesktopLayoutContainer) container;
		layoutContainer.setCurrentPresenter(this);
		layoutContainer.showLayout(CRONIOBusDesktopLayoutContainer.LAYOUT_EMPTY_ID);
		Widget viewAsWidget = getView().asWidget();
		layoutContainer.setLayoutContent(viewAsWidget);
	}
	

	/************************************************************************
	 *                   AEGWTHasAuthenticationEventHandlers
	 ************************************************************************/
	
	@Override
	public void dispatchEvent(AEGWTAuthenticationEvent evt) {
		if (AUTHENTICATION_TYPE.SIGN_IN_ATTEMPT.equals(evt.getEventType())) {
			signIn(evt);
		}		
	}

	@Override
	public boolean isDispatchEventType(AUTHENTICATION_TYPE type) {
		return AUTHENTICATION_TYPE.SIGN_IN_ATTEMPT.equals(type);
	}
	
	
	/************************************************************************
	 *                   AEGWTHasNavigationEventHandlers
	 ************************************************************************/
	
	@Override
	public void dispatchEvent(AEGWTNavigationEvent evt) {
		super.dispatchEvent(evt);
		String sourceWidget = evt.getSourceWidget();
		if (CRONIOBusDesktopSignInForm.NAME.equals(sourceWidget)) {
			AEGWTFlowEvent flowEvt = new AEGWTFlowEvent(getName(), getName(), null);
			flowEvt.setSourceWidget(sourceWidget);
			getFlowEventHandlerManager().fireEvent(flowEvt);
		}
	}

	@Override
	public boolean isDispatchEventType(NAVIGATOR_TYPE type) {
		return NAVIGATOR_TYPE.OK_EVENT.equals(type);
	}
	
	/************************************************************************
	 *                        PRIVATE FUNCTIONS
	 ************************************************************************/
	
	private void signIn(AEGWTAuthenticationEvent evt) {
		String userName = evt.getElementAsString(CRONIOBOIUserDataConstants.EMAIL);
		String passHash	= evt.getElementAsString(CRONIOBOIUserDataConstants.HASH);

		AEMFTMetadataElementComposite loginData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		getElementDataController().setElement(CRONIOBOIUserDataConstants.EMAIL, loginData, userName);
		getElementDataController().setElement(CRONIOBOIUserDataConstants.HASH, loginData, passHash);
		
		getClientServerConnection().executeComm(loginData, CRONIOBUIServiceIdConstant.CTE_MTF_AE_BUS_SERVICE_LOGIN_PROCESS_LOGIN_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {
			
			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				
				AEGWTAuthenticationEvent evt = new AEGWTAuthenticationEvent(getView().getWindowName(),getName());
				evt.setEventType(AUTHENTICATION_TYPE.SIGN_IN_RESPONSE);
				String loginResultStr = getElementDataController().getElementAsString(AEMFTILoginDataConstants.LOGIN_RESULT, dataResult);
				if (!AEMFTCommonUtilsBase.isEmptyString(loginResultStr)) {
					LoginResult loginResult = LoginResult.valueOf(loginResultStr);
					if (LoginResult.INCORRECT_USERNAME.equals(loginResult)) {
						evt.addElementAsIntDataValue(CRONIOBOIUserDataConstants.ERROR_TYPE_SIGNIN_ACCOUNT_NO_EXISTS);
					} else if (LoginResult.INCORRECT_PASSWORD.equals(loginResult)) {
						evt.addElementAsIntDataValue(CRONIOBOIUserDataConstants.ERROR_TYPE_SIGNIN_INCORRECT_PASSWORD);
					}
				} else {
					persistSessionData(dataResult);										
				}
				getLogicalEventHandlerManager().fireEvent(evt);

				
			}
			
			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
