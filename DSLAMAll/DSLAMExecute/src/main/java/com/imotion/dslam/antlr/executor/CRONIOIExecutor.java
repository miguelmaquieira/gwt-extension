package com.imotion.dslam.antlr.executor;

import java.util.List;

import com.imotion.dslam.bom.CRONIOBOILogNode;


public interface CRONIOIExecutor {
	
	List<CRONIOBOILogNode> execute(Long executionid,String nodeListName);
//	boolean isFinishExecution();
//	void setFinishExecution(boolean finishExecution);
	
}
