package com.imotion.dslam.logger;

import java.io.IOException;
import java.util.Date;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.DefaultBroadcasterFactory;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.CRONIOBOIProcess;
import com.imotion.dslam.bom.CRONIOBOIProject;
import com.imotion.dslam.conn.CRONIOIExecutionData;
import com.imotion.dslam.logger.atmosphere.base.CRONIOLoggerEvent;
import com.imotion.dslam.logger.atmosphere.base.CRONIOLoggerEventCollection;

public class CRONIOExecutionLoggerImpl implements CRONIOIExecutionLogger {

	private static final long MAX_TIME_OUT = 300;
	private static final String FILE_APPENDER_NAME = "fileAppender";

	private static Logger					log4jLogger;;
	private String 							projectName	;
	private String 							processId;
	private long							lastBroadcastTime;
	private CRONIOLoggerEventCollection		broadcastBuffer;

	public CRONIOExecutionLoggerImpl(CRONIOBOIProject project) throws IOException {
		broadcastBuffer = new CRONIOLoggerEventCollection();
		CRONIOBOIProcess process = project.getProcess();
		processId		= String.valueOf(process.getProcessId());
		projectName 	= project.getProjectName();
		Logger logger 	= getLog4JLogger();
		String 	logsDir	= "./logs/";


		//File Appender
		String targetLog = logsDir + projectName + ".log";
		FileAppender fileAppender = (FileAppender) logger.getAppender(FILE_APPENDER_NAME);
		if (fileAppender != null) {
			if (!fileAppender.getFile().equals(targetLog)) {
				logger.removeAppender(FILE_APPENDER_NAME);
				addFileAppender(logger, targetLog);
			}

		} else {
			addFileAppender(logger, targetLog);
		}


	}

	@Override
	public void log(String connectionId, CRONIOBOINode node, CRONIOIExecutionData data) {
		log(connectionId, node, data, Level.INFO);
	}
	
	@Override
	public void log(String connectionId, CRONIOBOINode node, CRONIOIExecutionData data, Level level) {
		String	nodeIp			= node.getNodeIp();
		String	nodeName		= node.getNodeName();
		String	request			= data.getSourceCommand();
		String	response		= data.getResponse();
		String	prompt			= data.getPrompt();

		StringBuilder logValueSB = new StringBuilder();
		logValueSB.append(connectionId);
		logValueSB.append(",");
		logValueSB.append(nodeIp);
		logValueSB.append(",");
		logValueSB.append(nodeName);
		logValueSB.append(",");
		logValueSB.append(request);
		logValueSB.append(",");
		logValueSB.append(response);
		logValueSB.append(",");
		logValueSB.append(prompt);
		logValueSB.append("\n");
		String logValueStr = logValueSB.toString();

		//Log 		
		if (Level.DEBUG.equals(level)) {
			log4jLogger.debug(logValueStr);
		} else if (Level.ERROR.equals(level)) {
			log4jLogger.error(logValueStr);	
		} else if (Level.FATAL.equals(level)) {
			log4jLogger.fatal(logValueStr);
		} else if (Level.INFO.equals(level)) {
			log4jLogger.info(logValueStr);	
		} else if (Level.TRACE.equals(level)) {
			log4jLogger.trace(logValueStr);		
		} else if (Level.WARN.equals(level)) {
			log4jLogger.warn(logValueStr);
		} else {
			log4jLogger.info(logValueStr);
		}
		
		
		//ClientConsole
		CRONIOLoggerEvent loggerEvent = new CRONIOLoggerEvent();
		loggerEvent.setConnectionId(connectionId);
		loggerEvent.setNodeIp(nodeIp);
		loggerEvent.setNodeName(nodeName);
		loggerEvent.setRequest(request);
		loggerEvent.setResponse(response);
		loggerEvent.setPrompt(prompt);
		loggerEvent.setFullTrace(logValueStr);
		loggerEvent.setTimestamp(new Date());

		//		logToClient(loggerEvent);

		CRONIOLoggerEventCollection	broadcastBuffer = new CRONIOLoggerEventCollection();
		broadcastBuffer.add(loggerEvent);
		getBroadcaster(processId).broadcast(broadcastBuffer.cloneObject());
	}

	@Override
	public synchronized void flush() {
		lastBroadcastTime = System.currentTimeMillis();
		getBroadcaster(processId).broadcast(broadcastBuffer.cloneObject());
		broadcastBuffer.clear();
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

	private Broadcaster getBroadcaster(String loggerId) {
		Broadcaster b = DefaultBroadcasterFactory.getDefault().lookup(loggerId, true);
		return b;
	}

	private synchronized void logToClient(CRONIOLoggerEvent loggerEvent) {
		long timeDiff 			= System.currentTimeMillis() - lastBroadcastTime;
		long timeToBroadCast 	= MAX_TIME_OUT - timeDiff;
		if (lastBroadcastTime <= 0) {
			lastBroadcastTime	= System.currentTimeMillis();
			timeToBroadCast		= MAX_TIME_OUT;
		}
		if (timeToBroadCast <= 0) {
			flush();
		} else {
			broadcastBuffer.add(loggerEvent);
		}
	}

	private void addFileAppender(Logger logger, String targetLog) {
		PatternLayout layout = new PatternLayout();
		layout.setConversionPattern("%d %-5p %m%n");

		// Create appender
		FileAppender appender = new FileAppender();
		appender.setFile(targetLog);
		appender.setName(FILE_APPENDER_NAME);
		appender.setLayout(layout);
		appender.activateOptions();
		logger.addAppender(appender);
	}
}
