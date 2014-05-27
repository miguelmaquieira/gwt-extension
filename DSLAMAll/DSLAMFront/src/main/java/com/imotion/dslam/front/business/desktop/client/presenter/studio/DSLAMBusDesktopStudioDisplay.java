package com.imotion.dslam.front.business.desktop.client.presenter.studio;

import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.mvp.AEGWTCompositePanelLoggedViewDisplay;

public interface DSLAMBusDesktopStudioDisplay extends AEGWTCompositePanelLoggedViewDisplay {

	void addFile(AEMFTMetadataElementComposite fileData);

	void updateFile(AEMFTMetadataElementComposite fileData);
	
}	
