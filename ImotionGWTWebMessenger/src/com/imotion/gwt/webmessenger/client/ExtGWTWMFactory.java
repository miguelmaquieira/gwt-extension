package com.imotion.gwt.webmessenger.client;

import com.google.gwt.core.client.GWT;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCS;

public class ExtGWTWMFactory {
	
	private static ExtGWTWMCommCS me;
	
	public static final ExtGWTWMCommCS getDefaultStandaloneCommCS() {
		if (me == null) {
			me = GWT.create(ExtGWTWMCommCS.class);
		}
		return me;
	}
	
	public static final ExtGWTWMCommCS getDefaultInstanceCommCS() {
		return GWT.create(ExtGWTWMCommCS.class);
	}
}
