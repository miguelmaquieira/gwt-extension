package com.imotion.dslam.bom;

public interface CRONIOBOIMachinePropertiesDataConstants {
	
	//attributes
	String PREFERENCES				= "preferences";
	String MACHINE_ID 				= "machineId";
	String MACHINE_NAME 			= "machineName";
	String MACHINE_DESCRIPTION 		= "machineDescription";
	String PROTOCOL_TYPE			= "protocolType";
	String USERNAME					= "username";
	String PASSWORD					= "password";
	String TIMEOUT					= "timeout";
	String PROMPT_REGEX				= "promptRegEx";
	String USERNAME_PROMPT_REGEX	= "usernamePromptRegEx";
	String PASSWORD_PROMPT_REGEX	= "passwordPromptRegEx";
	String ROLLBACK_CONDITION_REGEX	= "rollbackConditionRegEx";
	String SAVED_TIME				= "savedTime";
	String CREATION_TIME			= "creationTime";
	
	//relations
	String PREFERENCES_ID				= "preferencesId";
	String INIT_CONNECTION_SCRIPT_ID	= "initConnectionScriptId";
	String CLOSE_CONNECTION_SCRIPT_ID	= "closeConnectionScriptId";
	
	//PROTOCOL TYPES
	int PROTOCOL_TYPE_SSH		= 1;
	int PROTOCOL_TYPE_TELNET	= 2;

	//Context
	String MACHINE_CONNECTION_SCRIPT 	= "MACHINE_CONNECTION_SCRIPT";
	String MACHINE_DISCONNECTION_SCRIPT = "MACHINE_DISCONNECTION_SCRIPT";
	String MACHINE_VARIABLES 			= "MACHINE_VARIABLES";
	String MACHINE_CONNECTION_CONFIG 	= "MACHINE_CONNECTION_CONFIG";
	
	String IS_MODIFIED 					= "IS_MODIFIED";
	
	//DEFAULTS
	String CONNECTION_SCRIPT_DEFAULT_NAME			= "Connection Script";
	String DISCONNECTION_SCRIPT_DEFAULT_NAME 		= "Disconnection Script";
	String CONNECTION_VARIABLE_LIST_DEFAULT_NAME 	= "Connection Variable List";
}
