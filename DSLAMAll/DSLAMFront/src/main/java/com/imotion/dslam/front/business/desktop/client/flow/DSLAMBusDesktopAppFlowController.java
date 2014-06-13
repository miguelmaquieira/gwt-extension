package com.imotion.dslam.front.business.desktop.client.flow;

import com.google.gwt.user.client.History;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopHistoryNavigationConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusProjectBasePresenterConstants;
import com.selene.arch.exe.gwt.client.AEGWTHistoryNavigationConstants;
import com.selene.arch.exe.gwt.client.presenter.base.AEGWTBasePresenterConstants;
import com.selene.arch.exe.gwt.client.presenter.controller.AEGWTControllerPresenterConstants;
import com.selene.arch.exe.gwt.client.presenter.controller.AEGWTILoginAppControllerConstants;
import com.selene.arch.exe.gwt.client.presenter.flow.AEGWTBaseFlowController;
import com.selene.arch.exe.gwt.client.presenter.info.AEGWTInfoPresenterConstants;
import com.selene.arch.exe.gwt.client.presenter.login.AEGWTLoginPresenterConstants;
import com.selene.arch.exe.gwt.mvp.AEGWTCompositePanelViewDisplay;
import com.selene.arch.exe.gwt.mvp.AEGWTIPresenter;
import com.selene.arch.exe.gwt.mvp.event.flow.AEGWTFlowEvent;


public class DSLAMBusDesktopAppFlowController extends AEGWTBaseFlowController {

	public static final String NAME = "BusinessDesktopManagementAppFlowController";

	public DSLAMBusDesktopAppFlowController(AEGWTIPresenter presenter, AEGWTCompositePanelViewDisplay display) {
		super(display);
		initialize(presenter);
	}

	@Override
	public void dispatchEvent(AEGWTFlowEvent evt) {
		boolean processed = false;
		processed = processDeactivateFlowController(evt);
		if (super.active) {
			processed = processHeaderEvent(evt);
			processed = processQuickAccessButton(evt);

			String sourceWindow = evt.getSourceWindow();
			getAppController().setPreviousWindowName(sourceWindow);

			if (!processed) {
				if (AEGWTLoginPresenterConstants.NAME.equals(sourceWindow)) {
					loginScreenProcessFlowEvent(evt);
				} else if (AEGWTILoginAppControllerConstants.NAME.equals(sourceWindow)) {
					appControlerProcessFlowEvent(evt);
				} else if (AEGWTInfoPresenterConstants.NAME.equals(sourceWindow)) {
					infoProcessFlowEvent(evt);
				} else if (AEGWTControllerPresenterConstants.NAME.equals(sourceWindow)) {
					controllerProcessFlowEvent(evt);
				} else if (CRONIOBusProjectBasePresenterConstants.PROJECT_PRESENTER.equals(sourceWindow)) {
					projectProcessFlowEvent(evt);
				}
			}
		}
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	protected void bind() {
		// nothing to do
	}

	@Override
	protected boolean processQuickAccessButton(AEGWTFlowEvent evt) {
		return false;
	}

	/**********************************************************************
	 *                           PRIVATE FUNCTIONS
	 **********************************************************************/
	private void controllerProcessFlowEvent(AEGWTFlowEvent evt) {

	}
	
	private void projectProcessFlowEvent(AEGWTFlowEvent evt) {
		String mainSectionId = evt.getSourceWidgetId();
		if (CRONIOBusProjectBasePresenterConstants.SECTION_TYPE_PROCESS.equals(mainSectionId)) {
			History.newItem(DSLAMBusDesktopHistoryNavigationConstants.TOKEN_PROCESS_PAGE);
		} else if (CRONIOBusProjectBasePresenterConstants.SECTION_TYPE_SCRIPT.equals(mainSectionId)) {
			History.newItem(DSLAMBusDesktopHistoryNavigationConstants.TOKEN_SCRIPTS_MANAGER);
		} else {
			History.newItem(DSLAMBusDesktopHistoryNavigationConstants.TOKEN_PROJECT_PAGE);
		}
	}

	private void infoProcessFlowEvent(AEGWTFlowEvent evt) {
		if (AEGWTInfoPresenterConstants.EVENT_HOME_BUTTON.equals(evt.getSourceWidget())) {
			History.newItem("");
		}
	}

	private void appControlerProcessFlowEvent(AEGWTFlowEvent evt) {
		if (AEGWTILoginAppControllerConstants.EVENT_INIT_APP.equals(evt.getSourceWidget())) {
			History.newItem(AEGWTHistoryNavigationConstants.TOKEN_INIT_APP);
		} else if (AEGWTILoginAppControllerConstants.EVENT_INIT_MAIN_SCREEN.equals(evt.getSourceWidget())) {
			History.newItem(AEGWTHistoryNavigationConstants.TOKEN_DASHBOARD);
		} else if (AEGWTILoginAppControllerConstants.EVENT_LOGIN.equals(evt.getSourceWidget())) {
			History.newItem(AEGWTHistoryNavigationConstants.TOKEN_LOGIN_APP);
		}
	}

	private void loginScreenProcessFlowEvent(AEGWTFlowEvent evt) {
		if (AEGWTLoginPresenterConstants.EVENT_LOGIN.equals(evt.getSourceWidget())) {
			History.newItem(AEGWTHistoryNavigationConstants.TOKEN_LOGIN);
		} else if (AEGWTLoginPresenterConstants.EVENT_REGISTER.equals(evt.getSourceWidget())) {
			History.newItem(AEGWTHistoryNavigationConstants.TOKEN_SIGN_UP);
		} else if (AEGWTLoginPresenterConstants.EVENT_FORGOT_PASSWORD.equals(evt.getSourceWidget())) {
			History.newItem(AEGWTHistoryNavigationConstants.TOKEN_PASSWORD_FORGOT);
		} else if (AEGWTLoginPresenterConstants.EVENT_RESET_PASSWORD.equals(evt.getSourceWidget())) {
			History.newItem(AEGWTHistoryNavigationConstants.TOKEN_PASSWORD_RESET);
		} else if (AEGWTLoginPresenterConstants.EVENT_SUBSCRIPTION_DONE.equals(evt.getSourceWidget())) {
			History.newItem(AEGWTHistoryNavigationConstants.TOKEN_INFO_MESSAGE);
		}
	}

	private boolean processHeaderEvent(AEGWTFlowEvent evt){
		boolean processed = false;
		if (AEGWTBasePresenterConstants.EVENT_LOGOUT.equals(evt.getSourceWidget())) {
			processed = true;
			History.newItem(AEGWTILoginAppControllerConstants.TOKEN_LOGOUT);
		} else if (AEGWTBasePresenterConstants.EVENT_HOME.equals(evt.getSourceWidget())) {
			processed = true;
			History.newItem("");
		} else if (AEGWTBasePresenterConstants.EVENT_USERNAME.equals(evt.getSourceWidget())) {
			processed = true;
			History.newItem(AEGWTHistoryNavigationConstants.TOKEN_DASHBOARD);
		} else if (AEGWTBasePresenterConstants.EVENT_FEEDBACK.equals(evt.getSourceWidget())) {
			processed = true;
			History.newItem(AEGWTHistoryNavigationConstants.TOKEN_FEEDBACK);
		}
		return processed;
	}

	
}
