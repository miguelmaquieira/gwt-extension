package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import java.util.List;

import com.imotion.dslam.bom.DSLAMBOIProcess;
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

	@Override
	protected String getItemNameFromData(AEMFTMetadataElementComposite elementData) {
		String processName = getElementController().getElementAsString(DSLAMBOIProcess.PROCESS_NAME, elementData);
		return processName;
	}

	@Override
	protected Long getItemIdAsLong(AEMFTMetadataElementComposite elementData) {
		Long processId = getElementController().getElementAsLong(DSLAMBOIProcess.PROCESS_ID, elementData);;
		return processId;
	}

}
