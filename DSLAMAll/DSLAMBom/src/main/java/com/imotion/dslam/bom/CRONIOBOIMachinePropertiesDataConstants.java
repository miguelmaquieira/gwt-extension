package com.imotion.dslam.bom;

public interface CRONIOBOIMachinePropertiesDataConstants {
	
	//attributes
	String PREFERENCES	= "preferences";
	String MACHINE_NAME = "machineName";
	
	//relations
	String PREFERENCES_ID				= "preferencesId";
	String INIT_CONNECTION_SCRIPT_ID	= "initConnectionScriptId";
	String CLOSE_CONNECTION_SCRIPT_ID	= "closeConnectionScriptId"; 
	
	//PROTOCOL TYPES
	int PROTOCOL_TYPE_SSH		= 1;
	int PROTOCOL_TYPE_TELNET	= 2;


}
