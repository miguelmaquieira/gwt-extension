package com.imotion.dslam.logger;

import java.io.IOException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.DefaultBroadcasterFactory;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.conn.CRONIOIExecutionData;
import com.imotion.dslam.logger.amosphere.CRONIOLoggerEvent;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;

public class CRONIOExecutionLoggerImpl implements CRONIOIExecutionLogger {

	private static Logger	log4jLogger;;
	private String projectName	;
	
	public CRONIOExecutionLoggerImpl(DSLAMBOIProject project) throws IOException {
		projectName = project.getProjectName();
		Logger logger = getLog4JLogger();
		String targetLog = "logs/" + projectName + ".log";
		if (logger.getAppender(targetLog) == null) {
			PatternLayout layout = new PatternLayout("%d %-5p %m%n");
			FileAppender apndr = new FileAppender(layout, targetLog, true);
			apndr.setName(targetLog);
			logger.addAppender(apndr);
		}
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
		
		//Log file
		log4jLogger.debug(logValueStr);
		
		//ClientConsole
		CRONIOLoggerEvent loggerEvent = new CRONIOLoggerEvent();
		loggerEvent.setConnectionId(connectionId);
		loggerEvent.setNodeName(nodeName);
		loggerEvent.setRequest(request);
		loggerEvent.setResponse(response);
		loggerEvent.setPrompt(prompt);
		loggerEvent.setFullTrace(logValueStr);
		getClientComm(connectionId).broadcast(loggerEvent);
	}
	
	/**
	 * PRIVATE
	 */
	
	private Logger getLog4JLogger() {
		if (log4jLogger == null) {
			log4jLogger = Logger.getLogger(CRONIOExecutionLoggerImpl.class);
			log4jLogger.setLevel((Level) Level.ALL);
		}
		return log4jLogger;
	}
	
	private Broadcaster getClientComm(String connectionId) {
		String commId = connectionId;
		if (AEMFTCommonUtilsBase.isEmptyString(connectionId)) {
			commId = projectName;
		}
		Broadcaster b = DefaultBroadcasterFactory.getDefault().lookup(commId, true);
		return b;
	}

}
