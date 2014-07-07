package com.imotion.dslam.bom;

import java.io.Serializable;

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

	String getPrompt();

	void setPrompt(String prompt);

	String getUserPrompt();

	void setUserPrompt(String userPrompt);

	String getPasswordPrompt();

	void setPasswordPrompt(String passwordPrompt);

	String getEnablePrompt();

	void setEnablePrompt(String enablePrompt);

	String getEnablePasswordPrompt();

	void setEnablePasswordPrompt(String enablePasswordPrompt);

	long getInitConnectionTimeout();

	void setInitConnectionTimeout(long initConnectionTimeout);

	long getGeneralTimeout();

	void setGeneralTimeout(long generalTimeout);

	String getInitConnectionScript();

	void setInitConnectionScript(String initConnectionScript);

	String getEnableCommandScript();

	void setEnableCommandScript(String enableCommandScript);

	String getEnablePasswordScript();

	void setEnablePasswordScript(String enablePasswordScript);

	String getSetupTerminalScript();

	void setSetupTerminalScript(String setupTerminalScript);

	String getFinishConnectionScript();

	void setFinishConnectionScript(String finishConnectionScript);

	String getFinishedResponse();

	void setFinishedResponse(String finishedResponse);
	
	CRONIOBOIPreferences getPreferences();

	void setPreferences(CRONIOBOIPreferences preferences);

}
