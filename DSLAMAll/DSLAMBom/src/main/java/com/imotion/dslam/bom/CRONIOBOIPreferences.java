package com.imotion.dslam.bom;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface CRONIOBOIPreferences extends Serializable, CRONIOBOIPreferencesDataConstants {

	Long getPreferencesId();

	void setPreferencesId(Long preferencesId);

	List<CRONIOBOIMachineProperties> getMachinePropertiesList();

	void setMachinePropertiesList(List<CRONIOBOIMachineProperties> machinePropertiesList);

	CRONIOBOIUserPreferences getUserPreferences();
	
	void setUserPreferences(CRONIOBOIUserPreferences userPreferences);
	
	Date getSavedTime();

	void setSavedTime(Date savedTime);

	Date getCreationTime();

	void setCreationTime(Date creationTime);

	void addMachineProperties(CRONIOBOIMachineProperties machineProperties);

	void removeMachineProperties(CRONIOBOIMachineProperties machineProperties);

}
