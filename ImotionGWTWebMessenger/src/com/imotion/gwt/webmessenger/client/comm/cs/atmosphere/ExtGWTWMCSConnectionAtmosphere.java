package com.imotion.gwt.webmessenger.client.comm.cs.atmosphere;

import java.util.Arrays;
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
import org.atmosphere.gwt20.client.AtmosphereRequestConfig.Flags;
import org.atmosphere.gwt20.client.AtmosphereResponse;
import org.atmosphere.gwt20.client.AtmosphereTransportFailureHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.SerializationException;
import com.imotion.gwt.webmessenger.client.ExtGWTWMMessageTexts;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCSConnection;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCSHandlerNew;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMErrorCSHandlerNew;
import com.imotion.gwt.webmessenger.client.comm.cs.ExtGWTWMCommCSHandlerWrapperNew;
import com.imotion.gwt.webmessenger.client.comm.cs.ExtGWTWMErrorCSHandlerWrapperNew;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMError;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMError.TYPE;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMSession;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMUtils;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHandlerManager;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasCloseCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasErrorHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasOpenCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasReceiveCommHandler;
import com.imotion.gwt.webmessenger.client.handler.impl.ExtGWTWMHandlerManagerImpl;
import com.imotion.gwt.webmessenger.shared.ExtGWTWMRPCEvent;

public class ExtGWTWMCSConnectionAtmosphere implements ExtGWTWMCommCSConnection {
	
	private final ExtGWTWMMessageTexts MESSAGES = GWT.create(ExtGWTWMMessageTexts.class); 

	private ExtGWTWMSession 			sessionData;
	
	private ExtGWTWMHandlerManager 		handlerManager;
	
	private ExtGWTWMCommCSHandlerNew		commHandler;
	private ExtGWTWMErrorCSHandlerNew		errorHandler;

	private Atmosphere 				atmosphere ;
	private AtmosphereRequest 		rpcRequest;
	private AtmosphereRequestConfig rpcRequestConfig;

	
	@SuppressWarnings("unused")
	private ExtGWTWMCSConnectionAtmosphere() {
		// not allowed
	}
	
	public ExtGWTWMCSConnectionAtmosphere(ExtGWTWMHandlerManager handlerManager, String roomId, String userId) {
		this.handlerManager = handlerManager;
		this.sessionData = new ExtGWTWMSession(roomId, userId);
		initAtmosphere();
	}
	
	/**********************************************************************
	 *               ExtGWTWebMessengerCommCSConnection	    			  *
	 **********************************************************************/
	@Override
	public ExtGWTWMSession getSessionData() {
		if (sessionData == null) {
			sessionData = new ExtGWTWMSession();
		}
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
		} catch (SerializationException exception) {
			manageException(exception, "sendMessage");
		} catch (Exception exception) {	
			manageException(exception, "sendMessage");
		}
	}

	@Override
	public void disconnect() {
		try {
			atmosphere.unsubscribe();
			atmosphere = null;
		} catch (Exception exception) {
			manageException(exception, "disconnect");
		}
	}

	@Override
	public void connect() {
		try {
			atmosphere = Atmosphere.create();
			rpcRequest = atmosphere.subscribe(rpcRequestConfig);
		} catch (Exception exception) {
			manageException(exception, "connect");
		}
	}
	
	@Override
	public ExtGWTWMCommCSHandlerNew getCommHandlerWrapper() {
		if (commHandler == null) {
			commHandler = new ExtGWTWMCommCSHandlerWrapperNew(getHandlerManager());
		}
		return commHandler;
	}

	@Override
	public ExtGWTWMErrorCSHandlerNew getErrorHandlerWrapper() {
		if (errorHandler == null) {
			errorHandler = new ExtGWTWMErrorCSHandlerWrapperNew(getHandlerManager());
		}
		return errorHandler;
	}
	
	@Override
	public void renameUser(String userId) {
		getSessionData().setUserId(userId);
	}
	
	@Override
	public void release() {
		// TODO Auto-generated method stub	
	}
	
	/**********************************************************************
	 *                           PRIVATE FUNCTIONS						  *
	 **********************************************************************/
	
	private void handlerError(ExtGWTWMError error) {
		if (error != null) {
			List<ExtGWTWMHasErrorHandler> errorHandlers = getHandlerManager().getErrorHandlers(getSessionData().getRoomId());
			for (ExtGWTWMHasErrorHandler errorHandler: errorHandlers) {
				if (errorHandler.getErrorType() == null) {
					errorHandler.onError(error);
				} else {
					List<TYPE> errorTypeList = Arrays.asList(errorHandler.getErrorType());
					if (errorTypeList.contains(error.getErrorType())) {
						errorHandler.onError(error);
					}
				}
			}
		}
	}
	
	private void manageException(Exception exception, String action) {
		String message = MESSAGES.error_common_exception_message_text(action, 
																		getSessionData().getUserId(),
																		getSessionData().getRoomId(),
																		ExtGWTWMUtils.getStacktrace(exception));	
		handlerError(new ExtGWTWMError(TYPE.EXCEPTION, message, exception));
	}

	private void initAtmosphere() {

		// comm params
		ExtGWTWMRPCSerializerAtmosphere rpc_serializer = GWT.create(ExtGWTWMRPCSerializerAtmosphere.class);
		rpcRequestConfig = AtmosphereRequestConfig.create(rpc_serializer);
		rpcRequestConfig.setUrl(GWT.getModuleBaseURL() + "atmosphere/rpc?" + "roomId="		+ getSessionData().getRoomId()
																			+ "&userId=" 	+ getSessionData().getUserId());			
		rpcRequestConfig.setTransport(AtmosphereRequestConfig.Transport.WEBSOCKET);
		rpcRequestConfig.setFallbackTransport(AtmosphereRequestConfig.Transport.WEBSOCKET);
		
		rpcRequestConfig.setFlags(Flags.enableProtocol);
		
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
	}
	
	private ExtGWTWMHandlerManager getHandlerManager() {
		if (handlerManager == null) {
			handlerManager = new ExtGWTWMHandlerManagerImpl();
		}
		return handlerManager;
	}
}
