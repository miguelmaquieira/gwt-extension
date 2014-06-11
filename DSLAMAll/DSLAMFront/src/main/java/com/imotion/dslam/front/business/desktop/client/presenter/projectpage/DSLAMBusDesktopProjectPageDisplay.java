package com.imotion.dslam.front.business.desktop.client.presenter.projectpage;

import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.mvp.AEGWTCompositePanelLoggedViewDisplay;

public interface DSLAMBusDesktopProjectPageDisplay extends AEGWTCompositePanelLoggedViewDisplay {

	void addProject(AEMFTMetadataElementComposite projectData);
	void updateProject(AEMFTMetadataElementComposite projectData);
	void removeProject(String projectId);
}	
