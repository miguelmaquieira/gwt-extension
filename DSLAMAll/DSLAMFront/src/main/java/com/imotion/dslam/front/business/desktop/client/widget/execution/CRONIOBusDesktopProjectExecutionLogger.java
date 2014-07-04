package com.imotion.dslam.front.business.desktop.client.widget.execution;

import java.util.List;

import org.atmosphere.gwt20.client.Atmosphere;
import org.atmosphere.gwt20.client.AtmosphereMessageHandler;
import org.atmosphere.gwt20.client.AtmosphereRequest;
import org.atmosphere.gwt20.client.AtmosphereRequestConfig;
import org.atmosphere.gwt20.client.AtmosphereResponse;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.logger.atmosphere.base.CRONIOIClientLoggerConstants;
import com.imotion.dslam.logger.atmosphere.base.CRONIOLoggerEvent;
import com.imotion.dslam.logger.atmosphere.base.CRONIOLoggerEventCollection;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.composite.AEMFTMetadataElementCompositeRecordSetListRegroup;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.jquery.AEGWTJQueryPerfectScrollBar;

public abstract class CRONIOBusDesktopProjectExecutionLogger extends AEGWTCompositePanel {

	public static final String NAME = "CRONIOBusDesktopProjectExecutionLogger";

	private Atmosphere 			atmosphere;
	private AtmosphereRequest 	rpcRequest;
	private AEMFTMetadataElementCompositeRecordSetListRegroup logDataList;

	private FlowPanel loggerContaniner;

	public CRONIOBusDesktopProjectExecutionLogger(String loggerId) {
		setId(loggerId);
		logDataList = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getCompositeListRegroup();
		
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.EXECUTION_LOGGER);
		
		loggerContaniner = new FlowPanel();
		root.add(loggerContaniner);
		loggerContaniner.addStyleName(DSLAMBusDesktopIStyleConstants.EXECUTION_LOGGER_CONTAINER);
		
		CRONIOLoggerRPCSerializer rpc_serializer = GWT.create(CRONIOLoggerRPCSerializer.class);
		AtmosphereRequestConfig rpcRequestConfig = AtmosphereRequestConfig.create(rpc_serializer);
		rpcRequestConfig.setUrl(GWT.getModuleBaseURL() + "atmosphere/rpc?" + CRONIOIClientLoggerConstants.LOGGER_ID + "=" + loggerId);			
		rpcRequestConfig.setTransport(AtmosphereRequestConfig.Transport.WEBSOCKET);
		rpcRequestConfig.setFallbackTransport(AtmosphereRequestConfig.Transport.LONG_POLLING);
		rpcRequestConfig.setReconnectInterval(3000);
		rpcRequestConfig.setConnectTimeout(100000);
		
		
		atmosphere = Atmosphere.create();
		rpcRequest = atmosphere.subscribe(rpcRequestConfig);

		rpcRequestConfig.setMessageHandler(new AtmosphereMessageHandler() {
			@Override
			public void onMessage(AtmosphereResponse response) {
				List<CRONIOLoggerEventCollection> logEventCollectionList = response.getMessages();
				for (CRONIOLoggerEventCollection logEventCollection : logEventCollectionList) {
					List<CRONIOLoggerEvent> logEventList = logEventCollection.getList();
					for (CRONIOLoggerEvent logEvent : logEventList) {
						AEMFTMetadataElementComposite logData = getDataFromEvent(logEvent);
						logEventData(logData);
					}
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
	
	private AEMFTMetadataElementComposite getDataFromEvent(CRONIOLoggerEvent logEvent) {
		AEMFTMetadataElementComposite logData  = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		getElementController().setElement(CRONIOIClientLoggerConstants.CONNECTION_ID	, logData, logEvent.getConnectionId());
		getElementController().setElement(CRONIOIClientLoggerConstants.NODE_IP			, logData, logEvent.getNodeIp());
		getElementController().setElement(CRONIOIClientLoggerConstants.NODE_NAME		, logData, logEvent.getNodeName());
		getElementController().setElement(CRONIOIClientLoggerConstants.PROMPT_DATA		, logData, logEvent.getPrompt());
		getElementController().setElement(CRONIOIClientLoggerConstants.REQUEST_DATA		, logData, logEvent.getRequest());
		getElementController().setElement(CRONIOIClientLoggerConstants.RESPONSE_DATA	, logData, logEvent.getResponse());
		getElementController().setElement(CRONIOIClientLoggerConstants.TIMESTAMP		, logData, logEvent.getTimestamp());
		getElementController().setElement(CRONIOIClientLoggerConstants.FULLTRACE		, logData, logEvent.getFullTrace());
		return logData;
	}

	private void logEventData(AEMFTMetadataElementComposite logData) {
		logDataList.addElement(logData);
		
		addLogItem(logData);
		AEGWTJQueryPerfectScrollBar.updateScroll(getName());
		AEGWTJQueryPerfectScrollBar.bottom(getId());
	}
	
	protected abstract void addLogItem(AEMFTMetadataElementComposite logData);
	
	protected FlowPanel getLoggerContainer() {
		return loggerContaniner;
	}
	
	/**
	 * Widget
	 */
	@Override
	public void onUnload() {
		atmosphere.unsubscribe();
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
		if (data != null) {
			List<AEMFTMetadataElement> dataElements = data.getElementList();
			for (AEMFTMetadataElement dataElement : dataElements) {
				logEventData((AEMFTMetadataElementComposite) dataElement);
			}
		}
	}
	
	@Override
	public void postDisplay() {
		super.postDisplay();
		AEGWTJQueryPerfectScrollBar.addScrollToWidget(getId(), this, getCurrentHeight(), true);
	}

}
