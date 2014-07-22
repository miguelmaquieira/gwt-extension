package com.imotion.dslam.conn.wrapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.apache.commons.net.telnet.TelnetClient;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.conn.CRONIOConnectionUncheckedException;

public class CRONIOConnectionWrapperTelnet extends CRONIOConnectionWrapperBase implements CRONIOConnectionIWrapper {

	private TelnetClient telnet;

	@SuppressWarnings("resource")
	@Override
	public void connect(CRONIOBOINode node) throws CRONIOConnectionUncheckedException {
		super.connect(node);

		telnet = new TelnetClient();
		try {
			telnet.connect(getIp());

			InputStream isIn	= telnet.getInputStream();
			PrintStream osOut	= new PrintStream(telnet.getOutputStream());
			CRONIOConnectionStreams connectionStreams = new CRONIOConnectionStreams(isIn, osOut);
			setConnectionStreams(connectionStreams);

			runConnectScript();
		} catch (IOException e) {
			throw new CRONIOConnectionUncheckedException(e);
		}
	}

	@Override
	public void disconnect() {
		super.disconnect();
	}
	
	/**
	 * PROTECTED
	 */
	
	@Override
	protected void runConnectScript() throws IOException {
		getConnectionStreams().readUntil("Login: ");
		sendCommand(getUser());
		getConnectionStreams().readUntil("Password: ");
		sendNoResponseCommand(getPassword());
		getConnectionStreams().readUntil(getPromptRegEx());
		super.runConnectScript();
	}

}
