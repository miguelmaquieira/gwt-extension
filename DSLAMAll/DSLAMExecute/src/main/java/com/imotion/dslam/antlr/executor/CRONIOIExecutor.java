package com.imotion.dslam.antlr.executor;

import com.imotion.dslam.bom.DSLAMBOIProject;

public interface CRONIOIExecutor {
	
	String VARIABLE_PREFFIX_PROCESS 	= "#";
	
	void execute(DSLAMBOIProject project, String executionId);
	
}
