package com.imotion.dslam.bom;

import com.selene.arch.base.bom.AEMFTILoginData;

public interface CRONIOBOIUser extends AEMFTILoginData, CRONIOBOIUserDataConstants {

	CRONIOBOIPreferences getPreferences();
	
	void setPreferences(CRONIOBOIPreferences preferences);
	
}
