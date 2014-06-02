package com.imotion.gwt.webmessenger.client.common;

import java.io.Serializable;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;

public class ExtGWTWMCommand implements Serializable {

	// serial UID
	private static final long serialVersionUID = -1693375823225399642L;
	
	public enum COMMAND_TYPE {
		OPEN_CONNECTION,
		MESSAGE_HANDLER,
		CLOSE_CONNECTION,
		CLOSE_HANDLER,
	}
	
	private Timer 			action;
	private COMMAND_TYPE 	type;
	
	
	@SuppressWarnings("unused")
	private ExtGWTWMCommand() {
		// not allowed
	}
	public ExtGWTWMCommand(COMMAND_TYPE type) {
		this.type = type;
	}

	
	public void execute(final Command commad) {
		execute(commad, -1);
	}
	
	public void execute(final Command commad, int delay) {
		execute(commad, delay, -1);
	}
	
	public void execute(final Command commad, int delay, final int attemps) {
		action = new Timer() {
			private int counter = 0;
			@Override
			public void run() {
				if (counter < attemps) {
					commad.execute();
					counter = counter + 1;
				} else {
					cancel();
				}
			}
		};
		if (delay < 0 || attemps < 0) {
			action.run();
		} else {
			action.scheduleRepeating(delay);
		}
	}
	
	public void cancel() {
		action.cancel();
	}
	
	public void setType(COMMAND_TYPE type) {
		this.type = type;
	}
	
	public COMMAND_TYPE getType() {
		return this.type;
	}
}
