package com.imotion.gwt.webmessenger.client;

import java.util.List;
import java.util.logging.Level;
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
import org.atmosphere.gwt20.client.AtmosphereResponse;
import org.atmosphere.gwt20.client.AtmosphereTransportFailureHandler;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.logging.client.HasWidgetsLogHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.imotion.gwt.webmessenger.client.atmosphere.ExtGWTWebMessengerRPCEvent;
import com.imotion.gwt.webmessenger.client.atmosphere.ExtGWTWebMessengerRPCSerializer;




public class ImotionGWTWebMessenger implements EntryPoint {
	
	static final Logger logger = Logger.getLogger(ImotionGWTWebMessenger.class.getName());
	boolean CONNECTION_CLOSED = false;
	Atmosphere atmosphere ;
	AtmosphereRequest rpcRequest;
	Button sendRPC;
	Button desconectRPC;

	@Override
	public void onModuleLoad() {

		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
			@Override
			public void onUncaughtException(Throwable e) {
				logger.log(Level.SEVERE, "Uncaught exception", e);
			}
		});


		HorizontalPanel buttons = new HorizontalPanel();
		final TextBox messageInput = new TextBox();
		buttons.add(messageInput);

		sendRPC = new Button("Enviar");
		buttons.add(sendRPC);

		desconectRPC = new Button("Desconectar");
		buttons.add(desconectRPC);

		desconectRPC.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				atmosphere.unsubscribe();
			}
		});


		RootPanel.get().add(buttons);


		HTMLPanel logPanel = new HTMLPanel("") {
			@Override
			public void add(Widget widget) {
				super.add(widget);
				widget.getElement().scrollIntoView();
			}
		};
		RootPanel.get().add(logPanel);
		Logger.getLogger("").addHandler(new HasWidgetsLogHandler(logPanel));


		ExtGWTWebMessengerRPCSerializer rpc_serializer = GWT.create(ExtGWTWebMessengerRPCSerializer.class);

		final AtmosphereRequestConfig rpcRequestConfig = AtmosphereRequestConfig.create(rpc_serializer);
		rpcRequestConfig.setUrl(GWT.getModuleBaseURL() + "atmosphere/rpc?broadcastId=chatId");			
		rpcRequestConfig.setTransport(AtmosphereRequestConfig.Transport.LONG_POLLING);
		rpcRequestConfig.setFallbackTransport(AtmosphereRequestConfig.Transport.STREAMING);
//		rpcRequestConfig.setReconnectInterval(3000);
//		rpcRequestConfig.setConnectTimeout(100000);


		rpcRequestConfig.setErrorHandler(new AtmosphereErrorHandler() {
			@Override
			public void onError(AtmosphereResponse response) {
				logger.info("RPC transport failure");
			}
		});


		rpcRequestConfig.setTransportFailureHandler(new AtmosphereTransportFailureHandler() {
			@Override
			public void onTransportFailure(String errorMsg, AtmosphereRequest request) {
				logger.info("RPC transport failure");

			}
		});

		rpcRequestConfig.setOpenHandler(new AtmosphereOpenHandler() {
			@Override
			public void onOpen(AtmosphereResponse response) {
				logger.info("RPC Connection opened");
				CONNECTION_CLOSED = false;
				sendRPC.setText("Enviar");
			}
		});
		rpcRequestConfig.setReopenHandler(new AtmosphereReopenHandler() {
			@Override
			public void onReopen(AtmosphereResponse response) {
				logger.info("RPC Connection reopened");
			}
		});

		rpcRequestConfig.setReconnectHandler(new AtmosphereReconnectHandler() {

			@Override
			public void onReconnect(AtmosphereRequestConfig request,
					AtmosphereResponse response) {
				logger.info("RPC reconnection");					
			}
		});


		rpcRequestConfig.setCloseHandler(new AtmosphereCloseHandler() {
			@Override
			public void onClose(AtmosphereResponse response) {
				logger.info("RPC Connection closed");
				CONNECTION_CLOSED = true;
				sendRPC.setText("Conectar");
								
			}
		});

		rpcRequestConfig.setMessageHandler(new AtmosphereMessageHandler() {
			@Override
			public void onMessage(AtmosphereResponse response) {
				List<ExtGWTWebMessengerRPCEvent> messages = response.getMessages();
				for (ExtGWTWebMessengerRPCEvent event : messages) {
					logger.info("received message through RPC: " + event.getText());
				}
			}
		});


		atmosphere = Atmosphere.create();
		rpcRequest = atmosphere.subscribe(rpcRequestConfig);


		sendRPC.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				try {
					if (CONNECTION_CLOSED) {
						rpcRequest = atmosphere.subscribe(rpcRequestConfig);
					} else {
						if (messageInput.getText().trim().length() > 0) {
							ExtGWTWebMessengerRPCEvent myevent = new ExtGWTWebMessengerRPCEvent();
							myevent.setText(messageInput.getText());
							myevent.setBroadcastId("chatId");
							rpcRequest.push(myevent);
						}
					}
				} catch (Exception ex) {
					logger.log(Level.SEVERE, "Failed to serialize message", ex);

				}
			}
		});


	}
}
