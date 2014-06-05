package com.imotion.dslam.front.business.desktop.client.widget.scriptsmanager;

import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.front.business.desktop.client.widget.navigator.DSLAMBusDesktopNavigatorList;
import com.imotion.dslam.front.business.desktop.client.widget.navigator.DSLAMBusDesktopNavigatorListElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class DSLAMBusDesktopNavigatorFileList extends DSLAMBusDesktopNavigatorList {

	public DSLAMBusDesktopNavigatorFileList() {
		super();
	}

	@Override
	protected DSLAMBusDesktopNavigatorListElement createListElement() {
		return new DSLAMBusDesktopNavigatorFileListElement();
	}

	@Override
	protected String getItemNameFromData(AEMFTMetadataElementComposite elementData) {
		String fileName = getElementController().getElementAsString(DSLAMBOIFile.FILE_NAME, elementData);
		return fileName;
	}

	@Override
	protected Long getItemIdAsLong(AEMFTMetadataElementComposite elementData) {
		Long fileId = getElementController().getElementAsLong(DSLAMBOIFile.FILE_ID, elementData);;
		return fileId;
	}

}
