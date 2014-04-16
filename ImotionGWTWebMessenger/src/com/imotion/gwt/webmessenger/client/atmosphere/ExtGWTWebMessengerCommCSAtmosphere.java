package com.imotion.gwt.webmessenger.client.atmosphere;

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
import com.imotion.gwt.webmessenger.client.ExtGWTWebMessengerCommCS;
import com.imotion.gwt.webmessenger.client.ExtGWTWebMessengerHasCommHandler;
import com.imotion.gwt.webmessenger.shared.ExtGWTWebMessengerRPCEvent;

public class ExtGWTWebMessengerCommCSAtmosphere implements ExtGWTWebMessengerCommCS {

	private boolean CONNECTION_CLOSED;
//	private boolean RECONNECTION;
	private String userId;
	private String roomId;
	private ExtGWTWebMessengerHasCommHandler messengerWidget;

	Atmosphere atmosphere ;
	AtmosphereRequest rpcRequest;
	AtmosphereRequestConfig rpcRequestConfig;

	public ExtGWTWebMessengerCommCSAtmosphere() {
		CONNECTION_CLOSED = true;
//		RECONNECTION = false;
	}
	
	public ExtGWTWebMessengerCommCSAtmosphere(ExtGWTWebMessengerHasCommHandler messengerWidget,String senderId, String chatId) {
		CONNECTION_CLOSED = true;
//		RECONNECTION = false;
		this.userId = senderId;
		this.messengerWidget = messengerWidget;
		this.roomId = chatId;

	}

	/**********************************************************************
	 *                           PRIVATE FUNCTIONS						  *
	 **********************************************************************/

	private void launchAtmosphere() {


		ExtGWTWebMessengerRPCSerializer rpc_serializer = GWT.create(ExtGWTWebMessengerRPCSerializer.class);

		rpcRequestConfig = AtmosphereRequestConfig.create(rpc_serializer);
		rpcRequestConfig.setUrl(GWT.getModuleBaseURL() + "atmosphere/rpc?broadcastId="+ roomId);			
		rpcRequestConfig.setTransport(AtmosphereRequestConfig.Transport.LONG_POLLING);
		rpcRequestConfig.setFallbackTransport(AtmosphereRequestConfig.Transport.STREAMING);
		//		rpcRequestConfig.setReconnectInterval(3000);
		//		rpcRequestConfig.setConnectTimeout(100000);


		rpcRequestConfig.setOpenHandler(new AtmosphereOpenHandler() {
			@Override
			public void onOpen(AtmosphereResponse response) {
				//RPC Connection opened
				CONNECTION_CLOSED = false;
				messengerWidget.handleConnectionOpened();

			}
		});


		rpcRequestConfig.setCloseHandler(new AtmosphereCloseHandler() {
			@Override
			public void onClose(AtmosphereResponse response) {
				//RPC Connection closed				
				CONNECTION_CLOSED = true;
				
//				if(RECONNECTION){
//					launchAtmosphere();
//				} else {
					messengerWidget.handleConnectionClosed();
//				}
			
			}
		});

		rpcRequestConfig.setMessageHandler(new AtmosphereMessageHandler() {
			@Override
			public void onMessage(AtmosphereResponse response) {
				List<ExtGWTWebMessengerRPCEvent> messages = response.getMessages();
				for (ExtGWTWebMessengerRPCEvent rpcEvent : messages) {

					String text = rpcEvent.getMessage();
					long hour 	= rpcEvent.getTimestamp();
					String sender = rpcEvent.getSenderId();

					messengerWidget.handleReceivedMessage(text,hour,sender);

				}
			}
		});

		rpcRequestConfig.setErrorHandler(new AtmosphereErrorHandler() {
			@Override
			public void onError(AtmosphereResponse response) {
				//TODO
			}
		});

		rpcRequestConfig.setTransportFailureHandler(new AtmosphereTransportFailureHandler() {
			@Override
			public void onTransportFailure(String errorMsg, AtmosphereRequest request) {
				//TODO
			}
		});

		rpcRequestConfig.setReopenHandler(new AtmosphereReopenHandler() {
			@Override
			public void onReopen(AtmosphereResponse response) {
				//TODO
			}
		});

		rpcRequestConfig.setReconnectHandler(new AtmosphereReconnectHandler() {
			@Override
			public void onReconnect(AtmosphereRequestConfig request,AtmosphereResponse response) {
				//TODO
			}
		});

		atmosphere = Atmosphere.create();
		rpcRequest = atmosphere.subscribe(rpcRequestConfig);
	}


	/**********************************************************************
	 *                   IExtGWTWebMessengerHandlerDisplay				  *
	 **********************************************************************/
	

	@Override
	public void sendMessage(String message) {
		try {
			if (message.length() > 0) {
				ExtGWTWebMessengerRPCEvent myevent = new ExtGWTWebMessengerRPCEvent();
				myevent.setMessage(message);
				myevent.setSenderId(userId);
				myevent.setRoomId(roomId);
				rpcRequest.push(myevent);
			}
		} catch (SerializationException ex) {	
			String error = ex.getMessage();
			Window.alert(error);
		}
	}

	@Override
	public void disconnect() {
		atmosphere.unsubscribe();
	}

	@Override
	public void autoReconnection(long timeframe) {
		// TODO Auto-generated method stub
	}

	@Override
	public void initConnection(String nickname, String roomname) {
		if(CONNECTION_CLOSED) {
			launchAtmosphere();		
		}	
	}

	@Override
	public void reconnect(String nickname, String roomname) {
//		if (CONNECTION_CLOSED) {
//			userId = nickname;
//			roomId = roomname;
//			launchAtmosphere();
//		} else {
//			userId = nickname;
//			roomId = roomname;
//			RECONNECTION = true;
//			atmosphere.unsubscribe();			
//		}
	}

	@Override
	public void reconnect(String nickname) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isConnectionClosed() {
		return CONNECTION_CLOSED;
	}
}
