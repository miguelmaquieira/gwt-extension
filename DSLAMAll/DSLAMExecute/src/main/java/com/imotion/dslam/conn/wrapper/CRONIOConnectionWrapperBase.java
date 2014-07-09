package com.imotion.dslam.conn.wrapper;

import java.io.IOException;

import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.conn.CRONIOConnectionCheckedException;


public abstract class CRONIOConnectionWrapperBase implements CRONIOConnectionIWrapper {

	private CRONIOConnectionStreams connectionStreams;
	private String 		user;
	private String 		password;
	private int		timeout;
	private String		ip;
	
	@Override
	public void connect(CRONIOBOINode node) throws CRONIOConnectionCheckedException {
		CRONIOBOIMachineProperties machineProperties = node.getMachineProperties();
		user		= machineProperties.getUsername();
		password	= machineProperties.getPassword();
		timeout		= machineProperties.getTimeout();
		ip			= node.getNodeIp();
	}
	
	@Override
	public void disconnect() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void sendCommand(String command) {
		connectionStreams.sendCommand(command);
	}

	@Override
	public String getResponse() throws IOException {
		return connectionStreams.getResponse();
	}
	
	/**
	 * PROTECTED
	 */
	
	protected CRONIOConnectionStreams getConnectionStreams() {
		return connectionStreams;
	}

	protected void setConnectionStreams(CRONIOConnectionStreams connectionStreams) {
		this.connectionStreams = connectionStreams;
	}
	

	protected String getUser() {
		return user;
	}

	protected String getPassword() {
		return password;
	}

	protected int getTimeout() {
		return timeout;
	}

	protected String getIp() {
		return ip;
	}

}
