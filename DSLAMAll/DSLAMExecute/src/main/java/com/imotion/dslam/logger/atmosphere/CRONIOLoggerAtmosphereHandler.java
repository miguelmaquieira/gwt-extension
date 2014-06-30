package com.imotion.dslam.logger.atmosphere;

import java.io.IOException;

import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.DefaultBroadcasterFactory;
import org.atmosphere.handler.AbstractReflectorAtmosphereHandler;

import com.imotion.dslam.logger.atmosphere.base.CRONIOIClientLoggerConstants;

public class CRONIOLoggerAtmosphereHandler extends AbstractReflectorAtmosphereHandler {

	@Override
	public void onRequest(AtmosphereResource ar) throws IOException {
		createConnection(ar);
	}

	public void createConnection(AtmosphereResource ar) {
		String broadcastId = ar.getRequest().getParameter(CRONIOIClientLoggerConstants.LOGGER_ID);
		Broadcaster broadCaster = DefaultBroadcasterFactory.getDefault().lookup(broadcastId, true);
		ar.setBroadcaster(broadCaster);
		ar.suspend();
	}

}
