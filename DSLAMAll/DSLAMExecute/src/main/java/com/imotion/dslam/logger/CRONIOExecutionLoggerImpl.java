package com.imotion.dslam.logger;

import java.util.Date;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.conn.CRONIOIExecutionData;

public class CRONIOExecutionLoggerImpl implements CRONIOIExecutionLogger {

	private static CRONIOIExecutionLogger logger;
	
	private CRONIOExecutionLoggerImpl() {
		// TODO initialize Log4J and Atmosphere
	}
	
	public static CRONIOIExecutionLogger getLogger() {
		if (logger == null) {
			logger = new CRONIOExecutionLoggerImpl();
		}
		return logger;
	}

	@Override
	public void log(String connectionId, CRONIOBOINode node, CRONIOIExecutionData data) {
		Date	timeStamp 		= new Date();
		String	nodeIp			= node.getNodeIp();
		String	nodeName		= node.getNodeName();
		String	request			= data.getSourceCommand();
		String	response		= data.getResponse();
		String	prompt			= data.getPrompt();
		
		StringBuilder logValueSB = new StringBuilder();
		logValueSB.append(timeStamp);
		logValueSB.append("\t");
		logValueSB.append(nodeIp);
		logValueSB.append("\t");
		logValueSB.append(nodeName);
		logValueSB.append("\t");
		logValueSB.append(request);
		logValueSB.append("\n\n");
		logValueSB.append(response);
		logValueSB.append("\n\n");
		logValueSB.append(prompt);
		logValueSB.append("\n\n\n");
		
		String logValueStr = logValueSB.toString();
		//TODO: atmosphere and log4j
	}

}
