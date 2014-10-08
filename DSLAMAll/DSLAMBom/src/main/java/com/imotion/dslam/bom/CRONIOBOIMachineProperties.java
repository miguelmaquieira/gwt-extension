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

	CRONIOBOIFile getInitConnectionScript();

	void setInitConnectionScript(CRONIOBOIFile initConnectionScript);

	CRONIOBOIFile getCloseConnectionScript();

	void setCloseConnectionScript(CRONIOBOIFile finishConnectionScript);

	CRONIOBOIPreferences getPreferences();

	void setPreferences(CRONIOBOIPreferences preferences);
	
	void setSaveTime(Date savedTime);
	Date getSaveTime();
	
	void setCreationTime(Date createdTime);
	Date getCreationTime();

	List<CRONIOBOIVariable> getConnectionVariables();

	void setConnectionVariables(List<CRONIOBOIVariable> connectionVariables);

	String getUsernamePromptRegEx();

	void setUsernamePromptRegEx(String usernamePromptRegEx);

	String getPasswordPromptRegEx();

	void setPasswordPromptRegEx(String passwordPromptRegEx);
	
	String getRollbackConditionRegEx();

	void setRollbackConditionRegEx(String rollbackConditionRegEx);

}
