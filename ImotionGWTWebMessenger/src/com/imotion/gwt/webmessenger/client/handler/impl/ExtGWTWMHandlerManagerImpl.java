package com.imotion.gwt.webmessenger.client.handler.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHandlerManager;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasAllCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasCloseCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasErrorHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasOpenCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasReceiveCommHandler;

public class ExtGWTWMHandlerManagerImpl implements ExtGWTWMHandlerManager {
	
	private Map<String, Stack<ExtGWTWMHasCommHandler>> mapCommHandlers;
	private Map<String, Stack<ExtGWTWMHasErrorHandler>> mapErrorHandlers;
	
	/**********************************************************************
	 *                    ExtGWTWMCommHandlerManager					  *
	 **********************************************************************/


	@Override
	public void addCommHandler(String roomId, ExtGWTWMHasCommHandler handler) {
		Stack<ExtGWTWMHasCommHandler> stack = getCommStack(roomId);
		if (!stack.contains(handler)) {
			stack.add(handler);
		}
	}

	@Override
	public void removeCommHandler(String roomId, ExtGWTWMHasCommHandler handler) {
		getCommStack(roomId).remove(handler);
	}
	
	@Override
	public List<ExtGWTWMHasOpenCommHandler> getCommOpenHandlers(String roomId) {
		List<ExtGWTWMHasOpenCommHandler> handlerList = new ArrayList<>();
		if (roomId != null) {
			Stack<ExtGWTWMHasCommHandler> stack = getCommStack(roomId);
			if (stack != null) {
				Iterator<ExtGWTWMHasCommHandler> iter = stack.iterator();
				while (iter.hasNext()) {
					ExtGWTWMHasCommHandler handler = iter.next();
					if (handler instanceof ExtGWTWMHasOpenCommHandler || handler instanceof ExtGWTWMHasAllCommHandler) {
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
			Stack<ExtGWTWMHasCommHandler> stack = getCommStack(roomId);
			if (stack != null) {
				Iterator<ExtGWTWMHasCommHandler> iter = stack.iterator();
				while (iter.hasNext()) {
					ExtGWTWMHasCommHandler handler = iter.next();
					if (handler instanceof ExtGWTWMHasCloseCommHandler || handler instanceof ExtGWTWMHasAllCommHandler) {
						handlerList.add((ExtGWTWMHasCloseCommHandler)handler);
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
			Stack<ExtGWTWMHasCommHandler> stack = getCommStack(roomId);
			if (stack != null) {
				Iterator<ExtGWTWMHasCommHandler> iter = stack.iterator();
				while (iter.hasNext()) {
					ExtGWTWMHasCommHandler handler = iter.next();
					if (handler instanceof ExtGWTWMHasReceiveCommHandler || handler instanceof ExtGWTWMHasAllCommHandler) {
						handlerList.add((ExtGWTWMHasReceiveCommHandler)handler);
					}
				}
			}
		}
		return handlerList;
	}
	
	@Override
	public void releaseComm(String roomId) {
		removeCommStack(roomId);
	}
	
	/**********************************************************************
	 *                    ExtGWTWMCErrorHandlerManager					  *
	 **********************************************************************/

	@Override
	public void addErrorHandler(String roomId, ExtGWTWMHasErrorHandler handler) {
		Stack<ExtGWTWMHasErrorHandler> stack = getErrorStack(roomId);
		if (!stack.contains(handler)) {
			stack.add(handler);
		}
	}

	@Override
	public void removeErrorHandler(String roomId, ExtGWTWMHasErrorHandler handler) {
		getErrorStack(roomId).remove(handler);
	}

	@Override
	public List<ExtGWTWMHasErrorHandler> getErrorHandlers(String roomId) {
		Stack<ExtGWTWMHasErrorHandler> stack = getErrorStack(roomId);
		return new ArrayList<>(stack);
		
	}
	
	@Override
	public void releaseError(String roomId) {
		removeErrorStack(roomId);
	}
	
	/**********************************************************************
	 *                           PRIVATE FUNCTIONS						  *
	 **********************************************************************/
	
	private Stack<ExtGWTWMHasCommHandler> getCommStack(String stackName) {
		Stack<ExtGWTWMHasCommHandler> stack = getStacksCommMap().get(stackName);
		if (stack == null) {
			stack = new Stack<ExtGWTWMHasCommHandler>();
			getStacksCommMap().put(stackName, stack);
		}
		return stack;
	}
	
	private Stack<ExtGWTWMHasCommHandler> removeCommStack(String stackName) {
		Stack<ExtGWTWMHasCommHandler> stack = getStacksCommMap().get(stackName);
		if (stack != null) {
			getStacksCommMap().remove(stack);
			stack.clear();
			stack = null;
		}
		return stack;
	}
	
	private Stack<ExtGWTWMHasErrorHandler> getErrorStack(String stackName) {
		Stack<ExtGWTWMHasErrorHandler> stack = getStacksErrorMap().get(stackName);
		if (stack == null) {
			stack = new Stack<ExtGWTWMHasErrorHandler>();
			getStacksErrorMap().put(stackName, stack);
		}
		return stack;
	}
	
	private Stack<ExtGWTWMHasErrorHandler> removeErrorStack(String stackName) {
		Stack<ExtGWTWMHasErrorHandler> stack = getStacksErrorMap().get(stackName);
		if (stack != null) {
			getStacksErrorMap().remove(stack);
			stack.clear();
			stack = null;
		}
		return stack;
	}
	
	private Map<String, Stack<ExtGWTWMHasCommHandler>> getStacksCommMap() {
		if (mapCommHandlers == null) {
			mapCommHandlers = new HashMap<String, Stack<ExtGWTWMHasCommHandler>>();
		}
		return mapCommHandlers;
	}
	
	private Map<String, Stack<ExtGWTWMHasErrorHandler>> getStacksErrorMap() {
		if (mapErrorHandlers == null) {
			mapErrorHandlers = new HashMap<String, Stack<ExtGWTWMHasErrorHandler>>();
		}
		return mapErrorHandlers;
	}
}
