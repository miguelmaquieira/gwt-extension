package com.imotion.dslam.front.business.desktop.client.presenter.processpage;

import com.imotion.dslam.front.business.desktop.client.presenter.DSLAMBusBasePresenter;

public class DSLAMBusDesktopProcessPagePresenter extends DSLAMBusBasePresenter<DSLAMBusDesktopProcessPageDisplay> {

	public static final String NAME = "DSLAMBusDesktopProcessPagePresenter";

	public DSLAMBusDesktopProcessPagePresenter(DSLAMBusDesktopProcessPageDisplay view) {
		super(view);
	}

	@Override
	public void bind() {
	}

	@Override
	public String getName() {
		return NAME;
	}

}
