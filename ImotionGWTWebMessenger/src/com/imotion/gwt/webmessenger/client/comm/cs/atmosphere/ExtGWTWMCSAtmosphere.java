package com.imotion.gwt.webmessenger.client.comm.cs.atmosphere;

import java.util.List;

import org.atmosphere.gwt20.client.Atmosphere;
import org.atmosphere.gwt20.client.AtmosphereCloseHandler;
import org.atmosphere.gwt20.client.AtmosphereErrorHandler;
import org.atmosphere.gwt20.client.AtmosphereMessageHandler;
import org.atmosphere.gwt20.client.AtmosphereOpenHandler;
import org.atmosphere.gwt20.client.AtmosphereReconnectHandler;
import org.atmosphere.gwt20.client.AtmosphereReopenHandler;
import org.atmosphere.gwt20.client.AtmosphereRequest;
import org.atmosphere.gwt20.client.AtmosphereRequestConfig;
import org.atmosphere.gwt20.client.AtmosphereResponse;
import org.atmosphere.gwt20.client.AtmosphereTransportFailureHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.SerializationException;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCS;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCSHandler;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMErrorCSHandler;
import com.imotion.gwt.webmessenger.client.comm.cs.ExtGWTWMCommCSHandlerWrapper;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHandlerManager;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasCloseCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasOpenCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasReceiveCommHandler;
import com.imotion.gwt.webmessenger.client.handler.impl.ExtGWTWMHandlerManagerImpl;
import com.imotion.gwt.webmessenger.client.session.ExtGWTWMSession;
import com.imotion.gwt.webmessenger.shared.ExtGWTWMRPCEvent;

public class ExtGWTWMCSAtmosphere implements ExtGWTWMCommCS {

	private ExtGWTWMSession 			sessionData;
	
	private ExtGWTWMHandlerManagerImpl 	handlerManager;
	private ExtGWTWMCommCSHandler		commHandler;
	private ExtGWTWMErrorCSHandler		errorHandler;

	private Atmosphere 				atmosphere ;
	private AtmosphereRequest 		rpcRequest;
	private AtmosphereRequestConfig rpcRequestConfig;

	public ExtGWTWMCSAtmosphere() {
		
	}
	
	/**********************************************************************
	 *                   	ExtGWTWebMessengerCommCS	    			  *
	 **********************************************************************/
	@Override
	public ExtGWTWMSession getSessionData() {
		return sessionData;
	}
	
	@Override
	public void sendMessage(String message) {
		try {
			if (message.length() > 0) {
				ExtGWTWMRPCEvent myevent = new ExtGWTWMRPCEvent();
				myevent.setMessage(message);
				myevent.setSenderId(sessionData.getUserId());
				myevent.setRoomId(sessionData.getRoomId());
				rpcRequest.push(myevent);
			}
		} catch (SerializationException ex) {	
			Window.alert("Serialization Exception: \n" +  getStacktrace(ex));
		}
	}

	@Override
	public void disconnect() {
		atmosphere.unsubscribe();
		atmosphere = null;
	}

	@Override
	public void connect() {
		rpcRequest = atmosphere.subscribe(rpcRequestConfig);
	}

	@Override
	public void init(String nickname, String roomname) {
		this.sessionData = new ExtGWTWMSession(nickname, roomname);
		initAtmosphere();
	}

	@Override
	public void connect(String nickname, String roomname) {

	}

	@Override
	public void connect(String nickname) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public ExtGWTWMCommCSHandler getCommHandlerWrapper() {
		if (commHandler == null) {
			commHandler = new ExtGWTWMCommCSHandlerWrapper(getHandlerManager());
		}
		return commHandler;
	}

	@Override
	public ExtGWTWMErrorCSHandler getErrorHandlerWrapper() {
		if (errorHandler == null) {
			//errorHandler = new ExtGWTWMErrorCSHandlerWrapper(getHandlerManager());
		}
		return errorHandler;
	}
	
	/**********************************************************************
	 *                           PRIVATE FUNCTIONS						  *
	 **********************************************************************/

	private void initAtmosphere() {

		// comm params
		ExtGWTWMRPCSerializerAtmosphere rpc_serializer = GWT.create(ExtGWTWMRPCSerializerAtmosphere.class);
		rpcRequestConfig = AtmosphereRequestConfig.create(rpc_serializer);
		rpcRequestConfig.setUrl(GWT.getModuleBaseURL() + "atmosphere/rpc?roomId="+ sessionData.getRoomId());			
		rpcRequestConfig.setTransport(AtmosphereRequestConfig.Transport.WEBSOCKET);
		rpcRequestConfig.setFallbackTransport(AtmosphereRequestConfig.Transport.WEBSOCKET);
		
		rpcRequestConfig.setOpenHandler(new AtmosphereOpenHandler() {
			
			@Override
			public void onOpen(AtmosphereResponse response) {
				List<ExtGWTWMHasOpenCommHandler> handlersOpen = getHandlerManager().getCommOpenHandlers(getSessionData().getRoomId());
				for (int index = 0; index < handlersOpen.size(); index++) {
					handlersOpen.get(index).handleConnectionOpened();
				}
			}
		});
		
		rpcRequestConfig.setCloseHandler(new AtmosphereCloseHandler() {
			
			@Override
			public void onClose(AtmosphereResponse response) {
				List<ExtGWTWMHasCloseCommHandler> handlers = getHandlerManager().getCommCloseHandlers(getSessionData().getRoomId());
				for (int index = 0; index < handlers.size(); index++) {
					handlers.get(index).handleConnectionClosed();
				}
			}
		});
		
		rpcRequestConfig.setMessageHandler(new AtmosphereMessageHandler() {
			
			@Override
			public void onMessage(AtmosphereResponse response) {
				List<ExtGWTWMHasReceiveCommHandler> handlers = getHandlerManager().getCommReceiveHandlers(getSessionData().getRoomId());
				for (int index = 0; index < handlers.size(); index++) {
					List<ExtGWTWMRPCEvent> messages = response.getMessages();
					for (ExtGWTWMRPCEvent rpcEvent : messages) {
						String message 	= rpcEvent.getMessage();
						long timestamp 	= rpcEvent.getTimestamp();
						String sender 	= rpcEvent.getSenderId();
						handlers.get(index).handleReceivedMessage(message, timestamp, sender);
					}
				}
			}
		});
		
		rpcRequestConfig.setErrorHandler(new AtmosphereErrorHandler() {
			@Override
			public void onError(AtmosphereResponse response) {
				Window.alert("Error");
			}
		});

		rpcRequestConfig.setTransportFailureHandler(new AtmosphereTransportFailureHandler() {
			@Override
			public void onTransportFailure(String errorMsg, AtmosphereRequest request) {
				Window.alert("Transport Failure");
			}
		});

		rpcRequestConfig.setReopenHandler(new AtmosphereReopenHandler() {
			@Override
			public void onReopen(AtmosphereResponse response) {
				Window.alert("ReOpen");
			}
		});

		rpcRequestConfig.setReconnectHandler(new AtmosphereReconnectHandler() {
			@Override
			public void onReconnect(AtmosphereRequestConfig request,AtmosphereResponse response) {
				Window.alert("ReConnect");
			}
		});

		atmosphere = Atmosphere.create();
	}
	
	private String getStacktrace(Exception ex) {
		StackTraceElement[] traces = ex.getStackTrace();
		String trace = "no trace";
		if (traces != null) {
			for (int i = 0; i < traces.length || i < 10; i++) {
				trace = trace.concat(traces[i].toString());
			}
			trace.concat("\n...");
		}
		return trace;
	}
	
	private ExtGWTWMHandlerManager getHandlerManager() {
		if (handlerManager == null) {
			handlerManager = new ExtGWTWMHandlerManagerImpl();
		}
		return handlerManager;
	}
}
