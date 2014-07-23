package com.imotion.dslam.conn.wrapper;

import java.io.IOException;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.conn.CRONIOConnectionUncheckedException;

public interface CRONIOConnectionIWrapper {

	void connect(CRONIOBOINode node) throws CRONIOConnectionUncheckedException;
	
	String sendCommand(String command) throws IOException;
	
	void sendNoResponseCommand(String command);
	
	String readResponseUntil(String pattern) throws IOException;
	
	void disconnect();
	
}
