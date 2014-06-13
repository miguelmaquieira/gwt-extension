package com.imotion.dslam.front.business.desktop.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.imotion.dslam.front.business.desktop.client.CRONIODesktopIAppControllerConstants;
import com.imotion.dslam.front.business.desktop.client.widget.layout.CRONIOBusDesktopLayoutContainer;
import com.selene.arch.exe.gwt.mvp.AEGWTCompositePanelLoggedViewDisplay;

public abstract class CRONIOBusProjectBasePresenter<T extends AEGWTCompositePanelLoggedViewDisplay> extends DSLAMBusBasePresenter<T> {

	public CRONIOBusProjectBasePresenter(T view) {
		super(view);
	}
	
	@Override
	protected void addView(HasWidgets container) {
		CRONIOBusDesktopLayoutContainer layoutContainer = (CRONIOBusDesktopLayoutContainer) container;
		layoutContainer.setCurrentPresenter(this);
		layoutContainer.showLayout(CRONIOBusDesktopLayoutContainer.LAYOUT_PROJECT_ID);
		Widget viewAsWidget = getView().asWidget();
		layoutContainer.setLayoutContent(viewAsWidget);
	}
	
	@Override
	public String[] getInMapping() {
		return new String[] {CRONIODesktopIAppControllerConstants.PROJECTS_DATA};
	}
}
