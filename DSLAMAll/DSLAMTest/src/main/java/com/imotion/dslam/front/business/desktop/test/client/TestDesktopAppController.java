package com.imotion.dslam.front.business.desktop.test.client;

import com.google.gwt.core.client.Callback;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopAppController;
import com.imotion.dslam.front.business.desktop.test.client.presenter.test.TestPresenter;
import com.imotion.dslam.front.business.desktop.test.client.view.test.TestDesktopScreenView;
import com.selene.arch.exe.gwt.client.utils.AEGWTStringUtils;
import com.selene.arch.exe.gwt.mvp.AEGWTIPresenter;
import com.selene.arch.exe.gwt.mvp.context.ContextRetriever;

public class TestDesktopAppController extends CRONIOBusDesktopAppController {

	
	@Override
	protected ContextRetriever instantiateContextRetriever(final String retrieverId, String arg) {
		return null;
	}

	@Override
	protected AEGWTIPresenter getPresenter(String[] tokenElements) {
		AEGWTIPresenter presenter = null;
		String token1 = tokenElements[0];
		if (AEGWTStringUtils.isEmptyString(token1)) {
			token1 = "test";
		}
		if ("test".equals(token1)) {
			presenter = new TestPresenter(new TestDesktopScreenView());
		}
		return presenter;
	}
	
	@Override
	protected void processTokens(String fullTokens) {
		navigateURI(fullTokens);
	}
	
	@Override
	public void loadJS(Callback<Void, Exception> callback) {
		callback.onSuccess(null);
	}
	
	@Override
	public String getApplicationId() {
		return "TEST";
	}
}
