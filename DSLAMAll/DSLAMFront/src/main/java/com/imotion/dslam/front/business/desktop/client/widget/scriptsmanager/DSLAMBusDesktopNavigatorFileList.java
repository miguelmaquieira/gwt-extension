package com.imotion.dslam.front.business.desktop.client.widget.scriptsmanager;

import com.imotion.dslam.front.business.desktop.client.widget.navigator.DSLAMBusDesktopNavigatorList;
import com.imotion.dslam.front.business.desktop.client.widget.navigator.DSLAMBusDesktopNavigatorListElement;

public class DSLAMBusDesktopNavigatorFileList extends DSLAMBusDesktopNavigatorList {

	public DSLAMBusDesktopNavigatorFileList() {
		super();
	}

	@Override
	protected DSLAMBusDesktopNavigatorListElement createListElement() {
		return new DSLAMBusDesktopNavigatorFileListElement();
	}

}
