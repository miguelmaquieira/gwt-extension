package com.imotion.dslam.front.business.desktop.test.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootPanel;
import com.selene.arch.exe.gwt.client.presenter.base.AEGWTBaseAppController.WEB_VERSION;
import com.selene.arch.exe.gwt.mvp.SailorEntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestDesktopAppEntryPoint extends SailorEntryPoint {

	@Override
	protected void go() {
		RootPanel rootPanel = RootPanel.get();
		TestDesktopAppController controller = new TestDesktopAppController();
		controller.setPhoneGap(getPhoneGap());
		controller.initController(rootPanel, WEB_VERSION.DESKTOP);
		removeSplashIcon();
	}
	
	protected void removeSplashIcon() {
		Element body = RootPanel.getBodyElement();
		Element progressIcon = DOM.getElementById("progressIcon");
		if (progressIcon != null) {
			body.removeChild(progressIcon);
		}
	}
	
}
