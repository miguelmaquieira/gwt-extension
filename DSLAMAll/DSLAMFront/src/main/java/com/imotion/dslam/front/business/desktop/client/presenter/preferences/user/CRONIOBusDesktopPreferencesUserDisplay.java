package com.imotion.dslam.front.business.desktop.client.presenter.preferences.user;

import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusPreferencesBaseDisplay;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public interface CRONIOBusDesktopPreferencesUserDisplay extends CRONIOBusPreferencesBaseDisplay {
	void updatePreferences(AEMFTMetadataElementComposite preferencesData);
	void openUserSection(String sectionId, AEMFTMetadataElementComposite finalSectionData);
}	
