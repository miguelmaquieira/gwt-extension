package com.imotion.transit.front.business.desktop.client.presenter.example;

import com.imotion.transit.front.business.desktop.client.presenter.TRANSBusBasePresenter;

public class TRANSBusDesktopExamplePresenter extends TRANSBusBasePresenter<TRANSBusDesktopExampleDisplay> {

	public static final String NAME = "TRANSDesktopExamplePresenter";

	public TRANSBusDesktopExamplePresenter(TRANSBusDesktopExampleDisplay view) {
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
