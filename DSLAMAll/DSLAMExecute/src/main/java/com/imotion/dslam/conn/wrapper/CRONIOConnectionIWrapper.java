package com.imotion.dslam.conn.wrapper;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.conn.CRONIOConnectionUncheckedException;

public interface CRONIOConnectionIWrapper {

	void connect(CRONIOBOINode node) throws CRONIOConnectionUncheckedException;
	
	String sendCommand(String command) throws CRONIOConnectionUncheckedException;
	
	void sendNoResponseCommand(String command);
	
	String readResponseUntil(String pattern) throws CRONIOConnectionUncheckedException;
	
	void disconnect() throws CRONIOConnectionUncheckedException;
	
}
