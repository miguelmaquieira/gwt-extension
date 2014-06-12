package com.imotion.dslam.front.business.desktop.client.presenter.process;

import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.mvp.AEGWTCompositePanelLoggedViewDisplay;

public interface CRONIOBusDesktopProcessDisplay extends AEGWTCompositePanelLoggedViewDisplay {
	void updateProcess(AEMFTMetadataElementComposite processData);
	void openProcessSection(String sectionId, AEMFTMetadataElementComposite processData);
}	
