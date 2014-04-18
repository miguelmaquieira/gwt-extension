package com.imotion.gwt.webmessenger.client.comm;

import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasAllCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasCloseCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasOpenCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasReceiveCommHandler;
import com.imotion.gwt.webmessenger.client.session.ExtGWTWMSession;

public interface ExtGWTWMCommCSHandler {

	public void addCommHandler(String roomId, ExtGWTWMHasAllCommHandler handler);
	public void addCommHandler(ExtGWTWMHasAllCommHandler handler);
	public void removeCommHandler(String roomId, ExtGWTWMHasAllCommHandler handler);
	public void removeCommHandler(ExtGWTWMHasAllCommHandler handler);
	
	public void addCommOpenHandler(String roomId, ExtGWTWMHasOpenCommHandler handler);
	public void addCommOpenHandler(ExtGWTWMHasOpenCommHandler handler);
	public void removeCommOpenHandler(String roomId, ExtGWTWMHasOpenCommHandler handler);
	public void removeCommOpenHandler(ExtGWTWMHasOpenCommHandler handler);
	
	public void addCommReceiveHandler(String roomId, ExtGWTWMHasReceiveCommHandler handler);
	public void addCommReceiveHandler(ExtGWTWMHasReceiveCommHandler handler);
	public void removeCommReceiveHandler(String roomId, ExtGWTWMHasReceiveCommHandler handler);
	public void removeCommReceiveHandler(ExtGWTWMHasReceiveCommHandler handler);
	
	public void addCommCloseHandler(String roomId, ExtGWTWMHasCloseCommHandler handler);
	public void addCommCloseHandler(ExtGWTWMHasCloseCommHandler handler);
	public void removeCommCloseHandler(String roomId, ExtGWTWMHasCloseCommHandler handler);
	public void removeCommCloseHandler(ExtGWTWMHasCloseCommHandler handler);
	
	public ExtGWTWMSession getSessionData();
}
