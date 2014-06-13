package com.imotion.dslam.front.business.desktop.client.widget.layout;

import com.google.gwt.user.client.ui.HasWidgets;
import com.selene.arch.exe.gwt.client.presenter.base.AEGWTBasePresenter;

public interface CRONOIOBusDesktopIsLayoutContainer extends CRONIOBusDesktopIsLayout, HasWidgets {

	void addLayout(String layoutId, CRONIOBusDesktopIsLayout layout);

	void showLayout(String layoutId);

	void setCurrentPresenter(AEGWTBasePresenter<?> currentPresenter);

}
