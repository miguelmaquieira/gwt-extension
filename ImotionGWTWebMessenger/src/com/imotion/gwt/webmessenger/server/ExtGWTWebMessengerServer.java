package com.imotion.gwt.webmessenger.server;

import java.io.IOException;

import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.DefaultBroadcasterFactory;
import org.atmosphere.gwt20.shared.Constants;
import org.atmosphere.handler.AbstractReflectorAtmosphereHandler;

import com.imotion.gwt.webmessenger.shared.ExtGWTWebMessengerRPCEvent;


public class ExtGWTWebMessengerServer extends AbstractReflectorAtmosphereHandler {

	@Override
	public void onRequest(AtmosphereResource atResource) throws IOException {
		if (!atResource.isSuspended()) {
			broadcast(atResource);
		} else {
			onReceiveMessage(atResource);
		}
	}
	
	@Override
	public void onStateChange(AtmosphereResourceEvent event) throws IOException {
		
	}

	@Override
	public void destroy() {
		
	}
	
	/**********************************************************************
	 *                           PRIVATE FUNCTIONS						  *
	 **********************************************************************/
	
	private void broadcast(AtmosphereResource atResource) {
		String broadcastId = atResource.getRequest().getParameter("broadcastId");
		Broadcaster broadCaster = DefaultBroadcasterFactory.getDefault().lookup(broadcastId);
		if (broadCaster == null) {
			// lookup the broadcaster, if not found create it. Name is arbitrary
			broadCaster = DefaultBroadcasterFactory.getDefault().lookup(broadcastId, true);
		}
		atResource.setBroadcaster(broadCaster);   
		atResource.suspend();
	}
	
	private void onReceiveMessage(AtmosphereResource atResource) {
		Object msg = atResource.getRequest().getAttribute(Constants.MESSAGE_OBJECT);
		ExtGWTWebMessengerRPCEvent myEvent= (ExtGWTWebMessengerRPCEvent) msg;    	 
		String broadcastId = myEvent.getRoomId();
		if (msg != null) {
			DefaultBroadcasterFactory.getDefault().lookup(broadcastId).broadcast(msg);  
		}
	}
}
