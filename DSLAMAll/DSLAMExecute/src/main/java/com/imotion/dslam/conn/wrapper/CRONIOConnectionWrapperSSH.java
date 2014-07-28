package com.imotion.dslam.conn.wrapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.conn.CRONIOConnectionUncheckedException;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class CRONIOConnectionWrapperSSH extends CRONIOConnectionWrapperBase implements CRONIOConnectionIWrapper {

	private JSch					jsch;
	private Session					session;
	private Channel					channel;
	
	@SuppressWarnings("resource")
	@Override
	public void connect(CRONIOBOINode node) throws CRONIOConnectionUncheckedException {
		super.connect(node);
		Properties	sessionProperties 	= new Properties();
		sessionProperties.put("StrictHostKeyChecking", "no");
		
		jsch = new JSch();
		try {
			session = jsch.getSession(getUser(), getIp(), 22);
			session.setPassword(getPassword());
			session.setTimeout(getTimeout());
			session.setConfig(sessionProperties);
			session.connect();
			channel = session.openChannel("shell");
			channel.connect(getTimeout());
			InputStream isIn	= channel.getInputStream();
			PrintStream osOut	= new PrintStream(channel.getOutputStream());
			
			CRONIOConnectionStreams connectionStreams = new CRONIOConnectionStreams(isIn, osOut);
			setConnectionStreams(connectionStreams);
			
			initializeConnection();
		} catch (JSchException | IOException e) {
			throw new CRONIOConnectionUncheckedException(e);
		}
	}

	@Override
	public void disconnect() throws CRONIOConnectionUncheckedException {
		super.disconnect();
		channel.disconnect();
		session.disconnect();
	}
	/**
	 * PROTECTED
	 */
	@Override
	protected void initializeConnection() throws IOException {
		String promptRegEx 	= getPromptRegEx();
		readResponseUntil(promptRegEx);
		super.initializeConnection();
	}
}
