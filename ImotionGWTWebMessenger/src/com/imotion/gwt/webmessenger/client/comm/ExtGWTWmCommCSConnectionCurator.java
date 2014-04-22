package com.imotion.gwt.webmessenger.client.comm;

import com.imotion.gwt.webmessenger.client.ExtGWTWMReleasable;
import com.imotion.gwt.webmessenger.client.comm.cs.atmosphere.ExtGWTWMCSConnectionAtmosphere;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMCommand;

public interface ExtGWTWmCommCSConnectionCurator extends ExtGWTWMReleasable {
	

	public void connect() throws Exception;
	public void disconnect() throws Exception;
	public void sendMessage(String message, String roomId, String userId) throws Exception;
	
	public void executeCommand(ExtGWTWMCommand.COMMAND_TYPE type, int delay, int attemps, final ExtGWTWMCSConnectionAtmosphere conn);
	public void executeCommand(ExtGWTWMCommand command, int delay, int attemps, final ExtGWTWMCSConnectionAtmosphere conn);
	
	public void stopCommand(ExtGWTWMCommand.COMMAND_TYPE type);

}
