package com.imotion.dslam.conn;

import com.imotion.dslam.bom.CRONIOBOINode;

public class CRONIOConnectionDSLAM extends CRONIOConnectionBase {

	public CRONIOConnectionDSLAM(long processId, CRONIOBOINode node) {
		super(processId, node);
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
