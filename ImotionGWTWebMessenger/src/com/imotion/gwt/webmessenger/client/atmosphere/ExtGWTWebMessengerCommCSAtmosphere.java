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
import com.imotion.gwt.webmessenger.client.session.ExtGWTWebMessengerSession;
import com.imotion.gwt.webmessenger.shared.ExtGWTWebMessengerRPCEvent;

public class ExtGWTWebMessengerCommCSAtmosphere implements ExtGWTWebMessengerCommCS {

	private ExtGWTWebMessengerSession 			sessionData;
	private ExtGWTWebMessengerHasCommHandler 	messengerWidget;

	private Atmosphere 				atmosphere ;
	private AtmosphereRequest 		rpcRequest;
	private AtmosphereRequestConfig rpcRequestConfig;

	public ExtGWTWebMessengerCommCSAtmosphere() {
		
	}
	
	public ExtGWTWebMessengerCommCSAtmosphere(ExtGWTWebMessengerHasCommHandler messengerWidget) {
		this.messengerWidget = messengerWidget;
	}
	
	/**********************************************************************
	 *                   	ExtGWTWebMessengerCommCS	    			  *
	 **********************************************************************/
	@Override
	public ExtGWTWebMessengerSession getSessionData() {
		return sessionData;
	}
	
	@Override
	public void sendMessage(String message) {
		try {
			if (message.length() > 0) {
				ExtGWTWebMessengerRPCEvent myevent = new ExtGWTWebMessengerRPCEvent();
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
	public void autoReconnection(long timeframe) {
		// TODO Auto-generated method stub
	}

	@Override
	public void initConnection(String nickname, String roomname) {
		this.sessionData = new ExtGWTWebMessengerSession(nickname, roomname);
		initAtmosphere();	
	}

	@Override
	public void reconnect(String nickname, String roomname) {

	}

	@Override
	public void reconnect(String nickname) {
		// TODO Auto-generated method stub
	}
	
	/**********************************************************************
	 *                   ExtGWTWebMessengerCommHandlerManager			  *
	 **********************************************************************/
	@Override
	public void addCommHandler(ExtGWTWebMessengerHasCommHandler handler) {
		this.messengerWidget = handler;
	}

	@Override
	public void addCommHandler(String roomId, ExtGWTWebMessengerHasCommHandler handler) {
		// TODO Auto-generated method stub
	}

	@Override
	public void removeCommHandler(ExtGWTWebMessengerHasCommHandler handler) {
		// TODO Auto-generated method stub
	}

	@Override
	public void removeCommHandler(String roomId, ExtGWTWebMessengerHasCommHandler handler) {
		// TODO Auto-generated method stub
	}
	
	/**********************************************************************
	 *                           PRIVATE FUNCTIONS						  *
	 **********************************************************************/

	private void initAtmosphere() {

		ExtGWTWebMessengerRPCSerializer rpc_serializer = GWT.create(ExtGWTWebMessengerRPCSerializer.class);

		rpcRequestConfig = AtmosphereRequestConfig.create(rpc_serializer);
		rpcRequestConfig.setUrl(GWT.getModuleBaseURL() + "atmosphere/rpc?broadcastId="+ sessionData.getRoomId());			
		rpcRequestConfig.setTransport(AtmosphereRequestConfig.Transport.WEBSOCKET);
		rpcRequestConfig.setFallbackTransport(AtmosphereRequestConfig.Transport.WEBSOCKET);

		rpcRequestConfig.setOpenHandler(new AtmosphereOpenHandler() {
			@Override
			public void onOpen(AtmosphereResponse response) {
				messengerWidget.handleConnectionOpened();
			}
		});

		rpcRequestConfig.setCloseHandler(new AtmosphereCloseHandler() {
			@Override
			public void onClose(AtmosphereResponse response) {
				messengerWidget.handleConnectionClosed();		
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
		rpcRequest = atmosphere.subscribe(rpcRequestConfig);
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
}
