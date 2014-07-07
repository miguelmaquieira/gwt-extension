package com.imotion.dslam.bom.data;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOIPreferences;

@Entity(name="Preferences")
public class CRONIOBOPreferences implements CRONIOBOIPreferences {
	
	private static final long serialVersionUID = 773641344063751034L;
	
	private Long 								preferencesId;
	private List<CRONIOBOIMachineProperties> 	machinePropertiesList;
	private Date 								savedTime;
	private Date 								creationTime;
	private Long								version;

	public CRONIOBOPreferences() {}
	
	@Id
	@SequenceGenerator(name = "PreferencesIdGenerator", sequenceName = "PreferencesSeq") //It only takes effect for databases providing identifier generators.
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PreferencesIdGenerator")
	@Override
	public Long getPreferencesId() {
		return preferencesId;
	}

	@Override
	public void setPreferencesId(Long preferencesId) {
		this.preferencesId = preferencesId;
	}

	@OneToMany(mappedBy=CRONIOBOIMachineProperties.PREFERENCES_ID, targetEntity=CRONIOBOMachineProperties.class, cascade ={CascadeType.PERSIST, CascadeType.REMOVE})
	@Override
	public List<CRONIOBOIMachineProperties> getMachinePropertiesList() {
		return machinePropertiesList;
	}
	
	@Override
	public void setMachinePropertiesList(List<CRONIOBOIMachineProperties> machinePropertiesList) {
		this.machinePropertiesList = machinePropertiesList;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Override
	public Date getSavedTime() {
		return savedTime;
	}
	
	@Override
	public void setSavedTime(Date savedTime) {
		this.savedTime = savedTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Override
	public Date getCreationTime() {
		return creationTime;
	}

	@Override
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@Version
	protected Long getVersion() {
		return version;
	}

	protected void setVersion(Long version) {
		this.version = version;
	}
}
