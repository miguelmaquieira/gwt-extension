package com.imotion.gwt.webmessenger.client.comm.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommHandlerManager;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMHasCloseCommHandler;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMHasCommHandler;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMHasOpenCommHandler;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMHasReceiveCommHandler;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMHasSendCommHandler;

public class ExtGWTWMCommHandlerManagerImpl implements ExtGWTWMCommHandlerManager {
	
	private Map<String, Stack<ExtGWTWMHasCommHandler>> mapHandlers;
	
	private final static String DEFAULT_STACK = "DEFAULT_STACK";

	@Override
	public void addCommHandler(ExtGWTWMHasCommHandler handler) {
		getStack(DEFAULT_STACK).add(handler);
	}

	@Override
	public void addCommHandler(String roomId, ExtGWTWMHasCommHandler handler) {
		getStack(roomId).add(handler);
	}

	@Override
	public void removeCommHandler(ExtGWTWMHasCommHandler handler) {
		getStack(DEFAULT_STACK).remove(handler);
	}

	@Override
	public void removeCommHandler(String roomId, ExtGWTWMHasCommHandler handler) {
		getStack(roomId).remove(handler);
	}
	
	@Override
	public List<ExtGWTWMHasOpenCommHandler> getCommOpenHandlers(String roomId) {
		List<ExtGWTWMHasOpenCommHandler> handlerList = new ArrayList<>();
		if (roomId != null) {
			Stack<ExtGWTWMHasCommHandler> stack = getStack(roomId);
			if (stack != null) {
				Iterator<ExtGWTWMHasCommHandler> iter = stack.iterator();
				while (iter.hasNext()) {
					ExtGWTWMHasCommHandler handler = iter.next();
					if (handler instanceof ExtGWTWMHasOpenCommHandler || handler instanceof ExtGWTWMHasCommHandler) {
						handlerList.add((ExtGWTWMHasOpenCommHandler)handler);
					}
				}
			}
		}
		return handlerList;
	}

	@Override
	public List<ExtGWTWMHasCloseCommHandler> getCommCloseHandlers(String roomId) {
		List<ExtGWTWMHasCloseCommHandler> handlerList = new ArrayList<>();
		if (roomId != null) {
			Stack<ExtGWTWMHasCommHandler> stack = getStack(roomId);
			if (stack != null) {
				Iterator<ExtGWTWMHasCommHandler> iter = stack.iterator();
				while (iter.hasNext()) {
					ExtGWTWMHasCommHandler handler = iter.next();
					if (handler instanceof ExtGWTWMHasCloseCommHandler || handler instanceof ExtGWTWMHasCommHandler) {
						handlerList.add((ExtGWTWMHasCloseCommHandler)handler);
					}
				}
			}
		}
		return handlerList;
	}

	@Override
	public List<ExtGWTWMHasSendCommHandler> getCommSendHandlers(String roomId) {
		List<ExtGWTWMHasSendCommHandler> handlerList = new ArrayList<>();
		if (roomId != null) {
			Stack<ExtGWTWMHasCommHandler> stack = getStack(roomId);
			if (stack != null) {
				Iterator<ExtGWTWMHasCommHandler> iter = stack.iterator();
				while (iter.hasNext()) {
					ExtGWTWMHasCommHandler handler = iter.next();
					if (handler instanceof ExtGWTWMHasSendCommHandler || handler instanceof ExtGWTWMHasCommHandler) {
						handlerList.add((ExtGWTWMHasSendCommHandler)handler);
					}
				}
			}
		}
		return handlerList;
	}

	@Override
	public List<ExtGWTWMHasReceiveCommHandler> getCommReceiveHandlers(String roomId) {
		List<ExtGWTWMHasReceiveCommHandler> handlerList = new ArrayList<>();
		if (roomId != null) {
			Stack<ExtGWTWMHasCommHandler> stack = getStack(roomId);
			if (stack != null) {
				Iterator<ExtGWTWMHasCommHandler> iter = stack.iterator();
				while (iter.hasNext()) {
					ExtGWTWMHasCommHandler handler = iter.next();
					if (handler instanceof ExtGWTWMHasReceiveCommHandler || handler instanceof ExtGWTWMHasCommHandler) {
						handlerList.add((ExtGWTWMHasReceiveCommHandler)handler);
					}
				}
			}
		}
		return handlerList;
	}
	
	/**********************************************************************
	 *                           PRIVATE FUNCTIONS						  *
	 **********************************************************************/
	
	private Stack<ExtGWTWMHasCommHandler> getStack(String stackName) {
		Stack<ExtGWTWMHasCommHandler> stack = getStacksMap().get(stackName);
		if (stack == null) {
			stack = new Stack<ExtGWTWMHasCommHandler>();
			getStacksMap().put(stackName, stack);
		}
		return stack;
	}
	
	private Map<String, Stack<ExtGWTWMHasCommHandler>> getStacksMap() {
		if (mapHandlers == null) {
			mapHandlers = new HashMap<String, Stack<ExtGWTWMHasCommHandler>>();
		}
		return mapHandlers;
	}	
}
