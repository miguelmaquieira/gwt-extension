package com.imotion.gwt.webmessenger.client.comm.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommHandlerManager;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMHasCommHandler;

public class ExtGWTWMCommHandlerManagerImpl <T extends ExtGWTWMHasCommHandler> implements ExtGWTWMCommHandlerManager<T> {
	
	private Map<String, Stack<T>> mapHandlers;
	
	private final static String DEFAULT_STACK = "DEFAULT_STACK";

	@Override
	public void addCommHandler(T handler) {
		getStack(DEFAULT_STACK).add(handler);
	}

	@Override
	public void addCommHandler(String roomId, T handler) {
		getStack(roomId).add(handler);
	}

	@Override
	public void removeCommHandler(T handler) {
		getStack(DEFAULT_STACK).remove(handler);
	}

	@Override
	public void removeCommHandler(String roomId, T handler) {
		getStack(roomId).remove(handler);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <H extends ExtGWTWMHasCommHandler> List<H> getHandlers(Class<H> type, String roomId) {
		List<H> handlerList = new ArrayList<>();
		if (roomId != null) {
			Stack<T> stack = getStack(roomId);
			if (stack != null) {
				Iterator<T> iter = stack.iterator();
				while (iter.hasNext()) {
					H handler = (H)iter.next();
					if (type.isAssignableFrom(handler.getClass())) {
						handlerList.add(handler);
					}
				}
			}
		}
		return handlerList;
	}
	
	/**********************************************************************
	 *                           PRIVATE FUNCTIONS						  *
	 **********************************************************************/
	
	private Stack<T> getStack(String stackName) {
		Stack<T> stack = getStacksMap().get(stackName);
		if (stack == null) {
			stack = new Stack<T>();
			getStacksMap().put(stackName, stack);
		}
		return stack;
	}
	
	private Map<String, Stack<T>> getStacksMap() {
		if (mapHandlers == null) {
			mapHandlers = new HashMap<String, Stack<T>>();
		}
		return mapHandlers;
	}
}
