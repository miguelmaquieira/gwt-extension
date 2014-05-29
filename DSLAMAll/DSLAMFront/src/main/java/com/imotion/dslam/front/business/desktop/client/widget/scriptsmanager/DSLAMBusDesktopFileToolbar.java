package com.imotion.dslam.front.business.desktop.client.widget.scriptsmanager;

import java.util.Date;

import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.front.business.desktop.client.widget.toolbar.DSLAMBusDesktopToolbar;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class DSLAMBusDesktopFileToolbar extends DSLAMBusDesktopToolbar {

	public DSLAMBusDesktopFileToolbar() {
		super();
	}
	
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		if (data != null) {
			Long	fileId	 	= getElementController().getElementAsLong(DSLAMBOIFile.FILE_ID			, data);
			String	filename 	= getElementController().getElementAsString(DSLAMBOIFile.FILE_NAME		, data);
			String	contentType	= getElementController().getElementAsString(DSLAMBOIFile.CONTENT_TYPE	, data);
			Date	lastSaved	= (Date) getElementController().getElementAsSerializable(DSLAMBOIFile.SAVED_TIME, data);
			
			String fileIdStr = String.valueOf(fileId);
			setId(fileIdStr);
			setLastSaved(lastSaved);
			setMainTitleText(filename);
			setSecondaryTitleText(contentType);
			setModified(false);
		}

	}

}
