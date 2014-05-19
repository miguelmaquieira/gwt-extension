package com.imotion.transit.front.business.desktop.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.Callback;
import com.imotion.transit.front.business.client.TRANSBusBaseAppController;
import com.imotion.transit.front.business.client.presenter.controller.TRANSBusControllerDisplay;
import com.imotion.transit.front.business.client.presenter.controller.TRANSBusControllerPresenter;
import com.imotion.transit.front.business.desktop.client.flow.TRANSBusDesktopAppFlowController;
import com.imotion.transit.front.business.desktop.client.presenter.example.TRANSBusDesktopExamplePresenter;
import com.imotion.transit.front.business.desktop.client.view.controller.TRANSBusDesktopControllerScreenView;
import com.imotion.transit.front.business.desktop.client.view.example.TRANSBusDesktopExampleScreenView;
import com.imotion.transit.front.business.desktop.client.view.info.TRANSBusDesktopInfoScreenView;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.exe.gwt.client.common.AEGWTBaseAppContextMapper;
import com.selene.arch.exe.gwt.client.jsloaders.AEGWTIJSLoaderConstants;
import com.selene.arch.exe.gwt.client.jsloaders.AEGWTJSLoader;
import com.selene.arch.exe.gwt.client.presenter.controller.AEGWTControllerPresenter;
import com.selene.arch.exe.gwt.client.presenter.error.AEGWTErrorDisplay;
import com.selene.arch.exe.gwt.client.presenter.flow.AEGWTIFlowController;
import com.selene.arch.exe.gwt.client.utils.AEGWTStringUtils;
import com.selene.arch.exe.gwt.mvp.AEGWTIPresenter;
import com.selene.arch.exe.gwt.mvp.context.ContextRetriever;

public class TRANSBusDesktopAppController extends TRANSBusBaseAppController {
	
	@Override
	public void loadJS(Callback<Void, Exception> callback) {
		List<String> bootstrapLibraries = new ArrayList<String>();
		bootstrapLibraries.add(AEGWTIJSLoaderConstants.JQUERY_JS_URL);
		bootstrapLibraries.add(AEGWTIJSLoaderConstants.BOOTSTRAP_JS_URL);
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
			token1 = "test";
		}
		
		if (TRANSBusDesktopHistoryNavigationConstants.TOKEN_EXAMPLE.equals(token1) ) {
			presenter = new TRANSBusDesktopExamplePresenter(new TRANSBusDesktopExampleScreenView());
		}
		return presenter;
	}
	
	@Override
	protected AEGWTIFlowController createFlowController() {
		return new TRANSBusDesktopAppFlowController(this, getView());
	}
	
	@Override
	protected AEGWTErrorDisplay getErrorScreenView() {
		return new TRANSBusDesktopInfoScreenView(true);
	}

	@Override
	protected AEGWTControllerPresenter<?> createPresenterController() {
		return new TRANSBusControllerPresenter<TRANSBusControllerDisplay>(new TRANSBusDesktopControllerScreenView());
	}

	@Override
	protected AEGWTBaseAppContextMapper createContextMapper() {
		return new TRANSBusDesktopAppContextMapper(this);
	}

	@Override
	protected AEGWTIPresenter getNotFoundPresenter() {
		return null;
	}
}
