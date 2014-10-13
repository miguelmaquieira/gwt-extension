package com.imotion.dslam.logger;

import org.apache.log4j.Level;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.conn.CRONIOIExecutionData;

public interface CRONIOIExecutionLogger {

	void log(String connectionId, CRONIOBOINode node, CRONIOIExecutionData data);
	void log(String connectionId, CRONIOBOINode node, CRONIOIExecutionData data, Level level);
	
	void flush();
	
}
