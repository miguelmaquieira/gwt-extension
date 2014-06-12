package com.imotion.dslam.front.business.desktop.client.widget.layout;

import com.google.gwt.user.client.ui.HasWidgets;

public interface CRONOIOBusDesktopIsLayoutContainer extends CRONOIOBusDesktopIsLayout, HasWidgets {

	void addLayout(String layoutId, CRONOIOBusDesktopIsLayout layout);

	void showLayout(String layoutId);

}
