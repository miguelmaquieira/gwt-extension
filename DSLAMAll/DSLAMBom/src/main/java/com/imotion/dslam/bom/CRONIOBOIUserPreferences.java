package com.imotion.dslam.bom;

import java.io.Serializable;
import java.util.Date;

public interface CRONIOBOIUserPreferences extends Serializable, CRONIOBOIUserPreferencesDataConstants {

	Long getUserPreferencesId();

	void setUserPreferencesId(Long userpreferencesId);

	int getDownTime();

	void setDownTime(int downTime);
	
	CRONIOBOIPreferences getPreferences();
	
	void setPreferences(CRONIOBOIPreferences preferences);
	
	Date getSavedTime();

	void setSavedTime(Date savedTime);

	Date getCreationTime();

	void setCreationTime(Date creationTime);

}
