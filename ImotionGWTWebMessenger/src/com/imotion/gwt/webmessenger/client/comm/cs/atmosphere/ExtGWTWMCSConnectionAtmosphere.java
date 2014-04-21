package com.imotion.gwt.webmessenger.client.comm.cs.atmosphere;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

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
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCSHandler;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMErrorCSHandler;
import com.imotion.gwt.webmessenger.client.comm.cs.ExtGWTWMCommCSHandlerWrapper;
import com.imotion.gwt.webmessenger.client.comm.cs.ExtGWTWMErrorCSHandlerWrapper;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMError;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMError.TYPE;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMSession;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMUtils;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHandlerManager;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasCloseCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasErrorHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasOpenCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasReceiveCommHandler;
import com.imotion.gwt.webmessenger.shared.ExtGWTWMRPCEvent;

public class ExtGWTWMCSConnectionAtmosphere implements ExtGWTWMCommCSConnection {

	private final Logger 				logger 		= Logger.getLogger("ExtGWTWMCSConnectionAtmosphere");
	private final ExtGWTWMMessageTexts 	MESSAGES 	= GWT.create(ExtGWTWMMessageTexts.class); 

	private ExtGWTWMSession 			sessionData;

	private ExtGWTWMCommCSHandler		commHandler;
	private ExtGWTWMErrorCSHandler		errorHandler;

	private Atmosphere 				atmosphere ;
	private AtmosphereRequest 		rpcRequest;
	private AtmosphereRequestConfig rpcRequestConfig;

	private boolean RECONNECT_FLAG 	  = true;
	private boolean DISCONNECTED_FLAG = true;

	@SuppressWarnings("unused")
	private ExtGWTWMCSConnectionAtmosphere() {
		// not allowed
	}

	public ExtGWTWMCSConnectionAtmosphere(ExtGWTWMHandlerManager handlerManager, String roomId, String userId, int timeout) {
		this.sessionData = new ExtGWTWMSession(roomId, userId);
		initConnection(handlerManager, roomId, timeout);
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
			RECONNECT_FLAG = false;
			DISCONNECTED_FLAG = false;
			atmosphere.unsubscribe();
			atmosphere = null;
			rpcRequest = null;
		} catch (Exception exception) {
			manageException(exception, "disconnect");
		}
	}

	@Override
	public void connect() {
		RECONNECT_FLAG = true;
		if (atmosphere != null || rpcRequest != null) {

			String message = MESSAGES.error_open_connection_message_text(getSessionData().getRoomId(),
					getSessionData().getUserId());
			ExtGWTWMError error = new ExtGWTWMError(TYPE.COMMAND, message);
			handlerError(error);
		} else {
			try {
				atmosphere = Atmosphere.create();
				rpcRequest = atmosphere.subscribe(rpcRequestConfig);
			} catch (Exception exception) {
				manageException(exception, "connect");
			}
		}
	}

	@Override
	public ExtGWTWMCommCSHandler getCommHandlerWrapper() {
		return commHandler;
	}

	@Override
	public ExtGWTWMErrorCSHandler getErrorHandlerWrapper() {
		return errorHandler;
	}

	@Override
	public void release() {
		errorHandler.release();
		commHandler.release();
		errorHandler = null;
		commHandler = null;
		disconnect();
		atmosphere = null;
		rpcRequest = null;
		rpcRequestConfig = null;
	}

	/**********************************************************************
	 *                           PRIVATE FUNCTIONS						  *
	 **********************************************************************/

	private void handlerError(ExtGWTWMError error) {
		if (error != null) {
			List<ExtGWTWMHasErrorHandler> errorHandlers = getErrorHandlerWrapper().getErrorHandlers();
			for (ExtGWTWMHasErrorHandler errorHandler: errorHandlers) {
				if (errorHandler.getErrorType() == null) {
					errorHandler.onError(error);
				} else {
					List<TYPE> errorTypeList = Arrays.asList(errorHandler.getErrorType());
					if (errorTypeList.contains(error.getErrorType()) || errorTypeList.contains(TYPE.ALL)) {
						errorHandler.onError(error);
					}
				}
			}
		}
	}

	private void manageException(Exception exception, String action) {
		String message = MESSAGES.error_common_exception_message_text(action, 
				getSessionData().getRoomId(),
				getSessionData().getUserId(),
				ExtGWTWMUtils.getStacktrace(exception));	
		handlerError(new ExtGWTWMError(TYPE.EXCEPTION, message, exception));
	}

	private void initConnection(ExtGWTWMHandlerManager handlerManager, String roomId, int timeout){
		errorHandler = new ExtGWTWMErrorCSHandlerWrapper(handlerManager, roomId);
		commHandler = new ExtGWTWMCommCSHandlerWrapper(handlerManager, roomId);
		initAtmosphere(timeout);
	}

	private void initAtmosphere(int timeout) {

		// comm params
		ExtGWTWMRPCSerializerAtmosphere rpc_serializer = GWT.create(ExtGWTWMRPCSerializerAtmosphere.class);
		rpcRequestConfig = AtmosphereRequestConfig.create(rpc_serializer);
		rpcRequestConfig.setUrl(GWT.getModuleBaseURL() + "atmosphere/rpc?" + "roomId="		+ getSessionData().getRoomId()
				+ "&userId=" 	+ getSessionData().getUserId());			
		rpcRequestConfig.setTransport(AtmosphereRequestConfig.Transport.WEBSOCKET);
		rpcRequestConfig.setFallbackTransport(AtmosphereRequestConfig.Transport.STREAMING);
		rpcRequestConfig.setFlags(Flags.enableProtocol);
		rpcRequestConfig.setTimeout(timeout);
		rpcRequestConfig.setReconnectInterval(300000);

		rpcRequestConfig.setOpenHandler(new AtmosphereOpenHandler() {

			@Override
			public void onOpen(AtmosphereResponse response) {
				List<ExtGWTWMHasOpenCommHandler> handlersOpen = getCommHandlerWrapper().getCommOpenHandlers();
				for (int index = 0; index < handlersOpen.size(); index++) {
					handlersOpen.get(index).handleConnectionOpened();
				}
			}
		});

		rpcRequestConfig.setCloseHandler(new AtmosphereCloseHandler() {

			@Override
			public void onClose(AtmosphereResponse response) {

				List<ExtGWTWMHasCloseCommHandler> handlers = getCommHandlerWrapper().getCommCloseHandlers();
				for (int index = 0; index < handlers.size(); index++) {
					handlers.get(index).handleConnectionClosed();
				}

				if (RECONNECT_FLAG) {
					atmosphere = null;
					rpcRequest = null;
					connect();
				} 

			}

		});

		rpcRequestConfig.setMessageHandler(new AtmosphereMessageHandler() {

			@Override
			public void onMessage(AtmosphereResponse response) {
				List<ExtGWTWMHasReceiveCommHandler> handlers = getCommHandlerWrapper().getCommReceiveHandlers();
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
				Window.alert("Error. Response: " + response.toString());
				String state = response.toString();
				ExtGWTWMError error = new ExtGWTWMError(TYPE.UNDEFINED, state);
				handlerError(error);
			}
		});

		rpcRequestConfig.setTransportFailureHandler(new AtmosphereTransportFailureHandler() {
			@Override
			public void onTransportFailure(String errorMsg, AtmosphereRequest request) {
				handlerError(new ExtGWTWMError(TYPE.TRANSPORT, errorMsg));
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
			public void onReconnect(AtmosphereRequestConfig request, AtmosphereResponse response) {
				Window.alert("ReConnect");
			}
		});
	}
}
