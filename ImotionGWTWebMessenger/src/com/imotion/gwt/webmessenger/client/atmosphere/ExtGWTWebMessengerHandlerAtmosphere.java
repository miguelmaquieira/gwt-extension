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
import com.imotion.gwt.webmessenger.shared.ExtGWTWebMessengerRPCEvent;

public class ExtGWTWebMessengerHandlerAtmosphere implements IExtGWTWebMessengerHandlerDisplay{

	private boolean CONNECTION_CLOSED;
	private String senderId;
	private String chatId;
	private IExtGWTWebMessengerWidgetDisplay messengerWidget;

	Atmosphere atmosphere ;
	AtmosphereRequest rpcRequest;
	AtmosphereRequestConfig rpcRequestConfig;


	public ExtGWTWebMessengerHandlerAtmosphere(IExtGWTWebMessengerWidgetDisplay messengerWidget,String senderId, String chatId) {
		super();
		CONNECTION_CLOSED = true;
		this.senderId = senderId;
		this.messengerWidget = messengerWidget;
		this.chatId = chatId;

		launchAtmosphere();
	}

	/**********************************************************************
	 *                           PRIVATE FUNCTIONS						  *
	 **********************************************************************/

	private void launchAtmosphere(){


		ExtGWTWebMessengerRPCSerializer rpc_serializer = GWT.create(ExtGWTWebMessengerRPCSerializer.class);

		rpcRequestConfig = AtmosphereRequestConfig.create(rpc_serializer);
		rpcRequestConfig.setUrl(GWT.getModuleBaseURL() + "atmosphere/rpc?broadcastId="+ chatId);			
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
				messengerWidget.handleConnectionClosed();
			}
		});

		rpcRequestConfig.setMessageHandler(new AtmosphereMessageHandler() {
			@Override
			public void onMessage(AtmosphereResponse response) {
				List<ExtGWTWebMessengerRPCEvent> messages = response.getMessages();
				for (ExtGWTWebMessengerRPCEvent rpcEvent : messages) {

					String text = rpcEvent.getText();
					long hour 	= rpcEvent.getHour();
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
				myevent.setText(message);
				//myevent.setHour(hour);
				myevent.setSenderId(senderId);
				myevent.setBroadcastId(chatId);
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


}
