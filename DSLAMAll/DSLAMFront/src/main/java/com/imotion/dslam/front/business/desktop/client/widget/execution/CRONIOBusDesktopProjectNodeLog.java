package com.imotion.dslam.front.business.desktop.client.widget.execution;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.atmosphere.gwt20.client.Atmosphere;
import org.atmosphere.gwt20.client.AtmosphereMessageHandler;
import org.atmosphere.gwt20.client.AtmosphereRequest;
import org.atmosphere.gwt20.client.AtmosphereRequestConfig;
import org.atmosphere.gwt20.client.AtmosphereResponse;

import com.google.gwt.core.client.GWT;
import com.google.gwt.logging.client.HasWidgetsLogHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.imotion.dslam.logger.atmosphere.base.CRONIOIClientLoggerConstants;
import com.imotion.dslam.logger.atmosphere.base.CRONIOLoggerEvent;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.jquery.AEGWTJQueryPerfectScrollBar;

public class CRONIOBusDesktopProjectNodeLog extends AEGWTCompositePanel {

	public static final String NAME = "CRONIOBusDesktopProjectNodeLog";

//	private Atmosphere 			atmosphere ;
//	private AtmosphereRequest 	rpcRequest;

	private Logger 		logger;
	private HTMLPanel 	logPanel;

	public CRONIOBusDesktopProjectNodeLog(String loggerId) {
		setId(loggerId);
		logPanel = new HTMLPanel("") {
			@Override
			public void add(Widget widget) {
				super.add(widget);
				AEGWTJQueryPerfectScrollBar.updateScroll(getName());
				AEGWTJQueryPerfectScrollBar.bottom(getId());
			}
		};
		initWidget(logPanel);

		logger = Logger.getLogger(loggerId);
		HasWidgetsLogHandler loggerContainer = new HasWidgetsLogHandler(logPanel);
		logger.addHandler(loggerContainer);

		CRONIOLoggerRPCSerializer rpc_serializer = GWT.create(CRONIOLoggerRPCSerializer.class);
		AtmosphereRequestConfig rpcRequestConfig = AtmosphereRequestConfig.create(rpc_serializer);
		rpcRequestConfig.setUrl(GWT.getModuleBaseURL() + "atmosphere/rpc?" + CRONIOIClientLoggerConstants.LOGGER_ID + "=" + loggerId);			
		rpcRequestConfig.setTransport(AtmosphereRequestConfig.Transport.WEBSOCKET);
		rpcRequestConfig.setFallbackTransport(AtmosphereRequestConfig.Transport.LONG_POLLING);
		rpcRequestConfig.setReconnectInterval(3000);
		rpcRequestConfig.setConnectTimeout(100000);
		
		Atmosphere atmosphere = Atmosphere.create();
		AtmosphereRequest rpcRequest = atmosphere.subscribe(rpcRequestConfig);

		rpcRequestConfig.setMessageHandler(new AtmosphereMessageHandler() {
			@Override
			public void onMessage(AtmosphereResponse response) {
				List<CRONIOLoggerEvent> messages = response.getMessages();
				for (CRONIOLoggerEvent logEvent : messages) {
					logger.log(Level.INFO, logEvent.getFullTrace());
				}
			}
		});

		//		desconectRPC.addClickHandler(new ClickHandler() {
		//
		//			@Override
		//			public void onClick(ClickEvent event) {
		//				atmosphere.unsubscribe();
		//			}
		//		});

		//		rpcRequestConfig.setOpenHandler(new AtmosphereOpenHandler() {
		//			@Override
		//			public void onOpen(AtmosphereResponse response) {
		//				logger.info("RPC Connection opened");
		//				CONNECTION_CLOSED = false;
		//				sendRPC.setText("Enviar");
		//			}
		//		});
		//		rpcRequestConfig.setReopenHandler(new AtmosphereReopenHandler() {
		//			@Override
		//			public void onReopen(AtmosphereResponse response) {
		//				logger.info("RPC Connection reopened");
		//			}
		//		});
		//
		//		rpcRequestConfig.setReconnectHandler(new AtmosphereReconnectHandler() {
		//
		//			@Override
		//			public void onReconnect(AtmosphereRequestConfig request,
		//					AtmosphereResponse response) {
		//				logger.info("RPC reconnection");					
		//			}
		//		});


		//		rpcRequestConfig.setCloseHandler(new AtmosphereCloseHandler() {
		//			@Override
		//			public void onClose(AtmosphereResponse response) {
		//				logger.info("RPC Connection closed");
		//				CONNECTION_CLOSED = true;
		//				sendRPC.setText("Conectar");
		//								
		//			}
		//		});

		//		atmosphere = Atmosphere.create();
		//		rpcRequest = atmosphere.subscribe(rpcRequestConfig);

		//		sendRPC.addClickHandler(new ClickHandler() {
		//			@Override
		//			public void onClick(ClickEvent event) {
		//
		//				try {
		//					if (CONNECTION_CLOSED) {
		//						rpcRequest = atmosphere.subscribe(rpcRequestConfig);
		//					} else {
		//						if (messageInput.getText().trim().length() > 0) {
		//							RPCEvent myevent = new RPCEvent();
		//							myevent.setData(messageInput.getText());
		//							myevent.setBroadcastId(loggerId);
		//							rpcRequest.push(myevent);
		//						}
		//					}
		//				} catch (Exception ex) {
		//					logger.log(Level.SEVERE, "Failed to serialize message", ex);
		//
		//				}
		//			}
		//		});

	}

	/**
	 * AEGWTICompositePanel
	 */
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void postDisplay() {
		super.postDisplay();
		AEGWTJQueryPerfectScrollBar.addScrollToWidget(getId(), this, getCurrentHeight(), true);
	}

}
