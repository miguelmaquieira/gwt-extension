package com.imotion.dslam.logger;

import java.io.IOException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.conn.CRONIOIExecutionData;

public class CRONIOExecutionLoggerImpl implements CRONIOIExecutionLogger {

	private Logger 			slf4jLogger;

	public CRONIOExecutionLoggerImpl(DSLAMBOIProject project) throws IOException {
		// TODO initialize  Atmosphere
		
		String targetLog = "logs/" + project.getProjectName() + ".log";
		slf4jLogger = Logger.getLogger(CRONIOExecutionLoggerImpl.class);
		FileAppender apndr = new FileAppender(new PatternLayout("%d %-5p %m%n"), targetLog, true);    
		slf4jLogger.addAppender(apndr);
		slf4jLogger.setLevel((Level) Level.ALL);
	}

	@Override
	public void log(String connectionId, CRONIOBOINode node, CRONIOIExecutionData data) {
		String	nodeIp			= node.getNodeIp();
		String	nodeName		= node.getNodeName();
		String	request			= data.getSourceCommand();
		String	response		= data.getResponse();
		String	prompt			= data.getPrompt();

		StringBuilder logValueSB = new StringBuilder();
		logValueSB.append(connectionId);
		logValueSB.append("\t");
		logValueSB.append(nodeIp);
		logValueSB.append("\t");
		logValueSB.append(nodeName);
		logValueSB.append("\t");
		logValueSB.append(request);
		logValueSB.append("\n");
		logValueSB.append(response);
		logValueSB.append("\n");
		logValueSB.append(prompt);
		logValueSB.append("\n");

		String logValueStr = logValueSB.toString();
		//TODO: atmosphere
		slf4jLogger.debug(logValueStr);
	}

}
