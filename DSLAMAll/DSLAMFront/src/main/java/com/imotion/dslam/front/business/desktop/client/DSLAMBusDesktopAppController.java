package com.imotion.dslam.front.business.desktop.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.Callback;
import com.imotion.dslam.front.business.client.DSLAMBusBaseAppController;
import com.imotion.dslam.front.business.client.DSLAMBusBaseAppControllerConstants;
import com.imotion.dslam.front.business.client.presenter.controller.DSLAMBusControllerDisplay;
import com.imotion.dslam.front.business.client.presenter.controller.DSLAMBusControllerPresenter;
import com.imotion.dslam.front.business.desktop.client.flow.DSLAMBusDesktopAppFlowController;
import com.imotion.dslam.front.business.desktop.client.presenter.projectpage.DSLAMBusDesktopProjectPagePresenter;
import com.imotion.dslam.front.business.desktop.client.presenter.scriptsmanager.DSLAMBusDesktopScriptsManagerPresenter;
import com.imotion.dslam.front.business.desktop.client.view.controller.DSLAMBusDesktopControllerScreenView;
import com.imotion.dslam.front.business.desktop.client.view.info.DSLAMBusDesktopInfoScreenView;
import com.imotion.dslam.front.business.desktop.client.view.projectpage.DSLAMBusDesktopProjectPageScreenView;
import com.imotion.dslam.front.business.desktop.client.view.scriptsmanager.DSLAMBusDesktopScriptsManagerScreenView;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.exe.gwt.client.common.AEGWTBaseAppContextMapper;
import com.selene.arch.exe.gwt.client.jsloaders.AEGWTJSLoader;
import com.selene.arch.exe.gwt.client.presenter.controller.AEGWTControllerPresenter;
import com.selene.arch.exe.gwt.client.presenter.error.AEGWTErrorDisplay;
import com.selene.arch.exe.gwt.client.presenter.flow.AEGWTIFlowController;
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
}
