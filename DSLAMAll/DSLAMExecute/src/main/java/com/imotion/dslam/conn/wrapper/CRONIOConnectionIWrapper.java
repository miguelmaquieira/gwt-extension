package com.imotion.dslam.conn.wrapper;

import java.io.IOException;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.conn.CRONIOConnectionCheckedException;

public interface CRONIOConnectionIWrapper {

	void connect(CRONIOBOINode node) throws CRONIOConnectionCheckedException;
	
	void sendCommand(String command);
	
	String getResponse() throws IOException;
	
	void disconnect();
	
}
