package com.imotion.dslam.bom.data;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.bom.CRONIOBOIUser;
import com.selene.arch.base.bom.data.AEMFTLoginData;

public class CRONIOBOUser extends AEMFTLoginData implements CRONIOBOIUser {

	private static final long serialVersionUID = -2126490379134310701L;
	
	private Long					userId;
	private CRONIOBOIPreferences	preferences;

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
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, targetEntity=CRONIOBOPreferences.class)
	@JoinColumn(name=PREFERENCES_ID)
	@Override
	public CRONIOBOIPreferences getPreferences() {
		return preferences;
	}

	@Override
	public void setPreferences(CRONIOBOIPreferences preferences) {
		this.preferences = preferences;
	}

}
