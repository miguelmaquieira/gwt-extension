package com.imotion.dslam.conn;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.logger.CRONIOIExecutionLogger;

public class CRONIOConnectionFactory {

	public static CRONIOIConnection getConnection(long processId, CRONIOBOINode node, CRONIOIExecutionLogger logger) {
		//TODO:
		return new CRONIOConnectionImpl(processId, node, logger);
	}
	
	public static void releaseConnection(CRONIOIConnection connection) {
	}
	
}
