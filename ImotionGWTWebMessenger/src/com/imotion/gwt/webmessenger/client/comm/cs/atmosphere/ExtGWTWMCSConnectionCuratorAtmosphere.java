package com.imotion.gwt.webmessenger.client.comm.cs.atmosphere;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.atmosphere.gwt20.client.Atmosphere;
import org.atmosphere.gwt20.client.AtmosphereRequest;
import org.atmosphere.gwt20.client.AtmosphereRequestConfig;

import com.google.gwt.user.client.Command;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWmCommCSConnectionCurator;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMCommand;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMCommand.COMMAND_TYPE;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMError;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMError.TYPE;
import com.imotion.gwt.webmessenger.shared.ExtGWTWMRPCEvent;

public class ExtGWTWMCSConnectionCuratorAtmosphere implements ExtGWTWmCommCSConnectionCurator {
	
	private Atmosphere 						atmosphere;
	private AtmosphereRequest 				rpcRequest;
	private AtmosphereRequestConfig 		atmosphereConfig;
	
	private Map<ExtGWTWMCommand.COMMAND_TYPE, ExtGWTWMCommand> commandMap;
	
	public ExtGWTWMCSConnectionCuratorAtmosphere(AtmosphereRequestConfig atmosphereConfig) {
		this.atmosphereConfig = atmosphereConfig;
	}
	
	@Override
	public int connect() throws Exception {
		int responseCode = ExtGWTWmCommCSConnectionCurator.WAITING_RESPONSE;
		if (atmosphere == null || rpcRequest == null) {
			atmosphere = Atmosphere.create();
			rpcRequest = atmosphere.subscribe(atmosphereConfig);
		} else {
			responseCode = ExtGWTWmCommCSConnectionCurator.ERROR_RESPONSE;
		}
		return responseCode;
	}

	@Override
	public void disconnect() throws Exception {
		try {
			releaseCommandMap();
			String url = getUrl(rpcRequest);
			unsubscribe(url);
			atmosphere = null;
			atmosphereConfig = null;
			rpcRequest = null;
		} catch (Exception exception) {
			atmosphere = null;
		}
	}

	@Override
	public void sendMessage(String message, String roomId, String userId) throws Exception {
		if (message != null && message.length() > 0) {
			ExtGWTWMRPCEvent myevent = new ExtGWTWMRPCEvent();
			myevent.setMessage(message);
			myevent.setSenderId(userId);
			myevent.setRoomId(roomId);
			rpcRequest.push(myevent);
		}
	}

	@Override
	public void executeCommand(COMMAND_TYPE type, int delay, int attemps, final ExtGWTWMCSConnectionAtmosphere conn) {
		ExtGWTWMCommand command = new ExtGWTWMCommand(type);
		executeCommand(command, delay, attemps, conn);
	}
	
	@Override
	public void executeCommand(ExtGWTWMCommand command, int delay, int attemps, ExtGWTWMCSConnectionAtmosphere conn) {
		ExtGWTWMCommand commandOnFly = getCommandMap().remove(command.getType());
		if (commandOnFly != null) {
			commandOnFly.cancel();
		}
		getCommandMap().put(command.getType(), command);
		
		if (command.getType() == COMMAND_TYPE.OPEN_CONNECTION) {
			executeOpenHandler(command, delay, attemps, conn);
		} else if (command.getType() == COMMAND_TYPE.CLOSE_HANDLER) {
			executeCloseHandler(command, delay, attemps, conn);
		} else if (command.getType() == COMMAND_TYPE.MESSAGE_HANDLER) {
			executeMessageHandler(command, delay, attemps, conn);
		}
	}

	@Override
	public void stopCommand(COMMAND_TYPE type) {
		ExtGWTWMCommand command = getCommandMap().remove(type);
		if (command != null) {
			command.cancel();
		}
	}

	/**********************************************************************
	 *                           NATIVE FUNCTIONS						  *
	 **********************************************************************/
	
	public native void unsubscribe(String url) /*-{
		$wnd.atmosphere.unsubscribeUrl(url);
	}-*/;
	
	public native String getUrl(AtmosphereRequest request) /*-{
		var requestImpl = request;
		return requestImpl.getUrl();
	}-*/;
	
	/**********************************************************************
	 *                           PRIVATE FUNCTIONS						  *
	 **********************************************************************/
	
	
	private void executeOpenHandler(ExtGWTWMCommand command, int delay, int attemps, final ExtGWTWMCSConnectionAtmosphere conn) {
		command.execute(new Command() {
			
			@Override
			public void execute() {
				conn.manageException(null, "open", TYPE.CONNECTION_ERROR);
			}
		}, delay, attemps);
	}
	

	private void executeCloseHandler(ExtGWTWMCommand command, int delay, int attemps, final ExtGWTWMCSConnectionAtmosphere conn) {
		command.execute(new Command() {
			
			@Override
			public void execute() {
				conn.handleCloseEvent(null);
			}
		}, delay, attemps);
	}
	
	private void executeMessageHandler(ExtGWTWMCommand command, int delay, int attemps, final ExtGWTWMCSConnectionAtmosphere conn) {
		command.execute(new Command() {
			
			@Override
			public void execute() {
				ExtGWTWMError error = new ExtGWTWMError(TYPE.SEND_MESSAGE);
				conn.handlerError(error);
			}
		}, delay, attemps);
	}
	
	private Map<ExtGWTWMCommand.COMMAND_TYPE, ExtGWTWMCommand> getCommandMap() {
		if (commandMap == null) {
			commandMap = new HashMap<ExtGWTWMCommand.COMMAND_TYPE, ExtGWTWMCommand>();
		}
		return commandMap;
	}
	
	private void releaseCommandMap() {
		if (commandMap != null) {
			Iterator<ExtGWTWMCommand> iterCommands = commandMap.values().iterator();
			while (iterCommands.hasNext()) {
				iterCommands.next().cancel();
			}
			commandMap.clear();
			commandMap = null;
		}
	}
}