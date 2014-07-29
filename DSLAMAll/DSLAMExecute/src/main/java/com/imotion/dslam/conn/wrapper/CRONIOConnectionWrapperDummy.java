package com.imotion.dslam.conn.wrapper;

import java.io.IOException;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.conn.CRONIOConnectionUncheckedException;

public class CRONIOConnectionWrapperDummy extends CRONIOConnectionWrapperBase implements CRONIOConnectionIWrapper {
	
	@Override
	public void connect(CRONIOBOINode node) throws CRONIOConnectionUncheckedException {
	}

	@Override
	public void disconnect() throws CRONIOConnectionUncheckedException {
	}
	
	@Override
	public String sendCommand(String command) throws CRONIOConnectionUncheckedException {
		return	command + " Response " + getPromptRegEx();
	}
	
	@Override
	public String readResponseUntil(String pattern) throws CRONIOConnectionUncheckedException {
		return "Response " + pattern;
	}
	
	/**
	 * PROTECTED
	 */
	@Override
	protected void initializeConnection() throws IOException {
	}
}
