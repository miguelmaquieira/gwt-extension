package com.imotion.dslam.front.business.desktop.client.presenter.processpage;

import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.mvp.AEGWTCompositePanelLoggedViewDisplay;

public interface DSLAMBusDesktopProcessPageDisplay extends AEGWTCompositePanelLoggedViewDisplay {

	void addProcess(AEMFTMetadataElementComposite processData);
	void updateProcess(AEMFTMetadataElementComposite processData);
	void removeProcess(String processId);
}	
