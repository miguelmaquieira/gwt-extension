package com.imotion.dslam.front.business.desktop.client.presenter.studio;

import com.imotion.dslam.front.business.desktop.client.presenter.DSLAMBusBasePresenter;

public class DSLAMBusDesktopStudioPresenter extends DSLAMBusBasePresenter<DSLAMBusDesktopStudioDisplay> {

	public static final String NAME = "DSLAMBusDesktopStudioPresenter";

	public DSLAMBusDesktopStudioPresenter(DSLAMBusDesktopStudioDisplay view) {
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
