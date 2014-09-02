package com.imotion.dslam.bom;

import java.io.Serializable;
import java.util.Date;

import org.apache.log4j.Level;

import com.imotion.dslam.bom.data.CRONIOHostData;
import com.imotion.dslam.bom.data.CRONIOLoggerData;

public interface CRONIOBOILog extends Serializable, CRONIOBOILogDataConstants {

	String getLogId();

	void setLogId(String logId);
	
	Date getTimestamp();

	void setTimestamp(Date timestamp);

	Level getLevel();

	void setLevel(Level level);

	String getThread();

	void setThread(String thread);

	String getMessage();

	void setMessage(String message);

	CRONIOLoggerData getLoggerName();

	void setLoggerName(CRONIOLoggerData loggerName);

	String getFileName();

	void setFileName(String fileName);

	String getMethod();

	void setMethod(String method);

	String getLineNumber();

	void setLineNumber(String lineNumber);

	CRONIOLoggerData getLoggerClass();

	void setLoggerClass(CRONIOLoggerData loggerClass);

	CRONIOHostData getHost();

	void setHost(CRONIOHostData host);


}
