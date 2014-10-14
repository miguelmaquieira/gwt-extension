package com.imotion.dslam.front.business.desktop.client.presenter.log;

import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusProjectBaseDisplay;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public interface CRONIOBusDesktopLogDisplay extends CRONIOBusProjectBaseDisplay  {
	void openLogSection(String sectionId, AEMFTMetadataElementComposite logData);
}	
