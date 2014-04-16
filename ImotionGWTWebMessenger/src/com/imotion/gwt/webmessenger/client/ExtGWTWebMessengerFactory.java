package com.imotion.gwt.webmessenger.client;

import com.google.gwt.core.client.GWT;

public class ExtGWTWebMessengerFactory {
	
	private static ExtGWTWebMessengerCommCS me;
	
	public static final ExtGWTWebMessengerCommCS getDefaultStandaloneCommCS() {
		if (me == null) {
			me = GWT.create(ExtGWTWebMessengerCommCS.class);
		}
		return me;
	}
	
	public static final ExtGWTWebMessengerCommCS getDefaultInstanceCommCS() {
		return GWT.create(ExtGWTWebMessengerCommCS.class);
	}
}
