package com.imotion.dslam.front.business.desktop.client;

import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.selene.arch.exe.gwt.client.presenter.base.AEGWTBaseAppController.WEB_VERSION;
import com.selene.arch.exe.gwt.mvp.SailorEntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CRONIOBusDesktopAppEntryPoint extends SailorEntryPoint {

	@Override
	protected void go() {
		RootLayoutPanel rootPanel = RootLayoutPanel.get();
		CRONIOBusDesktopAppController controller = new CRONIOBusDesktopAppController();
		controller.initController(rootPanel, WEB_VERSION.DESKTOP);
	}
	
	@Override
	protected void loadApis(Runnable runnable) {
		runnable.run();
	}

}
