package com.imotion.gwt.webmessenger.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.atmosphere.cpr.AtmosphereRequest;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.DefaultBroadcasterFactory;
import org.atmosphere.gwt20.shared.Constants;
import org.atmosphere.handler.AbstractReflectorAtmosphereHandler;

import com.imotion.gwt.webmessenger.shared.ExtGWTWMRPCEvent;


public class ExtGWTWMServer extends AbstractReflectorAtmosphereHandler {
	
	private final static Logger logger = Logger.getLogger("ExtGWTWMServer");
	
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
		// Log params
		String methodLog = "onStateChange(AtmosphereResourceEvent)";
		String messageLog = null;
		String[] paramsLog = null;
		
		// Log ini
		messageLog = "Method name: {0} \nEvent:\n\t{1}";
		paramsLog = new String[] { methodLog, event.toString() };
		logger.log(Level.INFO, messageLog, paramsLog);
		// Log end
		super.onStateChange(event);
	}

	@Override
	public void destroy() {
		// Log params
		String methodLog = "destroy()";
		String messageLog = null;
		String[] paramsLog = null;
		
		// Log ini
		messageLog = "Method name: {0}";
		paramsLog = new String[] { methodLog };
		logger.log(Level.INFO, messageLog, paramsLog);
		// Log end
	}
	
	/**********************************************************************
	 *                           PRIVATE FUNCTIONS						  *
	 **********************************************************************/
	
	private void broadcast(final AtmosphereResource atResource) throws IOException {
		
		// Log params
		String methodLog = "broadcast(AtmosphereResource)";
		String messageLog = null;
		String[] paramsLog = null;
		
		// Log ini
		messageLog = "Method name: {0} \n{1}";
		paramsLog = new String[] { methodLog, atResource.toString() };
		logger.log(Level.INFO, messageLog, paramsLog);
		// Log end

		String roomId 	= atResource.getRequest().getParameter("roomId");
		String userId 	= atResource.getRequest().getParameter("userId");
	
		// Log ini
		messageLog = "Method name: {0} \nQuery params. Params:\n\troomId: {1}\n\tuserId: {2}";
		paramsLog = new String[] { methodLog, roomId, userId };
		logger.log(Level.INFO, messageLog, paramsLog);
		// Log end
		
		// get the broadcaster
		Broadcaster broadCaster = DefaultBroadcasterFactory.getDefault().lookup(roomId);
		
		if (broadCaster != null) {
			// Log ini
			messageLog = "Method name: {0} \nBroadcaster found. Params:\n\tboradcasterId: {1} \n\tSCOPE: {2}";
			paramsLog = new String[] { methodLog, broadCaster.getID(), broadCaster.getScope().toString() };
			logger.log(Level.INFO, messageLog, paramsLog);
			// Log end
		} else {
			broadCaster = DefaultBroadcasterFactory.getDefault().lookup(roomId, true);
			// Log ini
			messageLog = "Method name: {0} \nBroadcaster created. Params:\n\tboradcasterId: {1} \n\tSCOPE: {2}";
			paramsLog = new String[] { methodLog, broadCaster.getID(), broadCaster.getScope().toString() };
			logger.log(Level.INFO, messageLog, paramsLog);
			// Log end
		} 
		
		// set waiting message
		atResource.setBroadcaster(broadCaster);
		atResource.suspend();
		
	}

	private void onMessageRequest(AtmosphereResource atResource) {
		// Log params
		String methodLog = "onMessageRequest(AtmosphereResource)";
		String messageLog = null;
		String[] paramsLog = null;
		
		// Log ini
		messageLog = "Method name: {0} \n{1}";
		paramsLog = new String[] { methodLog, atResource.toString() };
		logger.log(Level.INFO, messageLog, paramsLog);
		// Log end
		
		Object msg = atResource.getRequest().getAttribute(Constants.MESSAGE_OBJECT);
		if (msg != null) {
			
			ExtGWTWMRPCEvent myEvent = (ExtGWTWMRPCEvent) msg;    	
			
			// Log ini
			messageLog = "Method name: {0} \nMessage: \n\t{1}";
			paramsLog = new String[] { methodLog, myEvent.toString() };
			logger.log(Level.INFO, messageLog, paramsLog);
			// Log end
			 
			String broadcastId = myEvent.getRoomId();
			DefaultBroadcasterFactory.getDefault().lookup(broadcastId).broadcast(msg);
		}
	}
}
