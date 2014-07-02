package com.imotion.dslam.logger;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.conn.CRONIOIExecutionData;

public interface CRONIOIExecutionLogger {

	void log(String connectionId, CRONIOBOINode node, CRONIOIExecutionData data);
	
	void flush();
	
}
