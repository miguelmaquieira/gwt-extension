package com.imotion.dslam.front.business.desktop.client.widget.editor;

import com.imotion.dslam.bom.DSLAMBOIFileDataConstants;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.widget.navigator.DSLAMBusDesktopNavigatorListElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class DSLAMBusDesktopNavigatorFileListElement extends DSLAMBusDesktopNavigatorListElement {
	
	public DSLAMBusDesktopNavigatorFileListElement() {
		super();
		addStyleName(DSLAMBusDesktopIStyleConstants.FILE_LIST_ELEMENT);
	}
	
	/**
	 * AEGWTICompositePanel
	 */
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		super.setData(data);
		Long	fileId		= getElementController().getElementAsLong(DSLAMBOIFileDataConstants.FILE_ID		, 	data);
		String	filename	= getElementController().getElementAsString(DSLAMBOIFileDataConstants.FILE_NAME	, 	data);
		String	contentType	= getElementController().getElementAsString(DSLAMBOIFileDataConstants.CONTENT_TYPE	, 	data);
		
		setId(String.valueOf(fileId));
		String text =	"<span class='" + DSLAMBusDesktopIStyleConstants.EDITOR_VIEW_FILE_NAME + "'>" + filename + "</span>" +
						"<span> - </span>" +
						"<span class='" + DSLAMBusDesktopIStyleConstants.EDITOR_VIEW_CONTENT_TYPE + "'>" + contentType + "</span>";
		setActionText(text);
		setActionTooltip(filename);
	}

}
