package com.imotion.dslam.conn.wrapper;

import java.io.IOException;

import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.conn.CRONIOConnectionUncheckedException;


public abstract class CRONIOConnectionWrapperBase implements CRONIOConnectionIWrapper {

	private CRONIOConnectionStreams 	connectionStreams;
	private CRONIOBOINode 				node;
	private String 						user;
	private String 						password;
	private int						timeout;
	private String						ip;
	private String						promptRegEx;
	private CRONIOBOIMachineProperties machineProperties;
	
	@Override
	public void connect(CRONIOBOINode node) throws CRONIOConnectionUncheckedException {
		this.node 				= node;
		this.machineProperties = node.getMachineProperties();
		user					= machineProperties.getUsername();
		password				= machineProperties.getPassword();
		timeout					= machineProperties.getTimeout();
		ip						= node.getNodeIp();
		promptRegEx				= machineProperties.getPromptRegEx();
	}
	
	@Override
	public void disconnect() throws CRONIOConnectionUncheckedException {
		finalizeConnection();
		connectionStreams.closeStreams();
	}
	
	@Override
	public String sendCommand(String command) throws CRONIOConnectionUncheckedException {
		return connectionStreams.sendCommand(command);
	}
	
	@Override
	public void sendNoResponseCommand(String command) {
		connectionStreams.sendCommandBase(command);
	}
	
	@Override
	public String readResponseUntil(String pattern) throws CRONIOConnectionUncheckedException {
		return connectionStreams.readUntil(pattern);
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

	protected String getHost() {
		return ip;
	}
	
	protected String getPromptRegEx() {
		return promptRegEx;
	}
	
	protected void initializeConnection() throws IOException {
		//nothing to
	}
	
	protected void finalizeConnection() {
		//nothing to
	}

	protected CRONIOBOINode getNode() {
		return node;
	}
	
	protected CRONIOBOIMachineProperties getMachineProperties() {
		return machineProperties;
	}

}
