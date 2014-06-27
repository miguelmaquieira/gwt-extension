package com.imotion.dslam.front.business.desktop.client.widget.execution;

import java.util.List;
import java.util.logging.Logger;

import org.atmosphere.gwt20.client.Atmosphere;
import org.atmosphere.gwt20.client.AtmosphereMessageHandler;
import org.atmosphere.gwt20.client.AtmosphereRequest;
import org.atmosphere.gwt20.client.AtmosphereRequestConfig;
import org.atmosphere.gwt20.client.AtmosphereResponse;

import com.google.gwt.core.client.GWT;
import com.imotion.dslam.logger.amosphere.CRONIOIClientLoggerConstants;
import com.imotion.dslam.logger.amosphere.CRONIOLoggerEvent;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;

public class CRONIOBusDesktopProjectLogConsole extends AEGWTCompositePanel {

	public static final String NAME = "CRONIOBusDesktopProjectLogConsole";
	
	private Atmosphere 			atmosphere ;
	private AtmosphereRequest 	rpcRequest;
	
	public CRONIOBusDesktopProjectLogConsole(String loggerId) {
		Logger logger = Logger.getLogger(loggerId);
		
		CRONIOLoggerRPCSerializer rpc_serializer = GWT.create(CRONIOLoggerRPCSerializer.class);
		AtmosphereRequestConfig rpcRequestConfig = AtmosphereRequestConfig.create(rpc_serializer);
		rpcRequestConfig.setUrl(GWT.getModuleBaseURL() + "atmosphere/rpc?" + CRONIOIClientLoggerConstants.LOGGER_ID + "=" + loggerId);			
		rpcRequestConfig.setTransport(AtmosphereRequestConfig.Transport.WEBSOCKET);
		rpcRequestConfig.setFallbackTransport(AtmosphereRequestConfig.Transport.LONG_POLLING);
		rpcRequestConfig.setReconnectInterval(3000);
		rpcRequestConfig.setConnectTimeout(100000);
		
		rpcRequestConfig.setMessageHandler(new AtmosphereMessageHandler() {
			@Override
			public void onMessage(AtmosphereResponse response) {
				List<CRONIOLoggerEvent> messages = response.getMessages();
				for (CRONIOLoggerEvent event : messages) {
					
				}
			}
		});
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

}
