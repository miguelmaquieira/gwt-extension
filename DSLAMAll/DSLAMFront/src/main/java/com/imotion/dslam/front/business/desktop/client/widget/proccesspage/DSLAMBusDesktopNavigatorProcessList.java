package com.imotion.dslam.front.business.desktop.client.widget.proccesspage;

import java.util.List;

import com.imotion.dslam.front.business.desktop.client.widget.navigator.DSLAMBusDesktopNavigatorList;
import com.imotion.dslam.front.business.desktop.client.widget.navigator.DSLAMBusDesktopNavigatorListElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class DSLAMBusDesktopNavigatorProcessList extends DSLAMBusDesktopNavigatorList {

	public DSLAMBusDesktopNavigatorProcessList() {
		super();
	}

	@Override
	protected DSLAMBusDesktopNavigatorListElement createListElement() {
		return new DSLAMBusDesktopNavigatorProcessListElement();
	}
	
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		if (data != null) {
			super.getElementListContainer().clear();
			List<AEMFTMetadataElement> elementDataList = data.getSortedElementList();
			for (AEMFTMetadataElement elementData : elementDataList) {
				addElement((AEMFTMetadataElementComposite) elementData);
			}
		}
	}

}
