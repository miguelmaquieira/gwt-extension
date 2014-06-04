package com.imotion.dslam.front.business.desktop.client.widget.proccesspage;

import com.imotion.dslam.bom.DSLAMBOIFileDataConstants;
import com.imotion.dslam.bom.DSLAMBOIProcessDataConstants;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.widget.navigator.DSLAMBusDesktopNavigatorListElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class DSLAMBusDesktopNavigatorProcessListElement extends DSLAMBusDesktopNavigatorListElement {
	
	public DSLAMBusDesktopNavigatorProcessListElement() {
		super();
	}
	
	/**
	 * AEGWTICompositePanel
	 */
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		super.setData(data);
		Long	processId		= getElementController().getElementAsLong(DSLAMBOIProcessDataConstants.PROCESS_ID			, 	data);
		String	processName		= getElementController().getElementAsString(DSLAMBOIProcessDataConstants.PROCESS_NAME		, 	data);
		
		AEMFTMetadataElementComposite scriptData = getElementController().getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_SCRIPT, data);
		
		String scriptName		= getElementController().getElementAsString(DSLAMBOIFileDataConstants.FILE_NAME				, 	scriptData);
		
		setId(String.valueOf(processId));
		String text =	"<span class='" + DSLAMBusDesktopIStyleConstants.PROCESSES_VIEW_PROCESS_NAME + "'>" + processName + "</span>" +
						"<span> - </span>" +
						"<span class='" + DSLAMBusDesktopIStyleConstants.PROCESSES_VIEW_SCRIPT_NAME + "'>" + scriptName + "</span>";
		setActionText(text);
		setActionTooltip(processName);
		

	}

}
