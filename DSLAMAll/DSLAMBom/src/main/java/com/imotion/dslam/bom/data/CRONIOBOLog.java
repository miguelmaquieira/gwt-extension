package com.imotion.dslam.bom.data;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.log4j.Level;
import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

import com.imotion.dslam.bom.CRONIOBOILog;

@Entity
@NoSql(dataType = "logs", dataFormat= DataFormatType.MAPPED)
public class CRONIOBOLog implements CRONIOBOILog{

	private static final long serialVersionUID = -414564215610115013L;

	@Id
	@GeneratedValue
	@Field(name="_id")
	private String logId;	
	private Date timestamp;
	private Level level;
	private String thread;
	private String message;
	@Embedded
	private CRONIOLoggerData loggerName;
	private String fileName; 
	private String method;
	private String lineNumber; 
	@Embedded
	private CRONIOLoggerData loggerClass;
	@Embedded
	private CRONIOHostData host;

	
	public CRONIOBOLog() {
		
	}
	
	@Override
	public String getLogId() {
		return logId;
	}

	@Override
	public void setLogId(String logId) {
		this.logId = logId;
	}

	@Override
	public Date getTimestamp() {
		return timestamp;
	}

	@Override
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public Level getLevel() {
		return level;
	}

	@Override
	public void setLevel(Level level) {
		this.level = level;
	}

	@Override
	public String getThread() {
		return thread;
	}

	@Override
	public void setThread(String thread) {
		this.thread = thread;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public CRONIOLoggerData getLoggerName() {
		return loggerName;
	}

	@Override
	public void setLoggerName(CRONIOLoggerData loggerName) {
		this.loggerName = loggerName;
	}

	@Override
	public String getFileName() {
		return fileName;
	}

	@Override
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String getMethod() {
		return method;
	}

	@Override
	public void setMethod(String method) {
		this.method = method;
	}

	@Override
	public String getLineNumber() {
		return lineNumber;
	}

	@Override
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	@Override
	public CRONIOLoggerData getLoggerClass() {
		return loggerClass;
	}

	@Override
	public void setLoggerClass(CRONIOLoggerData loggerClass) {
		this.loggerClass = loggerClass;
	}

	@Override
	public CRONIOHostData getHost() {
		return host;
	}

	@Override
	public void setHost(CRONIOHostData host) {
		this.host = host;
	}
	
	
	
}



