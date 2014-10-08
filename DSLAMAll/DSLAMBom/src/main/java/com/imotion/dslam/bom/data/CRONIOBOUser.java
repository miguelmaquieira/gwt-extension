package com.imotion.dslam.bom.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.bom.CRONIOBOIUser;
import com.imotion.dslam.bom.CRONIOBOIProject;
import com.selene.arch.base.bom.data.AEMFTLoginData;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;

@Entity(name="User")
public class CRONIOBOUser extends AEMFTLoginData implements CRONIOBOIUser {

	private static final long serialVersionUID = -2126490379134310701L;
	
	private Long					userId;
	private CRONIOBOIPreferences	preferences;
	private List<CRONIOBOIProject>	projectList;

	@Id
	@SequenceGenerator(name = "UserIdGenerator", sequenceName = "UserSeq") //It only takes effect for databases providing identifier generators.
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "UserIdGenerator")	
	@Override
	public Long getUserId() {
		return userId;
	}

	@Override
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@ManyToOne(cascade={CascadeType.PERSIST}, targetEntity=CRONIOBOPreferences.class)
	@JoinColumn(name=PREFERENCES_ID)
	@Override
	public CRONIOBOIPreferences getPreferences() {
		return preferences;
	}

	@Override
	public void setPreferences(CRONIOBOIPreferences preferences) {
		this.preferences = preferences;
	}

	@ManyToMany(cascade={CascadeType.PERSIST}, targetEntity=CRONIOBOProject.class)
	@JoinTable(name=USER_PROJECTS, joinColumns = @JoinColumn(name = USER_ID), inverseJoinColumns = @JoinColumn(name = CRONIOBOIProject.PROJECT_ID))
	@Override
	public List<CRONIOBOIProject> getProjectList() {
		return projectList;
	}

	@Override
	public void setProjectList(List<CRONIOBOIProject> projectList) {
		this.projectList = projectList;
	}
	
	@Override
	public void addProject(CRONIOBOIProject project) {
		if (AEMFTCommonUtilsBase.isEmptyList(projectList)) {
			projectList = new ArrayList<>();
		}
		projectList.add(project);
	}
	
	@Override
	public void removeProject(CRONIOBOIProject project) {
		projectList.remove(project);
	}

}
