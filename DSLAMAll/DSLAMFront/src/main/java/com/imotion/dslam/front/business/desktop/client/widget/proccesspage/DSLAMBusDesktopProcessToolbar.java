package com.imotion.dslam.front.business.desktop.client.widget.proccesspage;

import java.util.Date;

import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.front.business.desktop.client.widget.toolbar.DSLAMBusDesktopToolbar;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class DSLAMBusDesktopProcessToolbar extends DSLAMBusDesktopToolbar {

	public DSLAMBusDesktopProcessToolbar() {
		super();
	}
	
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		if (data != null) {
			Long	processId	 	= getElementController().getElementAsLong(DSLAMBOIProcess.PROCESS_ID				, data);
			String	processName 	= getElementController().getElementAsString(DSLAMBOIProcess.PROCESS_NAME			, data);
			Date	lastSaved		= (Date) getElementController().getElementAsSerializable(DSLAMBOIProcess.SAVED_TIME	, data);
			
			String processIdStr = String.valueOf(processId);
			setId(processIdStr);
			setLastSaved(lastSaved);
			setMainTitleText(processName);
			setModified(false);
		}
	}
}
