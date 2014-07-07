package com.imotion.dslam.bom.data;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOIPreferences;

public class CRONIOBOMachineProperties implements CRONIOBOIMachineProperties {

	private static final long serialVersionUID = 8096683887004005093L;
	
	private Long					machinePropertiesId;
	private String					machineName;
	private String					machineDescription;
	private int					protocolType;
	private String 					username;
	private String 					password;
	private String 					prompt;
	private String 					userPrompt;
	private String 					passwordPrompt;
	private String 					enablePrompt;
	private String 					enablePasswordPrompt;
	private long					initConnectionTimeout;
	private long					generalTimeout;
	private String 					initConnectionScript;
	private String 					enableCommandScript;
	private String 					enablePasswordScript;
	private String					setupTerminalScript;
	private String 					finishConnectionScript;
	private String 					finishedResponse;
	private Date					saveTime;
	private Date					creationTime;
	private CRONIOBOIPreferences	preferences;
	private Long					version;

	public CRONIOBOMachineProperties() {}
	
	@Id
	@SequenceGenerator(name = "machinePropertiesIdGenerator", sequenceName = "machinePropertiesSeq") //It only takes effect for databases providing identifier generators.
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "machinePropertiesIdGenerator")	
	@Override
	public Long getMachinePropertiesId() {
		return machinePropertiesId;
	}

	@Override
	public void setMachinePropertiesId(Long machinePropertiesId) {
		this.machinePropertiesId = machinePropertiesId;
	}
	
	@Override
	public String getMachineName() {
		return machineName;
	}

	@Override
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	@Override
	public String getMachineDescription() {
		return machineDescription;
	}

	@Override
	public void setMachineDescription(String machineDescription) {
		this.machineDescription = machineDescription;
	}

	@Override
	public int getProtocolType() {
		return protocolType;
	}

	@Override
	public void setProtocolType(int protocolType) {
		this.protocolType = protocolType;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPrompt() {
		return prompt;
	}

	@Override
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	@Override
	public String getUserPrompt() {
		return userPrompt;
	}

	@Override
	public void setUserPrompt(String userPrompt) {
		this.userPrompt = userPrompt;
	}

	@Override
	public String getPasswordPrompt() {
		return passwordPrompt;
	}

	@Override
	public void setPasswordPrompt(String passwordPrompt) {
		this.passwordPrompt = passwordPrompt;
	}

	@Override
	public String getEnablePrompt() {
		return enablePrompt;
	}

	@Override
	public void setEnablePrompt(String enablePrompt) {
		this.enablePrompt = enablePrompt;
	}

	@Override
	public String getEnablePasswordPrompt() {
		return enablePasswordPrompt;
	}

	@Override
	public void setEnablePasswordPrompt(String enablePasswordPrompt) {
		this.enablePasswordPrompt = enablePasswordPrompt;
	}

	@Override
	public long getInitConnectionTimeout() {
		return initConnectionTimeout;
	}

	@Override
	public void setInitConnectionTimeout(long initConnectionTimeout) {
		this.initConnectionTimeout = initConnectionTimeout;
	}

	@Override
	public long getGeneralTimeout() {
		return generalTimeout;
	}

	@Override
	public void setGeneralTimeout(long generalTimeout) {
		this.generalTimeout = generalTimeout;
	}

	@Lob 
	@Column(columnDefinition="TEXT")
	@Override
	public String getInitConnectionScript() {
		return initConnectionScript;
	}

	@Override
	public void setInitConnectionScript(String initConnectionScript) {
		this.initConnectionScript = initConnectionScript;
	}

	@Lob 
	@Column(columnDefinition="TEXT")
	@Override
	public String getEnableCommandScript() {
		return enableCommandScript;
	}

	@Override
	public void setEnableCommandScript(String enableCommandScript) {
		this.enableCommandScript = enableCommandScript;
	}

	@Lob 
	@Column(columnDefinition="TEXT")
	@Override
	public String getEnablePasswordScript() {
		return enablePasswordScript;
	}

	@Override
	public void setEnablePasswordScript(String enablePasswordScript) {
		this.enablePasswordScript = enablePasswordScript;
	}

	@Lob 
	@Column(columnDefinition="TEXT")
	@Override
	public String getSetupTerminalScript() {
		return setupTerminalScript;
	}

	@Override
	public void setSetupTerminalScript(String setupTerminalScript) {
		this.setupTerminalScript = setupTerminalScript;
	}

	@Lob 
	@Column(columnDefinition="TEXT")
	@Override
	public String getFinishConnectionScript() {
		return finishConnectionScript;
	}

	@Override
	public void setFinishConnectionScript(String finishConnectionScript) {
		this.finishConnectionScript = finishConnectionScript;
	}

	@Override
	public String getFinishedResponse() {
		return finishedResponse;
	}

	@Override
	public void setFinishedResponse(String finishedResponse) {
		this.finishedResponse = finishedResponse;
	}

	@OneToOne(cascade ={CascadeType.PERSIST, CascadeType.REMOVE}, targetEntity=CRONIOBOPreferences.class)
	@JoinColumn(name=PREFERENCES_ID)
	@Override
	public CRONIOBOIPreferences getPreferences() {
		return preferences;
	}

	@Override
	public void setPreferences(CRONIOBOIPreferences preferences) {
		this.preferences = preferences;
	}

	@Override
	public void setSaveTime(Date saveTime) {
		this.saveTime = saveTime;
	}

	@Override
	public Date getSaveTime() {
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

	@Version
	protected Long getVersion() {
		return version;
	}

	protected void setVersion(Long version) {
		this.version = version;
	}

}
