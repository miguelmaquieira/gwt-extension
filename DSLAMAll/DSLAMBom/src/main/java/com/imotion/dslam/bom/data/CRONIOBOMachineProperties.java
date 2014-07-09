package com.imotion.dslam.bom.data;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIVariable;

@Entity(name="MachineProperties")
public class CRONIOBOMachineProperties implements CRONIOBOIMachineProperties {

	private static final long serialVersionUID = 8096683887004005093L;
	
	private Long					machinePropertiesId;
	private String					machineName;
	private String					machineDescription;
	private int					protocolType;
	private String 					username;
	private String 					password;
	private int					timeout;
	private Date					saveTime;
	private Date					creationTime;
	private List<DSLAMBOIVariable>	connectionVariables;
	private DSLAMBOIFile 			initConnectionScript;
	private DSLAMBOIFile 			closeConnectionScript;
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
	public int getTimeout() {
		return timeout;
	}

	@Override
	public void setTimeout(int timeout) {
		this.timeout = timeout;
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
	
	@ManyToOne(cascade ={CascadeType.PERSIST, CascadeType.REMOVE}, targetEntity=DSLAMBOFile.class)
	@JoinColumn(name=INIT_CONNECTION_SCRIPT_ID)
	@Override
	public DSLAMBOIFile getInitConnectionScript() {
		return initConnectionScript;
	}

	@Override
	public void setInitConnectionScript(DSLAMBOIFile initConnectionScript) {
		this.initConnectionScript = initConnectionScript;
	}

	@ManyToOne(cascade ={CascadeType.PERSIST, CascadeType.REMOVE}, targetEntity=DSLAMBOFile.class)
	@JoinColumn(name=CLOSE_CONNECTION_SCRIPT_ID)
	@Override
	public DSLAMBOIFile getCloseConnectionScript() {
		return closeConnectionScript;
	}

	@Override
	public void setCloseConnectionScript(DSLAMBOIFile finishConnectionScript) {
		this.closeConnectionScript = finishConnectionScript;
	}

	@ManyToOne(targetEntity=CRONIOBOPreferences.class)
	@JoinColumn(name=PREFERENCES_ID)
	@Override
	public CRONIOBOIPreferences getPreferences() {
		return preferences;
	}

	@Override
	public void setPreferences(CRONIOBOIPreferences preferences) {
		this.preferences = preferences;
		preferences.getMachinePropertiesList().add(this);
	}
	
	@ElementCollection(targetClass=DSLAMBOVariable.class)
	@Override
	public List<DSLAMBOIVariable> getConnectionVariables() {
		return connectionVariables;
	}

	@Override
	public void setConnectionVariables(List<DSLAMBOIVariable> connectionVariables) {
		this.connectionVariables = connectionVariables;
	}

	@Version
	protected Long getVersion() {
		return version;
	}

	protected void setVersion(Long version) {
		this.version = version;
	}

}
