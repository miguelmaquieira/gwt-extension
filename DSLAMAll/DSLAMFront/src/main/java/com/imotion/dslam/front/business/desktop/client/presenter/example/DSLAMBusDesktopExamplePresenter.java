package com.imotion.dslam.front.business.desktop.client.presenter.example;

import com.imotion.dslam.front.business.desktop.client.presenter.DSLAMBusBasePresenter;

public class DSLAMBusDesktopExamplePresenter extends DSLAMBusBasePresenter<DSLAMBusDesktopExampleDisplay> {

	public static final String NAME = "DSLAMDesktopExamplePresenter";

	public DSLAMBusDesktopExamplePresenter(DSLAMBusDesktopExampleDisplay view) {
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
