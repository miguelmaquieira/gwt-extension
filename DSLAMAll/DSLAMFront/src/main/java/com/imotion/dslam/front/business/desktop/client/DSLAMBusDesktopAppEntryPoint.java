package com.imotion.dslam.front.business.desktop.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.selene.arch.exe.gwt.client.presenter.base.AEGWTBaseAppController.WEB_VERSION;
import com.selene.arch.exe.gwt.mvp.SailorEntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DSLAMBusDesktopAppEntryPoint extends SailorEntryPoint {

	@Override
	protected void go() {
		RootLayoutPanel rootPanel = RootLayoutPanel.get();
		DSLAMBusDesktopAppController controller = new DSLAMBusDesktopAppController();
		controller.setPhoneGap(getPhoneGap());
		controller.initController(rootPanel, WEB_VERSION.DESKTOP);
		removeSplashIcon();
	}

	protected void removeSplashIcon() {
		Element body = RootPanel.getBodyElement();
		Element progressIcon = DOM.getElementById("progressIcon");
		body.removeChild(progressIcon);
	}
}
