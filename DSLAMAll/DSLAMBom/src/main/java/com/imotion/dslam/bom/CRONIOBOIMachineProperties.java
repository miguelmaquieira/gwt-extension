package com.imotion.dslam.bom;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface CRONIOBOIMachineProperties extends Serializable, CRONIOBOIMachinePropertiesDataConstants {

	Long getMachinePropertiesId();

	void setMachinePropertiesId(Long machinePropertiesId);

	String getMachineName();

	void setMachineName(String machineName);

	String getMachineDescription();

	void setMachineDescription(String machineDescription);

	int getProtocolType();

	void setProtocolType(int protocolType);

	String getUsername();

	void setUsername(String user);

	String getPassword();

	void setPassword(String password);

	int getTimeout();

	void setTimeout(int timeout);
	
	String getPromptRegEx();

	void setPromptRegEx(String promptRegEx);

	DSLAMBOIFile getInitConnectionScript();

	void setInitConnectionScript(DSLAMBOIFile initConnectionScript);

	DSLAMBOIFile getCloseConnectionScript();

	void setCloseConnectionScript(DSLAMBOIFile finishConnectionScript);

	CRONIOBOIPreferences getPreferences();

	void setPreferences(CRONIOBOIPreferences preferences);
	
	void setSaveTime(Date savedTime);
	Date getSaveTime();
	
	void setCreationTime(Date createdTime);
	Date getCreationTime();

	List<DSLAMBOIVariable> getConnectionVariables();

	void setConnectionVariables(List<DSLAMBOIVariable> connectionVariables);

	String getUsernamePromptRegEx();

	void setUsernamePromptRegEx(String usernamePromptRegEx);

	String getPasswordPromptRegEx();

	void setPasswordPromptRegEx(String passwordPromptRegEx);

}
