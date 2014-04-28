package com.imotion.gwt.webmessenger.client.comm.cs.atmosphere;

import java.util.Arrays;
import java.util.List;

import org.atmosphere.gwt20.client.AtmosphereCloseHandler;
import org.atmosphere.gwt20.client.AtmosphereErrorHandler;
import org.atmosphere.gwt20.client.AtmosphereMessageHandler;
import org.atmosphere.gwt20.client.AtmosphereOpenHandler;
import org.atmosphere.gwt20.client.AtmosphereReconnectHandler;
import org.atmosphere.gwt20.client.AtmosphereReopenHandler;
import org.atmosphere.gwt20.client.AtmosphereRequest;
import org.atmosphere.gwt20.client.AtmosphereRequestConfig;
import org.atmosphere.gwt20.client.AtmosphereRequestConfig.Flags;
import org.atmosphere.gwt20.client.AtmosphereRequestConfig.Transport;
import org.atmosphere.gwt20.client.AtmosphereResponse;
import org.atmosphere.gwt20.client.AtmosphereTransportFailureHandler;

import com.google.gwt.core.client.GWT;
import com.imotion.gwt.webmessenger.client.ExtGWTWMException;
import com.imotion.gwt.webmessenger.client.ExtGWTWMMessageTexts;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCSConnection;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCSHandler;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMErrorCSHandler;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWmCommCSConnectionCurator;
import com.imotion.gwt.webmessenger.client.comm.cs.ExtGWTWMCommCSHandlerWrapper;
import com.imotion.gwt.webmessenger.client.comm.cs.ExtGWTWMErrorCSHandlerWrapper;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMCommand.COMMAND_TYPE;
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

	private final ExtGWTWMMessageTexts 	MESSAGES 	= GWT.create(ExtGWTWMMessageTexts.class); 

	private ExtGWTWMSession 				sessionData;

	private ExtGWTWMCommCSHandler			commHandler;
	private ExtGWTWMErrorCSHandler			errorHandler;

	private ExtGWTWmCommCSConnectionCurator curator;

	private AtmosphereRequestConfig 		atmosphereConfig;


	@SuppressWarnings("unused")
	private ExtGWTWMCSConnectionAtmosphere() {
		// not allowed
	}
	
	public ExtGWTWMCSConnectionAtmosphere(ExtGWTWMHandlerManager handlerManager, String roomId, String userId, int timeout, TRANSPORT_TYPE protocol, TRANSPORT_TYPE fallback) {
		this.sessionData = new ExtGWTWMSession(roomId, userId);	
		initConnection(handlerManager, roomId, timeout, protocol, fallback);
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
			// fix when the close handle is not invoke
			getCurator().executeCommand(COMMAND_TYPE.MESSAGE_HANDLER, 3000, 1, this);

			getCurator().sendMessage(message, getSessionData().getRoomId(), getSessionData().getUserId());
		} catch (Exception exception) {
			manageException(exception, "sendMessage");
		}
	}

	@Override
	public void disconnect() {
		try {
			// fix when the close handle is not invoke
			getCurator().executeCommand(COMMAND_TYPE.CLOSE_HANDLER, 3000, 1, this);

			// connection
			getCurator().disconnect();

		} catch (Exception exception) {
			manageException(exception, "disconnect");
		}
	}

	@Override
	public void connect() {
		try {
			getCurator().executeCommand(COMMAND_TYPE.OPEN_CONNECTION, 1000, 1, this);

			getCurator().connect();
		} catch (ExtGWTWMException exception) {
			String roomId = getSessionData().getRoomId();
			String userId = getSessionData().getUserId();
			String message = MESSAGES.error_open_connection_message_text(roomId, userId);
			handlerError(new ExtGWTWMError(message));
		} catch (Exception exception) {
			manageException(exception, "connect");
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
		disconnect();
		if (errorHandler != null) {
			errorHandler.release();
			errorHandler = null;
		}
		if (commHandler != null)  {
			commHandler.release();
			commHandler = null;
		}
		atmosphereConfig = null;
	}

	/**********************************************************************
	 *                         PROTECTED FUNCTIONS						  *
	 **********************************************************************/

	protected void handleCloseEvent(AtmosphereResponse response) {
		ExtGWTWMCommCSHandler commHandlerWrapper = getCommHandlerWrapper();
		if (commHandlerWrapper != null) {
			List<ExtGWTWMHasCloseCommHandler> handlers = commHandlerWrapper.getCommCloseHandlers();
			for (int index = 0; index < handlers.size(); index++) {
				handlers.get(index).handleConnectionClosed();
			}
		}
	}

	protected void handleOpenEvent(AtmosphereResponse response) {
		ExtGWTWMCommCSHandler commHandlerWrapper = getCommHandlerWrapper();
		if (commHandlerWrapper != null) {
		List<ExtGWTWMHasOpenCommHandler> handlersOpen = commHandlerWrapper.getCommOpenHandlers();
			for (int index = 0; index < handlersOpen.size(); index++) {
				handlersOpen.get(index).handleConnectionOpened();
			}
		}
	}

	protected void handlerError(ExtGWTWMError error) {
		if (error != null) {
			ExtGWTWMErrorCSHandler errorHandlerWrapper = getErrorHandlerWrapper();
			if (errorHandlerWrapper != null) { 
				List<ExtGWTWMHasErrorHandler> errorHandlers = errorHandlerWrapper.getErrorHandlers();
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
	}

	protected void manageException(Exception exception, String action) {
		String message = MESSAGES.error_common_exception_message_text(action, 
				getSessionData().getRoomId(),
				getSessionData().getUserId(),
				ExtGWTWMUtils.getStacktrace(exception));	
		handlerError(new ExtGWTWMError(TYPE.EXCEPTION, message, exception));
	}

	/**********************************************************************
	 *                           PRIVATE FUNCTIONS						  *
	 **********************************************************************/



	private void initConnection(ExtGWTWMHandlerManager handlerManager, String roomId, int timeout, TRANSPORT_TYPE protocol, TRANSPORT_TYPE fallback) {
		errorHandler = new ExtGWTWMErrorCSHandlerWrapper(handlerManager, roomId);
		commHandler = new ExtGWTWMCommCSHandlerWrapper(handlerManager, roomId);
		initAtmosphere(timeout, protocol, fallback);
	}

	private void initAtmosphere(int timeout, TRANSPORT_TYPE protocol, TRANSPORT_TYPE fallback) {

		// comm params
		ExtGWTWMRPCSerializerAtmosphere rpc_serializer = GWT.create(ExtGWTWMRPCSerializerAtmosphere.class);
		atmosphereConfig = AtmosphereRequestConfig.create(rpc_serializer);
		atmosphereConfig.setUrl(GWT.getModuleBaseURL() + "atmosphere/rpc?" + "roomId="		+ getSessionData().getRoomId()
				+ "&userId=" 	+ getSessionData().getUserId());
		
		
		atmosphereConfig.setTransport(getProtocol(protocol));
		atmosphereConfig.setFallbackTransport(getProtocol(fallback));
		atmosphereConfig.setFlags(Flags.enableProtocol);
		atmosphereConfig.setTimeout(timeout);

		atmosphereConfig.setOpenHandler(new AtmosphereOpenHandler() {

			@Override
			public void onOpen(AtmosphereResponse response) {
				getCurator().stopCommand(COMMAND_TYPE.OPEN_CONNECTION);
				handleOpenEvent(response);
			}
		});

		atmosphereConfig.setCloseHandler(new AtmosphereCloseHandler() {

			@Override
			public void onClose(AtmosphereResponse response) {
				getCurator().stopCommand(COMMAND_TYPE.CLOSE_HANDLER);
				handleCloseEvent(response);
			}
		});

		atmosphereConfig.setMessageHandler(new AtmosphereMessageHandler() {

			@Override
			public void onMessage(AtmosphereResponse response) {
				getCurator().stopCommand(COMMAND_TYPE.MESSAGE_HANDLER);
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

		atmosphereConfig.setErrorHandler(new AtmosphereErrorHandler() {
			@Override
			public void onError(AtmosphereResponse response) {
				String state = response.toString();
				ExtGWTWMError error = new ExtGWTWMError(TYPE.UNDEFINED, state);
				handlerError(error);
			}
		});

		atmosphereConfig.setTransportFailureHandler(new AtmosphereTransportFailureHandler() {
			@Override
			public void onTransportFailure(String errorMsg, AtmosphereRequest request) {
				handlerError(new ExtGWTWMError(TYPE.TRANSPORT, errorMsg));
			}
		});

		atmosphereConfig.setReopenHandler(new AtmosphereReopenHandler() {
			@Override
			public void onReopen(AtmosphereResponse response) {
				// nothing to do
			}
		});

		atmosphereConfig.setReconnectHandler(new AtmosphereReconnectHandler() {
			@Override
			public void onReconnect(AtmosphereRequestConfig request, AtmosphereResponse response) {
				// nothing to do
			}
		});
	}

	private ExtGWTWmCommCSConnectionCurator getCurator() {
		if (curator == null) {
			curator = new ExtGWTWMCSConnectionCuratorAtmosphere(atmosphereConfig);
		}
		return curator;
	}
	
	private Transport getProtocol(TRANSPORT_TYPE type) {
		Transport protocolType = Transport.LONG_POLLING;
		if (type == TRANSPORT_TYPE.WEBSOCKETS) {
			protocolType = Transport.WEBSOCKET;
		} else if (type == TRANSPORT_TYPE.STREAMING) {
			protocolType = Transport.STREAMING;
		} else if (type == TRANSPORT_TYPE.LONG_POLLING) {
			protocolType = Transport.LONG_POLLING;
		} else if (type == TRANSPORT_TYPE.LONG_POLLING) {
			protocolType = Transport.SSE;
		} else if (type == TRANSPORT_TYPE.SESSION) {
			protocolType = Transport.SESSION;
		}
		return protocolType;
	}
}
