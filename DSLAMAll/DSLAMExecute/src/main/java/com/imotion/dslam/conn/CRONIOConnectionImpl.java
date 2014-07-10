package com.imotion.dslam.conn;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.logger.CRONIOIExecutionLogger;

public class CRONIOConnectionImpl extends CRONIOConnectionBase {

	public CRONIOConnectionImpl(long processId, CRONIOBOINode node, CRONIOIExecutionLogger	logger) {
		super(processId, node, logger);
		//TODO SSH etc
	}

	@Override
	protected CRONIOIExecutionData executeNativeCommand(String command) {
		//TODO
		String prompt	= "#prompt ";
		String response	= "reponse";
		CRONIOIExecutionData executionData = new CRONIOExecutionData(command, prompt, response);
		return executionData;
	}

}
