package com.imotion.dslam.front.business.desktop.client.presenter.preferences;

import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusPreferencesBaseDisplay;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public interface CRONIOBusDesktopPreferencesDisplay extends CRONIOBusPreferencesBaseDisplay {
	void updatePreferences(AEMFTMetadataElementComposite preferencesData);
	//void openProcessSection(String sectionId, AEMFTMetadataElementComposite processData);
}	
