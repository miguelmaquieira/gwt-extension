package com.imotion.dslam.bom;

import java.util.List;

import com.selene.arch.base.bom.AEMFTILoginData;

public interface CRONIOBOIUser extends AEMFTILoginData, CRONIOBOIUserDataConstants {

	CRONIOBOIPreferences getPreferences();
	
	void setPreferences(CRONIOBOIPreferences preferences);

	List<DSLAMBOIProject> getProjectList();

	void setProjectList(List<DSLAMBOIProject> projectList);

	void addProject(DSLAMBOIProject project);

	void removeProject(DSLAMBOIProject project);
	
}
