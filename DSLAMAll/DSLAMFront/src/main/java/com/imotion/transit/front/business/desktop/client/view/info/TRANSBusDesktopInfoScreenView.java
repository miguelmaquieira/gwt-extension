package com.imotion.transit.front.business.desktop.client.view.info;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.imotion.transit.front.business.desktop.client.widget.TRANSBusNotLoggedScreenContainer;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.view.info.AEGWTDesktopInfoScreenView;

public class TRANSBusDesktopInfoScreenView extends AEGWTDesktopInfoScreenView {

	public TRANSBusDesktopInfoScreenView(boolean screenError) {
		super(screenError);
		setTitleStylename("fact-businessErrorPage-headerError");
		setDescriptionStylename("fact-businessErrorPage-descError");
	}

	@Override
	protected AEGWTCompositePanel createContaninerScreen(ComplexPanel content) {
		TRANSBusNotLoggedScreenContainer container = new TRANSBusNotLoggedScreenContainer();
		container.setContent(content);
		return container;
	}
}
