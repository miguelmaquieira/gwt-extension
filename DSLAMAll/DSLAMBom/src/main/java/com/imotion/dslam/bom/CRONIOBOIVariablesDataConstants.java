package com.imotion.dslam.bom;

public interface CRONIOBOIVariablesDataConstants {
	
	//ATTRIBUTES
	String 	VARIABLE_NAME 	= "variableName";
	String 	VARIABLE_VALUE	= "variableValue";
	String 	VARIABLE_SCOPE	= "variableScope";
	String 	VARIABLE_TYPE	= "variableType";
	
	//SCOPES
	int		VARIABLE_SCOPE_CONNECTION	= 3;
	int		VARIABLE_SCOPE_NODE			= 2;
	int		VARIABLE_SCOPE_EXTERNAL		= 1;
	int		VARIABLE_SCOPE_PROCESS		= 0;
	
	//TYPES
	int		VARIABLE_TYPE_NUMERIC		= 1;
	int		VARIABLE_TYPE_TEXT			= 2;

	//CONTEXT
	String EDIT_MODE					=	"EditMode";
	String SAVE_MODE					=	"SaveMode";
	String VARIABLE_DATA				= "VARIABLE_DATA";
}