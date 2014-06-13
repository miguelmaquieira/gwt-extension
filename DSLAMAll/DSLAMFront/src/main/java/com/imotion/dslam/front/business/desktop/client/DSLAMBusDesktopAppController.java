package com.imotion.dslam.front.business.desktop.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.Callback;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.imotion.dslam.business.service.DSLAMBUIProjectBusinessServiceConstants;
import com.imotion.dslam.front.business.client.DSLAMBusBaseAppController;
import com.imotion.dslam.front.business.client.DSLAMBusBaseAppControllerConstants;
import com.imotion.dslam.front.business.client.presenter.controller.DSLAMBusControllerDisplay;
import com.imotion.dslam.front.business.client.presenter.controller.DSLAMBusControllerPresenter;
import com.imotion.dslam.front.business.desktop.client.flow.DSLAMBusDesktopAppFlowController;
import com.imotion.dslam.front.business.desktop.client.presenter.process.CRONIOBusDesktopProcessPresenter;
import com.imotion.dslam.front.business.desktop.client.presenter.projectpage.DSLAMBusDesktopProjectPagePresenter;
import com.imotion.dslam.front.business.desktop.client.presenter.scriptsmanager.DSLAMBusDesktopScriptsManagerPresenter;
import com.imotion.dslam.front.business.desktop.client.view.controller.DSLAMBusDesktopControllerScreenView;
import com.imotion.dslam.front.business.desktop.client.view.info.DSLAMBusDesktopInfoScreenView;
import com.imotion.dslam.front.business.desktop.client.view.process.CRONIOBusDesktopProcessScreenView;
import com.imotion.dslam.front.business.desktop.client.view.projectpage.DSLAMBusDesktopProjectPageScreenView;
import com.imotion.dslam.front.business.desktop.client.view.scriptsmanager.DSLAMBusDesktopScriptsManagerScreenView;
import com.imotion.dslam.front.business.desktop.client.widget.layout.CRONIOBusDesktopLayoutContainer;
import com.imotion.dslam.front.business.desktop.client.widget.layout.CRONIOBusDesktopProjectsLayout;
import com.selene.arch.base.exe.bus.service.AEMFTIBusinessServiceIdConstant;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.common.AEGWTBaseAppContextMapper;
import com.selene.arch.exe.gwt.client.jsloaders.AEGWTJSLoader;
import com.selene.arch.exe.gwt.client.presenter.controller.AEGWTControllerPresenter;
import com.selene.arch.exe.gwt.client.presenter.error.AEGWTErrorDisplay;
import com.selene.arch.exe.gwt.client.presenter.flow.AEGWTIFlowController;
import com.selene.arch.exe.gwt.client.service.comm.AEGWTCommClientAsynchCallbackRequest;
import com.selene.arch.exe.gwt.client.utils.AEGWTStringUtils;
import com.selene.arch.exe.gwt.mvp.AEGWTIPresenter;
import com.selene.arch.exe.gwt.mvp.context.ContextRetriever;

public class DSLAMBusDesktopAppController extends DSLAMBusBaseAppController {

	@Override
	public void loadJS(Callback<Void, Exception> callback) {
		List<String> bootstrapLibraries = new ArrayList<String>();
		bootstrapLibraries.add(DSLAMBusBaseAppControllerConstants.JQUERY_JS_URL);
		bootstrapLibraries.add(DSLAMBusBaseAppControllerConstants.JQUERY_MOUSE_WHEEL_JS_URL);
		bootstrapLibraries.add(DSLAMBusBaseAppControllerConstants.JQUERY_PERFECT_SCROLLBAR_JS_URL);
		bootstrapLibraries.add(DSLAMBusBaseAppControllerConstants.BOOTSTRAP_JS_URL);
		bootstrapLibraries.add(DSLAMBusBaseAppControllerConstants.JQUERY_DATETIMEPICKER_JS);
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
		
		AEMFTMetadataElementComposite projectLayoutData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		projectLayoutData.addElement(CRONIOBusDesktopLayoutContainer.LAYOUT_PROJECT_ID, projectsData.cloneObject());
		setLayoutData(projectLayoutData);
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
		final String argProcessed = arg;

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

		if (DSLAMBusDesktopHistoryNavigationConstants.TOKEN_SCRIPTS_MANAGER.equals(token1) ) {
			presenter = new DSLAMBusDesktopScriptsManagerPresenter(new DSLAMBusDesktopScriptsManagerScreenView());
		} else if (DSLAMBusDesktopHistoryNavigationConstants.TOKEN_PROJECT_PAGE.equals(token1) ) {
			presenter = new DSLAMBusDesktopProjectPagePresenter(new DSLAMBusDesktopProjectPageScreenView());
		} else if (DSLAMBusDesktopHistoryNavigationConstants.TOKEN_PROCESS_PAGE.equals(token1) ) {
			presenter = new CRONIOBusDesktopProcessPresenter(new CRONIOBusDesktopProcessScreenView());
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
	protected AEGWTControllerPresenter<?> createPresenterController() {
		return new DSLAMBusControllerPresenter<DSLAMBusControllerDisplay>(new DSLAMBusDesktopControllerScreenView());
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

		CRONIOBusDesktopProjectsLayout projectsLayout = new CRONIOBusDesktopProjectsLayout();
		deckPanelLayout.addLayout(CRONIOBusDesktopLayoutContainer.LAYOUT_PROJECT_ID, projectsLayout);
	}

	//DELETE WHEN LOGIN WORKS!!
	@Override
	protected void validateSession(final AsyncCallback<Boolean> callback) {
		getClientServerConnection().executeComm(null, AEMFTIBusinessServiceIdConstant.CTE_MTF_AE_BUS_SERVICE_LOGIN_IS_VALID_SESION_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite result) {
				saveUserDataInClientStorage(result);
				callback.onSuccess(true);
			}

			@Override
			public void onError(Throwable caught) {
			}
		});
	}
	
	/**
	 * PRIVATE 
	 */
	
	private void setLayoutData(AEMFTMetadataElementComposite data) {
		CRONIOBusDesktopLayoutContainer layoutContainer = (CRONIOBusDesktopLayoutContainer) super.container;
		layoutContainer.setData(data);
	}

}
