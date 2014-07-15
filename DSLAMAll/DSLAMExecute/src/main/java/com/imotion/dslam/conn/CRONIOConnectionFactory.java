package com.imotion.dslam.conn;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.logger.CRONIOIExecutionLogger;

public class CRONIOConnectionFactory {

	public static CRONIOIConnection getConnection(long processId, CRONIOBOINode node, CRONIOIExecutionLogger logger) {
		CRONIOConnectionImpl connection = new CRONIOConnectionImpl(processId, node, logger);
//		try {
//			connection.openConnection();
//		} catch (IOException e) {
//			connection = null;
//			e.printStackTrace();
//		}
		return connection;
	}
	
	public static void releaseConnection(CRONIOIConnection connection) {
	}
	
}
