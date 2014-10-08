package com.imotion.dslam.front.business.client.presenter.controller;

import com.imotion.dslam.front.business.client.CRONIOBusPresenterBaseConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.presenter.controller.AEGWTControllerDisplay;
import com.selene.arch.exe.gwt.client.presenter.controller.AEGWTControllerPresenter;
import com.selene.arch.exe.gwt.client.presenter.controller.AEGWTILoginAppControllerConstants;

public class CRONIOBusControllerPresenter<T extends AEGWTControllerDisplay> extends AEGWTControllerPresenter<T> {

	public CRONIOBusControllerPresenter(T view) {
		super(view);
	}

	@Override
	protected void viewAdded() {
		super.viewAdded();

		//SESSION DATA
		AEMFTMetadataElementComposite sessionData = getContextDataController().getElementAsComposite(AEGWTILoginAppControllerConstants.SESSION);
		getView().loadSessionData(sessionData);
	}

	@Override
	public String getApplicationId() {
		return CRONIOBusPresenterBaseConstants.APPLICATION_ID;
	}

	/****************************************************************************
	 *                          	AEGWTIPresenter
	 ****************************************************************************/
	@Override
	public String[] getInMapping() {
		return new String[] {
						AEGWTILoginAppControllerConstants.SESSION, 
						AEGWTILoginAppControllerConstants.SIGNUP_INCOMPLETE
						};
	}
}