package com.imotion.gwt.webmessenger.server;

import java.io.IOException;

import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.DefaultBroadcasterFactory;
import org.atmosphere.gwt20.shared.Constants;
import org.atmosphere.handler.AbstractReflectorAtmosphereHandler;

import com.imotion.gwt.webmessenger.shared.ExtGWTWMRPCEvent;


public class ExtGWTWMServer extends AbstractReflectorAtmosphereHandler {
	
	@Override
	public void onRequest(AtmosphereResource ar) throws IOException {
		if (ar.isCancelled()) {
			
		} else if (ar.isResumed()) {
			
		} else if (ar.isSuspended()) {
			
		} else {
			onMessageRequest(ar);
		}
	}

	@Override
	public void destroy() {

	}
	
	/**********************************************************************
	 *                           PRIVATE FUNCTIONS						  *
	 **********************************************************************/
	
	private void broadcast(AtmosphereResource ar) {
		String broadcastId = ar.getRequest().getParameter("roomId");
		Broadcaster broadCaster = getBroadcaster(broadcastId);
		ar.setBroadcaster(broadCaster); 
		ar.suspend();
	}

	private void onMessageRequest(AtmosphereResource ar) {
		Object msg = ar.getRequest().getAttribute(Constants.MESSAGE_OBJECT);
		if (msg == null) {
			broadcast(ar);
		} else {
			ExtGWTWMRPCEvent myEvent= (ExtGWTWMRPCEvent) msg;    	 
			String broadcastId = myEvent.getRoomId();
			getBroadcaster(broadcastId).broadcast(msg);
		}
	}
	
	private Broadcaster getBroadcaster(String broadcasterId) {
		Broadcaster broadCaster = DefaultBroadcasterFactory.getDefault().lookup(broadcasterId);
		if (broadCaster == null) {
			broadCaster = DefaultBroadcasterFactory.getDefault().lookup(broadcasterId, true);
		}
		return broadCaster;
	}
}
