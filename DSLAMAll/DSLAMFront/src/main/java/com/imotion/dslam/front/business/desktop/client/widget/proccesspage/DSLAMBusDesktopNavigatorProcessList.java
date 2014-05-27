package com.imotion.dslam.front.business.desktop.client.widget.proccesspage;

import com.imotion.dslam.front.business.desktop.client.widget.navigator.DSLAMBusDesktopNavigatorList;
import com.imotion.dslam.front.business.desktop.client.widget.navigator.DSLAMBusDesktopNavigatorListElement;

public class DSLAMBusDesktopNavigatorProcessList extends DSLAMBusDesktopNavigatorList {

	public DSLAMBusDesktopNavigatorProcessList() {
		super();
	}

	@Override
	protected DSLAMBusDesktopNavigatorListElement createListElement() {
		return new DSLAMBusDesktopNavigatorProcessListElement();
	}

}
