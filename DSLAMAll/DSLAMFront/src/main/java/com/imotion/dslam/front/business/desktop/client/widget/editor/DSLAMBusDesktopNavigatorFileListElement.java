package com.imotion.dslam.front.business.desktop.client.widget.editor;

import com.imotion.dslam.bom.DSLAMBOIFileDataConstants;
import com.imotion.dslam.front.business.desktop.client.widget.navigator.DSLAMBusDesktopNavigatorListElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class DSLAMBusDesktopNavigatorFileListElement extends DSLAMBusDesktopNavigatorListElement {
	
	public DSLAMBusDesktopNavigatorFileListElement() {
		super();
	}
	
	/**
	 * AEGWTICompositePanel
	 */
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		super.setData(data);
		Long	fileId		= getElementController().getElementAsLong(DSLAMBOIFileDataConstants.FILE_ID		, 	data);
		String	filename	= getElementController().getElementAsString(DSLAMBOIFileDataConstants.FILE_NAME	, 	data);
		
		setId(String.valueOf(fileId));
		setActionText(filename);
	}

}
