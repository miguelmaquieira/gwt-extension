package com.imotion.gwt.webmessenger.client.comm;

import com.imotion.gwt.webmessenger.client.session.ExtGWTWMSession;

public interface ExtGWTWMCommCSHandler {

	public void addCommHandler(String roomId, ExtGWTWMCommHandler handler);
	public void addCommHandler(ExtGWTWMCommHandler handler);
	public void removeCommHandler(String roomId, ExtGWTWMCommHandler handler);
	public void removeCommHandler(ExtGWTWMCommHandler handler);
	
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
