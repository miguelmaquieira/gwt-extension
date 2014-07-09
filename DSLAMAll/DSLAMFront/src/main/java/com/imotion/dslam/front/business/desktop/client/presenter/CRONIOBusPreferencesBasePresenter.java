package com.imotion.dslam.front.business.desktop.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.imotion.dslam.front.business.desktop.client.widget.layout.CRONIOBusDesktopLayoutContainer;
import com.imotion.dslam.front.business.desktop.client.widget.layout.CRONIOBusDesktopPreferencesLayout;

public abstract class CRONIOBusPreferencesBasePresenter<T extends CRONIOBusPreferencesBaseDisplay> extends DSLAMBusBasePresenter<T> implements CRONIOBusPreferencesBasePresenterConstants {

	private CRONIOBusDesktopPreferencesLayout preferencesLayout;

	public CRONIOBusPreferencesBasePresenter(T view) {
		super(view);
	}

	/**
	 * PROTECTED
	 */

	@Override
	protected void addView(HasWidgets container) {
		CRONIOBusDesktopLayoutContainer layoutContainer = (CRONIOBusDesktopLayoutContainer) container;
		layoutContainer.setCurrentPresenter(this);
		layoutContainer.showLayout(CRONIOBusDesktopLayoutContainer.LAYOUT_PREFERENCES_ID);
		Widget viewAsWidget = getView().asWidget();
		layoutContainer.setLayoutContent(viewAsWidget);
		preferencesLayout = (CRONIOBusDesktopPreferencesLayout) layoutContainer.getCurrentLayout();
	}
}
