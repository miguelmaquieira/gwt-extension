package com.imotion.dslam.bom;

import java.util.List;

import com.selene.arch.base.bom.AEMFTILoginData;

public interface CRONIOBOIUser extends AEMFTILoginData, CRONIOBOIUserDataConstants {

	CRONIOBOIPreferences getPreferences();
	
	void setPreferences(CRONIOBOIPreferences preferences);

	List<CRONIOBOIProject> getProjectList();

	void setProjectList(List<CRONIOBOIProject> projectList);

	void addProject(CRONIOBOIProject project);

	void removeProject(CRONIOBOIProject project);
	
}
