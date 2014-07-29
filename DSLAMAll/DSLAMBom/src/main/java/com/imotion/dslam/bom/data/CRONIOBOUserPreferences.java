package com.imotion.dslam.bom.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.bom.CRONIOBOIUserPreferences;

@Entity(name="UserPreferences")
public class CRONIOBOUserPreferences implements CRONIOBOIUserPreferences {

	private static final long serialVersionUID = -1253606334979222470L;
	
	private Long					userPreferencesId;
	private int					downTime;
	private Date					saveTime;
	private Date					creationTime;
	private CRONIOBOIPreferences	preferences;
	private Long					version; 

	public CRONIOBOUserPreferences() {}
	
	@Id
	@SequenceGenerator(name = "userPreferencesIdGenerator", sequenceName = "userPreferencesSeq") //It only takes effect for databases providing identifier generators.
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "userPreferencesIdGenerator")	
	@Override
	public Long getUserPreferencesId() {
		return userPreferencesId;
	}

	@Override
	public void setUserPreferencesId(Long userPreferencesId) {
		this.userPreferencesId = userPreferencesId;
	}
	
	@Override
	public int getDownTime() {
		return downTime;
	}

	@Override
	public void setDownTime(int downTime) {
		this.downTime = downTime;
	}

	@Override
	public void setSavedTime(Date saveTime) {
		this.saveTime = saveTime;
	}

	@Override
	public Date getSavedTime() {
		return saveTime;
	}

	@Override
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public Date getCreationTime() {
		return creationTime;
	}
	
	@OneToOne(mappedBy = CRONIOBOIPreferences.PREFERENCES_USER_PROPERTIES , targetEntity = CRONIOBOPreferences.class)
	@Override
	public CRONIOBOIPreferences getPreferences() {
		return preferences;
	}

	@Override
	public void setPreferences(CRONIOBOIPreferences preferences) {
		this.preferences = preferences;
	}

	@Version
	protected Long getVersion() {
		return version;
	}

	protected void setVersion(Long version) {
		this.version = version;
	}

}
