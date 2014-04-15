package com.imotion.chat.server;

import java.io.IOException;
import java.util.logging.Logger;

import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.DefaultBroadcasterFactory;
import org.atmosphere.gwt20.shared.Constants;
import org.atmosphere.handler.AbstractReflectorAtmosphereHandler;
import com.imotion.chat.client.RPCEvent;



public class GwtRpcAtmosphereHandler extends AbstractReflectorAtmosphereHandler {


	static final Logger logger = Logger.getLogger("AtmosphereHandler");
	@Override
	public void onRequest(AtmosphereResource ar) throws IOException {

		if (ar.getRequest().getMethod().equals("GET") ) {
			doGet(ar);
		} else if (ar.getRequest().getMethod().equals("POST") ) {
			doPost(ar);
		}
	}

	public void doGet(AtmosphereResource ar) {
		String broadcastId = ar.getRequest().getParameter("broadcastId");

		Broadcaster broadCaster = DefaultBroadcasterFactory.getDefault().lookup(broadcastId);

		if (broadCaster == null) {
			// lookup the broadcaster, if not found create it. Name is arbitrary
			ar.setBroadcaster(DefaultBroadcasterFactory.getDefault().lookup(broadcastId, true));
		} else {
			ar.setBroadcaster(broadCaster);
		}   

		ar.suspend();

	}

	/**
	 * receive push message from client
	 **/
	public void doPost(AtmosphereResource ar) {

		Object msg = ar.getRequest().getAttribute(Constants.MESSAGE_OBJECT);
		RPCEvent myEvent= (RPCEvent) msg;    	 
		String broadcastId = myEvent.getBroadcastId();

		if (msg != null) {
			DefaultBroadcasterFactory.getDefault().lookup(broadcastId).broadcast(msg);  
		}
	}


	@Override
	public void destroy() {

	}

}
