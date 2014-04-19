package com.imotion.gwt.webmessenger.server;

import java.io.IOException;

import org.atmosphere.cpr.AtmosphereRequest;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.DefaultBroadcasterFactory;
import org.atmosphere.gwt20.shared.Constants;
import org.atmosphere.handler.AbstractReflectorAtmosphereHandler;

import com.imotion.gwt.webmessenger.shared.ExtGWTWMRPCEvent;


public class ExtGWTWMServer extends AbstractReflectorAtmosphereHandler {
	
	@Override
	public void onRequest(AtmosphereResource atResource) throws IOException {
		AtmosphereRequest request = atResource.getRequest();
		
		// First, tell Atmosphere to allow bi-directional communication by suspending.
		if (request.getMethod().equalsIgnoreCase("GET")) {
			broadcast(atResource);
		// Second, broadcast message to all connected users.
		} else if (request.getMethod().equalsIgnoreCase("POST")) {
			onMessageRequest(atResource);			
		}
	}
	
	@Override
	public void onStateChange(AtmosphereResourceEvent event) throws IOException {
		if (event.isClosedByApplication()) {
			
		} else if (event.isClosedByClient()) {
			
		} else if (event.isCancelled()) {
			
		}
		super.onStateChange(event);
	}

	@Override
	public void destroy() {

	}
	
	/**********************************************************************
	 *                           PRIVATE FUNCTIONS						  *
	 **********************************************************************/
	
	private void broadcast(AtmosphereResource atResource) throws IOException {
		// query params
		String roomId 	= atResource.getRequest().getParameter("roomId");
		//String userId 		= atResource.getRequest().getParameter("userId");
		
		// get the broadcaster
		Broadcaster broadCaster = DefaultBroadcasterFactory.getDefault().lookup(roomId);
		if (broadCaster == null) {
			broadCaster = DefaultBroadcasterFactory.getDefault().lookup(roomId, true);
		} 
	
		// set waiting message
		atResource.setBroadcaster(broadCaster);
		atResource.suspend();
	}

	private void onMessageRequest(AtmosphereResource ar) {
		Object msg = ar.getRequest().getAttribute(Constants.MESSAGE_OBJECT);
		if (msg != null) {
			ExtGWTWMRPCEvent myEvent= (ExtGWTWMRPCEvent) msg;    	 
			String broadcastId = myEvent.getRoomId();
			DefaultBroadcasterFactory.getDefault().lookup(broadcastId).broadcast(msg);
		}
	}
}
