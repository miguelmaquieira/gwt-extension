package com.imotion.dslam.front.business.desktop.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.bom.CRONIOBOIUserPreferences;
import com.imotion.dslam.business.service.CRONIOBUIExecuteBusinessServiceConstants;
import com.imotion.dslam.business.service.CRONIOBUILoginBusinessServiceConstants;
import com.imotion.dslam.business.service.CRONIOBUIPreferencesBusinessServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUIProjectBusinessServiceConstants;
import com.imotion.dslam.front.business.client.DSLAMBusBaseAppController;
import com.imotion.dslam.front.business.client.DSLAMBusBaseAppControllerConstants;
import com.imotion.dslam.front.business.client.DSLAMBusCommonConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.flow.DSLAMBusDesktopAppFlowController;
import com.imotion.dslam.front.business.desktop.client.presenter.execution.DSLAMBusDesktopExecutionPresenter;
import com.imotion.dslam.front.business.desktop.client.presenter.login.CRONIOBusDesktopLoginPresenter;
import com.imotion.dslam.front.business.desktop.client.presenter.preferences.CRONIOBusDesktopPreferencesPresenter;
import com.imotion.dslam.front.business.desktop.client.presenter.preferences.connection.CRONIOBusDesktopPreferencesConnectionPresenter;
import com.imotion.dslam.front.business.desktop.client.presenter.preferences.user.CRONIOBusDesktopPreferencesUserPresenter;
import com.imotion.dslam.front.business.desktop.client.presenter.process.CRONIOBusDesktopProcessPresenter;
import com.imotion.dslam.front.business.desktop.client.presenter.projectpage.DSLAMBusDesktopProjectPagePresenter;
import com.imotion.dslam.front.business.desktop.client.presenter.scriptsmanager.DSLAMBusDesktopScriptsManagerPresenter;
import com.imotion.dslam.front.business.desktop.client.view.execution.DSLAMBusDesktopExecutionScreenView;
import com.imotion.dslam.front.business.desktop.client.view.info.DSLAMBusDesktopInfoScreenView;
import com.imotion.dslam.front.business.desktop.client.view.login.CRONIOBusDesktopLoginScreenView;
import com.imotion.dslam.front.business.desktop.client.view.preferences.CRONIOBusDesktopPreferencesScreenView;
import com.imotion.dslam.front.business.desktop.client.view.preferences.connection.CRONIOBusDesktopPreferencesConnectionScreenView;
import com.imotion.dslam.front.business.desktop.client.view.preferences.user.CRONIOBusDesktopPreferencesUserScreenView;
import com.imotion.dslam.front.business.desktop.client.view.process.CRONIOBusDesktopProcessScreenView;
import com.imotion.dslam.front.business.desktop.client.view.projectpage.DSLAMBusDesktopProjectPageScreenView;
import com.imotion.dslam.front.business.desktop.client.view.scriptsmanager.DSLAMBusDesktopScriptsManagerScreenView;
import com.imotion.dslam.front.business.desktop.client.widget.layout.CRONIOBusDesktopEmptyLayout;
import com.imotion.dslam.front.business.desktop.client.widget.layout.CRONIOBusDesktopLayoutContainer;
import com.imotion.dslam.front.business.desktop.client.widget.layout.CRONIOBusDesktopPreferencesLayout;
import com.imotion.dslam.front.business.desktop.client.widget.layout.CRONIOBusDesktopProjectsLayout;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.common.AEGWTBaseAppContextMapper;
import com.selene.arch.exe.gwt.client.jsloaders.AEGWTJSLoader;
import com.selene.arch.exe.gwt.client.presenter.controller.AEGWTIControllerPresenter;
import com.selene.arch.exe.gwt.client.presenter.controller.AEGWTILoginAppControllerConstants;
import com.selene.arch.exe.gwt.client.presenter.error.AEGWTErrorDisplay;
import com.selene.arch.exe.gwt.client.presenter.flow.AEGWTIFlowController;
import com.selene.arch.exe.gwt.client.utils.AEGWTStringUtils;
import com.selene.arch.exe.gwt.mvp.AEGWTIPresenter;
import com.selene.arch.exe.gwt.mvp.context.ContextRetriever;

public class DSLAMBusDesktopAppController extends DSLAMBusBaseAppController {
	
	private DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private HandlerRegistration nativeHandlerRegistration;
	private Timer inactivityTimer ;

	@Override
	public void loadJS(Callback<Void, Exception> callback) {
		List<String> bootstrapLibraries = new ArrayList<String>();
		bootstrapLibraries.add(DSLAMBusBaseAppControllerConstants.JQUERY_JS_URL);
		bootstrapLibraries.add(DSLAMBusBaseAppControllerConstants.JQUERY_MOUSE_WHEEL_JS_URL);
		bootstrapLibraries.add(DSLAMBusBaseAppControllerConstants.JQUERY_PERFECT_SCROLLBAR_JS_URL);
		bootstrapLibraries.add(DSLAMBusBaseAppControllerConstants.BOOTSTRAP_JS_URL);
		bootstrapLibraries.add(DSLAMBusBaseAppControllerConstants.JQUERY_DATETIMEPICKER_JS);
		bootstrapLibraries.add(DSLAMBusBaseAppControllerConstants.BOOTSTRAP_FILESTYLE_JS);
		AEGWTJSLoader.fromUrl(bootstrapLibraries.iterator(), callback);
	}

	@Override
	public AEMFTMetadataElement getLoginDataRequested() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AEMFTMetadataElementComposite saveUserDataInClientStorage(AEMFTMetadataElementComposite result) {
		super.saveUserDataInClientStorage(result);

		AEMFTMetadataElementComposite projectsData = getElementDataController().getElementAsComposite(DSLAMBUIProjectBusinessServiceConstants.PROJECT_DATA_LIST, result);
		getContextDataController().setElement(CRONIODesktopIAppControllerConstants.PROJECTS_DATA, projectsData);

		AEMFTMetadataElementComposite preferencesData = getElementDataController().getElementAsComposite(CRONIOBUIPreferencesBusinessServiceConstants.PREFERENCES_DATA, result);
		getContextDataController().setElement(CRONIODesktopIAppControllerConstants.PREFERENCES_DATA, preferencesData);

		AEMFTMetadataElementComposite userData = getElementDataController().getElementAsComposite(CRONIOBUILoginBusinessServiceConstants.USER_DATA, result);
		getContextDataController().setElement(CRONIODesktopIAppControllerConstants.USER_DATA, userData);
		
		AEMFTMetadataElementComposite executionsData = getElementDataController().getElementAsComposite(CRONIOBUIExecuteBusinessServiceConstants.EXECUTIONS_DATA, result);
		projectsData.addElement(executionsData);
		
		AEMFTMetadataElementComposite layoutsData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		layoutsData.addElement(CRONIOBusDesktopLayoutContainer.LAYOUT_PROJECT_ID, projectsData.cloneObject());
		layoutsData.addElement(CRONIOBusDesktopLayoutContainer.LAYOUT_PREFERENCES_ID, preferencesData.cloneObject());
		layoutsData.addElement(CRONIOBusDesktopLayoutContainer.LAYOUT_EXECUTIONS_ID, executionsData.cloneObject());

		StringBuilder sbKey = new StringBuilder();
		sbKey.append(CRONIODesktopIAppControllerConstants.PREFERENCES_DATA);
		sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
		sbKey.append(CRONIOBOIPreferences.PREFERENCES_USER_PROPERTIES);
		sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
		sbKey.append(CRONIOBOIPreferences.USER_CONFIG);
		sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
		sbKey.append(CRONIOBOIUserPreferences.DOWNTIME);
		
		String 	downTimeKey = sbKey.toString();
		int		downTime 	= getContextDataController().getElementAsInt(downTimeKey);
		
		if (downTime <= 0) {
			downTime = CRONIODesktopIAppControllerConstants.DEFAULT_DOWNTIME;
		}
		
		setLayoutData(layoutsData);
		setTimeOut(downTime*60000);
		return getContextDataController().getContext();
	}

	/**
	 * PROTECTED
	 */
	@Override
	protected ContextRetriever instantiateContextRetriever(final String retrieverId, String arg) {
		//PAYPAL WORKAROUND
		if (arg.contains("?")) {
			String[] splitArgTokens = arg.split("\\?");
			arg = splitArgTokens[0];
		}

		ContextRetriever rc = null;
		return rc;
	}

	@Override
	protected AEGWTIPresenter getPresenter(String[] tokenElements) {
		AEGWTIPresenter presenter = null;
		String token1 = tokenElements[0];
		if (AEGWTStringUtils.isEmptyString(token1)) {
			token1 = DSLAMBusDesktopHistoryNavigationConstants.TOKEN_PROJECT_PAGE;
		}

		String 	sid 		= getContextDataController().getElementAsString(AEGWTILoginAppControllerConstants.SESSION_LAST_SESSION_ID);

		if (AEGWTStringUtils.isEmptyString(sid)) {
			presenter = new CRONIOBusDesktopLoginPresenter(new CRONIOBusDesktopLoginScreenView());
		} else {

			if (DSLAMBusDesktopHistoryNavigationConstants.TOKEN_SCRIPTS_MANAGER.equals(token1) ) {
				presenter = new DSLAMBusDesktopScriptsManagerPresenter(new DSLAMBusDesktopScriptsManagerScreenView());
			} else if (DSLAMBusDesktopHistoryNavigationConstants.TOKEN_PROJECT_PAGE.equals(token1) ) {
				presenter = new DSLAMBusDesktopProjectPagePresenter(new DSLAMBusDesktopProjectPageScreenView());
			} else if (DSLAMBusDesktopHistoryNavigationConstants.TOKEN_PROCESS_PAGE.equals(token1) ) {
				presenter = new CRONIOBusDesktopProcessPresenter(new CRONIOBusDesktopProcessScreenView());
			} else if (DSLAMBusDesktopHistoryNavigationConstants.TOKEN_EXECUTION.equals(token1) ) {
				presenter = new DSLAMBusDesktopExecutionPresenter(new DSLAMBusDesktopExecutionScreenView());
			} else if (DSLAMBusDesktopHistoryNavigationConstants.TOKEN_PREFERENCES.equals(token1) ) {
				presenter = new CRONIOBusDesktopPreferencesPresenter(new CRONIOBusDesktopPreferencesScreenView());
			} else if (DSLAMBusDesktopHistoryNavigationConstants.TOKEN_MACHINES.equals(token1) ) {
				presenter = new CRONIOBusDesktopPreferencesConnectionPresenter(new CRONIOBusDesktopPreferencesConnectionScreenView());
			} else if (DSLAMBusDesktopHistoryNavigationConstants.TOKEN_USER.equals(token1) ) {
				presenter = new CRONIOBusDesktopPreferencesUserPresenter(new CRONIOBusDesktopPreferencesUserScreenView());
			} 
		}
		return presenter;
	}

	@Override
	protected AEGWTIFlowController createFlowController() {
		return new DSLAMBusDesktopAppFlowController(this, getView());
	}

	@Override
	protected AEGWTErrorDisplay getErrorScreenView() {
		return new DSLAMBusDesktopInfoScreenView(true);
	}

	@Override
	protected AEGWTIControllerPresenter<?> createPresenterController() {
		return null;
	}

	@Override
	protected AEGWTBaseAppContextMapper createContextMapper() {
		return new DSLAMBusDesktopAppContextMapper(this);
	}

	@Override
	protected AEGWTIPresenter getNotFoundPresenter() {
		return null;
	}

	@Override
	protected void buildContainer(HasWidgets container) {
		CRONIOBusDesktopLayoutContainer deckPanelLayout = new CRONIOBusDesktopLayoutContainer();
		container.add(deckPanelLayout);
		super.container = deckPanelLayout;
		deckPanelLayout.setCurrentPresenter(this);

		CRONIOBusDesktopProjectsLayout projectsLayout = new CRONIOBusDesktopProjectsLayout();
		deckPanelLayout.addLayout(CRONIOBusDesktopLayoutContainer.LAYOUT_PROJECT_ID, projectsLayout);
		projectsLayout.postDisplay();

		CRONIOBusDesktopPreferencesLayout preferencesLayout = new CRONIOBusDesktopPreferencesLayout();
		deckPanelLayout.addLayout(CRONIOBusDesktopLayoutContainer.LAYOUT_PREFERENCES_ID, preferencesLayout);
		preferencesLayout.postDisplay();

		CRONIOBusDesktopEmptyLayout emptyLayout = new CRONIOBusDesktopEmptyLayout();
		deckPanelLayout.addLayout(CRONIOBusDesktopLayoutContainer.LAYOUT_EMPTY_ID, emptyLayout);
		emptyLayout.postDisplay();
	}

	/**
	 * PRIVATE 
	 */

	private void setLayoutData(AEMFTMetadataElementComposite data) {
		CRONIOBusDesktopLayoutContainer layoutContainer = (CRONIOBusDesktopLayoutContainer) super.container;
		layoutContainer.setData(data);
	}

	private void setTimeOut(final int timeoutInMillis) {
		
		inactivityTimer = new Timer() {

			@Override
			public void run() {
				nativeHandlerRegistration.removeHandler();
				inactivityTimer.cancel();
				Window.alert(TEXTS.session_timeout());
				logout();
			}
		};
		inactivityTimer.schedule(timeoutInMillis);

		NativePreviewHandler handler;
		handler = new NativePreviewHandler() {


			@Override
			public void onPreviewNativeEvent(NativePreviewEvent event) {
				inactivityTimer.schedule(timeoutInMillis);
			}
		};
		nativeHandlerRegistration = Event.addNativePreviewHandler(handler);
	}
}
