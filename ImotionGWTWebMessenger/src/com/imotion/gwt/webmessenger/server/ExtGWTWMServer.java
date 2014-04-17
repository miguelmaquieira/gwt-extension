package com.imotion.gwt.webmessenger.server;

import java.io.IOException;

import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.DefaultBroadcasterFactory;
import org.atmosphere.gwt20.shared.Constants;
import org.atmosphere.handler.AbstractReflectorAtmosphereHandler;


public class ExtGWTWMServer extends AbstractReflectorAtmosphereHandler {

	@Override
	public void onRequest(AtmosphereResource atResource) throws IOException {
		if (atResource.isSuspended()) {
			
		} else if (atResource.isResumed()) {
	
		} else if (atResource.isCancelled())  {
			
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
	
	private Broadcaster getBroadcaster(String broadcasterId) {
		Broadcaster broadCaster = DefaultBroadcasterFactory.getDefault().lookup(broadcasterId);
		if (broadCaster == null) {
			// lookup the broadcaster, if not found create it. Name is arbitrary
			broadCaster = DefaultBroadcasterFactory.getDefault().lookup(broadcasterId, true);
		}
		return broadCaster;
	}
	
	private void onReceiveMessage(AtmosphereResource atResource) {
		String broadcastId = atResource.getRequest().getParameter("broadcastId");
		Broadcaster broadcaster = getBroadcaster(broadcastId);
		if (broadcaster != null) {
			Object msg = atResource.getRequest().getAttribute(Constants.MESSAGE_OBJECT);
			if (msg != null) {    	 
				broadcaster.broadcast(msg);  
			} else {
				atResource.setBroadcaster(broadcaster);
				atResource.suspend();
			}
		} else {
			// TODO trace 
		}
	}
}
