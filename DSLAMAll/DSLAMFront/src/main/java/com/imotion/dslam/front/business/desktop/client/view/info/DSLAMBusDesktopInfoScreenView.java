package com.imotion.dslam.front.business.desktop.client.view.info;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.imotion.dslam.front.business.desktop.client.widget.DSLAMBusNotLoggedScreenContainer;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.view.info.AEGWTDesktopInfoScreenView;

public class DSLAMBusDesktopInfoScreenView extends AEGWTDesktopInfoScreenView {

	public DSLAMBusDesktopInfoScreenView(boolean screenError) {
		super(screenError);
		setTitleStylename("fact-businessErrorPage-headerError");
		setDescriptionStylename("fact-businessErrorPage-descError");
	}

	@Override
	protected AEGWTCompositePanel createContaninerScreen(ComplexPanel content) {
		DSLAMBusNotLoggedScreenContainer container = new DSLAMBusNotLoggedScreenContainer();
		container.setContent(content);
		return container;
	}
}
