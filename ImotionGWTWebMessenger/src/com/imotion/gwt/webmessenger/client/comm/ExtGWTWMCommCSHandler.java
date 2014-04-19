package com.imotion.gwt.webmessenger.client.comm;

import java.util.List;

import com.imotion.gwt.webmessenger.client.ExtGWTWMReleasable;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasAllCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasCloseCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasOpenCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasReceiveCommHandler;

public interface ExtGWTWMCommCSHandler extends ExtGWTWMReleasable {

	public void addCommHandler(ExtGWTWMHasAllCommHandler handler);
	public void removeCommHandler(ExtGWTWMHasAllCommHandler handler);
	
	public void addCommOpenHandler(ExtGWTWMHasOpenCommHandler handler);
	public void removeCommOpenHandler(ExtGWTWMHasOpenCommHandler handler);
	public List<ExtGWTWMHasOpenCommHandler> getCommOpenHandlers();
	
	public void addCommReceiveHandler(ExtGWTWMHasReceiveCommHandler handler);
	public void removeCommReceiveHandler(ExtGWTWMHasReceiveCommHandler handler);
	public List<ExtGWTWMHasReceiveCommHandler> getCommReceiveHandlers();
	
	public void addCommCloseHandler(ExtGWTWMHasCloseCommHandler handler);
	public void removeCommCloseHandler(ExtGWTWMHasCloseCommHandler handler);
	public List<ExtGWTWMHasCloseCommHandler> getCommCloseHandlers();
}
