package com.imotion.dslam.front.business.desktop.client.presenter.process;

import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusProjectBaseDisplay;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public interface CRONIOBusDesktopProcessDisplay extends CRONIOBusProjectBaseDisplay {
	void updateProcess(AEMFTMetadataElementComposite processData);
	void openProcessSection(String sectionId, AEMFTMetadataElementComposite processData);
}	
