package com.imotion.dslam.conn.wrapper;

import java.io.IOException;

import com.imotion.dslam.bom.CRONIOBOINode;

public class CRONIOConnectionWrapperDummy extends CRONIOConnectionWrapperBase implements CRONIOConnectionIWrapper {
	
	@Override
	public void connect(CRONIOBOINode node) {
		super.connect(node);
	}

	@Override
	public void disconnect() {
		super.disconnect();
	}
	
	@Override
	public String sendCommand(String command) throws IOException {
		return	command + " Response " + getPromptRegEx();
	}
	
	@Override
	public String readResponseUntil(String pattern) throws IOException {
		return "Response " + pattern;
	}
	
	/**
	 * PROTECTED
	 */
	@Override
	protected void runConnectScript() throws IOException {
	}
}
