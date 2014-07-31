package com.imotion.dslam.conn.wrapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.conn.CRONIOConnectionUncheckedException;
import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.transport.IgnoreHostKeyVerification;

public class CRONIOConnectionWrapperSSH1 extends CRONIOConnectionWrapperBase implements CRONIOConnectionIWrapper {


	private SshClient 				ssh;
	private SessionChannelClient 	session;

	@SuppressWarnings("resource")
	@Override
	public void connect(CRONIOBOINode node) throws CRONIOConnectionUncheckedException {
		super.connect(node);
		try {
			ssh = new SshClient();
			ssh.setSocketTimeout(getTimeout());
			ssh.connect(getHost(), new IgnoreHostKeyVerification());

			PasswordAuthenticationClient auth = new PasswordAuthenticationClient();
			auth.setUsername(getUser());
			auth.setPassword(getPassword());
			int result = ssh.authenticate(auth);
			if (result != AuthenticationProtocolState.COMPLETE) {
				throw new CRONIOConnectionUncheckedException("Error authentication");
			}

			session = ssh.openSessionChannel();
			session.startShell();
			InputStream 	isIn	= session.getInputStream();
			PrintStream 	osOut	= new PrintStream(session.getOutputStream());

			CRONIOConnectionStreams connectionStreams = new CRONIOConnectionStreams(isIn, osOut);
			setConnectionStreams(connectionStreams);
		} catch (IOException e) {
			throw new CRONIOConnectionUncheckedException(e);
		}
	}

	@Override
	public void disconnect() throws CRONIOConnectionUncheckedException {
		super.disconnect();
		try {
			session.close();
			ssh.disconnect();
		} catch (IOException e) {
			throw new CRONIOConnectionUncheckedException(e);
		}
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
